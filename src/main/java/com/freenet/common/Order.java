package com.freenet.common;

/**
 * 订单状态
 */
public class Order {
	
	//订单类型
	public static final int order_type_manager = 1; //管理员添加
	
	public static final int order_type_user = 2; // 用户自己卖出订单
	
	//线上订单状态
	public static final int order_line_complete = 1;//完成成交
	
	public static final int order_line_guadan = 2; //挂单中
	
	public static final int order_line_kill = 3; //已撤单
	
	//线下订单状态
	public static final int order_off_normal = 0;//订单为正常状态，挂单
	
	public static final int order_off_complete = 1;//完成成交
	
	public static final int order_off_waitBuy = 2;//等待买方打款
	
	public static final int order_off_sureReceivable = 3; //等待卖方确认收款
	
	public static final int order_off_buyBreach = 4;//买方违约
	
	public static final int order_off_sellBreach = 5; //卖方违约
	
	public static final int order_off_EntrustKill = 6; // 已撤单
	
	public static final int order_off_sellSendMust = 7; //拒绝，卖方未收到打款
	
	public static final int order_off_buySendMust = 8;//买方发起违约
	
	
	//取消订单的状态
	/*public static final int order_off_endStatus_have = 0; //正常
	
	public static final int order_off_endStatus_return = 1; //撤销
*/	
	
	
	//平台是否介入
	public static final int order_off_sellIntervention_no = 0;//未介入，正常订单
	
	public static final int order_off_sellIntervention_yes = 1;//平台介入,存在订单问题
	
	
	//线下订单委托状态
	/*public static final int order_off_EntrustGuadan = 0;//正在挂单
*/	
	
	
	

}
