package com.freenet.service.Impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.freenet.dao.CoinDao;
import com.freenet.dao.WalletDao;
import com.freenet.domain.LineOrder;
import com.freenet.domain.Wallet;
import com.freenet.service.WalletService;
import com.freenet.tools.FreeUtils;

@Service
public class WalletServiceImpl implements WalletService {

	@Resource
	private WalletDao walletDao;

	@Resource
	private CoinDao coinDao;

	// 添加一个用户钱包
	public int insertWallet(int userId, int coinId) {
		Wallet wl = new Wallet();
		wl.setUserId(userId);
		wl.setCoinId(coinId);
		wl.setCoin(new BigDecimal("0"));
		wl.setAdress(FreeUtils.getRandomString(32));
		wl.setRmbRech(new BigDecimal("0"));
		wl.setCoinFrozen(new BigDecimal("0"));
		wl.setCdt(new Date());
		return walletDao.insertWallet(wl);
	}

	// 根据用户id跟货币类型id查询
	public Wallet select(int userId, int coinId) {
		return walletDao.select(userId, coinId);
	}

	// 对用户钱包中的货币量进行修改
	@Override
	public void updateCoin(int userId, BigDecimal coinMoney) {
		walletDao.updateCoin(userId, coinMoney);
	}

	@Override
	public Wallet select0(int userId) {
		return walletDao.select0(userId);
	}

	@Override
	public void updateBuyContent(int id, LineOrder lineOrder) {
		// 增加购买用户的相应虚拟币
		updateCoin(id, select0(id).getCoin().add(lineOrder.getSellCoin()));

		// 扣除出售者相应的手续费
		// if (lineOrder.getType() == Order.order_type_user) {
		// BigDecimal rateFee=
		// lineOrder.getSellCoin().multiply(coinDao.selectAll().get(0).getCoinRate());
		// updateCoin(lineOrder.getSellUserId(),
		// select0(lineOrder.getSellUserId()).getCoin().subtract(rateFee));
		// }
	}

	@Override
	public void updateSellContent(int sellUserId, BigDecimal amount) {
		// 扣除相应数量的货币数量(手续费加卖出数量)
		updateCoin(sellUserId, select0(sellUserId).getCoin().subtract(amount));
		BigDecimal rateFee = amount.multiply(coinDao.selectAll().get(0).getCoinRate());
		updateCoin(sellUserId, select0(sellUserId).getCoin().subtract(rateFee));
	}

	@Override
	public void updateBackContent(int sellUserId, BigDecimal amount) {
		// 用户撤单 退回相应的费用（手续费+卖出量）
		updateCoin(sellUserId, select0(sellUserId).getCoin().add(amount));
		BigDecimal rateFee = amount.multiply(coinDao.selectAll().get(0).getCoinRate());
		updateCoin(sellUserId, select0(sellUserId).getCoin().add(rateFee));
	}
	
	//更新钱包中的冻结货币
	public int updateCoinFrozen(BigDecimal coinFrozen, int userId, int coinId) {
		return walletDao.updateCoinFrozen(coinFrozen, userId, coinId);
	}

	//管理员添加用户的钱包
	public int insertWalletByManager(int userId,int coinId,String coin) {
		Wallet wl = new Wallet();
		wl.setUserId(userId);
		wl.setCoinId(coinId);
		wl.setCoin(new BigDecimal(coin));
		wl.setAdress(FreeUtils.getRandomString(32));
		wl.setRmbRech(new BigDecimal("0"));
		wl.setCoinFrozen(new BigDecimal("0"));
		wl.setCdt(new Date());
		return walletDao.insertWallet(wl);
	}
}
