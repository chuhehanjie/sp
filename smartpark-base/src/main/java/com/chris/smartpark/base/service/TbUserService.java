package com.chris.smartpark.base.service;

import com.chris.smartpark.base.entity.TbUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户
 * 
 * @author chris
 * @email 258321511@qq.com
 * @since May 28.19
 */
public interface TbUserService {
	
	TbUserEntity queryObject(Long userId);
	
	List<TbUserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TbUserEntity tbUser);
	
	void update(TbUserEntity tbUser);
	
	void delete(Long userId);
	
	void deleteBatch(Long[] userIds);

    void setRole(TbUserEntity tbUser);

    void updateUsernameByOpenId(TbUserEntity user);
}
