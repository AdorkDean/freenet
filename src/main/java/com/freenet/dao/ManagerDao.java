package com.freenet.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.freenet.domain.Allocation;
import com.freenet.domain.Manager;

public interface ManagerDao {
	
	//添加管理员(工作人员)
	int insert(Manager manager);
	
	//修改密码
	int updatePwd(@Param("id") int id,@Param("password") String password);
	
	//登录操作(查询)
	Manager login(@Param("username") String username,@Param("password") String password);
	
	//查询管理员(工作人员)
	List<Manager> selectWork();
	
	//根据账号查询
	Manager selectByUserName(@Param("username") String username);
	
	//根据id查询
	Manager selectById(@Param("id") int id);
	
	//删除某个管理员(工作人员，仅限超级管理员有次操作)
	int deleteById(@Param("id") int id);
	
	//添加收款账号
	int insertAllocation(Allocation allocation);
	
	//修改收款账号
	int updateAllocation(@Param("id") int id,@Param("payZfb") String payZfb);
	
	//查询收款配置信息
	Allocation selectAllocationAll();

}
