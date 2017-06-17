package com.freenet.service.Impl;


import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.freenet.common.K;
import com.freenet.common.MailContent;
import com.freenet.common.Page;
import com.freenet.common.V;
import com.freenet.dao.UserDao;
import com.freenet.domain.Auth;
import com.freenet.domain.Bank;
import com.freenet.domain.LineOrder;
import com.freenet.domain.LoginHistroy;
import com.freenet.domain.Money;
import com.freenet.domain.User;
import com.freenet.service.UserService;
import com.freenet.service.mail.MailService;
import com.freenet.tools.FreeUtils;
import com.freenet.tools.MailTools;
import com.freenet.tools.PwdTools;

@Service
public class UserServiceImpl implements UserService{
	
	@Resource 
	private UserDao userDao;

	//添加一个用户(注册)
	public int insertUser(String username,String password,int type) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(PwdTools.ecodingPwd(password, K.md5));
		user.setStatus(V.user_status_normal); //正常状态
		if(type==1){//管理员添加的用户直接通过实名认证
			user.setAuthStatus(V.user_authStatus_yes); //未认证状态
		}else{
			user.setAuthStatus(V.user_authStatus_no); //未认证状态
		}
		user.setCdt(new Date());
		return userDao.insertUser(user);
		
	}


	//根据账号查找用户
	public User selectByUsername(String username) {
		return userDao.selectByUsername(username);
	}


	//用户登录(等于查询)
	public User login(String username, String pwd) {
		String password = PwdTools.ecodingPwd(pwd, K.md5);
		return userDao.login(username, password);
	}


	//根据手机账号修改密码
	public int updatePwd(String username, String pwd) {
		String password = PwdTools.ecodingPwd(pwd, K.md5);
		return userDao.updatePwd(username, password);
	}
	
	
	//根据userId查询用户
	public User selectByUserId(int userId) {
		return userDao.selectByUserId(userId);
	}

	
	/**
	 * 分页查询用户
	 */
	public List<User> selectUserByPage(int startPos, int pageSize) {
		return userDao.selectUserByPage(startPos, pageSize);
	}
	public int selectByPageCount() {
		return userDao.selectByPageCount();
	}
	
	public void showUserByPage(Model model,String pageNow){
		Page page = null;
		List<User> products = new ArrayList<User>();	
		int totalCount = selectByPageCount(); 	
		if (pageNow != null) {  
			page = new Page(totalCount, Integer.parseInt(pageNow));  
			products = this.selectUserByPage(page.getStartPos(), page.getPageSize());  
		} else {  
			page = new Page(totalCount, 1);  
		    products = this.selectUserByPage(page.getStartPos(), page.getPageSize());  
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
	}
	//结束分页
	
	
	/**
	 * 分页查询不同实名状态的用户
	 */
	public List<User> selectUserAuthByPage(int startPos, int pageSize, int authStatus) {
		return userDao.selectUserAuthByPage(startPos, pageSize, authStatus);
	}
	public int seletUserAuthByPageCount(int authStatus) {
		return userDao.seletUserAuthByPageCount(authStatus);
	}
	
	public void showUserAuthByPage(Model model, String pageNow,int authStatus) {
		Page page = null;
		List<User> products = new ArrayList<User>();
		int totalCount = selectByPageCount(); 	
		if (pageNow != null) {  
		    page = new Page(totalCount, Integer.parseInt(pageNow));  
		    products = this.selectUserAuthByPage(page.getStartPos(), page.getPageSize(),authStatus);  
		} else {  
			page = new Page(totalCount, 1);  
		    products = this.selectUserAuthByPage(page.getStartPos(), page.getPageSize(),authStatus);  
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
	}
	//结束分页
	
	/**
	 * 分页查询账号状态的用户
	 */
	public List<User> selectUserStatusByPage(int startPos, int pageSize, int status) {
		return userDao.selectUserStatusByPage(startPos, pageSize, status);
	}
	public int seletUserStatusByPageCount(int status) {
		return userDao.seletUserStatusByPageCount(status);
	}
	public void showUserStatusByPage(Model model, String pageNow, int status) {
		Page page = null;
		List<User> products = new ArrayList<User>();
		int totalCount = selectByPageCount(); 	
		if (pageNow != null) {  
		    page = new Page(totalCount, Integer.parseInt(pageNow));  
		    products = this.selectUserStatusByPage(page.getStartPos(), page.getPageSize(),status);  
		} else {  
			page = new Page(totalCount, 1);  
		    products = this.selectUserStatusByPage(page.getStartPos(), page.getPageSize(),status);  
		}
		model.addAttribute(K.list, products);
		model.addAttribute(K.page, page);
	}
	//结束分页

	//手机账号模糊查询
	public List<User> selectByKeyWord(String username) {
		return userDao.selectByKeyWord(username);
	}

	
	


	//冻结某用户
	public int updateStatus(int userId) {
		return userDao.updateStatus(userId);
	}


	//解冻某用户
	public int updateStatusNormal(int userId) {
		return userDao.updateStatusNormal(userId);
	}
	
	
	//修改用户的实名认证状态
	public int updateAuthStatus(int userId, int authStatus) {
		return userDao.updateAuthStatus(userId, authStatus);
	}
	
	//修改登录密码操作
	public int updateLoginPwd(int userId, String password){
		String nowPwd = PwdTools.ecodingPwd(password, K.md5);
		return userDao.updateLoginPwd(userId, nowPwd);
	}
	
	//修改资金密码操作
	public int updateDealPwd(int userId, String dealPwd) {
		String dealPwdNow = PwdTools.ecodingPwd(dealPwd, K.md5);
		return userDao.updateDealPwd(userId, dealPwdNow);
	}



	//查询用户的实名信息
	public Auth selectAuthByUserId(int userId) {
		return userDao.selectAuthByUserId(userId);
	}


	//添加用户的实名注册
	public int insertUserAuth(int userId, String name, int sex, int cardType, String card, String photoFront,
			String photoBack, String photoAll) {
		Auth auth = new Auth();
		auth.setUserId(userId);
		auth.setName(name);
		auth.setSex(sex);
		auth.setCardType(cardType);
		auth.setCard(card);
		auth.setPhotoFront(photoFront);
		auth.setPhotoBack(photoBack);
		auth.setPhotoAll(photoAll);
		auth.setCdt(new Date());
		return userDao.insertUserAuth(auth);
	}
	
	//修改实名注册信息
	public int updateAuth(int userId,String name, int sex, int cardType, String card, String photoFront, String photoBack,
			String photoAll) {
		Auth auth = new Auth();
		auth.setUserId(userId);
		auth.setName(name);
		auth.setSex(sex);
		auth.setCardType(cardType);
		auth.setCard(card);
		auth.setPhotoFront(photoFront);
		auth.setPhotoBack(photoBack);
		auth.setPhotoAll(photoAll);
		auth.setCdt(new Date());
		return userDao.updateAuth(auth);
	}


	//添加安全记录
	public int insertloginHistroy(int userId){
		InetAddress netAddress = FreeUtils.getInetAddress();
		LoginHistroy lh = new LoginHistroy();
		lh.setUserId(userId);
		lh.setLoginTime(new Date());
		lh.setLoginIp(FreeUtils.getHostIp(netAddress));
		return userDao.insertloginHistroy(lh);
	}


	//查询某用户的安全记录
	public List<LoginHistroy> selectHistroyByUserId(int userId) {
		return userDao.selectHistroyByUserId(userId);
	}


	//添加银行卡信息表格
	public int insertBank(int userId,String name, String zfbNumber, String phone, String province, String area) {
		Bank bank = new Bank();
		bank.setUserId(userId);
		bank.setName(name);
		bank.setZfbNumber(zfbNumber);
		bank.setPhone(phone);
		bank.setProvince(province);
		bank.setArea(area);
		bank.setCdt(new Date());
		return userDao.insertBank(bank);
	}


	//根据userId查询银行地址表格信息
	public Bank selectBankByUserId(int userId) {
		return userDao.selectBankByUserId(userId);
	}


	//删除银行地址信息
	public int deleteBank(int bankId) {
		return userDao.deleteBank(bankId);
	}


	//添加用户的账户资金
	public int insertMoney(int userId) {
		Money money = new Money();
		money.setUserId(userId);
		money.setMoney(new BigDecimal("0"));
		return userDao.insertMoney(money);
	}


	//根据userId查询用户的账户资金
	public Money selectMoneyByUserId(int userId) {
		return userDao.selectMoneyByUserId(userId);
	}


	//更新用户的账户余额
	public int updateMoneyByUserId(int userId,BigDecimal drawMoney,String money) {
		BigDecimal bd = new BigDecimal(money);
		BigDecimal nowMoney = drawMoney.subtract(bd);
		return userDao.updateMoneyByUserId(userId,nowMoney);
	}


	public int updateMoneyByUserId_add(int userId, BigDecimal drawMoney, BigDecimal money) {
		BigDecimal nowMoney = drawMoney.add(money);
		return userDao.updateMoneyByUserId(userId,nowMoney);
	}

	@Override
	public void updateBuyContent(Integer id, LineOrder lineOrder, BigDecimal price) {
		int sellUserId = lineOrder.getSellUserId();
		BigDecimal buyaccount = selectMoneyByUserId(id).getMoney();
		
		BigDecimal sellaccount = selectMoneyByUserId(sellUserId).getMoney();
		// 扣除购买者账户相应的钱
		updateMoneyByUserId(id, buyaccount, price.toString());
		//增加出售者账户的钱
		updateMoneyByUserId_add(sellUserId,sellaccount,price);
		
	}


	@Override
	public void sendMail(User user,String email) {
		String toSuccessUrl = "http://localhost:8080/freenet/user/safe/verify?uniqueSign=";
		//生成随机uuid数据
		String  uuid = UUID.randomUUID().toString();
		//存入邮箱队列
		MailService.putEmailVerification(uuid,email );
		toSuccessUrl+=uuid;
		//设置邮箱对象
		MailContent content = new MailContent(email, user.getUsername(), toSuccessUrl);
		try {
			MailTools.send_template(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public boolean verifyMail(User user, String uniqueSign,String email) {
		String veriyemail = MailService.getEmailVerification(uniqueSign);
		if(veriyemail==null){
			return false;
		}else if(veriyemail.equals(email)){
			userDao.updateEmail(user.getUserId(), veriyemail);
			return true;
		}else{
			return false;
		}
	}

	//管理员添加用户的money
	public int insertMoneyByManager(int userId, String money) {
		Money my = new Money();
		my.setUserId(userId);
		my.setMoney(new BigDecimal(money));
		return userDao.insertMoney(my);
	}


}
