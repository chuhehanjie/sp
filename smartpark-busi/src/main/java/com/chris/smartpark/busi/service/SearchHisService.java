package com.chris.smartpark.busi.service;

import com.chris.smartpark.busi.entity.SearchHisEntity;

import java.util.List;
import java.util.Map;

/**
 * 搜索历史表
 * 
 * @author chris
 * @email 258321511@qq.com
 * @since May 15.19
 */
public interface SearchHisService {
	
	SearchHisEntity queryObject(Long id);
	
	List<SearchHisEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SearchHisEntity searchHis);
	
	void update(SearchHisEntity searchHis);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
