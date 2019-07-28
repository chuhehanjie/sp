package com.chris.smartpark.busi.service;

import com.chris.smartpark.busi.entity.CompanionsEntity;

import java.util.List;
import java.util.Map;

/**
 * 同行人员信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 04.18
 */
public interface CompanionsService {
	
	CompanionsEntity queryObject(Long id);
	
	List<CompanionsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CompanionsEntity companions);
	
	void update(CompanionsEntity companions);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
