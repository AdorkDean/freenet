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
									<div class="widget-title am-fl">会员信息</div>
									<div class="widget-function am-fr">
										<a href="javascript:;" class="am-icon-cog"></a>
									</div>
								</div>
								<div class="widget-body am-fr">
									<div class="am-btn-toolbar">
										<div class="am-btn-group am-btn-group-xs">
											<a href="manager/user/toAuth?userId=${obj.userId }" class="am-btn am-btn-default am-btn-success"> 实名认证</a>
										</div>
									</div>
									<div class="am-form tpl-form-border-form tpl-form-border-br">

										<div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">账号 :</label>
											<div class="am-u-sm-9">
												<input style="width: 35%;" type="text" class="tpl-form-input" id="user-name" readonly="readonly" value="${obj.username}" />
											</div>
										</div>
										<div class="am-form-group">
											<label class="am-u-sm-3 am-form-label">状态 :</label>
											<c:if test="${obj.status==1 }">
												<div class="am-u-sm-9">
													<span style="line-height: 35px; font-size: 14px;">正常</span>
												</div>
											</c:if>
											<c:if test="${obj.status==2 }">
												<div class="am-u-sm-9">
													<span style="line-height: 35px; font-size: 14px;">已冻结</span>
												</div>
											</c:if>
										</div>
										<div class="am-form-group">
											<label class="am-u-sm-3 am-form-label">邮箱 :</label>
											<div class="am-u-sm-9">
												<input style="width: 35%;" type="text" readonly="readonly" value="${obj.email}" />
											</div>
										</div>
										<div class="am-form-group">
											<label class="am-u-sm-3 am-form-label">注册日期 :</label>
											<div class="am-u-sm-9">
												<span style="line-height: 35px; font-size: 14px;"><fmt:formatDate value="${obj.cdt}" pattern="yyyy-MM-dd HH:mm"/></span>
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

</html>