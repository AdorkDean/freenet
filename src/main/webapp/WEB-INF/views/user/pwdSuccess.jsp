<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
	<jsp:include page="../common/userHead.jsp"></jsp:include>
	
	<body>
		<jsp:include page="../common/nav.jsp"></jsp:include>
		
		<div class="Acc_mid">
			<div class="Acc_step">
				<img src="./resources/user/img/3.png" alt="" />
			</div>
			<div class="Com_mid">
				<div class="Com_img">
					<img src="./resources/user/img/xiaoren.png" alt="" />
				</div>
				<div class="Com_cont">
					<span class="Com_con">恭喜你</span><span class="Com_find">成功找回密码</span>
					<a href="user/toLogin">登录</a>
					<div class="Com_Prompt">
						<span style="color: #9b9da0;">温馨提示：</span><span>请牢记您的登录密码，以方便下次登录。</span>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../common/bottom.jsp"></jsp:include>
	</body>
</html>