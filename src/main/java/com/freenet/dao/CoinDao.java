package com.freenet.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.freenet.domain.Coin;

public interface CoinDao {
	
	//添加货币类型
	int insertCoin(Coin coin);
	
	//更新货币信息
	int updateByCoinId(@Param("coinRate") BigDecimal coinRate,@Param("coinId") int coinId,@Param("coinCount") BigDecimal coinCount);
	
	//查询所有货币
	List<Coin> selectAll();
	
	//查询货币详情
	Coin selectByCoinId(@Param("coinId") int coinId);
	
	

}
