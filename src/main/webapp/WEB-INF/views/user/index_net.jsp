<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<jsp:include page="../common/userHead.jsp"></jsp:include>
<body>
	<jsp:include page="../common/nav.jsp"></jsp:include>
	<div class="In_header">
		<div class="In_text_banner">
			<i class="In_iconfont fl">&#xe6d1;</i> <font class="fl">平台公告：</font>
			<div class="In_line fl" id="up_zzjs">
          <div id="marqueebox">
          <c:forEach items="${noticeArticleList }" var="article" begin="0">
            <div class="up_li">
              <a href="user/NoticeArticle/get_article?id=${ article.id }"><span><c:out value="${article.title }"/></span></a>
            </div>
            </c:forEach>
          </div>
        </div>
			<a class="In_More" href="user/NoticeArticle/get_articleList">更多>></a>
		</div>

		<div class="In_mid">
			<div class="In_tit">
				<p>
					未来最具<span>投资价值</span>的货币
				</p>
			</div>
			<c:if test="${sessionUser==null }">
				<div class="In_login">
					<form method="POST" action="user/Login" id="form1"
						enctype="multipart/form-data" onsubmit="return checkUser();">
						<ul style="margin-top: 34px;">
							<li><i class="iconfonts ico"></i> <input id="mobile"
								name="username" class="text_in" type="text" autocomplete="off"
								placeholder="请输入手机号码"> <select>
									<option>中国(+86)</option>
							</select></li>
							<li><i class="iconfonts ico"></i> <input id="checkPassword"
								name="password" class="text_in" type="password"
								autocomplete="off" placeholder="请输入密码"></li>
							<li><i class="iconfonts"></i> <input id="code" name="code"
								class="nav_Verify" type="text" autocomplete="off"
								placeholder="请输入验证码"> <img id="vcode1"
								onclick="change();" class="nav_Verify_img" alt=""
								src="user/imgCode"></li>
							<li><i class="iconfonts"></i> <input id="verifyCode"
								name="verifyCode" class="nav_Verify" type="text"
								autocomplete="off" placeholder="请输入短信验证码"> <input
								id="smss" class="nav_Verify_btn" type="button" value="点击获取">
							</li>
							<c:if test="${msg!=null }">
								<li>
									<p style="color: red;">${msg }</p>
								</li>
							</c:if>
						</ul>
						<input class="nav_Sub" type="submit" value="登录" />
						<div class="nav_pwd">
							<a class="nav_reg" href="user/toRegister">注册</a> <a
								class="nav_find_pwd" href="user/pwdForget_find">找回密码</a>
						</div>
					</form>
				</div>
			</c:if>
		</div>

	</div>
	<div class="In_bottom">
		<div class="In_mid_bot">
			<div class="In_mid_list">
				<p>平台优势</p>
				<div class="In_bot_banner">
					<img src="./resources/user/img/xitongkekao.png" alt="" />
					<p>系统可靠</p>
				</div>
			</div>
			<div class="In_mid_list">
				<p>网站公告</p>
				<div class="In_bot_context">
					<ul>
					<c:forEach items="${noticeArticleList }" var="article" begin="0">
						<li><i class="In_icon">&#xe606;</i> <a href="user/NoticeArticle/get_article?id=${ article.id }"><fmt:formatDate type  = "date" value="${article.releaseDate }"/>
								&nbsp;<c:out value="${article.title }"/></a></li>
					</c:forEach>
					</ul>
				</div>
			</div>
			<div class="In_mid_list In_list_bot">
				<p>媒体报道</p>
				
				<div class="In_bot_context" style="border: none; margin-top: 5px;">
					<ul>
						<c:forEach items="${mediaArticleList }" var="article" begin="0">
						<li><i class="In_icon">&#xe606;</i> <a href="user/MediaArticle/get_article?id=${ article.id }"><fmt:formatDate type="date" value="${article.releaseDate }"/>
								&nbsp;<c:out value="${article.title }"/></a></li>
					</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../common/bottom.jsp"></jsp:include>
</body>

<script type="text/javascript">
	function change() {
		document.getElementById("vcode1").src = "user/imgCode?" + Math.random();
	}
</script>
<!-- 获取验证码操作 -->
<script type="text/javascript">
	var wait = 120;// 120s等待
	$(function() {
		$("#smss").click(function() {
			var mobile = $("#mobile").val();
			var password = $("#checkPassword").val();
			var code = $("#code").val();
			if (mobile == '') {
				alert("请填写手机号!");
				return false;
			}
			if (!verifyPhone(mobile)) {
				alert("手机号码格式不正确!");
				return false;
			}
			if (password == '') {
				alert("请先输入密码!");
				return false;
			}
			if (code == '') {
				alert("请先输入验证码!");
				return false;
			}
			if (wait == 120) {
				time();
				$.ajax({
					cache : true,
					type : 'GET',
					url : 'user/smsVerifyCode',
					data : {
						mobile : mobile
					},
					async : false,
					error : function(request) {
						alert('网络异常,请稍后重试!');
					},
					success : function(data) {
						if (data == "success") {
							alert("验证码已发送！");
						} else {
							alert("验证码发送失败！");
						}
					}
				});
			}
		});
	});

	function time() {
		var o = $("#smss");
		if (wait == 0) {
			o.removeAttr("disabled");
			o.attr("value", "点击获取");
			wait = 120;
		} else {
			o.attr("disabled");
			o.attr("value", "重新发送(" + wait + ")");
			wait--;
			setTimeout(function() {
				time(o)
			}, 1000);
		}
	}
</script>

<script type="text/javascript">
	function checkUser() {
		var mobile = $("#mobile").val().trim();
		var password = $("#checkPassword").val();
		var code = $("#code").val();
		var verifyCode = $("#verifyCode").val();
		if (mobile == '') {
			alert("请输入手机账号!");
			return false;
		}
		if (!verifyPhone(mobile)) {
			alert("手机格式不正确!");
			return false;
		}
		if (password == '') {
			alert("请输入您的密码!");
			return false;
		}
		if (code == '') {
			alert("请输入验证码!");
			return false;
		}
		if (verifyCode == '') {
			alert("请输入短信验证码!");
			return false;
		}

		return true;
	}
</script>
<script type="text/javascript" src="./resources/user/js/font_banner.js"></script>

</html>