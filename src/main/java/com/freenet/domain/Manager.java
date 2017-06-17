package com.freenet.domain;
/**
 * 管理员实体类
 */
import java.util.Date;

public class Manager {
	
	private int id;
	
	private String username; //管理员账号
	
	private String password; //管理员密码
	
	private String name;     //管理员昵称
	
	private int level;       //管理员等级    0=超级管理员(最高等级)  1=管理员(工作人员)
	
	private Date cdt;        //创建时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Date getCdt() {
		return cdt;
	}

	public void setCdt(Date cdt) {
		this.cdt = cdt;
	}
	
	
	

}
