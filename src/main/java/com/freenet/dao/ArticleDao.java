package com.freenet.dao;

import java.util.List;

import com.freenet.domain.Article;

public interface ArticleDao {

	/**
	 * 增加文章
	 */
	public void insertArticle(Article article);

	/**
	 * 删除文章
	 */
	public void deleteArticle(int id);

	/**
	 * 修改文章
	 */
	public void updateArticle(Article article);

	/**
	 * 获取文章
	 */
	public Article getArticle(int id);

	/**
	 * 获取文章列表
	 */
	public List<Article> getArticleList();

	/**
	 * 根据分页获得文章列表
	 */
	public List<Article> getArticleListByPage(int start, int end);
	
	/**
	 * 获得总记录数
	 */
	public int getTotalCount();
	
	/**
	 * 查找上一条记录
	 */
	public Article getLastArticle(int id);
	
	/**
	 * 查找下一条记录
	 */
	public Article getNextArticle(int id);
}
