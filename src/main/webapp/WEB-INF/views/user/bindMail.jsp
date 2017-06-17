<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>

	<jsp:include page="../common/userHead.jsp"></jsp:include>	
		<script type="text/javascript">
			$(document).ready(function() {
				$(".Sa_mid_btn").click(function() {
					$(".Sa_mid_lists").toggle(200);
				});
				$(".Sa_mid_btns").click(function() {
					$(".Sa_mid_listl").toggle(200);
				});
			});
		</script>
	</head>
	<body>
		<jsp:include page="../common/nav.jsp"></jsp:include>
		<div class="Sa_mid">
			<jsp:include page="../common/userSafeLeft.jsp"></jsp:include>
			<div class="Sa_mid_right">
				<p class="Sa_mid_set" style="color: #1ce4ef;">绑定邮箱</p>
				<form>
					<div class="form_group">
						<div class="Se_pwd_group">
							<label>邮箱：</label>
							<div class="Se_pwd_inp">
								<input id="mail_address" type="text" />
								<span style="line-height: 34px; margin-left: 10px;">@yahoo.com</span>
							</div>
						</div>
						<div class="Se_form_submit">
							<input id="toBind"  type="button" value="点击发送绑定邮件" />
						</div>
						<a href="#" style="margin-left: 7rem; margin-top: 1rem; color: white;">点击发送绑定邮件</a>
					</div>
				</form>
			</div>
		</div>
		<jsp:include page="../common/bottom.jsp"></jsp:include>
	</body>
	<script type="text/javascript">
		$("#toBind").click(function () {
			var email = $("#mail_address").val()+"@yahoo.com";
			alert(email);
			$.ajax({
				url:"user/safe/bindMail",
				type:"post",
				dataType:"json",
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				timeout:6000,
				data:{
					"email":email
				},
				success: function(returndata){
					alert(returndata.message);
				},
				error:function(){
					alert("绑定失败，请重试！");
				}
			})
		})
	</script>
</html>