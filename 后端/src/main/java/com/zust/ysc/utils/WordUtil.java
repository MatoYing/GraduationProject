package com.zust.ysc.utils;

import cn.afterturn.easypoi.word.WordExportUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.util.Assert;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

/**
 * @Description
 * @Author 闫思潮
 * @Date 05/05/2023 11:43 am
 */

public class WordUtil {
   /**
    * 导出word
    * <p>第一步生成替换后的word文件，只支持docx</p>
    * <p>第二步下载生成的文件</p>
    * <p>第三步删除生成的临时文件</p>
    * 模版变量中变量格式：{{foo}}
    *
    * @param templatePath word模板地址
    * @param temDir       生成临时文件存放地址
    * @param fileName     文件名
    * @param params       替换的参数
    */
   public static void exportWord(String templatePath, String temDir, String fileName, Map<String, Object> params) {
      Assert.notNull(templatePath, "模板路径不能为空");
      Assert.notNull(temDir, "临时文件路径不能为空");
      Assert.notNull(fileName, "导出文件名不能为空");
      Assert.isTrue(fileName.endsWith(".docx"), "word导出请使用docx格式");
      if (!temDir.endsWith("/")) {
         temDir = temDir + File.separator;
      }
      File dir = new File(temDir);
      if (!dir.exists()) {
         dir.mkdirs();
      }
      try {
         XWPFDocument doc = WordExportUtil.exportWord07(templatePath, params);
         String tmpPath = temDir + fileName;
         FileOutputStream fos = new FileOutputStream(tmpPath);
         doc.write(fos);
         fos.flush();
         fos.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
