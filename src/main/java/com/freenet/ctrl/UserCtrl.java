package com.freenet.ctrl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.freenet.common.ArticleType;
import com.freenet.common.Constants;
import com.freenet.common.Jsp;
import com.freenet.common.K;
import com.freenet.common.Order;
import com.freenet.common.Text;
import com.freenet.common.V;
import com.freenet.conf.AliyunOssConf;
import com.freenet.domain.Article;
import com.freenet.domain.Auth;
import com.freenet.domain.Bank;
import com.freenet.domain.Coin;
import com.freenet.domain.LineOrder;
import com.freenet.domain.LoginHistroy;
import com.freenet.domain.Money;
import com.freenet.domain.OffOrder;
import com.freenet.domain.OpenClosePrice;
import com.freenet.domain.RecentPriceData;
import com.freenet.domain.RechargeRecord;
import com.freenet.domain.User;
import com.freenet.domain.Wallet;
import com.freenet.domain.WithDraw;
import com.freenet.service.AliyunOssService;
import com.freenet.service.ArticleService;
import com.freenet.service.CoinService;
import com.freenet.service.LineOrderService;
import com.freenet.service.ManagerService;
import com.freenet.service.OffOrderService;
import com.freenet.service.OpenClosePriceService;
import com.freenet.service.RecentPriceDataService;
import com.freenet.service.RechargeRecordService;
import com.freenet.service.UserService;
import com.freenet.service.WalletService;
import com.freenet.service.WebInfoService;
import com.freenet.service.WithDrawService;
import com.freenet.service.sms.SMSService;
import com.freenet.tools.FileUploadTools;
import com.freenet.tools.FreeUtils;
import com.freenet.tools.PwdTools;
import com.freenet.tools.SMSVerifyCodeTools;
import com.freenet.tools.StringSub;
import com.google.code.kaptcha.impl.DefaultKaptcha;

@Controller
@RequestMapping(value = "/user")
@Transactional
public class UserCtrl {

	Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	private UserService userService;

	@Resource
	private SMSService smsService;

	@Resource
	private WalletService walletService;

	@Resource
	private CoinService coinService;

	@Resource
	private AliyunOssService aliyunOssService;

	@Resource
	private AliyunOssConf aliyunOssConf;

	@Resource
	private WebInfoService webInfoService;

	@Resource
	private ArticleService articleService;

	@Resource
	private WithDrawService withDrawService;

	@Resource
	private RechargeRecordService rechargeRecordService;

	@Resource
	private LineOrderService lineOrderService;

	@Resource
	private OffOrderService offOrderService;

	@Resource
	private ManagerService managerService;

	@Resource
	private RecentPriceDataService recentPriceDataService;
	
	@Resource
	private OpenClosePriceService openClosePriceService;

	// 首页登录页面
	@RequestMapping(value = "/index_Net", method = RequestMethod.GET)
	public String indexLogin(HttpServletRequest request, Model model) {
		List<Article> noticeArticleList = getIndexNetData("NoticeArticle");
		List<Article> mediaArticleList = getIndexNetData("MediaArticle");
		model.addAttribute("noticeArticleList", noticeArticleList);
		model.addAttribute("mediaArticleList", mediaArticleList);

		return Jsp.user_index_net;
	}

	// 登录页面
	@RequestMapping(value = "/toLogin", method = RequestMethod.GET)
	public String toLogin() {
		return Jsp.user_login;
	}

	// 注册页面
	@RequestMapping(value = "/toRegister", method = RequestMethod.GET)
	public String toRegister() {
		return Jsp.user_register;
	}

	// 找回密码页面
	@RequestMapping(value = "/pwdForget_find", method = RequestMethod.GET)
	public String toBackpwd() {
		return Jsp.user_pwdForget;
	}

	// 注册操作
	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public String insertUser(Model model, HttpServletRequest req, HttpServletResponse res, String username, String code,
			String verifyCode, String password, String repassword) throws IOException {

		if (!code.equals(req.getSession().getAttribute(Constants.IMAGE_CODE_TEXT))) {
			model.addAttribute(K.msg, "验证码不正确!");
			return Jsp.user_register;
		}

		// 注册前首先检测手机账号是否存在
		User user = userService.selectByUsername(username);
		if (user != null) {
			model.addAttribute(K.msg, "手机号已存在!");
			return Jsp.user_register;
		}
		String realVerifyCode = smsService.getSmsVerifyCode(username);
		// 判断用户输入的验证码是否与系统生成的验证码相同
		if (!StringUtils.isEmpty(verifyCode) && verifyCode.equals(realVerifyCode)) {
			System.out.println("验证码正确!");
			userService.insertUser(username, repassword, 2);
			User userSession = userService.selectByUsername(username);
			userService.insertMoney(userSession.getUserId());
			List<Coin> list = coinService.selectAll();
			// 个人钱包
			for (int i = 0; i < list.size(); i++) {
				walletService.insertWallet(userSession.getUserId(), list.get(i).getCoinId());
			}
			createSession(req, userSession);
			smsService.removeSmsVerifyCode(username);// 若账号更改成功则删除验证码
		} else {
			model.addAttribute(K.msg, "短信验证码不正确!");
			return Jsp.user_register;
		}
		List<Article> noticeArticleList = getIndexNetData("NoticeArticle");
		List<Article> mediaArticleList = getIndexNetData("MediaArticle");
		model.addAttribute("noticeArticleList", noticeArticleList);
		model.addAttribute("mediaArticleList", mediaArticleList);
		return Jsp.user_index_net;

	}

	/*
	 * 通过手机找回密码操作 验证成功后进行下一步操作
	 */
	@RequestMapping(value = "/pwd/phone_find", method = RequestMethod.POST)
	public String pwd_phoneFind(HttpServletRequest req, Model model, String username, String code, String verifyCode) {
		// 检测图形验证码
		if (!code.equals(req.getSession().getAttribute(Constants.IMAGE_CODE_TEXT))) {
			System.out.println("验证码错误!");
			model.addAttribute(K.msg, "验证码错误!");
			return Jsp.user_pwdForget;
		}
		String realVerifyCode = smsService.getSmsVerifyCode(username);
		if (!StringUtils.isEmpty(verifyCode) && verifyCode.equals(realVerifyCode)) {// 检测用户的短信验证码是否正确
			// 检测账号是否为之前使用的账号
			if (userService.selectByUsername(username) == null) {// 说明使用新的账号申请找回密码
				model.addAttribute(K.msg, "您的手机账号不存在!");
				return Jsp.user_pwdForget;
			}
		} else {
			model.addAttribute(K.msg, "短息验证码不正确!");
			return Jsp.user_pwdForget;
		}
		smsService.removeSmsVerifyCode(username);// 若账号更改成功则删除验证码
		model.addAttribute("username", username);
		return Jsp.user_pwdSet;
	}

	// 保存密码操作
	@RequestMapping(value = "/pwd/phone_setpwd", method = RequestMethod.POST)
	public String pwd_phoneSetpwd(String username, String password, String repassword) {
		System.out.println(username);
		System.out.println(password);
		System.out.println(repassword);
		int cont = userService.updatePwd(username, password);
		if (cont < 1) {// 说明修改失败
			// 可以加一个失败页面!
			return null;
		} else {
			return Jsp.user_successPwd;
		}
	}

	// 首页用户登录操作
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String indexLogin(Model model, HttpServletRequest req, String username, String password, String code,
			String verifyCode) {
		List<Article> noticeArticleList = getIndexNetData("NoticeArticle");
		List<Article> mediaArticleList = getIndexNetData("MediaArticle");
		model.addAttribute("noticeArticleList", noticeArticleList);
		model.addAttribute("mediaArticleList", mediaArticleList);
		System.out.println(username);
		User user = userService.login(username, password);
		// 检测图片验证码是否正确
		if (!code.equals(req.getSession().getAttribute(Constants.IMAGE_CODE_TEXT))) {
			model.addAttribute(K.msg, "验证码不正确!");
			return Jsp.user_index_net;
		}
		String realVerifyCode = smsService.getSmsVerifyCode(username);
		// 判断用户输入的验证码是否与系统生成的验证码相同
		if (!StringUtils.isEmpty(verifyCode) && verifyCode.equals(realVerifyCode)) {
			System.out.println("验证码正确!");
			if (user == null) {
				model.addAttribute(K.msg, "账号或密码错误!");
				return Jsp.user_index_net;
			} else {
				if (user.getStatus() == V.user_status_freeze) {// 账号已被冻结
					model.addAttribute(K.msg, "您的账号已被冻结!");
					return Jsp.user_index_net;
				} else {
					// 创建当前用户的session
					createSession(req, user);
					// 添加登录记录
					userService.insertloginHistroy(user.getUserId());
					smsService.removeSmsVerifyCode(username);// 若账号更改成功则删除验证码
				}
			}
		} else {
			model.addAttribute(K.msg, "短信验证码不正确!");
			return Jsp.user_index_net;
		}

		return Jsp.user_index_net;
	}

	// 登录页面用户登陆操作
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpServletRequest req, String username, String password, String code,
			String verifyCode) {
		// 检测图片验证码是否正确
		if (!code.equals(req.getSession().getAttribute(Constants.IMAGE_CODE_TEXT))) {
			model.addAttribute(K.msg, "验证码不正确!");
			return Jsp.user_login;
		}
		String realVerifyCode = smsService.getSmsVerifyCode(username);
		// 检测短信验证码
		if (!StringUtils.isEmpty(verifyCode) && verifyCode.equals(realVerifyCode)) {
			// 检测用的账号跟密码
			User user = userService.login(username, password);
			if (user == null) {// 说明不存在或者账号跟密码错误
				model.addAttribute(K.msg, "账号或密码错误!");
				return Jsp.user_login;
			} else {
				if (user.getStatus() == V.user_status_freeze) {// 账号已被冻结
					model.addAttribute(K.msg, "您的账号已被冻结!");
					return Jsp.user_login;
				} else {
					// 创建当前用户的session
					createSession(req, user);
					// 添加登录记录
					userService.insertloginHistroy(user.getUserId());
					smsService.removeSmsVerifyCode(username);// 若账号更改成功则删除验证码
					List<Article> noticeArticleList = getIndexNetData("NoticeArticle");
					List<Article> mediaArticleList = getIndexNetData("MediaArticle");
					model.addAttribute("noticeArticleList", noticeArticleList);
					model.addAttribute("mediaArticleList", mediaArticleList);
					return Jsp.user_index_net;
				}
			}
		} else {
			model.addAttribute(K.msg, "短信验证码不正确!");
			return Jsp.user_login;
		}
	}

	// 用户登录后的创建session
	private void createSession(HttpServletRequest req, User user) {
		HttpSession session = req.getSession();
		session.setAttribute(K.sessionUser, user);
		session.setMaxInactiveInterval(60 * 30);// 30分钟超时
	}

	// 获取User
	public User getUser(HttpServletRequest req) {
		User user = (User) req.getSession().getAttribute(K.sessionUser);
		return user;
	}

	// 交易大厅页面
	@RequestMapping(value = "/trading/offlineSell", method = RequestMethod.GET)
	public String toBusiness(Model model, HttpServletRequest req) {
		User user = getUser(req);
		List<Coin> coins = coinService.selectAll();
		int coinId = coins.get(0).getCoinId();
		BigDecimal coinCount = coins.get(0).getCoinCount();// 线下订单抵押币
		Wallet wallet = walletService.select(user.getUserId(), coinId);// 获得用户的可用货币
		Bank bank = userService.selectBankByUserId(user.getUserId());
		List<OffOrder> list = offOrderService.selectEntrustByUserId(user.getUserId());
		model.addAttribute(K.list, list);
		model.addAttribute("user", user);
		model.addAttribute(K.obj, wallet);
		model.addAttribute("bank", bank);
		model.addAttribute("coinCount", coinCount);
		model.addAttribute("type", "offlineSell");
		return Jsp.user_offlineSell;
	}

	// 线下交易中卖出操作
	@RequestMapping(value = "/offOrder/sell", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> user_offOrder_sell(int userId, String mobile, String sellCoin, String price,
			String finalCoin, String verifyCode, String dealPwd) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 首先判断短信验证码
		String realVerifyCode = smsService.getSmsVerifyCode(mobile);
		if (!StringUtils.isEmpty(verifyCode) && verifyCode.equals(realVerifyCode)) {// 正确
			User user = userService.selectByUserId(userId);
			// 检测资金密码
			if (user.getDealPwd() == null) {
				result.put(K.result, false);
				result.put(K.msg, "请先设置您的资金密码!!");
				return result;
			} else if (!user.getDealPwd().equals(PwdTools.ecodingPwd(dealPwd, K.md5))) {
				result.put(K.result, false);
				result.put(K.msg, "资金密码不正确!");
				return result;
			} else {
				// 进行操作
				// 添加线下订单
				offOrderService.insertOffOrder(userId, user.getUsername(), 2, sellCoin, price);
				// 更新账户的钱包
				List<Coin> list = coinService.selectAll();
				int coinId = list.get(0).getCoinId();
				// 计算货币
				BigDecimal agoCoin = walletService.select(userId, coinId).getCoin();
				BigDecimal nowCoin = agoCoin.subtract(new BigDecimal(finalCoin));
				walletService.updateCoin(userId, nowCoin);
				smsService.removeSmsVerifyCode(mobile);// 若账号更改成功则删除验证码
				result.put(K.result, true);
				result.put(K.msg, "卖出订单成功!");
				return result;
			}
		} else {
			result.put(K.result, false);
			result.put(K.msg, "短信验证码错误!");
			return result;
		}
	}

	/*
	 * =========================================================================
	 * ==============交易大厅用户端
	 * START====================================================================
	 * == =====
	 * =========================================================================
	 * ==============
	 */
	// 线下交易买入页面
	@RequestMapping("/trading/offlineBuy")
	public String offlineBuy(HttpServletRequest request, Model model, String pageNow) {
		User user = getUser(request);
		// 分页查询非当前用户的所有线下卖单
		offOrderService.showSellUserIdByPage(model, pageNow, user.getUserId());
		String url = "user/trading/offlineBuy?pageNow=";
		model.addAttribute(K.url, url);
		model.addAttribute("type", "offlineBuy");
		return Jsp.user_offlineBuy;
	}

	// ajax统计表请求数据
	@RequestMapping("/trading/getTableDataByAjax")
	public void getTableDataByAjax(HttpServletRequest request, HttpServletResponse response) {
		JSONObject object;
		Calendar calendar = Calendar.getInstance();
		JSONArray array = new JSONArray();
		Map<String, Object> map = new HashMap<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		List<RecentPriceData> priceDataList = recentPriceDataService.getPriceDataList();
		Map<Integer, OpenClosePrice> openClosePriceMap = openClosePriceService.selectAllPrice();
		for (RecentPriceData priceData : priceDataList) {
			calendar.setTime(priceData.getStartDate());
			System.out.println(calendar.get(Calendar.DATE));
			map.put("time", dateFormat.format(priceData.getStartDate()));
			map.put("maxVal", priceData.getMaxVal().toString());
			map.put("minVal", priceData.getMinVal().toString());
			map.put("openVal", openClosePriceMap.get(calendar.get(Calendar.DATE)).getOpenPrice());
			map.put("closeVal", openClosePriceMap.get(calendar.get(Calendar.DATE)).getClosePrice());
			object = new JSONObject(map);
			array.add(object);
			map = new HashMap<>();
		}
		map.put("result", array);
		object = new JSONObject(map);
		System.out.println(object.toJSONString());
		try {
			response.setContentType("text/xml;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(object.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 买入操作
	// 请求卖家的收款账号
	@RequestMapping(value = "/offline/showPay", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> offline_showPay(int sellUserId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Bank bank = userService.selectBankByUserId(sellUserId);
		result.put(K.obj, bank);
		result.put(K.result, true);
		return result;
	}

	// 确认购买此订单
	@RequestMapping(value = "/offline/sureBuy", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> offline_sureBuy(Model model, int offId, HttpServletRequest req) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = getUser(req);
		Auth auth = userService.selectAuthByUserId(user.getUserId());
		if (userService.selectByUserId(user.getUserId()).getAuthStatus() == 2) {
			result.put(K.result, false);
			result.put(K.msg, "请您先进行实名认证!");
			return result;
		}
		offOrderService.updateOffOrder(user.getUserId(), user.getUsername(), auth.getName(), offId);
		result.put(K.result, true);
		return result;
	}

	/*
	 * @RequestMapping("/trading/offlineSell") public String
	 * offlineSell(HttpServletRequest request, Model model) {
	 * model.addAttribute("type", "offlineSell"); return Jsp.user_offlineSell; }
	 */

	// 买入记录
	@RequestMapping("/trading/offlineBuyRecord")
	public String offlineBuyRecord(HttpServletRequest request, Model model, String pageNow) {
		User user = getUser(request);
		// 分页查询当前用户的买入记录
		offOrderService.showOffOrderBuyByPage(model, pageNow, user.getUserId());
		String url = "user/trading/offlineBuyRecord?pageNow=";
		model.addAttribute(K.url, url);
		model.addAttribute("type", "offlineBuyRecord");
		return Jsp.user_offlineBuyRecord;
	}

	// 卖出、买入 根据起始日期与结束日期查询(分页)
	@RequestMapping(value = "/offline/findDate", method = RequestMethod.GET)
	public String offline_buyDate(Model model, String start, String end, String pageNow, int type,
			HttpServletRequest req) throws Exception {
		User user = getUser(req);
		if (type == 1) {// 买入记录
			offOrderService.showOffBuyDateUserByPage(model, pageNow, user.getUserId(), start, end);
			String url = "user/offline/findDate?type=" + type + "&start=" + start + "&end=" + end + "&pageNow=";
			model.addAttribute(K.url, url);
			model.addAttribute("type", "offlineBuyRecord");
			model.addAttribute("start", start);
			model.addAttribute("end", end);
			return Jsp.user_offlineBuyRecord;
		} else {// 卖出记录
			offOrderService.showOffSellDateUserByPage(model, pageNow, user.getUserId(), start, end);
			String url = "user/offline/findDate?type=" + type + "&start=" + start + "&end=" + end + "&pageNow=";
			model.addAttribute(K.url, url);
			model.addAttribute("type", "offlineSellRecord");
			model.addAttribute("start", start);
			model.addAttribute("end", end);
			return Jsp.user_offlineSellRecord;
		}
	}

	// 买入、卖出根据天数查询
	@RequestMapping(value = "/offline/findDay", method = RequestMethod.GET)
	public String offline_findDay(Model model, int type, int day, String pageNow, HttpServletRequest req)
			throws Exception {
		User user = getUser(req);
		if (type == 1) {// 买入记录
			offOrderService.showOffByBuyDayByPage(model, pageNow, user.getUserId(), day);
			String url = "user/offline/findDay?type=" + type + "&day=" + day + "&pageNow=";
			model.addAttribute(K.url, url);
			model.addAttribute("type", "offlineBuyRecord");
			return Jsp.user_offlineBuyRecord;
		} else {// 卖出记录
			offOrderService.showOffBySellDayByPage(model, pageNow, user.getUserId(), day);
			String url = "user/offline/findDay?type=" + type + "&day=" + day + "&pageNow=";
			model.addAttribute(K.url, url);
			model.addAttribute("type", "offlineSellRecord");
			return Jsp.user_offlineSellRecord;
		}
	}

	// 确认打款/确认收款
	@RequestMapping(value = "/offlin/sureFunds", method = RequestMethod.GET)
	public String offlin_surePay(int id, int type, HttpServletRequest req) {
		System.out.println(type);
		OffOrder off = offOrderService.selectOffOrderById(id);
		if (type == 1) {// 确认打款
			offOrderService.updateOffOrderStatus(Order.order_off_sureReceivable, id);// 修改为已打款，等待卖方确认收款
			withDrawService.insertContent(off.getSellUserId(), Text.offOrder_BuyUser_pay, V.content_type_system);
			return "redirect:/user/trading/offlineBuyRecord";
		} else {// 确认收款
			User user = getUser(req);
			offOrderService.updateOffOrderStatus(Order.order_off_complete, id);// 修改为已完成，交易完成
			// 将多余的抵押币还给卖方
			// 获取线下交易抵押币数量
			List<Coin> coin = coinService.selectAll();
			BigDecimal coinCount = coin.get(0).getCoinCount();
			Wallet wall = walletService.select(user.getUserId(), coin.get(0).getCoinId());
			BigDecimal userCoin = wall.getCoin();
			BigDecimal finalCoin = userCoin.add(coinCount);
			walletService.updateCoin(user.getUserId(), finalCoin);
			String text = String.format(Text.offOrder_Sell_yes, coinCount);
			// 货币进行转移，给买方增加货币
			BigDecimal sellCoin = off.getSellCoin();
			BigDecimal buyCoin = walletService.select(off.getBuyUserId(), coin.get(0).getCoinId()).getCoin();
			walletService.updateCoin(off.getBuyUserId(), buyCoin.add(sellCoin));
			// 更新realPrice与realCdt
			offOrderService.updaterealPrice(id, off.getPrice());
			// 增加个人消息
			// 卖方
			withDrawService.insertContent(user.getUserId(), text, V.content_type_system);// 系统消息
			// 买方
			withDrawService.insertContent(off.getBuyUserId(), Text.offOrder_SellUser_have, V.content_type_system);

			// 统计表
			OffOrder offTwo = offOrderService.selectOffOrderById(id);
			recentPriceDataService.resolveOffOrder(offTwo);
			
			return "redirect:/user/trading/offlineSellRecord";
		}

	}

	// 取消此订单操作
	@RequestMapping(value = "/offline/cancelOrder", method = RequestMethod.GET)
	public String offline_cancelOrder(int id, HttpServletRequest req) {
		OffOrder off = offOrderService.selectOffOrderById(id);
		// 将订单修改为挂单状态
		offOrderService.updateOffOrderStatus(Order.order_off_normal, id);
		// 提示卖出方订单取消，增加个人消息
		withDrawService.insertContent(off.getSellUserId(), Text.offOrder_cancelOrder, V.content_type_system);
		return "redirect:/user/trading/offlineBuyRecord";
	}

	// 发起违约操作
	@RequestMapping(value = "/offline/breach", method = RequestMethod.GET)
	public String offline_breach(int id, int type, HttpServletRequest req) {
		OffOrder off = offOrderService.selectOffOrderById(id);

		// 将订单中的货币与抵押币冻结
		BigDecimal sellCoin = off.getSellCoin();// 订单卖出的货币

		List<Coin> coins = coinService.selectAll();
		int coinId = coins.get(0).getCoinId();
		Wallet wall = walletService.select(off.getSellUserId(), coinId);
		// 获取卖方当前的钱包的冻结货币数量
		BigDecimal coinFrozen = wall.getCoinFrozen();
		System.out.println(coinFrozen);
		BigDecimal coinCount = coins.get(0).getCoinCount();// 获取平台的设置的抵押币数量
		System.out.println(coinCount);
		BigDecimal addFrozen = sellCoin.add(coinCount);// 计算冻结的货币
		System.out.println(addFrozen);

		BigDecimal finalFrozen = coinFrozen.add(addFrozen);// 将之前用户的钱包的冻结货币与现在订单的冻结货币相加
		System.out.println(finalFrozen);
		if (type == 1) {// 买方发起违约
			// 更新钱包中的冻结货币
			walletService.updateCoinFrozen(finalFrozen, off.getSellUserId(), coinId);
			String sellText = String.format(Text.offOrder_buySendBreach, sellCoin, coinCount);
			// 给卖方消息提示
			withDrawService.insertContent(off.getSellUserId(), sellText, V.content_type_system);
			// 给买方消息提示
			withDrawService.insertContent(off.getBuyUserId(), Text.offOrder_sendReturnMsg, V.content_type_system);
			// 更新当前订单的状态
			offOrderService.updateOffOrderStatus(Order.order_off_buySendMust, id);
			return "redirect:/user/trading/offlineBuyRecord";
		} else {// 卖方发起未收到打款
				// 给买方消息提示

			withDrawService.insertContent(off.getBuyUserId(), Text.offOrder_payMoney, V.content_type_system);
			// 更新当前订单的状态
			offOrderService.updateOffOrderStatus(Order.order_off_sellSendMust, id);
			return "redirect:/user/trading/offlineSellRecord";
		}
	}

	// 线下交易卖出记录
	@RequestMapping("/trading/offlineSellRecord")
	public String offlineSellRecord(HttpServletRequest request, Model model, String pageNow) {
		User user = getUser(request);
		// 分页查询某用户的线下卖出记录
		offOrderService.showOffOrderSellByPage(model, pageNow, user.getUserId());
		String url = "user/trading/offlineSellRecord?pageNow=";
		model.addAttribute(K.url, url);
		model.addAttribute("type", "offlineSellRecord");
		return Jsp.user_offlineSellRecord;
	}

	// 线下订单委托管理
	@RequestMapping("/trading/offlineEntrust")
	public String offlineEntrust(HttpServletRequest request, Model model, String pageNow) {
		User user = getUser(request);
		// 分页查询某个用户所有委托管理订单
		offOrderService.showEntrustByPage(model, pageNow, user.getUserId());
		String url = "user/trading/offlineEntrust?pageNow=";
		model.addAttribute(K.url, url);
		model.addAttribute("type", "offlineEntrust");
		return Jsp.user_offlineEntrust;
	}

	// 委托管理根据起始时间与结束时间分页查询
	@RequestMapping(value = "/offEntrust/findDate", method = RequestMethod.GET)
	public String offEntrust_findDate(Model model, String start, String end, String pageNow, HttpServletRequest req)
			throws Exception {
		User user = getUser(req);
		offOrderService.showOffEntrustByPage(model, pageNow, user.getUserId(), start, end);
		String url = "user/offEntrust/findDate?start=" + start + "&end=" + end + "&pageNow=";
		model.addAttribute(K.url, url);
		model.addAttribute("type", "offlineEntrust");
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		return Jsp.user_offlineEntrust;
	}

	// 委托管理根据天数分页查询
	@RequestMapping(value = "/offEntrust/findDay", method = RequestMethod.GET)
	public String offEntrust_findDay(Model model, int day, String pageNow, HttpServletRequest req) throws Exception {
		User user = getUser(req);
		offOrderService.showEntrustDayByPage(model, pageNow, user.getUserId(), day);
		String url = "user/offEntrust/findDay?day=" + day + "&pageNow=";
		model.addAttribute(K.url, url);
		model.addAttribute("type", "offlineEntrust");
		return Jsp.user_offlineEntrust;
	}

	// 线下订单撤单
	@RequestMapping(value = "/offline/kill", method = RequestMethod.GET)
	public String offline_kill(int id, HttpServletRequest req) {
		String retUrl = req.getHeader("Referer");
		String newUrl = retUrl.substring(retUrl.indexOf("http"), retUrl.indexOf("user") - 1);
		retUrl = retUrl.replaceAll(newUrl, "");
		OffOrder off = offOrderService.selectOffOrderById(id);
		BigDecimal sellCoin = off.getSellCoin();
		// 获取抵押币
		List<Coin> coin = coinService.selectAll();
		int coinId = coin.get(0).getCoinId();
		BigDecimal coinCount = coin.get(0).getCoinCount();
		Wallet wall = walletService.select(off.getSellUserId(), coinId);
		BigDecimal userCoin = wall.getCoin();
		BigDecimal addCoin = sellCoin.add(coinCount);
		walletService.updateCoin(off.getSellUserId(), userCoin.add(addCoin));// 将卖出的货币还给用户
		offOrderService.updateOffOrderStatus(Order.order_off_EntrustKill, id);// 改为撤单状态
		return "redirect:" + retUrl;
	}

	// 根据不同的委托管理状态分页查询
	@RequestMapping(value = "/offline/statusFind", method = RequestMethod.GET)
	public String offOrder_statusFind(Model model, HttpServletRequest req, int status, String pageNow) {
		User user = getUser(req);
		offOrderService.showEntrustStatusByPage(model, pageNow, user.getUserId(), status);
		String url = "user/offline/statusFind?status=" + status + "&pageNow=";
		model.addAttribute(K.url, url);
		model.addAttribute("type", "offlineEntrust");
		return Jsp.user_offlineEntrust;
	}

	// 卖出/买入 记录根据不同状态查询(分页)
	@RequestMapping(value = "/offline/findByStatus", method = RequestMethod.GET)
	public String offline_findByStatus(Model model, int status, HttpServletRequest req, String pageNow, int type) {
		User user = getUser(req);
		if (type == 1) {// 卖出记录
			offOrderService.showUserOffByPage(model, pageNow, user.getUserId(), status);
			String url = "user/offline/findByStatus?status=" + status + "&type=1" + "&pageNow=";
			model.addAttribute(K.url, url);
			model.addAttribute("type", "offlineSellRecord");
			return Jsp.user_offlineSellRecord;
		} else {// 买入记录
			offOrderService.showbuyOffByPage(model, pageNow, user.getUserId(), status);
			String url = "user/offline/findByStatus?status=" + status + "&type=2" + "&pageNow=";
			model.addAttribute(K.url, url);
			model.addAttribute("type", "offlineBuyRecord");
			return Jsp.user_offlineBuyRecord;
		}

	}

	// 平台是否介入线下订单，分页查询
	@RequestMapping(value = "/offline/Intervention", method = RequestMethod.GET)
	public String offline_Intervention(Model model, HttpServletRequest req, int intervention, int type,
			String pageNow) {
		User user = getUser(req);
		if (type == 1) {// 卖出记录是否平台介入
			offOrderService.showSellOffIntervenByPage(model, pageNow, user.getUserId(), intervention);
			String url = "user/offline/Intervention?intervention=" + intervention + "&type=1" + "&pageNow=";
			model.addAttribute(K.url, url);
			model.addAttribute("type", "offlineSellRecord");
			return Jsp.user_offlineSellRecord;
		} else {// 买入记录平台是否介入
			offOrderService.showBuyOffIntervenByPage(model, pageNow, user.getUserId(), intervention);
			String url = "user/offline/Intervention?intervention=" + intervention + "&type=2" + "&pageNow=";
			model.addAttribute(K.url, url);
			model.addAttribute("type", "offlineBuyRecord");
			return Jsp.user_offlineBuyRecord;
		}
	}

	/**
	 * 线上购买
	 */
	@RequestMapping("/trading/onlineBuy")
	public String onlineBuy(HttpServletRequest request, Model model) {
		int status = 2, page = 1;
		Map<List<LineOrder>, Integer> map = lineOrderService.getAllByStatusAndUser(null, null, status, page, "sellCdt",
				null, null);
		List<LineOrder> lineOrders = map.keySet().iterator().next();
		BigDecimal money = userService.selectMoneyByUserId(getUser(request).getUserId()).getMoney();
		// coinService.selectByCoinId(coinId)
		int totalPages = map.get(lineOrders);
		model.addAttribute("lineOrders", lineOrders);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("money", money);
		// model.addAttribute("rate", );
		request.setAttribute("error", request.getParameter("error"));
		model.addAttribute("type", "onlineBuy");
		return Jsp.user_onlineBuy;
	}

	/**
	 * 线上购买（分页）
	 */
	@RequestMapping("/trading/onlineBuyByPage")
	public void onlineBuyByPage(HttpServletRequest request, HttpServletResponse response) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String content = "";
		int status = 2, page = 1;
		if (request.getParameter("index") != null && request.getParameter("index") != "") {
			page = Integer.parseInt(request.getParameter("index"));
		}
		Map<List<LineOrder>, Integer> map = lineOrderService.getAllByStatusAndUser(null, null, status, page, "sellCdt",
				null, null);
		List<LineOrder> lineOrders = map.keySet().iterator().next();
		content += "<table><tr><th>时间</th><th>卖出量</th><th>价格</th><th>操作</th></tr>";
		for (LineOrder order : lineOrders) {
			content += "<tr id=\"" + order.getId() + "\"><td class=\"shijian\">" + dateFormat.format(order.getSellCdt())
					+ "</td><td class=\"maichuliang\">" + order.getSellCoin() + "</td><td class=\"jiage\">"
					+ order.getPrice() + "</td><td><a class=\"MB_R\">购买</a></td></tr>";
		}
		content += "</table>";
		try {
			response.setContentType("text/xml;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/trading/onlineBuyProcess")
	public String onlineBuyProcess(@RequestParam String dealpwd, @RequestParam Integer id, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		User user = userService.selectByUserId(getUser(request).getUserId());
		LineOrder lineOrder = lineOrderService.selectById(id);
		String pwdMD5 = PwdTools.ecodingPwd(dealpwd, "md5");
		if(user.getAuthStatus()!=V.user_authStatus_yes){
			return "redirect:/user/trading/onlineBuy?error=" + URLEncoder.encode("用户未实名认证", "utf-8");
		}
		if (!pwdMD5.equals(user.getDealPwd())) {
			return "redirect:/user/trading/onlineBuy?error=" + URLEncoder.encode("支付密码输入错误", "utf-8");
		}
		// 获得用户的余额
		BigDecimal money = userService.selectMoneyByUserId(user.getUserId()).getMoney();
		// 获得用户需要支付的现金
		BigDecimal price = lineOrder.getPrice();
		if (money.compareTo(price) == -1) {
			return "redirect:/user/trading/onlineBuy?error=" + URLEncoder.encode("用户余额不足", "utf-8");
		}
		// 修改用户的内容状态
		userService.updateBuyContent(user.getUserId(), lineOrder, price);
		// 修改钱包的内容状态
		walletService.updateBuyContent(user.getUserId(), lineOrder);
		// 修改单子的状态
		lineOrderService.updateBuyContent(id, user);
		// 统计表
		// recentPriceDataService.resolveLineOrder(lineOrder);
		return "redirect:/user/trading/onlineBuyRecord";
	}

	/*
	 * 获取实时交易信息
	 */
	@RequestMapping("/trading/getOnlineBuyNew")
	public void getOnlineBuyNew(HttpServletRequest request, HttpServletResponse response) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
		String content = "";
		List<LineOrder> lineOrders = lineOrderService.getNewByTime(1, 20);
		content += "<tr><th>时间</th><th>价格</th><th>数量</th></tr>";
		for (LineOrder lineOrder : lineOrders) {
			content += "<tr><th>" + dateFormat.format(lineOrder.getBuyCdt()) + "</th><th>" + lineOrder.getPrice()
					+ "</th><th>" + lineOrder.getSellCoin() + "MC</th></tr>";
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
	 * 线上卖出
	 */
	@RequestMapping("/trading/onlineSell")
	public String onlineSell(HttpServletRequest request, Model model) {
		int id = getUser(request).getUserId();
		int status = 2, page = 1;
		Map<List<LineOrder>, Integer> map = lineOrderService.getAllByStatusAndUser(id, null, status, page, "sellCdt",
				null, null);
		List<LineOrder> lineOrders = map.keySet().iterator().next();
		int totalPages = map.get(lineOrders);
		BigDecimal money = walletService.select0(getUser(request).getUserId()).getCoin();
		BigDecimal rate = coinService.selectAll().get(0).getCoinRate();
		model.addAttribute("lineOrders", lineOrders);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("money", money);
		model.addAttribute("rate", rate);
		model.addAttribute("mobile", userService.selectByUserId(id).getUsername());
		model.addAttribute("error", request.getParameter("error"));
		model.addAttribute("type", "onlineSell");
		return Jsp.user_onlineSell;
	}

	/**
	 * 线上卖出（分页）
	 */
	@RequestMapping("/trading/onlineSellByPage")
	public void onlineSellByPage(HttpServletRequest request, HttpServletResponse response) {
		int id = getUser(request).getUserId();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String content = "";
		int status = 2, page = 1;
		if (request.getParameter("index") != null && request.getParameter("index") != "") {
			page = Integer.parseInt(request.getParameter("index"));
		}
		Map<List<LineOrder>, Integer> map = lineOrderService.getAllByStatusAndUser(id, null, status, page, "sellCdt",
				null, null);
		List<LineOrder> lineOrders = map.keySet().iterator().next();
		content += "<table><tr><th>时间</th><th>卖出量</th><th>价格</th><th>操作</th></tr>";
		for (LineOrder order : lineOrders) {
			content += "<tr><td>" + dateFormat.format(order.getSellCdt()) + "</td><td>" + order.getSellCoin()
					+ "</td><td>" + order.getPrice()
					+ "</td><td><a class=\"back\" href=\"user/trading/onlineSellBack?id=" + order.getId()
					+ "\">撤回</a></td></tr>";
		}
		content += "</table>";
		try {
			response.setContentType("text/xml;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/trading/onlineSellProcess")
	public String onlineSellProcess(@RequestParam String sellPrice, @RequestParam String sellAmount,
			@RequestParam String message, @RequestParam String dealPwd, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		System.out.println(sellPrice + "---" + sellAmount + "---" + message + "---" + dealPwd);
		User user = userService.selectByUserId(getUser(request).getUserId());
		// 出售数量
		BigDecimal amount = new BigDecimal(sellAmount);
		// 手续费
		BigDecimal rateFee = amount.multiply(coinService.selectAll().get(0).getCoinRate());
		if (user.getAuthStatus() != V.user_authStatus_yes) {
			// model.addAttribute("error", "用户身份认证不通过！");
			return "redirect:/user/trading/onlineSell?error=" + URLEncoder.encode("用户身份认证状态不通过！", "utf-8");
		}
		if (!message.equals(smsService.getSmsVerifyCode(user.getUsername()))) {
			return "redirect:/user/trading/onlineSell?error=" + URLEncoder.encode("验证码错误！", "utf-8");
		}
		if (walletService.select0(user.getUserId()).getCoin().compareTo(amount.add(rateFee)) == -1) {
			return "redirect:/user/trading/onlineSell?error=" + URLEncoder.encode("账户余额不足！", "utf-8");
		}
		if (!PwdTools.ecodingPwd(dealPwd, "md5").equals(user.getDealPwd())) {
			if (user.getDealPwd() == null || user.getDealPwd() == "") {
				return "redirect:/user/trading/onlineSell?error=" + URLEncoder.encode("用户还未设置支付密码！", "utf-8");
			}
			return "redirect:/user/trading/onlineSell?error=" + URLEncoder.encode("输入支付密码错误！", "utf-8");
		}
		// 插入新纪录
		lineOrderService.insertLineOrder(user.getUserId(), user.getUsername(), Order.order_type_user, sellAmount,
				sellPrice);
		// 扣除相应数量的货币数量
		walletService.updateSellContent(user.getUserId(), amount);

		return "redirect:/user/trading/onlineSell";
	}

	/*
	 * 卖出者撤单
	 */
	@RequestMapping("/trading/onlineSellBack")
	public String onlineSellBack(@RequestParam Integer id, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 获得订单
		LineOrder lineOrder = lineOrderService.selectById(id);
		// 修改订单状态
		lineOrderService.updateBackContent(id);
		// 退回用户出售币
		walletService.updateBackContent(lineOrder.getSellUserId(), lineOrder.getSellCoin());
		return "redirect:/user/trading/onlineSell";
	}

	/*
	 * 线上买入记录
	 */
	@RequestMapping("/trading/onlineBuyRecord")
	public String onlineBuyRecord(HttpServletRequest request, Model model) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
		int page = 1;
		int id = getUser(request).getUserId();
		Map<List<LineOrder>, Integer> map;
		if (request.getParameter("days") != null && request.getParameter("days") != "") {
			Calendar startCalendar = Calendar.getInstance();
			Calendar endCalendar = Calendar.getInstance();
			startCalendar.add(Calendar.DAY_OF_MONTH, 0 - Integer.parseInt(request.getParameter("days")));
			map = lineOrderService.getAllByStatusAndUser(null, id, null, page, "buyCdt", startCalendar.getTime(),
					endCalendar.getTime());
		} else if (request.getParameter("startDate") != null && request.getParameter("startDate") != ""
				&& request.getParameter("endDate") != null && request.getParameter("endDate") != "") {
			Date startDate = dateFormat.parse(request.getParameter("startDate"));
			Date endDate = dateFormat.parse(request.getParameter("endDate"));
			map = lineOrderService.getAllByStatusAndUser(null, id, null, page, "buyCdt", startDate, endDate);
		} else {
			map = lineOrderService.getAllByStatusAndUser(null, id, null, page, "buyCdt", null, null);
		}
		List<LineOrder> lineOrders = map.keySet().iterator().next();
		int totalPages = map.get(lineOrders);
		model.addAttribute("lineOrders", lineOrders);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("startDate", request.getParameter("startDate"));
		model.addAttribute("endDate", request.getParameter("endDate"));
		model.addAttribute("days", request.getParameter("days"));
		model.addAttribute("type", "onlineBuyRecord");
		return Jsp.user_onnlineBuyRecord;
	}

	/*
	 * 线上买入记录（分页）
	 */
	@RequestMapping("/trading/onlineBuyRecordByPage")
	public void onlineBuyRecordByPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page = 1;
		String content = "";
		int id = getUser(request).getUserId();
		Map<List<LineOrder>, Integer> map;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy年MM月dd日");
		if (request.getParameter("index") != null && request.getParameter("index") != "") {
			page = Integer.parseInt(request.getParameter("index"));
		}
		if (request.getParameter("days") != null && request.getParameter("days") != "") {
			Calendar startCalendar = Calendar.getInstance();
			Calendar endCalendar = Calendar.getInstance();
			startCalendar.add(Calendar.DAY_OF_MONTH, 0 - Integer.parseInt(request.getParameter("days")));
			map = lineOrderService.getAllByStatusAndUser(null, id, null, page, "buyCdt", startCalendar.getTime(),
					endCalendar.getTime());
		} else if (request.getParameter("startDate") != null && request.getParameter("startDate") != "") {
			map = lineOrderService.getAllByStatusAndUser(null, id, null, page, "buyCdt",
					dateFormat1.parse(request.getParameter("startDate")),
					dateFormat1.parse(request.getParameter("endDate")));
		} else {
			map = lineOrderService.getAllByStatusAndUser(null, id, null, page, "buyCdt", null, null);
		}
		List<LineOrder> lineOrders = map.keySet().iterator().next();
		content += "<table><tr><th>购买时间</th><th>数量</th><th>成交金额</th><th>手续费</th></tr>";
		for (LineOrder order : lineOrders) {
			content += "<tr><td>" + dateFormat.format(order.getBuyCdt()) + "</td><td>" + order.getSellCoin()
					+ "</td><td>" + order.getPrice() + "</td><td>" + "手续费" + "</td></tr>";
		}
		content += "</table>";
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
	 * 线上卖出记录
	 */
	@RequestMapping("/trading/onlineSellRecord")
	public String onlineSellRecord(HttpServletRequest request, Model model) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
		int page = 1;
		Integer status = null;
		int id = getUser(request).getUserId();
		Map<List<LineOrder>, Integer> map;
		if (request.getParameter("status") != null && request.getParameter("status") != "") {
			status = Integer.parseInt(request.getParameter("status"));
		}
		if (request.getParameter("days") != null && request.getParameter("days") != "") {
			Calendar startCalendar = Calendar.getInstance();
			Calendar endCalendar = Calendar.getInstance();
			startCalendar.add(Calendar.DAY_OF_MONTH, 0 - Integer.parseInt(request.getParameter("days")));
			map = lineOrderService.getAllByStatusAndUser(id, null, status, page, "sellCdt", startCalendar.getTime(),
					endCalendar.getTime());
		} else if (request.getParameter("startDate") != null && request.getParameter("startDate") != ""
				&& request.getParameter("endDate") != null && request.getParameter("endDate") != "") {
			Date startDate = dateFormat.parse(request.getParameter("startDate"));
			Date endDate = dateFormat.parse(request.getParameter("endDate"));
			map = lineOrderService.getAllByStatusAndUser(id, null, null, page, "sellCdt", startDate, endDate);
		} else {
			map = lineOrderService.getAllByStatusAndUser(id, null, status, page, "sellCdt", null, null);
		}
		List<LineOrder> lineOrders = map.keySet().iterator().next();
		int totalPages = map.get(lineOrders);
		model.addAttribute("lineOrders", lineOrders);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("status", request.getParameter("status"));
		model.addAttribute("days", request.getParameter("days"));
		model.addAttribute("startDate", request.getParameter("startDate"));
		model.addAttribute("endDate", request.getParameter("endDate"));
		model.addAttribute("rate", coinService.selectAll().get(0).getCoinRate());
		model.addAttribute("type", "onlineSellRecord");
		return Jsp.user_onlinesSellRecord;
	}

	/*
	 * 线上卖出记录（分页）
	 */
	@RequestMapping("/trading/onlineSellRecordByPage")
	public void onlineSellRecordByPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = getUser(request).getUserId();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy年MM月dd日");
		String content = "";
		Integer status = null;
		Map<List<LineOrder>, Integer> map;
		int page = 1;
		if (request.getParameter("index") != null && request.getParameter("index") != "") {
			page = Integer.parseInt(request.getParameter("index"));
		}
		if (request.getParameter("status") != null && request.getParameter("status") != "") {
			status = Integer.parseInt(request.getParameter("status"));
		}
		if (request.getParameter("days") != null && request.getParameter("days") != "") {
			Calendar startCalendar = Calendar.getInstance();
			Calendar endCalendar = Calendar.getInstance();
			startCalendar.add(Calendar.DAY_OF_MONTH, 0 - Integer.parseInt(request.getParameter("days")));
			map = lineOrderService.getAllByStatusAndUser(id, null, status, page, "sellCdt", startCalendar.getTime(),
					endCalendar.getTime());
		} else if (request.getParameter("startDate") != null && request.getParameter("startDate") != "") {
			map = lineOrderService.getAllByStatusAndUser(id, null, status, page, "sellCdt",
					dateFormat1.parse(request.getParameter("startDate")),
					dateFormat1.parse(request.getParameter("endDate")));
		} else {
			map = lineOrderService.getAllByStatusAndUser(id, null, status, page, "sellCdt", null, null);
		}
		List<LineOrder> lineOrders = map.keySet().iterator().next();
		content += "<table><tr><th>时间</th><th>价格</th><th>卖出量</th><th>手续费</th><th>状态</th><th>成交时间</th></tr>";
		for (LineOrder order : lineOrders) {
			content += "<tr><td>" + dateFormat.format(order.getSellCdt()) + "</td><td>" + order.getPrice() + "</td><td>"
					+ order.getSellCoin() + "</td><td>"
					+ order.getSellCoin().multiply(coinService.selectAll().get(0).getCoinRate()).setScale(4,
							BigDecimal.ROUND_HALF_UP)
					+ "</td>";
			switch (order.getStatus()) {
			case 1:
				content += "<td>已完成</td><td>" + dateFormat.format(order.getBuyCdt()) + "</td></tr>";
				break;
			case 2:
				content += "<td>挂单中</td><td></td></tr>";
				break;
			case 3:
				content += "<td>已撤回</td><td></td></tr>";
				break;
			default:
				break;
			}
		}
		content += "</table>";
		try {
			response.setContentType("text/xml;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 财务中心页面
	@RequestMapping(value = "/finance/coin", method = RequestMethod.GET)
	public String user_finance_coin(Model model, HttpServletRequest req, int Type) {
		User user = getUser(req);
		// 查询货币类型
		List<Coin> list = coinService.selectAll();
		if (Type == 0) {
			Type = list.get(0).getCoinId();
		}
		Coin coin = coinService.selectByCoinId(Type);
		// 获取用户的账户人民币余额
		Money my = userService.selectMoneyByUserId(user.getUserId());
		// 如果用户的此钱包没有则进行注册
		Wallet wallet = walletService.select(user.getUserId(), Type);
		if (wallet == null) {
			walletService.insertWallet(user.getUserId(), Type);
			Wallet obj = walletService.select(user.getUserId(), Type);

			model.addAttribute(K.obj, obj);
			model.addAttribute(K.list, list);
			model.addAttribute("coinName", coin.getCoinName());
			return Jsp.user_wallet;
		}

		if (req.getParameter("out") != null && req.getParameter("out") != "" && req.getParameter("out").equals("1")) {
			System.out.println("-=-=-=-=-=-=");
			List<WithDraw> records = withDrawService.selectWithDrawByUserId(getUser(req).getUserId());
			model.addAttribute("iort", 1);
			model.addAttribute("records", records);
		} else {
			List<RechargeRecord> records = rechargeRecordService.getAllRecordByPageNow(getUser(req).getUserId(), 1)
					.keySet().iterator().next();
			model.addAttribute("iort", 0);
			model.addAttribute("records", records);
		}
		// 若存在直接进行查询返回
		model.addAttribute("money", my.getMoney());
		model.addAttribute(K.obj, wallet);
		model.addAttribute(K.list, list);
		model.addAttribute("coinName", coin.getCoinName());
		model.addAttribute("Type", Type);
		return Jsp.user_wallet;
	}

	/*
	 * =========================================================================
	 * =========
	 * 用户出金相关===================================================================
	 * =======
	 * =========================================================================
	 * =========
	 */

	// 出金页面
	@RequestMapping(value = "/wallet/toWithDraw", method = RequestMethod.GET)
	public String user_wallet_toWithDraw(Model model, HttpServletRequest req) {
		User user = getUser(req);
		Money money = userService.selectMoneyByUserId(user.getUserId());
		model.addAttribute("userMoney", money);
		model.addAttribute("mobile", user.getUsername());
		return Jsp.user_withDraw;
	}

	// 出金(体现操作)
	@RequestMapping(value = "/wallet/withDraw", method = RequestMethod.POST)
	public String user_wallet_withDraw(Model model, HttpServletRequest req, String drawMoney, String zfbNumber,
			String dealPwd, String mobile, String verifyCode) {
		User user = getUser(req);
		User obj = userService.selectByUserId(user.getUserId());

		if (obj.getDealPwd() == null) {// 资金密码为空时，重定向到设置资金密码页面
			return "redirect:/user/safe/toDealPwdSet";
		}
		Money money = userService.selectMoneyByUserId(user.getUserId());
		if (obj.getAuthStatus() == V.user_authStatus_no) {// 未进行实名认证
			model.addAttribute(K.msg, "请您先进行实名认证!");
			model.addAttribute("userMoney", money);
			return Jsp.user_withDraw;
		} else if (obj.getAuthStatus() == V.user_authStatus_wait) {
			model.addAttribute(K.msg, "请等待实名认证通过后在操作!");
			model.addAttribute("userMoney", money);
			return Jsp.user_withDraw;
		} else if (obj.getAuthStatus() == V.user_authStatus_pass) {
			model.addAttribute(K.msg, "未通过实名认证，请重新填写认证信息!");
			model.addAttribute("userMoney", money);
			return Jsp.user_withDraw;
		}
		// 判断验证码是否正确
		Auth auth = userService.selectAuthByUserId(obj.getUserId());
		String realVerifyCode = smsService.getSmsVerifyCode(mobile);
		if (!StringUtils.isEmpty(verifyCode) && verifyCode.equals(realVerifyCode)) {
			// 判断资金密码是否正确
			if (obj.getDealPwd().equals(PwdTools.ecodingPwd(dealPwd, K.md5))) {// 资金密码正确
				// 添加提现记录
				withDrawService.insertWithDraw(obj.getUserId(), drawMoney, zfbNumber, mobile, auth.getName());
				// 减少用户账户的余额
				userService.updateMoneyByUserId(obj.getUserId(), money.getMoney(), drawMoney);
				withDrawService.insertContent(obj.getUserId(), Text.withDraw_text_wait, V.content_type_withDraw);
				System.out.println("添加完毕，并更新了用户的账户的余额");
			} else {// 资金密码不正确
				model.addAttribute(K.msg, "资金密码不正确!");
				model.addAttribute("userMoney", money);
				return Jsp.user_withDraw;
			}
		} else {// 不正确
			model.addAttribute(K.msg, "短信验证码不正确!");
			model.addAttribute("userMoney", money);
			return Jsp.user_withDraw;
		}
		return "redirect:/user/account/gold?Type=-1";
	}

	// 出金页面记录
	@RequestMapping(value = "/account/gold", method = RequestMethod.GET)
	public String account_gold(Model model, HttpServletRequest req, @RequestParam Integer Type) {
		int page = 1;
		// 查询货币类型
		List<Coin> list = coinService.selectAll();
		if (Type == 0) {
			Type = list.get(0).getCoinId();
		}
		Map<List<WithDraw>, Integer> map = withDrawService.selectWithDrawAndTatolPageByPage(getUser(req).getUserId(),
				page);
		List<WithDraw> recordList = map.keySet().iterator().next();
		int totalPages = map.get(recordList);
		// 若存在直接进行查询返回
		model.addAttribute("totalPages", totalPages);
		model.addAttribute(K.list, list);
		model.addAttribute("recordList", recordList);
		model.addAttribute("Type", Type);
		return Jsp.user_outGold;
	}

	// 出金页面分页
	@RequestMapping(value = "/account/goldByPage")
	public void account_goldByPage(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam Integer index, @RequestParam Integer limitTime) {
		Map<List<WithDraw>, Integer> map = new HashMap<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 初始化html元素
		String content = "<table><tr><th>时间</th><th>提现金额</th><th>提现状态</th><th>支付宝账号</th></tr>";
		String fenye = "";
		int page = index;
		if (limitTime == -1) {
			map = withDrawService.selectWithDrawAndTatolPageByPage(getUser(request).getUserId(), page);
		} else {
			Calendar startCalendar = Calendar.getInstance();
			Calendar endCalendar = Calendar.getInstance();
			startCalendar.add(Calendar.DAY_OF_MONTH, 0 - limitTime);
			map = withDrawService.selectWithDrawAndTatolPageByPageDate(getUser(request).getUserId(),
					startCalendar.getTime(), endCalendar.getTime(), page);
		}
		List<WithDraw> recordList = map.keySet().iterator().next();
		int sumpage = map.get(recordList);
		if (sumpage > 1) {
			fenye += "<ul><li style=\"border-right: 1px solid #4d8db4;\"><a id=\"last_id\">《</a></li>";
			for (int i = 1; i <= sumpage; i++) {
				fenye += "<li style=\"border-right: 1px solid #4d8db4;\"><a id=\"" + i + "\" class=\"indexbutton\">" + i
						+ "</a></li>";
			}
			fenye += "<li style=\"border-right: 1px solid #4d8db4;\"><a id=\"next_id\">》</a></li></ul>";
		} else {
			fenye += "<ul><li style=\"border-right: 1px solid #4d8db4;\"><a id=\"" + sumpage
					+ "\" class=\"indexbutton\">" + sumpage + "</a></li></ul>";
		}
		for (WithDraw record : recordList) {
			content += "<tr><td>" + dateFormat.format(record.getCdt()) + "</td><td>" + record.getDrawMoney().toString()
					+ "</td>";
			switch (record.getStatus()) {
			case 0:
				content += "<td>已提现(通过) </td>";
				break;
			case 1:
				content += "<td>待审核(审核中)</td>";
				break;
			case 2:
				content += "<td>未通过</td>";
				break;
			default:
				content += "<td></td>";
				break;
			}
			content += "<td>" + record.getZfbNumber() + "</td>";

			content += "</tr>";
		}
		content += "</table>";
		try {
			response.setContentType("text/xml;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(content + "||" + fenye);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * =========================================================================
	 * ============== 用户出金相关
	 * END======================================================================
	 * ======
	 * =========================================================================
	 * ==============
	 */

	/*
	 * =========================================================================
	 * ============== 用户入金相关
	 * START====================================================================
	 * =======
	 * =========================================================================
	 * ==============
	 */
	// 用户充值页面
	@RequestMapping(value = "/account/toRecharge")
	public String user_toRecharge(@RequestParam Integer Type, HttpServletRequest request, Model model) {
		// String adress = request.getParameter("adress");
		List<Coin> list = coinService.selectAll();
		if (Type == 0) {
			Type = list.get(0).getCoinId();
		}
		Coin coin = coinService.selectByCoinId(Type);
		if (request.getParameter("errorCode") != null && request.getParameter("errorCode") != "") {
			int errorCode = Integer.parseInt(request.getParameter("errorCode"));
			if (errorCode == 1) {
				model.addAttribute("error", "交易密码错误");
			} else if (errorCode == 2) {
				model.addAttribute("error", "钱包地址错误");
			} else if (errorCode == 3) {
				model.addAttribute("error", "充值金额不得小于100");
			} else if (errorCode == 4) {
				model.addAttribute("error", "请输入支付宝账号");
			} else if (errorCode == 5) {
				model.addAttribute("error", "充值金额只能是整数");
			}
		}
		model.addAttribute(K.list, list);
		model.addAttribute("Type", Type);
		model.addAttribute("coinName", coin.getCoinName());
		model.addAttribute("ptAccount", managerService.selectAllocationAll().getPayZfb());
		model.addAttribute("adress", walletService.select0(getUser(request).getUserId()).getAdress());
		return Jsp.user_toRecharge;
	}

	// 用户充值流程
	@RequestMapping(value = "/account/recharge")
	public String user_Recharge(@RequestParam Integer Type, HttpServletRequest request, Model model) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
		// 查询货币类型
		List<Coin> list = coinService.selectAll();
		int page = 1;
		Map<List<RechargeRecord>, Integer> map;
		if (request.getParameter("startDate") != null && request.getParameter("startDate") != ""
				&& request.getParameter("endDate") != null && request.getParameter("endDate") != "") {
			Date startDate = dateFormat.parse(request.getParameter("startDate"));
			Date endDate = dateFormat.parse(request.getParameter("endDate"));
			map = rechargeRecordService.getRecordByDatePage(getUser(request).getUserId(), startDate, endDate, page);
		} else {
			map = rechargeRecordService.getAllRecordByPage(getUser(request).getUserId(), page);
		}
		List<RechargeRecord> recordList = map.keySet().iterator().next();
		int totalPages = map.get(recordList);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("recordList", recordList);
		model.addAttribute("startDate", request.getParameter("startDate"));
		model.addAttribute("endDate", request.getParameter("endDate"));
		model.addAttribute(K.list, list);
		model.addAttribute("Type", Type);
		return Jsp.user_toInOut;
	}

	// 用户充值分页
	@RequestMapping(value = "/account/rechargeByPage")
	public void user_Recharge(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam Integer index, @RequestParam Integer limitTime) throws Exception {
		Map<List<RechargeRecord>, Integer> map = new HashMap<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy年MM月dd日");
		// 初始化html元素
		String content = "<table><tr><th>充值数量</th><th>账户余额</th><th>充值状态</th><th>充值时间</th><th>到账时间</th></tr>";
		String fenye = "";
		int page = index;
		if (request.getParameter("startDate") != null && request.getParameter("startDate") != ""
				&& request.getParameter("endDate") != null && request.getParameter("endDate") != "") {
			Date startDate = dateFormat1.parse(request.getParameter("startDate"));
			Date endDate = dateFormat1.parse(request.getParameter("endDate"));
			map = rechargeRecordService.getRecordByDatePage(getUser(request).getUserId(), startDate, endDate, page);
		} else if (limitTime == -1) {
			map = rechargeRecordService.getAllRecordByPage(getUser(request).getUserId(), page);
		} else {
			Calendar startCalendar = Calendar.getInstance();
			Calendar endCalendar = Calendar.getInstance();
			startCalendar.add(Calendar.DAY_OF_MONTH, 0 - limitTime);
			map = rechargeRecordService.getRecordByDatePage(getUser(request).getUserId(), startCalendar.getTime(),
					endCalendar.getTime(), index);
		}
		List<RechargeRecord> recordList = map.keySet().iterator().next();
		int sumpage = map.get(recordList);
		if (sumpage > 1) {
			fenye += "<ul><li style=\"border-right: 1px solid #4d8db4;\"><a id=\"last_id\">《</a></li>";
			for (int i = 1; i <= sumpage; i++) {
				fenye += "<li style=\"border-right: 1px solid #4d8db4;\"><a id=\"" + i + "\" class=\"indexbutton\">" + i
						+ "</a></li>";
			}
			fenye += "<li style=\"border-right: 1px solid #4d8db4;\"><a id=\"next_id\">》</a></li></ul>";
		} else {
			fenye += "<ul><li style=\"border-right: 1px solid #4d8db4;\"><a id=\"" + sumpage
					+ "\" class=\"indexbutton\">" + sumpage + "</a></li></ul>";
		}
		for (RechargeRecord record : recordList) {
			content += "<tr><td>" + record.getRechargeQuantity() + "</td><td>" + record.getBalance() + "</td>";
			switch (record.getStatus()) {
			case V.user_recharge_wait:
				content += "<td>已提交</td>";
				break;
			case V.user_recharge_check:
				content += "<td>审核中</td>";
				break;
			case V.user_recharge_yes:
				content += "<td>已完成</td>";
				break;
			case V.user_recharge_no:
				content += "<td>未通过</td>";
				break;
			default:
				content += "<td></td>";
				break;
			}
			content += "<td>" + dateFormat.format(record.getFromDate()) + "</td>";
			if (record.getToDate() == null) {
				content += "<td></td>";
			} else {
				content += "<td>" + dateFormat.format(record.getToDate()) + "</td>";
			}
			content += "</tr>";
		}
		content += "</table>";
		try {
			response.setContentType("text/xml;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(content + "||" + fenye);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 用户充值数据处理流程
	@RequestMapping(value = "/account/rechargeByData")
	public void user_RechargeByData(@RequestParam Integer Type, @RequestParam String amount,
			@RequestParam String adress, @RequestParam String pwd, HttpServletRequest request,
			@RequestParam String payment, HttpServletResponse response, Model model) throws Exception {
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		// 获取该会话的用户
		User user = userService.selectByUserId(getUser(request).getUserId());
		User obj = userService.selectByUserId(user.getUserId());
		Integer chongzhiAmount;
		System.out.println(user.getDealPwd());
		Wallet wallet = walletService.select(user.getUserId(), Type);
		if (user.getDealPwd() == null || user.getDealPwd() == "") {
			response.getWriter().write("user/safe/toDealPwdSet?errorCode=0");
			return;
		}
		pwd = PwdTools.ecodingPwd(pwd, "md5");
		System.out.println(pwd);
		// 密码错误
		if (!pwd.equals(obj.getDealPwd())) {
			response.getWriter().write("user/account/toRecharge?Type=" + Type + "&errorCode=" + 1);
			return;
		}
		// 地址错误
		if (!adress.equals(wallet.getAdress())) {
			response.getWriter().write("user/account/toRecharge?Type=" + Type + "&errorCode=" + 2);
			return;
		}

		try {
			chongzhiAmount = Integer.parseInt(amount);
		} catch (Exception e) {
			response.getWriter().write("user/account/toRecharge?Type=" + Type + "&errorCode=" + 5);
			return;
		}
		// 充值数量
		if (chongzhiAmount < 100) {
			response.getWriter().write("user/account/toRecharge?Type=" + Type + "&errorCode=" + 3);
			return;
		}
		// 账号错误
		if (payment == "") {
			response.getWriter().write("user/account/toRecharge?Type=" + Type + "&errorCode=" + 4);
			return;
		}
		BigDecimal decimalAmount = BigDecimal.valueOf(Double.parseDouble(amount));
		// 增加充值记录
		rechargeRecordService.insert_Recharge(user, decimalAmount, adress, payment);
		withDrawService.insertContent(user.getUserId(), Text.Recharge_text_wait, V.content_type_recharge);
		response.getWriter().write("success");
		return;
	}
	/*
	 * =========================================================================
	 * ============== 用户入金相关
	 * END======================================================================
	 * =====
	 * =========================================================================
	 * ==============
	 */

	// 资金账号设置页面
	@RequestMapping(value = "/finance/bank", method = RequestMethod.GET)
	public String user_finance_bank(Model model, HttpServletRequest req) {
		// 查询货币类型
		List<Coin> list = coinService.selectAll();
		User user = getUser(req);
		Bank bank = userService.selectBankByUserId(user.getUserId());
		if (bank == null) {
			model.addAttribute(K.obj, null);
		} else {
			model.addAttribute(K.obj, bank);
		}
		model.addAttribute(K.list, list);
		return Jsp.user_bank;
	}

	// 删除银行地址操作
	@RequestMapping(value = "/bank/delete", method = RequestMethod.GET)
	public String user_bank_delete(int bankId) {
		userService.deleteBank(bankId);
		return "redirect:/user/finance/bank";
	}

	// 添加银行地址页面
	@RequestMapping(value = "/finance/bankSet", method = RequestMethod.GET)
	public String user_finance_bankSet(Model model, HttpServletRequest req) {
		User user = getUser(req);
		// 查询货币类型
		List<Coin> list = coinService.selectAll();
		User obj = userService.selectByUserId(user.getUserId());
		// 添加前首先进行实名认证状态的查询
		if (obj.getAuthStatus() == 2) {// 未实名认证
			model.addAttribute("userId", user.getUserId());
			return Jsp.user_auth;
		} else if (obj.getAuthStatus() == 3) {// 审核中
			model.addAttribute(K.msg, "请等待实名认证通过后在操作!");
			model.addAttribute(K.list, list);
			return Jsp.user_bank;
		} else if (obj.getAuthStatus() == 4) {// 未通过
			model.addAttribute(K.msg, "实名认证未通过，请修改后在操作!");
			model.addAttribute(K.list, list);
			return Jsp.user_bank;
		}
		// 信息通过后查询实名信息
		Auth auth = userService.selectAuthByUserId(obj.getUserId());
		model.addAttribute(K.list, list);
		model.addAttribute(K.obj, auth);
		return Jsp.user_bankSet;
	}

	// 添加银行地址操作
	@RequestMapping(value = "/finance/bankSave", method = RequestMethod.POST)
	public String user_finance_bankSave(Model model, HttpServletRequest req, String userName, String zfbNumber,
			String phone, String province, String city, String dealPwd) {
		// 查询货币类型
		List<Coin> list = coinService.selectAll();

		User user = getUser(req);
		User obj = userService.selectByUserId(user.getUserId());
		if (obj.getDealPwd() == null) {
			model.addAttribute(K.msg, "请先设置您的交易密码!");
			// model.addAttribute(K.obj, auth);
			model.addAttribute(K.list, list);
			return Jsp.user_bankSet;
		} else if (!obj.getDealPwd().equals(PwdTools.ecodingPwd(dealPwd, K.md5))) {
			Auth auth = userService.selectAuthByUserId(obj.getUserId());
			model.addAttribute(K.msg, "交易密码不正确!");
			model.addAttribute(K.obj, auth);
			model.addAttribute(K.list, list);
			return Jsp.user_bankSet;
		}

		// 信息正确后添加银行地址信息
		userService.insertBank(obj.getUserId(), userName, zfbNumber, phone, province, city);

		return "redirect:/user/finance/bank";
	}

	// 安全中心页面
	@RequestMapping(value = "/safe/safeCenter", method = RequestMethod.GET)
	public String user_safe_safeCenter(Model model, HttpServletRequest req) {
		User user = getUser(req);
		User obj = userService.selectByUserId(user.getUserId());
		model.addAttribute(K.obj, obj);
		model.addAttribute("name", "safeCenter");
		return Jsp.user_safeCenter;
	}

	// 绑定邮箱界面
	@RequestMapping("/safe/toBindMail")
	public String user_safe_toBindMail(HttpServletRequest request, HttpServletResponse response, Model model) {
		return Jsp.user_toBind;
	}

	// 绑定邮箱流程
	@RequestMapping("/safe/bindMail")
	public void user_safe_bindMail(@RequestParam String email, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<>();
		// 获取当前用户
		User user = userService.selectByUserId(getUser(request).getUserId());
		userService.sendMail(user, email);
		jsonMap.put("status", "1");
		jsonMap.put("message", "验证邮件已发送成功，请查收");
		JSONObject jsonObject = new JSONObject(jsonMap);
		String jsonData = jsonObject.toJSONString();
		try {
			response.setContentType("text/xml;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonData);
			// 当前会话存入email
			request.getSession().setAttribute("email", email);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/safe/verify")
	public String user_safe_mailVerify(@RequestParam String uniqueSign, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = userService.selectByUserId(getUser(request).getUserId());
		// 获取当前用户
		String email = request.getSession().getAttribute("email").toString();
		boolean isTrue = userService.verifyMail(user, uniqueSign, email);
		int Vstatus = isTrue == true ? 1 : 0;
		// 再次获取用户
		user = userService.selectByUserId(getUser(request).getUserId());
		model.addAttribute("Vstatus", Vstatus);
		model.addAttribute(K.obj, user);
		model.addAttribute("name", "safeCenter");
		return Jsp.user_safeCenter;
	}

	// 设置资金密码页面
	@RequestMapping(value = "/safe/toDealPwdSet", method = RequestMethod.GET)
	public String user_safe_toDealPwdSet(Model model, HttpServletRequest req) {
		User user = getUser(req);
		// System.out.println(req.getParameter("errorCode").equals("0"));
		if (req.getParameter("errorCode") != null && req.getParameter("errorCode").equals("0")) {
			model.addAttribute("error", "用户未设置交易密码");
		}
		model.addAttribute(K.obj, user);
		model.addAttribute("name", "safeCenter");
		return Jsp.user_dealPwdSet;
	}

	// 设置资金密码操作
	@RequestMapping(value = "/safe/dealPwdUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> user_safe_dealPwdUpdate(Model model, int userId, String username, String dealPwd,
			String verifyCode) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 检测短信验证码是否正确
		String realVerifyCode = smsService.getSmsVerifyCode(username);
		if (!StringUtils.isEmpty(verifyCode) && verifyCode.equals(realVerifyCode)) {// 正确
			userService.updateDealPwd(userId, dealPwd);
			withDrawService.insertContent(userId, Text.safe_dealPwd_update, V.content_type_safe);
			System.out.println("更新资金密码完毕!");
			smsService.removeSmsVerifyCode(username);// 若账号更改成功则删除验证码
			result.put(K.result, true);
			result.put(K.msg, "设置成功!");
			return result;
		} else {// 错误
			result.put(K.result, false);
			result.put(K.msg, "短信验证码错误!");
			return result;
		}
	}

	// 重置登录密码页面
	@RequestMapping(value = "/safe/lgPwdSet", method = RequestMethod.GET)
	public String user_safe_toLoginPwdChange(Model model, HttpServletRequest req) {
		User user = getUser(req);
		model.addAttribute("name", "safeCenter");
		model.addAttribute(K.obj, user);
		return Jsp.user_loginPwdSet;
	}

	// 重置登录密码操作
	@RequestMapping(value = "/safe/lgPwdUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> user_safe_loginPwdUpdate(Model model, String userId, String oldpassword,
			String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		System.out.println(userId + "================");
		User user = userService.selectByUserId(Integer.parseInt(userId.trim()));
		System.out.println(user.getPassword());
		System.out.println(PwdTools.ecodingPwd(oldpassword, K.md5));
		// 首先检测旧密码是否正确
		if (!user.getPassword().equals(PwdTools.ecodingPwd(oldpassword, K.md5))) {
			result.put(K.result, false);
			result.put(K.msg, "旧密码错误!");
			return result;
		}
		userService.updateLoginPwd(Integer.parseInt(userId), password);
		withDrawService.insertContent(Integer.parseInt(userId), Text.safe_loginPwd_update, V.content_type_safe);
		result.put(K.result, true);
		result.put(K.msg, "修改成功!");
		result.put("userId", userId);
		return result;
	}

	// 安全记录页面
	@RequestMapping(value = "/safe/toLoginHistroy", method = RequestMethod.GET)
	public String user_safe_toLoginHistroy(Model model, HttpServletRequest req) {
		User user = getUser(req);
		List<LoginHistroy> list = userService.selectHistroyByUserId(user.getUserId());
		// model.addAttribute("userId", userId);
		model.addAttribute(K.list, list);
		model.addAttribute("name", "toLoginHistroy");
		return Jsp.user_loginHistroy;
	}

	// 实名认证页面填写
	@RequestMapping(value = "/safe/user_toAuth", method = RequestMethod.GET)
	public String user_safe_user_toAuth(Model model, HttpServletRequest req) {
		int userId = getUser(req).getUserId();
		model.addAttribute("userId", userId);
		model.addAttribute("name", "user_toAuth");
		return Jsp.user_auth;
	}

	// 实名认证页面
	@RequestMapping(value = "/safe/toAuth", method = RequestMethod.GET)
	public String user_safe_toAuth(Model model, HttpServletRequest req) {
		User user = getUser(req);
		User obj = userService.selectByUserId(user.getUserId());
		if (obj.getAuthStatus() != 2) {
			Auth auth = userService.selectAuthByUserId(obj.getUserId());
			model.addAttribute(K.obj, obj);
			model.addAttribute("auth", auth);
			model.addAttribute("name", "user_toAuth");
			return Jsp.user_authStatus;

		}
		model.addAttribute(K.obj, obj);
		model.addAttribute("name", "user_toAuth");
		return Jsp.user_auth;
	}

	// 实名认证操作
	@RequestMapping(value = "/safe/user_Auth", method = RequestMethod.POST)
	public String safe_user_Auth(Model model, int userId, String authName, int sex, int cardType, String card,
			String frontImg, String backImg, String allImg, @RequestParam("photoFront") CommonsMultipartFile photoFront,
			@RequestParam("photoBack") CommonsMultipartFile photoBack,
			@RequestParam("photoAll") CommonsMultipartFile photoAll) {

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
		// 检测是添加还是更新
		if (userService.selectAuthByUserId(userId) == null) {// 添加
			userService.insertUserAuth(userId, authName, sex, cardType, card, frontImg, backImg, allImg);
			userService.updateAuthStatus(userId, V.user_authStatus_wait);
			withDrawService.insertContent(userId, Text.auth_waite, V.content_type_auth);
			return "redirect:/user/safe/toAuth";
		} else {// 更新
			userService.updateAuth(userId, authName, sex, cardType, card, frontImg, backImg, allImg);
			userService.updateAuthStatus(userId, V.user_authStatus_wait);
			withDrawService.insertContent(userId, Text.auth_waite, V.content_type_auth);
			return "redirect:/user/safe/toAuth";
		}
	}

	// 我的消息页面
	@RequestMapping(value = "/safe/content", method = RequestMethod.GET)
	public String safe_content(Model model, HttpServletRequest req, String pageNow) {
		User user = getUser(req);
		withDrawService.showContentByPage(model, pageNow, user.getUserId());
		String url = "user/safe/content?pageNow=";
		model.addAttribute(K.url, url);
		model.addAttribute("name", "content");
		return Jsp.user_content;
	}

	// 根据不同类型查询不同消息
	@RequestMapping(value = "/safe/content/meaasge", method = RequestMethod.GET)
	public String safe_content_message(Model model, HttpServletRequest req, int type, String pageNow) {
		User user = getUser(req);
		withDrawService.showContentTypeByPage(model, pageNow, user.getUserId(), type);
		String url = "user/safe/content?type=" + type + "&pageNow=";
		model.addAttribute(K.url, url);
		model.addAttribute("name", "content");
		return Jsp.user_content;
	}

	// 批量删除某个用户我的消息
	@RequestMapping(value = "/content/batchDeletes", method = RequestMethod.POST)
	public void batchDeletes(HttpServletRequest req, HttpServletResponse res) {
		String items = req.getParameter("delitems");
		List<String> delList = new ArrayList<String>();
		String[] strs = items.split(",");
		for (String str : strs) {
			delList.add(str);
		}
		withDrawService.deleteMore(delList);
	}

	// 退出登录操作
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest req) {
		// 清除session
		req.getSession().removeAttribute(K.sessionUser);
		return Jsp.user_index_net;

	}

	/**
	 * 新闻资讯||公司功能公告页面
	 */
	@RequestMapping("/news/message")
	public String newsMessage() {
		return Jsp.user_notice;
	}

	/**
	 * 媒体报道页面
	 */
	@RequestMapping("/news/media_message")
	public String media() {
		return Jsp.user_media;
	}

	/**
	 * 业内咨询页面
	 */
	@RequestMapping("/news/advisory_message")
	public String advisory() {
		return Jsp.user_media;
	}

	/*
	 * 获得文章详情
	 */
	@RequestMapping("/*/get_article")
	public String getArticle(@RequestParam Integer id, Model model, HttpServletRequest request) {
		// 获取请求的url地址
		String reuquestUrl = request.getRequestURI();
		// 获取url字符串中的文章类型字符串
		String reflactClassName = StringSub.getRequestSub(reuquestUrl, "user/", "/get_article");
		// 根据文章类型和文章id查找相应文章
		Article article = articleService.getArticle(id, reflactClassName);
		model.addAttribute("article", article);
		if (reflactClassName.equals(ArticleType.MediaArticle)) {
			return Jsp.user_media_context;
		} else if (reflactClassName.equals(ArticleType.NoticeArticle)) {
			return Jsp.user_notice_context;
		} else {
			return Jsp.user_advistory_context;
		}
	}

	/**
	 * 获得文章列表
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/*/get_articleList")
	public String getArticleList(HttpServletRequest request, Model model) {
		// 默认第一页
		Integer page = 1;
		String reuquestUrl = request.getRequestURI();
		String reflactClassName = StringSub.getRequestSub(reuquestUrl, "user/", "/get_articleList");
		// map（该页的文章列表，分页总页数）
		Map<List<Article>, Integer> pageListPageMap = articleService.getArticleList(reflactClassName, page);
		// 获取某页的文章列表
		List<Article> articleList = pageListPageMap.keySet().iterator().next();
		// 获得分页总页数
		int pages = pageListPageMap.get(articleList);
		model.addAttribute("articleList", articleList);
		model.addAttribute("totalPages", pages);
		model.addAttribute("name", reflactClassName);

		if (reflactClassName.equals(ArticleType.MediaArticle)) {
			return Jsp.user_media;
		} else if (reflactClassName.equals(ArticleType.NoticeArticle)) {
			return Jsp.user_notice;
		} else {
			return Jsp.user_advisory;
		}
	}

	@RequestMapping("/*/get_articleListByAjax")
	public void getArticleListByAjax(@RequestParam Integer index, HttpServletRequest request,
			HttpServletResponse response) {
		String content = "";
		String reuquestUrl = request.getRequestURI();
		String reflactClassName = StringSub.getRequestSub(reuquestUrl, "user/", "/get_articleListByAjax");
		Map<List<Article>, Integer> pageListPageMap = articleService.getArticleList(reflactClassName, index);
		List<Article> articleList = pageListPageMap.keySet().iterator().next();
		// 迭代该页文章列表的文章
		for (int i = 1; i <= articleList.size(); i = i + 2) {

			content += "<div class=\"Med_tit\"> <div class=\"Med_list_tit\"> <div class=\"Img_show\"> <a href=\"#\"> <img src=\"img/f4351976abd842cfae11c1fa5bbc7802.png\" /> </a> </div> <a class=\"article_title\" id=\""
					+ articleList.get(i - 1).getId() + "\">" + articleList.get(i - 1).getTitle() + "</a></div>";
			if (i < articleList.size()) {
				content += "<div class=\"Med_list_tit\"> <div class=\"Img_show\"> <a href=\"#\"> <img src=\"img/4d3223ae955d43c8a107fc13f57df528.jpg\" /> </a> </div> <a class=\"article_title\" id=\""
						+ articleList.get(i).getId() + "\">" + articleList.get(i).getTitle() + "</a> </div></div>";
			}
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

	@RequestMapping("/*/get_ArticleInfoByAjax")
	public void getArticleInfoByAjax(@RequestParam Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		String reuquestUrl = request.getRequestURI();
		String reflactClassName = StringSub.getRequestSub(reuquestUrl, "user/", "/get_ArticleInfoByAjax");
		// 获得当前文章
		Article article = articleService.getArticle(id, reflactClassName);
		// 获得上一篇文章
		Article lastArticle = articleService.getLastArticle(id, reflactClassName);
		if (lastArticle == null) {
			lastArticle = article;
		}
		// 获得下一篇文章
		Article nextArticle = articleService.getNextArticle(id, reflactClassName);
		if (nextArticle == null) {
			nextArticle = article;
		}
		String jsonData = "{\"lastarticle\":\"" + lastArticle.getTitle() + "\",\"nextarticle\":\""
				+ nextArticle.getTitle() + "\",\"lastid\":" + lastArticle.getId() + ",\"nextid\":" + nextArticle.getId()
				+ "}";
		System.out.println(jsonData);
		try {
			response.setContentType("text/xml;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 图片验证码接口
	 * 
	 * @param req
	 * @param res
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping(value = "/imgCode", method = RequestMethod.GET)
	public void imgCode(HttpServletRequest req, HttpServletResponse res, HttpSession session) throws IOException {
		DefaultKaptcha kaptchaProducer = new DefaultKaptcha();
		Properties p = new Properties();
		// p.put(Constants.kaptcha_image_width, 60);
		// p.put(Constants.kaptcha_image_height, 40);
		p.put(Constants.kaptcha_border, "no");
		p.put(Constants.kaptcha_background_clear_from, "244,244,244");
		p.put(Constants.kaptcha_textproducer_font_names, "楷体");
		p.put(Constants.kaptcha_textproducer_char_string, "0123456789");
		p.put(Constants.kaptcha_textproducer_char_length, "4");
		p.put(Constants.kaptcha_textproducer_char_space, "5");
		p.put(Constants.kaptcha_textproducer_font_color, "130,167,208");
		p.put(Constants.kaptcha_noise_color, "blue");
		p.put(Constants.kaptcha_noise_impl, "com.google.code.kaptcha.impl.NoNoise");
		com.google.code.kaptcha.util.Config config = new com.google.code.kaptcha.util.Config(p);
		kaptchaProducer.setConfig(config);
		String capText = kaptchaProducer.createText();
		session.setAttribute(Constants.IMAGE_CODE_TEXT, capText);
		session.setAttribute(Constants.IMAGE_CODE_TIME, new Date());
		res.setDateHeader("Expires", 0L);
		res.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		res.addHeader("Cache-Control", "post-check=0, pre-check=0");
		res.setHeader("Pragma", "no-cache");
		res.setContentType("image/jpeg");
		BufferedImage bi = kaptchaProducer.createImage(capText);
		ServletOutputStream out = res.getOutputStream();
		ImageIO.write(bi, "jpg", out);
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

	/**
	 * 短信验证码获取接口
	 * 
	 * @param model
	 * @param req
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/smsVerifyCode", method = RequestMethod.GET)
	@ResponseBody
	public String smsVerifyCode(Model model, HttpServletRequest req, String mobile) {
		String verifyCode = String.valueOf(SMSVerifyCodeTools.getSmsVerifyCode());
		smsService.putSmsVerifyCode(mobile, verifyCode);
//		 boolean sendOK = smsService.sendVerifyCode(mobile, verifyCode);
//		 return sendOK ? "success" : "fail";
		return "success";
	}

	@RequestMapping("/web/info")
	public void getInfo(@RequestParam String info, HttpServletRequest req, HttpServletResponse response) {
		System.out.println(info);
		String content = webInfoService.getInfo(info);
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

	// 获得文章列表的方法
	public List<Article> getIndexNetData(String typeName) {
		Map<List<Article>, Integer> PageListPageMap = articleService.getArticleList(typeName, 1);
		// 获取某页的文章列表
		List<Article> ArticleList = PageListPageMap.keySet().iterator().next();
		return ArticleList;
	}

}