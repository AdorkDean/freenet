package com.freenet.service;

import java.math.BigDecimal;

import com.freenet.domain.LineOrder;
import com.freenet.domain.Wallet;

public interface WalletService {
	
	//添加一个用户钱包
	//int insertWallet(int userId);
	int insertWallet(int userId,int coinId);
	
	//根据用户id跟货币类型id查询
	Wallet select0(int userId);
	Wallet select(int userId,int coinId);
	
	void updateCoin(int userId,  BigDecimal money);
	
	void updateBuyContent(int id , LineOrder lineOrder);
	void updateSellContent(int sellUserId,BigDecimal amount);
	void updateBackContent(int sellUserId,BigDecimal amount);
	
	//更新钱包中的冻结货币
	int updateCoinFrozen(BigDecimal coinFrozen,int userId,int coinId);
	
	//管理员添加用户的钱包
	int insertWalletByManager(int userId,int coinId,String coin);
	
	
}
