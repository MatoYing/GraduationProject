package com.zust.ysc.aspect;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description
 * @Author 闫思潮
 * @Date 04/03/2023 5:22 pm
 */

@Component
@Aspect  // 声明切面类
public class CountSumAop {

   @Autowired
   // 注入MeterRegistry
   MeterRegistry registry;

   // 计数器
   private Counter counter_total;

   ThreadLocal<Long> startTime = new ThreadLocal<>();

   @Pointcut("execution(public * com.zust.ysc.controller.*.*(..))")
   // 定义一个切入点，表示切面作用于所有Controller层的公共方法
   private void pointCut(){}

   @PostConstruct
   // 当依赖(@Autowired)注入完成后用于执行初始化的方法，并且只会被执行一次
   // 顺序：Constructor >> @Autowired >> @PostConstruct，所以无法放到构造器中，得用这个
   public void init(){
      // 这步添加的参数，之后可以在Prometheus中操作
      counter_total = registry.counter("app_requests_count", "v1", "core");
   }

   @Before("pointCut()")
   // 前置通知，在目标方法执行前自动调用
   public void doBefore(JoinPoint joinPoint)throws Throwable {
      // 记录请求开始时间
      startTime.set(System.currentTimeMillis());
      // 增加请求总次数
      counter_total.increment();
   }

   @AfterReturning(returning = "returnVal", pointcut = "pointCut()")
   // 后置通知，在目标方法正常返回后自动调用
   public void doAftereReturning(Object returnVal){
      // 计算请求执行时间并输出
      System.out.println("请求执行时间：" + (System.currentTimeMillis() - startTime.get()));
   }
}
