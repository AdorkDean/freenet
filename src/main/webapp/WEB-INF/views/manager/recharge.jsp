<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../common/managerHead.jsp"></jsp:include>
<script src="./resources/manager/js/echarts.min.js"></script>
<link rel="stylesheet" href="./resources/manager/css/amazeui.min.css" />
<link rel="stylesheet"
	href="./resources/manager/css/amazeui.datatables.min.css" />
<link rel="stylesheet" href="./resources/manager/css/app.css">
<script src="./resources/manager/js/jquery.min.js"></script>
</head>

<body data-type="widgets">
	<script type="text/javascript">
		//初始化页面分页
		$(document).ready(function() {
			var lastindex = $("#pageNow").val();
			var st = "${status}X";
			var stID = "#" + st;
			var lastID = "#" + lastindex;
			$(lastID).parent().attr("class", "am-active");
			$(stID).attr("selected", "selected");
		})
	</script>
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
								<div class="widget-title am-fl">充值列表</div>
								<div class="widget-function am-fr">
									<a href="javascript:;" class="am-icon-cog"></a>
								</div>
							</div>
							<form action="manager/recharge/toRecharge" method="post">
								<div class="Vip_select">
									<div class="Vip_sel" style="width: 19%;">
										<div class="Vip_sele" style="width: 97%;">
											<input name="payment" type="text" class="tpl-form-input"
												placeholder="请输入账号" value="${payment }" />
										</div>
									</div>
									<div class="Vip_sel" style="margin-left: 3%;">
										<label>状态</label>
										<div class="Vip_sele">
											<select id="selectStatus" data-am-selected name="status"
												onchange="selectChange();">
												<option id="-1X" value="-1">全部</option>
												<option id="0X" value="0">已提交</option>
												<option id="1X" value="1">审核中</option>
												<option id="2X" value="2">已通过</option>
												<option id="3X" value="3">未通过</option>
											</select>
										</div>
									</div>
									<div class="Vip_sel" style="width: 8%; margin-left: 3%;">
										<div class="Vip_sele" style="width: 100%;">
											<input class="am-btn am-btn-secondary" type="submit"
												style="border-radius: 2px; width: 100%;" value="搜索" />
										</div>
									</div>
								</div>
							</form>
							<div class="widget-body  am-fr">
								<div class="am-u-sm-12">
									<table style="width: 100;"
										class="am-table am-table-compact am-table-striped tpl-table-black "
										id="example-r">
										<thead>
											<tr>
												<th>充值地址</th>
												<th>充值账号</th>
												<th>充值金额</th>
												<th>充值时间</th>
												<th>状态</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${recordList }" var="record" begin="0">
												<tr class="gradeX">
													<td class=""><c:out value="${record.walletAddress}" /></td>
													<td class=""><c:out value="${record.payment}" /></td>
													<td class=""><c:out value="${record.rechargeQuantity}" /></td>
													<td class=""><fmt:formatDate type="date"
															value="${record.fromDate}" /></td>
													<td class=""><c:choose>
															<c:when test="${ record.status==0}">
																<span class="label-warning">已提交</span>
															</c:when>
															<c:when test="${ record.status==1}">
																<span class="label-warning">审核中</span>
															</c:when>
															<c:when test="${ record.status==2}">
																<span class="label-yes">已完成</span>
															</c:when>
															<c:when test="${ record.status==3}">
																<span class="label-not-pass">未通过</span>
															</c:when>
														</c:choose></td>
													<td>
														<div class="tpl-table-black-operation">
															<c:choose>
																<c:when test="${ record.status==0}">
																	<a id="<c:out value="${record.id +100}"/>"
																		href="javascript:;" class="shenhe"> <i
																		class="am-icon-pencil"></i> 审核
																	</a>
																</c:when>
																<c:when test="${ record.status==1}">
																	<a id="<c:out value="${record.id +100}"/>"
																		href="javascript:;" class="wancheng"> <i id=""
																		class="am-icon-pencil"></i> 完成
																	</a>
																	<a id="<c:out value="${record.id +100}"/>"
																		href="javascript:;" class="tuihui"> <i id=""
																		class="am-icon-pencil"></i> 退回
																	</a>
																</c:when>
															</c:choose>
														</div>
													</td>
												</tr>
											</c:forEach>
											<!-- more data -->
										</tbody>
									</table>
								</div>
								<div class="am-u-lg-12 am-cf">
									<div class="am-fr">
										<input type="hidden" id="pageNow" name="pageNow"
											value="${page }" /> <input type="hidden" id="status"
											value="${status }" /> <input type="hidden" id="payment"
											value="${payment }" />
										<ul class="am-pagination tpl-pagination">
											<c:if test="${totalPages>1}">
												<li class=""><a style="cursor: pointer;" id="last_id">«</a></li>
											</c:if>
											<c:forEach var="i" begin="1" end="${totalPages}">
												<li class=""><a style="cursor: pointer;" id="${i}"
													class="indexbutton"><c:out value="${i}" /></a></li>
											</c:forEach>
											<c:if test="${totalPages>1}">
												<li class=""><a style="cursor: pointer;" id="next_id">»</a></li>
											</c:if>
										</ul>
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
	function selectChange() {
		var status = $('#selectStatus option:selected').val();
		//alert(status);
		window.location.href = "manager/recharge/toRecharge?status="+status;
	}

	$("tbody").on(
			"click",
			".shenhe",
			function() {
				alert("hello world");
				var id = $(this).attr("id") - 100;
				var p = $("#pageNow").val();
				var status = $("#status").val();
				var payment = $("#payment").val();
				window.location.href = "manager/recharge/recharge?id=" + id
						+ "&page=" + p + "&status=" + status + "&payment="
						+ payment;
			})
	$("tbody").on(
			"click",
			".wancheng",
			function() {
				var id = $(this).attr("id") - 100;
				var p = $("#pageNow").val();
				var status = $("#status").val();
				var payment = $("#payment").val();
				window.location.href = "manager/recharge/complate?id=" + id
						+ "&page=" + p + "&status=" + status + "&payment="
						+ payment;
			})
	$("tbody").on(
			"click",
			".tuihui",
			function() {
				var id = $(this).attr("id") - 100;
				var p = $("#pageNow").val();
				var status = $("#status").val();
				var payment = $("#payment").val();
				window.location.href = "manager/recharge/tuihui?id=" + id
						+ "&page=" + p + "&status=" + status + "&payment="
						+ payment;
			})
</script>

<script type="text/javascript">
	var totalpages = "${totalPages}";
	//点击上一页
	$("#last_id")
			.click(
					function() {
						var lastindex = $("#pageNow").val();
						var status = $("#status").val();
						var payment = $("#payment").val();
						var l_index = lastindex - 1;
						if (l_index > 0) {
							var nowID = "#" + (lastindex);
							$(nowID).parent().attr("class", "");
							var lastID = "#" + (l_index);
							//alert(lastID);
							$(lastID).parent().attr("class", "am-active");
							//ajax获取页面内容
							$
									.ajax({
										url : "manager/recharge/toRechargeByPage",
										type : "post",
										dataType : "html",
										contentType : "application/x-www-form-urlencoded; charset=utf-8",
										timeout : 6000,
										data : {
											"index" : l_index,
											"status" : status,
											"payment" : payment
										},
										//获取成功，为页面新添加元素初始化
										success : function(msg) {
											$("tbody").empty();
											$("tbody").append(msg);
											$("#pageNow").val(l_index);
										}
									})
						}
					})
	//点击下一页
	$("#next_id")
			.click(
					function() {
						var lastindex = $("#pageNow").val();
						var status = $("#status").val();
						var payment = $("#payment").val();
						var n_index = lastindex - 1 + 2;
						if (lastindex < totalpages) {
							var nowID = "#" + (lastindex);
							$(nowID).parent().attr("class", "");
							var nextID = "#" + (n_index);
							$(nextID).parent().attr("class", "am-active");
							//ajax获取页面内容
							$
									.ajax({
										url : "manager/recharge/toRechargeByPage",
										type : "post",
										dataType : "html",
										contentType : "application/x-www-form-urlencoded; charset=utf-8",
										timeout : 6000,
										data : {
											"index" : n_index,
											"status" : status,
											"payment" : payment
										},
										//获取成功，为页面新添加元素初始化
										success : function(msg) {
											$("tbody").empty();
											$("tbody").append(msg);
											$("#pageNow").val(n_index);
										}
									})
						}
					})
</script>
<script type="text/javascript">
	$(".indexbutton").click(function() {
		var lastindex = $("#pageNow").val();
		var status = $("#status").val();
		var payment = $("#payment").val();
		var lastID = "#" + lastindex;
		$(lastID).parent().attr("class", "");
		var index = $(this).attr("id");
		$(this).parent().attr("class", "am-active");

		//ajax获取页面内容
		$.ajax({
			url : "manager/recharge/toRechargeByPage",
			type : "post",
			dataType : "html",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			timeout : 6000,
			data : {
				"index" : index,
				"status" : status,
				"payment" : payment
			},
			//获取成功，为页面新添加元素初始化
			success : function(msg) {
				//alert(msg);
				$("tbody").empty();
				$("tbody").append(msg);
				$("#pageNow").val(index);
			}
		})
	})
</script>
</html>