package com.chris.smartpark.busi.service;

import com.chris.smartpark.busi.dto.UserAndCarsDTO;
import com.chris.smartpark.busi.entity.VisitorInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 访客信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 11.18
 */
public interface VisitorInfoService {
	
	VisitorInfoEntity queryObject(Long id);
	
	List<VisitorInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);

	VisitorInfoEntity selectByIdcardNo(VisitorInfoEntity visitorInfo);

	void save(VisitorInfoEntity visitorInfo);
	
	void update(VisitorInfoEntity visitorInfo);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	UserAndCarsDTO queryUserAndCars(String openId);
}
