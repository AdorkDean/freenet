package com.freenet.domain;

import java.util.Date;

/**
 * 用户实名认证实体类
 *
 */
public class Auth {
	
	private int authId;
	
	private int userId;  //user表用户id
	
	private String name; //用户姓名
	
	private int sex;  //性别
	
	private int cardType; //证件类型
	
	private String card; //证件号码
	
	private String photoFront; //证件正面照片
	
	private String photoBack; //证件背面照片
	
	private String photoAll; //手持证件照
	
	private Date cdt;  //创建时间

	public int getAuthId() {
		return authId;
	}

	public void setAuthId(int authId) {
		this.authId = authId;
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

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getPhotoFront() {
		return photoFront;
	}

	public void setPhotoFront(String photoFront) {
		this.photoFront = photoFront;
	}

	public String getPhotoBack() {
		return photoBack;
	}

	public void setPhotoBack(String photoBack) {
		this.photoBack = photoBack;
	}

	public String getPhotoAll() {
		return photoAll;
	}

	public void setPhotoAll(String photoAll) {
		this.photoAll = photoAll;
	}

	public Date getCdt() {
		return cdt;
	}

	public void setCdt(Date cdt) {
		this.cdt = cdt;
	}
	
	
	

}
