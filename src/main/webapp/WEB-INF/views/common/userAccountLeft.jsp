<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="Real_mid_left">
	<div class="Sa_mid_white"></div>
	<div class="Sa_mid_triangle"></div>
	<p class="Sa_mid_font">财务中心</p>
	<ul class="Sa_mid_list">
		<li class="Sa_mid_item"><a class="Sa_mid_btn F_size"
			href="javascript:;"> 我的钱包 <i id="N_up" class="N_icon"></i></a>
			<ul class="Sa_mid_lists" style="display: none;">
				<c:forEach items="${list }" var="coin" begin="0">
					<li><a id="<c:out value="${coin.coinId }"/>"
						class="F_size coin <c:out value="${coin.coinName }"/>"
						style="cursor: pointer;"><c:out value="${coin.coinName }" />钱包</a></li>
				</c:forEach>
			</ul></li>
		<li class="Sa_mid_item"><a class="Sa_mid_btns F_size"
			href="javascript:;">出金入金 <i id="R_up" class="Rdown_icon"></i></a>
			<ul class="Sa_mid_listl">
				<li><a id="-1" class="F_size oi " style="cursor: pointer;">账户出金/入金</a></li>
				<li><a class=" F_size" href="user/finance/bank">资金账号管理</a></li>
			</ul></li>
	</ul>
</div>
<body>
	<script type="text/javascript">
		$(".oi").click(function() {
			//alert($(this).attr("class").substr(12));
			var coinID = $(this).attr("id").toString();
			//alert(coinID);
			var href = "user/account/recharge?Type=" + coinID;
			window.location.href = href;
		})
	</script>
	<script type="text/javascript">
		$(".coin").click(function() {
			var coinID = $(this).attr("id").toString();
			var href = "user/finance/coin?Type=" + coinID;
			window.location.href = href;
		})
	</script>


	<script type="text/javascript">
		var coinName = "${coinName}";
		var InOutName = "${InOutName}";

		if (coinName != "") {
			$(".Sa_mid_lists").attr("style", "display: block;");
			$(".Sa_mid_listl").attr("style", "display: none;");
			$(".Sa_mid_lists  .${coinName}").attr("style", "color: #1ce4ef;")
		} else {
			$(".Sa_mid_listl").attr("style", "display: block;");
			$(".Sa_mid_lists").attr("style", "display: none;");
			$(".Sa_mid_listl  .oi").attr("style", "color: #1ce4ef;")
		}
	</script>
</body>
</html>