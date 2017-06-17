package com.freenet.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.BucketInfo;
import com.freenet.conf.AliyunOssConf;

/**
 * 阿里云 OSS 服务
 * 
 * @author jqy
 *
 *         2016年5月11日下午3:47:38
 */
@Service
public class AliyunOssService implements InitializingBean {

  @Resource
  private AliyunOssConf aliyunOssConf;

  private OSSClient ossClient;

  private void init() {
    ossClient=
      new OSSClient("http://" + aliyunOssConf.getEndpoint(), aliyunOssConf.getAccessKeyId(), aliyunOssConf.getAccessKeySecret());
    // 判断Bucket是否存在。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
    // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
    if(ossClient.doesBucketExist(aliyunOssConf.getBucketName())) {
      System.out.println("您已经创建Bucket：" + aliyunOssConf.getBucketName() + "。");
    } else {
      System.out.println("您的Bucket不存在，创建Bucket：" + aliyunOssConf.getBucketName() + "。");
      // 创建Bucket。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
      // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
      ossClient.createBucket(aliyunOssConf.getBucketName());
    }
    // 查看Bucket信息。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
    // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
    BucketInfo info=ossClient.getBucketInfo(aliyunOssConf.getBucketName());
    System.out.println("Bucket " + aliyunOssConf.getBucketName() + "的信息如下：");
    System.out.println("\t数据中心：" + info.getBucket().getLocation());
    System.out.println("\t创建时间：" + info.getBucket().getCreationDate());
    System.out.println("\t用户标志：" + info.getBucket().getOwner());
  }

  public String upload(String fileName, String fileContent) {
    // 把字符串存入OSS，Object的名称为firstKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
    // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
    InputStream is=new ByteArrayInputStream(fileContent.getBytes());
    ossClient.putObject(aliyunOssConf.getBucketName(), fileName, is);
    System.out.println("Object：" + fileName + "存入OSS成功。");
    return String.format("http://%s.%s/%s", aliyunOssConf.getBucketName(), aliyunOssConf.getEndpoint(), fileName);
  }

  public String upload(String fileName, byte[] fileContent) {
    // 把字符串存入OSS，Object的名称为firstKey。详细请参看“SDK手册 > Java-SDK > 上传文件”。
    // 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/upload_object.html?spm=5176.docoss/user_guide/upload_object
    InputStream is=new ByteArrayInputStream(fileContent);
    ossClient.putObject(aliyunOssConf.getBucketName(), fileName, is);
    System.out.println("Object：" + fileName + "存入OSS成功。");
    return String.format("http://%s.%s/%s", aliyunOssConf.getBucketName(), aliyunOssConf.getEndpoint(), fileName);
  }

  public void delete(String fileName) {
    ossClient.deleteObject(aliyunOssConf.getBucketName(), fileName);
    System.out.println("删除Object：" + fileName + "成功。");
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    if(aliyunOssConf.isAliOssOpen()) {
      System.out.println("aliOss服务开启");
      init();
    }else{
      System.out.println("aliOss服务关闭");
    }
  }
}
