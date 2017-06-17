package com.freenet.tools;

public class SMSVerifyCodeTools {
	
	public static final int getSmsVerifyCode() {
	    int result =  9000 + (int)(Math.random() * 9000);
	    System.out.println("短信验证码为："+result);
	    return result;
	}

}
