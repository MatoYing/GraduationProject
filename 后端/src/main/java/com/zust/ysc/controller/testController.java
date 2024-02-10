package com.zust.ysc.controller;

import com.zust.ysc.dao.AlertDao;
import com.zust.ysc.dao.TestDao;
import com.zust.ysc.entity.Alert;
import com.zust.ysc.entity.JsonResult;
import com.zust.ysc.service.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author Github: MatoYing
 * @Date 20/05/2023 3:27 pm
 */

@RestController
@RequestMapping("/test")
public class testController {

   @Autowired
   AlertDao alertDao;

   @Autowired
   TestDao testDao;

   @Autowired
   ThirdPartyService thirdPartyService;

   @PostMapping("/tellDing")
   public JsonResult getMonthReport(@RequestBody Map map) {
      String name = (String) map.get("name");
      String phone = testDao.getPhone(name);
      thirdPartyService.sendMessageByDingDing("请检查报警", phone);
      return new JsonResult<>(0, "你的报警信息");
   }
}
