<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="Real_mid_left">
	<p class="Sa_mid_font">交易中心</p>
	<ul class="Sa_mid_list">
		<li class="Sa_mid_item"><a>Free net</a></li>
		<li class="Sa_mid_item"><a class="Sa_mid_btn" href="javascript:;"> 线下交易<i id="N_down" class="Ndown_icon"></i></a>
			<ul class="Sa_mid_lists"  style="display: none;">
				<li class="Sa_mid_listitem"><a href="user/trading/offlineSell" id="offlineSell" >卖出</a></li>
				<li class="Sa_mid_listitem  "><a href="user/trading/offlineBuy" id="offlineBuy">买入</a></li>
				<li class="Sa_mid_listitem "><a href="user/trading/offlineEntrust" id="offlineEntrust">委托管理</a></li>
				<li class="Sa_mid_listitem "><a href="user/trading/offlineSellRecord" id="offlineSellRecord">卖出记录</a></li>
				<li class="Sa_mid_listitem "><a href="user/trading/offlineBuyRecord" id="offlineBuyRecord">买入记录</a></li>
				<li class="Sa_mid_listitem"><a href="#">简介</a></li>
			</ul></li>
		<li class="Sa_mid_item"><a class="Sa_mid_btns"
			href="javascript:;"> 线上交易<i id="R_down" class="R_icon"></i>
		</a>
			<ul class="Sa_mid_listl" style="display: none;">
				<li ><a href="user/trading/onlineSell" id="onlineSell">卖出</a></li>
				<li ><a href="user/trading/onlineBuy" id="onlineBuy">买入</a></li>
				<li ><a href="user/trading/onlineSellRecord" id="onlineSellRecord">卖出记录</a></li>
				<li><a href="user/trading/onlineBuyRecord" id="onlineBuyRecord">买入记录</a></li>
				<li ><a href="#">简介</a></li>
			</ul></li>
	</ul>
</div>
<script type="text/javascript">
var type = "${type}";
$("#${type}").parent().parent().attr("style","display: block;");
$("#${type}").attr("class","Sa_active");

</script>
</html>