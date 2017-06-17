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
									<div class="widget-title am-fl">线上订单</div>
									<div class="widget-function am-fr">
										<a href="javascript:;" class="am-icon-cog"></a>
									</div>
								</div>
								
								<div class="Vip_select">
									<div class="Vip_sel">
										<label>状态</label>
										<div class="Vip_sele">
											<select id="orderStatus" onchange="findOrder();"data-am-selected>
												<option value="0">--请选择--</option>
												<option value="1">已完成</option>
												<option value="2">卖单中</option>
												<option value="3">已撤单</option>
											</select>
										</div>
									</div>
									<form action="manager/order/onlineKeyword" method="post" onsubmit="return checkKeyWord();">
									<div class="Vip_sel" style="width: 20%;">
										<div class="Vip_sele">
											<input name="keyword" id="keyword" type="text" style="width:135%;" class="tpl-form-input" placeholder="请输入卖方账号/姓名"  />
										</div>
									</div>
									<div class="Vip_sel" style="width: 20%;">
										<div class="Vip_sele">
											<input type="submit" class="am-btn am-btn-secondary" style="border-radius:2px ; width:100%;" value="搜索"  />
										</div>
									</div>
									</form>
								</div>
								
								<div class="widget-body  am-fr">
									<div class="am-u-sm-12">
										<table style="with:100%;" class="am-table am-table-compact am-table-striped tpl-table-black on-table " id="example-r">
											<thead>
												<tr>
													<th>账户</th>
													<th>姓名</th>
													<th>卖出数量</th>
													<th>价格</th>
													<!-- <th>手续费</th> -->
													<th>交易状态</th>
													<th>日期</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="obj" items="${list}" varStatus="status">
													<tr class="gradeX">
													<td class="">${obj.sellUsername}</td>
													<td class="">${obj.sellName }</td>
													<td class="">${obj.sellCoin}</td>
													<td class="">${obj.price}</td>
													<%-- <td class="">${obj.price/100}</td> --%>
													<c:if test="${obj.status==2}">
														<td><span class="label-warning">卖单中</span></td>
													</c:if>
													<c:if test="${obj.status==1}">
														<td><span class="label-yes">已完成</span></td>
													</c:if>
													<c:if test="${obj.status==3}">
														<td><span class="label-danger">已撤单</span></td>
													</c:if>
													<td class=""><fmt:formatDate value="${obj.sellCdt}" pattern="yyyy-MM-dd HH:mm" /></td>
													<td>
														<div class="tpl-table-black-operation on-td">
															<c:if test="${obj.status==1}">
																<a href="manager/order/online/edit?id=${obj.id }">
																	<i class="am-icon-pencil"></i> 订单详情
																</a>
															</c:if>
															<a href="manager/order/userOnline/list?sellUserId=${obj.sellUserId }">
																<i class="am-icon-pencil" ></i> 用户订单
															</a>
														</div>
													</td>
												</tr>
												</c:forEach>
												<!-- more data -->
											</tbody>
										</table>
									</div>
									<jsp:include page="../common/managerPager.jsp"></jsp:include>
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
function findOrder(){
	var status = $("#orderStatus").find("option:selected").val();
	if(status==0){
		return false;
	}
	window.location.href="manager/order/onlineStatusSearch?status="+status;
}
function checkKeyWord(){
	if($("#keyword").val()==''){
		return false;
	}
	return true;
}
</script>
</html>