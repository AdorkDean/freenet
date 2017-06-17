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
				<p class="Sa_mid_set">安全设置</p>
				<div class="Sa_mid_Acc">
					<div class="Sa_safeimg">
						<span class="Sa_span">高</span>
					</div>
					<div class="Sa_mid_vip">
						<label>会员账号：</label>
						<span>${obj.username }</span>
					</div>
					<div class="Sa_mid_vip">
						<label>安全等级：</label>
						<span>高</span>
						<c:if test="${obj.dealPwd==null&&obj.email==null }">
							<progress value="50" max="100"></progress> <span class="Sa_mid_num">50%</span>
						</c:if>
						<c:if test="${obj.dealPwd!=null&&obj.email==null|| obj.dealPwd==null&&obj.email!=null}">
							<progress value="75" max="100"></progress> <span class="Sa_mid_num">75%</span>
						</c:if>
						<c:if test="${obj.dealPwd!=null&&obj.email!=null }">
							<progress value="100" max="100"></progress> <span class="Sa_mid_num">100%</span>
						</c:if>
					</div>
				  </div>
				<table class="Sa_mid_table">
					<tr>
						<td style="text-align: center;">
							<i class="S_iconfont">&#xe741;</i>&nbsp;&nbsp;<span class="Sa_mid_binding">绑定邮箱</span>
						</td>
						<c:if test="${obj.email==null }">
							<td>
								<i class=" safe_icon">&#xe644;</i><span style="color: red; font-size: 14px;">未绑定</span>&nbsp;&nbsp;&nbsp;
								<span style="color: #b4c0ca;font-size: 14px;">暂未绑定</span>&nbsp;&nbsp;&nbsp;<span>|</span>&nbsp;&nbsp;&nbsp;
								<a style="color: #1ce4ef; font-size: 14px;" href="#">点击注册邮箱</a>
							</td>
							<td>
								<a class="Sa_mid_active" href="user/safe/toBindMail">绑定</a>
							</td>
						</c:if>
						<c:if test="${obj.email!=null }">
							<td>
								<i class="sa_icon">&#xe612;</i><span style="color: white;font-size: 14px;">已绑定</span>&nbsp;&nbsp;&nbsp;
								<span style="color: #b4c0ca;font-size: 14px;">您绑定的邮箱:${obj.email }</span>
							</td>
							<td>
							<a class="Sa_mid_active" href="user/safe/toBindMail">重置</a>
							</td>
						</c:if>
						
					</tr>
					<tr>
						<td style="text-align: center;">
							<i class="S_iconfont">&#xe625;</i>&nbsp;&nbsp;<span class="Sa_mid_binding">绑定手机</span>
						</td>
						<td>
							<i class="sa_icon ">&#xe612;</i><span style="color: white;font-size: 14px;">已绑定</span>&nbsp;&nbsp;&nbsp;
							<span style="color: #b4c0ca;font-size: 14px;">您绑定的手机：${obj.username }</span>
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">
							<i class="S_iconfont">&#xe681;</i>&nbsp;&nbsp;<span class="Sa_mid_binding">登录密码</span>
						</td>
						<td>
							<i class="sa_icon ">&#xe612;</i><span style="color: white;font-size: 14px;">已设置 </span>&nbsp;&nbsp;&nbsp;
							<span style="color: #b4c0ca;font-size: 14px;">登录时使用</span>
						</td>
						<td>
							<a class="Sa_mid_active" href="user/safe/lgPwdSet?userId=${obj.userId }">重置</a>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;">
							<i class="S_iconfont">&#xe60d;</i> &nbsp;&nbsp;<span class="Sa_mid_binding">资金密码</span>
						</td>
						<td>
							<c:if test="${obj.dealPwd==null}">
								<i class=" safe_icon">&#xe644;</i><span style="color: red;font-size: 14px;">未设置 </span>&nbsp;&nbsp;&nbsp;
							</c:if>
							<c:if test="${obj.dealPwd!=null}">
								<i class="sa_icon">&#xe612;</i><span style="color: white;font-size: 14px;">已设置 </span>&nbsp;&nbsp;&nbsp;
							</c:if>
							<span style="color: #b4c0ca;font-size: 14px;">账户资金变动时，需先验证该资金密码</span>
						</td>
						<td>
							<a class="Sa_mid_active" href="user/safe/toDealPwdSet">
								<c:if test="${obj.dealPwd==null }">设置</c:if>
								<c:if test="${obj.dealPwd!=null }">重置</c:if>
							</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<jsp:include page="../common/bottom.jsp"></jsp:include>
	</body>
</html>