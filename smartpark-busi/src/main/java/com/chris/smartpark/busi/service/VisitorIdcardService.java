package com.chris.smartpark.busi.service;

import com.chris.smartpark.busi.entity.VisitorIdcardEntity;

import java.util.List;
import java.util.Map;

/**
 * 访客身份信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 11.18
 */
public interface VisitorIdcardService {
	
	VisitorIdcardEntity queryObject(Long id);
	
	List<VisitorIdcardEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(VisitorIdcardEntity visitorIdcard);
	
	void update(VisitorIdcardEntity visitorIdcard);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
