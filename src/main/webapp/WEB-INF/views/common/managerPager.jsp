<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<div class="am-u-lg-12 am-cf">
	<div class="am-fr">
		<ul class="am-pagination tpl-pagination">
		<c:choose>
			<c:when test="${page.pageNow - 1 > 0}">
				<li>
					<a href="${url}${page.pageNow-1 }">«</a>
				</li>
			</c:when>
		</c:choose>
		<c:forEach var="obj" begin="1" end="${page.totalPageCount}" step="1">
			<c:if test="${obj==page.pageNow }">
				<c:if test="${page.pageNow-3>0 }">
					<li id="${page.pageNow-3 }" >
						<a href="${url}${page.pageNow-3 }">${page.pageNow-3 }</a>
					</li>
				</c:if>
				<c:if test="${page.pageNow-2>0 }">
					<li id="${page.pageNow-2 }">
						<a href="${url}${page.pageNow-2 }">${page.pageNow-2 }</a>
					</li>
				</c:if>
				<c:if test="${page.pageNow-1>0 }">
					<li id="${page.pageNow-1 }" >
						<a href="${url}${page.pageNow-1 }">${page.pageNow-1 }</a>
					</li>
				</c:if>
				<li id="${page.pageNow}">
					<a href="${url}${page.pageNow }">${page.pageNow }</a>
				</li>
				<c:if test="${page.pageNow+1<=page.totalPageCount }">
					<li id="${page.pageNow+1}"  >
						<a href="${url}${page.pageNow+1 }">${page.pageNow+1 }</a>
					</li>
				</c:if>
				<c:if test="${page.pageNow+2<=page.totalPageCount }">
					<li id="${page.pageNow+2}" >
						<a href="${url}${page.pageNow+2 }">${page.pageNow+2 }</a>
					</li>
				</c:if>
				<c:if test="${page.pageNow+3<=page.totalPageCount }">
					<li id="${page.pageNow+3 }">
						<a href="${url}${page.pageNow+3 }">${page.pageNow+3 }</a>
					</li>
				</c:if>
			</c:if>
		</c:forEach>
		<c:if test="${page.pageNow < page.totalPageCount}">
			<li>
				<a href="${url}${page.pageNow+1}">»</a>
			</li>
		</c:if>
		</ul>
	</div>
</div>

<script>
$(function(){
	var page = "${page.pageNow}";
	$("#"+page).attr("class","am-active");
});

</script>