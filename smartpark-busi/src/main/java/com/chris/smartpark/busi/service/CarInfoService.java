package com.chris.smartpark.busi.service;

import com.chris.smartpark.busi.entity.CarInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 车辆信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 04.18
 */
public interface CarInfoService {
	
	CarInfoEntity queryObject(Long id);
	
	List<CarInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CarInfoEntity carInfo);

	void batchInsert(List<CarInfoEntity> list);
	
	void update(CarInfoEntity carInfo);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
