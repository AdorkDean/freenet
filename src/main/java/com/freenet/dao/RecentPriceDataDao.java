package com.freenet.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.freenet.domain.RecentPriceData;

public interface RecentPriceDataDao {

	public  void insertPriceData(RecentPriceData data);
	
	public void updatePriceData(@Param("id")int id,@Param("data")RecentPriceData data);
	
	public List<RecentPriceData> getAllPriceData();
	
	public RecentPriceData getPriceData();
	
	public void deletePriceData(@Param("id")int id);
	
}
