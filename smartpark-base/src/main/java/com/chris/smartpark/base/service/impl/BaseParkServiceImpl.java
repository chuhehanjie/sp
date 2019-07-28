package com.chris.smartpark.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.base.dao.BaseParkDao;
import com.chris.smartpark.base.entity.BaseParkEntity;
import com.chris.smartpark.base.service.BaseParkService;



@Service("baseParkService")
public class BaseParkServiceImpl implements BaseParkService {
	@Autowired
	private BaseParkDao baseParkDao;
	
	@Override
	public BaseParkEntity queryObject(Integer id){
		return baseParkDao.queryObject(id);
	}
	
	@Override
	public List<BaseParkEntity> queryList(Map<String, Object> map){
		return baseParkDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return baseParkDao.queryTotal(map);
	}
	
	@Override
	public void save(BaseParkEntity basePark){
		baseParkDao.save(basePark);
	}
	
	@Override
	public void update(BaseParkEntity basePark){
		baseParkDao.update(basePark);
	}
	
	@Override
	public void delete(Integer id){
		baseParkDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		baseParkDao.deleteBatch(ids);
	}
	
}
