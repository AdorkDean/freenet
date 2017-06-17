package com.freenet.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.freenet.domain.OpenClosePrice;

public interface OpenClosePriceDao {
	// 增减新的价格数据
	int insertNewPrice(OpenClosePrice openClosePrice);

	// 增加收盘价
	int updateClosePrice(Date inDay,BigDecimal ClosePrice);

	// 获取某日的记录
	OpenClosePrice selectPriceByDate(Date inDay);
	
	//获得最后一条记录
	OpenClosePrice selectLastPrice();
	
	// 删除某记录
	int deletePriceByDate(Date inDay);

	// 获取所有记录
	List<OpenClosePrice> selectAllPrice();
}
