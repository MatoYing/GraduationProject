package com.zust.ysc.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;

/**
 * @Description 将利用Jackson返回的JSON的null值转换为空字符串(xxx: null -> xxx: "")
 *              为什么？前后台传值，null会变成"null"，这是由于JSON的原理，只能变成字符串或数字的形式。
 *              具体批注不去理解了，太高级了(批注来时chatGPT)
 * @Author Github: MatoYing
 * @Date 27/02/2023 4:00 pm
 */

@Configuration
public class JacksonConfig {
   @Bean
   // 使用@Primary注解将这个bean标记为首选（primary）的bean，
   // 当有多个相同类型的bean时，会优先选择这个bean。
   @Primary
   // 使用@ConditionalOnMissingBean注解来定义一个条件，
   // 当容器中不存在ObjectMapper类型的bean时才创建这个bean。
   @ConditionalOnMissingBean(ObjectMapper.class)
   public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
      // 使用传入的Jackson2ObjectMapperBuilder对象来创建ObjectMapper实例，
      // 并禁用XML序列化器。
      ObjectMapper objectMapper = builder.createXmlMapper(false).build();
      // 配置一个NullValueSerializer，用于将空值序列化为空字符串。
      objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
         @Override
         public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString("");
         }
      });
      // 返回创建好的ObjectMapper实例。
      return objectMapper;
   }
}
