package com.freenet.service;

import java.util.List;

import com.freenet.domain.Allocation;
import com.freenet.domain.Manager;

public interface ManagerService {
	
	//添加管理员(工作人员)
	int insert(String username,String password,String name);
	
	//修改密码
	int updatePwd(int id,String password);
	
	//登录操作(查询)
	Manager login(String username,String password);
	
	//查询管理员(工作人员)
	List<Manager> selectWork();
	
	//根据账号查询
	Manager selectByUserName(String username);
	
	//根据id查询
	Manager selectById(int id);
	
	//删除某个管理员(工作人员，仅限超级管理员有次操作)
	int deleteById(int id);
	
	//添加收款账号
	int insertAllocation(String payZfb);
	
	//修改收款账号
	int updateAllocation(int id,String payZfb);
	
	//查询收款配置信息
	Allocation selectAllocationAll();

}
