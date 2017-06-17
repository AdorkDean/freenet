<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header>
	<!-- logo -->
	<div class="am-fl tpl-header-logo">
		<a href="javascript:;"><img src="./resources/manager/img/logo.png" alt=""></a>
	</div>
	<!-- 右侧内容 -->
	<div class="tpl-header-fluid">
		<!-- 侧边切换 -->
		<div class="am-fl tpl-header-switch-button am-icon-list">
			<span>
            </span>
		</div>
		<!-- 其它功能-->
		<div class="am-fr tpl-header-navbar">
			<ul>
				<li class="am-text-sm">
					<span style="line-height: 55px;">欢迎您：${sessionManager.name }</span>
				</li>
						
				<!-- 退出 -->
				<li class="am-text-sm">
					<a href="manager/logout">
						<span class="am-icon-sign-out"></span> 退出
					</a>
				</li>
			</ul>
		</div>
	</div>
</header>