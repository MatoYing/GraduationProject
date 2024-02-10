package com.zust.ysc.service.impl;

import com.aliyun.dingtalkcontact_1_0.models.GetUserHeaders;
import com.aliyun.dingtalkoauth2_1_0.models.GetUserTokenRequest;
import com.aliyun.dingtalkoauth2_1_0.models.GetUserTokenResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.Common;
import com.aliyun.teautil.models.RuntimeOptions;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.zust.ysc.service.ThirdPartyService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Properties;

/**
 * @Description
 * @Author Github: MatoYing
 * @Date 03/04/2023 11:39 pm
 */

@Service("CommonService")
public class ThirdPartyServiceImpl implements ThirdPartyService {

   ///////////////////////////////阿里云短信//////////////////////////////////////
   public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) {
      com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
              // 必填，您的 AccessKey ID
              .setAccessKeyId(accessKeyId)
              // 必填，您的 AccessKey Secret
              .setAccessKeySecret(accessKeySecret);
      // 访问的域名
      config.endpoint = "dysmsapi.aliyuncs.com";
      try {
         return new com.aliyun.dysmsapi20170525.Client(config);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }
   @Override
   public void sendCode(int code, String phone) {
      // 工程代码泄露可能会导致AccessKey泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考，建议使用更安全的 STS 方式，更多鉴权访问方式请参见：https://help.aliyun.com/document_detail/378657.html
      com.aliyun.dysmsapi20170525.Client client = createClient("LTAI5tKZsifWdfjamh26QUxU", "y9O1zLm5jatYrmr6IKPq7f5CDBbj2S");
      com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
              .setSignName("基础平台动态监管系统")
              .setTemplateCode("SMS_275295435")
              .setPhoneNumbers(phone)
              .setTemplateParam("{\"code\":\"" + code + "\"}");
      com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
      System.out.println(phone);
      try {
         // 复制代码运行请自行打印 API 的返回值
         client.sendSmsWithOptions(sendSmsRequest, runtime);
      } catch (TeaException error) {
         // 如有需要，请打印 error
         System.out.println(Common.assertAsString(error.message));
      } catch (Exception _error) {
         TeaException error = new TeaException(_error.getMessage(), _error);
         // 如有需要，请打印 error
         System.out.println(com.aliyun.teautil.Common.assertAsString(error.message));
      }
      System.out.println(phone);
   }


   ////////////////////////////////钉钉登录/////////////////////////////////////////////
   public static com.aliyun.dingtalkoauth2_1_0.Client authClient() throws Exception {
      Config config = new Config();
      config.protocol = "https";
      config.regionId = "central";
      return new com.aliyun.dingtalkoauth2_1_0.Client(config);
   }

   public static com.aliyun.dingtalkcontact_1_0.Client contactClient() throws Exception {
      Config config = new Config();
      config.protocol = "https";
      config.regionId = "central";
      return new com.aliyun.dingtalkcontact_1_0.Client(config);
   }

   @Override
   public String getDingDingID(String authCode) {
      com.aliyun.dingtalkoauth2_1_0.Client client = null;
      try {
         client = authClient();
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
      GetUserTokenRequest getUserTokenRequest = new GetUserTokenRequest()
              //应用基础信息-应用信息的AppKey,请务必替换为开发的应用AppKey
              .setClientId("ding91tq9woqjqwy8d3i")
              //应用基础信息-应用信息的AppSecret，,请务必替换为开发的应用AppSecret
              .setClientSecret("1OmLKrsSL3etSlq3EFssMDJ0PAvUojiBLRggi-0PI5kMYM3E4YIt7DjK6M4AgLHW")
              .setCode(authCode)
              .setGrantType("authorization_code");
      GetUserTokenResponse getUserTokenResponse = null;
      try {
         getUserTokenResponse = client.getUserToken(getUserTokenRequest);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
      //获取用户个人token
      String accessToken = getUserTokenResponse.getBody().getAccessToken();

      com.aliyun.dingtalkcontact_1_0.Client client2 = null;
      try {
         client2 = contactClient();
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
      GetUserHeaders getUserHeaders = new GetUserHeaders();
      getUserHeaders.xAcsDingtalkAccessToken = accessToken;
      //获取用户个人信息，如需获取当前授权人的信息，unionId参数必须传me
      String openId = null;
      try {
         openId = client2.getUserWithOptions("me", getUserHeaders, new RuntimeOptions()).getBody().getOpenId();
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
      return openId;
   }

   //////////////////////////////////钉钉机器人通知////////////////////////////////////////
   private static final String SECRET = "SEC48d4fcf281e41b9b74fde0a2a1a0c8df63aac76be9f29c35d4fc40b5a42540b8";
   private static final String URL = "https://oapi.dingtalk.com/robot/send?access_token=5c3521c6b5834225943fc9605136f2ccddf44e93197c628a5f8263749c1d8a5f";
   @Override
   public void sendMessageByDingDing(String message, String phone) {
      Long timestamp = System.currentTimeMillis();
      String stringToSign = timestamp + "\n" + SECRET;
      try {
         // 调用Mac.getInstance("HmacSHA256")获取Mac实例，指定使用HmacSHA256算法
         Mac mac = Mac.getInstance("HmacSHA256");
         // 使用指定密钥和算法对指定字符串进行加密
         mac.init(new SecretKeySpec(SECRET.getBytes("UTF-8"), "HmacSHA256"));
         byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
         String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
         // 根据签名和时间戳构建请求参数
         String signResult = "&timestamp=" + timestamp + "&sign=" + sign;
         // 得到拼接后的url
         String url = URL + signResult;
         // 通过DingTalkClient构建请求URL
         DingTalkClient client = new DefaultDingTalkClient(url);
         OapiRobotSendRequest request = new OapiRobotSendRequest();
         request.setMsgtype("text");
         // 添加文本
         OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
         text.setContent(message);
         request.setText(text);
         // 添加要@的人
         OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
         at.setAtMobiles(Arrays.asList("13333461340"));
         request.setAt(at);

         // 发送通知为链接
//         request.setMsgtype("link");
//         OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
//         link.setMessageUrl("https://www.dingtalk.com/");
//         link.setPicUrl("");
//         link.setTitle("时代的火车向前开");
//         link.setText("这个即将发布的新版本，创始人xx称它为红树林。而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是红树林");
//         request.setLink(link);

         // 发送通知为MarkDown
//         request.setMsgtype("markdown");
//         OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
//         markdown.setTitle("杭州天气");
//         markdown.setText("#### 杭州天气 @156xxxx8827\n" +
//                 "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
//                 "> ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\n" +
//                 "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n");
//         request.setMarkdown(markdown);
         OapiRobotSendResponse response = client.execute(request);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }



   //////////////////////////////////////////////QQ发送邮件///////////////////////////////////////////
   @Override
   public void sendEmail(String email, String content) {
      // 创建Properties 类用于记录邮箱的一些属性
      Properties props = new Properties();
      // 表示SMTP发送邮件，必须进行身份验证
      props.put("mail.smtp.auth", "true");
      //此处填写SMTP服务器
      props.put("mail.smtp.host", "smtp.qq.com");
      //端口号，QQ邮箱端口587
      props.put("mail.smtp.port", "587");
      // 此处填写，写信人的账号(改邮箱账号)
      props.put("mail.user", "1350478903@qq.com");
      // 此处填写16位STMP口令（改16位）
      props.put("mail.password", "asyjclilothufihg");
      // 构建授权信息，用于进行SMTP进行身份验证
      Authenticator authenticator = new Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            // 用户名、密码
            String userName = props.getProperty("mail.user");
            String password = props.getProperty("mail.password");
            return new PasswordAuthentication(userName, password);
         }
      };
      // 使用环境属性和授权信息，创建邮件会话
      Session mailSession = Session.getInstance(props, authenticator);
      // 创建邮件消息
      MimeMessage message = new MimeMessage(mailSession);
      try {
         // 设置发件人
         InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
         message.setFrom(form);
         // 设置收件人的邮箱(改)
         InternetAddress to = new InternetAddress(email);
         message.setRecipient(MimeMessage.RecipientType.TO, to);
         // 设置邮件标题
         message.setSubject("基础平台监管系统报警通知");
         // 设置邮件的内容体
         message.setContent(content, "text/html;charset=UTF-8");
         // 最后当然就是发送邮件啦
         Transport.send(message);
      } catch (AddressException e) {
         e.printStackTrace();
      } catch (MessagingException e) {
         e.printStackTrace();
      }
   }
}
