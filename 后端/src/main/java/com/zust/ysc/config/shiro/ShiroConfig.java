//package com.zust.ysc.config.shiro;
//
//import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
//import org.apache.shiro.mgt.DefaultSubjectDAO;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
//import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//import javax.servlet.Filter;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
///**
// * @Description
// * @Author Github: MatoYing
// * @Date 17/03/2023 5:05 am
// */
//
//@Configuration
//public class ShiroConfig {
//
//    // Realm负责我们的认证与授权
//    @Bean
//    AccountRealm accountRealm() {
//        return new AccountRealm();
//    }
//
//    // Shiro的核心管理器
//    @Bean
//    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("accountRealm") AccountRealm accountRealm) {
//        // 就算不加@Qualifier也是可以依赖注入的，只要让Spring接管就行，也就是@Bean
//        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
//        defaultWebSecurityManager.setRealm(accountRealm);
//        return defaultWebSecurityManager;
//    }
//
//    // 过滤器配置，也就是拦截什么样的请求
//    @Bean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        // 设置安全管理器
//        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
//        /*
//            TODO 这里用注解实现：@RequiresRoles(角色)和@RequiresPermissions(权限)
//            有了角色为什么还要权限呢？比如一个角色是user，但是有一个user比较特别，想单给他独加一个权限，就可以用权限来区分
//            @RequiresRoles("user")     //具有user角色可以访问
//            @RequiresRoles(value = {"admin","user"},logical = Logical.AND)//用来判断是否拥有角色  同时具有admin 和 user角色才能访问
//            @RequiresRoles(value = {"admin","user"},logical = Logical.OR)//用来判断是否拥有角色  具有admin 或 user其中一角色才能访问
//            @RequiresPermissions("user:update:01")//拥有user:update:01操作权限才能访问
//            @RequiresPermissions(value = {"user:update:01","user:delete:*"},logical = Logical.AND)//同时拥有user:update:01和user:delete:*操作权限才能访问
//            @RequiresPermissions(value = {"user:update:01","user:delete:*"},logical = Logical.OR)//拥有user:update:01和user:delete:*其中一种操作权限才能访问
//         */
//        /*
//            anon：无需认证就可以访问
//            authc：必须认证了才能访问
//            user：必须拥有记住我功能才能用
//            perms：拥有对某个资源的权限才能访问（区分角色role和权限permission，一个角色你要具体设置其权限）
//            role：拥有某个角色权限
//         */
//        // /user/add这是者controller的路径，不是页面的路径
//        Map<String, String> filterMap = new LinkedHashMap<>();
//        filterMap.put("/*","anon");  // 谁都可以访问
////        filterMap.put("/user/update","authc");  必须认证了才能访问
////        filterMap.put("/user/add","perms[user:add]");  访问/user/add必须有这个权限；user:add这个名字看你自己怎么定
////        filterMap.put("/user/update","perms[user:update]");
////        filterMap.put("/user/*","authc");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
//
//        // 未登录的情况
//        shiroFilterFactoryBean.setLoginUrl("/login/toLogin");
//        // 未授权的情况
//        shiroFilterFactoryBean.setUnauthorizedUrl("/login/toAuthorization");
//
//        return shiroFilterFactoryBean;
//    }
//}