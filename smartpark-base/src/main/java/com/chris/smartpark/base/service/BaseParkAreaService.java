package com.chris.smartpark.base.service;

import com.chris.smartpark.base.entity.BaseParkAreaEntity;

import java.util.List;
import java.util.Map;

/**
 * 园区区域信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
public interface BaseParkAreaService {
	
	BaseParkAreaEntity queryObject(Integer id);
	
	List<BaseParkAreaEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BaseParkAreaEntity baseParkArea);
	
	void update(BaseParkAreaEntity baseParkArea);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
