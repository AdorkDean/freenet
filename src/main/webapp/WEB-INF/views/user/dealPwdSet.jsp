<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<html>
<jsp:include page="../common/userHead.jsp"></jsp:include>	

	<body>
		<jsp:include page="../common/nav.jsp"></jsp:include>
		<script type="text/javascript">
			var error = "${error}";
			if(error!=""){
				alert(error);
			}
		</script>
		<div class="Sa_mid">
			<jsp:include page="../common/userSafeLeft.jsp"></jsp:include>
			<div class="Sa_mid_right">
				<p class="Sa_mid_set" style="color: #1ce4ef;">安全设置 / <span>设置资金密码</span></p>
					<div class="form_group">
						<div class="Se_pwd_group">
							<label>资金密码：</label>
							<div class="Se_pwd_inp">
								<input type="hidden" id="userId" value="${obj.userId }"/>
								<input type="password" id="dealPwd" name="dealPwd"/>
							</div>
						</div>
						<div class="Se_pwd_group">
							<label>确认资金密码：</label>
							<div class="Se_pwd_inp">
								<input type="password" id="reDelPwd" name="reDelPwd"/>
							</div>
						</div>
						<div class="Se_pwd_group">
							<label>手机号：</label>
							<div class="Se_pwd_inp">
								<input type="hidden" id="mobile" name="username" value="${obj.username}"/>
								<span>${obj.username}</span>
							</div>
						</div>
						<div class="Se_pwd_group">
							<label>验证码：</label>
							<div class="Se_pwd_inp">
								<input style="width: 35%;" type="text" id="verifyCode" name="verifyCode"/>
								<input id="smss" style="width: 20%;margin-left: 5%;" type="button" value="获取验证码" />
							</div>
						</div>
						<div class="Se_form_submit">
							<input onclick="submitDelPwd();" type="button" value="设置" />
						</div>
					</div>
			</div>
		</div>
		<jsp:include page="../common/bottom.jsp"></jsp:include>
	</body>
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
<!-- 检查用户输入格式 -->
<script type="text/javascript">
function submitDelPwd(){
	var userId = $("#userId").val();
	var username = $("#mobile").val();
	var dealPwd = $("#dealPwd").val();
	var reDelPwd = $("#reDelPwd").val();
	var verifyCode = $("#verifyCode").val();
	if(dealPwd==''){
		alert("请填写资金密码!");
		return false;
	}
	if(reDelPwd==''){
		alert("请确认您的资金密码!");
		return false;
	}
	if(verifyCode==''){
		alert("请输入短信验证码!");
		return false;
	}
	if(dealPwd!=reDelPwd){
		alert("两次密码不一致!");
		return false;
	}
	$.ajax({
		url:'user/safe/dealPwdUpdate',
		type: 'POST',
		data:{userId:userId,username:username,dealPwd:dealPwd,verifyCode:verifyCode},
		dataType:'json',
		async:false,
		success:function(data){
			if(data.result==true){
				alert(data.msg);
				window.location.href="user/safe/safeCenter";
			}else{
				alert(data.msg);
			}
		},
		error:function(){
			alert("网络繁忙,请稍后再试!");
		}
	});
}
</script>

<!-- 短信发送请求 -->
<script type="text/javascript">
var wait=120;// 120s等待
$(function(){
	$("#smss").click(function(){
		var mobile = $("#mobile").val();
		var dealPwd = $("#dealPwd").val();
		var reDelPwd = $("#reDelPwd").val();
		if(dealPwd==''||reDelPwd==''){
			alert("请先完善好密码信息!");
			return false;
		}
		if(wait==120){
			time();
			$.ajax({
				cache:true,
				type:'GET',
				url:'user/smsVerifyCode',
				data:{mobile:mobile},
				async:false,
				error:function(request){
					alert('网络异常,请稍后重试!');
				},
				success:function(data){
					if(data=="success"){
						alert("验证码已发送！");
					}else{
						alert("验证码发送失败！");
					}
				}
			});
		}
	});
	
});
function time() {
	var o=$("#smss");
    if (wait == 0) {
    	o.removeAttr("disabled");
        o.attr("value","点击获取");
        wait = 120;
    } else {
    	o.attr("disabled");
        o.attr("value","重新发送(" + wait + ")");
        wait--;
        setTimeout(function() {
            time(o)
        },1000);
    }
}
</script>


</html>