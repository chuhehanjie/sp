package com.chris.smartpark.base.service;

import com.chris.smartpark.base.entity.BaseFloorEntity;

import java.util.List;
import java.util.Map;

/**
 * 园区楼层信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
public interface BaseFloorService {
	
	BaseFloorEntity queryObject(Integer id);
	
	List<BaseFloorEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BaseFloorEntity baseFloor);
	
	void update(BaseFloorEntity baseFloor);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
