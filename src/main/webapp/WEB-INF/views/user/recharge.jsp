<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<jsp:include page="../common/userHead.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		$(".Sa_mid_btn").click(function() {
			$(".Sa_mid_lists").toggle(200);
			if ($(".N_icon").attr("id") == "N_up") {
				$(".N_icon").attr({
					id : "N_down"
				});
				$("#N_down").addClass("Ndown_icon");
				$("#N_down").removeClass("N_icon");
			} else {
				$(".Ndown_icon").attr({
					id : "N_up"
				});
				$("#N_up").addClass("N_icon");
				$("#N_up").removeClass("Ndown_icon");
			}
		});
		$(".Sa_mid_btns").click(function() {
			$(".Sa_mid_listl").toggle(200);
			if ($(".Rdown_icon").attr("id") == "R_up") {
				$(".Rdown_icon").attr({
					id : "R_down"
				});
				$("#R_down").addClass(" R_icon");
				$("#R_down").removeClass(" Rdown_icon");
			} else {
				$(".R_icon").attr({
					id : "R_up"
				});
				$("#R_up").addClass(" Rdown_icon");
				$("#R_up").removeClass("R_icon");
			}
		});
		var speed = 600;//弹出显示动画
		$(".MB_Rech_btn").click(function(event) {
			$("#racePop").hide(speed); //动画显示
			$("#Mask").hide(speed);
		});
		
	});
</script>
<body style="position: relative;">
	<div id="Mask" class="MB_Rmask"></div>
	<jsp:include page="../common/nav.jsp"></jsp:include>
	<div class="Sa_mid">
		<jsp:include page="../common/userAccountLeft.jsp"></jsp:include>
		<div class="Sa_mid_right">
			<div id="racePop">
				<div class="MB_Rport">
					<p>提示</p>
				</div>
				<div class="Mb_Rport_text">请您通过支付宝充值到账号为    <c:out value="${ptAccount }"/>, 充值成功后
					资金将会在24小时之内到达您的账户,若期间出现问题,我们会第一时间联系您</div>
				<div class="MB_btn">
					<input class="MB_Rech_btn  haveMoney" type="submit" value="已打款" />
					<input class="MB_Rech_btn" type="submit" value="取消" />
				</div>
			</div>
			<div class="MB_mid_set">
				<a href="#">MC入金</a>&nbsp;&nbsp;&nbsp; <a href="#"
					style="color: white;">MC出金</a>
			</div>
			<div class="MB_form_group">
				<div class="Rel_form_group">
					<div class="Se_pwd_group Out_group">
						<label>充值金额：</label>
						<div class="Se_pwd_inp">
							<input class="MB_inp amount" type="text">
						</div>
					</div>
					<div class="Se_pwd_group Out_group">
						<label>入金地址：</label>
						<div class="Se_pwd_inp">
							<input class="MB_inp adress" type="text" value="${adress }">
						</div>
					</div>
					<div class="Se_pwd_group Out_group">
						<label>支付账号：</label>
						<div class="Se_pwd_inp">
							<input class="MB_inp payment" type="text" >
						</div>
					</div>
					<div class="Se_pwd_group Out_group">
						<label>资金密码：</label>
						<div class="Se_pwd_inp">
							<input class="MB_inp pwd" type="password">
						</div>
					</div>
					<span
						style="font-size: 14px; color: #9a2222; margin-left: 100px; margin-bottom: 15px; display: block;">${error }</span>
					<div class="Se_form_submit">
						<input id="MB_R" type="button" value="提交">
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../common/bottom.jsp"></jsp:include>
</body>
<script type="text/javascript">
$("#MB_R").click(function(){
	var speed = 600;//弹出显示动画
	var amount = $(".amount").val()*1;
	var adress = $(".adress").val();
	var pwd = $(".pwd").val();
	var payment = $(".payment").val();
	if(isNaN(amount)){
		alert("请输入正确的金钱格式!");
		return false;
	}
	if(amount==''){
		alert("请输入您要充值的金额!");
		return false;
	}
	if(amount<100){
		alert("充值金额不能小于100元!");
		return false;
	}
	if(adress==''){
		alert("请填写您的入金地址!");
		return false;
	}
	if(payment==''){
		alert("请输入您充值的支付宝账号!");
		return false;
	}
	if(pwd==''){
		alert("请输入您的资金密码!");
		return false;
	}
	
	$("#racePop").show(speed); //动画显示
	$("#Mask").show(speed);
	
});
</script>
<script type="text/javascript">
	$(".haveMoney").click(function() {
		var Type = "${Type}";
		var amount = $(".amount").val();
		var adress = $(".adress").val();
		var pwd = $(".pwd").val();
		var payment = $(".payment").val();
		//ajax获取页面内容
		$.ajax({
			url : "user/account/rechargeByData",
			type : "post",
			dataType : "html",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			timeout : 6000,
			data : {
				"amount" : amount,
				"adress" : adress,
				"pwd" : pwd,
				"payment": payment,
				"Type" : Type
			},
			//获取成功，为页面新添加元素初始化
			success : function(msg) {
				if(msg=='success'){
				window.location.href = "user/account/recharge?Type=" + Type;
				}else{
					window.location.href = msg;
				}
			}
		})
	})
</script>
</html>