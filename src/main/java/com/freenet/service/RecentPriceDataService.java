package com.freenet.service;

import java.util.List;

import com.freenet.domain.LineOrder;
import com.freenet.domain.OffOrder;
import com.freenet.domain.RecentPriceData;

public interface RecentPriceDataService {
	//处理当前订单的价格是否在区间
	public void resolveLineOrder(LineOrder lineOrder);
	public void resolveOffOrder(OffOrder offOrder);
	
	//获取当前统计表所需的数据
	public List<RecentPriceData> getPriceDataList();
	
	
}
