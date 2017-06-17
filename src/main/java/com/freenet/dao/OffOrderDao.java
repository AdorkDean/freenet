package com.freenet.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.freenet.domain.OffOrder;

public interface OffOrderDao {
	
	//添加线下订单
	int insertOffOrder(OffOrder offOrder);
	
	//分页查询所有的线下订单
	List<OffOrder> selectOffOrderByPage(@Param("startPos") int startPos,@Param("pageSize") int pageSize);
	int selectOffOrderByPageCount();
	
	//分页查询某用户所有委托管理的订单
	List<OffOrder> selectEntrustByPage(@Param("startPos") int startPos,@Param("pageSize") int pageSize,@Param("sellUserId") int sellUserId);
	int selectEntrustByPageCount(@Param("sellUserId") int sellUserId);
	
	//查询某个用户的所有委托管理
	List<OffOrder> selectEntrustByUserId(@Param("sellUserId") int sellUserId);
	
	//分页查询非当前用户的所有线下卖单
	List<OffOrder> selectSellUserIdByPage(@Param("startPos") int startPos,@Param("pageSize") int pageSize,@Param("sellUserId") int sellUserId);
	int selectSellUserIdByPageCount(@Param("sellUserId") int sellUserId);
	
	//分页查询某用户的线下卖出记录
	List<OffOrder> selectOffOrderSellByPage(@Param("startPos") int startPos,@Param("pageSize") int pageSize,@Param("sellUserId") int sellUserId);
	int selectOffOrderSellByPageCount(@Param("sellUserId") int sellUserId);
	
	//分页查询某用户的买入记录
	List<OffOrder> selectOffOrderBuyByPage(@Param("startPos") int startPos,@Param("pageSize") int pageSize,@Param("buyUserId") int buyUserId);
	int selectOffOrderBuyByPageCount(@Param("buyUserId") int buyUserId);
	
	//分页查询不同状态的线下订单
	List<OffOrder> selectEntrustStatusByPage(@Param("startPos") int startPos,@Param("pageSize") int pageSize,
			@Param("sellUserId") int sellUserId,@Param("status") int status);
	int selectEntrustStatusByPageCount(@Param("sellUserId") int sellUserId,@Param("status") int status);
	
	//用户确认购买此订单后补充信息
	int updateOffOrder(@Param("offOrder") OffOrder offOrder);
	
	//更新订单的订单状态
	int updateOffOrderStatus(@Param("status") int status,@Param("id") int id);
	
	//根据id查询某条线下订单
	OffOrder selectOffOrderById(@Param("id") int id);
	
	//更新realPrice与realCdt
	int updaterealPrice(@Param("offOrder") OffOrder offOrder);
	
	//根据不同状态查询线下订单
	List<OffOrder> selectOffOrderByStatus(@Param("startPos") int startPos,@Param("pageSize") int pageSize,@Param("status") int status);
	int selectOffOrderByStatusCount(@Param("status") int status);
	
	//分页查询平台介入的线下订单
	List<OffOrder> selectOffOrderByInterven(@Param("startPos") int startPos,@Param("pageSize") int pageSize);
	int selectOffOrderByIntervenCount();
	
	//模糊查询线下订单
	List<OffOrder> selectByKeyword(@Param("keyword") String keyword);
	
	//根据不同状态查询某用的线下卖出订单
	List<OffOrder> selectUserOffByStatus(@Param("startPos") int startPos,@Param("pageSize") int pageSize,
			@Param("sellUserId") int sellUserId,@Param("status") int status);
	int selectUserOffByStatusCount(@Param("sellUserId") int sellUserId,@Param("status") int status);
	
	//平台介入的某用户的线下卖出订单
	List<OffOrder> selectSellOffByInterven(@Param("startPos") int startPos,@Param("pageSize") int pageSize,
			@Param("sellUserId") int sellUserId,@Param("intervention") int intervention);
	int selectSellOffByIntervenCount(@Param("sellUserId") int sellUserId,@Param("intervention") int intervention);
	
	//平台是否介入某用户的线下买入订单
	List<OffOrder> selectBuyOffByInterven(@Param("startPos") int startPos,@Param("pageSize") int pageSize,
			@Param("buyUserId") int buyUserId,@Param("intervention") int intervention);
	int selectBuyOffByIntervenCount(@Param("buyUserId") int buyUserId,@Param("intervention") int intervention);
	
	//根据不同状态查询某用的线下买入订单
	List<OffOrder> selectBuyOffByStatus(@Param("startPos") int startPos,@Param("pageSize") int pageSize,
			@Param("buyUserId") int buyUserId,@Param("status") int status);
	int selectBuyOffByStatusCount(@Param("buyUserId") int buyUserId,@Param("status") int status);
	
	//查询某个用户的所有线下订单 
	List<OffOrder> selectOffBySellUserId(@Param("sellUserId") int sellUserId);
	
	//更新平台介入的状态
	int updateIntervention(@Param("intervention") int intervention,@Param("id") int id);
	
	//分页查询用户买入记录的起始跟结束时间的分页查询 
	List<OffOrder> selectOffBuyDateAndUser(@Param("startPos") int startPos,@Param("pageSize") int pageSize,
			@Param("buyUserId") int buyUserId,@Param("start") String start,@Param("end") String end);
	int selectOffBuyDateUserCount(@Param("buyUserId") int buyUserId,@Param("start") String start,@Param("end") String end);
	
	//分页查询用户卖出记录的起始跟结束时间的分页查询 
	List<OffOrder> selectOffSellDateAndUser(@Param("startPos") int startPos,@Param("pageSize") int pageSize,
			@Param("sellUserId") int sellUserId,@Param("start") String start,@Param("end") String end);
	int selectOffSellDateUserCount(@Param("sellUserId") int sellUserId,@Param("start") String start,@Param("end") String end);
	
	
	//分页查询选择天数买入记录
	List<OffOrder> selectOffBuyByDay(@Param("startPos") int startPos,@Param("pageSize") int pageSize,
			@Param("buyUserId") int buyUserId,@Param("start") String start,@Param("end") String end);
	int selectOffBuyByDayCount(@Param("buyUserId") int buyUserId,@Param("start") String start,@Param("end") String end);
	
	//分页查询选择天数卖出记录
	List<OffOrder> selectOffSellByDay(@Param("startPos") int startPos,@Param("pageSize") int pageSize,
			@Param("sellUserId") int sellUserId,@Param("start") String start,@Param("end") String end);
	int selectOffSellByDayCount(@Param("sellUserId") int sellUserId,@Param("start") String start,@Param("end") String end);

	//分页查询用户买委托管理的起始跟结束时间的分页查询 
	List<OffOrder> selectEntrustDateAndUser(@Param("startPos") int startPos,@Param("pageSize") int pageSize,
			@Param("sellUserId") int sellUserId,@Param("start") String start,@Param("end") String end);
	int selectEntrustDateUserCount(@Param("sellUserId") int sellUserId,@Param("start") String start,@Param("end") String end);

	//分页查询选择天数委托管理
	List<OffOrder> selectEntrustByDay(@Param("startPos") int startPos,@Param("pageSize") int pageSize,
			@Param("sellUserId") int sellUserId,@Param("start") String start,@Param("end") String end);
	int selectEntrustByDayCount(@Param("sellUserId") int sellUserId,@Param("start") String start,@Param("end") String end);




}
