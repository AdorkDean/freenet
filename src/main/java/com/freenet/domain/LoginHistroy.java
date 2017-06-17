package com.freenet.domain;

import java.util.Date;

/**
 * 安全记录实体类
 *
 */
public class LoginHistroy {
	
	private int loginId;
	
	private int userId; //用户Id
	
	private Date loginTime; //登录时间
	
	private String loginIp; //登录Ip

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
	

}
