package com.zust.ysc;

import io.micrometer.core.instrument.MeterRegistry;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@MapperScan("com.zust.ysc.dao")
public class YscApplication {

    public static void main(String[] args) {
        SpringApplication.run(YscApplication.class, args);
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String applicationName) {
        // http://localhost:8080/actuator/prometheus可以访问到数据
        return registry -> registry.config().commonTags("application", applicationName);
    }

    // 原版应该是这么写，挺高级的，返回了一个匿名函数
//    @Bean
//    MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String applicationName) {
//        return new MeterRegistryCustomizer<MeterRegistry>() {
//            @Override
//            public void customize(MeterRegistry registry) {
//                registry.config().commonTags("application", applicationName);
//            }
//        };
//    }


}
