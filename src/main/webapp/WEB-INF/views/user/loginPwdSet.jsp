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
				<p class="Sa_mid_set" style="color: #1ce4ef;">安全设置 / <span>设置登录密码</span></p>
					<div class="form_group">
						<div class="Se_pwd_group">
							<label>旧登录密码：</label>
							<div class="Se_pwd_inp">
								<input type="hidden" id="userId" name="userId" value="${obj.userId}"/>
								<input id="oldpwd" name="oldpassword" type="password" />
							</div>
						</div>
						<div class="Se_pwd_group">
							<label>新登录密码：</label>
							<div class="Se_pwd_inp">
								<input id="pwd" name="password" type="password" />
							</div>
						</div>
						<div class="Se_pwd_group">
							<label>确认登录密码：</label>
							<div class="Se_pwd_inp">
								<input id="repPwd" name="repassword" type="password" />
							</div>
						</div>
						<div class="Se_form_submit">
							<input id="pwdChange" type="button" value="设置" />
						</div>
					</div>
			</div>
		</div>
		<jsp:include page="../common/bottom.jsp"></jsp:include>
	</body>
	
<script type="text/javascript">
//充值登录密码请求操作
$(function(){
	$("#pwdChange").click(function(){
		var oldpassword = $("#oldpwd").val();
		var password = $("#pwd").val();
		var repassword = $("#repPwd").val();
		var userId = $("#userId").val();
		if(oldpassword==''){
			alert("请输入您的旧密码!");
			return false;
		}
		if(password==''){
			alert("请填写您的密码!");
			return false;
		}
		if(repassword==''){
			alert("请确认您的密码!");
			return false;
		}
		if(password!=repassword){
			alert("两次密码不一致!");
			return false;
		}
		$.ajax({
			url:'user/safe/lgPwdUpdate',
			type: 'POST',
			data:{userId:userId,oldpassword:oldpassword,password:password},
			dataType:'json',
			async:false,
			success:function(data){
				if(data.result==true){
					var userId = data.userId;
					alert(data.msg);
					window.location.href="user/safe/safeCenter";
				}else{
					alert(data.msg);
				}
			},
			error:function(){
				alert("网络繁忙，请稍后重试!");
			}
		});
	});
});
	

</script>

</html>