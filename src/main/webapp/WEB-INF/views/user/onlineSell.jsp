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
	});
</script>
<script type="text/javascript">
var error = "${error}";
if (error != "") {
//	alert("11111");
	//alert(decodeURIComponent(error)+"111");
}
</script>
<body>
	<jsp:include page="../common/nav.jsp"></jsp:include>
	<div class="Sa_mid">
		<jsp:include page="../common/userTradingHallLeft.jsp"></jsp:include>
		<div class="Sa_mid_right">
			<div class="On_title">
				<p class="Wal_mid_set">卖出MC</p>
			</div>
			<div class="OnSell_right">
				<p>
					可用<span style="color: #1CE4EF;">${money }MC</span>
				</p>
				<form method="post" action="user/trading/onlineSellProcess">
					<input type="text" placeholder="卖出数量" id="sellAmount"
						name="sellAmount" autocomplete="off" onchange="jisuan()" /> <input
						type="text" placeholder="卖出价格" id="sellPrice" name="sellPrice"
						autocomplete="off" /> <input type="password" placeholder="交易密码"
						id="dealPwd" name="dealPwd" autocomplete="off" /> <input
						class="On_email" type="text" placeholder="短信验证码" id="message"
						name="message" autocomplete="off" /> <input class="phone_email"
						id="smss" type="button" value="获取短信验证码" />
					<div class="On_dotted">
						<p>
							手续费<span class="On_sell_p shouxufei">0.0000MC</span>
						</p>
						<p>
							交易额 <span class="On_sell_p jiaoyie">0.00 MC</span>
						</p>
					</div>
					<input type="submit" value="卖出" class="On_Sell_btn" />
				</form>
			</div>
			<div class="On_mid">
				<p>当前卖单</p>
				<div class="On_list En_list">
					<table>
						<tr>
							<th>时间</th>
							<th>卖出量</th>
							<th>价格</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${lineOrders }" var="order" begin="0">
							<tr>
								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value="${order.sellCdt }" /></td>
								<td><c:out value="${order.sellCoin }" /></td>
								<td><c:out value="${order.price }" /></td>
								<td><a class="back" href="user/trading/onlineSellBack?id=${order.id }">撤回</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				
				<div class="Not_Page">
					<input type="hidden" id="pageNow" name="pageNow" value="1" />
					<ul style="margin-right: 0;margin-bottom: 1rem;">
						<c:if test="${totalPages>1 }">
							<li style="border-right: 1px solid #4d8db4;"><a id="last_id">《</a></li>
						</c:if>
						<c:forEach begin="1" var="i" end="${totalPages }">
							<li style="border-right: 1px solid #4d8db4;"><a id="${i}"
								class="indexbutton"><c:out value="${i}"></c:out></a></li>
						</c:forEach>
						<c:if test="${totalPages>1 }">
							<li style="border-right: 1px solid #4d8db4;"><a id="next_id">》</a></li>
						</c:if>
					</ul>
					<a id="dangqianye" style="left:0" >当前页 : 1</a>
				</div>
			</div>
		</div>
	</div>
	<div class="Acc_bot">
		<p>联系我们: 邮箱123456@freenet.com</p>
		<p>Copyright 2011 - 2016 Free net All rights reserved.</p>
	</div>
</body>
<!-- 获取验证码操作 -->
<script type="text/javascript">
var wait=120;// 120s等待
$(function(){
	$("#smss").click(function(){
		var amount = $("#sellAmount").val();
		var price = $("#sellPrice").val();
		var dealPwd = $("#dealPwd").val();
		if(amount==''){
			alert("请输入出售数量!");
			return false;
		}
		if(price==''){
			alert("请先输入出售价格!");
			return false;
		}
		if(dealPwd==''){
			alert("请先输入支付密码!");
			return false;
		}
		if(wait==120){
			time();
			$.ajax({
				cache:true,
				type:'GET',
				url:'user/smsVerifyCode',
				data:{mobile:${mobile}},
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
	var rate = ${rate};
	function jisuan() {
		//获得卖出量
		var money  = new Number($("#sellAmount").val());
		var assistMoney = new Number(money*rate);
		var sum= new Number(assistMoney+money);
		$(".shouxufei").text(assistMoney.toFixed(4)+"MC");
		$(".jiaoyie").text((sum).toFixed(4)+"MC");
	}
</script>
<script type="text/javascript">
	var totalpages = "${totalPages}";
	//点击上一页
	$("#last_id")
			.click(
					function() {
						var lastindex = $("#pageNow").val();
						var l_index = lastindex - 1;
						if (l_index > 0) {
							//ajax获取页面内容
							$
									.ajax({
										url : "user/trading/onlineSellByPage",
										type : "post",
										dataType : "html",
										contentType : "application/x-www-form-urlencoded; charset=utf-8",
										timeout : 6000,
										data : {
											"index" : l_index
										},
										//获取成功，为页面新添加元素初始化
										success : function(msg) {
											$(".En_list").empty();
											$(".En_list").append(msg);
											$("#pageNow").val(l_index);
											$("#dangqianye").text("当前页 : "+l_index);
										}
									})
						}
					})
	//点击下一页
	$("#next_id")
			.click(
					function() {
						var lastindex = $("#pageNow").val();
						var n_index = lastindex - 1 + 2;
						if (lastindex < totalpages) {
							//ajax获取页面内容
							$
									.ajax({
										url : "user/trading/onlineSellByPage",
										type : "post",
										dataType : "html",
										contentType : "application/x-www-form-urlencoded; charset=utf-8",
										timeout : 6000,
										data : {
											"index" : n_index
										},
										//获取成功，为页面新添加元素初始化
										success : function(msg) {
											$(".En_list").empty();
											$(".En_list").append(msg);
											$("#pageNow").val(n_index);
											$("#dangqianye").text("当前页 : "+n_index);
										}
									})
						}
					})
</script>
<script type="text/javascript">
	$(".indexbutton").click(function() {
		var lastindex = $("#pageNow").val();
		var index = $(this).attr("id");
		//ajax获取页面内容
		$.ajax({
			url : "user/trading/onlineSellByPage",
			type : "post",
			dataType : "html",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			timeout : 6000,
			data : {
				"index" : index
			},
			//获取成功，为页面新添加元素初始化
			success : function(msg) {
				$(".En_list").empty();
				$(".En_list").append(msg);
				$("#pageNow").val(index);
				$("#dangqianye").text("当前页 : "+index);
			}
		})
	})
</script>
</html>