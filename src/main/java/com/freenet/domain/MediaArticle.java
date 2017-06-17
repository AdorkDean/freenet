package com.freenet.domain;

import java.util.Date;

/**
 * 媒体报道文章类
 * 
 * @author 83771
 *
 */
public class MediaArticle extends Article {
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
	public MediaArticle(Integer id, String title, String author, Date releaseDate, String content) {
		super();
		this.id = id;
		this.title = title;
		Author = author;
		this.releaseDate = releaseDate;
		this.content = content;
	}
	public MediaArticle(String title, String author, Date releaseDate, String content) {
		super();
		this.title = title;
		Author = author;
		this.releaseDate = releaseDate;
		this.content = content;
	}
	public MediaArticle() {
		super();
	} 
}
