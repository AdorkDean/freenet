package com.freenet.service;

import java.util.List;

import com.freenet.domain.Coin;

public interface CoinService {
	
	//添加货币类型
	int insertCoin(String coinName,String coinRate,String coinCount);
	
	//初始化货币
	int insert();
	
	//更新货币信息
	int updateByCoinId(String coinRate,int coinId,String coinCount);
	
	//查询所有货币
	List<Coin> selectAll();
	
	//查询货币详情
	Coin selectByCoinId(int coinId);
	

	

}
