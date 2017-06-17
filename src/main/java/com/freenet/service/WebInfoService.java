package com.freenet.service;


import com.freenet.domain.WebInfo;


/**
 * 用户信息逻辑类
 * @author 83771
 *
 */
public interface WebInfoService {

	public WebInfo getAll();
	public void updateInfo(String name,String email,String descrip,String website,String coptright);
	public String getEmail();
	public String getInfo(String infoName);
}
