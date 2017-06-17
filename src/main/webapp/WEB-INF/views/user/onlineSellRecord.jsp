<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<jsp:include page="../common/userHead.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		$(".Sa_mid_btn").click(function() {
			$(".Sa_mid_lists").toggle(200);
			if ($(".N_icon").attr("id") == "N_up") {
				$(".N_icon").attr({
					id : "N_down"
				});
				$("#N_down").addClass("Ndown_icon");
				$("#N_down").removeClass("N_icon");
			} else {
				$(".Ndown_icon").attr({
					id : "N_up"
				});
				$("#N_up").addClass("N_icon");
				$("#N_up").removeClass("Ndown_icon");
			}
		});
		$(".Sa_mid_btns").click(function() {
			$(".Sa_mid_listl").toggle(200);
			if ($(".Rdown_icon").attr("id") == "R_up") {
				$(".Rdown_icon").attr({
					id : "R_down"
				});
				$("#R_down").addClass(" R_icon");
				$("#R_down").removeClass(" Rdown_icon");
			} else {
				$(".R_icon").attr({
					id : "R_up"
				});
				$("#R_up").addClass(" Rdown_icon");
				$("#R_up").removeClass("R_icon");
			}
		});
	});
</script>
<body>
	<jsp:include page="../common/nav.jsp"></jsp:include>
	<div class="Sa_mid">
		<jsp:include page="../common/userTradingHallLeft.jsp"></jsp:include>
		<div class="Sa_mid_right">
			<p class="Wal_mid_set" style="margin-left: 9.3rem;">
				<a href="#" style="color: white;">MC Pending order record</a>
			</p>
			<div class="Wal_Detailed">
				<div class="Wal_timer">
					<label>起始日期</label>
					<div class="Wal_start">
						<input type="text"  placeholder="起始日期"   readonly="readonly" id=startdateinfo />
					</div>
					<div class="Wal_end">
						<input type="text"  placeholder="结束时间"  readonly="readonly" id="enddateinfo" />
					</div>
				</div>
				<div class="Wal_day">
					<ul>
						<li><a href="user/trading/onlineSellRecord?days=1">今天</a></li>
						<li><a href="user/trading/onlineSellRecord?days=7">7天</a></li>
						<li><a href="user/trading/onlineSellRecord?days=15">15天</a></li>
						<li><a href="user/trading/onlineSellRecord?days=30">30天</a></li>
					</ul>
				</div>
				<div class="Wal_op_type">
					<span><h3>委托状态</h3></span>
					<ul>
						<li><a href="user/trading/onlineSellRecord">全部</a></li>
						<li><a href="user/trading/onlineSellRecord?status=1">完全成交</a></li>
						<li><a href="user/trading/onlineSellRecord?status=2">挂单中</a></li>
						<li><a href="user/trading/onlineSellRecord?status=3">已撤单</a></li>
					</ul>
				</div>
			</div>

			<div class="Wal_list En_list">
				<table>
					<tr>
						<th>时间</th>
						<th>价格</th>
						<th>卖出量</th>
						<th>手续费</th>
						<th>状态</th>
						<th>成交时间</th>
					</tr>
					<c:forEach items="${lineOrders }" var="order" begin="0">
						<tr>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${order.sellCdt }" /></td>
							<td><c:out value="${order.price }" /></td>
							<td><c:out value="${order.sellCoin }" /></td>
							<td><fmt:formatNumber  minFractionDigits="4" value="${ order.sellCoin*rate}" /></td>
							<c:choose>
								<c:when test="${order. status==1}">
									<td>已完成</td>
									<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${order.buyCdt}" /></td>
								</c:when>
								<c:when test="${order. status==2}">
									<td>挂单中</td>
									<td></td>
								</c:when>
								<c:when test="${order. status==3}">
									<td>已撤销</td>
									<td></td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="Not_Page">
				<input type="hidden" id="pageNow" name="pageNow" value="1" /> <input
					type="hidden" id="status" name="status" value="${status }" />
					<input type="hidden" id="days" name="days" value="${days }" />
				<ul>
					<c:if test="${totalPages>1 }">
						<li style="border-right: 1px solid #4d8db4;"><a id="last_id">《</a></li>
					</c:if>
					<c:forEach begin="1" var="i" end="${totalPages }">
						<li style="border-right: 1px solid #4d8db4;"><a id="${i}"
							class="indexbutton"><c:out value="${i}"></c:out></a></li>
					</c:forEach>
					<c:if test="${totalPages>1 }">
						<li style="border-right: 1px solid #4d8db4;"><a id="next_id">》</a></li>
					</c:if>
				</ul>
				<a id="dangqianye" >当前页 : 1</a>
			</div>
		</div>
	</div>
	<div class="Acc_bot">
		<p>联系我们: 邮箱123456@freenet.com</p>
		<p>Copyright 2011 - 2016 Free net All rights reserved.</p>
	</div>
	<script type="text/javascript">
		var s = "${startDate}";
		var e = "${endDate}";
		if(s!=""||e!=""){
			$("#startdateinfo").val(s);
			$("#enddateinfo").val(e);
		}
	</script>
	<script type="text/javascript">
	jeDate({
		dateCell : "#startdateinfo",
		format : "YYYY年MM月DD日",
		isinitVal : false,
		minDate : "2014-09-19 ",
		maxDate : "2099-09-19 ",
		choosefun : function(val) {
			$("#startdateinfo").val(val);
			if ($("#enddateinfo").val() != "") {
				window.location.href = "user/trading/onlineSellRecord?startDate="
						+ $("#startdateinfo").val()
						+ "&endDate="
						+ $("#enddateinfo").val();
			}
		}
	});
	jeDate({
		dateCell : "#enddateinfo",
		format : "YYYY年MM月DD日",
		minDate : "2014-09-19",
		maxDate : "2099-09-19 ",
		choosefun : function(val) {
			$("#enddateinfo").val(val);
			if ($("#startdateinfo").val() != "") {
				window.location.href = "user/trading/onlineSellRecord?startDate="
						+ $("#startdateinfo").val()
						+ "&endDate="
						+ $("#enddateinfo").val();
			} else {
				alert("请先填写起始日期！");
			}
		}
	});
	</script>
</body>
<script type="text/javascript">
	var totalpages = "${totalPages}";
	var startDate = $("#startdateinfo").val();
	var endDate = $("#enddateinfo").val();
	//点击上一页
	$("#last_id")
			.click(
					function() {
						var lastindex = $("#pageNow").val();
						var l_index = lastindex - 1;
						var status = $("#status").val();
						var days = $("#days").val();
						if (l_index > 0) {
							//ajax获取页面内容
							$
									.ajax({
										url : "user/trading/onlineSellRecordByPage",
										type : "post",
										dataType : "html",
										contentType : "application/x-www-form-urlencoded; charset=utf-8",
										timeout : 6000,
										data : {
											"index" : l_index,
											"status" : status,
											"days":days,
											"startDate":startDate,
											"endDate":endDate
										},
										//获取成功，为页面新添加元素初始化
										success : function(msg) {
											$(".En_list").empty();
											$(".En_list").append(msg);
											$("#pageNow").val(l_index);
											$("#dangqianye").text("当前页 : "+l_index);
										}
									})
						}
					})
	//点击下一页
	$("#next_id")
			.click(
					function() {
						var lastindex = $("#pageNow").val();
						var n_index = lastindex - 1 + 2;
						var status = $("#status").val();
						var days = $("#days").val();
						if (lastindex < totalpages) {
							//ajax获取页面内容
							$
									.ajax({
										url : "user/trading/onlineSellRecordByPage",
										type : "post",
										dataType : "html",
										contentType : "application/x-www-form-urlencoded; charset=utf-8",
										timeout : 6000,
										data : {
											"index" : n_index,
											"status" : status,
											"days":days,
											"startDate":startDate,
											"endDate":endDate
										},
										//获取成功，为页面新添加元素初始化
										success : function(msg) {
											$(".En_list").empty();
											$(".En_list").append(msg);
											$("#pageNow").val(n_index);
											$("#dangqianye").text("当前页 : "+n_index);
										}
									})
						}
					})
</script>
<script type="text/javascript">
	$(".indexbutton").click(function() {
		var lastindex = $("#pageNow").val();
		var index = $(this).attr("id");
		var status = $("#status").val();
		var days = $("#days").val();
		//ajax获取页面内容
		$.ajax({
			url : "user/trading/onlineSellRecordByPage",
			type : "post",
			dataType : "html",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			timeout : 6000,
			data : {
				"index" : index,
				"status" : status,
				"days":days,
				"startDate":startDate,
				"endDate":endDate
			},
			//获取成功，为页面新添加元素初始化
			success : function(msg) {
				$(".En_list").empty();
				$(".En_list").append(msg);
				$("#pageNow").val(index);
				$("#dangqianye").text("当前页 : "+index);
			}
		})
	})
</script>
</html>