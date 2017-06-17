package com.freenet.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.ui.Model;

import com.freenet.domain.Auth;
import com.freenet.domain.Bank;
import com.freenet.domain.LineOrder;
import com.freenet.domain.LoginHistroy;
import com.freenet.domain.Money;
import com.freenet.domain.User;

public interface UserService {

	// 添加一个用户
	int insertUser(String username, String password,int type);

	// 根据账号查找用户
	User selectByUsername(String username);

	// 用户登录(等于查询)
	User login(String username, String pwd);

	// 根据手机账号修改密码
	int updatePwd(String username, String pwd);

	// 根据userId查询用户
	User selectByUserId(int userId);

	// 分页查询
	List<User> selectUserByPage(int startPos, int pageSize);

	int selectByPageCount();

	void showUserByPage(Model model, String pageNow);

	// 分页查询不同实名状态的用户
	List<User> selectUserAuthByPage(int startPos, int pageSize, int authStatus);

	int seletUserAuthByPageCount(int authStatus);

	void showUserAuthByPage(Model model, String pageNow, int authStatus);

	// 分页查询账号状态的用户
	List<User> selectUserStatusByPage(int startPos, int pageSize, int Status);

	int seletUserStatusByPageCount(int status);

	void showUserStatusByPage(Model model, String pageNow, int status);

	// 手机账号模糊查询
	List<User> selectByKeyWord(String username);

	// 冻结某用户
	int updateStatus(int userId);

	// 解冻某用户
	int updateStatusNormal(int userId);

	// 修改用户的实名认证状态
	int updateAuthStatus(int userId, int authStatus);

	// 修改登录密码操作
	int updateLoginPwd(int userId, String password);

	// 修改资金密码操作
	int updateDealPwd(int userId, String dealPwd);

	// 查询是否实名认证
	Auth selectAuthByUserId(int userId);

	// 添加用户的实名注册表格
	int insertUserAuth(int userId, String name,int sex, int cardType, String card, String photoFront, String photoBack,
			String photoAll);

	// 修改实名注册信息
	int updateAuth(int userId, String name, int sex, int cardType, String card, String photoFront, String photoBack,
			String photoAll);

	/**
	 * 发送给用户绑定邮件
	 */
	void sendMail(User user,String email);
	
	/**
	 *验证用户的邮件是否正确 
	 */
	boolean verifyMail(User user ,String uniqueSign,String email);
	
	// 添加安全记录表格
	int insertloginHistroy(int userId);

	// 根据userId查询安全记录
	List<LoginHistroy> selectHistroyByUserId(int userId);
	
	//添加银行卡信息表格
	int insertBank(int userId,String name,String zfbNumber,String phone,String province,String area);
	
	//根据userId查询银行地址表格信息
	Bank selectBankByUserId(int userId);

	// 删除银行地址信息
	int deleteBank(int bankId);

	// 添加用户的账户资金
	int insertMoney(int userId);

	// 根据userId查询用户的账户资金
	Money selectMoneyByUserId(int userId);

	// 根据userId更新用户账户余额
	int updateMoneyByUserId(int userId, BigDecimal drawMoney, String money);

	// 根据userId更新用户账户余额
	int updateMoneyByUserId_add(int userId, BigDecimal drawMoney, BigDecimal money);
	
	//用户购买用户业务逻辑
	/**
	 * 
	 * @param id  购买者id
	 * @param lineOrder 订单
	 * @param price  价格
	 */
	void updateBuyContent(Integer id,LineOrder lineOrder,BigDecimal price);
	
	
	//管理员添加用户的money
	int insertMoneyByManager(int userId,String money);
}
