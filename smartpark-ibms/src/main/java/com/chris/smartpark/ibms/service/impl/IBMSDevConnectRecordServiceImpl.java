package com.chris.smartpark.ibms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.ibms.dao.IBMSDevConnectRecordDao;
import com.chris.smartpark.ibms.entity.IBMSDevConnectRecordEntity;
import com.chris.smartpark.ibms.service.IBMSDevConnectRecordService;



@Service("ibmsDevConnectRecordService")
public class IBMSDevConnectRecordServiceImpl implements IBMSDevConnectRecordService {
	@Autowired
	private IBMSDevConnectRecordDao ibmsDevConnectRecordDao;
	
	@Override
	public IBMSDevConnectRecordEntity queryObject(Integer id){
		return ibmsDevConnectRecordDao.queryObject(id);
	}
	
	@Override
	public List<IBMSDevConnectRecordEntity> queryList(Map<String, Object> map){
		return ibmsDevConnectRecordDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ibmsDevConnectRecordDao.queryTotal(map);
	}
	
	@Override
	public void save(IBMSDevConnectRecordEntity ibmsDevConnectRecord){
		ibmsDevConnectRecordDao.save(ibmsDevConnectRecord);
	}
	
	@Override
	public void update(IBMSDevConnectRecordEntity ibmsDevConnectRecord){
		ibmsDevConnectRecordDao.update(ibmsDevConnectRecord);
	}
	
	@Override
	public void delete(Integer id){
		ibmsDevConnectRecordDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		ibmsDevConnectRecordDao.deleteBatch(ids);
	}
	
}
