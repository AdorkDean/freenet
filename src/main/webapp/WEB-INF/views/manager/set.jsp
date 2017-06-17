<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
								<div class="widget-title am-fl">网站设置</div>
								<div class="widget-function am-fr">
									<a href="javascript:;" class="am-icon-cog"></a>
								</div>
							</div>
							<div class="widget-body am-fr">
								<form id="submitForm"
									class="am-form tpl-form-border-form tpl-form-border-br"
									method="POST" action="manager/web/updateinfo">
									<div class="am-form-group">
										<label for="user-name" class="am-u-sm-3 am-form-label">网站名称
										</label>
										<div class="am-u-sm-9">
											<input name="name" type="text" class="tpl-form-input"
												id="user-name" placeholder="请输入网站名称" value="${webinfo.name}" />
											<small>请填写网站名称。</small>
										</div>
									</div>
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">网站邮箱</label>
										<div class="am-u-sm-9">
											<input name="email" type="text" placeholder="输入网站关键字"
												value="${webinfo.email}">
										</div>
									</div>
									<div class="am-form-group">
										<label for="user-intro" class="am-u-sm-3 am-form-label">网站描述</label>
										<div class="am-u-sm-9">
											<textarea name="descrip" class="" rows="10" id="user-intro"
												placeholder="请输入网站描述"><c:out
													value="${webinfo.descrip}" /></textarea>
										</div>
									</div>
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">网站域名 </label>
										<div class="am-u-sm-9">
											<input name="website" type="text" placeholder="输入网站域名"
												value="${webinfo.website}">
										</div>
									</div>
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">网站版权 </label>
										<div class="am-u-sm-9">
											<input name="copyright" type="text" placeholder="输入网站版权"
												value="${webinfo.copyright}">
										</div>
									</div>
									<div class="am-form-group">
										<div class="am-u-sm-9 am-u-sm-push-3">
											<input id="submitButton" type="submit"
												class=" am-btn am-btn-primary tpl-btn-bg-color-success "></input>
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
	<script>
		var flag = "${flag}";
		if (flag == 1) {
			alert("修改成功");
		}
		$(document).ready(function() {
			$("#submitButton").click(function() {
				$("form").submit(function() {

				});
			});
		});
	</script>
</body>

</html>