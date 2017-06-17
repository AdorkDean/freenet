package com.freenet.dao;


import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.freenet.domain.Wallet;

public interface WalletDao {
	
	//添加一个用户钱包
	//int insertWallet(Wallet wallet);
	int insertWallet(Wallet wallet);
	
	//根据用户id跟货币类型id查询
	Wallet select0(@Param("userId") int userId);
	Wallet select(@Param("userId") int userId,@Param("coinId") int coinId);
	
	//修改用户钱包的余额
	void updateCoin(@Param("userId") int userId,@Param("coin")BigDecimal coin);
	
	//更新钱包中的冻结货币
	int updateCoinFrozen(@Param("coinFrozen") BigDecimal coinFrozen,@Param("userId") int userId,@Param("coinId") int coinId);

}
