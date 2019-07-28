package com.chris.smartpark.busi.service;

import com.chris.smartpark.busi.entity.ContactEntity;

import java.util.List;
import java.util.Map;

/**
 * 联系人表
 * 
 * @author chris
 * @email 258321511@qq.com
 * @since May 16.19
 */
public interface ContactService {
	
	ContactEntity queryObject(Long id);
	
	List<ContactEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ContactEntity contact);
	
	void update(ContactEntity contact);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
