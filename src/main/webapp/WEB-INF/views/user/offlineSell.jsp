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
<body>
	<jsp:include page="../common/nav.jsp"></jsp:include>
	<div class="Sa_mid">
		<jsp:include page="../common/userTradingHallLeft.jsp"></jsp:include>
		<div class="Sa_mid_right">
			<p class="Wal_mid_set">MC(CNY)</p>
			<div class="Sel_Detailed">
				<div id="main" style="width: 100%; height: 400px;"></div>
<script type="text/javascript">
				
$(function(){
	var data0 =[];
	$.ajax({
		url : "user/trading/getTableDataByAjax",
		type : "post",
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		timeout : 6000,
		data : {},
		async:false,
		//获取成功，为页面新添加元素初始化
		success : function(result) {
			var retData = result.result;
			var arr = new Array();
			for(var j=0;j<retData.length;j++){
				var brr = new Array();
				var time = retData[j].time;
				brr[0]=time;
				brr[1]=retData[j].minVal*1;
				brr[2]=retData[j].maxVal*1;
				console.log(brr);
				arr.push(brr);
			}
			data0  =  splitData(arr);
		
		}	
	});
	
	// 基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('main'));
	// 指定图表的配置项和数据
	var option = {
              backgroundColor: 'white', //背景色
              //缩放
              dataZoom: [{
                type: 'silder', //支持单独的滑动条缩放
                start: 10, //默认数据初始缩放范围为10%到90%
                end: 100,
              }],
              // 图表边距
              grid: {
                x: 50,
                y: 40,
                x2: 80,
                y2: 35
              },
              tooltip: {
                trigger: 'axis',
                axisPointer: {
                  type: 'cross'
                }
              },
              xAxis: {
                type: 'category',
                data: data0.categoryData,
                scale: true,
                axisLine: {
                  onZero: false
                },
                splitLine: {
                  show: false
                },
                splitNumber: 20,
                min: 'dataMin',
                max: 'dataMax'
              },
              yAxis: {
                scale: false,
                splitArea: {
                  show: false
                }
              },
              dataZoom: [{
                type: 'inside',
                start: 50,
                end: 100
              }],
              series: [{
                  type: 'candlestick',
                  data: data0.values,
                  markLine: {
                    symbol: ['none', 'none']
                  }
                }, 

              ]
            };
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
});	
				
function splitData(rawData) {
    var categoryData = [];
    var values = []
    for(var i = 0; i < rawData.length; i++) {
      categoryData.push(rawData[i].splice(0, 1)[0]);
      values.push(rawData[i])
    }
    return {
      categoryData: categoryData,
      values: values
    };
  }
  function calculateMA(dayCount) {
    var result = [];
    for(var i = 0, len = data0.values.length; i < len; i++) {
      if(i < dayCount) {
        result.push('-');
        continue;
      }
      var sum = 0;
      for(var j = 0; j < dayCount; j++) {
        sum += data0.values[i - j][1];
        
      }
      result.push(sum / dayCount);
    }
    console.log(result);
    return result;
  }
</script>

			</div>
			<div class="Sel_text">
				<div class="Sel_context">
					<p>温馨提示:</p>
					<p>1、请使用支付宝进行交易，使用非支付宝收汇款导致发生交易纠纷的用户需承担此次交易责任（责任包含抵押币和信用度）</p>
					<p>
						2、交易平台为了提供给广大用户公正的交易环境，抵制恶性交易
					</p>
				</div>
			</div>
			<div class="OnSell_right">
				<p>
					可用 <span style="color: #1CE4EF;"><c:out value="${obj.coin }"></c:out>
						MC</span> <input type="hidden" value="${obj.coin }" id="coin" />
				</p>
				<input type="hidden" id="mobile" value="${user.username }" /> <input
					type="hidden" id="userId" value="${user.userId }" /> <input
					id="price" name="price" type="text" placeholder="卖出价格 RMB"
					autocomplete="off" /> <input id="sellCoin" name="sellCoin"
					type="text" placeholder="卖出数量 MC" autocomplete="off"> <input
					id="zfbNumber" name="zfbNumber" class="On_email"
					style="float: left;" value="${bank.zfbNumber }" type="text"
					placeholder="收款账号" autocomplete="off" readonly="readonly">
				<input type="button" onclick="addBank();" value="添加"
					class="phone_email" /> <input id="verifyCode" name="verifyCode"
					class="On_email" style="float: left;" placeholder="短信验证码"
					autocomplete="off" type="text"> <input id="smss"
					type="button" value="获取短信验证码" class="phone_email" /> <input
					id="dealPwd" name="dealPwd" type="password" placeholder="交易密码"
					autocomplete="off">
				<div class="On_dotted">
					<p>
						抵押币<span class="On_sell_p"><c:out value="${coinCount }"></c:out>
							MC</span>
					</p>
					<input type="hidden" id="coinCount" value="${coinCount }" />
					<p>
						交易额 <span class="On_sell_p"><span id="finalMc">0.0000</span>
							MC</span>
					</p>
				</div>
				<input type="button" onclick="checkSell();" value="卖出"
					class="On_Sell_btn">
			</div>
			<div class="On_mid">
				<p>
					我的卖出 (最近7单) <a
						style="float: right; margin-right: 2%; color: white;"
						href="user/trading/offlineEntrust">全部 >></a>
				</p>
				<div class="On_list En_list">
					<table>
						<tbody>
							<tr>
								<th>时间</th>
								<th>卖出量</th>
								<th>价格</th>
								<th>抵押币</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
							<c:forEach var="offOrder" begin="0" end="6" items="${list}"
								varStatus="status">
								<tr>
									<td><fmt:formatDate value="${offOrder.sellCdt}"
											pattern="yyyy-MM-dd HH:mm" /></td>
									<td>${offOrder.sellCoin }</td>
									<td>${offOrder.price }</td>
									<td>${coinCount}</td>
									<td><c:if test="${offOrder.status==0 }">挂单中</c:if> <c:if
											test="${offOrder.status==6 }">已撤单</c:if></td>
									<td><c:if test="${offOrder.status==0 }">
											<a onClick="return confirm('确定要撤单吗?');" href="user/offline/kill?id=${offOrder.id }">撤单</a>
										</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../common/bottom.jsp"></jsp:include>
</body>

<script type="text/javascript">
$("#sellCoin").blur(function(){
	var thisCoin = $("#sellCoin").val()*1;
	var thidCoinCount = '${coinCount}';
	if(isNaN(thisCoin)){
		alert("请输入正确的货币格式!");
		return false;
	}
	$("#finalMc").text((thisCoin+thidCoinCount*1).toFixed(4));
});
</script>

<script type="text/javascript">
function checkSell(){
	var userId = $("#userId").val();
	var mobile = $("#mobile").val();
	var price = $("#price").val();
	var sellCoin = $("#sellCoin").val();
	var zfbNumber = $("#zfbNumber").val();
	var verifyCode = $("#verifyCode").val();
	var dealPwd = $("#dealPwd").val();
	var coin = $("#coin").val();
	var finalCoin = $("#finalMc").text();
	if(price==''){
		alert("请输入卖出的价格!");
		return false;
	}
	if(isNaN(price)){
		alert("请输入正确的金钱格式!");
		return false;
	}
	if(sellCoin==''){
		alert("请输入卖出的货币数量!");
		return false;
	}
	if(isNaN(sellCoin)){
		alert("请输入正确货币格式!");
		return false;
	}
	if(sellCoin*1>coin*1){
		alert("您的货币不足!");
		return false;
	}
	if(finalCoin*1>coin*1){
		alert("您的交易额超出了您拥有的货币数量!");
		return false;
	}
	if(zfbNumber==''){
		alert("请先添加收款账号!");
		return false;
	}
	if(verifyCode==''){
		alert("请输入短信验证码!");
		return false;
	}
	if(dealPwd==''){
		alert("请输入资金密码!");
		return false;
	}
	
	$.ajax({
		url:'user/offOrder/sell',
		type:'POST',
		data:{userId:userId,mobile:mobile,sellCoin:sellCoin,price:price,finalCoin:finalCoin,verifyCode:verifyCode,dealPwd:dealPwd},
		dataType:'json',
		async:false,
		success:function(data){
			if(data.result){
				alert(data.msg);
				window.location.href="user/trading/offlineEntrust";
			}else{
				alert(data.msg);
			}
		},
		error:function(){
			alert("网络繁忙，请稍后再试!");
		}
	});
}
	
</script>
<script type="text/javascript">
function addBank(){
	window.location.href="user/finance/bank";
}
</script>

<!-- 获取验证码操作 -->
<script type="text/javascript">
var wait=120;// 120s等待
$(function(){
	$("#smss").click(function(){
		var mobile = $("#mobile").val();
		var price = $("#price").val();
		var sellCoin = $("#sellCoin").val();
		var zfbNumber = $("#zfbNumber").val();
		if(price==''){
			alert("请先输入卖出的价格!");
			return false;
		}
		if(sellCoin==''){
			alert("请先输入卖出的货币量!");
			return false;
		}
		if(zfbNumber==''){
			alert("请先添加收款账号!");
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