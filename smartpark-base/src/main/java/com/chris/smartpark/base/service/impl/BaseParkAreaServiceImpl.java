package com.chris.smartpark.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.base.dao.BaseParkAreaDao;
import com.chris.smartpark.base.entity.BaseParkAreaEntity;
import com.chris.smartpark.base.service.BaseParkAreaService;



@Service("baseParkAreaService")
public class BaseParkAreaServiceImpl implements BaseParkAreaService {
	@Autowired
	private BaseParkAreaDao baseParkAreaDao;
	
	@Override
	public BaseParkAreaEntity queryObject(Integer id){
		return baseParkAreaDao.queryObject(id);
	}
	
	@Override
	public List<BaseParkAreaEntity> queryList(Map<String, Object> map){
		return baseParkAreaDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return baseParkAreaDao.queryTotal(map);
	}
	
	@Override
	public void save(BaseParkAreaEntity baseParkArea){
		baseParkAreaDao.save(baseParkArea);
	}
	
	@Override
	public void update(BaseParkAreaEntity baseParkArea){
		baseParkAreaDao.update(baseParkArea);
	}
	
	@Override
	public void delete(Integer id){
		baseParkAreaDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		baseParkAreaDao.deleteBatch(ids);
	}
	
}
