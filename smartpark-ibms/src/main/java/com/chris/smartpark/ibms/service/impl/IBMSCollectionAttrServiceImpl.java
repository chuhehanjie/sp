package com.chris.smartpark.ibms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.ibms.dao.IBMSCollectionAttrDao;
import com.chris.smartpark.ibms.entity.IBMSCollectionAttrEntity;
import com.chris.smartpark.ibms.service.IBMSCollectionAttrService;



@Service("ibmsCollectionAttrService")
public class IBMSCollectionAttrServiceImpl implements IBMSCollectionAttrService {
	@Autowired
	private IBMSCollectionAttrDao ibmsCollectionAttrDao;
	
	@Override
	public IBMSCollectionAttrEntity queryObject(Integer id){
		return ibmsCollectionAttrDao.queryObject(id);
	}
	
	@Override
	public List<IBMSCollectionAttrEntity> queryList(Map<String, Object> map){
		return ibmsCollectionAttrDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ibmsCollectionAttrDao.queryTotal(map);
	}
	
	@Override
	public void save(IBMSCollectionAttrEntity ibmsCollectionAttr){
		ibmsCollectionAttrDao.save(ibmsCollectionAttr);
	}
	
	@Override
	public void update(IBMSCollectionAttrEntity ibmsCollectionAttr){
		ibmsCollectionAttrDao.update(ibmsCollectionAttr);
	}
	
	@Override
	public void delete(Integer id){
		ibmsCollectionAttrDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		ibmsCollectionAttrDao.deleteBatch(ids);
	}
	
}
