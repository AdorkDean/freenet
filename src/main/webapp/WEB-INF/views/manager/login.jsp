<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>

<jsp:include page="../common/managerHead.jsp"></jsp:include>
<link rel="stylesheet" href="./resources/manager/css/amazeui.min.css" />
<link rel="stylesheet" href="./resources/manager/css/amazeui.datatables.min.css" />
<link rel="stylesheet" href="./resources/manager/css/app.css">
<script src="./resources/manager/js/jquery.min.js"></script>

<body class="theme-black" data-type="login">
    <div class="am-g tpl-g">
        <div class="tpl-login">
            <div class="tpl-login-content">
                
				<div class="tpl-login-logo">
                  <img src="./resources/manager/img/logob.png" style="width: 300px;margin-left: 35px;" alt="" />
                </div>
                <form method="POST" action="manager/login" id="form1" enctype="multipart/form-data" class="am-form tpl-form-line-form">
                    <div class="am-form-group">
                        <input type="text" name="username" class="tpl-form-input" id="user-name" placeholder="请输入账号">
                    </div>
                    <div class="am-form-group">
                        <input type="password" name="password" class="tpl-form-input" id="user-name" placeholder="请输入密码">
                    </div>
                    <div class="am-form-group tpl-login-remember-me">
                    	<c:if test="${msg!=null }">
                     		<p style="color:red;">${msg }</p>
                     	</c:if>
                    </div>
                    <div class="am-form-group">
                        <button type="submit" class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">提交</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="./resources/manager/js/amazeui.min.js"></script>
    <script type="text/javascript" src="./resources/manager/js/app.js"></script>
</body>
</html>