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
				<form id="authForm" action="user/safe/user_Auth" method="post" onsubmit="return checkAuth();" enctype="multipart/form-data">
					<div class="Rel_form_group">
						<div class="Se_pwd_group">
							<label>姓名：</label>
							<div class="Se_pwd_inp">
								<input type="hidden" value="${sessionUser.userId}" name="userId"/>
								<input type="text" id="authName" name="authName"/>
								
							</div>
						</div>
						<div class="Rel_pwd_group">
							<label>性别：</label>
							<div class="Rel_pwd_inp">
								<input class="Rel_radio" type="radio" name="sex" value="1" /><label class="Rel_sex">先生</label>

							</div>
							<div class="Rel_pwd_inp">
								<input class="Rel_radio" type="radio" name="sex" value="2" /><label class="Rel_sex">女士</label>
							</div>
						</div>
						<div class="Se_pwd_group">
							<label>证件类型：</label>
							<div class="Se_pwd_inp">
								<select id="cardType" name="cardType">
									<option value="0">请选择</option>
									<option value="1">身份证</option>
									<option value="2">护照</option>
								</select>
							</div>
						</div>
						<div class="Se_pwd_group">
							<label>证件号码：</label>
							<div class="Se_pwd_inp">
								<input id="card" type="text" name="card"/>
							</div>
						</div>
						<div class="Se_pwd_group">
							<label>证件正面：</label>
							<div class="Se_pwd_inp">
								<span class="Real_Click">点击上传</span>
								<input class="Real_Upload" type="file" name="photoFront" id="file0" value="点击上传" multiple="multiple" />
								<span class="Real_Prompt">请上传gif，JPG，png，bmp</span>
								<br>
								<div style="width: 251px; height: 137px;"><img class="Real_img" src="" id="img0"></div>
							</div>
						</div>
						<div class="Se_pwd_group">
							<label>证件背面：</label>
							<div class="Se_pwd_inp">
								<span class="Real_Click">点击上传</span>
								<input class="Real_Upload" type="file" name="photoBack" id="file1" value="点击上传" multiple="multiple" />
								<span class="Real_Prompt">请上传gif，JPG，png，bmp</span>
								<br>
								<div style="width: 251px; height: 137px;"><img class="Real_img" src="" id="img1"></div>

							</div>
						</div>
						<div class="Se_pwd_group">
							<label>手持证件照：</label>
							<div class="Se_pwd_inp">
								<span class="Real_Click">点击上传</span>
								<input class="Real_Upload" type="file" name="photoAll" id="file3" value="点击上传" multiple="multiple" />
								<span class="Real_Prompt">请上传gif，JPG，png，bmp</span>
								<br>
								<div style="width: 251px; height: 137px;"><img class="Real_img" src="" id="img3"></div>
							</div>
						</div>
						<div class="Se_form_submit">
							<input type="submit" id="submitAuth" value="提交" />&nbsp;&nbsp;&nbsp;<span style="font-size: 14px; color: #1ce4ef;">我们将会在24小时之内审核</span>
						</div>
					</div>
				</form>
				<div class="Real_Process">
					<p style="padding-top: 20px;">实名认证信息流程：</p>
					<p style="padding-top: 20px;">1.如若您的实名认证未通过，请您仔细审查您的实名认证信息错误并认真修改后再进行提交</p>
					<p style="padding-top: 10px;">2.提交后,平台会第一时间审核您的信息,为了方便你之后的交易,请随时注意您的实名状态</p>
					<p style="padding-top: 10px;">3.申请通过,您可以进行平台内的交易流程</p>
				</div>
			</div>
		</div>
		<jsp:include page="../common/bottom.jsp"></jsp:include>
		<script>
			$("#file0").change(function() {
				var objUrl = getObjectURL(this.files[0]);
				console.log("objUrl = " + objUrl);
				if(objUrl) {
					$("#img0").attr("src", objUrl);
					$("#img0").css("width", "249px");
					$("#img0").css("height", "136px");
				}
			});
			$("#file1").change(function() {
				var objUrl = getObjectURL(this.files[0]);
				console.log("objUrl = " + objUrl);
				if(objUrl) {
					$("#img1").attr("src", objUrl);
					$("#img1").css("width", "249px");
					$("#img1").css("height", "136px");
				}
			});
			$("#file3").change(function() {
				var objUrl = getObjectURL(this.files[0]);
				console.log("objUrl = " + objUrl);
				if(objUrl) {
					$("#img3").attr("src", objUrl);
					$("#img3").css("width", "249px");
					$("#img3").css("height", "136px");
				}
			});
			//建立一個可存取到該file的url
			function getObjectURL(file) {
				var url = null;
				if(window.createObjectURL != undefined) { // basic
					url = window.createObjectURL(file);
				} else if(window.URL != undefined) { // mozilla(firefox)
					url = window.URL.createObjectURL(file);
				} else if(window.webkitURL != undefined) { // webkit or chrome
					url = window.webkitURL.createObjectURL(file);
				}
				return url;
			}
		</script>
		
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
		
		<script type="text/javascript">
			function checkAuth(){
				if($("#authName").val()==''){
					alert("请输入姓名!");
					return false;
				}
				if($('input:radio[name="sex"]:checked').val()==null){
					alert("请选择性别!");
					return false;
				}
				if($("#cardType option:selected").val()==0){
					alert("请选择证件类型!");
					return false;
				}
				if($("#card").val()==''){
					alert("请输入证件号码!");
					return false;
				}
				if($("#img0").attr("src")==''){
					alert("请上传证件正面图片!");
					return false;
				}
				if($("#img1").attr("src")==''){
					alert("请上传证件背面图片!");
					return false;
				}
				if($("#img3").attr("src")==''){
					alert("请上传手持证件照片!");
					return false;
				}
				return true;
			}
		</script>
		
	</body>
</html>