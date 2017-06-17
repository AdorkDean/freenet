<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<html>
<jsp:include page="../common/userHead.jsp"></jsp:include>
<script type="text/javascript">
	$(document).ready(function() {
		$(".Sa_mid_btn").click(function() {
			$(".Sa_mid_lists").toggle(200);
			if($(".N_icon").attr("id") == "N_up") {
				$(".N_icon").attr({
					id: "N_down"
				});
				$("#N_down").addClass("Ndown_icon");
				$("#N_down").removeClass("N_icon");
			} else {
				$(".Ndown_icon").attr({
					id: "N_up"
				});
				$("#N_up").addClass("N_icon");
				$("#N_up").removeClass("Ndown_icon");
			}
		});
		$(".Sa_mid_btns").click(function() {
			$(".Sa_mid_listl").toggle(200);
			if($(".Rdown_icon").attr("id") == "R_up") {
				$(".Rdown_icon").attr({
					id: "R_down"
				});
				$("#R_down").addClass(" R_icon");
				$("#R_down").removeClass(" Rdown_icon");
			} else {
				$(".R_icon").attr({
					id: "R_up"
				});
				$("#R_up").addClass(" Rdown_icon");
				$("#R_up").removeClass("R_icon");
			}
		});
	});
</script>

	<body>
		<jsp:include page="../common/nav.jsp"></jsp:include>
		<div class="Sa_mid">
			<jsp:include page="../common/userSafeLeft.jsp"></jsp:include>
			<div class="Sa_mid_right ">
				<p class="Sa_mid_set ">我的消息</p>
				<div class="M_News ">
					<div class="Wal_op_type ">
						<span>筛选类型</span>
						<ul>
							<li>
								<a href="user/safe/content">全部</a>
							</li>
							<li>
								<a href="user/safe/content/meaasge?type=1">系统消息</a>
							</li>
							<li>
								<a href="user/safe/content/meaasge?type=2">出金</a>
							</li>
							<li>
								<a href="user/safe/content/meaasge?type=3">入金</a>
							</li>
							<li>
								<a href="user/safe/content/meaasge?type=4">安全</a>
							</li>
							<li>
								<a href="user/safe/content/meaasge?type=5">认证</a>
							</li>
						</ul>
					</div>
				</div>
				<div class="My_tables ">
						<table>
							<tr style="border-bottom: 1px solid white; ">
								<th style="text-align: left; "><input  id="SelectAll" onclick="selectAll();"style="margin-top: 2px;" type="checkbox"/><label style="font-size: 12px;">全选</label> </th>
								<th style="text-align: right; "></th>
								<th style="text-align: right; "><a onclick="batchDeletes();"><i class=" My_iconfont ">&#xe648;</i></a></th>
							</tr>
							<c:forEach var="obj" items="${list}" varStatus="status">
							<tr>
								<td><input type="checkbox" id="subcheck" name="subcheck" value="${obj.id}" /> </td>
								<td ><label>${obj.message}</label></td>
								<td ><label><fmt:formatDate value="${obj.cdt}" pattern="yyyy-MM-dd HH:mm" /></label></td>
							</tr>
							</c:forEach>
							
						</table>
					</div>
			</div>
			<jsp:include page="../common/userPager.jsp"></jsp:include>
		</div>
		<jsp:include page="../common/bottom.jsp"></jsp:include>
	</body>
	
<script type="text/javascript">
function batchDeletes(){
    //判断至少写了一项
    var checkedNum = $("input[name='subcheck']:checked").length;
    if(checkedNum==0){
        alert("请至少选择一项!");
        return false;
    }
    if(confirm("确定删除所选项目?")){
    var checkedList = new Array();
    $("input[name='subcheck']:checked").each(function(){
        checkedList.push($(this).val());
    });
    $.ajax({
        type:"POST",
        url:"user/content/batchDeletes",
        data:{"delitems":checkedList.toString()},
        datatype:"html",
        success:function(data){
        	$(":checkbox").attr("checked",false)
            alert('删除成功!');
            setTimeout("location.reload()",1000);//页面刷新
        },
        error:function(data){
            alert('删除失败!');
        }
    });
    }
}


function selectAll(){
    if ($("#SelectAll").is(":checked")) {
        $(":checkbox").prop("checked", true);//所有选择框都选中
    } else {
        $(":checkbox").prop("checked", false);
    }
}
</script>
</html>