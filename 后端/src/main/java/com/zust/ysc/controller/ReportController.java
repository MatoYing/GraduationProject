package com.zust.ysc.controller;

import cn.afterturn.easypoi.entity.ImageEntity;
import com.zust.ysc.dao.CommonDao;
import com.zust.ysc.dao.ReportDao;
import com.zust.ysc.entity.Alert;
import com.zust.ysc.entity.JsonResult;
import com.zust.ysc.entity.Report;
import com.zust.ysc.service.impl.MinIOServiceImpl;
import com.zust.ysc.utils.JfreeUtil;
import com.zust.ysc.utils.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description
 * @Author Github: MatoYing
 * @Date 05/05/2023 2:31 am
 */

@RestController
@RequestMapping("/report")
public class ReportController {

   @Autowired
   CommonDao commonDao;

   @Autowired
   MinIOServiceImpl minIOService;

   @Autowired
   ReportDao reportDao;

   @PostMapping("/weekReport")
   public JsonResult getWeekReport() {
      List<Report> list = reportDao.getReport();
      return new JsonResult<>(list, "所有周报");
   }

   @PostMapping("/getMonthReport")
   public JsonResult getMonthReport(@RequestBody Map map) {
      return new JsonResult<>(0, "你的报警信息");
   }

   @Scheduled(cron = "0 0 8 ? * 6")  // 每周六8点执行一次0 0 8 ? * 6
   public void makeWeekReport() {
      HashMap<String, Object> map = new HashMap<>();

      //模拟饼状图数据
      HashMap<String, Integer> datas = new HashMap<>();

      datas.put("普通报警", commonDao.countDay1ForWarning() + commonDao.countDay2ForWarning() + commonDao.countDay3ForWarning() + commonDao.countDay4ForWarning() + commonDao.countDay5ForWarning());
      datas.put("中级报警", commonDao.countDay1ForEmergency() + commonDao.countDay2ForEmergency() + commonDao.countDay3ForEmergency() + commonDao.countDay4ForEmergency() + commonDao.countDay5ForEmergency());
      datas.put("高级报警", commonDao.countDay1ForCritical() + commonDao.countDay2ForCritical() + commonDao.countDay3ForCritical() + commonDao.countDay4ForCritical() + commonDao.countDay5ForCritical());
      ImageEntity imageEntity = JfreeUtil.pieChart("占比图",datas, 500, 300);
      map.put("picture", imageEntity);

      //模拟其它普通数据
      Calendar calendar = Calendar.getInstance();
      Date currentDate = calendar.getTime();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      String today = sdf.format(currentDate);
      map.put("date", today);

      //模拟表格数据
      ArrayList<HashMap<String, String>> list = new ArrayList<>();
      List<Alert> alertList = commonDao.getWeekAlert();
      for (int i = 0; i < alertList.size(); i++) {
         HashMap<String, String> temp = new HashMap<>();
         temp.put("id", String.valueOf(i + 1));
         temp.put("monitor_name", alertList.get(i).getMonitor_name());
         temp.put("name", alertList.get(i).getName());
         temp.put("responsible_name", alertList.get(i).getResponsible_name());
         temp.put("start_data", sdf.format(alertList.get(i).getStart_data()));
         temp.put("submit_date", alertList.get(i).getSubmit_date());
         list.add(temp);
      }
      map.put("alertList",list);
      System.out.println(map);

      //word模板相对路径、word生成路径、word生成的文件名称、数据源
      WordUtil.exportWord("./src/main/resources/static/demo1.docx", "./src/main/resources/static/", today + ".docx", map);
      minIOService.uploadFile(today + ".docx", "./src/main/resources/static/" + today + ".docx");
      reportDao.insertReport(today);
   }

   @Scheduled(cron = "0 15 10 ? * 6L")  // 每个月的最后一个星期五上午10:15执行
   public void makeMonthReport() {

   }




//   @Scheduled(cron = "*/5 * * * * ?")  // 每五秒执行一次
//   public void test1() {
//      System.out.println("每五秒执行一次");
//   }


}
