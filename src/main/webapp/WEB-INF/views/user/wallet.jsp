<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<html>
<jsp:include page="../common/userHead.jsp"></jsp:include>
<script src="./resources/user/jedate/jedate.js"></script>

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
			<p class="Wal_mid_set">MC钱包</p>
			<div class="Wal_mid_Acc">
				<div class="Wal_money f1">
					<p class="Wal_white">可用${coinName} :</p>
					<p class="Wal_color">${obj.coin }
						&nbsp;&nbsp;<span class="Wal_white"></span>
					</p>
				</div>
				<div class="Wal_money f1">
					<p class="Wal_white">冻结 :</p>
					<p class="Wal_color">${obj.coinFrozen }</p>
				</div>
				<div class="Wal_money">
					<p class="Wal_white">资金余额  :</p>
					<p class="Wal_color">${money} RMB</p>
				</div>
				<div style="margin-top: 16px;" class="Wal_operation">
					<a href="user/account/toRecharge?Type=${Type}&adress=${obj.adress}"
						class="Wal_whites">入金</a> <a href="user/wallet/toWithDraw"
						class="Wal_whites Wal_active">出金</a>
				</div>
			</div>
			<div class="Wal_address">
				<p>
					入金地址 &nbsp;<span>${obj.adress}</span>
				</p>
			</div>
			<p class="Wal_p">MC最近明细</p>
			<div class="Wal_Detailed">
				<div class="Wal_op_type">
					<span>操作类型</span>
					<ul>
						<li ><a id="rujjin" style="color: ;" href="user/finance/coin?Type=0">充值</a></li>
						<li><a id="chujin" href="user/finance/coin?Type=0&out=1">出金</a></li>
					</ul>
				</div>
			</div>
			<div class="Wal_list">
				<table>
					<c:choose>
						<c:when test="${iort==0}">
							<tr>
								<th>充值数量</th>
								<th>账户余额</th>
								<th>充值状态</th>
								<th>充值时间</th>
								<th>到账时间</th>
							</tr>
							<c:forEach items="${records}" begin="0" var="record">
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
									<td><fmt:formatDate type="date"
											value="${record.fromDate }" /></td>
									<td><fmt:formatDate type="date" value="${record.toDate }" /></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:when test="${iort==1}">
							<tr>
								<th>时间</th>
								<th>提现金额</th>
								<th>提现状态</th>
								<th>支付宝账号</th>
							</tr>
							<c:forEach items="${records}" begin="0" var="record">
								<tr>
									<td><fmt:formatDate type="date" value="${record.cdt }" /></td>
									<td><c:out value="${record.drawMoney }" /></td>
									<c:choose>
										<c:when test="${record.status ==0}">
											<td><c:out value="已提现" /></td>
										</c:when>
										<c:when test="${record.status ==1}">
											<td><c:out value="审核中" /></td>
										</c:when>
										<c:when test="${record.status ==2}">
											<td><c:out value="未通过" /></td>
										</c:when>
										<c:otherwise>
											<td></td>
										</c:otherwise>
									</c:choose>
									<td><c:out value="${record.zfbNumber }" /></td>
								</tr>
							</c:forEach>
						</c:when>
					</c:choose>

				</table>
			</div>
		</div>
	</div>
	<jsp:include page="../common/bottom.jsp"></jsp:include>
</body>
<script type="text/javascript">
	var out = "${iort}";
	if(out == "0"){
		$("#rujjin").attr("style","color: #1CE4EF;");
		$("#chujin").attr("style","");
	}else{
		$("#rujjin").attr("style","");
		$("#chujin").attr("style","color: #1CE4EF;");
	}
</script>

</html>