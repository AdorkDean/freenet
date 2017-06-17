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
.cdtspan{
	line-height:37px;
	margin-left:1%;
}
.offlable{
	width: 17%;
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
											<label for="user-name" class="am-u-sm-3 am-form-label offlable">订单状态 :</label>
											<div class="am-u-sm-9" style="float: left;">
												<c:if test="${obj.status==1 }">
													<input type="text" style="width: 45%;" class="tpl-form-input" id="user-name" value="已完成" readonly="readonly" />
												</c:if>
												<c:if test="${obj.status==2 }">
													<input type="text" style="width: 45%;" class="tpl-form-input" id="user-name" value="等待买方打款" readonly="readonly" />
												</c:if>
												<c:if test="${obj.status==3 }">
													<input type="text" style="width: 45%;" class="tpl-form-input" id="user-name" value="等待卖方确认收款" readonly="readonly" />
												</c:if>
												<c:if test="${obj.status==4 }">
													<input type="text" style="width: 45%;" class="tpl-form-input" id="user-name" value="买方违约" readonly="readonly" />
												</c:if>
												<c:if test="${obj.status==5 }">
													<input type="text" style="width: 45%;" class="tpl-form-input" id="user-name" value="卖方违约" readonly="readonly" />
												</c:if>
												<c:if test="${obj.status==8 }">
													<input type="text" style="width: 45%;" class="tpl-form-input" id="user-name" value="买方发起违约" readonly="readonly" />
												</c:if>
												
												
											</div>
										</div>
										<div class="am-form-group" style="padding-left: 10%;">
											<label class="am-u-sm-3 am-form-label offlable">卖方账号 :</label>
											<div class="am-u-sm-9" style="float: left;">
												<input type="text" style="width: 45%;" value="${obj.sellUsername }" readonly="readonly"/>
											</div>
										</div>
										<div class="am-form-group" style="padding-left: 10%;">
											<label class="am-u-sm-3 am-form-label offlable">卖方姓名 :</label>
											<div class="am-u-sm-9" style="float: left;">
												<input type="text" style="width: 45%;" value="${obj.sellName }" readonly="readonly" />
											</div>
										</div>
										<div class="am-form-group" style="padding-left: 10%;">
											<label class="am-u-sm-3 am-form-label offlable">卖出数量 :</label>
											<div class="am-u-sm-9" style="float: left;">
												<input type="text" style="width: 45%;" value="${obj.sellCoin }" readonly="readonly" />
											</div>
										</div>
										<div class="am-form-group" style="padding-left: 10%;">
											<label class="am-u-sm-3 am-form-label offlable">卖出价格 :</label>
											<div class="am-u-sm-9" style="float: left;">
												<input type="text" style="width: 45%;" value="${obj.price }" readonly="readonly" />
											</div>
										</div>
										<div class="am-form-group" style="padding-left: 10%;">
											<label class="am-u-sm-3 am-form-label offlable">交易价格 :</label>
											<div class="am-u-sm-9" style="float: left;">
												<input type="text" style="width: 45%;" value="${obj.realPrice }" readonly="readonly" />
											</div>
										</div>
										<div class="am-form-group" style="padding-left: 10%;">
											<label class="am-u-sm-3 am-form-label offlable">买方账号 :</label>
											<div class="am-u-sm-9" style="float: left;">
												<input type="text" style="width: 45%;" value="${obj.buyUsername }" readonly="readonly" />
											</div>
										</div>
										<div class="am-form-group" style="padding-left: 10%;">
											<label class="am-u-sm-3 am-form-label offlable">买方姓名 :</label>
											<div class="am-u-sm-9" style="float: left;">
												<input type="text" style="width: 45%;" value="${obj.buyName }" readonly="readonly" />
											</div>
										</div>
										<div class="am-form-group" style="padding-left: 10%;">
											<label class="am-u-sm-3 am-form-label offlable">出售日期 :</label>
											<span class="cdtspan"><fmt:formatDate value="${obj.sellCdt}" pattern="yyyy-MM-dd HH:mm" /></span>
										</div> 
										<div class="am-form-group" style="padding-left: 10%;">
											<label class="am-u-sm-3 am-form-label offlable">购买日期 :</label>
											<span class="cdtspan"><fmt:formatDate value="${obj.buyCdt}" pattern="yyyy-MM-dd HH:mm" /></span>
										</div>
										<div class="am-form-group" style="padding-left: 10%;">
											<label class="am-u-sm-3 am-form-label offlable">收款截止日期 :</label>
											<span class="cdtspan" ><fmt:formatDate value="${obj.realCdt}" pattern="yyyy-MM-dd HH:mm" /></span>
										</div>
										<c:if test="${obj.status==7||obj.status==8 }">
											<div class="am-form-group">
												<div class="am-u-sm-9 am-u-sm-push-3">
													<a href="manager/user/offOrderBreach?type=1&id=${obj.id}" onClick="return confirm('确定为卖方违约吗?');" class="am-btn am-btn-primary tpl-btn-bg-color-success ">卖方违约</a>
													<a href="manager/user/offOrderBreach?type=2&id=${obj.id}" onClick="return confirm('确定买方违约吗?');" class="am-btn am-btn-danger tpl-btn-bg-color-success ">买方违约</a>
												</div>
											</div>
										</c:if>
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