 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../common/managerHead.jsp"></jsp:include>
<script src="./resources/manager/js/echarts.min.js"></script>
<link rel="stylesheet" href="./resources/manager/css/amazeui.min.css" />
<link rel="stylesheet"
	href="./resources/manager/css/amazeui.datatables.min.css" />
<link rel="stylesheet" href="./resources/manager/css/app.css">
<script src="./resources/manager/js/jquery.min.js"></script>
</head>
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
								<div class="widget-title  am-cf">网站公告</div>

							</div>
							<div class="widget-body  am-fr">
								<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
									<div class="am-form-group">
										<div class="am-btn-toolbar">
											<div class="am-btn-group am-btn-group-xs">
												<a href="manager/web/addNotice"
													class="am-btn am-btn-default am-btn-success"><span
													class="am-icon-plus"></span> 新增</a>
											</div>
										</div>
									</div>
								</div>
								<div class="am-u-sm-12">
									<table width="100%"
										class="am-table am-table-compact am-table-striped tpl-table-black "
										id="example-r">
										<thead>
											<tr>
												<th></th>
												<th>公告标题</th>
												<th>作者</th>
												<th>时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${articleList}" begin="0" var="article">
												<tr class="gradeX">
													<td><img src="./resources/manager/img/k.jpg"
														class="tpl-table-line-img" alt=""></td>
													<td class="am-text-middle"><c:out
															value="${article.title}"></c:out></td>
													<td class="am-text-middle"><c:out
															value="${article.author}" /></td>
													<td class="am-text-middle"><fmt:formatDate pattern="yyyy-MM-dd"
															value="${article.releaseDate}" /></td>
													<td class="am-text-middle">
														<div class="tpl-table-black-operation">
															<a
																href="manager/NoticeArticle/toUpdate_article?id=<c:out value="${article.id}"/>">
																<i class="am-icon-pencil"></i> 编辑
															</a> <a
																href="manager/NoticeArticle/delete_article?id=<c:out value="${article.id}"/>"
																class="tpl-table-black-operation-del"> <i
																class="am-icon-trash"></i> 删除
															</a>
														</div>
													</td>
												</tr>
											</c:forEach>
											<!-- more data -->
										</tbody>
									</table>
								</div>
								<div class="am-u-lg-12 am-cf">

									<div class="am-fr">
										<input type="hidden" id="pageNow" name="pageNow" value="1" />
										<ul class="am-pagination tpl-pagination">
											<c:if test="${totalPages>1}">
												<li class=""><a  style= "cursor:pointer; "    id="last_id">«</a></li>
											</c:if>
											<c:forEach var="i" begin="1" end="${totalPages}">
												<li class=""><a  style= "cursor:pointer; "  id="${i}" class="indexbutton"><c:out
															value="${i}" /></a></li>
											</c:forEach>
											<c:if test="${totalPages>1}">
												<li class=""><a  style= "cursor:pointer; "   id="next_id">»</a></li>
											</c:if>
										</ul>
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
		<script type="text/javascript">
		//初始化页面分页
		$(document).ready(function() {
			var lastindex = $("#pageNow").val();
			var lastID = "#" + lastindex;
			$(lastID).parent().attr("class", "am-active");
		})
	</script>
	<script type="text/javascript">
		var totalpages = "${totalPages}";
		//点击上一页
		$("#last_id").click(function () {
			var lastindex = $("#pageNow").val();
			var l_index = lastindex-1;
			if(l_index>0){
				var nowID = "#" + (lastindex);
				$(nowID).parent().attr("class", "");
				var lastID = "#" + (l_index);
				$(lastID).parent().attr("class", "am-active");
				//ajax获取页面内容
				$.ajax({
							url : "manager/NoticeArticle/get_articleListByAjax",
							type : "post",
							dataType : "html",
							contentType : "application/x-www-form-urlencoded; charset=utf-8",
							timeout : 6000,
							data : {
								"index" :l_index
							},
							//获取成功，为页面新添加元素初始化
							success : function(msg) {
								$("tbody").empty();
								$("tbody").append(msg);
								$("#pageNow").val(l_index);
							}
						})
			}
		})
		//点击下一页
		$("#next_id").click(function () {
			var lastindex = $("#pageNow").val();
			var n_index = lastindex-1+2;
			if(lastindex<totalpages){
				var nowID = "#" + (lastindex);
				$(nowID).parent().attr("class", "");
				var nextID = "#" + (n_index);
				$(nextID).parent().attr("class", "am-active");
				//ajax获取页面内容
				$.ajax({
							url : "manager/NoticeArticle/get_articleListByAjax",
							type : "post",
							dataType : "html",
							contentType : "application/x-www-form-urlencoded; charset=utf-8",
							timeout : 6000,
							data : {
								"index" :n_index
							},
							//获取成功，为页面新添加元素初始化
							success : function(msg) {
								$("tbody").empty();
								$("tbody").append(msg);
								$("#pageNow").val(n_index);
							}
						})
			}
		})
	</script>
	<script type="text/javascript">
		$(".indexbutton")
				.click(
						function() {
							var lastindex = $("#pageNow").val();
							var lastID = "#" + lastindex;
							$(lastID).parent().attr("class", "");
							var index = $(this).attr("id");
							$(this).parent().attr("class", "am-active");

							//ajax获取页面内容
							$
									.ajax({
										url : "manager/NoticeArticle/get_articleListByAjax",
										type : "post",
										dataType : "html",
										contentType : "application/x-www-form-urlencoded; charset=utf-8",
										timeout : 6000,
										data : {
											"index" : index
										},
										//获取成功，为页面新添加元素初始化
										success : function(msg) {
											//alert(msg);
											$("tbody").empty();
											$("tbody").append(msg);
											$("#pageNow").val(index);
										}
									})
						})
	</script>
</body>

</html>