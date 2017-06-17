package com.freenet.service.mail;

import java.util.HashMap;
import java.util.Map;

/**
 * 邮箱验证服务类
 * @author jeker-chen
 *
 */
public class MailService {
	//邮箱验证储存队列
	public final static  Map<String, String> mailMaps = new HashMap<>();
	
	/**
	 * 给队列中添加新元素
	 */
	public static void putEmailVerification(String verification,String email){
		mailMaps.put(verification, email);
	}
	
	
	
	/**
	 * 取出队列中的一个元素
	 */
	public static String getEmailVerification(String verification){
		if(null!=mailMaps.get(verification)){
			String email = mailMaps.get(verification);
			mailMaps.remove(verification);
			return email;
		}else{
			return null;
		}
	}
	
	/**
	 * 删除队列中的一个元素
	 */
	public static void  removeEmailVerification(String verification){
			mailMaps.remove(verification);
	}
	
}
