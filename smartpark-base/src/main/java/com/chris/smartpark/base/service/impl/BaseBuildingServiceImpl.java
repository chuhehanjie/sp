package com.chris.smartpark.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.base.dao.BaseBuildingDao;
import com.chris.smartpark.base.entity.BaseBuildingEntity;
import com.chris.smartpark.base.service.BaseBuildingService;



@Service("baseBuildingService")
public class BaseBuildingServiceImpl implements BaseBuildingService {
	@Autowired
	private BaseBuildingDao baseBuildingDao;
	
	@Override
	public BaseBuildingEntity queryObject(Integer id){
		return baseBuildingDao.queryObject(id);
	}
	
	@Override
	public List<BaseBuildingEntity> queryList(Map<String, Object> map){
		return baseBuildingDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return baseBuildingDao.queryTotal(map);
	}
	
	@Override
	public void save(BaseBuildingEntity baseBuilding){
		baseBuildingDao.save(baseBuilding);
	}
	
	@Override
	public void update(BaseBuildingEntity baseBuilding){
		baseBuildingDao.update(baseBuilding);
	}
	
	@Override
	public void delete(Integer id){
		baseBuildingDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		baseBuildingDao.deleteBatch(ids);
	}
	
}
