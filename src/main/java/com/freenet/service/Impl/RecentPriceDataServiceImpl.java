package com.freenet.service.Impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.freenet.dao.OpenClosePriceDao;
import com.freenet.dao.RecentPriceDataDao;
import com.freenet.domain.LineOrder;
import com.freenet.domain.OffOrder;
import com.freenet.domain.OpenClosePrice;
import com.freenet.domain.RecentPriceData;
import com.freenet.service.RecentPriceDataService;

@Service
public class RecentPriceDataServiceImpl implements RecentPriceDataService {

	@Resource
	RecentPriceDataDao recentPriceDataDao;
	@Resource
	OpenClosePriceDao openClosePriceDao;

	@Override
	public void resolveLineOrder(LineOrder lineOrder) {
		// // 计算该订单的单价
		// BigDecimal amount = lineOrder.getSellCoin();
		// BigDecimal sumPrice = lineOrder.getPrice();
		// BigDecimal price = sumPrice.divide(amount, 2,
		// BigDecimal.ROUND_HALF_UP);
		//
		// // 获取该订单的时间
		// Date buyTime = lineOrder.getBuyCdt();
		//
		// // 获取价格趋势表中最后一条记录
		// RecentPriceData priceData = recentPriceDataDao.getPriceData();
		// if (priceData == null) {
		// recentPriceDataDao.insertPriceData(new RecentPriceData(price, price,
		// lineOrder.getBuyCdt()));
		// openClosePriceDao.insertNewPrice(new
		// OpenClosePrice(price,lineOrder.getBuyCdt()));
		// return;
		// }
		//
		// Calendar buyCalendar = Calendar.getInstance();
		// Calendar recentCalendar = Calendar.getInstance();
		// buyCalendar.setTime(buyTime);
		// recentCalendar.setTime(priceData.getStartDate());
		// //若购买的日期与最后一条购买记录不同，则此条为开盘价,最后一个记录为收盘价
		// if(buyCalendar.get(Calendar.DATE)!=recentCalendar.get(Calendar.DATE)){
		//
		// }
		//
		// // 如果此订单据价格趋势表最近一条记录超过五分钟
		// if (buyCalendar.getTimeInMillis() - recentCalendar.getTimeInMillis()
		// > 5 * 60 * 1000) {
		// recentPriceDataDao.insertPriceData(new RecentPriceData(price, price,
		// lineOrder.getBuyCdt()));
		// } else {
		// // 小于最小值
		// if (price.compareTo(priceData.getMinVal()) == -1) {
		// priceData.setMinVal(price);
		// recentPriceDataDao.updatePriceData(priceData.getId(), priceData);
		// // 大于最大值
		// } else if (price.compareTo(priceData.getMaxVal()) == 1) {
		// priceData.setMaxVal(price);
		// recentPriceDataDao.updatePriceData(priceData.getId(), priceData);
		// } else {
		// // do nothing
		// }
		// }

	}

	@Override
	public void resolveOffOrder(OffOrder offOrder) {
		// 获取该订单的时间
		Date buyTime = offOrder.getRealCdt();
		System.out.println(buyTime);

		// 计算该订单的单价
		BigDecimal amount = offOrder.getSellCoin();
		BigDecimal sumPrice = offOrder.getPrice();
		BigDecimal price = sumPrice.divide(amount, 2, BigDecimal.ROUND_HALF_UP);

		// 获取价格趋势表中最后一条记录
		RecentPriceData priceData = recentPriceDataDao.getPriceData();
		if (priceData == null) {
			recentPriceDataDao.insertPriceData(new RecentPriceData(price, price, buyTime));
			// 增加开盘价
			openClosePriceDao.insertNewPrice(new OpenClosePrice(price, price, buyTime));
			return;
		}

		Calendar realCalendar = Calendar.getInstance();
		Calendar recentCalendar = Calendar.getInstance();
		realCalendar.setTime(buyTime);
		recentCalendar.setTime(priceData.getStartDate());
		// 新增开盘价格和收盘价格
		if (realCalendar.get(Calendar.DATE) != recentCalendar.get(Calendar.DATE)) {
			// 得到前一天的收盘价格
			openClosePriceDao.updateClosePrice(openClosePriceDao.selectLastPrice().getInDay(), price);
			// 新增开盘价格
			openClosePriceDao.insertNewPrice(new OpenClosePrice(price, price, buyTime));
		}

		// 如果此订单据价格趋势表最近一条记录超过五分钟
		if (realCalendar.getTimeInMillis() - recentCalendar.getTimeInMillis() > 60 * 1000) {
			// System.out.println(offOrder.getRealCdt());
			recentPriceDataDao.insertPriceData(new RecentPriceData(price, price, offOrder.getRealCdt()));
		} else {
			// 小于最小值
			if (price.compareTo(priceData.getMinVal()) == -1) {
				priceData.setMinVal(price);
				recentPriceDataDao.updatePriceData(priceData.getId(), priceData);
				// 大于最大值
			} else if (price.compareTo(priceData.getMaxVal()) == 1) {
				priceData.setMaxVal(price);
				recentPriceDataDao.updatePriceData(priceData.getId(), priceData);
			} else {
				// do nothing
			}
		}
	}

	// 获取最近时间的价格数据
	@Override
	public List<RecentPriceData> getPriceDataList() {
		List<RecentPriceData> priceDataList = recentPriceDataDao.getAllPriceData();
		return priceDataList;
	}

}
