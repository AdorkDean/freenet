<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<jsp:include page="../common/userHead.jsp"></jsp:include>
	
	<body>
		<div class="nav_outer">
			<div class="nav_top">
				<img src="./resources/user/img/logo.png" alt="" />
			</div>
			<div class="nav_safe">
				<p>Comprehensive security strategy</p>
			</div>
			<form id="formId" action="user/Register" enctype="multipart/form-data" method="POST" onsubmit="return checkUser();">
			<div class="nav_mid">
				<p>用户注册</p>
				<ul>
					<li>
						<i class="iconfonts ico">&#xe625;</i>
						<input id="mobile" name="username" class="text_in" type="text" autocomplete="off" placeholder="请输入手机号码" />
						<select>
							<option>中国(+86)</option>
						</select>
					</li>
					<li>
						<i class="iconfonts">&#xe60d;</i>
						<input id="code" name="code" class="nav_Verify" type="text" autocomplete="off" placeholder="请输入验证码" />
						<img id="vcode1" onclick="change();" class="nav_Verify_img" alt="" src="user/imgCode">
					</li>
					<li>
						<i class="iconfonts">&#xe60d;</i>
						<input id="verifyCode" name="verifyCode" class="nav_Verify" type="text" autocomplete="off" placeholder="请输入短信验证码" />
						<input id="smss" class="nav_Verify_btn" type="button" value="点击获取" />
					</li>
					<li>
						<i class="iconfonts ico">&#xe681;</i>
						<input id="passwordone" name="password" class="text_in" type="password" autocomplete="off" placeholder="请输入密码" />
					</li>
					<li>
						<i class="iconfonts ico">&#xe681;</i>
						<input id="passwordtwo" name="repassword" class="text_in" type="password" autocomplete="off" placeholder="请再次输入密码" />
					</li>
					<li>
						<c:if test="${msg!=null }">
							<li>
								<p class="nav_test"><i class="icon_font">&#xe644;</i>${msg }</p>
							</li>
						</c:if>
					</li>
				</ul>
				
				
				<input class="nav_Sub" type="submit" value="注册" />
				<div class="nav_pwd">
					<a class="nav_Login " href="user/toLogin">登录</a>
				</div>
			</div>
			</form>
		</div>
	</body>
	
<script type="text/javascript">
function change(){
	document.getElementById("vcode1").src="user/imgCode?"+Math.random();
}
</script>

<!-- 获取验证码操作 -->
<script type="text/javascript">
var wait=120;// 120s等待
$(function(){
	$("#smss").click(function(){
		var mobile = $("#mobile").val().trim();
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
        o.attr("value","获取验证码");
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
function checkUser(){
	var username = $("#mobile").val();
	var code = $("#code").val();
	var verifyCode = $("#verifyCode").val();
	var password = $("#passwordone").val();
	var rePassword = $("#passwordtwo").val();
	if(username==''){
		alert("请输入手机号码!");
		return false;
	}
	if(!verifyPhone(username)){
		alert("手机号码格式不正确!");
		return false;
	}
	if(code==''){
		alert("验证码不能为空!");
		return false;
	}
	if(verifyCode==''){
		alert("请输入短信验证码!");
		return false;
	}
	if(password==''){
		alert("请输入密码!");
		return false;
	}
	if(rePassword==''){
		alert("请输入您的确认密码!");
		return false;
	}
	if(password!=rePassword){
		alert("输入的密码不一致!");
		return false;
	}
	return true;
	
}

</script>
</html>