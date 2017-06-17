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
			<p class="Wal_mid_set">MC(CNY)</p>
			<div class="Wal_Detailed">
				<div class="Wal_timer">
					<label>起始日期</label>
					<div class="Wal_start">
						<input type="text" readonly="readonly" id="startdateinfo" value="${start }"/>
					</div>
					<div class="Wal_end">
						<input type="text" readonly="readonly" id="enddateinfo" value="${end }"/>
					</div>
				</div>
				<div class="Wal_day">
					<ul>
						<li><a href="user/offline/findDay?type=2&day=1">今天</a></li>
						<li><a href="user/offline/findDay?type=2&day=7">7天</a></li>
						<li><a href="user/offline/findDay?type=2&day=15">15天</a></li>
						<li><a href="user/offline/findDay?type=2&day=30">30天</a></li>
					</ul>
				</div>
				<div class="Wal_op_type">
					<span>交易状态</span>
					<ul>
						<li><a href="user/trading/offlineSellRecord">全部</a></li>
						<li><a href="user/offline/findByStatus?status=2&type=1">等待买方打款</a></li>
						<li><a href="user/offline/findByStatus?status=3&type=1">等待确认收款</a></li>
						<li><a href="user/offline/findByStatus?status=4&type=1">买方违约</a></li>
						<li><a href="user/offline/findByStatus?status=5&type=1">卖方违约</a></li>
						<li><a href="user/offline/findByStatus?status=1&type=1">完全成交</a></li>
						<li><a href="user/offline/Intervention?intervention=1&type=1">平台介入</a></li>
					</ul>
				</div>
			</div>
			<div class="Wal_list En_list">
				<table>
					<tr>
						<th>时间</th>
						<th>卖出价格</th>
						<th>卖出数量</th>
						<th>成交金额</th>
						<th>状态</th>
						<th>收款时间</th>
						<th>操作</th>
					</tr>
					<c:forEach var="obj" items="${list }">
						<tr>
							<td><fmt:formatDate value="${obj.sellCdt}" pattern="yyyy-MM-dd HH:mm" /></td>
							<td>${obj.price }</td>
							<td>${obj.sellCoin }</td>
							<td>${obj.realPrice }</td>
							<c:if test="${obj.status==1 }">
								<td>已完成</td>
							</c:if>
							<c:if test="${obj.status==2}">
								<td>等待买方打款</td>
							</c:if>
							<c:if test="${obj.status==3 }">
								<td>待您确认收款</td>
							</c:if>
							<c:if test="${obj.status==4}">
								<td>买方违约</td>
							</c:if>
							<c:if test="${obj.status==5 }">
								<td>卖方违约</td>
							</c:if>
							<c:if test="${obj.status==7 }">
								<td>未收到打款</td>
							</c:if>
							<c:if test="${obj.status==8 }">
								<td>买方已发起违约</td>
							</c:if>
							
							<td><fmt:formatDate value="${obj.realCdt}" pattern="yyyy-MM-dd HH:mm" /></td>
							<td>
								<c:if test="${obj.status==3 }">
									<a style="color:white;" href="user/offlin/sureFunds?id=${obj.id }&type=2" onClick="return confirm('您确定收到资金了吗?');">确认收款</a>
									<a style="color:white;margin-left: 7%;" href="user/offline/breach?id=${obj.id }&type=2" onClick="return confirm('您确定未收买方打款吗?');">未打款</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<jsp:include page="../common/userPager.jsp"></jsp:include>
	</div>
	<jsp:include page="../common/bottom.jsp"></jsp:include>
	<script type="text/javascript">
		jeDate({
			dateCell : "#startdateinfo",
			format : "YYYY-MM-DD",
			isinitVal : true,
			isTime : true, //isClear:false,
			minDate : "2014-09-19 ",
			maxDate : "2099-09-19 ",
			choosefun:function(val) {
				$("#startdateinfo").val(val);
			},  //选中日期后的回调
			clearfun:function(val) {
				return false;
			}  //清除日期后的回调
		})
		jeDate({
			dateCell : "#enddateinfo",
			format : "YYYY-MM-DD ",
			isinitVal : true,
			isTime : true, //isClear:false,
			minDate : "2014-09-19 ",
			maxDate : "2099-09-19 ",
			choosefun:function(val) {
				$("#enddateinfo").val(val);
				if($("#startdateinfo").val()==''){
					alert("请选择开始日期!");
					return false;
				}
				window.location.href='user/offline/findDate?type=2&start='+$("#startdateinfo").val()+
						'&end='+$("#enddateinfo").val();
			},  //选中日期后的回调
			clearfun:function(val) {
				return false;
			}  //清除日期后的回调
		})
	</script>
</body>

</html>