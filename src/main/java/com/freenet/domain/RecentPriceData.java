package com.freenet.domain;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 近期成交数据数据
 * 
 * @author jeker-chen
 *
 */
public class RecentPriceData {
	private int id;
	/**
	 * 时间段最大值
	 */
	private BigDecimal minVal;
	/**
	 * 时间段最小值
	 */
	private BigDecimal maxVal;
	
	/*
	 * 时间段
	 */
	private Date startDate;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getMinVal() {
		return minVal;
	}

	public void setMinVal(BigDecimal minVal) {
		this.minVal = minVal;
	}

	public BigDecimal getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(BigDecimal maxVal) {
		this.maxVal = maxVal;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	
	
	public RecentPriceData() {
		super();
	}

	public RecentPriceData(BigDecimal minVal, BigDecimal maxVal, Date startDate) {
		super();
		this.minVal = minVal;
		this.maxVal = maxVal;
		this.startDate = startDate;
	}

	

}
