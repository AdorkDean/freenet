<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
	<jsp:include page="../common/userHead.jsp"></jsp:include>
	<body>
		<jsp:include page="../common/nav.jsp"></jsp:include>
		
		<div class="Acc_mid">
			<div class="Acc_step">
				<img class="imgSet" src="./resources/user/img/pwdSet.png" alt="" />
			</div>
			<form method="POST" action="user/pwd/phone_setpwd" enctype="multipart/form-data" onsubmit="return checkPwd();" class="Acc_form_group">
				<div class="Acc_textin">
					<input id="mobile" type="hidden" name="username" value="${username }"/>
					<p class="Acc_phone">您正在通过<span style="color: #1bdde8;">手机</span>找回密码</p>
					<div class="Acc_group">
						<span class="Acc_lable">新密码：</span>
						<div class="Acc_textinput">
							<input id="password" name="password" class="Acc_select" type="password" />
						</div>
					</div>
					<div class="Acc_group">
						<span class="Acc_lable">确认新密码：</span>
						<div class="Acc_textinput">
							<input id="repassword" name="repassword" class="Acc_select" type="password" />
						</div>
					</div>
				</div>
				<input class="Acc_next" type="submit" value="下一步>" />
			</form>
		</div>
		<jsp:include page="../common/bottom.jsp"></jsp:include>
	</body>
	
<script type="text/javascript">
function checkPwd(){
	var password = $("#password").val();
	var repassword = $("#repassword").val();
	if(password==''){
		$("#writePwd").html("请输入密码!");
		return false;
	}else{
		$("#writePwd").html("");
	}
	if(repassword==''){
		$("#writeRepwd").html("请输入密码!");
		return false;
	}else{
		$("#writeRepwd").html("");
	}
	if(password!=repassword){
		$("#checkSame").html("两次密码不一致!");
		return false;
	}
	return true;
}
</script>

</html>