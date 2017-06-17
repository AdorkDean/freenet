package com.freenet.service.Impl;

import java.lang.reflect.Method;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.freenet.dao.WebInfoDao;
import com.freenet.domain.WebInfo;
import com.freenet.service.WebInfoService;

@Service
public class WebInfoServiceImpl implements WebInfoService {

	@Resource
	WebInfoDao Dao;

	@Override
	public WebInfo getAll() {
		return Dao.getAll();
	}

	@Override
	public void updateInfo(String name, String email, String descrip, String website, String copyright) {
		WebInfo info = new WebInfo(name, email, descrip, website, copyright);
		if (Dao.getAll() == null) {
			Dao.insertInfo(info);
		} else {
			Dao.updateInfo(info);
		}
	}

	@Override
	public String getEmail() {
		Map<String, String> map = Dao.getEmail();
		System.out.println(map.get("email"));
		return map.get("email");
	}

	@Override
	public String getInfo(String infoName) {
		Class<WebInfoDao> dao = WebInfoDao.class;
		String content = "";
		try {
			Method method = dao.getMethod("get" + infoName);
			@SuppressWarnings("unchecked")
			Map<String, String> map = (Map<String, String>) method.invoke(Dao);
			content = map.get(infoName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("cotent是：" + content);
		return content;
	}

}
