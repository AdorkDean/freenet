<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="left-sidebar">
	<!-- 菜单 -->
	<ul class="sidebar-nav">
		<li class="sidebar-nav-link"><a href="manager/web/allinfo"
			class="sidebar-nav-sub-title "> <i
				class="am-icon-internet-explorer sidebar-nav-link-logo"></i> 网站设置
		</a></li>
		<li class="sidebar-nav-link"><a href="javascript:;"
			class="sidebar-nav-sub-title"> <i
				class="am-icon-users sidebar-nav-link-logo"></i> 会员管理 <span
				class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico"></span>
		</a>
			<ul class="sidebar-nav sidebar-nav-sub">
				<li class="sidebar-nav-link"><a href="manager/user/list"> <span
						class="am-icon-angle-right sidebar-nav-link-logo"></span>会员列表
				</a></li>
				<li class="sidebar-nav-link"><a href="manager/user/toAdd">
						<span class="am-icon-angle-right sidebar-nav-link-logo"></span>添加会员
				</a></li>
			</ul></li>
		<li class="sidebar-nav-link"><a href="javascript:;"
			class="sidebar-nav-sub-title"> <i
				class="am-icon-balance-scale sidebar-nav-link-logo"></i> 订单管理<span
				class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico"></span>
		</a>
			<ul class="sidebar-nav sidebar-nav-sub">
				<li class="sidebar-nav-link"><a href="manager/order/online"> <span
						class="am-icon-angle-right sidebar-nav-link-logo"></span> 线上订单
				</a></li>
				<li class="sidebar-nav-link"><a href="manager/order/offline"> <span
						class="am-icon-angle-right sidebar-nav-link-logo"></span> 线下订单
				</a></li>
				<li class="sidebar-nav-link"><a href="manager/order/toAdd"> <span
						class="am-icon-angle-right sidebar-nav-link-logo"></span> 添加订单
				</a></li>
			</ul></li>
		<li class="sidebar-nav-link"><a href="javascript:;"
			class="sidebar-nav-sub-title"> <i
				class="am-icon-usd sidebar-nav-link-logo"></i> 资金管理 <span
				class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico"></span>
		</a>
			<ul class="sidebar-nav sidebar-nav-sub">
				<li class="sidebar-nav-link"><a href="manager/recharge/toRecharge"> <span
						class="am-icon-angle-right sidebar-nav-link-logo"></span> 用户充值
				</a></li>
				<li class="sidebar-nav-link">
					<a href="manager/withDraw/list">
						<span class="am-icon-angle-right sidebar-nav-link-logo"></span> 提现申请
					</a>
				</li>
			</ul>
		</li>
		<!-- <li class="sidebar-nav-link">
			<a href="javascript:;" class="sidebar-nav-sub-title">
				<i class="am-icon-first-order sidebar-nav-link-logo"></i> 订单管理
				<span class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico"></span>
			</a>
			<ul class="sidebar-nav sidebar-nav-sub">
				<li class="sidebar-nav-link"><a href="OnlineOrder.html"> <span
						class="am-icon-angle-right sidebar-nav-link-logo"></span> 添加订单
				</a></li>
			</ul></li> -->

		<li class="sidebar-nav-link"><a href="javascript:;"
			class="sidebar-nav-sub-title"> <i
				class="am-icon-jpy sidebar-nav-link-logo"></i> 货币管理 <span
				class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico"></span>
		</a>
			<ul class="sidebar-nav sidebar-nav-sub">
				<li class="sidebar-nav-link"><a href="manager/coin/list"> <span
						class="am-icon-angle-right sidebar-nav-link-logo"></span> 货币列表
				</a></li>
			</ul></li>
		<li class="sidebar-nav-link"><a href="javascript:;"
			class="sidebar-nav-sub-title"> <i
				class="am-icon-comments-o sidebar-nav-link-logo"></i> 咨询管理 <span
				class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico"></span>
		</a>
			<ul class="sidebar-nav sidebar-nav-sub">
				<li class="sidebar-nav-link"><a
					href="manager/NoticeArticle/get_articleList"> <span
						class="am-icon-angle-right sidebar-nav-link-logo"></span> 网站公告
				</a></li>
				<li class="sidebar-nav-link"><a
					href="manager/MediaArticle/get_articleList"> <span
						class="am-icon-angle-right sidebar-nav-link-logo"></span> 媒体报道
				</a></li>
				<li class="sidebar-nav-link"><a
					href="manager/AdvisoryArticle/get_articleList"> <span
						class="am-icon-angle-right sidebar-nav-link-logo"></span> 业内咨询
				</a></li>
			</ul></li>
		<li class="sidebar-nav-link"><a href="javascript:;"
			class="sidebar-nav-sub-title"> <i
				class="am-icon-wrench sidebar-nav-link-logo"></i> 系统管理 <span
				class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico"></span>
		</a>
			<ul class="sidebar-nav sidebar-nav-sub">
				<li class="sidebar-nav-link"><a href="manager/work/list"> <span
						class="am-icon-angle-right sidebar-nav-link-logo"></span> 管理员列表
				</a></li>
				<li class="sidebar-nav-link"><a href="manager/toPwdSet"> <span
						class="am-icon-angle-right sidebar-nav-link-logo"></span> 修改密码
				</a></li>
				<li class="sidebar-nav-link"><a href="manager/payAllocation"> <span
						class="am-icon-angle-right sidebar-nav-link-logo"></span> 收款配置
				</a></li>
			</ul></li>
	</ul>
</div>