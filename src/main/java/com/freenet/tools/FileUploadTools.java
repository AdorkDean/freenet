package com.freenet.tools;

import java.io.File;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * spring 文件上传工具
 * 
 * @author Simple
 * @date 2013-8-20 上午11:47:45
 * @Description TODO
 */
public class FileUploadTools {

  public static boolean uploadFile_APK(CommonsMultipartFile myFile, String savePath) {
    String[] names=myFile.getOriginalFilename().split("\\.");
    String suffix=names[names.length - 1].toLowerCase();
    if("apk".equals(suffix)) {
      if(!myFile.isEmpty()) {
        System.out.println("=======UploadFile Name=======" + myFile.getOriginalFilename());
        // 检测路径是否存在
        File folder=new File(savePath);
        if(!folder.exists()) {
          folder.mkdirs();
        }
        // 新建一个文件
        File newFile=new File(savePath + myFile.getOriginalFilename());
        try {
          // 将上传的文件写入新建的文件中
          myFile.getFileItem().write(newFile);
          System.out.println("=======UploadFile Success=======");
          return true;
        } catch(Exception e) {
          e.printStackTrace();
        }
      }
    }
    return false;
  }

  public static boolean uploadFile_GIF(CommonsMultipartFile myFile, String savePath) {
    String[] names=myFile.getOriginalFilename().split("\\.");
    String suffix=names[names.length - 1].toLowerCase();
    if("gif".equals(suffix)) {
      if(!myFile.isEmpty()) {
        System.out.println("=======UploadFile Name=======" + myFile.getOriginalFilename());
        // 检测路径是否存在
        File folder=new File(savePath);
        if(!folder.exists()) {
          folder.mkdirs();
        }
        // 新建一个文件
        File newFile=new File(savePath + myFile.getOriginalFilename());
        try {
          // 将上传的文件写入新建的文件中
          myFile.getFileItem().write(newFile);
          System.out.println("=======UploadFile Success=======");
          return true;
        } catch(Exception e) {
          e.printStackTrace();
        }
      }
    }
    return false;
  }

  public static boolean uploadFile(CommonsMultipartFile file, String fileName, String savePath) {
    if(!file.isEmpty()) {
      System.out.println("=======UploadFile Name=======" + file.getOriginalFilename());
      // 检测路径是否存在
      File folder=new File(savePath);
      if(!folder.exists()) {
        folder.mkdirs();
      }
      // 新建一个文件
      File newFile=new File(savePath + "/" + fileName);
      try {
        // 将上传的文件写入新建的文件中
        file.getFileItem().write(newFile);
        System.out.println("=======UploadFile Success=======");
        return true;
      } catch(Exception e) {
        e.printStackTrace();
      }
    }
    return false;
  }
}
