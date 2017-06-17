package com.freenet.service;

import java.math.BigDecimal;

import java.util.List;


import org.springframework.ui.Model;

import com.freenet.domain.OffOrder;

public interface OffOrderService {
	
	//添加线下订单
	int insertOffOrder(int sellUserId,String sellUsername,int addType,String sellCoin,String price);
	
	//分页查询所有的线下订单
	List<OffOrder> selectOffOrderByPage(int startPos, int pageSize);
	int selectOffOrderByPageCount();
	void showOffOrderByPage(Model model, String pageNow);
	
	//分页查询某个用户所有委托管理订单
	List<OffOrder> selectEntrustByPage(int startPos, int pageSize,int sellUserId);
	int selectEntrustByPageCount(int sellUserId);
	void showEntrustByPage(Model model,String pageNow,int sellUserId);
	
	//查询某个用户的所有委托管理
	List<OffOrder> selectEntrustByUserId(int sellUserId);
	

	//分页查询非当前用户的所有线下卖单
	List<OffOrder> selectSellUserIdByPage(int startPos, int pageSize,int sellUserId);
	int selectSellUserIdByPageCount(int sellUserId);
	void showSellUserIdByPage(Model model,String pageNow,int sellUserId);
	
	//分页查询某用户的线下卖出记录
	List<OffOrder> selectOffOrderSellByPage(int startPos, int pageSize,int sellUserId);
	int selectOffOrderSellByPageCount(int sellUserId);
	void showOffOrderSellByPage(Model model,String pageNow,int sellUserId);
	
	//分页查询某用户的买入记录
	List<OffOrder> selectOffOrderBuyByPage(int startPos, int pageSize,int buyUserId);
	int selectOffOrderBuyByPageCount(int buyUserId);
	void showOffOrderBuyByPage(Model model,String pageNow,int buyUserId);
	
	//分页查询不同状态的线下订单
	List<OffOrder> selectEntrustStatusByPage(int startPos, int pageSize,int sellUserId,int status);
	int selectEntrustStatusByPageCount(int sellUserId,int status);
	void showEntrustStatusByPage(Model model,String pageNow,int sellUserId,int status);
	
	//用户确认购买此订单后补充信息
	int updateOffOrder(int buyUserId,String buyUsername,String buyName,int id);
	
	//更新订单的订单状态
	int updateOffOrderStatus(int status,int id);
	
	//根据id查询某条线下订单
	OffOrder selectOffOrderById(int id);
	
	//更新realPrice与realCdt
	int updaterealPrice(int id,BigDecimal realPrice);
	
	//根据不同状态查询线下订单
	List<OffOrder> selectOffOrderByStatus(int startPos, int pageSize,int status);
	int selectOffOrderByStatusCount(int status);
	void showOffOrderStatusByPage(Model model,String pageNow,int status);
	
	//分页查询平台接入的线下订单
	List<OffOrder> selectOffOrderByInterven(int startPos, int pageSize);
	int selectOffOrderByIntervenCount();
	void showOffOrderIntervenByPage(Model model,String pageNow);
	
	//模糊查询线下订单
	List<OffOrder> selectByKeyword(String keyword);
	
	//根据不同状态查询某用的线下卖出订单
	List<OffOrder> selectUserOffByStatus(int startPos,int pageSize,int sellUserId,int status);
	int selectUserOffByStatusCount(int sellUserId,int status);
	void showUserOffByPage(Model model,String pageNow,int sellUserId,int status);
	
	//平台介入的某用户的线下卖出订单
	List<OffOrder> selectSellOffByInterven(int startPos,int pageSize,int sellUserId,int intervention);
	int selectSellOffByIntervenCount(int sellUserId,int intervention);
	void showSellOffIntervenByPage(Model model,String pageNow,int sellUserId,int intervention);
	
	//平台是否介入某用户的线下买入订单
	List<OffOrder> selectBuyOffByInterven(int startPos,int pageSize,int buyUserId,int intervention);
	int selectBuyOffByIntervenCount(int buyUserId,int intervention);
	void showBuyOffIntervenByPage(Model model,String pageNow,int buyUserId,int intervention);
	
	//根据不同状态查询某用的线下买入订单
	List<OffOrder> selectBuyOffByStatus(int startPos,int pageSize,int buyUserId,int status);
	int selectBuyOffByStatusCount(int buyUserId,int status);
	void showbuyOffByPage(Model model,String pageNow,int buyUserId,int status);
	
	//查询某个用户的所有线下订单 
	List<OffOrder> selectOffBySellUserId(int sellUserId);
	
	//更新平台介入的状态
	int updateIntervention(int intervention,int id);
	
	//分页查询用户买入记录的起始跟结束时间的分页查询 
	List<OffOrder> selectOffBuyDateAndUser(int startPos,int pageSize,int buyUserId,String start,String end) throws Exception;
	int selectOffBuyDateUserCount(int buyUserId,String start, String end)throws Exception;
	void showOffBuyDateUserByPage(Model model,String pageNow,int buyUserId,String start, String end)throws Exception;
	
	//分页查询用户卖出记录的起始跟结束时间的分页查询 
	List<OffOrder> selectOffSellDateAndUser(int startPos,int pageSize,int sellUserId,String start,String end)throws Exception;
	int selectOffSellDateUserCount(int sellUserId,String start, String end) throws Exception ;
	void showOffSellDateUserByPage(Model model,String pageNow,int sellUserId,String start, String end)throws Exception;
	
	//分页查询选择天数买入记录
	List<OffOrder> selectOffBuyByDay(int startPos,int pageSize,int buyUserId,int day)throws Exception;
	int selectOffBuyByDayCount(int buyUserId,int day) throws Exception;
	void showOffByBuyDayByPage(Model model,String pageNow,int buyUserId,int day)throws Exception;
	
	//分页查询选择天数卖出记录
	List<OffOrder> selectOffSellByDay(int startPos,int pageSize,int sellUserId,int day)throws Exception;
	int selectOffSellByDayCount(int sellUserId,int day) throws Exception;
	void showOffBySellDayByPage(Model model,String pageNow,int sellUserId,int day)throws Exception;
	
	//分页查询用户买委托管理的起始跟结束时间的分页查询 
	List<OffOrder> selectEntrustDateAndUser(int startPos,int pageSize,int sellUserId,String start,String end) throws Exception;
	int selectEntrustDateUserCount(int sellUserId,String start, String end)throws Exception;
	void showOffEntrustByPage(Model model,String pageNow,int sellUserId,String start, String end)throws Exception;
	
	//分页查询选择天数委托管理
	List<OffOrder> selectEntrustByDay(int startPos,int pageSize,int sellUserId,int day)throws Exception;
	int selectEntrustByDayCount(int sellUserId,int day) throws Exception;
	void showEntrustDayByPage(Model model,String pageNow,int sellUserId,int day)throws Exception;
	
}
