package com.chris.smartpark.busi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.busi.dao.AuthenticationRecordDao;
import com.chris.smartpark.busi.entity.AuthenticationRecordEntity;
import com.chris.smartpark.busi.service.AuthenticationRecordService;



@Service("authenticationRecordService")
public class AuthenticationRecordServiceImpl implements AuthenticationRecordService {
	@Autowired
	private AuthenticationRecordDao authenticationRecordDao;
	
	@Override
	public AuthenticationRecordEntity queryObject(Long id){
		return authenticationRecordDao.queryObject(id);
	}
	
	@Override
	public List<AuthenticationRecordEntity> queryList(Map<String, Object> map){
		return authenticationRecordDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return authenticationRecordDao.queryTotal(map);
	}
	
	@Override
	public void save(AuthenticationRecordEntity authenticationRecord){
		authenticationRecordDao.save(authenticationRecord);
	}
	
	@Override
	public void update(AuthenticationRecordEntity authenticationRecord){
		authenticationRecordDao.update(authenticationRecord);
	}
	
	@Override
	public void delete(Long id){
		authenticationRecordDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		authenticationRecordDao.deleteBatch(ids);
	}
	
}
