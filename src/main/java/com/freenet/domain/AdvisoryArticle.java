package com.freenet.domain;

import java.util.Date;

public class AdvisoryArticle extends Article {

	public AdvisoryArticle(Integer id, String title, String author, Date releaseDate, String content) {
		super();
		this.id = id;
		this.title = title;
		Author = author;
		this.releaseDate = releaseDate;
		this.content = content;
	}

	public AdvisoryArticle() {
		super();
	}

	public AdvisoryArticle(String title, String author, Date releaseDate, String content) {
		super();
		this.title = title;
		Author = author;
		this.releaseDate = releaseDate;
		this.content = content;
	}
}
