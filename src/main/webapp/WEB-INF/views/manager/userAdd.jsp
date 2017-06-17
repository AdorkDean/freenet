<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<html lang="en">
<jsp:include page="../common/managerHead.jsp"></jsp:include>

<script src="./resources/user/js/main.js"></script>
<script src="./resources/manager/js/echarts.min.js"></script>
<link rel="stylesheet" href="./resources/manager/css/amazeui.min.css" />
<link rel="stylesheet" href="./resources/manager/css/amazeui.datatables.min.css" />
<link rel="stylesheet" href="./resources/manager/css/app.css">
<script src="./resources/manager/js/jquery.min.js"></script>
<style type="text/css">
.am-selected {
	width: 35%;
}
			
.upload_box {
	color: white;
	background: #0c79b1;
	border: 0;
	width: 82px;
	height: 32px;
	font-size: 14px;
	border-radius: 4px;
}
			
#file0 ,#file3 ,#file1 {
	width: 11%;
	position: absolute;
	top: 0;
	left: 0.8rem;
	opacity: 0;
	height: 32px;
}
			
#Con_text {
	background: #4b5357;
	border: none;
	margin-left: 1rem;
	outline: none;
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
									<div class="widget-title am-fl">添加会员</div>
									<div class="widget-function am-fr">
										<a href="javascript:;" class="am-icon-cog"></a>
									</div>
								</div>
								<div class="widget-body am-fr">
									<form method="POST" action="manager/user/save" onsubmit="return checkUser();" enctype="multipart/form-data" class="am-form tpl-form-border-form tpl-form-border-br">
										<c:if test="${msg!=null }">
											<div class="am-form-group">
												<div class="am-u-sm-9" style="text-align:center;font-size:14px; color: #fc5050;">
													<span>${msg}</span>
												</div>
											</div>
										</c:if>
										<div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">账号 </label>
											<div class="am-u-sm-9">
												<input style="width: 35%;" type="text" class="tpl-form-input" id="username" name="username" autocomplete="off" placeholder="请输入账号">
											</div>
										</div>
										<div class="am-form-group">
											<label class="am-u-sm-3 am-form-label">密码 </label>
											<div class="am-u-sm-9">
												<input style="width: 35%;" type="password" id="pwd" name="password" placeholder="请输入密码">
											</div>
										</div>
										<div class="am-form-group">
											<label class="am-u-sm-3 am-form-label">确认密码 </label>
											<div class="am-u-sm-9">
												<input style="width: 35%;" type="password" id="rePwd" name="repassword" placeholder="请再次输入密码">
											</div>
										</div>
										<div class="am-form-group">
											<label class="am-u-sm-3 am-form-label">姓名 </label>
											<div class="am-u-sm-9">
												<input id="authName" name="authName" style="width: 35%;" type="text" autocomplete="off" placeholder="请输入姓名">
											</div>
										</div>
										<div class="am-form-group">
											<label class="am-u-sm-3 am-form-label">性别 </label>
											<div class="am-u-sm-9" style="margin-top: 0.3rem;">
												<label for="man"><input id="man" type="radio" name="sex" value="1"/>男 </label>
												<label for="man2"><input id="man2" type="radio" name="sex" value="2"/>女</label>
											</div>
										</div>
										<div class="am-form-group">
											<label class="am-u-sm-3 am-form-label">证件类型 </label>
											<div class="am-u-sm-9">
												<select id="cardType" name="cardType" style="width: 35%;" data-am-selected>
													<option value="0">--请选择--</option>
													<option value="1">身份证</option>
													<option value="2">护照</option>
												</select>
											</div>
										</div>
										<div class="am-form-group">
											<label class="am-u-sm-3 am-form-label">证件号码 </label>
											<div class="am-u-sm-9">
												<input id="card" name="card" style="width: 35%;" type="text" autocomplete="off" placeholder="请输入证件号码">
											</div>
										</div>
										<div class="am-form-group">
											<label class="am-u-sm-3 am-form-label">收款账号 </label>
											<div class="am-u-sm-9">
												<input id="zfbNumber" name="zfbNumber" style="width: 35%;" type="text" autocomplete="off" placeholder="请输入收款支付宝账号">
											</div>
										</div>
										<div class="am-form-group">
											<label class="am-u-sm-3 am-form-label">账户资金 </label>
											<div class="am-u-sm-9">
												<input id="money" name="money" style="width: 35%;" type="text" autocomplete="off" placeholder="请输入账户资金余额">
											</div>
										</div>
										<div class="am-form-group">
											<label class="am-u-sm-3 am-form-label">账户货币量 </label>
											<div class="am-u-sm-9">
												<input id="coin" name="coin" style="width: 35%;" type="text" autocomplete="off" placeholder="请输入账户货币数量">
											</div>
										</div>
										<div class="am-form-group">
											<label class="am-u-sm-3 am-form-label">证件正面 </label>
											<div class="am-u-sm-9" style="position: relative;">
												<input type="button" class="upload_box" value="点击上传" />
												<input id="file0" name="photoFront" type="file">
												<div style="width: 251px; height: 137px;"><img class="Real_img" src="" id="img0"></div>
											</div>
										</div>
										<div class="am-form-group">
											<label class="am-u-sm-3 am-form-label">证件反面 </label>
											<div class="am-u-sm-9" style="position: relative;">
												<input type="button" class="upload_box" value="点击上传" />
												<input id="file1" name="photoBack" type="file">
												<div style="width: 251px; height: 137px;"><img class="Real_img" src="" id="img1"></div>
											</div>
										</div>
										<div class="am-form-group">
											<label class="am-u-sm-3 am-form-label">手持证件照</label>
											<div class="am-u-sm-9" style="position: relative;">
												<input type="button" class="upload_box" value="点击上传" />
												<input id="file3" name="photoAll" type="file">
												<div style="width: 251px; height: 137px;"><img class="Real_img" src="" id="img3"></div>
											</div>
										</div>
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
<script>
$("#file0").change(function() {
	var objUrl = getObjectURL(this.files[0]);
	console.log("objUrl = " + objUrl);
	if(objUrl) {
		$("#img0").attr("src", objUrl);
		$("#img0").css("width", "249px");
		$("#img0").css("height", "136px");
	}
});
$("#file1").change(function() {
	var objUrl = getObjectURL(this.files[0]);
	console.log("objUrl = " + objUrl);
	if(objUrl) {
		$("#img1").attr("src", objUrl);
		$("#img1").css("width", "249px");
		$("#img1").css("height", "136px");
	}
});
$("#file3").change(function() {
	var objUrl = getObjectURL(this.files[0]);
	console.log("objUrl = " + objUrl);
	if(objUrl) {
		$("#img3").attr("src", objUrl);
		$("#img3").css("width", "249px");
		$("#img3").css("height", "136px");
	}
});
//建立一個可存取到該file的url
function getObjectURL(file) {
	var url = null;
	if(window.createObjectURL != undefined) { // basic
		url = window.createObjectURL(file);
	} else if(window.URL != undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file);
	} else if(window.webkitURL != undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file);
	}
	return url;
}
</script>
<script src="./resources/manager/js/amazeui.min.js"></script>
<script src="./resources/manager/js/amazeui.datatables.min.js"></script>
<script src="./resources/manager/js/dataTables.responsive.min.js"></script>
<script src="./resources/manager/js/app.js"></script>
</body>
<script type="text/javascript">
function checkUser(){
	var mobile = $("#username").val();
	var pwd = $("#pwd").val();
	var rePwd = $("#rePwd").val();
	var money = $("#money").val();
	var coin = $("#coin").val();
	var zfbNumber = $("#zfbNumber").val();
	if(mobile==''){
		alert("请输入手机账号!");
		return false;
	}
	if(!verifyPhone(mobile)){
		alert("手机号码格式不正确!");
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
	if($("#authName").val()==''){
		alert("请输入姓名!");
		return false;
	}
	if($('input:radio[name="sex"]:checked').val()==null){
		alert("请选择性别!");
		return false;
	}
	if($("#cardType option:selected").val()==0){
		alert("请选择证件类型!");
		return false;
	}
	if($("#card").val()==''){
		alert("请输入证件号码!");
		return false;
	}
	if(zfbNumber==''){
		alert("请输入收款支付宝账号!");
		return false;
	}
	if(money==''){
		alert("请输入账户资金余额");
		return false;
	}
	if(coin==''){
		alert("请输入账户货币量!");
		return false;
	}
	if($("#img0").attr("src")==''){
		alert("请上传证件正面图片!");
		return false;
	}
	if($("#img1").attr("src")==''){
		alert("请上传证件背面图片!");
		return false;
	}
	if($("#img3").attr("src")==''){
		alert("请上传手持证件照片!");
		return false;
	}
	return true;
}
</script>

</html>