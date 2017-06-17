package com.freenet.ctrl;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.freenet.common.ArticleType;
import com.freenet.common.Jsp;
import com.freenet.common.K;
import com.freenet.common.Order;
import com.freenet.common.Text;
import com.freenet.common.V;
import com.freenet.conf.AliyunOssConf;
import com.freenet.domain.Coin;
import com.freenet.domain.LineOrder;
import com.freenet.domain.Manager;
import com.freenet.domain.OffOrder;
import com.freenet.domain.RechargeRecord;
import com.freenet.domain.WebInfo;
import com.freenet.domain.WithDraw;
import com.freenet.domain.Allocation;
import com.freenet.domain.Article;
import com.freenet.domain.Auth;
import com.freenet.domain.User;
import com.freenet.domain.Wallet;
import com.freenet.service.AliyunOssService;
import com.freenet.service.ArticleService;
import com.freenet.service.CoinService;
import com.freenet.service.LineOrderService;
import com.freenet.service.ManagerService;
import com.freenet.service.OffOrderService;
import com.freenet.service.RechargeRecordService;
import com.freenet.service.UserService;
import com.freenet.service.WalletService;
import com.freenet.service.WebInfoService;
import com.freenet.service.WithDrawService;
import com.freenet.tools.FileUploadTools;
import com.freenet.tools.FreeUtils;
import com.freenet.tools.PwdTools;
import com.freenet.tools.StringSub;

@Controller
@RequestMapping(value = "/manager")
public class ManagerCtrl {

	Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	private ManagerService managerService;

	@Resource
	private UserService userService;

	@Resource
	private CoinService coinService;

	@Resource
	private ArticleService articleService;

	@Resource
	private WebInfoService webInfoService;

	@Resource
	private WithDrawService withDrawService;

	@Resource
	private RechargeRecordService rechargeRecordService;
	
	@Resource
	private LineOrderService lineOrderService;
	
	@Resource
	private OffOrderService offOrderService;
	
	@Resource
	private AliyunOssService aliyunOssService;

	@Resource
	private AliyunOssConf aliyunOssConf;
	
	@Resource
	private WalletService walletService;

	// 首页登录
	@RequestMapping(value = "/toLogin", method = RequestMethod.GET)
	public String to_Login() {
		return Jsp.manager_login;
	}

	// 管理员登录操作
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpServletRequest req, String username, String password) {
		Manager manager = managerService.login(username, password);
		if (manager == null) {
			model.addAttribute(K.msg, "账号或密码错误!");
			return Jsp.manager_login;
		} else {
			// 创建session
			createSession(req, manager);
			return Jsp.manager_index;
		}
	}

	// 退出登录操作
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest req) {
		// 清除session
		req.getSession().removeAttribute(K.sessionManager);
		return Jsp.manager_login;
	}
	
	/**
	 * ================================================================================
	 * 会员管理===========================================================================
	 * ================================================================================
	 */

	// 会员列表页面
	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public String user_list(Model model, String pageNow) {
		userService.showUserByPage(model, pageNow);
		String url = "manager/user/list?pageNow=";
		model.addAttribute(K.url, url);
		return Jsp.manager_userList;
	}

	// 根据实名状态查询用户
	@RequestMapping(value = "/user/authStatusSearch", method = RequestMethod.GET)
	public String user_authStatusSearch(Model model, String pageNow, int authStatus) {
		userService.showUserAuthByPage(model, pageNow, authStatus);
		String url = "manager/user/authStatusSearch?authStatus=" + authStatus + "&pageNow=";
		model.addAttribute(K.url, url);
		return Jsp.manager_userList;
	}

	// 根据账号状态查询用户
	@RequestMapping(value = "/user/statusSearch", method = RequestMethod.GET)
	public String user_statusSearch(Model model, String pageNow, int status) {
		userService.showUserStatusByPage(model, pageNow, status);
		String url = "manager/user/statusSearch?status=" + status + "&pageNow=";
		model.addAttribute(K.url, url);
		return Jsp.manager_userList;
	}

	// 根据手机账号查询用户
	@RequestMapping(value = "/user/usernameSearch", method = RequestMethod.GET)
	public String user_usernameSearch(Model model, String pageNow, String username) {
		List<User> list = userService.selectByKeyWord(username);
		// String url =
		// "manager/user/usernameSearch?username="+username+"&pageNow=1";
		model.addAttribute(K.list, list);
		// model.addAttribute(K.url, url);
		return Jsp.manager_userList;
	}

	// 用户详情信息页面
	@RequestMapping(value = "/user/Edit", method = RequestMethod.GET)
	public String user_Edit(Model model, int userId) {
		User user = userService.selectByUserId(userId);
		model.addAttribute(K.obj, user);
		return Jsp.manager_userEdit;

	}

	// 用户实名认证信息
	@RequestMapping(value = "/user/toAuth", method = RequestMethod.GET)
	public String user_toAuth(Model model, int userId) {
		User user = userService.selectByUserId(userId);
		if (user.getAuthStatus() == 2) {
			model.addAttribute("auth", null);
			model.addAttribute(K.obj, user);
		} else {
			Auth auth = userService.selectAuthByUserId(userId);
			model.addAttribute("auth", auth);
			model.addAttribute(K.obj, user);
		}
		return Jsp.manager_userAuth;
	}

	// 通过实名认证操作
	@RequestMapping(value = "/user/authAgree", method = RequestMethod.GET)
	public String user_authAgree(int userId) {
		// 通过
		userService.updateAuthStatus(userId, V.user_authStatus_yes);
		withDrawService.insertContent(userId,Text.auth_yes, V.content_type_auth);
		return "redirect:/manager/user/toAuth?userId=" + userId;
	}

	// 不通过实名认证操作
	@RequestMapping(value = "/user/authPass", method = RequestMethod.GET)
	public String user_authPass(int userId) {
		// 不通过
		userService.updateAuthStatus(userId, V.user_authStatus_pass);
		withDrawService.insertContent(userId,Text.auth_no, V.content_type_auth);
		return "redirect:/manager/user/toAuth?userId=" + userId;
	}

	// 添加会员页面
	@RequestMapping(value = "/user/toAdd", method = RequestMethod.GET)
	public String user_toAdd(Model model) {
		return Jsp.manager_userAdd;
	}

	//添加一个会员操作
	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public String user_save(Model model, String username, String password, String repassword,
			String authName,int sex,int cardType,String card,String zfbNumber,String money,String coin,
			String frontImg, String backImg, String allImg,
			@RequestParam("photoFront") CommonsMultipartFile photoFront,
			@RequestParam("photoBack") CommonsMultipartFile photoBack,
			@RequestParam("photoAll") CommonsMultipartFile photoAll) {
		// 判断密码是否一致
		if (!PwdTools.ecodingPwd(password, K.md5).equals(PwdTools.ecodingPwd(repassword, K.md5))) {
			model.addAttribute(K.msg, "两次密码不一致!");
			return Jsp.manager_userAdd;
		}
		// 判断手机账号是否存在
		if(userService.selectByUsername(username)!= null) {
			model.addAttribute(K.msg, "手机账号已存在!");
			return Jsp.manager_userAdd;
		}
		// 上传图片
		if (!photoFront.isEmpty()) {
			String url = uploadFile(photoFront);
			frontImg = url;
		}
		if (!photoBack.isEmpty()) {
			String url = uploadFile(photoBack);
			backImg = url;
		}
		if (!photoAll.isEmpty()) {
			String url = uploadFile(photoAll);
			allImg = url;
		}
		//添加相关的信息
		userService.insertUser(username, password,1);
		User user = userService.selectByUsername(username);
		//添加实名认证信息(直接通过)
		userService.insertUserAuth(user.getUserId(),authName, sex, cardType, card, frontImg, backImg, allImg);
		//添加个人账户
		userService.insertMoneyByManager(user.getUserId(),money);
		//添加收款账号
		userService.insertBank(user.getUserId(),authName, zfbNumber, "", "", "");
		// 个人钱包
		List<Coin> list = coinService.selectAll();
		for (int i = 0; i < list.size(); i++) {
			walletService.insertWalletByManager(user.getUserId(), list.get(i).getCoinId(),coin);
		}
		return "redirect:/manager/user/list";
	}

	// 冻结某用户操作
	@RequestMapping(value = "/user/freeze", method = RequestMethod.GET)
	public String user_freeze(Model mode, int userId, String url) {
		String newUrl = url.substring(url.indexOf("http"), url.indexOf("manager") - 1);
		url = url.replaceAll(newUrl, "");
		// url = url.replaceAll("")
		int count = userService.updateStatus(userId);
		// 判断是否更新成功
		if (count < 1) {
			return null;
		} else {// 更新成功
			return "redirect:" + url;
		}
	}

	// 解冻某用户
	@RequestMapping(value = "/user/normal", method = RequestMethod.GET)
	public String user_normal(Model model, int userId, String url) {
		System.out.println(url);
		String newUrl = url.substring(url.indexOf("http"), url.indexOf("manager") - 1);
		System.out.println(newUrl);
		url = url.replaceAll(newUrl, "");
		int count = userService.updateStatusNormal(userId);
		if (count < 1) {
			return null;
		} else {
			return "redirect:" + url;
		}
	}
	
	/**
	 * ===============================================================================
	 * 交易管理==========================================================================
	 * ===============================================================================
	 */
	
	//线上订单列表
	@RequestMapping(value="/order/online",method=RequestMethod.GET)
	public String order_online(Model model,String pageNow){
		lineOrderService.showLineOrderByPage(model,pageNow);
		String url = "manager/order/online?pageNow=";
		model.addAttribute(K.url, url);
		return Jsp.manager_lineOrder;
	}
	
	//已完成的线上订单详情
	@RequestMapping(value="/order/online/edit",method=RequestMethod.GET)
	public String order_online_edit(Model model,int id,HttpServletRequest req){
		LineOrder obj = lineOrderService.selectById(id);
		model.addAttribute(K.obj, obj);
		return Jsp.manager_lineOrderDetail;
	}
	
	//某个用户的线上所有订单
	@RequestMapping(value="/order/userOnline/list",method=RequestMethod.GET)
	public String order_userOnline_list(Model model,int sellUserId,HttpServletRequest req){
		List<LineOrder> list = lineOrderService.selectBySellUserId(sellUserId);
		model.addAttribute(K.list, list);
		return Jsp.manager_lineOrderList;
	}
	
	//不同状态的线上订单显示
	@RequestMapping(value="/order/onlineStatusSearch",method=RequestMethod.GET)
	public String order_onlineStatusSearch(Model model,int status,String pageNow){
		lineOrderService.showLineOrderStatusByPage(model, pageNow, status);
		String url = "manager/order/onlineStatusSearch?status="+status+"&pageNow=";
		model.addAttribute(K.url, url);
		return Jsp.manager_lineOrder;
	}
	
	//模糊查询某条线上订单
	@RequestMapping(value="/order/onlineKeyword",method=RequestMethod.POST)
	public String order_onlineKeyword(Model model,String keyword){
		List<LineOrder> list = lineOrderService.selectByKeyword(keyword);
		model.addAttribute(K.list, list);
		return Jsp.manager_lineOrder;
	}
	
	//线下订单页面
	@RequestMapping(value="/order/offline",method=RequestMethod.GET)
	public String order_offline(Model model,String pageNow){
		offOrderService.showOffOrderByPage(model, pageNow);
		String url = "manager/order/offline?pageNow=";
		model.addAttribute(K.url, url);
		return Jsp.manager_offOrder;
	}
	
	//查看某个线下订单的详细信息  不属于挂单中/已撤单
	@RequestMapping(value="/order/offline/edit",method=RequestMethod.GET)
	public String order_offline_edit(Model model,int id){
		OffOrder off = offOrderService.selectOffOrderById(id);
		model.addAttribute(K.obj, off);
		return Jsp.manager_offOrderDetail;
		
	}
	
	//查看某个用户的所有线下订单
	@RequestMapping(value="/order/userOffline/list",method=RequestMethod.GET)
	public String order_userOffline_list(Model model,int sellUserId){
		List<OffOrder> list = offOrderService.selectOffBySellUserId(sellUserId);
		model.addAttribute(K.list, list);
		return Jsp.manager_offOrderList;
	}
	
	//根据不同状态查询线下订单(分页)
	@RequestMapping(value="/order/offlineStatusSearch",method=RequestMethod.GET)
	public String order_offlineStatusSearch(Model model,int status,String pageNow){
		if(status!=9){//查询status
			offOrderService.showOffOrderStatusByPage(model, pageNow, status);
		}else{//查询平台是否介入
			offOrderService.showOffOrderIntervenByPage(model, pageNow);
		}
		String url = "manager/order/offlineStatusSearch?status="+status+"&pageNow=";
		model.addAttribute(K.url, url);
		return Jsp.manager_offOrder;
	}
	
	//模糊查询线下订单
	@RequestMapping(value="/order/offlineKeyword",method=RequestMethod.POST)
	public String order_offlineKeyword(Model model,String keyword){
		List<OffOrder> list = offOrderService.selectByKeyword(keyword);
		model.addAttribute(K.list, list);
		return Jsp.manager_offOrder;
	}
	
	//确定某方违约操作
	@RequestMapping(value="/user/offOrderBreach",method=RequestMethod.GET)
	public String user_offOrderBreach(Model model,int id,int type){
		//获取上一次请求链接
		OffOrder off = offOrderService.selectOffOrderById(id);
		BigDecimal sellCoin = off.getSellCoin();
		//获取抵押币
		List<Coin> coins = coinService.selectAll();
		BigDecimal coinCount = coins.get(0).getCoinCount();
		int coinId = coins.get(0).getCoinId();
		//获取卖方的冻结货币数量
		Wallet wall = walletService.select(off.getSellUserId(), coinId);
		BigDecimal coinFrozen = wall.getCoinFrozen();//卖方钱包中的冻结货币数量
		if(type==1){//确定为卖方违约
			//将卖方钱包减去当前订单冻结货币数量
			BigDecimal finalCoin = coinFrozen.subtract(coinCount.add(sellCoin));
			//将卖出的货币给买方
			//获取买方的钱包货币数量
			BigDecimal userCoin = walletService.select(off.getBuyUserId(), coinId).getCoin();
			//增加买方的货币
			walletService.updateCoin(off.getBuyUserId(), userCoin.add(sellCoin));
			//将卖方的冻结货币清零
			walletService.updateCoinFrozen(finalCoin, off.getSellUserId(), coinId);
			//增加卖方的个人消息
			String sellText = String.format(Text.offOrder_sellConfiscate,sellCoin,coinCount);
			withDrawService.insertContent(off.getSellUserId(), sellText, V.content_type_system);
			//增加买方个人消息
			String buyText = String.format(Text.offOrder_buySuccess, sellCoin);
			withDrawService.insertContent(off.getBuyUserId(), buyText,  V.content_type_system);
			//更新此订单的状态
			offOrderService.updateOffOrderStatus(Order.order_off_sellBreach,id);
			//更新订单的realPrice与realCdt
			offOrderService.updaterealPrice(id, sellCoin);
			//更新订单的平台介入状态
			offOrderService.updateIntervention(Order.order_off_sellIntervention_yes, id);//介入			
		}else{//确定为买方违约
			//将冻结的货币还给卖方
			BigDecimal retCoin = coinCount.add(sellCoin);//还给卖方的货币数量
			//获取卖方的钱包中货币量的总量
			BigDecimal coin = wall.getCoin();
			//开始归还货币
			BigDecimal finalCoin = coin.add(retCoin);
			//将冻结货币相减
			BigDecimal subCoin = coinFrozen.subtract(retCoin);//减去此订单中的冻结货币
			//更新冻结的货币
			walletService.updateCoinFrozen(subCoin, off.getSellUserId(), coinId);
			//更新钱包货币数量
			walletService.updateCoin(off.getSellUserId(), finalCoin);
			//更新此订单的状态
			offOrderService.updateOffOrderStatus(Order.order_off_buyBreach,id);
			//更新订单的平台介入状态
			offOrderService.updateIntervention(Order.order_off_sellIntervention_yes, id);//介入
			//将违约方买方进行账号冻结
			userService.updateStatus(off.getBuyUserId());
			//增加卖方个人消息
			String sellText = String.format(Text.offOrder_noMoney, sellCoin,coinCount);
			withDrawService.insertContent(off.getSellUserId(), sellText,V.content_type_system);
			//增加买方个人消息
			withDrawService.insertContent(off.getBuyUserId(),Text.offOrder_buyContract,V.content_type_system);	
			
		}
		return "redirect:/manager/order/offline";
	}
	
	
	
	
	
	//添加订单页面
	@RequestMapping(value="/order/toAdd",method=RequestMethod.GET)
	public String order_toAdd(Model model){
		return Jsp.manager_orderAdd;
	}
	
	//添加订单操作
	@RequestMapping(value="/order/save",method=RequestMethod.POST)
	public String order_save(Model model,String sellUsername,int addType,int orderType,String sellCoin,String price){
		User user = userService.selectByUsername(sellUsername);
		if(user==null){//检测账号是否存在
			model.addAttribute(K.msg, "请先注册此账号!");
			return Jsp.manager_orderAdd;
		}
		if(orderType==1){//线上订单
			lineOrderService.insertLineOrder(user.getUserId(), sellUsername,addType, sellCoin, price);
			return "redirect:/manager/order/online";
		}else{//线下订单
			offOrderService.insertOffOrder(user.getUserId(), sellUsername, addType, sellCoin, price);
			return "redirect:/manager/order/offline";
		}
	}
	

	/**
	 * =================================================================================
	 * 资金管理============================================================================
	 * =================================================================================
	 */
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//提现模块///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// 提现申请页面
	@RequestMapping(value = "/withDraw/list", method = RequestMethod.GET)
	public String withDraw_list(Model model, String pageNow) {
		withDrawService.showWithDrawByPage(model, pageNow);
		String url = "manager/withDraw/list?pageNow=";
		model.addAttribute(K.url, url);
		return Jsp.manager_withDraw;
	}
	
	//模糊查询提现记录
	@RequestMapping(value="/withDraw/findKeyWord",method=RequestMethod.POST)
	public String withDraw_findKeyWord(Model model,String keyword){
		List<WithDraw> list = withDrawService.selectByKeyword(keyword);
		model.addAttribute(K.list, list);
		return Jsp.manager_withDraw;
	}
	
	//根据审核状态查询提现记录
	@RequestMapping(value="/withDraw/statusSearch",method=RequestMethod.GET)
	public String withDraw_statusSearch(Model model,int WithStatus,String pageNow){
		withDrawService.showWithDrawStatusByPage(model, pageNow, WithStatus);
		String url = "manager/withDraw/statusSearch?WithStatus=" + WithStatus + "&pageNow=";
		model.addAttribute(K.url, url);
		return Jsp.manager_withDraw;
	}

	// 提现申请操作 通过/不通过
	@RequestMapping(value = "/withDraw/statusSet", method = RequestMethod.GET)
	public String withDraw_accept(Model model, int id, int type, String url){
		String newUrl = url.substring(url.indexOf("http"), url.indexOf("manager") - 1);
		url = url.replaceAll(newUrl, "");
		// 更新提现状态
		withDrawService.updateWithDrawStatus(id, type);

		WithDraw wd = withDrawService.selectWithDrawById(id);

		// 生成一条个人消息
		if (type == 1) {// 通过信息
			String message = String.format(Text.withDraw_text_yes, wd.getDrawMoney() + "");
			withDrawService.insertContent(wd.getUserId(), message, V.content_type_withDraw);
		} else {// 不同过信息
			String message = String.format(Text.withDraw_text_no, wd.getDrawMoney() + "");
			withDrawService.insertContent(wd.getUserId(), message, V.content_type_withDraw);
		}
		return "redirect:" + url;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//充值模块//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping("/recharge/toRecharge")
	public String toCheck(HttpServletRequest request, HttpServletResponse response, Model model) {
		int page = 1;
		Integer status = -1;
		String payment = "";
		if (request.getParameter("page") != null&&request.getParameter("page") != "") {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if (request.getParameter("status") != null&&request.getParameter("status") != "") {
			status = Integer.parseInt(request.getParameter("status"));
		}
		if (request.getParameter("payment") != null && request.getParameter("payment") != "") {
			payment = request.getParameter("payment");
		}
		Map<List<RechargeRecord>, Integer> map = rechargeRecordService.M_chooseGetAllRecord(status, payment, page);
		List<RechargeRecord> recordList = map.keySet().iterator().next();
		int totalPages = map.get(recordList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("recordList", recordList);
		model.addAttribute("page", page);
		model.addAttribute("status", status);
		model.addAttribute("payment", payment);
		return Jsp.manager_toRecharge;
	}

	// ajax分页
	@RequestMapping("/recharge/toRechargeByPage")
	public void toCheckByPage(@RequestParam Integer index, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 初始化返回内容
		String content = "";
		Integer status = -1;
		String payment = "";
		if (request.getParameter("status") != null&&request.getParameter("status") != "") {
			status = Integer.parseInt(request.getParameter("status"));
		}
		if (request.getParameter("payment") != null && request.getParameter("payment") != "") {
			payment = request.getParameter("payment");
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Map<List<RechargeRecord>, Integer> pageListPageMap = rechargeRecordService.M_chooseGetAllRecord(status, payment,
				index);
		List<RechargeRecord> RechargeRecordList = pageListPageMap.keySet().iterator().next();
		// 迭代添加元素
		for (RechargeRecord record : RechargeRecordList) {
			content += "<tr class=\"gradeX\"><td class=\"\">" + record.getWalletAddress() + "</td><td class=\"\">"
					+ record.getPayment() + "</td><td class=\"\">" + record.getRechargeQuantity() + "</td>";
			content += "<td class=\"\">" + dateFormat.format(record.getFromDate()) + "</td>";
			if (record.getStatus() == V.user_recharge_wait) {
				content += "<td class=\"\"><span class=\"label-warning\">已提交</span></td>";
				content += "<td><div class=\"tpl-table-black-operation\">";
				content += "<a id=\"" + (record.getId().intValue() + 100)
						+ "\" href=\"javascript:;\" class=\"shenhe\"> <i class=\"am-icon-pencil\"></i> 审核 </a>";
			} else if (record.getStatus() == V.user_recharge_check) {
				content += "<td class=\"\"><span class=\"label-yes\">审核中</span></td>";
				content += "<td><div class=\"tpl-table-black-operation\">";
				content += "<a id=\"" + (record.getId().intValue() + 100)
						+ "\" href=\"javascript:;\" class=\"wancheng\"> <i class=\"am-icon-pencil\"></i> 完成 </a> ";
				content += "<a id=\"" + (record.getId().intValue() + 100)
						+ "\" href=\"javascript:;\" class=\"tuihui\"> <i class=\"am-icon-pencil\"></i> 退回 </a>";
			} else if (record.getStatus() == V.user_recharge_yes) {
				content += "<td class=\"\"><span class=\"label-danger\">已完成</span></td>";
				content += "<td><div class=\"tpl-table-black-operation\">";
			} else {
				content += "<td class=\"\"><span class=\"label-not-pass\">未通过</span></td>";
				content += "<td><div class=\"tpl-table-black-operation\">";
			}
			content += "</div> </td> </tr>";

		}
		try {
			response.setContentType("text/xml;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/recharge/recharge")
	public String check(@RequestParam int page, HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer id) {
		Integer status = -1;
		String payment = "";
		if (request.getParameter("status") != null&&request.getParameter("status") != "") {
			status = Integer.parseInt(request.getParameter("status"));
		}
		if (request.getParameter("payment") != null && request.getParameter("payment") != "") {
			payment = request.getParameter("payment");
		}
		RechargeRecord record = rechargeRecordService.getOneById(id);
		rechargeRecordService.updateStatus(id, V.user_recharge_check);
		withDrawService.insertContent(record.getUserId(), Text.Recharge_text_check, V.content_type_recharge);
		return "redirect:/manager/recharge/toRecharge?page=" + page + "&status=" + status + "&payment=" + payment;
	}

	@RequestMapping("/recharge/complate")
	public String complate(@RequestParam int page, HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer id) {
		Integer status = -1;
		String payment = "";
		if (request.getParameter("status") != null&&request.getParameter("status") != "") {
			status = Integer.parseInt(request.getParameter("status"));
		}
		if (request.getParameter("payment") != null && request.getParameter("payment") != "") {
			payment = request.getParameter("payment");
		}
		RechargeRecord record = rechargeRecordService.getOneById(id);
		RechargeRecord rechargeRecord = rechargeRecordService.getOneById(id);
		// 充值金额
		BigDecimal money = rechargeRecord.getRechargeQuantity();
		rechargeRecordService.updatemoney_add(rechargeRecord.getId(), rechargeRecord.getUserId(), money);
		rechargeRecordService.updateStatus(id, V.user_recharge_yes);
		withDrawService.insertContent(record.getUserId(), String.format(Text.Recharge_text_yes, money.toString()),
				V.content_type_recharge);
		return "redirect:/manager/recharge/toRecharge?page=" + page + "&status=" + status + "&payment=" + payment;
	}

	@RequestMapping("/recharge/tuihui")
	public String tuihui(@RequestParam int page, HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer id) {
		Integer status = -1;
		String payment = "";
		if (request.getParameter("status") != null&&request.getParameter("status") != "") {
			status = Integer.parseInt(request.getParameter("status"));
		}
		if (request.getParameter("payment") != null && request.getParameter("payment") != "") {
			payment = request.getParameter("payment");
		}
		RechargeRecord record = rechargeRecordService.getOneById(id);
		BigDecimal money = record.getRechargeQuantity();
		rechargeRecordService.updateStatus(id, V.user_recharge_no);
		withDrawService.insertContent(record.getUserId(), String.format(Text.Recharge_text_no, money.toString()),
				V.content_type_recharge);
		return "redirect:/manager/recharge/toRecharge?page=" + page + "&status=" + status + "&payment=" + payment;
	}

	

	/*
	 * ====================================================================================
	 * 货币管理===============================================================================
	 * ====================================================================================
	 */
	
	// 货币列表
	@RequestMapping(value = "/coin/list", method = RequestMethod.GET)
	public String coin_list(Model model) {
		List<Coin> list = coinService.selectAll();
		int len = list.size();
		model.addAttribute(K.list, list);
		model.addAttribute("length", len);
		return Jsp.manager_coinList;
	}

	// 查看货币详情
	@RequestMapping(value = "/coin/toEdit", method = RequestMethod.GET)
	public String coin_toEdit(Model model, int coinId) {
		Coin coin = coinService.selectByCoinId(coinId);
		model.addAttribute(K.obj, coin);
		return Jsp.manager_coinSet;
	}

	// 新增货币页面
	@RequestMapping(value = "/coin/toCoinAdd", method = RequestMethod.GET)
	public String coin_toCoinAdd(Model model){
		return Jsp.manager_coinAdd;
	}
	
	//初始化货币
	@RequestMapping(value="/coin/initialize",method=RequestMethod.GET)
	public String coin_initialize(Model model){
		coinService.insert();
		return "redirect:/manager/coin/list";
	}

	// 添加货币类型操作
	@RequestMapping(value = "/coin/typeSave", method = RequestMethod.POST)
	public String coin_typeSave(Model model, String coinName, String coinRate,String coinCount) {
		coinService.insertCoin(coinName, coinRate,coinCount);
		return "redirect:/manager/coin/list";
	}

	// 修改货币信息操作
	@RequestMapping(value = "/coin/typeUpdate", method = RequestMethod.POST)
	public String coin_typeUpdate(Model model, int coinId, String coinRate,String coinCount) {
		coinService.updateByCoinId(coinRate, coinId,coinCount);
		return "redirect:/manager/coin/list";
	}
	

	/*
	 * =================================================================================
	 * 系统管理===========================================================================
	 * =================================================================================
	 */

	// 管理员列表页面
	@RequestMapping(value = "/work/list", method = RequestMethod.GET)
	public String work_list(Model model) {
		List<Manager> list = managerService.selectWork();
		model.addAttribute(K.list, list);
		return Jsp.manager_workList;
	}

	// 添加管理员页面
	@RequestMapping(value = "/work/toAdd", method = RequestMethod.GET)
	public String work_toAdd(Model model) {
		return Jsp.manager_workAdd;
	}

	// 添加管理员操作
	@RequestMapping(value = "/work/save", method = RequestMethod.POST)
	public String work_save(Model model, String managerName, String username, String password, String repassword) {
		if (!password.equals(repassword)) {// 检测密码是否一致
			model.addAttribute(K.msg, "两次密码不一致!");
			return Jsp.manager_workAdd;
		}
		if (managerService.selectByUserName(username) != null) {// 添加前检测手机账号是否存在
			model.addAttribute(K.msg, "手机账号已存在!");
			return Jsp.manager_workAdd;
		}

		managerService.insert(username, password, managerName);
		return "redirect:/manager/work/list";
	}

	// 删除某个管理员
	@RequestMapping(value = "/work/delete", method = RequestMethod.GET)
	public String work_delete(int id) {
		managerService.deleteById(id);
		return "redirect:/manager/work/list";
	}

	// 修改密码页面
	@RequestMapping(value = "/toPwdSet", method = RequestMethod.GET)
	public String toPwdSet() {
		return Jsp.manager_pwdSet;
	}

	// 修改密码操作
	@RequestMapping(value = "/pwdUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> pwdUpdate(HttpServletRequest req, Model model, int managerId, String oldPassword,
			String newPwd, String rePwd) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 首先检测两次密码是否相同
		if (!newPwd.equals(rePwd)) {
			result.put(K.result, false);
			result.put(K.msg, "两次密码不一致!");
			return result;
		}
		// 检测旧密码是否正确
		Manager obj = managerService.selectById(managerId);
		if (!obj.getPassword().equals(PwdTools.ecodingPwd(oldPassword, K.md5))) {
			result.put(K.result, false);
			result.put(K.msg, "旧密码不正确!");
			return result;
		}
		managerService.updatePwd(managerId, newPwd);
		result.put(K.result, true);
		result.put(K.msg, "修改成功,请重新登录!");
		req.getSession().removeAttribute(K.sessionManager);
		return result;
	}
	
	//收款配置页面
	@RequestMapping(value="/payAllocation",method=RequestMethod.GET)
	public String payAllocation(Model model){
		Allocation all = managerService.selectAllocationAll();
		model.addAttribute(K.obj, all);
		return Jsp.manager_payAllocation;
	}
	
	@RequestMapping(value="/payAllocation/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> payAllocation_save(Model model,int payId,String payZfb){
		Map<String, Object> result = new HashMap<String, Object>();
		if(payId==0){
			managerService.insertAllocation(payZfb);
			result.put(K.msg, "添加成功!");
			result.put(K.result, true);
			return result;
		}else{
			managerService.updateAllocation(payId, payZfb);
			result.put(K.msg, "修改成功!");
			result.put(K.result, true);
			return result;
		}
	}
	
	

	// 创建管理员的session
	private void createSession(HttpServletRequest req, Manager manager) {
		HttpSession session = req.getSession();
		session.setAttribute(K.sessionManager, manager);
		session.setMaxInactiveInterval(60 * 30);// 30分钟超时
	}

	
	/*
	 * ===========================================================================================
	 * 咨询管理======================================================================================
	 * ===========================================================================================
	 */

	@RequestMapping(value = "/*/add_article")
	public String insertArticle(@RequestParam String title, @RequestParam String author,
			@RequestParam String releaseDate, @RequestParam String content, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Calendar calendar = Calendar.getInstance();
		// 获得请求url字符串
		String reuquestUrl = request.getRequestURI();
		// 获得请求中文章的类型
		String reflactClassName = StringSub.getRequestSub(reuquestUrl, "manager/", "/add_article");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			calendar.setTime(dateFormat.parse(releaseDate));
			Date release = calendar.getTime();
			// 根据文章类型 动态获得对应的文章类
			Class<?> article1 = Class.forName("com.freenet.domain." + reflactClassName);
			// 获得构造器
			Constructor<?> constructor = article1
					.getDeclaredConstructor(new Class[] { String.class, String.class, Date.class, String.class });
			// 实例化该文章
			Article article = (Article) constructor.newInstance(new Object[] { title, author, release, content });
			articleService.insertArticle(article);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "redirect:/manager/" + reflactClassName + "/get_articleList";
	}

	@RequestMapping("/*/delete_article")
	public String deleteArticle(@RequestParam Integer id, HttpServletRequest request) {
		String reuquestUrl = request.getRequestURI();
		String reflactClassName = StringSub.getRequestSub(reuquestUrl, "manager/", "/delete_article");
		articleService.deleteArticle(id, reflactClassName);
		return "redirect:/manager/" + reflactClassName + "/get_articleList";
	}

	@RequestMapping("/*/toUpdate_article")
	public String toUpdateArticle(@RequestParam Integer id, Model model, HttpServletRequest request) {
		String reuquestUrl = request.getRequestURI();
		String reflactClassName = StringSub.getRequestSub(reuquestUrl, "manager/", "/toUpdate_article");
		Article article = articleService.getArticle(id, reflactClassName);
		model.addAttribute("article", article);
		if (reflactClassName.equals(ArticleType.MediaArticle)) {
			return Jsp.manager_addMedia;
		} else if (reflactClassName.equals(ArticleType.NoticeArticle)) {
			return Jsp.manager_addNotice;
		} else {
			return Jsp.manager_addAdvisory;
		}
	}

	@RequestMapping("/*/update_article")
	public String updateArticle(@RequestParam String title, @RequestParam String author,
			@RequestParam String releaseDate, @RequestParam Integer id, @RequestParam String content,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String reuquestUrl = request.getRequestURI();
		String reflactClassName = StringSub.getRequestSub(reuquestUrl, "manager/", "/update_article");
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			calendar.setTime(dateFormat.parse(releaseDate));
			Date release = calendar.getTime();
			Class<?> article1 = Class.forName("com.freenet.domain." + reflactClassName);
			Constructor<?> constructor = article1.getDeclaredConstructor(
					new Class[] { Integer.class, String.class, String.class, Date.class, String.class });
			Article article = (Article) constructor.newInstance(new Object[] { id, title, author, release, content });
			articleService.updateArticle(article);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "redirect:/manager/" + reflactClassName + "/get_articleList";
	}

	@RequestMapping("/*/get_article")
	public String getArticle(@RequestParam Integer id, Model model, HttpServletRequest request) {
		String reuquestUrl = request.getRequestURI();
		String reflactClassName = StringSub.getRequestSub(reuquestUrl, "manager/", "/get_article");
		Article article = articleService.getArticle(id, reflactClassName);
		model.addAttribute("article", article);
		if (reflactClassName.equals(ArticleType.MediaArticle)) {
			return Jsp.manager_webMedia;
		} else if (reflactClassName.equals(ArticleType.NoticeArticle)) {
			return Jsp.manager_webNotice;
		} else {
			return Jsp.manager_webAdvisory;
		}
	}

	@RequestMapping("/*/get_articleList")
	public String getArticleList(HttpServletRequest request, Model model) {
		// 默认当前页为第1页
		Integer page = 1;
		String reuquestUrl = request.getRequestURI();
		String reflactClassName = StringSub.getRequestSub(reuquestUrl, "manager/", "/get_articleList");
		Map<List<Article>, Integer> pageListPageMap = articleService.getArticleList(reflactClassName, page);
		List<Article> articleList = pageListPageMap.keySet().iterator().next();
		int pages = pageListPageMap.get(articleList);
		model.addAttribute("articleList", articleList);
		model.addAttribute("totalPages", pages);
		if (reflactClassName.equals(ArticleType.MediaArticle)) {
			return Jsp.manager_webMedia;
		} else if (reflactClassName.equals(ArticleType.NoticeArticle)) {
			return Jsp.manager_webNotice;
		} else {
			return Jsp.manager_webAdvisory;
		}
	}

	@RequestMapping("/*/get_articleListByAjax")
	public void getArticleListByAjax(@RequestParam Integer index, HttpServletRequest request,
			HttpServletResponse response) {
		// 初始化返回内容
		String content = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String reuquestUrl = request.getRequestURI();
		String reflactClassName = StringSub.getRequestSub(reuquestUrl, "manager/", "/get_articleListByAjax");
		Map<List<Article>, Integer> pageListPageMap = articleService.getArticleList(reflactClassName, index);
		List<Article> articleList = pageListPageMap.keySet().iterator().next();
		// 迭代添加元素
		for (Article article : articleList) {
			content += "<tr class=\"gradeX\">";
			content += "<td><img src=\"./resources/manager/img/k.jpg\" class=\"tpl-table-line-img\" alt=\"\" /></td>";
			content += "<td class=\"am-text-middle\">" + article.getTitle() + "</td>";
			content += "<td class=\"am-text-middle\">" + article.getAuthor() + "</td>";
			content += "<td class=\"am-text-middle\">" + dateFormat.format(article.getReleaseDate()) + "</td>";
			content += "<td class=\"am-text-middle\">";
			content += "<div class=\"tpl-table-black-operation\">";
			content += "<a href=\"manager/" + reflactClassName + "/toUpdate_article?id=" + article.getId()
					+ "\"> <i class=\"am-icon-pencil\"></i> 编辑 </a> ";
			content += "<a href=\"manager/" + reflactClassName + "/delete_article?id=" + article.getId()
					+ "\"class=\"tpl-table-black-operation-del\"> <i class=\"am-icon-trash\"></i> 删除 </a>";
			content += "</div>";
			content += "</td></tr>";
		}
		try {
			response.setContentType("text/xml;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 *===============================================================================
	 *网站管理==========================================================================
	 *===============================================================================
	 */

	@RequestMapping("/web/allinfo")
	public String getAllInfo(Model model, HttpServletRequest req, HttpServletResponse rps) {
		WebInfo infos = webInfoService.getAll();
		model.addAttribute("webinfo", infos);

		if (req.getParameter("flag") != null && req.getParameter("flag").equals("1")) {
			model.addAttribute("flag", 1);
		}
		return Jsp.manager_set;
	}

	@RequestMapping("/web/email")
	public void getEmail(@RequestParam String info, HttpServletRequest req, HttpServletResponse response) {
		String content = webInfoService.getEmail();
		System.out.println(content);
		try {
			response.setContentType("text/xml;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/web/updateinfo")
	public String updateInfo(@RequestParam("name") String name, @RequestParam String email,
			@RequestParam String descrip, @RequestParam String website, @RequestParam String copyright,
			HttpServletRequest req, HttpServletResponse rps) {
		System.out.println(name);
		System.out.println(descrip);
		webInfoService.updateInfo(name, email, descrip, website, copyright);
		return "redirect:/manager/web/allinfo?flag=1";
	}

	@RequestMapping("/web/notice")
	public String notice() {
		return Jsp.manager_webNotice;
	}

	@RequestMapping("/web/media")
	public String media() {
		return Jsp.manager_webMedia;
	}

	@RequestMapping("/web/advisory")
	public String advisory() {
		return Jsp.manager_webAdvisory;
	}

	@RequestMapping("/web/addNotice")
	public String add_notice() {
		return Jsp.manager_addNotice;
	}

	@RequestMapping("/web/addMedia")
	public String add_media() {
		return Jsp.manager_addMedia;
	}

	@RequestMapping("/web/addAdvisory")
	public String add_advisory() {
		return Jsp.manager_addAdvisory;
	}
	
	
	/**
	 * 上传文件
	 * 
	 * @param file
	 * @return
	 */
	public String uploadFile(CommonsMultipartFile file) {
		String savePath = aliyunOssConf.getUploadPath() + aliyunOssConf.getUploadFolder();
		log.info("savePath={}", savePath);
		String fileName = FreeUtils.generateImgName();
		if (FileUploadTools.uploadFile(file, fileName, savePath)) {
			String downloadPath = String.format("http://%s%s/%s", aliyunOssConf.getDomainName(),
					aliyunOssConf.getUploadFolder(), fileName);
			log.info("downloadPath={}", downloadPath);
			return downloadPath;
		} else {
			return null;
		}
	}
	
	
}
