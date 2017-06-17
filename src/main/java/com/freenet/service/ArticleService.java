package com.freenet.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.freenet.domain.Article;

public interface ArticleService {

	/**
	 * 添加文章
	 * @throws IOException 
	 */
	public void insertArticle(Article article) throws IOException;

	/**
	 * 删除文章
	 */
	public void deleteArticle(int id,String type);

	/**
	 * 修改文章
	 */
	public void updateArticle(Article article);

	/**
	 * 查询文章
	 */
	public Article getArticle(int id,String type);
	
	/**
	 * 获取文章列表
	 */
	public Map<List<Article>, Integer> getArticleList(String articleType,int pageindex);

	/**
	 * 查找上一条记录
	 */
	public Article getLastArticle(int id,String articleType);
	
	/**
	 * 查找下一条记录
	 */
	public Article getNextArticle(int id,String articleType);
}
