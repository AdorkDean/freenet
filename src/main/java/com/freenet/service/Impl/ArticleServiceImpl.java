package com.freenet.service.Impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.freenet.common.Page;
import com.freenet.dao.AdvisoryArticleDao;
import com.freenet.dao.ArticleDao;
import com.freenet.dao.MediaArticleDao;
import com.freenet.dao.NoticeArticleDao;
import com.freenet.domain.Article;
import com.freenet.service.ArticleService;
import com.freenet.tools.fileIO;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Resource
	private fileIO fileIO;
	@Resource
	private MediaArticleDao mediaArticleDao;
	@Resource
	private NoticeArticleDao noticeArticleDao;
	@Resource
	private AdvisoryArticleDao advisoryArticleDao;

	@Override
	public void insertArticle(Article article) throws IOException {
		// 获得文章的类型
		String str = article.getClass().getSimpleName();
		System.out.println(str + "文章的类型");
		//根据文章类型获得相应的文章Dao
		ArticleDao articleDao = getArticleDao(str);
		//将文章基本信息存入数据库
		articleDao.insertArticle(article);
		//获取数据库中文章的id
		System.out.println("所存文章的id是："+article.getId());
		//将文章存入文件系统
		fileIO.fileWrite(article.getContent(),str ,article.getId());
	}

	@Override
	public void deleteArticle(int id, String type) {
		ArticleDao articleDao = getArticleDao(type);
		articleDao.deleteArticle(id);
		try {
			fileIO.deleteFile(type, id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateArticle(Article article) {
		// 获得文章的类型
		String str = article.getClass().getSimpleName();
		System.out.println(str + "文章的类型");
		ArticleDao articleDao = getArticleDao(str);
		articleDao.updateArticle(article);
		System.out.println("所改文章的id为："+article.getId());
		try {
			fileIO.deleteFile(str, article.getId());
			fileIO.fileWrite(article.getContent(), str, article.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Article getArticle(int id, String type) {
		ArticleDao articleDao = getArticleDao(type);
		//获取相应的文章
		Article article = articleDao.getArticle(id);
		try {
			///获取存储在本地的文章内容
			String content = fileIO.fileRead(type, id);
			article.setContent(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return article;
		
	}

	@Override
	public Map<List<Article>, Integer> getArticleList(String articleType, int pageindex) {
		ArticleDao articleDao = getArticleDao(articleType);
		//获得该类文章的总数
		int totalCount = articleDao.getTotalCount();
		//获取分页信息
		Page page = new Page(totalCount, pageindex);
		//根据参数页获得该页的列表
		List<Article> articles = articleDao.getArticleListByPage(page.getStartPos(), page.getPageSize());
		//获得分页的总页数
		int totalPages = page.getTotalPageCount();
		//将该页的文章列表和总页数作为键值对存入map
		Map<List<Article>, Integer> articlePageMap = new HashMap<>();
		articlePageMap.put(articles, totalPages);
		return articlePageMap;
	}
	
	

	@Override
	public Article getLastArticle(int id,String articleType) {
		ArticleDao articleDao = getArticleDao(articleType);
		return articleDao.getLastArticle(id);
	}

	@Override
	public Article getNextArticle(int id,String articleType) {
		ArticleDao articleDao = getArticleDao(articleType);
		return articleDao.getNextArticle(id);
	}

	/**
	 * 判断article类型
	 */
	public ArticleDao getArticleDao(String str) {
		switch (str) {
		case "MediaArticle":
			return mediaArticleDao;
		case "NoticeArticle":
			return noticeArticleDao;
		case "AdvisoryArticle":
			return advisoryArticleDao;
		default:
			break;
		}
		return null;
	}
	
	

}
