package com.chris.smartpark.ibms.service;

import com.chris.smartpark.ibms.entity.IBMSDevAlarmRecordEntity;

import java.util.List;
import java.util.Map;

/**
 * 设备报警记录表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
public interface IBMSDevAlarmRecordService {
	
	IBMSDevAlarmRecordEntity queryObject(Integer id);
	
	List<IBMSDevAlarmRecordEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(IBMSDevAlarmRecordEntity ibmsDevAlarmRecord);
	
	void update(IBMSDevAlarmRecordEntity ibmsDevAlarmRecord);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
