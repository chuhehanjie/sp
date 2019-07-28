package com.chris.smartpark.base.service;

import com.chris.smartpark.base.entity.BaseRoomEntity;

import java.util.List;
import java.util.Map;

/**
 * 园区房间信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
public interface BaseRoomService {
	
	BaseRoomEntity queryObject(Integer id);
	
	List<BaseRoomEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BaseRoomEntity baseRoom);
	
	void update(BaseRoomEntity baseRoom);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
