package com.freenet.service.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.freenet.common.K;
import com.freenet.common.Order;
import com.freenet.common.Page;
import com.freenet.dao.LineOrderDao;
import com.freenet.dao.UserDao;
import com.freenet.domain.Auth;
import com.freenet.domain.LineOrder;
import com.freenet.domain.User;
import com.freenet.service.LineOrderService;

@Service
public class LineOrderServiceImpl implements LineOrderService {

	@Resource
	private LineOrderDao lineOrderDao;

	@Resource
	private UserDao userDao;

	// 添加一条线上订单
	public int insertLineOrder(int sellUserId, String sellUsername, int addType, String sellCoin, String price) {
		LineOrder lo = new LineOrder();
		if (addType == 1) {
			lo.setType(Order.order_type_manager);// 管理员添加
			lo.setSellName("管理员添加");
		} else {// 用户自己卖出订单
			Auth auth = userDao.selectAuthByUserId(sellUserId);
			lo.setType(Order.order_type_user);
			lo.setSellName(auth.getName());
		}
		lo.setSellUserId(sellUserId);
		lo.setSellUsername(sellUsername);

		lo.setSellCoin(new BigDecimal(sellCoin));
		lo.setPrice(new BigDecimal(price));
		lo.setStatus(Order.order_line_guadan);// 挂单中
		lo.setSellCdt(new Date());
		return lineOrderDao.insertLineOrder(lo);
	}

	// 分页查询所有线上订单
	public List<LineOrder> selectLineOrderByPage(int startPos, int pageSize) {
		return lineOrderDao.selectLineOrderByPage(startPos, pageSize);
	}

	public int selectLineOrderByPageCount() {
		return lineOrderDao.selectLineOrderByPageCount();
	}

	public void showLineOrderByPage(Model model, String pageNow) {
		Page page = null;
		List<LineOrder> products = new ArrayList<LineOrder>();
		int totalCount = selectLineOrderByPageCount();
		if (pageNow != null) {
			page = new Page(totalCount, Integer.parseInt(pageNow));
			products = this.selectLineOrderByPage(page.getStartPos(), page.getPageSize());
		} else {
			page = new Page(totalCount, 1);
			products = this.selectLineOrderByPage(page.getStartPos(), page.getPageSize());
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
	}

	// 分页查询不同状态的订单
	public List<LineOrder> selectLineOrderStatusByPage(int startPos, int pageSize, int status) {
		return lineOrderDao.selectLineOrderStatusByPage(startPos, pageSize, status);
	}

	public int selectLineOrderStatusByPageCount(int status) {
		return lineOrderDao.selectLineOrderStatusByPageCount(status);
	}

	public void showLineOrderStatusByPage(Model model, String pageNow, int status) {
		Page page = null;
		List<LineOrder> products = new ArrayList<LineOrder>();
		int totalCount = selectLineOrderStatusByPageCount(status);
		if (pageNow != null) {
			page = new Page(totalCount, Integer.parseInt(pageNow));
			products = this.selectLineOrderStatusByPage(page.getStartPos(), page.getPageSize(), status);
		} else {
			page = new Page(totalCount, 1);
			products = this.selectLineOrderStatusByPage(page.getStartPos(), page.getPageSize(), status);
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
	}

	// 模糊查询某条线上订单
	public List<LineOrder> selectByKeyword(String keyword) {
		return lineOrderDao.selectByKeyword(keyword);
	}

	// 查询某条已完成的订单详情
	public LineOrder selectById(int id) {
		return lineOrderDao.selectById(id);
	}

	// 根据卖方id查询所有线上订单
	public List<LineOrder> selectBySellUserId(int sellUserId) {
		return lineOrderDao.selectBySellUserId(sellUserId);
	}

	@Override
	public List<LineOrder> getNewByTime(Integer status, Integer size) {
		List<LineOrder> lineOrders = lineOrderDao.getAllByStatusAndUser(null, null, status, 0, size, "buyCdt", null,
				null);
		return lineOrders;
	}

	public Map<List<LineOrder>, Integer> getAllByStatusAndUser(Integer sellUserId, Integer buyUserId, Integer status,
			Integer pageindex, String dateType, Date startDate, Date endDate) {
		int totalCount = lineOrderDao.getCountByStatusAndUser(sellUserId, buyUserId, status, dateType, startDate,
				endDate);
		Page page = new Page(totalCount, pageindex);
		List<LineOrder> lineOrders = lineOrderDao.getAllByStatusAndUser(sellUserId, buyUserId, status,
				page.getStartPos(), page.getPageSize(), dateType, startDate, endDate);
		int totalPages = page.getTotalPageCount();
		Map<List<LineOrder>, Integer> map = new HashMap<>();
		map.put(lineOrders, totalPages);
		return map;
	}

	@Override
	public void updateBuyContent(Integer id,User user) {
		lineOrderDao.updateSatusById(id, Order.order_line_complete);
		lineOrderDao.updateBuyDateById(id, new Date());
		lineOrderDao.updateBuyUseridById(id, user.getUserId());
		lineOrderDao.updateBuyUsernameById(id,user.getUsername());
		lineOrderDao.updateBuynameById(id,userDao.selectAuthByUserId(user.getUserId()).getName());
	}

	@Override
	public void updateBackContent(Integer orderId) {
		//获得订单
	//	LineOrder lineOrder = selectById(orderId);
		//修改订单状态
		lineOrderDao.updateSatusById(orderId, Order.order_line_kill);
	}



}
