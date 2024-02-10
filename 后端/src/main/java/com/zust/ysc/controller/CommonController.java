package com.zust.ysc.controller;

import com.zust.ysc.dao.CommonDao;
import com.zust.ysc.entity.File;
import com.zust.ysc.entity.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author Github: MatoYing
 * @Date 10/05/2023 12:36 am
 */


@RestController
@RequestMapping("/common")
public class CommonController {

   @Autowired
   CommonDao commonDao;

   @PostMapping("/forIndex")
   public JsonResult getForIndex() {
      List<String> list = new ArrayList<>();
      list.add(String.valueOf(commonDao.countInspect()));
      list.add(String.valueOf(commonDao.countWarning()));
      list.add(String.valueOf(commonDao.countCritical()));
      list.add(String.valueOf(commonDao.countEmergency()));
      list.add(String.valueOf(commonDao.countNotWarning()));
      list.add(String.valueOf(commonDao.countNotCritical()));
      list.add(String.valueOf(commonDao.countNotEmergency()));
      list.add(String.valueOf(commonDao.countMonitor()));
      list.add(String.valueOf(commonDao.countLog()));
      list.add(String.valueOf(commonDao.countKnowledge()));
      // knowledge从knowledgeDao拿
      return new JsonResult<>(list, "index要的值");
   }

   @PostMapping("/forBig")
   public JsonResult getForBig() {
      List<String> list = new ArrayList<>();
      list.add(String.valueOf(commonDao.countDay1ForWarning()));
      list.add(String.valueOf(commonDao.countDay2ForWarning()));
      list.add(String.valueOf(commonDao.countDay3ForWarning()));
      list.add(String.valueOf(commonDao.countDay4ForWarning()));
      list.add(String.valueOf(commonDao.countDay5ForWarning()));
      list.add(String.valueOf(commonDao.countDay1ForCritical()));
      list.add(String.valueOf(commonDao.countDay2ForCritical()));
      list.add(String.valueOf(commonDao.countDay3ForCritical()));
      list.add(String.valueOf(commonDao.countDay4ForCritical()));
      list.add(String.valueOf(commonDao.countDay5ForCritical()));
      list.add(String.valueOf(commonDao.countDay1ForEmergency()));
      list.add(String.valueOf(commonDao.countDay2ForEmergency()));
      list.add(String.valueOf(commonDao.countDay3ForEmergency()));
      list.add(String.valueOf(commonDao.countDay4ForEmergency()));
      list.add(String.valueOf(commonDao.countDay5ForEmergency()));
      list.add(String.valueOf(commonDao.countTodayForFinishInspect()));
      list.add(String.valueOf(commonDao.countTodayForInspect()));
      list.add(String.valueOf(commonDao.countTodayForWarning()));
      list.add(String.valueOf(commonDao.countTodayForCritical()));
      list.add(String.valueOf(commonDao.countTodayForEmergency()));
      list.add(String.valueOf(commonDao.countTodayForFinishAlert()));
      return new JsonResult<>(list, "Big要的值");
   }

   @PostMapping("/forPerson")
   public JsonResult getForPerson(@RequestBody Map map) {
      String name = (String) map.get("name");
      List<String> list = new ArrayList<>();
      list.add(String.valueOf(commonDao.countInspectForPerson(name)));
      list.add(String.valueOf(commonDao.countWarningForPerson(name)));
      list.add(String.valueOf(commonDao.countCriticalForPerson(name)));
      list.add(String.valueOf(commonDao.countEmergencyForPerson(name)));
      return new JsonResult<>(list, "Big要的值");
   }




}
