package com.chris.smartpark.busi.service;

import com.chris.smartpark.busi.entity.MyCarEntity;

import java.util.List;
import java.util.Map;

/**
 * 我的车辆表
 * 
 * @author chris
 * @email 258321511@qq.com
 * @since May 16.19
 */
public interface MyCarService {
	
	MyCarEntity queryObject(Long id);
	
	List<MyCarEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MyCarEntity myCar);
	
	void update(MyCarEntity myCar);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
