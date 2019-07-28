package com.chris.smartpark.ibms.service;

import com.chris.smartpark.ibms.entity.IBMSCollectionAttrEntity;

import java.util.List;
import java.util.Map;

/**
 * 数据采集表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
public interface IBMSCollectionAttrService {
	
	IBMSCollectionAttrEntity queryObject(Integer id);
	
	List<IBMSCollectionAttrEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(IBMSCollectionAttrEntity ibmsCollectionAttr);
	
	void update(IBMSCollectionAttrEntity ibmsCollectionAttr);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
