package com.freenet.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.freenet.domain.Content;
import com.freenet.domain.WithDraw;

public interface WithDrawDao {

	// 添加一条提现记录
	int insertWithDraw(WithDraw withDraw);

	// 查询某个用户的提现记录
	List<WithDraw> selectWithDrawByUserId(@Param("userId") int userId);

	List<WithDraw> selectWithDrawByUserIdPage(@Param("userId") int userId, @Param("startPos") int startPos,
			@Param("pageSize") int pageSize);

	int selectAllCountByDate(@Param("userId") int userId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	List<WithDraw> selectWithDrawByUserIdPageDate(@Param("userId") int userId, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("startPos") int startPos, @Param("pageSize") int pageSize);

	// 分页查询用户的提现申请
	List<WithDraw> selectWithDrawByPage(@Param("startPos") int startPos, @Param("pageSize") int pageSize);

	int selectWithDrawByPageCount();

	// 更新用户提现的状态
	int updateWithDrawStatus(@Param("status") int status, @Param("id") int id);

	// 根据id查询提现记录
	WithDraw selectWithDrawById(@Param("id") int id);

	// 查询某条提现记录
	List<WithDraw> selectByKeyword(@Param("keyword") String keyword);

	// 分页查询不同状态的提现记录
	List<WithDraw> selectWithDrawStatusByPage(@Param("startPos") int startPos, @Param("pageSize") int pageSize,
			@Param("status") int status);

	int selectWithDrawStatusByPageCount(@Param("status") int status);

	// 删除某条提现记录
	// int deleteWithDrawById(@Param("id") int id);

	// 添加个人消息
	int insertContent(Content content);

	// 查询某个用户的所有消息
	List<Content> selectContentByUserId(@Param("userId") int userId);

	// 分页查询我的消息
	List<Content> selectContentByPage(@Param("startPos") int startPos, @Param("pageSize") int pageSize,
			@Param("userId") int userId);

	int seletContentByPageCount(@Param("userId") int userId);

	// 批量删除我的消息
	void deleteMore(List<String> delList);

	// 根据类型分页查询我的消息
	List<Content> selectContentTypeByPage(@Param("startPos") int startPos, @Param("pageSize") int pageSize,
			@Param("userId") int userId, @Param("type") int type);

	int seletContentTypeByPageCount(@Param("userId") int userId, @Param("type") int type);

}
