<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<html>
<jsp:include page="../common/userHead.jsp"></jsp:include>	
	<body>
		<jsp:include page="../common/nav.jsp"></jsp:include>
		<div class="Sa_mid">
			<jsp:include page="../common/userSafeLeft.jsp"></jsp:include>
			<div class="Sa_mid_right">
				<p class="Sa_mid_set">实名认证</p>
				
				<div class="Real_external">
					<div class="Rel_form_group">
						<div class="Se_pwd_group">
							<label>审核状态：</label>
							<div class="Se_pwd_inp">
								<c:if test="${obj.authStatus==1 }"><span style="line-height: 1.89rem;">已认证</span></c:if>
								<c:if test="${obj.authStatus==3 }"><span style="line-height: 1.89rem;">审核中</span></c:if>
								<c:if test="${obj.authStatus==4 }"><span style="line-height: 1.89rem;">审核未通过</span></c:if>
							</div>
						</div>
						<div class="Se_pwd_group">
							<label>姓名：</label>
							<div class="Se_pwd_inp">
								<input id="U_name" type="text" readonly="readonly" value="${auth.name}" />
							</div>
						</div>
						<div class="Se_pwd_group">
							<label>性别：</label>
							<div class="Se_pwd_inp">
								<c:if test="${auth.sex==1 }"><span style="line-height: 1.89rem;">先生</span></c:if>
								<c:if test="${auth.sex==2 }"><span style="line-height: 1.89rem;">女士</span></c:if>
							</div>
						</div>
						<div class="Se_pwd_group">
							<label>证件类型：</label>
							<div class="Se_pwd_inp">
								<c:if test="${auth.cardType==1 }">
									<input id="U_cardType" type="text" readonly="readonly" value="身份证" />
								</c:if>
								<c:if test="${auth.cardType==2 }">
									<input id="U_cardType" type="text" readonly="readonly" value="护照" />
								</c:if>
							</div>
						</div>
						<div class="Se_pwd_group">
							<label>证件号码：</label>
							<div class="Se_pwd_inp">
								<input id="U_Cid" type="text" readonly="readonly" value="${auth.card }" />
							</div>
						</div>
						<div class="Se_pwd_group">
							<label>证件正面：</label>
							<div class="Se_pwd_inp">
								<div style="width: 251px; height: 137px;">
									<img class="Real_img" width="251" height="137" src="${auth.photoFront }"></div>
							</div>
						</div>
						<div class="Se_pwd_group">
							<label>证件背面：</label>
							<div class="Se_pwd_inp">
								<div style="width: 251px; height: 137px;">
									<img class="Real_img" width="251" height="137" src="${auth.photoBack }" id="img1"></div>

							</div>
						</div>
						<div class="Se_pwd_group">
							<label>手持证件照：</label>
							<div class="Se_pwd_inp">
								<div style="width: 251px; height: 137px;">
									<img class="Real_img" width="251" height="137" src="${auth.photoAll }" id="img3"></div>
							</div>
						</div>
					</div>
					<c:if test="${obj.authStatus==4 }">
						<div class="Se_form_submit">
							<a href="user/safe/user_toAuth" id="submitAuth" class="sub_mitAuth">修改</a>
						</div>
					</c:if>
				</div>
				<div class="Real_Process">
					<p style="padding-top: 20px;">实名认证信息流程：</p>
					<p style="padding-top: 20px;">1.如若您的实名认证未通过，请重新修改您的实名认证信息并提交。</p>
					<p style="padding-top: 10px;">2.提交后,平台会审核您的信息,为了方便你之后的交易,请随时注意您的实名状态</p>
					<p style="padding-top: 10px;">3.申请通过,您可以进行平台内的交易流程</p>
				</div>
			</div>
		</div>
		<jsp:include page="../common/bottom.jsp"></jsp:include>
	</body>
	<script type="text/javascript">
		 $(document).ready(function() {
		        $(".Sa_mid_btn").click(function() {
		          $(".Sa_mid_lists").toggle(200);
		          if($(".N_icon").attr("id")=="N_up"){
		            $(".N_icon").attr({id:"N_down"});
		            $("#N_down").addClass("Ndown_icon");
		            $("#N_down").removeClass("N_icon");
		          }else{
		            $(".Ndown_icon").attr({id:"N_up"});
		            $("#N_up").addClass("N_icon");
		            $("#N_up").removeClass("Ndown_icon");
		          }
		        });
		        $(".Sa_mid_btns").click(function() {
		          $(".Sa_mid_listl").toggle(200);
		          if($(".Rdown_icon").attr("id")=="R_up"){
		            $(".Rdown_icon").attr({id:"R_down"});
		            $("#R_down").addClass(" R_icon");
		            $("#R_down").removeClass(" Rdown_icon");
		          }else{
		            $(".R_icon").attr({id:"R_up"});
		            $("#R_up").addClass(" Rdown_icon");
		            $("#R_up").removeClass("R_icon");
		          }
		        });
		      });
		</script>
</html>