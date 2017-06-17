<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="Real_mid_left">
	<p class="Sa_mid_font">新闻资讯</p>
	<ul class="Sa_mid_list">
		<li class="Sa_mid_item"><a class="type_notice"
			href="user/NoticeArticle/get_articleList">网站公告</a></li>
		<li class="Sa_mid_item"><a class="Sa_mid_btn type_media"
			href="javascript:;"> 媒体报道 <i id="N_up" class="N_icon"></i>
		</a>
			<ul class="Sa_mid_lists" style="display: none;">
				<li class="Sa_mid_listitem"><a
					href="user/MediaArticle/get_articleList" class="  type_media">公众媒体</a>
				</li>
			</ul></li>
		<li class="Sa_mid_item"><a class="type_advisory"
			href="user/AdvisoryArticle/get_articleList" class="Sa_mid_btns">业内资讯</a></li>
	</ul>
</div>
<body>
	<script type="text/javascript">
		var type = "${name}";
		//alert(type);
		if (type == 'MediaArticle') {
			$(".type_media").attr("style", "color: #1ce4ef !important;");
			$(".Sa_mid_lists").attr("style","display: block;");
		} else if (type == 'NoticeArticle') {
			$(".type_notice").attr("style", "color: #1ce4ef !important;");
		} else {
			$(".type_advisory").attr("style", "color: #1ce4ef !important;");
		}
	</script>
</body>
</html>