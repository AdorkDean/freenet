<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="Acc_bot">
	<p>联系我们:</p>
	<p>Copyright 2011 - 2016 Free net All rights reserved</p>
</div>
<script type="text/javascript">
	//ajax获取页面内容
	$.ajax({
		url : "user/web/info",
		type : "post",
		dataType : "html",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		timeout : 6000,
		data : {
			"info" : "email",
		},
		//获取成功，为页面新添加元素初始化
		success : function(msg) {
			$(".Acc_bot p:first").append(msg);
		}
	})
</script>