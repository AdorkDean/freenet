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
	<body style="position: relative;">
		<div id="Mask" style="display: none;" class="MB_Rmask"></div>
		<jsp:include page="../common/nav.jsp"></jsp:include>
		<div class="Sa_mid">
			<div class="Real_mid_left">
				<div class="Sa_mid_white"></div>
				<div class="Sa_mid_triangle"></div>
				<p class="Sa_mid_font">财务中心</p>
				<ul class="Sa_mid_list">
					<li class="Sa_mid_item">
						<a class="Sa_mid_btn F_size"  href="javascript:;">我的钱包
						<i id="N_up" class="N_icon"></i></a>
						<ul class="Sa_mid_lists" style="display: none;">
							<li class="Sa_mid_listitem">
								<a class="F_size" href="#">MC钱包</a>
							</li>
						</ul>
					</li>
					<li class="Sa_mid_item">
						<a class="Sa_mid_btns F_size" style="color: #1ce4ef;" href="javascript:;">出金入金
						<i id="R_up" class="Rdown_icon"></i></a>
						<ul class="Sa_mid_listl" >
							<li>
								<a class="Sa_active F_size" href="#">MC出金/入金</a>
							</li>
							<li>
								<a class=" F_size" href="#">资金账号管理</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
			<div class="Sa_mid_right">
				<div class="MB_mid_set">
        			<a href="#" style="color: white;">MC入金</a>&nbsp;&nbsp;&nbsp;
        			<a href="#">MC出金</a>
        		</div>
				<form action="user/wallet/withDraw" method="POST" enctype="multipart/form-data" onsubmit="return checkDraw();" class="MB_form_group">
					<div class="Rel_form_group">
						<div class="Se_pwd_group Out_group">
							<label>剩余金额：</label>
							<div class="Se_pwd_inp">
								<p>${userMoney.money }&nbsp;&nbsp;<span>RMB</span></p>
								<input id="userMoney" type="hidden" value="${userMoney.money}"/>
							</div>
						</div>
						<div class="Se_pwd_group Out_group">
							<label>提现金额：</label>
							<div class="Se_pwd_inp">
								<input id="drawMoney" name="drawMoney" autocomplete="off" class="MB_inp" type="text">
							</div>
						</div>
						<div class="Se_pwd_group Out_group">
							<label>支付宝账户：</label>
							<div class="Se_pwd_inp">
								<input id="zfbNumber" name="zfbNumber" autocomplete="off" class="MB_inp" type="text">
							</div>
						</div>
						<div class="Se_pwd_group Out_group">
							<label>资金密码：</label>
							<div class="Se_pwd_inp">
								<input id="dealPwd" name="dealPwd" class="MB_inp" type="password">
							</div>
						</div>
						<div class="Se_pwd_group Out_group">
							<label>绑定手机：</label>
							<div class="Se_pwd_inp">
								<input id="mobile" name="mobile" class="MB_inp_Verify" type="text" value="${mobile}" readonly="readonly">
							</div>
						</div>
						<div class="Se_pwd_group Out_group">
							<label>短信验证码：</label>
							<div class="Se_pwd_inp">
								<input id="verifyCode" name="verifyCode" autocomplete="off" class="MB_inp_Verify" type="text">
								<input id="smss" type="button" class="Out_btns" value="获取验证码" />
							</div>
						</div>
						<span style="font-size: 14px; color: #9a2222; margin-left:100px; margin-bottom: 15px; display: block;">${msg}</span>
						<div class="Se_form_submit">
							<input id="MB_R" type="submit" value="提交">
							<input id="MB_R" onclick="checkDraw();" type="button" value="提交">
						</div>
					</div>
				</form>
			</div>
		</div>
		<jsp:include page="../common/bottom.jsp"></jsp:include>
	</body>
	
<!-- 获取短信验证码操作 -->
<script type="text/javascript">
var wait=120;// 120s等待
$(function(){
	$("#smss").click(function(){
		var mobile = $("#mobile").val();
		var password = $("#checkPassword").val();
		var code = $("#code").val();
		if($("#drawMoney").val()==''){
			alert("请先输入提现金额!");
			return false;
		}
		if($("#zfbNumber").val()==''){
			alert("请输入支付宝账号!");
			return false;
		}
		if($("#dealPwd").val()==''){
			alert("请输入资金密码!");
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
function checkDraw(){
	var drawMoney = $("#drawMoney").val();
	var money = $("#userMoney").val();
	if(money==0){
		alert("余额不足，不能体现!");
		return false;
	}
	if(drawMoney*1<100){
		alert("提现金额不能低于100元!");
		return false;
	}
	if(drawMoney*1>money*1){
		alert("提现金额不能大于账户余额!");
		return false;
	}
	if(drawMoney==''){
		alert("请输入提现金额!");
		return false;
	}
	if(isNaN(drawMoney)){
		alert("请输入正确的金钱格式!");
		return false;
	}
	if($("#zfbNumber").val()==''){
		alert("请输入支付宝账号!");
		return false;
	}
	if($("#dealPwd").val()==''){
		alert("请输入资金密码!");
		return false;
	}
	if($("#verifyCode").val()==''){
		alert("请输入短信验证码!");
		return false;
	}
	return true;
}
</script>
	
	
</html>