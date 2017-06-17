package com.freenet.domain;

import java.util.Date;

public class Content {
	
	private int id;
	
	private int userId;//用户Id
	
	private int type;  //消息类型
	
	private String message; //内容
	
	private Date cdt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCdt() {
		return cdt;
	}

	public void setCdt(Date cdt) {
		this.cdt = cdt;
	}
	
	

	
	
}
