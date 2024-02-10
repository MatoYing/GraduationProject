package com.zust.ysc.service.impl;

import com.zust.ysc.service.IMinIOService;
import io.minio.DownloadObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.http.Method;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author Github: MatoYing
 * @Date 12/05/2023 6:30 pm
 */

@Service()
public class MinIOServiceImpl implements IMinIOService {
   private static MinioClient minioClient;

   static {
      minioClient = MinioClient.builder()
              .endpoint("http://182.92.232.96:9000")
              .credentials("nR6EOvrx6VKIgACC", "mQFcbCURvhV0D7kaSvbVOvQW9EbicmZn")
              .build();
   }

   @Override
   public void uploadFile(String name, String path) {
      try {
         minioClient.uploadObject(
                 UploadObjectArgs.builder()
                         .bucket("report")
                         .object(name)
                         .filename(path)
                         .contentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
                         .build());
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   @Override
   public void downloadFile() {
      try {
         minioClient.downloadObject(
                 DownloadObjectArgs.builder()
                         .bucket("sound")
                         .object("Test1.mp3")
                         .filename("C:\\Users\\86133\\Desktop\\haha.mp3")
                         .build());
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   @Override
   public void getLimitedURL() {
      Map<String, String> reqParams = new HashMap<String, String>();
      // 设置返回内容类型（https://www.runoob.com/http/http-content-type.html）
      reqParams.put("response-content-type", "video/mpeg");
      String url = null;
      try {
         url = minioClient.getPresignedObjectUrl(
                 GetPresignedObjectUrlArgs.builder()
                         .method(Method.GET)
                         .bucket("sound")
                         .object("Test1.mp3")
                         .expiry(2, TimeUnit.HOURS)
                         .extraQueryParams(reqParams)
                         .build());
      } catch (Exception e) {
         e.printStackTrace();
      }
      System.out.println(url);
   }
}
