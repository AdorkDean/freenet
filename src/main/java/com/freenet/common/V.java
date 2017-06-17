package com.freenet.common;

public class V {

	// Manager(管理员)
	public static final int manager_level_supper = 0; // 超级管理员等级

	public static final int manager_level_worker = 1; // 管理员等级(工作人员)

	// User(用户账号状态)
	public static final int user_status_normal = 1; // 正常

	public static final int user_status_freeze = 2; // 已冻结

	// 实名认证
	public static final int user_authStatus_yes = 1; // 已认证(通过审核)

	public static final int user_authStatus_no = 2; // 未认证

	public static final int user_authStatus_wait = 3; // 审核中(待审核)

	public static final int user_authStatus_pass = 4; // 未认证(属于未通过审核)

	// 性别
	public static final int user_sex_man = 1; // 男

	public static final int user_sex_woman = 2; // 女

	// 证件类别
	public static final int user_cardType_IDCard = 1; // 身份证

	public static final int user_cardType_PassPort = 2; // 护照

	// 提现状态
	public static final int user_withDraw_yes = 0; // 已提现(通过)

	public static final int user_withDraw_wait = 1; // 待审核(审核中)

	public static final int user_withDraw_no = 2; // 未通过

	/// 充值状态
	public static final int user_recharge_wait = 0; // 已提交

	public static final int user_recharge_check = 1; // 审核中

	public static final int user_recharge_yes = 2; // 已提现(通过)

	public static final int user_recharge_no = 3; // 未通过

	// 我的消息类型
	public static final int content_type_system = 1; // 系统消息

	public static final int content_type_withDraw = 2; // 出金（提现）

	public static final int content_type_recharge = 3; // 入金(充值)

	public static final int content_type_safe = 4; // 安全

	public static final int content_type_auth = 5; // 认证

}
