package com.freenet.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.freenet.domain.Content;
import com.freenet.domain.WithDraw;

public interface WithDrawService {

	// 添加一条提现记录
	int insertWithDraw(int userId, String drawMoney, String zfbNumber, String mobile, String userName);

	// 查询某个用户的提现记录
	List<WithDraw> selectWithDrawByUserId(int userId);

	Map<List<WithDraw>, Integer> selectWithDrawAndTatolPageByPage(int userId, int pageindex);

	Map<List<WithDraw>, Integer> selectWithDrawAndTatolPageByPageDate(int userId, Date startDate, Date endDate,int pageindex);

	// 分页查询用户的提现申请
	List<WithDraw> selectWithDrawByPage(int startPos, int pageSize);

	int selectWithDrawByPageCount();

	void showWithDrawByPage(Model model, String pageNow);

	// 更新用户提现的状态
	int updateWithDrawStatus(int id, int type);

	// 根据id查询提现记录
	WithDraw selectWithDrawById(int id);

	// 查询某条提现记录
	List<WithDraw> selectByKeyword(String keyword);

	// 分页查询不同状态的提现记录
	List<WithDraw> selectWithDrawStatusByPage(int startPos, int pageSize, int status);

	int selectWithDrawStatusByPageCount(int status);

	void showWithDrawStatusByPage(Model model, String pageNow, int status);

	// 删除某条提现记录
	// int deleteWithDrawById(int id);

	// 添加个人消息
	int insertContent(int userId, String message, int type);

	// 查询某个用户的所有消息
	List<Content> selectContentByUserId(int userId);

	// 分夜查询我的消息
	List<Content> selectContentByPage(int startPos, int pageSize, int userId);

	int seletContentByPageCount(int userId);

	void showContentByPage(Model model, String pageNow, int userId);

	// 批量删除我的消息
	void deleteMore(List<String> delList);

	// 根据类型分页查询我的消息
	List<Content> selectContentTypeByPage(int startPos, int pageSize, int userId, int type);

	int seletContentTypeByPageCount(int userId, int type);

	void showContentTypeByPage(Model model, String pageNow, int userId, int type);

}
