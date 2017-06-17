package com.freenet.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.freenet.common.Jsp;
import com.freenet.common.K;
import com.freenet.domain.User;



/**
 * User拦截器
 * @author asus
 *
 */

public class UserInterceptor implements HandlerInterceptor {

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
		//检测是否为此系统的忽略接口
		String[] ingoreList=new String[]{"toLogin","index_Net","get_article","get_articleList","get_articleListByAjax","get_ArticleInfoByAjax","Login","smsVerifyCode","login","logout","toRegister","Register","imgCode","pwdForget_find","web/info","pwd/phone_find","pwd/phone_setpwd"};
		for(String ingore: ingoreList) {
		  if(uri.contains(ingore)) {
		     return true;
		  }
		}
		//检测session
		User user = (User)req.getSession().getAttribute(K.sessionUser);
		if(user==null){
			req.setAttribute(K.msg, "登录超时，请重新登录！");
			req.getRequestDispatcher("/WEB-INF/views/" + Jsp.user_login + ".jsp").forward(req, res);
			System.out.println("进来了");
			return false;
			
		}
		
		return true;
	}

}
