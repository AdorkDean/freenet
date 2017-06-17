package com.freenet.domain;

import java.math.BigDecimal;

/**
 * 用户账户人民币实体类
 *
 */
public class Money {
	
	private int moneyId;  
	
	private int userId; //用户的Id
	
	private BigDecimal money; //用户人民币

	public int getMoneyId() {
		return moneyId;
	}

	public void setMoneyId(int moneyId) {
		this.moneyId = moneyId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	

}
