package com.chris.smartpark.busi.service;

import com.chris.smartpark.busi.entity.AuthenticationRecordEntity;

import java.util.List;
import java.util.Map;

/**
 * 预约审核表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 09.18
 */
public interface AuthenticationRecordService {
	
	AuthenticationRecordEntity queryObject(Long id);
	
	List<AuthenticationRecordEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AuthenticationRecordEntity authenticationRecord);
	
	void update(AuthenticationRecordEntity authenticationRecord);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
