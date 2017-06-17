package com.freenet.domain;

import java.util.Date;

/**
 * 收款配置
 *
 */
public class Allocation {
	
	private int id;
	
	private String payZfb; //收款支付宝
	
	private Date cdt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPayZfb() {
		return payZfb;
	}

	public void setPayZfb(String payZfb) {
		this.payZfb = payZfb;
	}

	public Date getCdt() {
		return cdt;
	}

	public void setCdt(Date cdt) {
		this.cdt = cdt;
	}
	
	

}
