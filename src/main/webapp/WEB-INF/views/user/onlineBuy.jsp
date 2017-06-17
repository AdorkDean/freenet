<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<jsp:include page="../common/userHead.jsp"></jsp:include>
<script type="text/javascript">
	var error = "${error}";
	if (error != "") {
		alert(error);
	}
</script>
<script type="text/javascript">
	$(document).ready(
			function() {
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
				var speed = 600; //弹出显示动画
				$(".En_list")
						.on(
								"click",
								".MB_R",
								function(event) {
									event.stopPropagation(); //停止事件的传播,阻止它被分派到其他 Document 节点。
									$("#Purbuy").show(speed); //动画显示
									$("#Mask").show(speed);
									$("#sellAmount").text(
											$(this).parent().parent().children(
													"td.maichuliang").text());
									$("#sellPrice").text(
											$(this).parent().parent().children(
													"td.jiage").text());
									var id = $(this).parent().parent().attr("id");
									$("form").attr(
											"action",
											"user/trading/onlineBuyProcess?id="
													+ id);
								});
				$(".On_Rech_btn").click(function(event) {
					$("#Purbuy").hide(speed); //动画显示
					$("#Mask").hide(speed);
				});
				$(".Btn_close").click(function(event) {
					$("#Purbuy").hide(speed); //动画显示
					$("#Mask").hide(speed);
				});
			});
</script>
<body style="position: relative;">
	<div id="Mask" class="MB_Rmask"></div>
	<jsp:include page="../common/nav.jsp"></jsp:include>
	<div class="Sa_mid">
		<jsp:include page="../common/userTradingHallLeft.jsp"></jsp:include>
		<div class="Sa_mid_right">
			<div id="Purbuy" class="On_buy">
				<form method="post">
					<div class="MB_Rport">
						<p>买入</p>
						<i class="Btn_close">&#xe68c;</i>
					</div>
					<div class="On_form_group">
						<div class="Se_pwd_group Out_group">
							<label>卖出数量：</label>
							<div class="On_pwd_lab">
								<label class="On_num" id="sellAmount"></label>
							</div>
						</div>
						<div class="Se_pwd_group Out_group">
							<label>卖出价格：</label>
							<div class="On_pwd_lab">
								<label class="On_num" id="sellPrice"></label>
							</div>
						</div>
						<div class="Se_pwd_group Out_group">
							<label>当前余额：</label>
							<div class="On_pwd_lab">
								<label class="On_num" id="currentBalance"><c:out
										value="${money }" /></label>
							</div>
						</div>
						<div class="Se_pwd_group Out_group">
							<label>交易密码：</label>
							<div class="On_pwd_inp">
								<input class="On_inp" type="password" name="dealpwd"
									placeholder="请输入交易密码" autocomplete="off" />
							</div>
						</div>
					</div>
					<div class="On_btn">
						<input class="On_Rech_btn" type="submit" value="买入">
					</div>
				</form>
			</div>
			<div class="On_title">
				<p class="Wal_mid_set">MC卖出列表</p>
			</div>
			<div class="On_mid">
				<p>当前卖单</p>
				<div class="On_list En_list">
					<table>
						<tr>
							<th>时间</th>
							<th>卖出量</th>
							<th>价格</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${lineOrders }" var="order" begin="0">
							<tr id="${order.id }">
								<td class="shijian"><fmt:formatDate pattern="yyyy-MM-dd"
										value="${order.sellCdt }" /></td>
								<td class="maichuliang"><c:out value="${order.sellCoin }" /></td>
								<td class="jiage"><c:out value="${order.price }" /></td>
								<c:choose>
									<c:when test="${sessionScope.sessionUser.userId ==order.sellUserId }">
										<td></td>
									</c:when>
									<c:otherwise>
										<td><a class="MB_R">购买</a></td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
					</table>
				</div>
				<a id="dangqianye" style="float:right ;margin-right: 40px;color: white;" >当前页 : 1</a>
				<div class="Not_Page">
					<input type="hidden" id="pageNow" name="pageNow" value="1" />
					<ul style="margin-right: 0;margin-bottom: 1rem;">
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
				</div>
			</div>
			<div class="On_right">
				<p>实时成交</p>
				<table class="On_table">


				</table>
			</div>
		</div>
	</div>
	<div class="Acc_bot">
		<p>联系我们: 邮箱123456@freenet.com</p>
		<p>Copyright 2011 - 2016 Free net All rights reserved.</p>
	</div>
</body>
<script type="text/javascript">
	var c = 0
	var t
	function timedCount() {
		//ajax获取页面内容
		$.ajax({
			url : "user/trading/getOnlineBuyNew",
			type : "post",
			dataType : "html",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			timeout : 6000,
			data : {},
			//获取成功，为页面新添加元素初始化
			success : function(msg) {
				$(".On_table").empty();
				$(".On_table").html(msg);
			}
		})
		t = setTimeout("timedCount()", 30000)
	}
	$("document").ready(function() {
		timedCount();
	});
</script>
<script type="text/javascript">
	var totalpages = "${totalPages}";
	//点击上一页
	$("#last_id")
			.click(
					function() {
						var lastindex = $("#pageNow").val();
						var l_index = lastindex - 1;
						if (l_index > 0) {
							//ajax获取页面内容
							$
									.ajax({
										url : "user/trading/onlineBuyByPage",
										type : "post",
										dataType : "html",
										contentType : "application/x-www-form-urlencoded; charset=utf-8",
										timeout : 6000,
										data : {
											"index" : l_index
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
						if (lastindex < totalpages) {
							//ajax获取页面内容
							$
									.ajax({
										url : "user/trading/onlineBuyByPage",
										type : "post",
										dataType : "html",
										contentType : "application/x-www-form-urlencoded; charset=utf-8",
										timeout : 6000,
										data : {
											"index" : n_index
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
		//ajax获取页面内容
		$.ajax({
			url : "user/trading/onlineBuyByPage",
			type : "post",
			dataType : "html",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			timeout : 6000,
			data : {
				"index" : index
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