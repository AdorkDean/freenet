package com.freenet.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.freenet.domain.RechargeRecord;

public interface RechargeRecordDao {
	/**
	 * 获得充值表的所有记录总数
	 */

	public int M_getAllCount(@Param("status")Integer status, @Param("payment")String payment);

	/**
	 * 管理员查询记录列表按照记录的状态 (分页)
	 */
	public List<RechargeRecord> M_getAllRecordByPage(@Param("status")Integer status, @Param("payment")String payment, @Param("start")int start, @Param("end")int end);

	/**
	 * 获得充值记录总数
	 */
	public int getAllCount(Integer userId);

	/**
	 * 获得充值记录(时间段)
	 */
	public int getAllCountByDate(Integer userId, Date startDate, Date endDate);

	/**
	 * 获得特定一条记录
	 */
	public RechargeRecord getOne(Integer id);

	/**
	 * 用户充值
	 */
	public void insert_Recharge(RechargeRecord record);

	/**
	 * 修改充值记录状态
	 */
	public void update_Recharge(RechargeRecord record);

	/**
	 * 用户查询充值记录
	 */
	public List<RechargeRecord> getAllRecord(Integer userid);

	/**
	 * 用户查询记录列表（分页）
	 */
	public List<RechargeRecord> getAllRecordByPage(Integer userid, int start, int end);

	/**
	 * 用户查询某时间段的充值记录
	 */
	public List<RechargeRecord> getRecordByDate(Integer userId, Date startDate, Date endDate);

	/**
	 * 用户查询某时间段的充值记录(分页)
	 */
	public List<RechargeRecord> getRecordByDatePage(Integer userId, Date startDate, Date endDate, int start, int end);

	/**
	 * 查询到账记录
	 */
	public List<RechargeRecord> getRecordEnd(Integer userId);

	/**
	 * 查询某时间段到账记录
	 */
	public List<RechargeRecord> getRecordEndByDate(Integer userId, Date startDate, Date endDate);

	/**
	 * 改变充值记录状态
	 */
	public void updateStatus(Integer id, int status);

	/**
	 * 修改充值记录余额
	 */
	public void updateRecord(Integer id, BigDecimal money);
	/**
	 * 设置到账时间
	 */
	public void updateArriveDate(Integer id,Date date);

}
