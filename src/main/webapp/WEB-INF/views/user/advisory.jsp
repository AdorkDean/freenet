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
		<input type="hidden" id="pageNow" name="pageNow" value="1" />
			<p class="Wal_mid_set">业内资讯</p>
			<div class="container">
				<c:forEach items="${articleList }" var="article" begin="0"
					varStatus="status" step="2">
					<div class="Med_tit">
						<div class="Med_list_tit">
							<div class="Img_show">
								 <img
									src="img/f4351976abd842cfae11c1fa5bbc7802.png" />
							</div>
							<a class="article_title" id="<c:out value="${article.id }"/>"><c:out
									value="${article.title }" /> </a>
						</div>
						<c:forEach items="${articleList }" var="article1"
							begin="${status.count }" end="${status.count}">
							<div class="Med_list_tit">
								<div class="Img_show">
									 <img
										src="img/4d3223ae955d43c8a107fc13f57df528.jpg" />
								</div>
								<a class="article_title" id="<c:out value="${article1.id }"/>"><c:out
										value="${article1.title }" /></a>
							</div>
						</c:forEach>
					</div>
				</c:forEach>
			</div>

			<div class="Not_Page">
				<ul>
					<c:if test="${totalPages>1 }">
						<li style="border-right: 1px solid #4d8db4;"><a id="last_id">《</a></li>
					</c:if>
					<c:forEach begin="1" var="i" end="${totalPages }">
						<li style="border-right: 1px solid #4d8db4;"><a id="${i}"
							class="indexbutton"><c:out value="${i}"></c:out></a></li>
					</c:forEach>
					<c:if test="${totalPages>1 }">
						<li style="border-right: 1px solid #4d8db4;"><a id="next_id">》</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../common/bottom.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(".container").on('click','.article_title',function() {
		//alert("heeee");
		var id = $(this).attr("id");
		window.location.href = "user/AdvisoryArticle/get_article?id=" + id;
	})
</script>
<script type="text/javascript">
	var totalpages = "${totalPages}";
	//点击上一页
	$("#last_id")
			.click(
					function() {
						var lastindex = $("#pageNow").val();
						var l_index = lastindex - 1;
						if (l_index > 0) {
							var nowID = "#" + (lastindex);
							//	$(nowID).parent().attr("class", "");
							var lastID = "#" + (l_index);
							//		$(lastID).parent().attr("class", "am-active");
							//ajax获取页面内容
							$
									.ajax({
										url : "user/AdvisoryArticle/get_articleListByAjax",
										type : "post",
										dataType : "html",
										contentType : "application/x-www-form-urlencoded; charset=utf-8",
										timeout : 6000,
										data : {
											"index" : l_index
										},
										//获取成功，为页面新添加元素初始化
										success : function(msg) {
											$(".container").empty();
											$(".container").append(msg);
											$("#pageNow").val(l_index);
										}
									})
						}
					})
	//点击下一页
	$("#next_id")
			.click(
					function() {
						var lastindex = $("#pageNow").val();
						var n_index = lastindex - 1 + 2;
						if (lastindex < totalpages) {
							var nowID = "#" + (lastindex);
							//$(nowID).parent().attr("class", "");
							var nextID = "#" + (n_index);
							//	$(nextID).parent().attr("class", "am-active");
							//ajax获取页面内容
							$
									.ajax({
										url : "user/AdvisoryArticle/get_articleListByAjax",
										type : "post",
										dataType : "html",
										contentType : "application/x-www-form-urlencoded; charset=utf-8",
										timeout : 6000,
										data : {
											"index" : n_index
										},
										//获取成功，为页面新添加元素初始化
										success : function(msg) {
											$(".container").empty();
											$(".container").append(msg);
											$("#pageNow").val(n_index);
										}
									})
						}
					})
</script>
<script type="text/javascript">
	$(".indexbutton").click(function() {
		var lastindex = $("#pageNow").val();
		var lastID = "#" + lastindex;
		//$(lastID).parent().attr("class", "");
		var index = $(this).attr("id");
		//$(this).parent().attr("class", "am-active");

		//ajax获取页面内容
		$.ajax({
			url : "user/AdvisoryArticle/get_articleListByAjax",
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
				$(".container").empty();
				$(".container").append(msg);
				$("#pageNow").val(index);
			}
		})
	})
</script>
</html>