package com.chris.smartpark.busi.service;

import com.chris.smartpark.busi.dto.DoorControllerDTO;
import com.chris.smartpark.busi.entity.DoorEntity;

import java.util.List;
import java.util.Map;

/**
 * 门定义
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Dec 15.18
 */
public interface DoorService {
	
	DoorEntity queryObject(Long id);
	
	List<DoorEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DoorEntity door);
	
	void update(DoorEntity door);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	List<DoorControllerDTO> queryDoorControllersByOpenId(String openId);

    List<Map<String,Object>> searchDoorCtrlList(Map<String, Object> params);
}
