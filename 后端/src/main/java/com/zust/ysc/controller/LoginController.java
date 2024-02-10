package com.zust.ysc.controller;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;
import com.zust.ysc.dao.UserDao;
import com.zust.ysc.entity.JsonResult;
import com.zust.ysc.entity.User;
import com.zust.ysc.service.ThirdPartyService;
import com.zust.ysc.utils.SnowflakeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.subject.Subject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Description
 * @Author Github: MatoYing
 * @Date 27/02/2023 4:04 am
 */

@RestController  // Controller + ResponseBody(默认使用Jackson)
@RequestMapping("/login")  // 父路径只能RequestMapping
@Api(value = "登录注册模块")  // 路径是http://localhost:8080/swagger-ui.html
@Slf4j
public class LoginController {

    @Autowired
    UserDao userDao;

    @Autowired
    private SnowflakeUtil snowflakeUtil;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ThirdPartyService thirdPartyService;


    @ApiOperation(value = "注册用户信息", notes = "无")
    @PostMapping("/register")
    public JsonResult register(@RequestBody Map map, HttpSession session) {
        String captcha = (String) map.get("captcha");
        String password = (String) map.get("password");
        String phone = (String) map.get("phone");
        // 获得用户SessionID
        String sessionID = session.getId();
        // 从Redis中获得之前申请的验证码
        String real_captcha = (String) stringRedisTemplate.opsForHash().get("spring:session:sessions:" + sessionID, "sessionAttr:captcha");
        // 可能因为QQ登录跳转过来得，提取出QQ
        String QQ = (String) stringRedisTemplate.opsForHash().get("spring:session:sessions:" + sessionID, "sessionAttr:QQ");
        // 可能因为DingDing登录跳转过来得，提取出DingDing
        String DingDing = (String) stringRedisTemplate.opsForHash().get("spring:session:sessions:" + sessionID, "sessionAttr:DingDing");
        System.out.println("DD:" + DingDing);
        // 判断验证码
        if (captcha.equals(real_captcha)) {
            // 验证码正确
            User user = userDao.selectUserByPhone(phone);
            if (user == null) {
                // 用户不存在
                return new JsonResult<>(0, "该用户无法注册，因为谁能注册提前已经定好了");
            } else {
                if (user.getPassword() != null || user.getPassword() == "") {
                    System.out.println("password:" + user.getPassword());
                    // 用户已经注册
                    // 更改密码
                    userDao.updatePassword(phone, password);
                    // 将用户信息放入session中
                    session.setAttribute("ID", user.getID());
                    session.setAttribute("phone", user.getPhone());
                    session.setAttribute("password", password);
                    session.setAttribute("name", user.getName());
                    session.setAttribute("role", user.getRole());
                    session.setAttribute("permission", user.getPermission());
                    session.setAttribute("QQ", user.getQQ());
                    session.setAttribute("DingDing", user.getDingDing());
                    return new JsonResult<>(1, "该用户已经注册，自动把密码改了，跳转到index");
                } else {
                    System.out.println("password:" + user.getPassword());
                    // 用户未注册，插入用户数据
                    String ID = String.valueOf(snowflakeUtil.nextId());
                    userDao.insertUser2(ID, phone, password, QQ, DingDing);
                    session.setAttribute("ID", ID);
                    session.setAttribute("phone", phone);
                    session.setAttribute("password", password);
                    session.setAttribute("name", user.getName());
                    session.setAttribute("role", user.getRole());
                    session.setAttribute("permission", user.getPermission());
                    session.setAttribute("QQ", user.getQQ());
                    return new JsonResult<>(2, "该用户注册成功，跳转到index");
                }
            }
        } else {
            // 验证码错误
            return new JsonResult<>(3, "验证码错误");
        }
    }



    /**
     * 接入阿里云，获取验证码
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/getVerificationCode")
    public JsonResult getVerificationCode(@RequestBody Map map, HttpSession session) {
        String phone = (String) map.get("phone");
        Random random = new Random();
        // 生成四位随机数(加1000是为了防止生成的随机数不是是四位数)
        int randomNumber = random.nextInt(9000) + 1000;
        // 将验证码存入Session中，之后通过SessionID获取来验证
        session.setAttribute("captcha", randomNumber);
        // 发送验证码
        thirdPartyService.sendCode(randomNumber, phone);
//        Cookie cookie = new Cookie("test","same");
//        cookie.setPath("/");
//        response.addCookie(cookie);
        return new JsonResult<>(1, "验证码发送成功");
    }

//    对于tomcat容器来说，当服务端的session被创建时，Response中自动添加了一个Cookie：JSESSIONID：xxxx,
//    再后续的请求中，浏览器也是自动的带上了这个Cookie，服务端根据Cookie中的JSESSIONID取到了对应的session。
//    这验证了一开始的说法，客户端服务端是通过JSESSIONID进行交互的，并且，
//    添加和携带key为JSESSIONID的Cookie都是tomcat和浏览器自动帮助我们完成的，这很关键。




    /**
     * 获取QQ登录链接
     * @param request
     * @return QQ登录链接
     * @throws QQConnectException
     */
    @PostMapping("/loginByQQ")
    public JsonResult QQLogin(HttpServletRequest request) throws QQConnectException {
        // 获取授权链接
        String authorizeUrl = new Oauth().getAuthorizeURL(request);
        // 重定向到授权链接
        System.out.println(authorizeUrl);
        return new JsonResult<>(authorizeUrl, "获取QQ登录链接成功");
    }



    /**
     * QQ回调方法
     * @param request
     * @return QQ用户信息
     * @throws QQConnectException
     */
    @GetMapping("/callback")  // 必须GET，因为QQ要回调，它要走GET请求
    public void callbackForQQ(HttpServletRequest request, HttpServletResponse resp,HttpSession session) throws QQConnectException, IOException {
        // 通过回调中的code得到accessToken
        AccessToken accessTokenObj = new Oauth().getAccessTokenByRequest(request);
        String accessToken = accessTokenObj.getAccessToken();
        if(accessToken == null || "".equals(accessToken)){
            throw new QQConnectException("accessToken为空，授权失败");
        }
        // 通过accessToken得到openId
        OpenID openIdObj = new OpenID(accessToken);
        if(openIdObj == null || "".equals(openIdObj.getUserOpenID())){
            throw new QQConnectException("openIdObj为空，授权失败");
        }
        String openId = openIdObj.getUserOpenID();
//        // 通过accessToken和openId得到用户信息
//        UserInfo qzoneUserInfo = new UserInfo(accessToken, openId);
//        UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
//        if(userInfoBean == null || userInfoBean.getRet() != 0){
//            throw new QQConnectException(userInfoBean.getMsg()+",授权失败");
//        }
//        //得到用户昵称
//        String nickname = userInfoBean.getNickname();
//        String imgUrl = userInfoBean.getAvatar().getAvatarURL30();
        ///////////////////////////上面主要是拿到OpenID，下面开始逻辑////////////////////////////////
        // 检测OpenID是否存在
        List<String> QQList = userDao.selectAllQQ();
        if (QQList.contains(openId)) {
            // OpenID存在，说明用户已经注册过了，直接登录
            // 获取用户所有数据存入Session中
            User user = userDao.selectUserByDingDing(openId);
            session.setAttribute("ID", user.getID());
            session.setAttribute("phone", user.getPhone());
            session.setAttribute("password", user.getPassword());
            session.setAttribute("name", user.getName());
            session.setAttribute("role", user.getRole());
            session.setAttribute("permission", user.getPermission());
            session.setAttribute("QQ", user.getQQ());
            session.setAttribute("DingDing", user.getDingDing());
            // 跳转index界面
            resp.sendRedirect("http://127.0.0.1:5500/index.html");
        } else {
            // OpenID不存在，说明用户没有注册过，跳转到注册页面
            session.setAttribute("QQ", openId);
            // 跳转注册界面
            resp.sendRedirect("http://127.0.0.1:5500/login_module/sign-up.html");
        }
    }

    /**
     * 钉钉回调方法
     * @param authCode
     * @param session
     * @return
     */
    @GetMapping("/callback2")
    public void callbackForDingDing(@RequestParam(value = "authCode")String authCode, HttpServletResponse resp, HttpSession session) throws IOException {
        String openId = thirdPartyService.getDingDingID(authCode);
        List<String> DingDingList = userDao.selectAllDingDing();
        if (DingDingList.contains(openId)) {
            // OpenID存在，说明用户已经注册过了，直接登录
            // 获取用户所有数据存入Session中
            User user = userDao.selectUserByDingDing(openId);
            session.setAttribute("ID", user.getID());
            session.setAttribute("phone", user.getPhone());
            session.setAttribute("password", user.getPassword());
            session.setAttribute("name", user.getName());
            session.setAttribute("role", user.getRole());
            session.setAttribute("permission", user.getPermission());
            session.setAttribute("QQ", user.getQQ());
            session.setAttribute("DingDing", user.getDingDing());
            // 对汉字编码
            String encodedName = URLEncoder.encode(user.getName(), "UTF-8");
            // 跳转index界面
            resp.sendRedirect("http://47.115.226.81/index.html?name=" + encodedName);
        } else {
            // OpenID不存在，说明用户没有注册过，跳转到注册页面
            System.out.println("shibai");
            session.setAttribute("DingDing", openId);
            // 跳转注册界面
            resp.sendRedirect("http://127.0.0.1:5500/login_module/sign-up.html");
//            resp.sendRedirect("http://127.0.0.1:5500/login_module/sign-up.html");
        }
    }

    /**
     * 普通登录
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/login")
    public JsonResult login(@RequestBody Map map, HttpSession session) {
        System.out.println("laile");
        String phone = (String) map.get("phone");
        String password = (String) map.get("password");
        User user = userDao.selectUserByPhone(phone);
        if (user == null) {
            // 用户不存在
            return new JsonResult<>(1, "用户不存在");
        } else if (!user.getPassword().equals(password)) {
            // 密码错误
            return new JsonResult<>(2, "密码错误");
        } else {
            // 登录成功
            // 获取用户所有数据存入Session中
            session.setAttribute("ID", user.getID());
            session.setAttribute("phone", user.getPhone());
            session.setAttribute("password", user.getPassword());
            session.setAttribute("name", user.getName());
            session.setAttribute("role", user.getRole());
            session.setAttribute("permission", user.getPermission());
            session.setAttribute("QQ", user.getQQ());
            session.setAttribute("DingDing", user.getDingDing());
            return new JsonResult<>(3, "登录成功，跳转用户页面");
        }
    }



























    ////////////////////////////Shiro的东西/////////////////////////////////////

    /**
     * 未登录，跳转到登录页面
     * @return
     */
    @PostMapping("/toLogin")
    public JsonResult toLogin() {
        return new JsonResult<>(401);
    }



    /**
     * 未授权，跳转到未授权页面
     * @return
     */
    @PostMapping("/toAuthorization")
    public JsonResult toAuthorization() {
        return new JsonResult<>(403);
    }



    /**
     * 退出登录，跳转到登录页面
     * @return
     */
//    @PostMapping("/logout")
//    public JsonResult logout() {
//        SecurityUtils.getSubject().logout();
//        return new JsonResult<>(204);
//    }

    @GetMapping("/test1")
    public JsonResult test1() {
        // TODO 发送验证码
        log.error("测试");
        return new JsonResult<>(1);
    }














    //    @PostMapping("/login")
//    @ApiOperation("用户登录")
//    public JsonResult login(@RequestBody Map map) {
//        String phone = (String) map.get("phone");
//        String password = (String) map.get("password");
//        // 获取/创造当前用户
//        Subject subject = SecurityUtils.getSubject();
//        // 封装用户数据
//        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(phone, password);
//        try {
//            // 执行登录方法，如果没有异常就说明ok了；这里其实就是调用了AccountRealm中的doGetAuthenticationInfo方法
//            subject.login(usernamePasswordToken);
//            return new JsonResult<>(200);
//        } catch (UnknownAccountException e) {
//            // 用户名不存在
//            return new JsonResult<>(401);
//        } catch (IncorrectCredentialsException e) {
//            // 密码错误
//            return new JsonResult<>(402);
//        }
//    }

}
