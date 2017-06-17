package com.freenet.domain;

/**
 * 网站信息类
 * 
 * @author 83771
 *
 */
public class WebInfo {

	/**
	 * id
	 */
	private int id;
	
	/**
	 * 网站名字
	 */
	private String name;
	/**
	 * 网站邮箱
	 */
	private String email;
	/**
	 * 网站描述
	 */
	private String descrip;
	/**
	 * 网站域名
	 */
	private String website;
	/**
	 * 网站版权
	 */
	private String copyright;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public WebInfo(String name, String email, String descrip, String website, String copyright) {
		super();
		this.name = name;
		this.email = email;
		this.descrip = descrip;
		this.website = website;
		this.copyright = copyright;
	}

	public WebInfo(Integer id, String name, String email, String descrip, String website, String copyright) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.descrip = descrip;
		this.website = website;
		this.copyright = copyright;
	}
	
	
	
	

}
