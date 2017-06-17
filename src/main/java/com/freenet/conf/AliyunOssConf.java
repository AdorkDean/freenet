package com.freenet.conf;

/**
 * 阿里云OSS服务配置
 * 
 *
 */
public class AliyunOssConf {

  private boolean aliOssOpen;

  private String endpoint;

  private String accessKeyId;

  private String accessKeySecret;

  private String bucketName;

  private String domainName;

  private String uploadPath;

  private String uploadFolder;

  public String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint=endpoint;
  }

  public String getAccessKeyId() {
    return accessKeyId;
  }

  public void setAccessKeyId(String accessKeyId) {
    this.accessKeyId=accessKeyId;
  }

  public String getAccessKeySecret() {
    return accessKeySecret;
  }

  public void setAccessKeySecret(String accessKeySecret) {
    this.accessKeySecret=accessKeySecret;
  }

  public String getBucketName() {
    return bucketName;
  }

  public void setBucketName(String bucketName) {
    this.bucketName=bucketName;
  }

  public String getDomainName() {
    return domainName;
  }

  public void setDomainName(String domainName) {
    this.domainName=domainName;
  }

  public String getUploadPath() {
    return uploadPath;
  }

  public void setUploadPath(String uploadPath) {
    this.uploadPath=uploadPath;
  }

  public String getUploadFolder() {
    return uploadFolder;
  }

  public void setUploadFolder(String uploadFolder) {
    this.uploadFolder=uploadFolder;
  }

  public boolean isAliOssOpen() {
    return aliOssOpen;
  }

  public void setAliOssOpen(boolean aliOssOpen) {
    this.aliOssOpen=aliOssOpen;
  }
}
