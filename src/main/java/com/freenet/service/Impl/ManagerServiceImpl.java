package com.freenet.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.freenet.common.K;
import com.freenet.common.V;
import com.freenet.dao.ManagerDao;
import com.freenet.domain.Allocation;
import com.freenet.domain.Manager;
import com.freenet.service.ManagerService;
import com.freenet.tools.PwdTools;

@Service
public class ManagerServiceImpl implements ManagerService{
	
	@Resource
	private ManagerDao managerDao;
	
	
	//添加管理员(工作人员)
	public int insert(String username, String password, String name) {
		String pwdNow = PwdTools.ecodingPwd(password, K.md5);
		Manager mg = new Manager();
		mg.setUsername(username);
		mg.setPassword(pwdNow);
		mg.setName(name);
		mg.setLevel(V.manager_level_worker);//管理员(工作人员)
		mg.setCdt(new Date());
		return managerDao.insert(mg);
	}
	
	//修改密码
	public int updatePwd(int id, String password) {
		String pwdNow = PwdTools.ecodingPwd(password, K.md5);
		return managerDao.updatePwd(id, pwdNow);
		
	}


	//登录操作(查询)
	public Manager login(String username, String password) {
		String realPass = PwdTools.ecodingPwd(password, K.md5);
		return managerDao.login(username, realPass);
	}

	//查询管理员(工作人员)
	public List<Manager> selectWork() {
		return managerDao.selectWork();
	}

	//根据账号查询
	public Manager selectByUserName(String username) {
		return managerDao.selectByUserName(username);
	}
	
	//根据id查询
	public Manager selectById(int id) {
		return managerDao.selectById(id);
	}
	
	//删除某个管理员(工作人员，仅限超级管理员有次操作)
	public int deleteById(int id) {
		return managerDao.deleteById(id);
	}
	
	//添加收款账号
	public int insertAllocation(String payZfb) {
		Allocation all = new Allocation();
		all.setPayZfb(payZfb);
		all.setCdt(new Date());
		return managerDao.insertAllocation(all);
	}
	
	//修改收款账号
	public int updateAllocation(int id, String payZfb) {
		return managerDao.updateAllocation(id, payZfb);
	}

	//查询收款配置信息
	public Allocation selectAllocationAll() {
		return managerDao.selectAllocationAll();
	}

	

	

	

	
	

	

}
