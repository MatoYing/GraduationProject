package com.zust.ysc.controller;

import com.zust.ysc.dao.MonitorDao;
import com.zust.ysc.entity.JsonResult;
import com.zust.ysc.entity.Monitor;
import com.zust.ysc.entity.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author Github: MatoYing
 * @Date 14/04/2023 6:44 pm
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController {

    @Autowired
    MonitorDao monitorDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/getMonitor")
    public JsonResult getMonitor() {
        List<Monitor> list = monitorDao.getMonitor();
        return new JsonResult<>(list, "所有monitor");
    }

    @PostMapping("/updateMonitor")
    public JsonResult updateMonitor(@RequestBody Map map) {
        String name = (String) map.get("name");
        String status = (String) map.get("status");
        String server_address = (String) map.get("server_address");
        String file_address = (String) map.get("file_address");
        String grafana = (String) map.get("grafana");
        String name2 = (String) map.get("name2");
        String note = (String) map.get("note");
        monitorDao.updateMonitor(name, status, server_address, file_address, grafana, note, name2);



        return new JsonResult<>(200, "更新monitor");
    }

    @PostMapping("/addMonitor")
    public JsonResult addMonitor(@RequestBody Map map, HttpSession session) {
        String name = (String) map.get("name");
//        String status = (String) map.get("status");
        String server_address = (String) map.get("server_address");
        String file_address = (String) map.get("file_address");
        String grafana = (String) map.get("grafana");
        String note = (String) map.get("note");
        String sessionID = session.getId();
        String people = (String) stringRedisTemplate.opsForHash().get("spring:session:sessions:" + sessionID, "sessionAttr:name");
        people = people.replaceAll("\"", "");
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());  //将时间转换成Timestamp类型，这样便可以存入到Mysql数据库中
        String status = "运行";
        System.out.println(people);
        System.out.println();
        monitorDao.addMonitor(name, server_address, file_address, grafana, note, people, timestamp, status);

        return new JsonResult<>(0, "添加monitor");
    }


    @PostMapping("/getRules")
    public JsonResult getRules(@RequestBody Map map) {
        String monitor_name = (String) map.get("monitor_name");
        List<Rule> list = monitorDao.getRules(monitor_name);
        System.out.println(list);
        return new JsonResult<>(list, "所有rule");
    }

    @PostMapping("/updateRule")
    public JsonResult updateRule(@RequestBody Map map) {
        String name = (String) map.get("name");
        String responsible_person = (String) map.get("responsible_person");
        String status = (String) map.get("status");
        String severity = (String) map.get("severity");
        String note = (String) map.get("note");
        String rule = (String) map.get("rule");
        String monitor_name = (String) map.get("monitor_name");
        String name2 = (String) map.get("name2");
        monitorDao.updateRule(name, responsible_person, status, severity, note, rule, monitor_name, name2);
        return new JsonResult<>(200, "更新Rule");
    }

    ///////////////////////////////////////////////////////////////////////////////////

    @PostMapping("/getMonitorForLog")
    public JsonResult getMonitorForLog() {
        List<Monitor> list = monitorDao.getMonitorForLog();
        return new JsonResult<>(list, "所有monitor");
    }

    @PostMapping("/updateMonitorForLog")
    public JsonResult updateMonitorForLog(@RequestBody Map map) {
        String name = (String) map.get("name");
        String status = (String) map.get("status");
        String server_address = (String) map.get("server_address");
        String file_address = (String) map.get("file_address");
        String grafana = (String) map.get("grafana");
        String name2 = (String) map.get("name2");
        String note = (String) map.get("note");
        monitorDao.updateMonitorForLog(name, status, server_address, file_address, grafana, note, name2);



        return new JsonResult<>(200, "更新monitor");
    }

    @PostMapping("/addMonitorForLog")
    public JsonResult addMonitorForLog(@RequestBody Map map, HttpSession session) {
        String name = (String) map.get("name");
//        String status = (String) map.get("status");
        String server_address = (String) map.get("server_address");
        String file_address = (String) map.get("file_address");
        String grafana = (String) map.get("grafana");
        String note = (String) map.get("note");
        String sessionID = session.getId();
        String people = (String) stringRedisTemplate.opsForHash().get("spring:session:sessions:" + sessionID, "sessionAttr:name");
        people = people.replaceAll("\"", "");
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());  //将时间转换成Timestamp类型，这样便可以存入到Mysql数据库中
        String status = "运行";
        monitorDao.addMonitorForLog(name, server_address, file_address, grafana, note, people, timestamp, status);

        return new JsonResult<>(0, "添加monitor");
    }


    @PostMapping("/getRulesForLog")
    public JsonResult getRulesForLog(@RequestBody Map map) {
        String monitor_name = (String) map.get("monitor_name");
        List<Rule> list = monitorDao.getRulesForLog(monitor_name);
        System.out.println(list);
        return new JsonResult<>(list, "所有rule");
    }

    @PostMapping("/updateRuleForLog")
    public JsonResult updateRuleForLog(@RequestBody Map map) {
        String name = (String) map.get("name");
        String responsible_person = (String) map.get("responsible_person");
        String status = (String) map.get("status");
        String severity = (String) map.get("severity");
        String note = (String) map.get("note");
        String rule = (String) map.get("rule");
        String monitor_name = (String) map.get("monitor_name");
        String name2 = (String) map.get("name2");
        monitorDao.updateRuleForLog(name, responsible_person, status, severity, note, rule, monitor_name, name2);
        return new JsonResult<>(200, "更新Rule");
    }
}
