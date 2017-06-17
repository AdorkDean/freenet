package com.freenet.domain;

import java.util.Date;

public class Bank {
	
	private int bankId;
	
	private int userId; //用户的Id
	
	private String name; //用户姓名
	
	private String zfbNumber; //支付宝账号
	
	private String phone;  //手机号码
	
	private String province; //省
	
	private String area; //区
	
	private Date cdt;

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZfbNumber() {
		return zfbNumber;
	}

	public void setZfbNumber(String zfbNumber) {
		this.zfbNumber = zfbNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Date getCdt() {
		return cdt;
	}

	public void setCdt(Date cdt) {
		this.cdt = cdt;
	}

	
}
