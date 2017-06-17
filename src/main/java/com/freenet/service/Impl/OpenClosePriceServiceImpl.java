package com.freenet.service.Impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.freenet.dao.OpenClosePriceDao;
import com.freenet.domain.OpenClosePrice;
import com.freenet.service.OpenClosePriceService;


@Service
public class OpenClosePriceServiceImpl implements OpenClosePriceService {

	@Resource
	private OpenClosePriceDao closePriceDao;

	// 增减新的价格数据
	public void insertNewPrice(OpenClosePrice openClosePrice) {
		closePriceDao.insertNewPrice(openClosePrice);
	}

	// 增加收盘价
	public void updateClosePrice(Date date, BigDecimal ClosePrice) {
		closePriceDao.updateClosePrice(date, ClosePrice);
	}

	// 获取某日的记录
	public OpenClosePrice selectPriceByDate(Date date) {

		return closePriceDao.selectPriceByDate(date);
	}

	// 删除某记录
	public void deletePriceByDate(Date date) {
		closePriceDao.deletePriceByDate(date);
	}

	// 获取所有记录
	public Map<Integer, OpenClosePrice> selectAllPrice() {
		Map<Integer, OpenClosePrice> priceMap = new HashMap<>();
		Calendar calendar = Calendar.getInstance();
		List<OpenClosePrice> priceList = closePriceDao.selectAllPrice();
		for (OpenClosePrice price : priceList) {
			System.out.println(price.getInDay().getDate()+"---"+price.getOpenPrice()+"---"+price.getClosePrice());
			calendar.setTime(price.getInDay());
			priceMap.put(calendar.get(Calendar.DATE), price);
		}
		return priceMap;
	}

}
