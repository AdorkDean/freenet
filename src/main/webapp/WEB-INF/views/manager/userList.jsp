<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html lang="en">
<jsp:include page="../common/managerHead.jsp"></jsp:include>
<script src="./resources/manager/js/echarts.min.js"></script>
<link rel="stylesheet" href="./resources/manager/css/amazeui.min.css" />
<link rel="stylesheet"
	href="./resources/manager/css/amazeui.datatables.min.css" />
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
								<div class="widget-title am-fl">会员管理</div>
								<div class="widget-function am-fr">
									<a href="javascript:;" class="am-icon-cog"></a>
								</div>
							</div>

							<div class="Vip_select">
								<div class="Vip_sel">
									<label>认证状态</label>
									<div class="Vip_sele">
										<select id="authStatus" onchange="findAuth();" data-am-selected>
											<option value="0">请选择</option>
											<option value="1">已认证</option>
											<option value="2">未认证</option>
											<option value="3">待审核</option>
											<option value="4">未通过</option>
										</select>
									</div>
								</div>
								<div class="Vip_sel">
									<label>账号状态</label>
									<div class="Vip_sele">
										<select id="status" onchange="findStatus();" data-am-selected>
											<option value="0">请选择</option>
											<option value="1">正常</option>
											<option value="2">已冻结</option>
										</select>
									</div>
								</div>
								<div class="Vip_sel" style="width: 15%;">
									<div class="Vip_sele">
										<input type="text" id="writeMobile" class="tpl-form-input" placeholder="请输入账号" />
									</div>
								</div>
								<div class="Vip_sel" style="width: 20%;">
									<div class="Vip_sele">
										<input type="button" onclick="findUsername();" class="am-btn am-btn-secondary"
												style="border-radius: 2px; width: 100%;" value="搜索" />
									</div>
								</div>
						</div>


							<div class="widget-body  am-fr">
								<div class="am-u-sm-12">
									<table style="width: 100%;"
										class="am-table am-table-compact am-table-striped tpl-table-black "
										id="example-r">
										<thead>
											<tr>
												<th width="12%">实名认证</th>
												<th width="14%">账号</th>
												<th width="14%">状态</th>
												<th width="20%">注册日期</th>
												<th width="28%">操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="obj" items="${list}" varStatus="status">
												<tr class="gradeX">
													<c:if test="${obj.authStatus==1 }">
														<td class=""><span class="label-warning">已实名</span></td>
													</c:if>
													<c:if test="${obj.authStatus==2 }">
														<td class=""><span class="label-danger">未实名</span></td>
													</c:if>
													<c:if test="${obj.authStatus==3 }">
														<td class=""><span class="label-danger">待审核</span></td>
													</c:if>
													<c:if test="${obj.authStatus==4 }">
														<td class=""><span class="label-danger">未通过</span></td>
													</c:if>
													<td class="">${obj.username}</td>
													<c:if test="${obj.status==1}">
														<td class=""><span class="label-warning">正常</span></td>
													</c:if>
													<c:if test="${obj.status==2}">
														<td class=""><span class="label-danger">已冻结</span></td>
													</c:if>
													<td class=""><fmt:formatDate value="${obj.cdt}"
															pattern="yyyy-MM-dd HH:mm" /></td>
													<td>
														<div class="tpl-table-black-operation">
															<c:if test="${obj.status==1 }">
																<a style="cursor: pointer;"
																	onclick="userFreeze(${obj.userId})"> <i
																	class="am-icon-pencil"></i> 冻结
																</a>
															</c:if>
															<c:if test="${obj.status==2 }">
																<a style="cursor: pointer;"
																	onclick="userNormal(${obj.userId})"> <i
																	class="am-icon-pencil"></i> 解冻
																</a>
															</c:if>
															<a href="javascript:;"> <i class="am-icon-pencil"></i>
																查看订单
															</a> <a href="manager/user/Edit?userId=${obj.userId}"
																class="tpl-table-black-operation-del"> <i
																class="am-icon-file-text"></i> 查看详情
															</a>
														</div>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<jsp:include page="../common/managerPager.jsp"></jsp:include>
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
		function userFreeze(userId){
			var url = window.location.href;
			window.location.href="manager/user/freeze?userId="+userId+"&url="+url;
		}
		function userNormal(userId){
			var url = window.location.href;
			window.location.href="manager/user/normal?userId="+userId+"&url="+url;
		}
</script>

<script type="text/javascript">
function findAuth(){
	var authStatus = $("#authStatus").find("option:selected").val();
	if(authStatus==0){
		return false;
	}
	window.location.href="manager/user/authStatusSearch?authStatus="+authStatus;
}

function findStatus(){
	var status = $("#status").find("option:selected").val();
	if(status==0){
		return false;
	}
	window.location.href="manager/user/statusSearch?status="+status;
}

function findUsername(){
	var username = $("#writeMobile").val();
	if(username==''){
		return false;
	}
	window.location.href="manager/user/usernameSearch?username="+username;
}
</script>

</html>