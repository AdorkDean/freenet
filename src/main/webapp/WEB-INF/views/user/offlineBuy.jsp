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
		});
		$(".Sa_mid_btns").click(function() {
			$(".Sa_mid_listl").toggle(200);
		});
		
	});
</script>
<body>
	<div id="Mask" class="MB_Rmask"></div>
	<jsp:include page="../common/nav.jsp"></jsp:include>
	<div class="Sa_mid">
		<jsp:include page="../common/userTradingHallLeft.jsp"></jsp:include>
		<div class="Sa_mid_right">
			<p class="Wal_mid_set">MC(CNY)</p>
			<p class="Pur_tit">当前卖单</p>
			<div class="Wal_Sales">
				<table class="Pur_tab">
					<tr>
						<th>时间</th>
						<th>价格</th>
						<th>卖出量</th>
						<!-- <th>剩余数量</th> -->
						<th>操作</th>
					</tr>
					<c:forEach var="obj" items="${list }" varStatus="status">
						<tr>
							<td><fmt:formatDate value="${obj.sellCdt}" pattern="yyyy-MM-dd HH:mm" /></td>
							<td>${obj.price }</td>
							<td>${obj.sellCoin }</td>
							<!-- <td>100.000</td> -->
							<td><a onclick="showNumeber(${obj.sellUserId},${obj.id });" >购买</a></td>
						</tr>
					</c:forEach>
				</table>
				<div class="Not_Page">
					<ul style="margin-bottom:2rem;margin-right:0;">
							<c:if test="${page.pageNow - 1 > 0}">
								<li class="liPager"><a href="${url}${page.pageNow-1 }" class="liPager" id="last_id">《</a></li>
							</c:if>
							<c:forEach var="obj" begin="1" end="${page.totalPageCount}" step="1">
								<c:if test="${obj==page.pageNow }">
									<c:if test="${page.pageNow-1>0 }">
										<li id="li${page.pageNow-1 }" class="liPager"><a href="${url}${page.pageNow-1 }" id="a${page.pageNow-1 }" class="indexbutton">${page.pageNow-1 }</a></li>
									</c:if>
									<li id="li${page.pageNow }" class="liPager"><a href="${url}${page.pageNow }" id="a${page.pageNow }" class="indexbutton">${page.pageNow }</a></li>
									<c:if test="${page.pageNow+1<=page.totalPageCount }">
										<li id="li${page.pageNow+1}" class="liPager"><a href="${url}${page.pageNow+1}" id="a${page.pageNow+1}" class="indexbutton">${page.pageNow+1 }</a></li>
									</c:if>
								</c:if>
							</c:forEach>
							<c:if test="${page.pageNow < page.totalPageCount}">
								<li class="liPager"><a href="${url}${page.pageNow+1 }" id="next_id">》</a></li>
							</c:if>
						</ul>
					</div>
			</div>
			<div class="Sa_mid_rights">
				<table class="Pur_table">
					<tr>
						<th>开盘价</th>
						<td>54.0000</td>
					</tr>
					<tr>
						<th>收盘价</th>
						<td>65.0000</td>
					</tr>
					<tr>
						<th>成交价</th>
						<td>65.0000</td>
					</tr>
					<tr>
						<th>成交量</th>
						<td>396246</td>
					</tr>
				</table>
			</div>
		</div>
		
	</div>
	<div id="racePop">
		<div class="MB_Rport">
			<p>提示</p>
		</div>
		<div class="Mb_Rport_text">请您通过支付宝转账到 账号  : <span style="color:black;" id="zfbNumber"></span>  ,
		请认真核对账号信息，转账成功后,请保留转账记录,回到平台进行下一步操作</div>
		<div class="MB_btn">
			<input class="MB_Rech_btn  haveMoney" id="mustBuy" type="submit" value="确认购买" />
			<input type="hidden" value="" id="sureBuy"/>
			<input id="hideDiv" class="MB_Rech_btn" type="submit" value="取消" />
		</div>
	</div>
	<jsp:include page="../common/bottom.jsp"></jsp:include>
</body>
<script>
$(function(){
	var page = "${page.pageNow}";
	$("#li"+page).attr("style","border-right:1px solid #4d8db4; background: white;");
	$("#a"+page).attr("style","color: #0b6499;");
});

</script>
<script type="text/javascript">
var speed = 600;//弹出显示动画
function showNumeber(sellUserId,offId){
	$.ajax({
		url:'user/offline/showPay',
		type:'POST',
		data:{sellUserId:sellUserId},
		async:false,
		success:function(data){
			if(data.result==true){
				var zfbNumber = data.obj.zfbNumber;
				$("#zfbNumber").text(zfbNumber);
				$("#sureBuy").val(offId);
				//event.stopPropagation();//停止事件的传播,阻止它被分派到其他 Document 节点。
				$("#racePop").show(speed); //动画显示
				$("#Mask").show(speed);
			}
		},
		error:function(){
			alert("网络繁忙，请稍后再试!")
		}
	});
}
$("#mustBuy").click(function(){
	var offId = $("#sureBuy").val();
	$.ajax({
		url:'user/offline/sureBuy',
		type:'GET',
		data:{offId:offId},
		async:false,
		success:function(data){
			if(data.result){
				window.location.href="user/trading/offlineBuyRecord";
			}else{
				alert(data.msg);
			}
		},
		error:function(){
			alert("网络繁忙，请稍后再试!");
		}
	});
});
$("#hideDiv").click(function(event) {
	$("#racePop").hide(speed); //动画显示
	$("#Mask").hide(speed);
});
</script>
</html>