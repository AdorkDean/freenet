package com.freenet.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 开盘收盘价格
 * @author jeker-chen
 */
public class OpenClosePrice {

	private int id;
	private BigDecimal openPrice;
	private BigDecimal closePrice;
	private Date inDay;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getInDay() {
		return inDay;
	}
	public void setInDay(Date inDay) {
		this.inDay = inDay;
	}
	public BigDecimal getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(BigDecimal openPrice) {
		this.openPrice = openPrice;
	}
	public BigDecimal getClosePrice() {
		return closePrice;
	}
	public void setClosePrice(BigDecimal closePrice) {
		this.closePrice = closePrice;
	}
	public OpenClosePrice( BigDecimal openPrice, BigDecimal closePrice, Date inDay) {
		super();
		this.openPrice = openPrice;
		this.closePrice = closePrice;
		this.inDay = inDay;
	}
	public OpenClosePrice() {
		super();
	}
	
	
}
