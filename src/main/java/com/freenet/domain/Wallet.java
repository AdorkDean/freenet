package com.freenet.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 货币钱包实体类
 *
 */
public class Wallet {
	
	private int walletId;
	
	private int userId; //用户的Id
	
	private int coinId;  //货币类型Id
	
	private BigDecimal coin;//货币的数量
	
	private String adress; //钱包地址
	
	private BigDecimal rmbRech; //累计充值当前货币的人民币
	
	private BigDecimal coinFrozen; //冻结的货币数量
	
	private Date cdt;

	public int getWalletId() {
		return walletId;
	}

	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCoinId() {
		return coinId;
	}

	public void setCoinId(int coinId) {
		this.coinId = coinId;
	}

	public BigDecimal getCoin() {
		return coin;
	}

	public void setCoin(BigDecimal coin) {
		this.coin = coin;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public BigDecimal getRmbRech() {
		return rmbRech;
	}

	public void setRmbRech(BigDecimal rmbRech) {
		this.rmbRech = rmbRech;
	}

	public BigDecimal getCoinFrozen() {
		return coinFrozen;
	}

	public void setCoinFrozen(BigDecimal coinFrozen) {
		this.coinFrozen = coinFrozen;
	}

	public Date getCdt() {
		return cdt;
	}

	public void setCdt(Date cdt) {
		this.cdt = cdt;
	}

	
	
	
	

}
