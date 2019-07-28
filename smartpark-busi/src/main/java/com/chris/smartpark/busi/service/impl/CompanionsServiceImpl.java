package com.chris.smartpark.busi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.busi.dao.CompanionsDao;
import com.chris.smartpark.busi.entity.CompanionsEntity;
import com.chris.smartpark.busi.service.CompanionsService;



@Service("companionsService")
public class CompanionsServiceImpl implements CompanionsService {
	@Autowired
	private CompanionsDao companionsDao;
	
	@Override
	public CompanionsEntity queryObject(Long id){
		return companionsDao.queryObject(id);
	}
	
	@Override
	public List<CompanionsEntity> queryList(Map<String, Object> map){
		return companionsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return companionsDao.queryTotal(map);
	}
	
	@Override
	public void save(CompanionsEntity companions){
		companionsDao.save(companions);
	}
	
	@Override
	public void update(CompanionsEntity companions){
		companionsDao.update(companions);
	}
	
	@Override
	public void delete(Long id){
		companionsDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		companionsDao.deleteBatch(ids);
	}
	
}
