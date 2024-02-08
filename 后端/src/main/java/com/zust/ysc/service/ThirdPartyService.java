package com.zust.ysc.service;

/**
 * @Description
 * @Author 闫思潮
 * @Date 03/04/2023 11:36 pm
 */

public interface ThirdPartyService {

   /**
    * 发送验证码
    * @param code
    * @param phone
    */
   void sendCode(int code, String phone);

   /**
    * 获取钉钉openId
    * (为什么钉钉的放在Service，而QQ没有？因为QQ的要用Request，钉钉不用，只用一个字符串就可以接)
    * @param authCode
    * @return
    */
   String getDingDingID(String authCode);

   /**
    * 通过钉钉机器人发送消息
    * @param message
    */
   void sendMessageByDingDing(String message, String phone);

   void sendEmail(String email, String message);
}
