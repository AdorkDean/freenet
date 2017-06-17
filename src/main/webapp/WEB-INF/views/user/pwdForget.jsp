<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<jsp:include page="../common/userHead.jsp"></jsp:include>
	
	<body>
		<jsp:include page="../common/nav.jsp"></jsp:include>
		
		<div class="Acc_mid">
			<div class="Acc_step">
				<img class="imgSet" src="./resources/user/img/1.png" alt="" />
			</div>

			<form method="POST" action="user/pwd/phone_find" enctype="multipart/form-data" onsubmit="return checkPhone();" class="Acc_form_group">
				<div class="Acc_textin">
					<p class="Acc_phone">您正在通过<span style="color: #1bdde8;">手机</span>找回密码</p>
					<div class="Acc_group">
						<span class="Acc_lable">所在地：</span>
						<div class="Acc_textinput">
							<select class="Acc_select">
								<option>中国(+86)</option>
							</select>
						</div>
					</div>
					<div class="Acc_group">
						<span class="Acc_lable">手机号码：</span>
						<div class="Acc_textinput">
							<input id="mobile" name="username" class="Acc_select" type="text" autocomplete="off"/>
						</div>
					</div>
					<div class="Acc_group">
						<span class="Acc_lable">验证码：</span>
						<div class="Acc_textinput">
							<input id="code" name="code" class="Acc_select" type="text" autocomplete="off"/>
							<img id="vcode1" onclick="change();" class="Acc_Verify" src="user/imgCode" alt="" />
						</div>
					</div>
					<div class="Acc_group">
						<span class="Acc_lable">手机验证码：</span>
						<div class="Acc_textinput">
							<input id="verifyCode" name="verifyCode" class="Acc_select" type="text" autocomplete="off"/>
							<input id="smss" class="Acc_btn" type="button" value="点击获取" />
						</div>
						<p>${msg }</p>
					</div>
				</div>
				<input class="Acc_next" type="submit" value="下一步>" />
			</form>

		</div>
		<jsp:include page="../common/bottom.jsp"></jsp:include>
	</body>
	
<script type="text/javascript">
function change(){
	document.getElementById("vcode1").src="user/imgCode?"+Math.random();
}
</script>

<!-- 获取验证码  -->
<script type="text/javascript">
var wait=120;// 120s等待
$(function(){
	$("#smss").click(function(){
		var mobile = $("#mobile").val();
		var code = $("#code").val();
		if(mobile==''){
			alert("请填写手机号!");
			return false;
		}
		if(!verifyPhone(mobile)){
			alert("手机号码格式不正确!");
			return false;
		}
		if(code==''){
			alert("请先输入验证码!");
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

<script type="text/javascript">
function checkPhone(){
	var mobile = $("#mobile").val().trim();
	var code = $("#code").val().trim();
	var verifyCode = $("#verifyCode").val();
	if(mobile==''){
		alert("请输入手机账号!");
		return false;
	}
	if(!verifyPhone(mobile)){
		alert("手机格式不正确!");
		return false;
	}
	if(code==''){
		alert("请输入验证码!");
		return false;
	}
	if(verifyCode==''){
		alert("请输入短信验证码!");
		return false;
	}
	return true;
}
</script>

</html>