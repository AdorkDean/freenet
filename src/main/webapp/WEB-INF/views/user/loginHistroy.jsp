<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<html>
<jsp:include page="../common/userHead.jsp"></jsp:include>

<script type="text/javascript">
	$(document).ready(function() {
		$(".Sa_mid_btn").click(function() {
			$(".Sa_mid_lists").toggle(200);
			if($(".N_icon").attr("id")=="N_up"){
				$(".N_icon").attr({id:"N_down"});
				$("#N_down").addClass("Ndown_icon");
				$("#N_down").removeClass("N_icon");
			}else{
				$(".Ndown_icon").attr({id:"N_up"});
				$("#N_up").addClass("N_icon");
				$("#N_up").removeClass("Ndown_icon");
			}
		});
		$(".Sa_mid_btns").click(function() {
			$(".Sa_mid_listl").toggle(200);
			if($(".Rdown_icon").attr("id")=="R_up"){
				$(".Rdown_icon").attr({id:"R_down"});
				$("#R_down").addClass(" R_icon");
				$("#R_down").removeClass(" Rdown_icon");
			}else{
				$(".R_icon").attr({id:"R_up"});
				$("#R_up").addClass(" Rdown_icon");
				$("#R_up").removeClass("R_icon");
			}
		});
	});
</script>
	<body>
		<jsp:include page="../common/nav.jsp"></jsp:include>
		<div class="Sa_mid">
			<jsp:include page="../common/userSafeLeft.jsp"></jsp:include>
			<div class="Sa_mid_right">
				<p class="Sa_mid_set" ><a style="color:#1ce4ef ;">最近登录历史</a></p>
				<div class="Record_mid">
					<table class="Rec_table">
						<tr>
							<th>登录时间</th>
							<th>登录IP</th>
						</tr>
						<c:forEach var="histroy" begin="0" end ="6" items="${list}" varStatus="status">
							<tr>
								<td><fmt:formatDate value="${histroy.loginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${histroy.loginIp}</td>
							</tr>
						</c:forEach>
						
					</table>
					
				</div>
			</div>
		</div>
		<jsp:include page="../common/bottom.jsp"></jsp:include>
	</body>
</html>