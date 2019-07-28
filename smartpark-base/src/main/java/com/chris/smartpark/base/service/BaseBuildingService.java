package com.chris.smartpark.base.service;

import com.chris.smartpark.base.entity.BaseBuildingEntity;

import java.util.List;
import java.util.Map;

/**
 * 园区楼宇信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
public interface BaseBuildingService {
	
	BaseBuildingEntity queryObject(Integer id);
	
	List<BaseBuildingEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BaseBuildingEntity baseBuilding);
	
	void update(BaseBuildingEntity baseBuilding);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
