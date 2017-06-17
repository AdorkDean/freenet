package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.freenet.domain.RecentPriceData;

public class jsonTest {

	public static void main(String[] args) {
		List<RecentPriceData > priceDataList = new ArrayList<>();
		priceDataList.add(new RecentPriceData(new BigDecimal(13), new BigDecimal(32), new Date()));
		priceDataList.add(new RecentPriceData(new BigDecimal(13), new BigDecimal(32), new Date()));
		priceDataList.add(new RecentPriceData(new BigDecimal(13), new BigDecimal(32), new Date()));
		priceDataList.add(new RecentPriceData(new BigDecimal(13), new BigDecimal(32), new Date()));
		priceDataList.add(new RecentPriceData(new BigDecimal(13), new BigDecimal(32), new Date()));
		Map<String, Object> map = new HashMap<>();
		JSONArray array = new JSONArray();
		JSONObject object = new JSONObject();
		for(RecentPriceData priceData : priceDataList){
			map.put("minVal",priceData.getMinVal().toString() );
			map.put("maxVal",priceData.getMaxVal().toString() );
			map.put("time",priceData.getStartDate().toString());
			object = new JSONObject(map);
			array.add(object);
			map = new HashMap<>();
		}
		
		System.out.println(array.toJSONString().toString());
	}
}
