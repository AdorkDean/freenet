package com.freenet.tools;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.UUID;

/**
 * 工具类
 *
 */
public class FreeUtils {
	
	/**
	 * 获取随机字符串
	 */
	public static String getRandomString(int length){
		//含有字符和数字的字符串
		String str = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();//随机类初始化
		 StringBuffer sb = new StringBuffer();//StringBuffer类生成，为了拼接字符串
		 for (int i = 0; i < length; ++i) {
			 int number = random.nextInt(36);// [0,36)
			 sb.append(str.charAt(number));
		 }
		 return sb.toString();
	}
	
	
	/**
	 * 获取ip
	 * @param netAddress
	 * @return
	 */
	public static String getHostIp(InetAddress netAddress){  
        if(null == netAddress){  
            return null;  
        }  
        String ip = netAddress.getHostAddress(); //get the ip address  
        return ip;  
    }
	
	public static InetAddress getInetAddress(){  
		  
        try{  
            return InetAddress.getLocalHost();  
        }catch(UnknownHostException e){  
            System.out.println("unknown host!");  
        }  
        return null;
    } 
	
	
	/*
	 * 生成随机图片名字工具
	 */
	public static String generateImgName() {
	    String uuid=UUID.randomUUID().toString();
	    // 去掉“-”符号
	    uuid=uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
	    return uuid + ".png";
	  }
	
	
	
	public static void main(String agrs[]){
		System.out.println(getRandomString(34));
		InetAddress netAddress = getInetAddress();  
		System.out.println("host ip:" + getHostIp(netAddress));  //计算机ip
		//3e645422a6ba491eb836489d2bb4f99e64
		//Wr6FYVbxAap4PqP0uVFrQTH4WHSh14ppwF
	}
	

}
