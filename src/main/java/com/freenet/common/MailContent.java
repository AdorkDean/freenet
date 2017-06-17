package com.freenet.common;

/**
 * 邮件内容类
 * 
 * @author jeker-chen
 *
 */
public class MailContent {

	private String address;
	private String name;
	private String content;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MailContent(String address, String name, String content) {
		super();
		this.address = address;
		this.name = name;
		this.content = content;
	}

}
