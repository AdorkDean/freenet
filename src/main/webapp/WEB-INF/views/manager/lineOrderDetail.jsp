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
									<div class="widget-title am-fl">订单详情</div>
									<div class="widget-function am-fr">
										<a href="javascript:;" class="am-icon-cog"></a>
									</div>
								</div>
								<div class="widget-body am-fr">
									<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
										<div class="am-form-group" >
											<div class="am-btn-toolbar">
												<div class="am-btn-group am-btn-group-xs" style="margin-left: 22px;">
													<a onclick="history.go(-1)" class="am-btn am-btn-default am-btn-success"><span class="fa arrow-left"></span> 返回</a>
												</div>
											</div>
										</div>
									</div>
									<div class="am-form tpl-form-border-form tpl-form-border-br" style="clear: both;">
										<div class="am-form-group" style="padding-left: 10%;">
											<label style="width: 17%;" for="user-name" class="am-u-sm-3 am-form-label">订单状态 :</label>
											<div class="am-u-sm-9" style="float: left;">
												<input type="text" style="width: 45%;" class="tpl-form-input" id="user-name" value="已完成" readonly="readonly" />
											</div>
										</div>
										<div class="am-form-group" style="padding-left: 10%;">
											<label style="width: 17%;" class="am-u-sm-3 am-form-label">卖方账号 :</label>
											<div class="am-u-sm-9" style="float: left;">
												<input type="text" style="width: 45%;" value="${obj.sellUsername }" readonly="readonly"/>
											</div>
										</div>
										<div class="am-form-group" style="padding-left: 10%;">
											<label style="width: 17%;" class="am-u-sm-3 am-form-label">卖方姓名 :</label>
											<div class="am-u-sm-9" style="float: left;">
												<input type="text" style="width: 45%;" value="${obj.sellName }" readonly="readonly" />
											</div>
										</div>
										<div class="am-form-group" style="padding-left: 10%;">
											<label style="width: 17%;" class="am-u-sm-3 am-form-label">卖出数量 :</label>
											<div class="am-u-sm-9" style="float: left;">
												<input type="text" style="width: 45%;" value="${obj.sellCoin }" readonly="readonly" />
											</div>
										</div>
										<div class="am-form-group" style="padding-left: 10%;">
											<label style="width: 17%;" class="am-u-sm-3 am-form-label">交易价格 :</label>
											<div class="am-u-sm-9" style="float: left;">
												<input type="text" style="width: 45%;" value="${obj.price }" readonly="readonly" />
											</div>
										</div>
										<div class="am-form-group" style="padding-left: 10%;">
											<label style="width: 17%;" class="am-u-sm-3 am-form-label">买方账号 :</label>
											<div class="am-u-sm-9" style="float: left;">
												<input type="text" style="width: 45%;" value="${obj.buyUsername }" readonly="readonly" />
											</div>
										</div>
										<div class="am-form-group" style="padding-left: 10%;">
											<label style="width: 17%;" class="am-u-sm-3 am-form-label">买方姓名 :</label>
											<div class="am-u-sm-9" style="float: left;">
												<input type="text" style="width: 45%;" value="${obj.buyName }" readonly="readonly" />
											</div>
										</div>
										<div class="am-form-group" style="padding-left: 10%;">
											<label style="width: 17%;" class="am-u-sm-3 am-form-label">出售日期 :</label>
											<span style="line-height:37px;"><fmt:formatDate value="${obj.sellCdt}" pattern="yyyy-MM-dd HH:mm" /></span>
										</div> 
										<div class="am-form-group" style="padding-left: 10%;">
											<label style="width: 17%;" class="am-u-sm-3 am-form-label">交易日期 :</label>
											<span style="line-height:37px;"><fmt:formatDate value="${obj.buyCdt}" pattern="yyyy-MM-dd HH:mm" /></span>
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

</html>