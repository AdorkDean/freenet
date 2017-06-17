package com.freenet.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.freenet.domain.LineOrder;

public interface LineOrderDao {

	// 添加一条线上订单
	int insertLineOrder(LineOrder lineOrder);

	// 分页查询所有线上订单
	List<LineOrder> selectLineOrderByPage(@Param("startPos") int startPos, @Param("pageSize") int pageSize);

	int selectLineOrderByPageCount();

	// 分页查询所有线上订单
	List<LineOrder> selectLinOrderByPage(@Param("startPos") int startPos, @Param("pageSize") int pageSize);

	int selectLinOrderByPageCount();

	// 分页查询不同状态的订单
	List<LineOrder> selectLineOrderStatusByPage(@Param("startPos") int startPos, @Param("pageSize") int pageSize,
			@Param("status") int status);

	int selectLineOrderStatusByPageCount(@Param("status") int status);

	// 模糊查询某条线上订单
	List<LineOrder> selectByKeyword(@Param("keyword") String keyword);

	// 查询某条已完成的订单详情
	LineOrder selectById(@Param("id") int id);

	// 根据卖方id查询所有线上订单
	List<LineOrder> selectBySellUserId(@Param("sellUserId") int sellUserId);

	// 根据状态获取表中的订单数量
	int getCountByStatusAndUser(@Param("sellUserId") Integer sellUserId, @Param("buyUserId") Integer buyUserId,
			@Param("status") Integer status, @Param("dateType") String dateType, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

	// 根据状态及特定用户进行查询
	List<LineOrder> getAllByStatusAndUser(@Param("sellUserId") Integer sellUserId,
			@Param("buyUserId") Integer buyUserId, @Param("status") Integer status,
			@Param("startPage") Integer startPage, @Param("pageSize") Integer pageSize,
			@Param("dateType") String dateType, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	void updateSatusById(@Param("id") Integer id, @Param("status") Integer status);
	void updateBuyDateById(@Param("id") Integer id, @Param("buyCdt") Date buyCdt);
	void updateBuyUseridById(@Param("id") Integer id, @Param("buyUserId") Integer buyUserId);
	void updateBuyUsernameById(@Param("id") Integer id, @Param("buyUsername") String buyUsername);
	void updateBuynameById(@Param("id") Integer id, @Param("buyName") String buyName);

}
