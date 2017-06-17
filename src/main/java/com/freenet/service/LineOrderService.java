package com.freenet.service;

import java.util.Date;
import java.util.List;

import org.springframework.ui.Model;

import com.freenet.domain.LineOrder;
import com.freenet.domain.User;

import java.util.Map;

public interface LineOrderService {

	// 添加一条线上订单
	int insertLineOrder(int sellUserId, String sellUsername, int addType, String sellCoin, String price);

	// 分页查询所有线上订单
	List<LineOrder> selectLineOrderByPage(int startPos, int pageSize);
	int selectLineOrderByPageCount();
	void showLineOrderByPage(Model model, String pageNow);
	
	//分页查询不同状态的订单
	List<LineOrder> selectLineOrderStatusByPage(int startPos, int pageSize,int status);
	int selectLineOrderStatusByPageCount(int status);
	void showLineOrderStatusByPage(Model model, String pageNow,int status);
	
	//模糊查询某条线上订单
	List<LineOrder> selectByKeyword(String keyword);
	
	// 查询某条已完成的订单详情
	LineOrder selectById(int id);

	// 根据卖方id查询所有线上订单
	List<LineOrder> selectBySellUserId(int sellUserId);

	// 实时获取表中的新记录
	List<LineOrder> getNewByTime(Integer status, Integer size);

	// 获取表中指定用户和状态的所有记录
	Map<List<LineOrder>, Integer> getAllByStatusAndUser(Integer sellUserId, Integer buyUserId, Integer status,
			Integer pageindex, String dateType, Date startDate, Date endDate);
	
	//修改相应记录的状态(购买）
	void updateBuyContent(Integer id,User user);
	
	//修改相应的状态（撤单）
	void updateBackContent(Integer orderId);
	
}
