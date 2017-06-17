<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<html lang="en">
<jsp:include page="../common/managerHead.jsp"></jsp:include>

<script src="assets/js/echarts.min.js"></script>
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
									<div class="widget-title am-fl">实名认证</div>
									<div class="widget-function am-fr">
										<a href="javascript:;" class="am-icon-cog"></a>
									</div>
								</div>
									
								<div class="widget-body am-fr">
									<div class="am-btn-toolbar">
										<div class="am-btn-group am-btn-group-xs">
											<a href="manager/user/Edit?userId=${obj.userId}" class="am-btn am-btn-default am-btn-success"> 会员信息</a>
										</div>
									</div>
									<c:if test="${auth!=null }">
										<div class="am-form tpl-form-border-form tpl-form-border-br">
											<div class="am-form-group">
												<label for="user-name" class="am-u-sm-3 am-form-label">认证状态:</label>
												<div class="am-u-sm-9">
													<c:if test="${obj.authStatus==1}">
														<span style="line-height: 35px;font-size: 14px;">已认证</span>
													</c:if>
													<c:if test="${obj.authStatus==3}">
														<span style="line-height: 35px;font-size: 14px;">待审核</span>
													</c:if>
													<c:if test="${obj.authStatus==4}">
														<span style="line-height: 35px;font-size: 14px;">未通过</span>
													</c:if>
												</div>
											</div>
											<div class="am-form-group">
												<label for="user-name" class="am-u-sm-3 am-form-label">姓名:</label>
												<div class="am-u-sm-9">
													<input style=" width: 31.1rem;" type="text" class="tpl-form-input" id="user-name" readonly="readonly" value="${auth.name}" />
												</div>
											</div>
											<div class="am-form-group">
												<label for="user-name" class="am-u-sm-3 am-form-label">性别:</label>
												<div class="am-u-sm-9">
													<c:if test="${auth.sex==1}">
														<input style=" width: 31.1rem;" type="text" class="tpl-form-input" id="user-name" readonly="readonly" value="男" />
													</c:if>
													<c:if test="${auth.sex==2}">
														<input style=" width: 31.1rem;" type="text" class="tpl-form-input" id="user-name" readonly="readonly" value="女" />
													</c:if>
												</div>
											</div>
											<div class="am-form-group">
												<label class="am-u-sm-3 am-form-label">证件类型:</label>
												<div class="am-u-sm-9">
													<c:if test="${auth.cardType==1 }">
														<input style=" width: 31.1rem;" type="text" class="tpl-form-input" readonly="readonly" value="居民身份证" />
													</c:if>
													<c:if test="${auth.cardType==2 }">
														<input style=" width: 31.1rem;" type="text" class="tpl-form-input" readonly="readonly" value="护照" />
													</c:if>
												</div>
											</div>
											<div class="am-form-group">
												<label class="am-u-sm-3 am-form-label">证件号码:</label>
												<div class="am-u-sm-9">
													<input style=" width: 31.1rem;" type="text" readonly="readonly" value="${auth.card }" />
												</div>
											</div>
											<div class="am-form-group">
												<label class="am-u-sm-3 am-form-label">证件正面照: </label>
												<div class="am-u-sm-9">
													<div class="tpl-form-file-img">
														<a href="${auth.photoFront }" target="_Blank" ><img style="width: 31.1rem;height: 17.2rem;" src="${auth.photoFront }" alt=""></a>
	                                                </div>
												</div>
											</div>
											<div class="am-form-group">
												<label class="am-u-sm-3 am-form-label">证件背面照: </label>
												<div class="am-u-sm-9">
													<div class="tpl-form-file-img">
													<a href="${auth.photoBack }" target="_Blank" ><img style="width: 31.1rem;height: 17.2rem;" src="${auth.photoBack }" alt=""></a>
	                                                </div>
												</div>
											</div>
											<div class="am-form-group">
												<label class="am-u-sm-3 am-form-label">手持证件银行卡合照: </label>
												<div class="am-u-sm-9">
													<div class="tpl-form-file-img">
													<a href="${auth.photoAll }" target="_Blank"><img style="width: 31.1rem;height: 17.2rem;" src="${auth.photoAll }" alt=""></a>
	                                                </div>
												</div>
											</div>
											<div class="am-form-group">
												<label class="am-u-sm-3 am-form-label">认证时间: </label>
												<div class="am-u-sm-9">
													<span style="line-height: 35px; font-size: 14px;">
														<fmt:formatDate value="${auth.cdt}" pattern="yyyy-MM-dd HH:mm"/>
													</span>
												</div>
											</div>
											
											<c:if test="${obj.authStatus==3 }">
												<div class="am-form-group">
													<div class="am-u-sm-9 am-u-sm-push-3">
														<a href="manager/user/authAgree?userId=${obj.userId}" class="am-btn am-btn-primary tpl-btn-bg-color-success ">通过</a>
														<a href="manager/user/authPass?userId=${obj.userId}" class="am-btn am-btn-danger tpl-btn-bg-color-success ">不通过</a>
													</div>
												</div>
											</c:if>
										</div>
									</c:if>
									<c:if test="${auth==null }">
										<p style="text-align: center;">此用户未实名认证</p>
									</c:if>
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