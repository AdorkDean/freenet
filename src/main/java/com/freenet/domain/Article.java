package com.freenet.domain;

import java.util.Date;

public abstract class Article {
	/**
	 * 文章序号
	 */
	protected int id;
	/**
	 * 文章标题
	 */
	protected String title;
	/**
	 * 文章作者
	 */
	protected String Author;
	/**
	 * 文章发布日期
	 */
	protected Date releaseDate;
	/**
	 * 文章日期
	 */
	protected String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Article(int id, String title, String author, Date releaseDate, String content) {
		super();
		this.id = id;
		this.title = title;
		Author = author;
		this.releaseDate = releaseDate;
		this.content = content;
	}
	public Article() {
		super();
	}
	public Article(String title, String author, Date releaseDate, String content) {
		super();
		this.title = title;
		Author = author;
		this.releaseDate = releaseDate;
		this.content = content;
	}
	
	
	
	
}
