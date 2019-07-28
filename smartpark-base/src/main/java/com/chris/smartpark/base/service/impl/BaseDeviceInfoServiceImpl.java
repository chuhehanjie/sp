package com.chris.smartpark.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.base.dao.BaseDeviceInfoDao;
import com.chris.smartpark.base.entity.BaseDeviceInfoEntity;
import com.chris.smartpark.base.service.BaseDeviceInfoService;



@Service("baseDeviceInfoService")
public class BaseDeviceInfoServiceImpl implements BaseDeviceInfoService {
	@Autowired
	private BaseDeviceInfoDao baseDeviceInfoDao;
	
	@Override
	public BaseDeviceInfoEntity queryObject(Integer id){
		return baseDeviceInfoDao.queryObject(id);
	}
	
	@Override
	public List<BaseDeviceInfoEntity> queryList(Map<String, Object> map){
		return baseDeviceInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return baseDeviceInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(BaseDeviceInfoEntity baseDeviceInfo){
		baseDeviceInfoDao.save(baseDeviceInfo);
	}
	
	@Override
	public void update(BaseDeviceInfoEntity baseDeviceInfo){
		baseDeviceInfoDao.update(baseDeviceInfo);
	}
	
	@Override
	public void delete(Integer id){
		baseDeviceInfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		baseDeviceInfoDao.deleteBatch(ids);
	}
	
}
