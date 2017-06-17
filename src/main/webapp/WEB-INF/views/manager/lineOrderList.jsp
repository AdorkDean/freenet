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
									<div class="widget-title am-fl">用户订单</div>
									<div class="widget-function am-fr">
										<a href="javascript:;" class="am-icon-cog"></a>
									</div>
								</div>
								<div class="widget-body  am-fr">
									<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
										<div class="am-form-group">
											<div class="am-btn-toolbar">
												<div class="am-btn-group am-btn-group-xs" style="margin-left: 22px;">
													<a onclick="history.go(-1)" class="am-btn am-btn-default am-btn-success"><span class="fa arrow-left"></span> 返回</a>
												</div>
											</div>
										</div>
									</div>
									<div class="am-u-sm-12">
										<table style="width: 100%;" class="am-table am-table-compact am-table-striped tpl-table-black " id="example-r">
											<thead>
												<tr>
													<th>卖出量</th>
													<th>卖出价格</th>
													<th>状态</th>
													<th>卖出日期</th>
													<th>交易日期</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="obj" items="${list}" varStatus="status">
													<tr class="gradeX">
														<td class="">${obj.sellCoin }</td>
														<td class="">${obj.price}</td>
														<c:if test="${obj.status==1 }">
															<td><span class="label-yes">已完成</span></td>
															<td class=""><fmt:formatDate value="${obj.sellCdt}" pattern="yyyy-MM-dd HH:mm" /></td>
															<td class=""><fmt:formatDate value="${obj.buyCdt}" pattern="yyyy-MM-dd HH:mm" /></td>
														</c:if>
														<c:if test="${obj.status==2 }">
															<td><span class="label-warning">卖单中</span></td>
															<td class=""><fmt:formatDate value="${obj.sellCdt}" pattern="yyyy-MM-dd HH:mm" /></td>
															<td class="">---</td>
														</c:if>
														<c:if test="${obj.status==3 }">
															<td><span class="label-danger">已撤单</span></td>
															<td class=""><fmt:formatDate value="${obj.sellCdt}" pattern="yyyy-MM-dd HH:mm" /></td>
															<td class="">---</td>
														</c:if>
														<td class="">
														<c:if test="${obj.status==1 }">
															<div class="tpl-table-black-operation">
																<a href="manager/order/online/edit?id=${obj.id }">
																	<i class="am-icon-pencil"></i>查看
																</a>
															</div>
														</c:if>
													</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
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