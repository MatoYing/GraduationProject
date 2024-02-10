package com.zust.ysc.controller;

import com.zust.ysc.dao.CurrentUserDao;
import com.zust.ysc.dao.KnowledgeDao;
import com.zust.ysc.entity.CurrentUser;
import com.zust.ysc.entity.JsonResult;
import com.zust.ysc.entity.knowledge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author Github: MatoYing
 * @Date 29/04/2023 10:25 am
 */

@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {

    @Autowired
    CurrentUserDao currentUserDao;

    @Autowired
    KnowledgeDao knowledgeDao;

    @PostMapping("/getKnowledge")
    public JsonResult getKnowledge() {
//        String name = currentUserDao.getCurrentUser().getName();
        List<knowledge> list = knowledgeDao.getKnowledge();
        return new JsonResult<>(list, "所有知识");
    }

    @PostMapping("/addKnowledge")
    public JsonResult addKnowledge(@RequestBody Map map) {
        String name = (String) map.get("name");
        String content = (String) map.get("content");
        String people = (String) map.get("people");
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());  //将时间转换成Timestamp类型，这样便可以存入到Mysql数据库中
        knowledgeDao.addKnowledge(name, content, timestamp, people);
        return new JsonResult<>(0, "添加知识");
    }

    @PostMapping("/deleteKnowledge")
    public JsonResult deleteKnowledge(@RequestBody Map map) {
        String name = (String) map.get("name");
        knowledgeDao.deleteKnowledge(name);
        return new JsonResult<>(0, "删除知识");
    }


}
