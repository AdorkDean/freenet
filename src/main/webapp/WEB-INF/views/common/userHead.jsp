<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 头文件 -->

<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<title>Freenet</title>
		
		
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
	<base href="<%=basePath%>">
		
		
		
		<link rel="stylesheet" type="text/css" href="./resources/user/css/Acc.css">
		<link rel="stylesheet" type="text/css" href="./resources/user/css/Comp.css">
		<link rel="stylesheet" type="text/css" href="./resources/user/css/F_Register.css">
		<link rel="stylesheet" type="text/css" href="./resources/user/css/header.css">
		<link rel="stylesheet" type="text/css" href="./resources/user/css/Index.css">
		<link rel="stylesheet" type="text/css" href="./resources/user/css/Real.css">
		<link rel="stylesheet" type="text/css" href="./resources/user/css/safe.css">
		<script type="text/javascript" src="./resources/user/js/jquery.min.js"></script>
		<script type="text/javascript" src="./resources/user/js/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="./resources/user/js/main.js"></script>
		<script type="text/javascript" src="./resources/user/js/echarts.js"></script>
		<script type="text/javascript" src="./resources/user/js/jedate/jedate.js"></script>
		
</head>