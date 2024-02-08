package com.zust.ysc.controller;

import com.zust.ysc.dao.AlertDao;
import com.zust.ysc.entity.JsonResult;
import com.zust.ysc.entity.Alert;
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
 * @Author 闫思潮
 * @Date 13/05/2023 1:04 am
 */

@RestController
@RequestMapping("/alert")
public class AlertController {

   @Autowired
   AlertDao alertDao;

   @PostMapping("/getAlertFromMonitor")
   public JsonResult geAlertFromMonitor() {


      List<Alert> list = alertDao.getAlertFromMonitor();

      // knowledge从knowledgeDao拿
      return new JsonResult<>(list, "指标的报警");
   }

   @PostMapping("/updateNote")
   public JsonResult updateNote(@RequestBody Map map) {
      String note = (String) map.get("note");
      int id = (Integer) map.get("id");
      alertDao.updateNote(id, note);
      return new JsonResult<>(0, "更改备注成功");
   }

   @PostMapping("/getAlertFromLog")
   public JsonResult geAlertFromLog() {
      List<Alert> list = alertDao.getAlertFromLog();
      // knowledge从knowledgeDao拿
      return new JsonResult<>(list, "日志的报警");
   }

   @PostMapping("/getAlert")
   public JsonResult getAlert() {
      List<Alert> list = alertDao.getAlert();
      // knowledge从knowledgeDao拿
      return new JsonResult<>(list, "所有的报警");
   }

   @PostMapping("/addRule")
   public JsonResult addRule(@RequestBody Map map) {
      String name = (String) map.get("name");
      String responsible_name = (String) map.get("responsible_person");
      String severity = (String) map.get("severity");
      String rule = (String) map.get("summary");
      String note = (String) map.get("note");
      String add_people = (String) map.get("add_people");
      String monitor_name = (String) map.get("monitor_name");
      alertDao.insertRule(name, responsible_name, severity, rule, note, add_people, monitor_name);
      return new JsonResult<>(0, "添加规则");
   }

   @PostMapping("/deleteRule")
   public JsonResult deleteRule(@RequestBody Map map) {
      String name = (String) map.get("name");
      alertDao.deleteRule(name);
      return new JsonResult<>(0, "删除规则");
   }

   @PostMapping("/addRule2")
   public JsonResult addRule2(@RequestBody Map map) {
      String name = (String) map.get("name");
      String responsible_name = (String) map.get("responsible_person");
      String severity = (String) map.get("severity");
      String rule = (String) map.get("summary");
      String note = (String) map.get("note");
      String add_people = (String) map.get("add_people");
      String monitor_name = (String) map.get("monitor_name");
      alertDao.insertRuleLog(name, responsible_name, severity, rule, note, add_people, monitor_name);
      return new JsonResult<>(0, "添加规则");
   }

   @PostMapping("/submitAlert")
   public JsonResult submitAlert(@RequestBody Map map) {
      int id  = (Integer) map.get("id");
      String submit_note = (String) map.get("submit_note");
      alertDao.submitAlert(id, submit_note);
      return new JsonResult<>(0, "添加规则");
   }




}
