package com.zust.ysc.controller;

import com.zust.ysc.dao.AlertDao;
import com.zust.ysc.dao.MonitorDao;
import com.zust.ysc.entity.Alert;
import com.zust.ysc.entity.JsonResult;
import com.zust.ysc.service.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author 闫思潮
 * @Date 10/04/2023 7:52 pm
 */


@RestController
@RequestMapping("/prometheus")
public class PrometheusController {

    @Autowired
    ThirdPartyService thirdPartyService;

    @Autowired
    AlertDao alertDao;

    @PostMapping("/alert")
    public void prometheus_alert1(@RequestBody Map map) {
        System.out.println(map);

        String status = (String) map.get("status");
        System.out.println("status: " + status);
        if (status.equals("firing")) {
            // 触发报警(Fifing)
            List list = (List) map.get("alerts");
            Map alerts_map = (Map) list.get(0);

            Map labels_map = (Map) alerts_map.get("labels");
            String alertname = (String) labels_map.get("alertname");  // alert的name
            String instance = (String) labels_map.get("instance");
            String job = (String) labels_map.get("job");  // monitor的name
            String severity = (String) labels_map.get("severity");  // warning、critical和emergency
            String person = (String) labels_map.get("person");  // 电话
            String executor = (String) labels_map.get("executor");  // 执行人

            Map annotations_map = (Map) alerts_map.get("annotations");
            String summary = (String) annotations_map.get("summary");
            System.out.println("66");
            System.out.println(alertname + instance + job + severity + person + summary);
            System.out.println("55");
            String phone = alertDao.getPhoneByName(person);
            System.out.println("77");
            System.out.println("severity: " + severity);
            if (severity.equals("warning")) {
                // 发送钉钉
                thirdPartyService.sendMessageByDingDing(summary, phone);
            } else if (severity.equals("critical")) {
                // 发送钉钉
                thirdPartyService.sendMessageByDingDing(summary, phone);
                // 发送QQ邮箱
                thirdPartyService.sendEmail("via1350478903@163.com", summary);
            } else if (severity.equals("emergency")) {
                // 发送钉钉
                thirdPartyService.sendMessageByDingDing(summary, phone);
                // 发送阿里云短信
                thirdPartyService.sendCode(1234, phone);
            }
            System.out.println("22");
            // 判断这个alertname之前插入过没，如果查如果但还没解决(有没有提交工单)，就不插入
            List<Alert> alertList = alertDao.getAlert();
            for (Alert alert : alertList) {
                if (alert.getName().equals(alertname) && alert.getSubmit_date() == null) {
                    System.out.println("11");
                    return;
                }
            }
            System.out.println("44");
            // TODO:区分是不是log
            // 以上情况不满足，向alert插入
            alertDao.insertAlert(alertname, person, severity, summary, job, "未解决", executor);
            System.out.println("33");

        } else {
            System.out.println(status);
            System.out.println("来了");
            // 恢复正常(resolved)
            List list = (List) map.get("alerts");
            Map alerts_map = (Map) list.get(0);

            Map labels_map = (Map) alerts_map.get("labels");
            String alertname = (String) labels_map.get("alertname");

            System.out.println(alertname);
            // 改变这个alert的状态
            alertDao.updateAlert(alertname);
        }


    }
}



//            String endsAt = (String) alerts_map.get("endsAt");
//            // 转换时间
//            endsAt = endsAt.replace("Z", " UTC");
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
//            SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            try {
//                Date time = format.parse(endsAt);
//                endsAt = defaultFormat.format(time);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
