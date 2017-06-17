package com.freenet.domain;

import java.math.BigDecimal;
import java.util.Date;

public class WithDraw {
	
	private int id;
	
	private int userId; //用户id
	
	private String userName; //用户名字
	
	private BigDecimal drawMoney; //提现金额
	
	private String zfbNumber; //支付宝账号
	
	private String mobile;  //手机号
	
	private int status;  //提现状态   0=已提现(通过)  1=待审核(审核中) 2=未通过   
	
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getDrawMoney() {
		return drawMoney;
	}

	public void setDrawMoney(BigDecimal drawMoney) {
		this.drawMoney = drawMoney;
	}

	public String getZfbNumber() {
		return zfbNumber;
	}

	public void setZfbNumber(String zfbNumber) {
		this.zfbNumber = zfbNumber;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCdt() {
		return cdt;
	}

	public void setCdt(Date cdt) {
		this.cdt = cdt;
	}

	
	
}
