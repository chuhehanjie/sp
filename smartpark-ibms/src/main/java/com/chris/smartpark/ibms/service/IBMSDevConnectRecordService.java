package com.chris.smartpark.ibms.service;

import com.chris.smartpark.ibms.entity.IBMSDevConnectRecordEntity;

import java.util.List;
import java.util.Map;

/**
 * 设备连接记录表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
public interface IBMSDevConnectRecordService {
	
	IBMSDevConnectRecordEntity queryObject(Integer id);
	
	List<IBMSDevConnectRecordEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(IBMSDevConnectRecordEntity ibmsDevConnectRecord);
	
	void update(IBMSDevConnectRecordEntity ibmsDevConnectRecord);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
