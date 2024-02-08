package com.zust.ysc.service;

/**
 * @Description
 * @Author 闫思潮
 * @Date 12/05/2023 6:29 pm
 */

public interface IMinIOService {
   // 上传文件
   void uploadFile(String name, String path);

   // 下载文件
   void downloadFile();

   // 得到URL（有时间限制的）
   void getLimitedURL();
}
