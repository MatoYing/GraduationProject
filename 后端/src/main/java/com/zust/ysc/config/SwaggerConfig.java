package com.zust.ysc.config;

import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * @Description 访问地址http://localhost:8088/swagger-ui/index.html
 * @Author Github: MatoYing
 * @Date 19/03/2023 1:58 am
 */

@EnableOpenApi
@Configuration
public class SwaggerConfig {
   /**
    * 配置基本信息
    * @return
    */
   @Bean
   public ApiInfo apiInfo() {
      return new ApiInfoBuilder()
              .title("Swagger Test App Restful API")
              .description("swagger test app restful api")
              .termsOfServiceUrl("https://localhost:8088")
              .contact(new Contact("深入技术架构","https://blog.csdn.net","xxx@csdn.net"))
              .version("1.0")
              .build();
   }

   /**
    * 配置文档生成最佳实践
    * @param apiInfo
    * @return
    */
   @Bean
   public Docket createRestApi(ApiInfo apiInfo) {
      return new Docket(DocumentationType.OAS_30)
              .apiInfo(apiInfo)
              .groupName("SwaggerGroupOneAPI")
              .select()
              .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
              .paths(PathSelectors.any())
              .build();
   }

   /**
    * 增加如下配置可解决SpringBoot与Swagger3不兼容问题
    **/
   @Bean
   public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier, ServletEndpointsSupplier servletEndpointsSupplier, ControllerEndpointsSupplier controllerEndpointsSupplier, EndpointMediaTypes endpointMediaTypes, CorsEndpointProperties corsProperties, WebEndpointProperties webEndpointProperties, Environment environment) {
      List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
      Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
      allEndpoints.addAll(webEndpoints);
      allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
      allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
      String basePath = webEndpointProperties.getBasePath();
      EndpointMapping endpointMapping = new EndpointMapping(basePath);
      boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
      return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes, corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath), shouldRegisterLinksMapping, null);
   }
   private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment, String basePath) {
      return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath) || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
   }
}
