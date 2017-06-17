<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>

<jsp:include page="../common/userHead.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		$(".Sa_mid_btn").click(function() {
			$(".Sa_mid_lists").toggle(200);
			if ($(".N_icon").attr("id") == "N_up") {
				$(".N_icon").attr({
					id : "N_down"
				});
				$("#N_down").addClass("Ndown_icon");
				$("#N_down").removeClass("N_icon");
			} else {
				$(".Ndown_icon").attr({
					id : "N_up"
				});
				$("#N_up").addClass("N_icon");
				$("#N_up").removeClass("Ndown_icon");
			}
		});
		$(".Sa_mid_btns").click(function() {
			$(".Sa_mid_listl").toggle(200);
		});
	});
</script>
</head>
<body>
	<jsp:include page="../common/nav.jsp"></jsp:include>
	<div class="Sa_mid">
		<jsp:include page="../common/userMessageLeft.jsp"></jsp:include>
		<div class="Sa_mid_right">
			<p class="Wal_mid_set mid_ser">
				网站公告/<span><c:out value="${article.title }" /></span>
			</p>
			<div class="Cont_text">
				<div class="Cont_tit">
					<p>
						<c:out value="${article.title }" />
					</p>
				</div>
				<div class="Con_context">
					<input class="input_content" type="hidden" value="<c:out value="${article.content }" />">
					<article>
					</article>
				</div>
				<div class="Cont_Article">
					<p class="Cont_top">上一篇：<a class="last"></a></p>
					<p >下一篇：<a class="next"></a></p>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../common/bottom.jsp"></jsp:include>
</body>
<script>
var id = "${article.id}";
var content = $(".input_content").val();
$("article").html(content);
$.ajax({
	url : "user/NoticeArticle/get_ArticleInfoByAjax",
	type : "post",
	dataType : "json",
	contentType : "application/x-www-form-urlencoded; charset=utf-8",
	timeout : 6000,
	data : {
		"id" : id
	},
	//获取成功，为页面新添加元素初始化
	success : function(msg) {
		$(".last").text(msg.lastarticle);
		$(".last").attr("href","user/NoticeArticle/get_article?id="+msg.lastid);
		$(".next").attr("href","user/NoticeArticle/get_article?id="+msg.nextid);
		$(".next").text(msg.nextarticle);
	}
})
</script>
</html>