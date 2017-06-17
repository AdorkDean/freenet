package com.freenet.service.Impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.freenet.common.Page;
import com.freenet.common.V;
import com.freenet.dao.RechargeRecordDao;
import com.freenet.dao.UserDao;
import com.freenet.domain.RechargeRecord;
import com.freenet.domain.User;
import com.freenet.service.RechargeRecordService;

@Service
public class RechargeRecordServiceImpl implements RechargeRecordService {

	@Resource
	private RechargeRecordDao rechargeRecordDao;
	@Resource
	private UserDao userDao;

	@Override
	public void insert_Recharge(User user, BigDecimal amount, String adress, String payment) {
		BigDecimal balance = userDao.selectMoneyByUserId(user.getUserId()).getMoney();
		RechargeRecord record = new RechargeRecord();
		record.setUserId(user.getUserId());
		record.setRechargeQuantity(amount);
		record.setStatus(V.user_recharge_wait);
		record.setFromDate(new Date());
		record.setBalance(balance);
		record.setPayment(payment);
		record.setWalletAddress(adress);
		rechargeRecordDao.insert_Recharge(record);
	}

	@Override
	public Map<List<RechargeRecord>, Integer> getAllRecordByPage(Integer userid, int pageindex) {
		// 获得该类文章的总数
		int totalCount = rechargeRecordDao.getAllCount(userid);
		// 获取分页信息
		Page page = new Page(totalCount, pageindex);
		// 根据参数页获得该页的列表
		List<RechargeRecord> articles = rechargeRecordDao.getAllRecordByPage(userid, page.getStartPos(),
				page.getPageSize());
		// 获得分页的总页数
		int totalPages = page.getTotalPageCount();
		// 将该页的文章列表和总页数作为键值对存入map
		Map<List<RechargeRecord>, Integer> articlePageMap = new HashMap<>();
		articlePageMap.put(articles, totalPages);
		return articlePageMap;
	}

	@Override
	public Map<List<RechargeRecord>, Integer> getRecordByDatePage(Integer userId, Date startDate, Date endDate,
			int indexpage) {
		// 获得该类文章的总数
		int totalCount = rechargeRecordDao.getAllCountByDate(userId, startDate, endDate);
		// 获取分页信息
		Page page = new Page(totalCount, indexpage);
		// 根据参数页获得该页的列表
		List<RechargeRecord> articles = rechargeRecordDao.getRecordByDatePage(userId, startDate, endDate,
				page.getStartPos(), page.getPageSize());
		// 获得分页的总页数
		int totalPages = page.getTotalPageCount();
		// 将该页的文章列表和总页数作为键值对存入map
		Map<List<RechargeRecord>, Integer> articlePageMap = new HashMap<>();
		articlePageMap.put(articles, totalPages);
		return articlePageMap;
	}

	@Override
	public List<RechargeRecord> getRecordEnd(Integer userId) {
		return null;
	}

	@Override
	public List<RechargeRecord> getRecordEndByDate(Integer userId, Date startDate, Date endDate) {
		return null;
	}

	@Override
	public Map<List<RechargeRecord>, Integer> M_getAllRecord(int indexpage) {
		// 获得该类文章的总数
		int totalCount = rechargeRecordDao.M_getAllCount(null, null);
		// 获取分页信息
		Page page = new Page(totalCount, indexpage);
		// 根据参数页获得该页的列表
		List<RechargeRecord> articles = rechargeRecordDao.M_getAllRecordByPage(null, null, page.getStartPos(),
				page.getPageSize());
		// 获得分页的总页数
		int totalPages = page.getTotalPageCount();
		// 将该页的文章列表和总页数作为键值对存入map
		Map<List<RechargeRecord>, Integer> articlePageMap = new HashMap<>();
		articlePageMap.put(articles, totalPages);
		return articlePageMap;
	}

	public Map<List<RechargeRecord>, Integer> M_getAllRecord(int status, int indexpage) {
		// 获得该类文章的总数
		int totalCount = rechargeRecordDao.M_getAllCount(status, null);
		// 获取分页信息
		Page page = new Page(totalCount, indexpage);
		// 根据参数页获得该页的列表
		List<RechargeRecord> articles = rechargeRecordDao.M_getAllRecordByPage(status, null, page.getStartPos(),
				page.getPageSize());
		// 获得分页的总页数
		int totalPages = page.getTotalPageCount();
		// 将该页的文章列表和总页数作为键值对存入map
		Map<List<RechargeRecord>, Integer> articlePageMap = new HashMap<>();
		articlePageMap.put(articles, totalPages);
		return articlePageMap;
	}

	public Map<List<RechargeRecord>, Integer> M_getAllRecord(String payment, int indexpage) {
		// 获得该类文章的总数
		int totalCount = rechargeRecordDao.M_getAllCount(null, payment);
		// 获取分页信息
		Page page = new Page(totalCount, indexpage);
		// 根据参数页获得该页的列表
		List<RechargeRecord> articles = rechargeRecordDao.M_getAllRecordByPage(null, payment, page.getStartPos(),
				page.getPageSize());
		// 获得分页的总页数
		int totalPages = page.getTotalPageCount();
		// 将该页的文章列表和总页数作为键值对存入map
		Map<List<RechargeRecord>, Integer> articlePageMap = new HashMap<>();
		articlePageMap.put(articles, totalPages);
		return articlePageMap;
	}

	public Map<List<RechargeRecord>, Integer> M_getAllRecord(int status, String payment, int indexpage) {
		// 获得该类文章的总数
		int totalCount = rechargeRecordDao.M_getAllCount(status, payment);
		// 获取分页信息
		Page page = new Page(totalCount, indexpage);
		// 根据参数页获得该页的列表
		List<RechargeRecord> articles = rechargeRecordDao.M_getAllRecordByPage(status, payment, page.getStartPos(),
				page.getPageSize());
		// 获得分页的总页数
		int totalPages = page.getTotalPageCount();
		// 将该页的文章列表和总页数作为键值对存入map
		Map<List<RechargeRecord>, Integer> articlePageMap = new HashMap<>();
		articlePageMap.put(articles, totalPages);
		return articlePageMap;
	}

	public Map<List<RechargeRecord>, Integer> M_chooseGetAllRecord(Integer status, String payment, int indexpage) {
		if (status == -1 && payment == "") {
			System.out.println("查全部");
			return M_getAllRecord(indexpage);
		} else if (status == -1) {
			System.out.println("限制账户");
			return M_getAllRecord(payment, indexpage);
		} else if (payment == "") {
			System.out.println("查限制状态");
			return M_getAllRecord(status, indexpage);
		} else {
			System.out.println("状态和账户都限制");
			return M_getAllRecord(status, payment, indexpage);
		}
	}

	@Override
	public void updateStatus(Integer id, Integer status) {
		rechargeRecordDao.updateStatus(id, status);
	}

	@Override
	public RechargeRecord getOneById(int id) {
		RechargeRecord record = rechargeRecordDao.getOne(id);
		return record;
	}

	@Override
	public void updatemoney_add(int id, int userId, BigDecimal money) {
		BigDecimal money2 = userDao.selectMoneyByUserId(userId).getMoney();
		money2 = money2.add(money);
		userDao.updateMoneyByUserId(userId, money2);
		rechargeRecordDao.updateRecord(id, money2);
		rechargeRecordDao.updateArriveDate(id, new Date());
	}

	@Override
	public Map<List<RechargeRecord>, Integer> getAllRecordByPageNow(Integer userid, int indexpage) {
		// 获得该类文章的总数
		int totalCount = rechargeRecordDao.getAllCount(userid);
		// 获取分页信息
		Page page = new Page(totalCount, indexpage);
		// 根据参数页获得该页的列表
		List<RechargeRecord> articles = rechargeRecordDao.getAllRecordByPage(userid, page.getStartPos(), 10);
		// 获得分页的总页数
		int totalPages = page.getTotalPageCount();
		// 将该页的文章列表和总页数作为键值对存入map
		Map<List<RechargeRecord>, Integer> articlePageMap = new HashMap<>();
		articlePageMap.put(articles, totalPages);
		return articlePageMap;
	}

}
