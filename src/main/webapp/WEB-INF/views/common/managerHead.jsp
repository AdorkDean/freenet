<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 头文件 -->

<head>
		<title>Freenet</title>
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="keywords" content="index">
	    <meta name="renderer" content="webkit">
	    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
		
		
		
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
	<base href="<%=basePath%>">
		

		
		
</head>