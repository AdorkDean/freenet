package com.freenet.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.freenet.domain.OpenClosePrice;

public interface OpenClosePriceService {

	// 增减新的价格数据
	void	 insertNewPrice(OpenClosePrice openClosePrice);

	// 增加收盘价
	void updateClosePrice(Date date, BigDecimal ClosePrice);

	// 获取某日的记录
	OpenClosePrice selectPriceByDate(Date date);

	// 删除某记录
	void deletePriceByDate(Date date);

	// 获取所有记录
	Map<Integer, OpenClosePrice> selectAllPrice();

}
