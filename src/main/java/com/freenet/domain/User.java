package com.freenet.domain;
/**
 * 用户实体类
 */

import java.util.Date;

public class User {
	
	private int userId;    
	
	private String username; //账号
	
	private String password; //密码
	
	private String dealPwd;  //交易密码
	
	private int status;   //用户账号的状态   1=正常   2=冻结
		
	private int authStatus; //用户的认证状态
	
	private String email; //用户邮箱
	
	private Date cdt;  //添加时间

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDealPwd() {
		return dealPwd;
	}

	public void setDealPwd(String dealPwd) {
		this.dealPwd = dealPwd;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(int authStatus) {
		this.authStatus = authStatus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCdt() {
		return cdt;
	}

	public void setCdt(Date cdt) {
		this.cdt = cdt;
	}

	
	
	
	

	
	
}
