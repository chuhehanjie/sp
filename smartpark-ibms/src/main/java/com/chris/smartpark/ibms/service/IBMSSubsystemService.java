package com.chris.smartpark.ibms.service;

import com.chris.smartpark.ibms.entity.IBMSSubsystemEntity;
import com.chris.smartpark.ibms.entity.IBMSSubsystemStateEntity;

import java.util.List;
import java.util.Map;

/**
 * ibms子系统表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 06.18
 */
public interface IBMSSubsystemService {
	
	IBMSSubsystemEntity queryObject(Integer id);
	
	List<IBMSSubsystemEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(IBMSSubsystemEntity ibmsSubsystem);
	
	void update(IBMSSubsystemEntity ibmsSubsystem);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	List<IBMSSubsystemStateEntity> queryListState();
}
