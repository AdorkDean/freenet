package com.freenet.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 线下订单
 */
public class OffOrder {
	
	private int id;
	
	private int type; //当前订单的类型  0=管理员添加   1=用户自行卖出
	
	private int sellUserId; //卖出方id
	
	private String sellUsername; // 卖方账号
	
	private String sellName; //卖出方姓名
	
	private int buyUserId; //买方id
	
	private String buyUsername; //买方账号
	
	private String buyName;//买方姓名
	
	private BigDecimal sellCoin; //卖出的货币数量
	
	private BigDecimal price; //卖出的价格
	
	private BigDecimal realPrice; //交易完成的金额
	
	private int status; //订单状态
	
	private int intervention; //平台是否介入
	
	private Date sellCdt; //卖出日期
	
	private Date buyCdt; //成交时间
	
	private Date realCdt; // 打款(收款时间)

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSellUserId() {
		return sellUserId;
	}

	public void setSellUserId(int sellUserId) {
		this.sellUserId = sellUserId;
	}

	public String getSellUsername() {
		return sellUsername;
	}

	public void setSellUsername(String sellUsername) {
		this.sellUsername = sellUsername;
	}

	public String getSellName() {
		return sellName;
	}

	public void setSellName(String sellName) {
		this.sellName = sellName;
	}

	public int getBuyUserId() {
		return buyUserId;
	}

	public void setBuyUserId(int buyUserId) {
		this.buyUserId = buyUserId;
	}

	public String getBuyUsername() {
		return buyUsername;
	}

	public void setBuyUsername(String buyUsername) {
		this.buyUsername = buyUsername;
	}

	public String getBuyName() {
		return buyName;
	}

	public void setBuyName(String buyName) {
		this.buyName = buyName;
	}

	public BigDecimal getSellCoin() {
		return sellCoin;
	}

	public void setSellCoin(BigDecimal sellCoin) {
		this.sellCoin = sellCoin;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIntervention() {
		return intervention;
	}

	public void setIntervention(int intervention) {
		this.intervention = intervention;
	}

	public Date getSellCdt() {
		return sellCdt;
	}

	public void setSellCdt(Date sellCdt) {
		this.sellCdt = sellCdt;
	}

	public Date getBuyCdt() {
		return buyCdt;
	}

	public void setBuyCdt(Date buyCdt) {
		this.buyCdt = buyCdt;
	}

	public Date getRealCdt() {
		return realCdt;
	}

	public void setRealCdt(Date realCdt) {
		this.realCdt = realCdt;
	}
	
	
}
