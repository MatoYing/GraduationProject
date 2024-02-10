package com.zust.ysc.controller;

import com.zust.ysc.dao.InspectDao;
import com.zust.ysc.dao.UserDao;
import com.zust.ysc.entity.File;
import com.zust.ysc.entity.Inspect;
import com.zust.ysc.entity.JsonResult;
import com.zust.ysc.entity.User;
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
 * @Date 01/05/2023 1:48 pm
 */

@RestController
@RequestMapping("/inspect")
public class InspectController {

    @Autowired
    InspectDao inspectDao;

    @Autowired
    UserDao userDao;

    @PostMapping("/getInspectForCalendar")
    public JsonResult getInspectForCalendar(@RequestBody Map map) {
        String name = (String) map.get("name");
        List<Inspect> list = inspectDao.getInspectForCalendar(name);
        return new JsonResult<>(list, "你的巡检任务");
    }

    @PostMapping("/getUser")
    public JsonResult getUser() {
        List<User> list = userDao.getAllPerson();
        return new JsonResult<>(list, "所有用户");
    }

    @PostMapping("/addInspect")
    public JsonResult addInspect(@RequestBody Map map) {
        String author = (String) map.get("author");
        String title = (String) map.get("title");
        String description = (String) map.get("description");
        String people = (String) map.get("people");
        String start = (String) map.get("start");
        inspectDao.addInspect(author, title, description, people, start);
        return new JsonResult<>(0, "添加巡检任务");
    }

    @PostMapping("/getInspect")
    public JsonResult getInspect() {
        List<Inspect> list = inspectDao.getInspect();
        return new JsonResult<>(list, "添加巡检任务");
    }

    @PostMapping("/deleteInspect")
    public JsonResult deleteInspect(@RequestBody Map map) {
        String title = (String) map.get("title");
        inspectDao.deleteInspect(title);
        return new JsonResult<>(0, "添加巡检任务");
    }

    @PostMapping("/deleteSecondInspect")
    public JsonResult deleteSecondInspect(@RequestBody Map map) {
        String title = (String) map.get("title");
        inspectDao.deleteInspectForEndDate(title);
        return new JsonResult<>(0, "删除已提交的巡检任务");
    }

    @PostMapping("/submitInspect")
    public JsonResult submitInspect(@RequestBody Map map) {
        String title = (String) map.get("title");
        String problem = (String) map.get("problem");
        inspectDao.submitInspect(title, problem);
        return new JsonResult<>(0, "提交巡检任务");
    }
}
