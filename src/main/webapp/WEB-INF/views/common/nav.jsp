<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="Acc_top">
	<!--描述：顶部语言栏-->
	<div class="Acc_top_tit">
		<div class="Acc_top_langu">
			
			<c:if test="${sessionUser==null }">
				<a class="Acc_login" href="user/toLogin">登录</a><span style="color: white;">|</span>
				<a class="Acc_register" href="user/toRegister">注册</a>
			</c:if>
			<c:if test="${sessionUser!=null }">
				<a class="Acc_login">欢迎您: ${sessionUser.username }</a><span style="color: white;">|</span>
				<a class="Acc_register" href="user/logout">退出</a>
			</c:if>
		</div>
	</div>
	<div class="Acc_navlist">
		<div class="Acc_logo">
			<img alt="" src="./resources/user/img/logo.png" />
		</div>
		<c:if test="${sessionUser==null }">
			<div class="Acc_nav">
				<ul class="Acc_menu">
					<li>
						<a href="user/index_Net">首页</a>
					</li>
					<li>
						<a href="user/index_Net">交易大厅</a>
					</li>
					<li>
						<a href="user/index_Net">财务中心</a>
					</li>
					<li>
						<a href="user/index_Net">安全中心</a>
					</li>
					<li>
						<a href="user/NoticeArticle/get_articleList">新闻资讯</a>
					</li>
				</ul>
			</div>
		</c:if>
				
		<c:if test="${sessionUser!=null }">
			<div class="Acc_nav">
				<ul class="Acc_menu">
					<li>
						<a href="#">首页</a>
					</li>
					<li>
						<a href="user/trading/offlineSell">交易大厅</a>
					</li>
					<li>
						<!-- <a href="user/finance/coin?Type=0">财务中心</a> -->
						<a href="user/finance/coin?Type=0">财务中心</a>
					</li>
					<li>
						<a href="user/safe/safeCenter">安全中心</a>
					</li>
					<li>
						<a href="user/NoticeArticle/get_articleList">新闻资讯</a>
					</li>
				</ul>
			</div>
		</c:if>
				
	</div>
</div>


