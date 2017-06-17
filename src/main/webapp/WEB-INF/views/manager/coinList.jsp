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

	<body data-type="widgets" class="theme-black">
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
									<div class="widget-title  am-cf">货币管理</div>

								</div>
								<div class="widget-body  am-fr">
									<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
										<div class="am-form-group">
											<div class="am-btn-toolbar">
												<div class="am-btn-group am-btn-group-xs">
													<c:if test="${length==0}">
														<a href="manager/coin/initialize" class="am-btn am-btn-default am-btn-success">
														<span class="am-icon-plus"></span>初始化</a>
													</c:if>
												</div>
											</div>
										</div>
									</div>
									<div class="am-u-sm-12">
										<table style="width:100%;" class="am-table am-table-compact am-table-striped tpl-table-black " id="example-r">
											<thead>
												<tr>
													<th>货币名称</th>
													<th>线上手续费</th>
													<th>线下抵押币</th>
													<th>添加日期</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="obj" items="${list}" varStatus="status">
													<tr class="gradeX">
														<td>${obj.coinName}</td>
														<td>${obj.coinRate}</td>
														<td>${obj.coinCount}</td>
														<td><fmt:formatDate value="${obj.cdt}" pattern="yyyy-MM-dd HH:mm"/></td>
														<td>
															<div class="tpl-table-black-operation">
																<a href="manager/coin/toEdit?coinId=${obj.coinId}">
																	<i class="am-icon-pencil"></i> 编辑
																</a>
															</div>
														</td>
													</tr>
												</c:forEach>
												<!-- more data -->
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