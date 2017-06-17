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
<script type="text/javascript" src="./resources/user/js/main.js"></script>
<style type="text/css">
.am-selected{
	width: 45%;
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
									<div class="widget-title am-fl">添加订单</div>
									<div class="widget-function am-fr">
										<a href="javascript:;" class="am-icon-cog"></a>
									</div>
								</div>
								<div class="widget-body am-fr">
									<form action="manager/order/save" method="post" onsubmit="return checkOrder();" class="am-form tpl-form-border-form tpl-form-border-br" style="clear: both;">
										<div class="am-form-group" style="padding-left: 10%;">
											<div class="am-u-sm-9" style="float: left;">
												<span id="returnMsg" style="color:#fc5050;display: block;margin-left: 23%;">${msg}</span>
											</div>
										</div>
										<div class="am-form-group" style="padding-left: 10%;">
											<label style="width: 17%;" class="am-u-sm-3 am-form-label">卖家账号 </label>
											<div class="am-u-sm-9" style="float: left;">
												<input id="sellUsername" name="sellUsername" type="text" autocomplete="off" style="width:45%;float:left;" placeholder="请输入卖家账号">
												<input type="hidden" name="addType" value="1">
												<span id="usernameMsg" style="color:#fc5050;margin-left:1%;"></span>
											</div>
											
										</div>
										<div class="am-form-group" style="padding-left: 10%;">
											<label style="width: 17%;" for="user-intro" class="am-u-sm-3 am-form-label">类型</label>
											<div class="am-u-sm-9" style="float: left;">
												<select id="orderType" name="orderType" data-am-selected style="width: 45%;">
												<option value="0">--请选择--</option>
												<option value="1">线上订单</option>
												<option value="2">线下订单</option>
											</select>
											<span id="orderMsg" style="color:#fc5050;margin-left:0.3%;"></span>
											</div>
										</div>
										<div class="am-form-group" style="padding-left: 10%;">
											<label style="width: 17%;" class="am-u-sm-3 am-form-label">卖出量 </label>
											<div class="am-u-sm-9" style="float: left;">
												<input id="sellCoin" name="sellCoin" type="text" autocomplete="off" style="width:45%;float:left;" placeholder="请输入卖出量">
												<span id="sellMsg" style="color:#fc5050;margin-left:1%;"></span>
											</div>
										</div>
										<div class="am-form-group" style="padding-left: 10%;">
											<label style="width: 17%;" for="user-intro" class="am-u-sm-3 am-form-label">卖出金额</label>
											<div class="am-u-sm-9" style="float: left;">
												<input id="price" name="price" type="text" autocomplete="off"  style="width:45%;float:left;" placeholder="请输入卖出金额">
												<span id="pricenMsg" style="color:#fc5050;margin-left:1%;"></span>
											</div>
										</div>
										<div class="am-form-group" style="padding-left: 10%;">
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
function checkOrder(){
	var sellUsername = $("#sellUsername").val();
	var orderType = $("#orderType option:selected").val();
	var sellCoin = $("#sellCoin").val();
	var price = $("#price").val();
	if(sellUsername==''){
		$("#usernameMsg").text("请输入手机账号!");
		return false;
	}
	if(!verifyPhone(sellUsername)){
		$("#usernameMsg").text("手机账号格式不正确!");
		return false;
	}
	if(orderType==0){
		$("#orderMsg").text("请选择订单类型!");
		return false;
	}
	if(sellCoin==''){
		$("#sellMsg").text("请输入卖出的货币量!");
		return false;
	}
	if(sellCoin==0){
		$("#sellMsg").text("卖出量必须大约0!");
		return false;
	}
	if(isNaN(sellCoin)){
		$("#sellMsg").text("请输入正确的货币单位!");
		return false;
	}
	if(price==''){
		$("#pricenMsg").text("请输入卖出的金额!");
		return false;
	}
	if(price==0){
		$("#pricenMsg").text("卖出金额必须大约0!");
		return false;
	}
	if(isNaN(price)){
		$("#pricenMsg").text("请输入正确金钱格式!");
		return false;
	}
	return true;
}
</script>

</html>