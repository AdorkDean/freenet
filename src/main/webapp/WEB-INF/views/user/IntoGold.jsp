<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
		<jsp:include page="../common/userAccountLeft.jsp"></jsp:include>
		<div class="Sa_mid_right">
			<input type="hidden" id="pageNow" name="pageNow" value="1" /> <input
				type="hidden" id="limitTime" name="limitTime" value="-1" />
			<div class="MB_mid_set">

				<a id="-1" class="rj">MC入金</a>&nbsp;&nbsp;&nbsp; <a id="-1" class="cj" style="color: white;">MC出金</a>
			</div>
		</div>
		<div class="Wal_Detailed">
			<div class="Wal_timer">
				<label>时间查询</label>
				<div class="Wal_start">
					<input type="text" placeholder="起始日期" readonly="readonly" id="startdateinfo" />
				</div>
				<div class="Wal_end">
					<input type="text" placeholder="结束日期" readonly="readonly" id="enddateinfo" />
				</div>
			</div>
			<div class="Wal_day">
				<ul>
					<li><a id="1" class="limitTime" style="cursor: pointer;">今天</a></li>
					<li><a id="7" class="limitTime" style="cursor: pointer;">7天</a></li>
					<li><a id="15" class="limitTime" style="cursor: pointer;">15天</a></li>
					<li><a id="30" class="limitTime" style="cursor: pointer;">30天</a></li>
					<li><a id="-1" class="limitTime" style="cursor: pointer;">全部</a></li>
				</ul>
			</div>
			<div style="margin-bottom: 15px" class="Wal_op_type"></div>
		</div>
		<div class="Wal_list">
			<div class="container">
				<table>
					<tr>
						<th>充值数量</th>
						<th>账户余额</th>
						<th>充值状态</th>
						<th>充值时间</th>
						<th>到账时间</th>
					</tr>
					<c:forEach items="${recordList }" begin="0" var="record">
						<tr>
							<td><c:out value="${record.rechargeQuantity }" /></td>
							<td><c:out value="${record.balance }" /></td>
							<c:choose>
								<c:when test="${record.status ==0}">
									<td><c:out value="已提交" /></td>
								</c:when>
								<c:when test="${record.status ==1}">
									<td><c:out value="审核中" /></td>
								</c:when>
								<c:when test="${record.status ==2}">
									<td><c:out value="已完成" /></td>
								</c:when>
								<c:when test="${record.status ==3}">
									<td><c:out value="未通过" /></td>
								</c:when>
							</c:choose>
							<td><fmt:formatDate pattern ="yyyy-MM-dd" value="${record.fromDate }" /></td>
							<td><fmt:formatDate pattern ="yyyy-MM-dd" value="${record.toDate }" /></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="Not_Page">
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
	<jsp:include page="../common/bottom.jsp"></jsp:include>
	<script type="text/javascript">
	$(".rj").click(function() {
		var coinID = $(this).attr("id");
		var href = "user/account/recharge?Type=" + coinID;
		window.location.href = href;
	})
	$(".cj").click(function() {
		var coinID = $(this).attr("id");
		var href = "user/account/gold?Type=" + coinID;
		window.location.href = href;
	})
	</script>
	<script type="text/javascript">
		var s = "${startDate}";
		var e = "${endDate}";
		if(s!=""||e!=""){
			$("#startdateinfo").val(s);
			$("#enddateinfo").val(e);
		}
	</script>
	<script type="text/javascript">
		$(".limitTime")
				.on("click",
						function() {
							var limitTime = $(this).attr("id");
							$
									.ajax({
										url : "user/account/rechargeByPage",
										type : "post",
										dataType : "html",
										contentType : "application/x-www-form-urlencoded; charset=utf-8",
										timeout : 6000,
										data : {
											"index" : 1,
											"limitTime" : limitTime
										},
										//获取成功，为页面新添加元素初始化
										success : function(content) {
											var context = content.substring(0,content.indexOf("||"));
											$(".container").empty();
											$(".container").append(context);
											var fenye = content.substr(content.indexOf("||")+2);
											$(".Not_Page").empty();
											$(".Not_Page").append(fenye);
											$("#limitTime").val(limitTime);
											$("#pageNow").val(1);
											$("#startdateinfo").val("");
											$("#enddateinfo").val("");
										}
									})
						})
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
				window.location.href = "user/account/recharge?startDate="
						+ $("#startdateinfo").val()
						+ "&endDate="
						+ $("#enddateinfo").val()+"&Type=-1";
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
				window.location.href = "user/account/recharge?startDate="
						+ $("#startdateinfo").val()
						+ "&endDate="
						+ $("#enddateinfo").val()+"&Type=-1";
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
	$(".Not_Page")
			.on("click", "#last_id",function() {
						var lastindex = $("#pageNow").val();
						var limitTime = $("#limitTime").val();
						var l_index = lastindex - 1;
						if (l_index > 0) {
							var nowID = "#" + (lastindex);
							var lastID = "#" + (l_index);
							//ajax获取页面内容
							$
									.ajax({
										url : "user/account/rechargeByPage",
										type : "post",
										dataType : "html",
										contentType : "application/x-www-form-urlencoded; charset=utf-8",
										timeout : 6000,
										data : {
											"index" : l_index,
											"limitTime" : limitTime,
											"startDate":startDate,
											"endDate":endDate
										},
										//获取成功，为页面新添加元素初始化
										success : function(content) {
											var context = content.substring(0,content.indexOf("||"));
											$(".container").empty();
											$(".container").append(context);
											var fenye = content.substr(content.indexOf("||")+2);
											$(".Not_Page").empty();
											$(".Not_Page").append(fenye);
											$("#pageNow").val(l_index);
											$("#dangqianye").text("当前页 : "+l_index);
										}
									})
						}
					})
	//点击下一页
	$(".Not_Page")
			.on("click","#next_id",
					function() {
						var lastindex = $("#pageNow").val();
						var limitTime = $("#limitTime").val();
						var n_index = lastindex - 1 + 2;
						if (lastindex < totalpages) {
							var nowID = "#" + (lastindex);
							var nextID = "#" + (n_index);
							//ajax获取页面内容
							$
									.ajax({
										url : "user/account/rechargeByPage",
										type : "post",
										dataType : "html",
										contentType : "application/x-www-form-urlencoded; charset=utf-8",
										timeout : 6000,
										data : {
											"index" : n_index,
											"limitTime" : limitTime,
											"startDate":startDate,
											"endDate":endDate
										},
										//获取成功，为页面新添加元素初始化
										success : function(content) {
											var context = content.substring(0,content.indexOf("||"));
											$(".container").empty();
											$(".container").append(context);
											var fenye = content.substr(content.indexOf("||")+2);
											$(".Not_Page").empty();
											$(".Not_Page").append(fenye);
											$("#pageNow").val(n_index);
											$("#dangqianye").text("当前页 : "+n_index);
										}
									})
						}
					})
</script>
<script type="text/javascript">
	$(".Not_Page").on("click","a",function() {
		var lastindex = $("#pageNow").val();
		var limitTime = $("#limitTime").val();
		var lastID = "#" + lastindex;
		var index = $(this).attr("id");
		$.ajax({
			url : "user/account/rechargeByPage",
			type : "post",
			dataType : "html",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			timeout : 6000,
			data : {
				"index" : index,
				"limitTime" : limitTime,
				"startDate":startDate,
				"endDate":endDate
			},
			//获取成功，为页面新添加元素初始化
			success : function(content) {
				var context = content.substring(0,content.indexOf("||"));
				$(".container").empty();
				$(".container").append(context);
				var fenye = content.substr(content.indexOf("||")+2);
				$(".Not_Page").empty();
				$(".Not_Page").append(fenye);
				$("#pageNow").val(index);
				$("#dangqianye").text("当前页 : "+index);
			}
		})
	})
</script>
</html>