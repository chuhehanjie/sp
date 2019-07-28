package com.chris.smartpark.ibms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.ibms.dao.IBMSDevAlarmRecordDao;
import com.chris.smartpark.ibms.entity.IBMSDevAlarmRecordEntity;
import com.chris.smartpark.ibms.service.IBMSDevAlarmRecordService;



@Service("ibmsDevAlarmRecordService")
public class IBMSDevAlarmRecordServiceImpl implements IBMSDevAlarmRecordService {
	@Autowired
	private IBMSDevAlarmRecordDao ibmsDevAlarmRecordDao;
	
	@Override
	public IBMSDevAlarmRecordEntity queryObject(Integer id){
		return ibmsDevAlarmRecordDao.queryObject(id);
	}
	
	@Override
	public List<IBMSDevAlarmRecordEntity> queryList(Map<String, Object> map){
		return ibmsDevAlarmRecordDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ibmsDevAlarmRecordDao.queryTotal(map);
	}
	
	@Override
	public void save(IBMSDevAlarmRecordEntity ibmsDevAlarmRecord){
		ibmsDevAlarmRecordDao.save(ibmsDevAlarmRecord);
	}
	
	@Override
	public void update(IBMSDevAlarmRecordEntity ibmsDevAlarmRecord){
		ibmsDevAlarmRecordDao.update(ibmsDevAlarmRecord);
	}
	
	@Override
	public void delete(Integer id){
		ibmsDevAlarmRecordDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		ibmsDevAlarmRecordDao.deleteBatch(ids);
	}
	
}
