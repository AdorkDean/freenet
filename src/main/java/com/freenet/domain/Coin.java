package com.freenet.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 货币类型实体类
 *
 */
public class Coin {
	
	private int coinId;
	
	private String coinName; //货币名称
	
	private BigDecimal coinRate; //线上交易手续费
	
	private BigDecimal coinCount; //线下交易的抵押币
	
	private Date cdt;

	public int getCoinId() {
		return coinId;
	}

	public void setCoinId(int coinId) {
		this.coinId = coinId;
	}

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	public BigDecimal getCoinRate() {
		return coinRate;
	}

	public void setCoinRate(BigDecimal coinRate) {
		this.coinRate = coinRate;
	}

	public BigDecimal getCoinCount() {
		return coinCount;
	}

	public void setCoinCount(BigDecimal coinCount) {
		this.coinCount = coinCount;
	}

	public Date getCdt() {
		return cdt;
	}

	public void setCdt(Date cdt) {
		this.cdt = cdt;
	}

	
	

}
