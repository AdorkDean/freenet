<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<div class="Not_Page N_Pages">
	<ul>
		<c:if test="${page.pageNow - 1 > 0}">
			<li class="liPager"><a href="${url}${page.pageNow-1 }" class="liPager" id="last_id">《</a></li>
		</c:if>
		<c:forEach var="obj" begin="1" end="${page.totalPageCount}" step="1">
			<c:if test="${obj==page.pageNow }">
				<c:if test="${page.pageNow-1>0 }">
					<li id="li${page.pageNow-1 }" class="liPager"><a href="${url}${page.pageNow-1 }" id="a${page.pageNow-1 }" class="indexbutton">${page.pageNow-1 }</a></li>
				</c:if>
				<li id="li${page.pageNow }" class="liPager"><a href="${url}${page.pageNow }" id="a${page.pageNow }" class="indexbutton">${page.pageNow }</a></li>
				<c:if test="${page.pageNow+1<=page.totalPageCount }">
					<li id="li${page.pageNow+1}" class="liPager"><a href="${url}${page.pageNow+1}" id="a${page.pageNow+1}" class="indexbutton">${page.pageNow+1 }</a></li>
				</c:if>
			</c:if>
		</c:forEach>
		<c:if test="${page.pageNow < page.totalPageCount}">
			<li class="liPager"><a href="${url}${page.pageNow+1 }" id="next_id">》</a></li>
		</c:if>
	</ul>
</div>

<script>
$(function(){
	var page = "${page.pageNow}";
	$("#li"+page).attr("style","border-right:1px solid #4d8db4; background: white;");
	$("#a"+page).attr("style","color: #0b6499;");
});

</script>