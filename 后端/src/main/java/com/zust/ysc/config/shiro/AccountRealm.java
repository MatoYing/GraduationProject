//package com.zust.ysc.config.shiro;
//
//import com.zust.ysc.dao.LoginDao;
//import com.zust.ysc.entity.User;
//import com.zust.ysc.service.LoginService;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.subject.Subject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.data.redis.core.StringRedisTemplate;
//
///**
// * @Description
// * @Author 闫思潮
// * @Date 19/03/2023 12:19 am
// */
//
//public class AccountRealm extends AuthorizingRealm {
//
//   @Autowired
//   LoginDao loginDao;
//
//   /**
//    * 授权方法
//    */
//   @Override
//   protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//
//      SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//      // 拿到当前登录的这个对象
//      Subject subject = SecurityUtils.getSubject();
//      // 拿到user对象
//      User currentUser = (User) subject.getPrincipal();
//      // 设置当前用户的角色
//      simpleAuthorizationInfo.addRole(currentUser.getRole());
//      // 设置当前用户的权限
//      simpleAuthorizationInfo.addStringPermission(currentUser.getPermission());
//      return simpleAuthorizationInfo;
//   }
//
//   /**
//    * 认证方法
//    */
//   @Override
//   protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//
//      System.out.println("执行了=>认证doGetAuthorizationInfo");
//
//      UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
//
//      // 拿取数据库中的用户信息
//      User user = loginDao.selectUser(usernamePasswordToken.getUsername());
//
//      //没有这个人
//      if (user == null) {
//         return null;
//      }
//
//      // TODO 连接Redis，放Session
//      // Subject currentSubject = SecurityUtils.getSubject();
//      // Session session = currentSubject.getSession();
//      // session.setAttribute("loginUser",user);
//
//
//      // 密码认证，shiro做
//      // 这里做完之后，直接接到你的Controller中
//      return new SimpleAuthenticationInfo(user,user.getPassword(),"");
//   }
//}
