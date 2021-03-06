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
<script src="./resources/manager/Editor/ueditor.config.js"></script>
<script src="./resources/manager/Editor/ueditor.all.js"></script>


<body data-type="widgets">
	<script src="./resources/manager/js/theme.js"></script>
	<div class="am-g tpl-g">
		<!-- 头部 -->
		<jsp:include page="../common/managerHead.jsp"></jsp:include>

		<!-- 侧边导航栏 -->
		<jsp:include page="../common/managerLeft.jsp"></jsp:include>
		<!-- 内容区域 -->
		<div class="tpl-content-wrapper">
			<div class="row-content am-cf">
				<div class="row">
					<div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
						<div class="widget am-cf">
							<div class="widget-head am-cf">
								<div class="widget-title am-fl">新增网站公告</div>
								<div class="widget-function am-fr">
									<a href="javascript:;" class="am-icon-cog"></a>
								</div>
							</div>
							<div class="widget-body am-fr">
								<form id="submitForm"
									class="am-form tpl-form-border-form tpl-form-border-br"
									action="manager/NoticeArticle/add_article" method="post">
									<div class="am-form-group">
										<label for="user-name" class="am-u-sm-3 am-form-label">公告标题
										</label>
										<div class="am-u-sm-9">
											<input type="text" class="tpl-form-input" id="user-name"
												placeholder="请输入公告标题" name="title"
												value="<c:out value="${article.title}"/>">
										</div>
									</div>
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">作者 </label>
										<div class="am-u-sm-9">
											<input type="text" placeholder="请输入编辑人" name="author"
												value="<c:out value="${article.author}"/>">
										</div>
									</div>
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">时间 </label>
										<div class="am-u-sm-9">
											<input  style= "cursor:pointer; " readonly="readonly"  type="text" class="am-form-field tpl-form-no-bg"
												placeholder="请输入发布时间" data-am-datepicker=""
												name="releaseDate"
												value="<fmt:formatDate type="date" value="${article.releaseDate}" />" />
										</div>
									</div>
									<div class="am-form-group">
										<label for="user-intro" class="am-u-sm-3 am-form-label">公告内容</label>
										<div class="am-u-sm-9">
											<script id="container" name="content" type="text/plain">
                                                    这里写你的初始化内容
                                             </script>
                                             <input type="hidden" value="<c:out value="${article.content}"/>" id="article_content"/>
										</div>
									</div>
									<div class="am-form-group">
										<div class="am-u-sm-9 am-u-sm-push-3">
											<button id="submitbutton" type="button"
												class="am-btn am-btn-primary tpl-btn-bg-color-success ">提交</button>
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
	<script type="text/javascript">
		var id = "${article.id}";
		if (id > 0) {
			$("form").attr("action", "manager/NoticeArticle/update_article?id=" + id);
		}
		$("#submitbutton").click(function() {
			//	alert($("form").attr("action"));
			$("form").submit();
		})
	</script>
	<!-- 实例化编辑器 -->
	<script type="text/javascript">
		var content = $("#article_content").val();
		var ue = UE.getEditor('container');
		//对编辑器的操作最好在编辑器ready之后再做
		ue.ready(function() {
			//设置编辑器的内容
			ue.setContent(content);
		});
	</script>
	<script src="./resources/manager/js/amazeui.min.js"></script>
	<script src="./resources/manager/js/amazeui.datatables.min.js"></script>
	<script src="./resources/manager/js/dataTables.responsive.min.js"></script>
	<script src="./resources/manager/js/app.js"></script>
</body>
</html>
