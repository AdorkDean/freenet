package com.freenet.domain;
/**
 * 充值记录类
 * @author jeker-chen
 *
 */

import java.math.BigDecimal;
import java.util.Date;

public class RechargeRecord {

	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 充值钱包地址
	 */
	private String walletAddress;
	/**
	 * 支付账户
	 */
	private String payment;
	/**
	 * 充值数量
	 */
	private BigDecimal rechargeQuantity;
	/**
	 * 账户余额
	 */
	private BigDecimal balance;
	/**
	 * 充值状态
	 */
	private Integer status;
	/**
	 * 充值时间
	 */
	private Date fromDate;
	/**
	 * 到账时间
	 */
	private Date toDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getWalletAddress() {
		return walletAddress;
	}

	public void setWalletAddress(String walletAddress) {
		this.walletAddress = walletAddress;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public BigDecimal getRechargeQuantity() {
		return rechargeQuantity;
	}

	public void setRechargeQuantity(BigDecimal rechargeQuantity) {
		this.rechargeQuantity = rechargeQuantity;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public RechargeRecord(Integer userId, String walletAddress, String payment, BigDecimal rechargeQuantity,
			BigDecimal balance, Integer status, Date fromDate, Date toDate) {
		super();
		this.userId = userId;
		this.walletAddress = walletAddress;
		this.payment = payment;
		this.rechargeQuantity = rechargeQuantity;
		this.balance = balance;
		this.status = status;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public RechargeRecord() {
		super();
	}

}
