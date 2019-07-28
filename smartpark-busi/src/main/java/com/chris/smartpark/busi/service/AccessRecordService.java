package com.chris.smartpark.busi.service;

import com.chris.smartpark.busi.entity.AccessRecordEntity;

import java.util.List;
import java.util.Map;

/**
 * 出入记录表
 * 
 * @author chris
 * @email 258321511@qq.com
 * @since Apr 29.19
 */
public interface AccessRecordService {
	
	AccessRecordEntity queryObject(Long id);
	
	List<AccessRecordEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AccessRecordEntity accessRecord);
	
	void update(AccessRecordEntity accessRecord);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
