package com.zust.ysc.controller;

import com.zust.ysc.dao.UserDao;
import com.zust.ysc.entity.JsonResult;
import com.zust.ysc.entity.User;
import com.zust.ysc.entity.knowledge;
import com.zust.ysc.utils.SnowflakeUtil;
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
 * @Date 30/04/2023 7:26 am
 */

@RestController
@RequestMapping("/person")
public class PersonController {

   @Autowired
   UserDao userDao;

   @Autowired
   SnowflakeUtil snowflakeUtil;

   @PostMapping("/getPerson")
   public JsonResult getPerson(@RequestBody Map map) {
      String name = (String) map.get("name");
      User user = userDao.getUserByName(name);
      return new JsonResult<>(user, "个人信息");
   }

    @PostMapping("/updatePerson")
    public JsonResult updatePerson(@RequestBody Map map) {
        String name = (String) map.get("name");
        String phone = (String) map.get("phone");
        String email = (String) map.get("email");
        String birthday = (String) map.get("birthday");
        String sex = (String) map.get("sex");
        userDao.updatePerson(name, phone, email, birthday, sex);
        return new JsonResult<>(0, "修改个人信息");
    }

    @PostMapping("/getAllPerson")
    public JsonResult getAllPerson() {
        List<User> user = userDao.getAllPerson();
        return new JsonResult<>(user, "所有个人信息");
    }

    @PostMapping("/addPerson")
    public JsonResult addPerson(@RequestBody Map map) {
        String name = (String) map.get("name");
        String phone = (String) map.get("phone");
        String email = (String) map.get("email");
        String role = (String) map.get("role");
        userDao.addPerson(String.valueOf(snowflakeUtil.nextId()), name, phone, email, role);
        return new JsonResult<>(0, "所有个人信息");
    }

    @PostMapping("/deletePerson")
    public JsonResult deletePerson(@RequestBody Map map) {
        String name = (String) map.get("name");
        userDao.deletePerson(name);
        return new JsonResult<>(0, "删除个人信息");
    }


    @PostMapping("/editPerson")
    public JsonResult editPerson(@RequestBody Map map) {
        String id = (String) map.get("id");
        String name = (String) map.get("name");
        String phone = (String) map.get("phone");
        String email = (String) map.get("email");
        String role = (String) map.get("role");
        userDao.editPerson(id, name, phone, email, role);
        return new JsonResult<>(0, "删除个人信息");
    }



}
