package com.freenet.dao;

import java.util.Map;

import com.freenet.domain.WebInfo;

public interface WebInfoDao {

	/**
	 * 获取网站所有信息
	 */
	public WebInfo getAll();
	
	/**
	 * 修改网站某个信息
	 */
	public void insertInfo(WebInfo info);


	/**
	 * 修改网站某个信息
	 */
	public void updateInfo(WebInfo info);

	/**
	 * 获取网站邮箱信息
	 * 
	 * @return
	 */
	public Map<String, String> getEmail();

	/**
	 * 获取网站名字
	 */
	public Map<String, String> getname();
	/**
	 * 获取网站邮箱
	 */
	public Map<String, String> getemail();
	/**
	 * 获取网站描述
	 */
	public Map<String, String> getdescrip();
	/**
	 * 获取网站链接
	 */
	public Map<String, String> getwebsite();
	/**
	 * 获取网站版权
	 */
	public Map<String, String> getcopyright();
	
}
