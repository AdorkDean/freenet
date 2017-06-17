package com.freenet.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.freenet.common.Jsp;
import com.freenet.common.K;
import com.freenet.domain.Manager;


public class ManagerInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object obj, Exception e) throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object obj, ModelAndView mv) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object obj) throws Exception {
		String uri=req.getRequestURI();
		System.out.println(uri);
		//检测是否为忽略接口
		String[] ingoreList=new String[]{"toLogin", "login", "logout"};
	    for(String ingore: ingoreList) {
	      if(uri.contains(ingore)) {
	        return true;
	      }
	    }
	    //检测session
	    Manager manager = (Manager)req.getSession().getAttribute(K.sessionManager);
	    if(manager==null){
	    	req.setAttribute(K.msg, "登录超时，请重新登录！");
	    	req.getRequestDispatcher("/WEB-INF/views/" + Jsp.manager_login + ".jsp").forward(req, res);
	    	return false;
	    }
	    return true;
	}

}
