package com.zust.ysc.controller;

import com.zust.ysc.dao.FileDao;
import com.zust.ysc.entity.File;
import com.zust.ysc.entity.JsonResult;
import com.zust.ysc.entity.knowledge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author 闫思潮
 * @Date 01/05/2023 7:27 am
 */

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileDao fileDao;

    @PostMapping("/getFile")
    public JsonResult getFile() {
        List<File> list = fileDao.getFile();
        return new JsonResult<>(list, "所有文件");
    }

    @PostMapping("/addFile")
    public JsonResult addFile(@RequestBody Map map) {
        String name = (String) map.get("name");
        String address = (String) map.get("address");
        String file = (String) map.get("file");
        String people = (String) map.get("people");
        String note = (String) map.get("note");
        fileDao.addFile(name, address, file, people, note);
        return new JsonResult<>(0, "添加文件");
    }

    @PostMapping("/deleteFile")
    public JsonResult deleteFile(@RequestBody Map map) {
        String name = (String) map.get("name");
        fileDao.deleteFile(name);
        return new JsonResult<>(0, "删除文件");
    }

    @PostMapping("/editFile")
    public JsonResult editFile(@RequestBody Map map) {
        String name = (String) map.get("name");
        String address = (String) map.get("address");
        String file = (String) map.get("file");
        String note = (String) map.get("note");
        fileDao.editFile(name, address, file, note);
        return new JsonResult<>(0, "编辑文件");
    }
}
