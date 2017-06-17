package com.freenet.common;

/**
 * 相关消息内容
 *
 */
public class Text {

	//(出金)提现
	public static final String withDraw_text_yes = "您的提现申请已通过,资金%s元将会在24小时之内转入您的支付宝账户";
	
	public static final String withDraw_text_no = "您的提现申请未通过,资金%s元将会在24小时之内转回您的当前账户！";

	public static final String withDraw_text_wait = "您的提现申请已提交，请耐心等待工作人员确认信息！";
	
	//安全
	public static final String safe_dealPwd_update = "资金密码修改成功,请牢记您的资金密码！";
	
	public static final String safe_loginPwd_update = "登录密码修改成功,请牢记您的登录密码!";
	
	//认证
	public static final String auth_waite = "您的实名认证信息已提交,请等待工作人员进行核实!";
	
	public static final String auth_yes = "您的实名认证信息已通过审核!";
	
	public static final String auth_no = "您的实名认证信息未通过审核,请仔细修改后在进行提交!";
	
	//系统消息
	public static final String offOrder_Sell_yes = "线下订单完成交易,平台扣留您的%s个抵押币已回到您的账户！";
	
	public static final String offOrder_BuyUser_pay = "对方已打款，请您及时查收资金是否到达您的线下账户！";
	
	public static final String offOrder_SellUser_have = "对方已确认收款,线下订单交易完成！";
	
	public static final String offOrder_cancelOrder = "对方取消了您的订单,此订单正在正常挂单中!";
	
	public static final String offOrder_buySendBreach = "买方发起了违约,当前订单%sMC与%sMC抵押币已被冻结,请耐心等待平台处理！";
	
	public static final String offOrder_sendReturnMsg = "您发起了违约申请,请耐心等待平台处理!";
	
	public static final String offOrder_sellSendBreach = "您发起了违约申请，当前订单的%sMC与%sMC抵押币已被冻结，请耐心等待平台处理!";
	
	public static final String offOrder_buyReturnMsg = "卖方发起了违约,当前订单暂被冻结，请耐心等待平台处理!";
	
	public static final String offOrder_sellConfiscate = "由于您的违约行为，平台将您卖出%sMC已转入买方账户,并将您的%sMC抵押币扣除,希望您之后诚信交易!";
	
	public static final String offOrder_buySuccess = "经过平台的仔细审查，判定为卖方违约,此订单的%sMC已转入您的账户,请查收!";
	
	public static final String offOrder_payMoney = "对方未收到您的打款,若您已打款并确认对方收款账号正确之后,可重新提交或发起违约申请!";
	
	public static final String offOrder_noMoney = "经过平台仔细审查,判定为买方违约并对此进行了相应的处罚,此订单的%sMC与%sMC抵押币已回到您的账户";
	
	public static final String offOrder_buyContract = "经过平台仔细审查,您的违约申请不符合实际情况,平台决定您为违约方,您的账号将会被冻结处理!";
	
	//(入金)充值
	public static final String Recharge_text_check = "您的充值申请管理员审核中！";
	
	public static final String Recharge_text_yes= "您的充值申请已通过,资金%s元将会在24小时之内转入您的账户！";

	public static final String Recharge_text_no = "您的充值申请未通过,资金%s元将会在24小时之内转回您的支付宝账户！";

	public static final String Recharge_text_wait = "您的充值申请已提交，请耐心等待工作人员确认信息！";

}
