package com.chris.smartpark.base.service;

import com.chris.smartpark.base.entity.BaseDeviceInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 设备基本信息
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 22.18
 */
public interface BaseDeviceInfoService {
	
	BaseDeviceInfoEntity queryObject(Integer id);
	
	List<BaseDeviceInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BaseDeviceInfoEntity baseDeviceInfo);
	
	void update(BaseDeviceInfoEntity baseDeviceInfo);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
