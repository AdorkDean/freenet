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
			<jsp:include page="../common/userAccountLeft.jsp"></jsp:include>
			<div class="Sa_mid_right">
				<p class="Wal_mid_set">资金账号管理</p>
				<div class="Mg_add">
					<c:if test="${obj==null}">
						<div class="Mg_list_address">
							<a href="user/finance/bankSet">
							<i class="iconfont">&#xe60f;</i>
							<span class="Mg_address">添加地址</span>
							</a>
						</div>
					</c:if>
					<c:if test="${obj!=null }">
						<div class="Mg_list_address">
							<%-- <div class="Mg_list">
				              <label class="Mg_label">开户行：</label>
				              <div class="Mg_val">
				                <span>${obj.bankType }</span>
				              </div>
				            </div> --%>
				            <div class="Mg_list">
				              <label class="Mg_label">姓名：</label>
				              <div class="Mg_val">
				                <span>${obj.name }</span>
				              </div>
				            </div>
				            <div class="Mg_list">
				              <label class="Mg_label">支付宝：</label>
				              <div class="Mg_val">
				                <span>${obj.zfbNumber}</span>
				              </div>
				            </div>
				            <div class="Mg_list">
				              <label class="Mg_label">手机号：</label>
				              <div class="Mg_val">
				                <span>${obj.phone}</span>
				              </div>
				            </div>
				            <div class="Mg_list">
				              <label class="Mg_label">设置日期：</label>
				              <div class="Mg_val">
				                <span><fmt:formatDate value="${obj.cdt }" pattern="yyyy-MM-dd HH:mm" /></span>
				              </div>
				            </div>
				            <a href="user/bank/delete?bankId=${obj.bankId }"><i class="Mg_iconfont">&#xe648;</i></a>
						</div>
					</c:if>
					<c:if test="${msg!=null}">
						<p class="Mg_Prompt">${msg}</p>
					</c:if>
				</div>
			</div>
		</div>
		<jsp:include page="../common/bottom.jsp"></jsp:include>
	</body>
</html>