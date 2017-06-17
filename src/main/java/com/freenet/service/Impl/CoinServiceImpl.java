package com.freenet.service.Impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.freenet.dao.CoinDao;
import com.freenet.domain.Coin;
import com.freenet.service.CoinService;

@Service
public class CoinServiceImpl implements CoinService{
	
	@Resource 
	private CoinDao coinDao;

	//添加货币类型
	public int insertCoin(String coinName, String coinRate,String coinCount) {
		Coin coin = new Coin();
		coin.setCoinName(coinName);
		coin.setCoinRate(new BigDecimal(coinRate));
		coin.setCoinCount(new BigDecimal(coinCount));
		coin.setCdt(new Date());
		return coinDao.insertCoin(coin);
	}
	
	//初始化货币
	public int insert() {
		Coin coin = new Coin();
		coin.setCoinName("MC");
		coin.setCoinRate(new BigDecimal("0"));
		coin.setCoinCount(new BigDecimal("0"));
		coin.setCdt(new Date());
		return coinDao.insertCoin(coin);
	}
	
	//更新货币信息
	public int updateByCoinId(String coinRate, int coinId,String coinCount) {
		return coinDao.updateByCoinId(new BigDecimal(coinRate), coinId,new BigDecimal(coinCount));
	}

	//查询所有货币类型
	public List<Coin> selectAll() {
		return coinDao.selectAll();
	}

	//查询货币详情
	public Coin selectByCoinId(int coinId) {
		return coinDao.selectByCoinId(coinId);
	}

	

	

	
	

}
