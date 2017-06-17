<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<html lang="en">
<jsp:include page="../common/managerHead.jsp"></jsp:include>
<script src="./resources/manager/js/echarts.min.js"></script>
<link rel="stylesheet" href="./resources/manager/css/amazeui.min.css" />
<link rel="stylesheet" href="./resources/manager/css/amazeui.datatables.min.css" />
<link rel="stylesheet" href="./resources/manager/css/app.css">
<script src="./resources/manager/js/jquery.min.js"></script>

	<body data-type="widgets">
		<script src="./resources/manager/js/theme.js"></script>
		<div class="am-g tpl-g">
			<!-- 头部 -->
			<jsp:include page="../common/managerNav.jsp"></jsp:include>
			<!-- 侧边导航栏 -->
			<jsp:include page="../common/managerLeft.jsp"></jsp:include>
			<!-- 内容区域 -->
			<div class="tpl-content-wrapper">
				<div class="row-content am-cf">
					<div class="row">
						<div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
							<div class="widget am-cf">
								<div class="widget-head am-cf">
									<div class="widget-title am-fl">收款配置</div>
									<div class="widget-function am-fr">
										<a href="javascript:;" class="am-icon-cog"></a>
									</div>
								</div>
								<div class="widget-body am-fr">
									<div class="am-form tpl-form-border-form tpl-form-border-br" style="clear: both;">
										<div class="am-form-group" style="padding-left: 10%;">
											<label style="width: 17%;" class="am-u-sm-3 am-form-label">支付宝账号 </label>
											<div class="am-u-sm-9" style="float: left;">
												<input id="payId" type="hidden" value="${obj.id }"/>
												<input id="payZfb" type="text" autocomplete="off" value="${obj.payZfb }" style="width: 45%;float: left" placeholder="请输入卖家账号">
												<span id="retMsg" style="color:red;margin-left: 1%;"></span>
											</div>
										</div>
										<div class="am-form-group" style="padding-left: 10%;">
											<div class="am-u-sm-9 am-u-sm-push-3">
												<button id="but" type="button" onclick="AllocationSave();" class="am-btn am-btn-primary tpl-btn-bg-color-success ">提交</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script src="./resources/manager/js/amazeui.min.js"></script>
		<script src="./resources/manager/js/amazeui.datatables.min.js"></script>
		<script src="./resources/manager/js/dataTables.responsive.min.js"></script>
		<script src="./resources/manager/js/app.js"></script>
	</body>

<script type="text/javascript">
$(function(){
	var all = '${obj}';
	if(all!=''){
		$("#but").text("修改");
	}
});

function AllocationSave(){
	var payId = $("#payId").val();
	if(payId==''){
		payId=0;
	}
	var payZfb = $("#payZfb").val();
	if(payZfb==''){
		$("#retMsg").text("请输入收款账号!");
		return false;
	}
	$.ajax({
		url:'manager/payAllocation/save',
		type:'POST',
		data:{payId:payId,payZfb:payZfb},
		dataType:'json',
		success:function(data){
			alert(data.msg);
			setTimeout("location.reload()",500);//页面刷新
		},
		error:function(){
			alert("网络繁忙，请稍后再试！");
		}
	});

}

</script>

</html>