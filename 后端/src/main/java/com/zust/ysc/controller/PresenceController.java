package com.zust.ysc.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author 闫思潮
 * @Date 05/03/2023 2:32 am
 */

@RestController
@RequestMapping("/presence")
public class PresenceController {
   @Autowired
   MeterRegistry registry;

   private Counter counter_core;
   private Counter counter_index;
   private AtomicInteger app_online_count;

   @PostConstruct
   private void init(){
      counter_core = registry.counter("app_requests_method_count", "method", "IndexController.core");
      counter_index = registry.counter("app_requests_method_count", "method", "IndexController.index");
      app_online_count = registry.gauge("app_online_count", new AtomicInteger(0));
   }

   @RequestMapping(value = "/index")
   public Object index(){
      try{
         counter_index.increment();
      } catch (Exception e) {
         return e;
      }
      return counter_index.count() + " index of springboot2-prometheus.";
   }

   @RequestMapping(value = "/core")
   public Object coreUrl(){
      try{
         counter_core.increment();
      } catch (Exception e) {
         return e;
      }
      return counter_core.count() + " coreUrl Monitor by Prometheus.";
   }

   @RequestMapping(value = "/online")
   public Object onlineCount(){
      int people = 0;
      try {
         people = new Random().nextInt(2000);
         app_online_count.set(people);
      } catch (Exception e){
         return e;
      }
      return "current online people: " + people;
   }
}
