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
									<div class="widget-title am-fl">新增货币</div>
									<div class="widget-function am-fr">
										<a href="javascript:;" class="am-icon-cog"></a>
									</div>
								</div>
								<div class="widget-body am-fr">
									<form method="POST" action="manager/coin/typeSave" enctype="multipart/form-data" onsubmit="return checkText();" class="am-form tpl-form-border-form tpl-form-border-br">
										<div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">货币名称 </label>
											<div class="am-u-sm-9">
												<input style="width:28%;" type="text" class="tpl-form-input" id="coinName" name="coinName" autocomplete="off" placeholder="请输入货币名称">
											</div>
										</div>
										<div class="am-form-group">
											<label class="am-u-sm-3 am-form-label">手续费 </label>
											<div class="am-u-sm-9">
												<input style="width:28%;" id="coinRate" name="coinRate" type="text" autocomplete="off" placeholder="请输入手续费">
											</div>
										</div>
										<div class="am-form-group">
											<div class="am-u-sm-9 am-u-sm-push-3">
												<button type="submit" class="am-btn am-btn-primary tpl-btn-bg-color-success ">提交</button>
											</div>
										</div>
									</form>
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
		function checkText(){
			if($("#coinName").val()==''){
				alert("请输入货币名称!");
				return false;
			}
			if($("#coinRate").val()==''){
				alert("请输入手续费用!");
				return false;
			}
			return true;
		}
	</script>
</html>