package com.freenet.service.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.freenet.common.K;
import com.freenet.common.Page;
import com.freenet.common.V;
import com.freenet.dao.UserDao;
import com.freenet.dao.WithDrawDao;
import com.freenet.domain.Content;
import com.freenet.domain.Money;
import com.freenet.domain.WithDraw;
import com.freenet.service.WithDrawService;

@Service
public class WithDrawServiceImpl implements WithDrawService {

	@Resource
	private WithDrawDao withDrawDao;

	@Resource
	private UserDao userDao;

	// 添加一天提现记录
	public int insertWithDraw(int userId, String drawMoney, String zfbNumber, String mobile, String userName) {
		WithDraw wd = new WithDraw();
		wd.setUserId(userId);
		wd.setUserName(userName);
		wd.setDrawMoney(new BigDecimal(drawMoney));
		wd.setZfbNumber(zfbNumber);
		wd.setMobile(mobile);
		wd.setStatus(V.user_withDraw_wait); // 审核中
		wd.setCdt(new Date());
		return withDrawDao.insertWithDraw(wd);
	}

	// 查询某个用户的提现记录
	public List<WithDraw> selectWithDrawByUserId(int userId) {
		List<WithDraw> WithDraws = withDrawDao.selectWithDrawByUserIdPage(userId, 0,
				20);
		return WithDraws;
	}

	public Map<List<WithDraw>, Integer> selectWithDrawAndTatolPageByPage(int userId, int pageindex) {
		// 获得该类文章的总数
		int totalCount = withDrawDao.selectWithDrawByUserId(userId).size();
		// 获取分页信息
		Page page = new Page(totalCount, pageindex);
		// 根据参数页获得该页的列表
		List<WithDraw> WithDraws = withDrawDao.selectWithDrawByUserIdPage(userId, page.getStartPos(),
				page.getPageSize());
		// 获得分页的总页数
		int totalPages = page.getTotalPageCount();
		// 将该页的文章列表和总页数作为键值对存入map
		Map<List<WithDraw>, Integer> articlePageMap = new HashMap<>();
		articlePageMap.put(WithDraws, totalPages);
		return articlePageMap;
	}

	public Map<List<WithDraw>, Integer> selectWithDrawAndTatolPageByPageDate(int userId, Date startDate, Date endDate,
			int pageindex) {
		// 获得该类文章的总数
		int totalCount = withDrawDao.selectAllCountByDate(userId, startDate, endDate);
		// 获取分页信息
		Page page = new Page(totalCount, pageindex);
		// 根据参数页获得该页的列表
		List<WithDraw> WithDraws = withDrawDao.selectWithDrawByUserIdPageDate(userId, startDate, endDate,
				page.getStartPos(), page.getPageSize());
		// 获得分页的总页数
		int totalPages = page.getTotalPageCount();
		// 将该页的文章列表和总页数作为键值对存入map
		Map<List<WithDraw>, Integer> articlePageMap = new HashMap<>();
		articlePageMap.put(WithDraws, totalPages);
		return articlePageMap;
	}

	// 管理员分页查询所有用户的提现记录
	public List<WithDraw> selectWithDrawByPage(int startPos, int pageSize) {
		return withDrawDao.selectWithDrawByPage(startPos, pageSize);
	}

	public int selectWithDrawByPageCount() {
		return withDrawDao.selectWithDrawByPageCount();
	}

	public void showWithDrawByPage(Model model, String pageNow) {
		Page page = null;
		List<WithDraw> products = new ArrayList<WithDraw>();
		int totalCount = selectWithDrawByPageCount();
		if (pageNow != null) {
			page = new Page(totalCount, Integer.parseInt(pageNow));
			products = this.selectWithDrawByPage(page.getStartPos(), page.getPageSize());
		} else {
			page = new Page(totalCount, 1);
			products = this.selectWithDrawByPage(page.getStartPos(), page.getPageSize());
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
	}

	// 更新用户提现的状态
	public int updateWithDrawStatus(int id, int type) {
		if (type == 1) {// 通过
			return withDrawDao.updateWithDrawStatus(V.user_withDraw_yes, id);
		} else {// 未通过，并返还资金
			WithDraw wd = withDrawDao.selectWithDrawById(id);
			BigDecimal drawMoney = wd.getDrawMoney();
			Money my = userDao.selectMoneyByUserId(wd.getUserId());
			BigDecimal money = my.getMoney();
			BigDecimal nowMoney = money.add(drawMoney);
			userDao.updateMoneyByUserId(wd.getUserId(), nowMoney);
			return withDrawDao.updateWithDrawStatus(V.user_withDraw_no, id);
		}
	}

	// 根据id查询提现记录
	public WithDraw selectWithDrawById(int id) {
		return withDrawDao.selectWithDrawById(id);
	}

	// 查询某条提现记录
	public List<WithDraw> selectByKeyword(String keyword) {
		return withDrawDao.selectByKeyword(keyword);
	}

	// 分页查询不同状态的提现记录
	public List<WithDraw> selectWithDrawStatusByPage(int startPos, int pageSize, int status) {
		return withDrawDao.selectWithDrawStatusByPage(startPos, pageSize, status);
	}

	public int selectWithDrawStatusByPageCount(int status) {
		return withDrawDao.selectWithDrawStatusByPageCount(status);
	}

	public void showWithDrawStatusByPage(Model model, String pageNow, int status) {
		Page page = null;
		List<WithDraw> products = new ArrayList<WithDraw>();
		int totalCount = selectWithDrawStatusByPageCount(status);
		if (pageNow != null) {
			page = new Page(totalCount, Integer.parseInt(pageNow));
			products = this.selectWithDrawStatusByPage(page.getStartPos(), page.getPageSize(), status);
		} else {
			page = new Page(totalCount, 1);
			products = this.selectWithDrawStatusByPage(page.getStartPos(), page.getPageSize(), status);
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
	}

	// 删除某条提现记录
	/*
	 * public int deleteWithDrawById(int id) { return
	 * withDrawDao.deleteWithDrawById(id); }
	 */

	// 添加一条个人消息
	public int insertContent(int userId, String message, int type) {
		Content ct = new Content();
		ct.setUserId(userId);
		ct.setMessage(message);
		ct.setType(type);
		ct.setCdt(new Date());
		return withDrawDao.insertContent(ct);
	}

	// 查询某个用户的所有消息
	public List<Content> selectContentByUserId(int userId) {
		return withDrawDao.selectContentByUserId(userId);
	}

	// 分页查询我的消息
	public List<Content> selectContentByPage(int startPos, int pageSize, int userId) {
		return withDrawDao.selectContentByPage(startPos, pageSize, userId);
	}

	public int seletContentByPageCount(int userId) {
		return withDrawDao.seletContentByPageCount(userId);
	}

	public void showContentByPage(Model model, String pageNow, int userId) {
		Page page = null;
		List<Content> products = new ArrayList<Content>();
		int totalCount = seletContentByPageCount(userId);
		if (pageNow != null) {
			page = new Page(totalCount, Integer.parseInt(pageNow));
			products = this.selectContentByPage(page.getStartPos(), page.getPageSize(), userId);
		} else {
			page = new Page(totalCount, 1);
			products = this.selectContentByPage(page.getStartPos(), page.getPageSize(), userId);
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
	}

	// 批量删除我的消息
	public void deleteMore(List<String> delList) {
		withDrawDao.deleteMore(delList);
	}

	// 根据类型分页查询我的消息
	public List<Content> selectContentTypeByPage(int startPos, int pageSize, int userId, int type) {
		return withDrawDao.selectContentTypeByPage(startPos, pageSize, userId, type);
	}

	public int seletContentTypeByPageCount(int userId, int type) {
		return withDrawDao.seletContentTypeByPageCount(userId, type);
	}

	public void showContentTypeByPage(Model model, String pageNow, int userId, int type) {
		Page page = null;
		List<Content> products = new ArrayList<Content>();
		int totalCount = seletContentTypeByPageCount(userId, type);
		if (pageNow != null) {
			page = new Page(totalCount, Integer.parseInt(pageNow));
			products = this.selectContentTypeByPage(page.getStartPos(), page.getPageSize(), userId, type);
		} else {
			page = new Page(totalCount, 1);
			products = this.selectContentTypeByPage(page.getStartPos(), page.getPageSize(), userId, type);
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
	}

}
