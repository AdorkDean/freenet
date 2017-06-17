package com.freenet.service.Impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.freenet.common.K;
import com.freenet.common.Order;
import com.freenet.common.Page;
import com.freenet.dao.OffOrderDao;
import com.freenet.dao.UserDao;
import com.freenet.domain.Auth;
import com.freenet.domain.OffOrder;
import com.freenet.service.OffOrderService;

@Service
public class OffOrderServiceImpl implements OffOrderService{
	
	@Resource
	private OffOrderDao offOrderDao;
	
	@Resource
	private UserDao userDao;

	//添加线下订单
	public int insertOffOrder(int sellUserId, String sellUsername, int addType, String sellCoin, String price) {
		OffOrder of = new OffOrder();
		Auth auth = userDao.selectAuthByUserId(sellUserId);
		if(addType==1){//管理员添加
			of.setType(Order.order_type_manager);
		}else{//用户自行卖出
			of.setType(Order.order_type_user);
		}
		of.setSellUserId(sellUserId);
		of.setSellUsername(sellUsername);
		of.setSellName(auth.getName());
		of.setSellCoin(new BigDecimal(sellCoin));
		of.setPrice(new BigDecimal(price));
		of.setStatus(Order.order_off_normal);//订单状态为正常
		//of.setEndStatus(Order.order_off_EntrustGuadan);//属于挂单中  委托状态
		of.setIntervention(Order.order_off_sellIntervention_no);//正常订单，平台未介入
		of.setSellCdt(new Date());
		return offOrderDao.insertOffOrder(of);
	}

	//分页查询所有的线下订单
	public List<OffOrder> selectOffOrderByPage(int startPos, int pageSize) {
		return offOrderDao.selectOffOrderByPage(startPos, pageSize);
	}
	public int selectOffOrderByPageCount() {
		return offOrderDao.selectOffOrderByPageCount();
	}
	public void showOffOrderByPage(Model model, String pageNow) {
		Page page = null;
		List<OffOrder> products = new ArrayList<OffOrder>();	
		int totalCount = selectOffOrderByPageCount(); 	
		if (pageNow != null) {  
			page = new Page(totalCount, Integer.parseInt(pageNow));  
			products = this.selectOffOrderByPage(page.getStartPos(), page.getPageSize());  
		} else {  
			page = new Page(totalCount, 1);  
		    products = this.selectOffOrderByPage(page.getStartPos(), page.getPageSize());  
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
	}

	//分页查询某用户所有委托管理订单
	public List<OffOrder> selectEntrustByPage(int startPos, int pageSize, int sellUserId) {
		return offOrderDao.selectEntrustByPage(startPos, pageSize, sellUserId);
	}
	public int selectEntrustByPageCount(int sellUserId) {
		return offOrderDao.selectEntrustByPageCount(sellUserId);
	}
	public void showEntrustByPage(Model model, String pageNow, int sellUserId) {
		Page page = null;
		List<OffOrder> products = new ArrayList<OffOrder>();	
		int totalCount = selectEntrustByPageCount(sellUserId); 	
		if (pageNow != null) {  
			page = new Page(totalCount, Integer.parseInt(pageNow));  
			products = this.selectEntrustByPage(page.getStartPos(), page.getPageSize(),sellUserId);  
		} else {  
			page = new Page(totalCount, 1);  
		    products = this.selectEntrustByPage(page.getStartPos(), page.getPageSize(),sellUserId);  
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
	}
	
	
	//查询某个用户的所有委托管理
	public List<OffOrder> selectEntrustByUserId(int sellUserId) {
		return offOrderDao.selectEntrustByUserId(sellUserId);
	}
	
	
	
	//分页查询非当前用户的所有线下卖单
	public List<OffOrder> selectSellUserIdByPage(int startPos, int pageSize, int sellUserId) {
		return offOrderDao.selectSellUserIdByPage(startPos, pageSize, sellUserId);
	}
	public int selectSellUserIdByPageCount(int sellUserId) {
		return offOrderDao.selectSellUserIdByPageCount(sellUserId);
	}
	public void showSellUserIdByPage(Model model, String pageNow, int sellUserId) {
		Page page = null;
		List<OffOrder> products = new ArrayList<OffOrder>();	
		int totalCount = selectSellUserIdByPageCount(sellUserId); 	
		if (pageNow != null) {  
			page = new Page(totalCount, Integer.parseInt(pageNow));  
			products = this.selectSellUserIdByPage(page.getStartPos(), page.getPageSize(),sellUserId);  
		} else {  
			page = new Page(totalCount, 1);  
		    products = this.selectSellUserIdByPage(page.getStartPos(), page.getPageSize(),sellUserId);  
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
		
	}

	//分页查询某用户的线下卖出记录
	public List<OffOrder> selectOffOrderSellByPage(int startPos, int pageSize, int sellUserId) {
		return offOrderDao.selectOffOrderSellByPage(startPos, pageSize, sellUserId);
	}
	public int selectOffOrderSellByPageCount(int sellUserId) {
		return offOrderDao.selectOffOrderSellByPageCount(sellUserId);
	}
	public void showOffOrderSellByPage(Model model, String pageNow, int sellUserId) {
		Page page = null;
		List<OffOrder> products = new ArrayList<OffOrder>();	
		int totalCount = selectOffOrderSellByPageCount(sellUserId); 	
		if (pageNow != null) {  
			page = new Page(totalCount, Integer.parseInt(pageNow));  
			products = this.selectOffOrderSellByPage(page.getStartPos(), page.getPageSize(),sellUserId);  
		} else {  
			page = new Page(totalCount, 1);  
		    products = this.selectOffOrderSellByPage(page.getStartPos(), page.getPageSize(),sellUserId);  
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
	}

	//分页查询某用户的买入记录
	public List<OffOrder> selectOffOrderBuyByPage(int startPos, int pageSize, int buyUserId) {
		return offOrderDao.selectOffOrderBuyByPage(startPos,pageSize,buyUserId);
	}
	public int selectOffOrderBuyByPageCount(int buyUserId) {
		return offOrderDao.selectOffOrderBuyByPageCount(buyUserId);
	}
	public void showOffOrderBuyByPage(Model model, String pageNow, int buyUserId) {
		Page page = null;
		List<OffOrder> products = new ArrayList<OffOrder>();	
		int totalCount = selectOffOrderBuyByPageCount(buyUserId); 	
		if (pageNow != null) {  
			page = new Page(totalCount, Integer.parseInt(pageNow));  
			products = this.selectOffOrderBuyByPage(page.getStartPos(), page.getPageSize(),buyUserId);  
		} else {  
			page = new Page(totalCount, 1);  
		    products = this.selectOffOrderBuyByPage(page.getStartPos(), page.getPageSize(),buyUserId);  
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
	}
	
	//分页查询不同状态的线下订单
	public List<OffOrder> selectEntrustStatusByPage(int startPos, int pageSize, int sellUserId, int status) {
		return offOrderDao.selectEntrustStatusByPage(startPos, pageSize, sellUserId, status);
	}
	public int selectEntrustStatusByPageCount(int sellUserId, int status) {
		return offOrderDao.selectEntrustStatusByPageCount(sellUserId, status);
	}
	public void showEntrustStatusByPage(Model model, String pageNow, int sellUserId, int status) {
		Page page = null;
		List<OffOrder> products = new ArrayList<OffOrder>();	
		int totalCount = selectEntrustStatusByPageCount(sellUserId,status); 	
		if (pageNow != null) {  
			page = new Page(totalCount, Integer.parseInt(pageNow));  
			products = this.selectEntrustStatusByPage(page.getStartPos(), page.getPageSize(),sellUserId,status);  
		} else {  
			page = new Page(totalCount, 1);  
		    products = this.selectEntrustStatusByPage(page.getStartPos(), page.getPageSize(),sellUserId,status);  
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
		
	}


	//用户确认购买此订单后补充信息
	public int updateOffOrder(int buyUserId, String buyUsername, String buyName, int id) {
		OffOrder off = new OffOrder();
		off.setBuyUserId(buyUserId);
		off.setBuyUsername(buyUsername);
		off.setBuyName(buyName);
		off.setStatus(Order.order_off_waitBuy);
		off.setBuyCdt(new Date());
		off.setId(id);
		return offOrderDao.updateOffOrder(off);
	}

	//更新订单的订单状态
	public int updateOffOrderStatus(int status, int id) {
		return offOrderDao.updateOffOrderStatus(status, id);
	}

	//根据id查询某条线下订单
	public OffOrder selectOffOrderById(int id) {
		return offOrderDao.selectOffOrderById(id);
	}

	//更新realPrice与realCdt
	public int updaterealPrice(int id, BigDecimal realPrice) {
		OffOrder off = new OffOrder();
		off.setRealPrice(realPrice);
		off.setRealCdt(new Date());
		off.setId(id);
		return offOrderDao.updaterealPrice(off);
	}

	//根据不同状态查询线下订单
	public List<OffOrder> selectOffOrderByStatus(int startPos, int pageSize, int status) {
		return offOrderDao.selectOffOrderByStatus(startPos, pageSize, status);
	}
	public int selectOffOrderByStatusCount(int status) {
		return offOrderDao.selectOffOrderByStatusCount(status);
	}
	public void showOffOrderStatusByPage(Model model, String pageNow, int status) {
		Page page = null;
		List<OffOrder> products = new ArrayList<OffOrder>();	
		int totalCount = selectOffOrderByStatusCount(status); 	
		if (pageNow != null) {  
			page = new Page(totalCount, Integer.parseInt(pageNow));  
			products = this.selectOffOrderByStatus(page.getStartPos(), page.getPageSize(),status);  
		} else {  
			page = new Page(totalCount, 1);  
		    products = this.selectOffOrderByStatus(page.getStartPos(), page.getPageSize(),status);  
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
		
	}

	//分页查询平台接入的线下订单
	public List<OffOrder> selectOffOrderByInterven(int startPos, int pageSize) {
		return offOrderDao.selectOffOrderByInterven(startPos, pageSize);
	}
	public int selectOffOrderByIntervenCount() {
		return offOrderDao.selectOffOrderByIntervenCount();
	}
	public void showOffOrderIntervenByPage(Model model, String pageNow) {
		Page page = null;
		List<OffOrder> products = new ArrayList<OffOrder>();	
		int totalCount = selectOffOrderByIntervenCount(); 	
		if (pageNow != null) {  
			page = new Page(totalCount, Integer.parseInt(pageNow));  
			products = this.selectOffOrderByInterven(page.getStartPos(), page.getPageSize());  
		} else {  
			page = new Page(totalCount, 1);  
		    products = this.selectOffOrderByInterven(page.getStartPos(), page.getPageSize());  
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
	}

	//模糊查询线下订单
	public List<OffOrder> selectByKeyword(String keyword) {
		return offOrderDao.selectByKeyword(keyword);
	}

	//根据不同状态查询某用的线下卖出订单
	public List<OffOrder> selectUserOffByStatus(int startPos, int pageSize, int sellUserId, int status) {
		return offOrderDao.selectUserOffByStatus(startPos,pageSize,sellUserId,status);
	}
	public int selectUserOffByStatusCount(int sellUserId, int status) {
		return offOrderDao.selectUserOffByStatusCount(sellUserId, status);
	}
	public void showUserOffByPage(Model model, String pageNow,int sellUserId,int status) {
		Page page = null;
		List<OffOrder> products = new ArrayList<OffOrder>();	
		int totalCount = selectUserOffByStatusCount(sellUserId,status); 	
		if (pageNow != null) {  
			page = new Page(totalCount, Integer.parseInt(pageNow));  
			products = this.selectUserOffByStatus(page.getStartPos(), page.getPageSize(),sellUserId,status);  
		} else {  
			page = new Page(totalCount, 1);  
		    products = this.selectUserOffByStatus(page.getStartPos(), page.getPageSize(),sellUserId,status);  
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
		
	}

	//平台介入的某用户的线下卖出订单
	public List<OffOrder> selectSellOffByInterven(int startPos, int pageSize, int sellUserId, int intervention) {
		return offOrderDao.selectSellOffByInterven(startPos, pageSize, sellUserId, intervention);
	}
	public int selectSellOffByIntervenCount(int sellUserId, int intervention) {
		return offOrderDao.selectSellOffByIntervenCount(sellUserId, intervention);
	}
	public void showSellOffIntervenByPage(Model model, String pageNow, int sellUserId, int intervention) {
		Page page = null;
		List<OffOrder> products = new ArrayList<OffOrder>();	
		int totalCount = selectSellOffByIntervenCount(sellUserId,intervention); 	
		if (pageNow != null) {  
			page = new Page(totalCount, Integer.parseInt(pageNow));  
			products = this.selectSellOffByInterven(page.getStartPos(), page.getPageSize(),sellUserId,intervention);  
		} else {  
			page = new Page(totalCount, 1);  
		    products = this.selectSellOffByInterven(page.getStartPos(), page.getPageSize(),sellUserId,intervention);  
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
		
	}

	//平台是否介入某用户的线下买入订单
	public List<OffOrder> selectBuyOffByInterven(int startPos, int pageSize, int buyUserId, int intervention) {
		return offOrderDao.selectBuyOffByInterven(startPos,pageSize,buyUserId,intervention);
	}
	public int selectBuyOffByIntervenCount(int buyUserId, int intervention) {
		return offOrderDao.selectBuyOffByIntervenCount(buyUserId, intervention);
	}
	public void showBuyOffIntervenByPage(Model model, String pageNow, int buyUserId, int intervention) {
		Page page = null;
		List<OffOrder> products = new ArrayList<OffOrder>();	
		int totalCount = selectBuyOffByIntervenCount(buyUserId,intervention); 	
		if (pageNow != null) {  
			page = new Page(totalCount, Integer.parseInt(pageNow));  
			products = this.selectBuyOffByInterven(page.getStartPos(), page.getPageSize(),buyUserId,intervention);  
		} else {  
			page = new Page(totalCount, 1);  
		    products = this.selectBuyOffByInterven(page.getStartPos(), page.getPageSize(),buyUserId,intervention);  
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
		
	}

	//根据不同状态查询某用的线下买入订单
	public List<OffOrder> selectBuyOffByStatus(int startPos, int pageSize, int buyUserId, int status) {
		return offOrderDao.selectBuyOffByStatus(startPos, pageSize, buyUserId, status);
	}
	public int selectBuyOffByStatusCount(int buyUserId, int status) {
		return offOrderDao.selectBuyOffByStatusCount(buyUserId, status);
	}
	public void showbuyOffByPage(Model model, String pageNow, int buyUserId, int status) {
		Page page = null;
		List<OffOrder> products = new ArrayList<OffOrder>();	
		int totalCount = selectBuyOffByStatusCount(buyUserId,status); 	
		if (pageNow != null) {  
			page = new Page(totalCount, Integer.parseInt(pageNow));  
			products = this.selectBuyOffByStatus(page.getStartPos(), page.getPageSize(),buyUserId,status);  
		} else {  
			page = new Page(totalCount, 1);  
		    products = this.selectBuyOffByStatus(page.getStartPos(), page.getPageSize(),buyUserId,status);  
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
	}

	//查询某个用户的所有线下订单 
	public List<OffOrder> selectOffBySellUserId(int sellUserId) {
		return offOrderDao.selectOffBySellUserId(sellUserId);
	}

	//更新平台介入状态
	public int updateIntervention(int intervention, int id) {
		return offOrderDao.updateIntervention(intervention, id);
	}

	//分页查询用户买入记录的起始跟结束时间的分页查询 
	public List<OffOrder> selectOffBuyDateAndUser(int startPos,int pageSize,int buyUserId, String start, String end) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dd = sdf.parse(end);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dd);
		calendar.add(Calendar.DAY_OF_MONTH, 1);//加一天
		end = sdf.format(calendar.getTime());
		System.out.println(end);
		return offOrderDao.selectOffBuyDateAndUser(startPos,pageSize,buyUserId, start, end);
	}
	public int selectOffBuyDateUserCount(int buyUserId, String start, String end) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dd = sdf.parse(end);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dd);
		calendar.add(Calendar.DAY_OF_MONTH, 1);//加一天
		end = sdf.format(calendar.getTime());
		System.out.println(end);
		return offOrderDao.selectOffBuyDateUserCount(buyUserId, start, end);
	}
	public void showOffBuyDateUserByPage(Model model, String pageNow, int buyUserId, String start, String end) throws Exception {
		Page page = null;
		List<OffOrder> products = new ArrayList<OffOrder>();	
		int totalCount = selectOffBuyDateUserCount(buyUserId,start, end); 	
		if (pageNow != null) {  
			page = new Page(totalCount, Integer.parseInt(pageNow));  
			products = this.selectOffBuyDateAndUser(page.getStartPos(), page.getPageSize(),buyUserId,start, end);  
		} else {  
			page = new Page(totalCount, 1);  
		    products = this.selectOffBuyDateAndUser(page.getStartPos(), page.getPageSize(),buyUserId,start, end);  
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
		
	}

	//分页查询用户卖出记录的起始跟结束时间的分页查询 
	public List<OffOrder> selectOffSellDateAndUser(int startPos, int pageSize, int sellUserId, String start, String end) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dd = sdf.parse(end);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dd);
		calendar.add(Calendar.DAY_OF_MONTH, 1);//加一天
		end = sdf.format(calendar.getTime());
		System.out.println(end);
		return offOrderDao.selectOffSellDateAndUser(startPos, pageSize, sellUserId, start, end);
	}
	public int selectOffSellDateUserCount(int sellUserId, String start, String end) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dd = sdf.parse(end);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dd);
		calendar.add(Calendar.DAY_OF_MONTH, 1);//加一天
		end = sdf.format(calendar.getTime());
		System.out.println(end);
		return offOrderDao.selectOffSellDateUserCount(sellUserId, start, end);
	}
	public void showOffSellDateUserByPage(Model model, String pageNow, int sellUserId, String start, String end)
			throws Exception {
		Page page = null;
		List<OffOrder> products = new ArrayList<OffOrder>();	
		int totalCount = selectOffSellDateUserCount(sellUserId,start, end); 	
		if (pageNow != null) {  
			page = new Page(totalCount, Integer.parseInt(pageNow));  
			products = this.selectOffSellDateAndUser(page.getStartPos(), page.getPageSize(),sellUserId,start, end);  
		} else {  
			page = new Page(totalCount, 1);  
		    products = this.selectOffSellDateAndUser(page.getStartPos(), page.getPageSize(),sellUserId,start, end);  
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
		
	}

	//分页查询选择天数买入记录
	public List<OffOrder> selectOffBuyByDay(int startPos, int pageSize, int buyUserId,int day)
			throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(day==1){//查询当天
			String start = sdf.format(date);//start
			Calendar ca = Calendar.getInstance();
			ca.add(Calendar.DAY_OF_MONTH,day);
			Date bb = ca.getTime();
			String end = sdf.format(bb);//end
			return offOrderDao.selectOffBuyByDay(startPos,pageSize,buyUserId, start, end);
		}else{
			Calendar ca = Calendar.getInstance();
			ca.add(Calendar.DAY_OF_MONTH,-day);
			Date bb = ca.getTime();
			String start = sdf.format(bb);//start
			Calendar ca1 = Calendar.getInstance();
			ca1.add(Calendar.DAY_OF_MONTH, 1);
			Date thistime = ca1.getTime();
			String end = sdf.format(thistime);//end
			return offOrderDao.selectOffBuyByDay(startPos,pageSize,buyUserId, start, end);
		}
	}
	public int selectOffBuyByDayCount(int buyUserId,int day) throws Exception{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(day==1){//查询当天
			String start = sdf.format(date);//start
			Calendar ca = Calendar.getInstance();
			ca.add(Calendar.DAY_OF_MONTH,day);
			Date bb = ca.getTime();
			String end = sdf.format(bb);//end
			return offOrderDao.selectOffBuyByDayCount(buyUserId, start, end);
		}else{
			Calendar ca = Calendar.getInstance();
			ca.add(Calendar.DAY_OF_MONTH,-day);
			Date bb = ca.getTime();
			String start = sdf.format(bb);//start
			Calendar ca1 = Calendar.getInstance();
			ca1.add(Calendar.DAY_OF_MONTH, 1);
			Date thistime = ca1.getTime();
			String end = sdf.format(thistime);//end
			return offOrderDao.selectOffBuyByDayCount(buyUserId, start, end);
		}
	}
	public void showOffByBuyDayByPage(Model model, String pageNow, int buyUserId,int day)
			throws Exception {
			Page page = null;
			List<OffOrder> products = new ArrayList<OffOrder>();	
			int totalCount = selectOffBuyByDayCount(buyUserId,day); 	
			if (pageNow != null) {  
				page = new Page(totalCount, Integer.parseInt(pageNow));  
				products = this.selectOffBuyByDay(page.getStartPos(), page.getPageSize(),buyUserId,day);  
			} else {  
				page = new Page(totalCount, 1);  
			    products = this.selectOffBuyByDay(page.getStartPos(), page.getPageSize(),buyUserId,day);  
			}
			model.addAttribute(K.list, products);
			model.addAttribute(K.page, page);
		
	}

	//分页查询选择天数卖出记录
	public List<OffOrder> selectOffSellByDay(int startPos, int pageSize, int sellUserId, int day) throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(day==1){//查询当天
			String start = sdf.format(date);//start
			Calendar ca = Calendar.getInstance();
			ca.add(Calendar.DAY_OF_MONTH,day);
			Date bb = ca.getTime();
			String end = sdf.format(bb);//end
			return offOrderDao.selectOffSellByDay(startPos,pageSize,sellUserId, start, end);
		}else{
			Calendar ca = Calendar.getInstance();
			ca.add(Calendar.DAY_OF_MONTH,-day);
			Date bb = ca.getTime();
			String start = sdf.format(bb);//start
			Calendar ca1 = Calendar.getInstance();
			ca1.add(Calendar.DAY_OF_MONTH, 1);
			Date thistime = ca1.getTime();
			String end = sdf.format(thistime);//end
			return offOrderDao.selectOffSellByDay(startPos,pageSize,sellUserId, start, end);
		}
	}
	public int selectOffSellByDayCount(int sellUserId, int day) throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(day==1){//查询当天
			String start = sdf.format(date);//start
			Calendar ca = Calendar.getInstance();
			ca.add(Calendar.DAY_OF_MONTH,day);
			Date bb = ca.getTime();
			String end = sdf.format(bb);//end
			return offOrderDao.selectOffSellByDayCount(sellUserId, start, end);
		}else{
			Calendar ca = Calendar.getInstance();
			ca.add(Calendar.DAY_OF_MONTH,-day);
			Date bb = ca.getTime();
			String start = sdf.format(bb);//start
			Calendar ca1 = Calendar.getInstance();
			ca1.add(Calendar.DAY_OF_MONTH, 1);
			Date thistime = ca1.getTime();
			String end = sdf.format(thistime);//end
			return offOrderDao.selectOffSellByDayCount(sellUserId, start, end);
		}
	}
	public void showOffBySellDayByPage(Model model, String pageNow, int sellUserId, int day) throws Exception {
		Page page = null;
		List<OffOrder> products = new ArrayList<OffOrder>();	
		int totalCount = selectOffSellByDayCount(sellUserId,day); 	
		if (pageNow != null) {  
			page = new Page(totalCount, Integer.parseInt(pageNow));  
			products = this.selectOffSellByDay(page.getStartPos(), page.getPageSize(),sellUserId,day);  
		} else {  
			page = new Page(totalCount, 1);  
		    products = this.selectOffSellByDay(page.getStartPos(), page.getPageSize(),sellUserId,day);  
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
		
	}

	//分页查询用户买委托管理的起始跟结束时间的分页查询 
	public List<OffOrder> selectEntrustDateAndUser(int startPos, int pageSize, int sellUserId, String start, String end)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dd = sdf.parse(end);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dd);
		calendar.add(Calendar.DAY_OF_MONTH, 1);//加一天
		end = sdf.format(calendar.getTime());
		System.out.println(end);
		return offOrderDao.selectEntrustDateAndUser(startPos, pageSize, sellUserId, start, end);
	}
	public int selectEntrustDateUserCount(int sellUserId, String start, String end) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dd = sdf.parse(end);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dd);
		calendar.add(Calendar.DAY_OF_MONTH, 1);//加一天
		end = sdf.format(calendar.getTime());
		System.out.println(end);
		return offOrderDao.selectEntrustDateUserCount(sellUserId, start, end);
	}
	public void showOffEntrustByPage(Model model, String pageNow, int sellUserId, String start, String end)
			throws Exception {
		Page page = null;
		List<OffOrder> products = new ArrayList<OffOrder>();	
		int totalCount = selectEntrustDateUserCount(sellUserId,start, end); 	
		if (pageNow != null) {  
			page = new Page(totalCount, Integer.parseInt(pageNow));  
			products = this.selectEntrustDateAndUser(page.getStartPos(), page.getPageSize(),sellUserId,start, end);  
		} else {  
			page = new Page(totalCount, 1);  
		    products = this.selectEntrustDateAndUser(page.getStartPos(), page.getPageSize(),sellUserId,start, end);  
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
	}

	//分页查询选择天数委托管理
	public List<OffOrder> selectEntrustByDay(int startPos, int pageSize, int sellUserId, int day) throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(day==1){//查询当天
			String start = sdf.format(date);//start
			Calendar ca = Calendar.getInstance();
			ca.add(Calendar.DAY_OF_MONTH,day);
			Date bb = ca.getTime();
			String end = sdf.format(bb);//end
			return offOrderDao.selectEntrustByDay(startPos,pageSize,sellUserId, start, end);
		}else{
			Calendar ca = Calendar.getInstance();
			ca.add(Calendar.DAY_OF_MONTH,-day);
			Date bb = ca.getTime();
			String start = sdf.format(bb);//start
			Calendar ca1 = Calendar.getInstance();
			ca1.add(Calendar.DAY_OF_MONTH, 1);
			Date thistime = ca1.getTime();
			String end = sdf.format(thistime);//end
			return offOrderDao.selectEntrustByDay(startPos,pageSize,sellUserId, start, end);
		}
	}
	public int selectEntrustByDayCount(int sellUserId, int day) throws Exception {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(day==1){//查询当天
			String start = sdf.format(date);//start
			Calendar ca = Calendar.getInstance();
			ca.add(Calendar.DAY_OF_MONTH,day);
			Date bb = ca.getTime();
			String end = sdf.format(bb);//end
			return offOrderDao.selectEntrustByDayCount(sellUserId, start, end);
		}else{
			Calendar ca = Calendar.getInstance();
			ca.add(Calendar.DAY_OF_MONTH,-day);
			Date bb = ca.getTime();
			String start = sdf.format(bb);//start
			Calendar ca1 = Calendar.getInstance();
			ca1.add(Calendar.DAY_OF_MONTH, 1);
			Date thistime = ca1.getTime();
			String end = sdf.format(thistime);//end
			return offOrderDao.selectEntrustByDayCount(sellUserId, start, end);
		}
	}
	public void showEntrustDayByPage(Model model, String pageNow, int sellUserId, int day) throws Exception {
		Page page = null;
		List<OffOrder> products = new ArrayList<OffOrder>();	
		int totalCount = selectEntrustByDayCount(sellUserId,day); 	
		if (pageNow != null) {  
			page = new Page(totalCount, Integer.parseInt(pageNow));  
			products = this.selectEntrustByDay(page.getStartPos(), page.getPageSize(),sellUserId,day);  
		} else {  
			page = new Page(totalCount, 1);  
		    products = this.selectEntrustByDay(page.getStartPos(), page.getPageSize(),sellUserId,day);  
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
		
	}

	
	
	

	
}
