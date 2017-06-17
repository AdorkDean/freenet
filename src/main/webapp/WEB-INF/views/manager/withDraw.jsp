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
									<div class="widget-title am-fl">提现列表</div>
									<div class="widget-function am-fr">
										<a href="javascript:;" class="am-icon-cog"></a>
									</div>
								</div>
								<div class="Vip_select">
					                <form action="manager/withDraw/findKeyWord" method="POST" onsubmit="return keyWord();">
					                  <div class="Vip_sel" style="width: 19%;">
					                    <div class="Vip_sele" style="width: 97%;">
					                      <input type="text" id="keyword" name="keyword" class="tpl-form-input" placeholder="请输入账号、支付宝账号"  />
					                    </div>
					                  </div>
					                  <div class="Vip_sel" style="width: 8%; margin-left:3%;">
					                    <div class="Vip_sele" style="width: 100%;">
					                      <input class="am-btn am-btn-secondary" type="submit" style="border-radius:2px ; width: 100%;" value="搜索"  />
					                    </div>
					                  </div>
					                   <div class="Vip_sel" style="margin-left: 3%;">
					                    <label>状态</label>
					                    <div class="Vip_sele">
					                      <select id="WithDrawStatus" onchange="findWithDraw();"data-am-selected>
					                      	<option value="-1">请选择</option>
					                      	<option value="0">已通过</option>
					                        <option value="1">待审核</option>
					                        <option value="2">未通过</option>
					                      </select>
					                    </div>
					                  </div>
					                  </form>
					                 
					                </div>
								<div class="widget-body  am-fr">
									<div class="am-u-sm-12">
										<table style="width:100%;" class="am-table am-table-compact am-table-striped tpl-table-black " id="example-r">
											<thead>
												<tr>
													<th>账号</th>
													<th>姓名</th>
													<th>金额</th>
													<th>支付宝账号</th>
													<th>状态</th>
													<th>提现日期</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="obj" items="${list}" varStatus="status">
													<tr class="gradeX">
														<td class="">${obj.mobile}</td>
														<td class="">${obj.userName}</td>
														<td class=""><fmt:formatNumber type="number" value="${obj.drawMoney}" pattern="#,###.##" minFractionDigits="2"/></td>
														<td class="">${obj.zfbNumber}</td>
														<c:if test="${obj.status==0 }">
															<td class=""><span class="label-yes">已通过</span></td>
														</c:if>
														<c:if test="${obj.status==1 }">
															<td class=""><span class="label-warning">待审核</span></td>
														</c:if>
														<c:if test="${obj.status==2 }">
															<td class=""><span class="label-danger">未通过</span></td>
														</c:if>
														<td class=""><fmt:formatDate value="${obj.cdt}" pattern="yyyy-MM-dd HH:mm" /></td>
														<td>
															<div class="tpl-table-black-operation">
																<c:if test="${obj.status!=0&&obj.status!=2}">
																	<a style="cursor: pointer;" onclick="withDrawStatus(${obj.id},1);">
																		<i class="am-icon-pencil"></i> 通过
																	</a>
																	<a style="cursor: pointer;" onclick="withDrawStatus(${obj.id},2);">
																		<i class="am-icon-pencil"></i> 不通过
																	</a>
																</c:if>
																<%-- <c:if test="${obj.status==0||obj.status==2}">
																	<a style="cursor: pointer;" onclick="withDrawDelete(${obj.id})" class="tpl-table-black-operation-del">
																		<i class="am-icon-file-text"></i> 删除
																	</a>
																</c:if> --%>
															</div>
														</td>
													</tr>
												</c:forEach>
												<!-- more data -->
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
function withDrawStatus(id,type){
	var url = window.location.href;
	window.location.href="manager/withDraw/statusSet?id="+id+"&type="+type+"&url="+url;
}
function withDrawDelete(id){
	var url = window.location.href;
	window.location.href="manager/withDraw/delete?id="+id+"&url="+url;
}
</script>

<script type="text/javascript">
function keyWord(){
	if($("#keyword").val()==''){
		return false;
	}
	return true;
}

function findWithDraw(){
	var WithDrawStatus = $("#WithDrawStatus").find("option:selected").val();
	if(WithDrawStatus==-1){
		window.location.href=="manager/withDraw/list";
	}
	window.location.href="manager/withDraw/statusSearch?WithStatus="+WithDrawStatus;
}
</script>
</html>