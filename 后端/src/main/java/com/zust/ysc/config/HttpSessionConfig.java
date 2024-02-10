package com.zust.ysc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Description
 * @Author Github: MatoYing
 * @Date 21/03/2023 11:54 pm
 */

@Configuration
// 启用了Spring Session Redis支持，并设置session过期时间,默认是1800秒(这意味着如果HttpSession在30分钟内没有被使用，它将自动过期并被删除)
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60 * 60)
public class HttpSessionConfig {

   // 禁用Spring Boot自动配置的Redis操作
   @Bean
   public static ConfigureRedisAction configureRedisAction() {
      return ConfigureRedisAction.NO_OP;
   }

   // 更换序列化器，SpringSession中默认的序列化器为jdk序列化器，该序列化器效率低下，内存占用大，而且会乱码
   @Bean("springSessionDefaultRedisSerializer")
   public RedisSerializer setSerializer(){
      return new GenericJackson2JsonRedisSerializer();
   }
}