<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<html>
<jsp:include page="../common/userHead.jsp"></jsp:include>
<script type="text/javascript" src="./resources/user/js/City.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$(".Sa_mid_btn").click(function() {
			$(".Sa_mid_lists").toggle(200);
			if($(".N_icon").attr("id") == "N_up") {
				$(".N_icon").attr({
					id: "N_down"
				});
				$("#N_down").addClass("Ndown_icon");
				$("#N_down").removeClass("N_icon");
			} else {
				$(".Ndown_icon").attr({
					id: "N_up"
				});
				$("#N_up").addClass("N_icon");
				$("#N_up").removeClass("Ndown_icon");
			}
		});
		$(".Sa_mid_btns").click(function() {
			$(".Sa_mid_listl").toggle(200);
			if($(".Rdown_icon").attr("id") == "R_up") {
				$(".Rdown_icon").attr({
					id: "R_down"
				});
				$("#R_down").addClass(" R_icon");
				$("#R_down").removeClass(" Rdown_icon");
			} else {
				$(".R_icon").attr({
					id: "R_up"
				});
				$("#R_up").addClass(" Rdown_icon");
				$("#R_up").removeClass("R_icon");
			}
		});
	});
</script>

	<body>
		<jsp:include page="../common/nav.jsp"></jsp:include>
		
		<div class="Sa_mid">
			<div class="Real_mid_left">
				<div class="Sa_mid_white"></div>
				<div class="Sa_mid_triangle"></div>
				<p class="Sa_mid_font">财务中心</p>
				<ul class="Sa_mid_list">
					<li class="Sa_mid_item">
						<a class="Sa_mid_btn F_size" href="javascript:;">我的钱包
							<i id="N_up" class="N_icon"></i></a>
						<ul class="Sa_mid_lists" style="display: none;">
							<c:forEach var="List" items="${list }" varStatus="status">
								<li class="Sa_mid_listitem">
									<a class="Sa_active" href="user/finance/coin?Type=${List.coinId}">${List.coinName}钱包</a>
								</li>
							</c:forEach>
						</ul>
					</li>
					<li class="Sa_mid_item">
						<a class="Sa_mid_btns F_size" style="color: #1ce4ef;" href="javascript:;">出金入金
							<i id="R_up" class="Rdown_icon"></i></a>
						<ul class="Sa_mid_listl">
							<li>
								<a class="F_size" href="#">MB出金/入金</a>
							</li>
							<li>
								<a class="Sa_active F_size" href="user/finance/bank">资金账号管理</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
			<div class="Sa_mid_right">
				<form action="user/finance/bankSave" method="POST" onsubmit="return checkbank();" enctype="multipart/form-data">
					<div class="Add_form_group">
						<div class="Se_pwd_group">
							<label>姓名：</label>
							<div class="Se_pwd_inp">
								<input type="text" name="userName" value="${obj.name}" readonly="readonly"/>
							</div>
						</div>
						<div class="Se_pwd_group">
							<label>支付宝账号：</label>
							<div class="Se_pwd_inp">
								<input id="zfbNumber" name="zfbNumber" type="text" />
							</div>
						</div>
						<div class="Se_pwd_group">
							<label>手机号：</label>
							<div class="Se_pwd_inp">
								<input id="phone" name="phone" type="text" />
							</div>
						</div>
						<!-- <div class="Se_pwd_group">
							<label>开户银行：</label>
							<div class="Se_pwd_inp">
								<select id="bankType" name="bankType">
									<option value="0">请选择</option>
									<option value="工商银行(ICBC)">工商银行(ICBC)</option>
									<option value="建设银行(CBC)">建设银行(CBC)</option>
									<option value="中国银行(BOC)">中国银行(BOC)</option>
									<option value="交通银行(BCM)">交通银行(BCM)</option>
									<option value="农业银行(ABC)">农业银行(ABC)</option>
									<option value="招商银行(CMB)">招商银行(CMB)</option>
									<option value="邮政储蓄银行(PSBC)">邮政储蓄银行(PSBC)</option>
									<option value="光大银行(CEB)">光大银行(CEB)</option>
									<option value="北京银行(BOB)">北京银行(BOB)</option>
								</select>
							</div>
						</div>
						<div class="Se_pwd_group">
							<label>开户支行：</label>
							<div class="Se_pwd_inp">
								<input id="bankName" name="bankName" type="text" />
							</div>
						</div> -->
						<div class="Se_pwd_group">
							<label>所属地区：</label>
							<div class="Se_pwd_inp">
								<select id="province" name="province" onchange="getcity()">
									<option value="-1">选择省份</option>
									<option value="北京市">北京市 </option>
									<option value="广东省">广东省 </option>
									<option value="上海市">上海市 </option>
									<option value="天津市">天津市 </option>
									<option value="重庆市">重庆市 </option>
									<option value="辽宁省">辽宁省</option>
									<option value="江苏省">江苏省</option>
									<option value="湖北省">湖北省</option>
									<option value="四川省">四川省</option>
									<option value="陕西省">陕西省</option>
									<option value="河北省">河北省</option>
									<option value="山西省">山西省</option>
									<option value="河南省">河南省</option>
									<option value="吉林省">吉林省</option>
									<option value="黑龙江">黑龙江</option>
									<option value="内蒙古">内蒙古</option>
									<option value="山东省">山东省</option>
									<option value="安徽省">安徽省</option>
									<option value="浙江省">浙江省</option>
									<option value="福建省">福建省</option>
									<option value="湖南省">湖南省</option>
									<option value="广西省">广西省</option>
									<option value="江西省">江西省</option>
									<option value="贵州省">贵州省</option>
									<option value="云南省">云南省</option>
									<option value="西藏">西藏</option>
									<option value="海南省">海南省</option>
									<option value="甘肃省">甘肃省</option>
									<option value="宁夏">宁夏</option>
									<option value="青海省">青海省</option>
									<option value="新疆">新疆</option>
									<option value="香港">香港</option>
									<option value="澳门">澳门</option>
									<option value="台湾">台湾</option>
									<option value="海外">海外</option>
									<option value="其他">其他</option>
							</select>
							</div>
						</div>
						<div class="Se_pwd_group">
							<div class="Se_pwd_inp">
								<select id="city" name="city">
									<option value="-1">市</option>
								</select>
							</div>
						</div>
						<div class="Se_pwd_group">
							<label>交易密码：</label>
							<div class="Se_pwd_inp">
								<input id="dealPwd" name="dealPwd" type="password" />
								<c:if test="${msg!=null }">
									<p style="color:#a81b11;" class="Mg_trade">${msg }</p>
								</c:if>
							</div>
						</div>
						<div class="Se_form_submit">
							<input type="submit" value="提交" /> 
						</div>
						
					</div>
				</form>
			</div>
		</div>
		<jsp:include page="../common/bottom.jsp"></jsp:include>
	</body>
	
<script type="text/javascript">
	function checkbank(){
		var zfbNumber = $("#zfbNumber").val();
		var mobile = $("#phone").val();
		var bankName = $("#bankName").val();
		var dealPwd = $("#dealPwd").val();
		if(zfbNumber==''){
			alert("请填写支付宝账号!");
			return false;
		}
		if(mobile==''){
			alert("请填写手机号!");
			return false;
		}
		if(!verifyPhone(mobile)){
			alert("手机格式不正确!");
			return false;
		}
		/* if($("#bankType option:selected").val()==0){
			alert("请选择开户银行!");
			return false;
		} */
		/* if(bankName==''){
			alert("请填写开户支行!");
			return false;
		} */
		if($("#province option:selected").val()==-1){
			alert("请选择省份!");
			return false;
		}
		if($("#city option:selected").val()==-1){
			alert("请选择市区!");
			return false;
		}
		if(dealPwd==''){
			alert("请填写交易密码!");
			return false;
		}
		return true;
	}
</script>

</html>