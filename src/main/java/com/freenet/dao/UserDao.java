package com.freenet.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.freenet.domain.Auth;
import com.freenet.domain.Bank;
import com.freenet.domain.LoginHistroy;
import com.freenet.domain.Money;
import com.freenet.domain.User;

public interface UserDao {
	
	//添加一个用户
	int insertUser(User user);
	
	//根据账号查找用户
	User selectByUsername(@Param("username") String username);
	
	//用户登录(等于查询)
	User login(@Param("username") String username,@Param("password") String password);
	
	//根据账号修改密码
	int updatePwd(@Param("username") String username,@Param("password") String password);
	
	//根据userId查询用户
	User selectByUserId(@Param("userId") int userId);
	
	//分页查询用户
	List<User> selectUserByPage(@Param("startPos") int startPos,@Param("pageSize") int pageSize);
	int selectByPageCount();
	
	//分页查询不同实名状态的用户
	List<User> selectUserAuthByPage(@Param("startPos") int startPos,@Param("pageSize") int pageSize,@Param("authStatus") int authStatus);
	int seletUserAuthByPageCount(@Param("authStatus") int authStatus);
	
	//分页查询账号状态的用户
	List<User> selectUserStatusByPage(@Param("startPos") int startPos,@Param("pageSize") int pageSize,@Param("status") int status);
	int seletUserStatusByPageCount(@Param("status") int status);
	
	//手机账号模糊查询
	List<User> selectByKeyWord(@Param("username") String username);
	
	//冻结某用户
	int updateStatus(@Param("userId") int userId);
	
	//解冻某用户
	int updateStatusNormal(@Param("userId") int userId);
	
	//修改用户的实名状态
	int updateAuthStatus(@Param("userId") int userId,@Param("authStatus") int authStatus);
	
	//修改登录密码操作
	int updateLoginPwd(@Param("userId") int userId,@Param("password") String password);
	
	//修改资金密码操作
	int updateDealPwd(@Param("userId") int userId,@Param("dealPwd") String dealPwd);
	
	//修改用户邮箱
	void updateEmail(@Param("userId") int userId,@Param("email") String email);
	
	//查询是否实名认证
	Auth selectAuthByUserId(@Param("userId") int userId);
	
	//添加用户的实名注册表格
	int insertUserAuth(Auth auth);
	
	//修改实名认证信息
	int updateAuth(@Param("auth") Auth auth);
	
	//添加安全记录表格
	int insertloginHistroy(LoginHistroy loginHistroy);
	
	//根据userId查询安全记录
	List<LoginHistroy> selectHistroyByUserId(@Param("userId") int userId);
	
	//添加银行卡信息表格
	int insertBank(Bank bank);
	
	//根据userId查询银行地址表格信息
	Bank selectBankByUserId(@Param("userId") int userId);
	
	//删除银行地址信息
	int deleteBank(@Param("bankId") int bankId);
	
	//添加用户的账户资金
	int insertMoney(Money money);
	
	//根据userId查询用户的账户资金
	Money selectMoneyByUserId(@Param("userId") int userId);
	
	//根据userId更新用户账户余额
	int updateMoneyByUserId(@Param("userId") int userId,@Param("money") BigDecimal money);
	
	

}
