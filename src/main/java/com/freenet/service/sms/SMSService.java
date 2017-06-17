package com.freenet.service.sms;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import org.springframework.stereotype.Service;



/**
 * 短信服务类
 * 
 * @author jqy
 *
 *         2016年5月16日上午3:44:01
 */
@Service
public class SMSService {

  public static final String username="w281253644";

  public static final String password="ma123456";

  public static final String sign="Freenet";

  public static final String sms_url="http://api.smsbao.com/sms";

  // 短信验证码队列
  private Map<String, String> smsVerifyCodeList=new ConcurrentHashMap<String, String>();



  public void putSmsVerifyCode(String phoneNumber, String verifyCode) {
    smsVerifyCodeList.put(phoneNumber, verifyCode);
  }

  public String getSmsVerifyCode(String phoneNumber) {
    if(smsVerifyCodeList.containsKey(phoneNumber)) {
      return smsVerifyCodeList.get(phoneNumber);
    }
    return null;
  }

  public void removeSmsVerifyCode(String phoneNumber) {
    if(smsVerifyCodeList.containsKey(phoneNumber)) {
      smsVerifyCodeList.remove(phoneNumber);
    }
  }

  public boolean sendVerifyCode(String phone, String code) {
    String content=String.format("【%s】这是您的验证码：%s，误将验证码告诉他人，若非本人操作请忽略此信息。", sign, code);
    return sendSms(phone, content);
  }



  

  private boolean sendSms(String phone, String content) {
    StringBuffer reqUrl=new StringBuffer();
    reqUrl.append("u=").append(username).append("&");
    reqUrl.append("p=").append(md5(password)).append("&");
    reqUrl.append("m=").append(phone).append("&");
    reqUrl.append("c=").append(encodeUrlString(content, "UTF-8"));
    String result=request(sms_url, reqUrl.toString());
    System.out.println("短信发送结果：" + result);
    if("0".equals(result)) {
      return true;
    } else {
      return false;
    }
  }

  private String request(String httpUrl, String httpArg) {
    BufferedReader reader=null;
    String result=null;
    StringBuffer sbf=new StringBuffer();
    httpUrl=httpUrl + "?" + httpArg;
    try {
      URL url=new URL(httpUrl);
      HttpURLConnection connection=(HttpURLConnection)url.openConnection();
      connection.setRequestMethod("GET");
      connection.connect();
      InputStream is=connection.getInputStream();
      reader=new BufferedReader(new InputStreamReader(is, "UTF-8"));
      String strRead=reader.readLine();
      if(strRead != null) {
        sbf.append(strRead);
        while((strRead=reader.readLine()) != null) {
          sbf.append("\n");
          sbf.append(strRead);
        }
      }
      reader.close();
      result=sbf.toString();
    } catch(Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  private String md5(String plainText) {
    StringBuffer buf=null;
    try {
      MessageDigest md=MessageDigest.getInstance("MD5");
      md.update(plainText.getBytes());
      byte b[]=md.digest();
      int i;
      buf=new StringBuffer("");
      for(int offset=0; offset < b.length; offset++) {
        i=b[offset];
        if(i < 0)
          i+=256;
        if(i < 16)
          buf.append("0");
        buf.append(Integer.toHexString(i));
      }
    } catch(NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return buf.toString();
  }

  private String encodeUrlString(String str, String charset) {
    String strret=null;
    if(str == null)
      return str;
    try {
      strret=java.net.URLEncoder.encode(str, charset);
    } catch(Exception e) {
      e.printStackTrace();
      return null;
    }
    return strret;
  }
}
