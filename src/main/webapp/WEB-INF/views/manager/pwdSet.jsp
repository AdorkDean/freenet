<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<html lang="en">
<jsp:include page="../common/managerHead.jsp"></jsp:include>
<script src="./resources/manager/js/echarts.min.js"></script>
<link rel="stylesheet" href="./resources/manager/css/amazeui.min.css" />
<link rel="stylesheet" href="./resources/manager/css/amazeui.datatables.min.css" />
<link rel="stylesheet" href="./resources/manager/css/app.css">
<script src="./resources/manager/js/jquery.min.js"></script>
	<body data-type="widgets">
		<script src="./resources/manager/js/theme.js"></script>
		<div class="am-g tpl-g">
			<!-- 头部 -->
			<jsp:include page="../common/managerNav.jsp"></jsp:include>
			<!-- 侧边导航栏 -->
			<jsp:include page="../common/managerLeft.jsp"></jsp:include>
			<!-- 内容区域 -->
			<div class="tpl-content-wrapper">
				<div class="row-content am-cf">
					<div class="row">
						<div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
							<div class="widget am-cf">
								<div class="widget-head am-cf">
									<div class="widget-title am-fl">修改密码</div>
									<div class="widget-function am-fr">
										<a href="javascript:;" class="am-icon-cog"></a>
									</div>
								</div>
								<div class="widget-body am-fr">
									<div class="am-form tpl-form-border-form tpl-form-border-br">
										<div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">旧密码 </label>
											<div class="am-u-sm-9">
												<input type="hidden" value="${sessionManager.id }" id="managerId"/>
												<input style="width:35%;" type="password" id="oldPassword" name="oldPassword" class="tpl-form-input" placeholder="请输入密码">
											</div>
										</div>
										<div class="am-form-group">
											<label class="am-u-sm-3 am-form-label">新密码 </label>
											<div class="am-u-sm-9">
												<input style="width:35%;" type="password" id="newPwd" name="newPwd" placeholder="输入新密码">
											</div>
										</div>
										<div class="am-form-group">
											<label class="am-u-sm-3 am-form-label">确认密码 </label>
											<div class="am-u-sm-9">
												<input style="width:35%;" type="password" id="rePwd" name="rePwd" placeholder="请再次输入密码">
											</div>
										</div>
										<div class="am-form-group">
											<div class="am-u-sm-9 am-u-sm-push-3">
												<button id="changePwd" type="button" class="am-btn am-btn-primary tpl-btn-bg-color-success ">提交</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
		<script src="./resources/manager/js/amazeui.min.js"></script>
		<script src="./resources/manager/js/amazeui.datatables.min.js"></script>
		<script src="./resources/manager/js/dataTables.responsive.min.js"></script>
		<script src="./resources/manager/js/app.js"></script>
	</body>
	
	<script type="text/javascript">
	$(function(){
		$("#changePwd").click(function(){
			var managerId = $("#managerId").val();
			var oldPassword = $("#oldPassword").val();
			var newPwd = $("#newPwd").val();
			var rePwd = $("#rePwd").val();
			if(oldPassword==''){
				 alert("请输入旧密码!");
				 return false;
			 }
			 if(newPwd==''){
				 alert("请输入新密码!");
				 return false;
			 }
			 if(rePwd==''){
				 alert("请确认密码!");
				 return false;
			 }
			 $.ajax({
				 url:'manager/pwdUpdate',
				 type:'POST',
				 data:{managerId:managerId,oldPassword:oldPassword,newPwd:newPwd,rePwd:rePwd},
				 dataType:'json',
				 async:false,
				 success:function(data){
					 if(data.result==true){
						 alert(data.msg);
						 window.location.href="manager/toLogin"
					 }else{
						 alert(data.msg);
					 }
				 },
				 error:function(){
					 alert("网络缓慢，请稍后再试!");
				 }
			 });
		});
	});
	</script>
</html>