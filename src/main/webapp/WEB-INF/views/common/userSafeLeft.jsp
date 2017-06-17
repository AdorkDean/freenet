<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="Sa_mid_left">
	<p class="Sa_mid_font">安全中心</p>
	<ul class="Sa_mid_list">
		<li class="Sa_mid_item">
			<a id="Sa_mid_btn" class="Sa_mid_btn"  href="javascript:;">安全中心
			<i id="N_down" class="Ndown_icon"></i></a>
			<ul class="Sa_mid_lists">
				<li class="Sa_mid_listitem">
					<a id="safeCenter" href="user/safe/safeCenter">安全设置</a>
				</li>
				<li>
					<a id="toLoginHistroy" href="user/safe/toLoginHistroy">安全记录</a>
				</li>
			</ul>
		</li>
		<li class="Sa_mid_item">
			<a id="Sa_mid_btns" class="Sa_mid_btns" href="javascript:;">用户中心
				<i id="R_down" class="R_icon"></i>
			</a>
			<ul class="Sa_mid_listl" style="display: none;">
				<li>
					<a id="toAuth" href="user/safe/toAuth">实名认证</a>
				</li>
				<li>
					<a id="content" href="user/safe/content">我的消息</a>
				</li>
			</ul>
		</li>
	</ul>
</div>



<script>
var name="${name}";

if(name=='safeCenter'){
	$("#safeCenter").attr("class","Sa_active");
	$("#toAuth").attr("class","");
	$("#toLoginHistroy").attr("class","");
	
}else if(name=='user_toAuth'){
	$("#toAuth").attr("class","Sa_active");
	$("#safeCenter").attr("class","");
	$("#content").attr("class","");
	$(".Sa_mid_listl").attr("style","display:block");
	$(".Sa_mid_lists").attr("style","display:none");
	$("#toLoginHistroy").attr("class","");
}else if(name=='toLoginHistroy'){
	$("#toLoginHistroy").attr("class","Sa_active");
	$("#toAuth").attr("class","");
	$("#safeCenter").attr("class","");
}else if(name=="content"){
	$("#content").attr("class","Sa_active");
	$("#toAuth").attr("class","");
	$("#safeCenter").attr("class","");
	$("#toLoginHistroy").attr("class","");
	$(".Sa_mid_listl").attr("style","display:block");
	$(".Sa_mid_lists").attr("style","display:none");
}
</script>