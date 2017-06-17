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
<style>
#msg{
	margin-left: 31rem;
    color: red;
    margin-bottom: 1rem;
    display: block;
    font-size: 13px;
}
</style>
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
									<div class="widget-title am-fl">新增管理员</div>
									<div class="widget-function am-fr">
										<a href="javascript:;" class="am-icon-cog"></a>
									</div>
								</div>
								<div class="widget-body am-fr">
									<form method="Post" action="manager/work/save" enctype="multipart/form-data" onsubmit="return checkWork();" class="am-form tpl-form-border-form tpl-form-border-br">
										<div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">名称 </label>
											<div class="am-u-sm-9">
												<input style="width: 35%;" type="text" class="tpl-form-input" id="name" name="managerName" autocomplete="off" placeholder="请输入管理员名称">
											</div>
										</div>
										<div class="am-form-group">
											<label class="am-u-sm-3 am-form-label">账号 </label>
											<div class="am-u-sm-9">
												<input style="width: 35%;" id="username" name="username" type="text" autocomplete="off" placeholder="请输入手机账号">
											</div>
										</div>
										<div class="am-form-group">
											<label class="am-u-sm-3 am-form-label">密码</label>
											<div class="am-u-sm-9">
												<input style="width: 35%;" id="pwd" name="password" type="password" placeholder="请输入管理员密码">
											</div>
										</div>
										<div class="am-form-group">
											<label class="am-u-sm-3 am-form-label">确认密码</label>
											<div class="am-u-sm-9">
												<input style="width: 35%;" id="rePwd" name="repassword" type="password" placeholder="请输确认密码">
											</div>
										</div>
										<span id="msg">
											<c:if test="${msg!=null }">
												${msg }
											</c:if>
										</span>
										<div class="am-form-group">
											<div class="am-u-sm-9 am-u-sm-push-3">
												<button type="submit" class="am-btn am-btn-primary tpl-btn-bg-color-success ">提交</button>
											</div>
										</div>
										
									</form>
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
function checkWork(){
	var name = $("#name").val();
	var mobile = $("#username").val();
	var pwd = $("#pwd").val();
	var rePwd = $("#rePwd").val();
	if(name==''){
		alert("请填写名称!");
		return false;
	}
	if(!verifyPhone(mobile)){
		alert("手机账号格式不正确!");
		return false;
	}
	if(pwd==''){
		alert("请输入密码!");
		return false;
	}
	if(rePwd==''){
		alert("请确认密码!");
		return false;
	}
	
	return true;
}
</script>

</html>