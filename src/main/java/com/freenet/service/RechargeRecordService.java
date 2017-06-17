package com.freenet.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.freenet.domain.RechargeRecord;
import com.freenet.domain.User;

public interface RechargeRecordService {

	/**
	 * 用户充值
	 */
	public void insert_Recharge(User user, BigDecimal amount, String adress, String payment);

	
	
	/**
	 * 管理员查询表中所有的充值记录(分页)
	 */
	public Map<List<RechargeRecord>, Integer> M_getAllRecord(int indexpage);

	/**
	 * 管理员查询记录列表按照记录的状态 (分页)
	 */
	public Map<List<RechargeRecord>, Integer> M_getAllRecord(int status, int indexpage);

	/**
	 * 管理员查询记录列表按照记录的状态 (分页)
	 */
	public Map<List<RechargeRecord>, Integer> M_getAllRecord(String payment, int indexpage);

	/**
	 * 管理员查询记录列表按照记录的状态 (分页)
	 */
	public Map<List<RechargeRecord>, Integer> M_getAllRecord(int status, String payment, int indexpage);

	public Map<List<RechargeRecord>, Integer> M_chooseGetAllRecord(Integer status, String payment, int indexpage);
	
	
	/**
	 * 用户查询充值记录（分页）
	 */
	public Map<List<RechargeRecord>, Integer> getAllRecordByPage(Integer userid, int indexpage);
	
	/**
	 * 获得最近10条记录
	 */
	public Map<List<RechargeRecord>, Integer> getAllRecordByPageNow(Integer userid, int indexpage);
	
	/**
	 * 用户查询某时间段的充值记录(分页)
	 */
	public Map<List<RechargeRecord>, Integer> getRecordByDatePage(Integer userId, Date startDate, Date endDate,
			int indexpage);
	
	/**
	 * 查询到账记录
	 */
	public List<RechargeRecord> getRecordEnd(Integer userId);

	/**
	 * 查询某时间段到账记录
	 */
	public List<RechargeRecord> getRecordEndByDate(Integer userId, Date startDate, Date endDate);

	/**
	 * 改变记录状态
	 */
	public void updateStatus(Integer id, Integer status);

	/**
	 * 充值余额
	 */
	public void updatemoney_add(int id, int userId, BigDecimal money);

	/**
	 * 获得某一条记录
	 */
	public RechargeRecord getOneById(int id);
	
	/**
	 * 获取最近10条记录
	 */
	
}
