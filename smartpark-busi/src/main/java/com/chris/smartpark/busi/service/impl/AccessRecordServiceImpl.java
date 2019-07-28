package com.chris.smartpark.busi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.busi.dao.AccessRecordDao;
import com.chris.smartpark.busi.entity.AccessRecordEntity;
import com.chris.smartpark.busi.service.AccessRecordService;



@Service("accessRecordService")
public class AccessRecordServiceImpl implements AccessRecordService {
	@Autowired
	private AccessRecordDao accessRecordDao;
	
	@Override
	public AccessRecordEntity queryObject(Long id){
		return accessRecordDao.queryObject(id);
	}
	
	@Override
	public List<AccessRecordEntity> queryList(Map<String, Object> map){
		return accessRecordDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return accessRecordDao.queryTotal(map);
	}
	
	@Override
	public void save(AccessRecordEntity accessRecord){
		accessRecordDao.save(accessRecord);
	}
	
	@Override
	public void update(AccessRecordEntity accessRecord){
		accessRecordDao.update(accessRecord);
	}
	
	@Override
	public void delete(Long id){
		accessRecordDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		accessRecordDao.deleteBatch(ids);
	}
	
}
