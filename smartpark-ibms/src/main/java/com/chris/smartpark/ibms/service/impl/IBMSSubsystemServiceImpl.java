package com.chris.smartpark.ibms.service.impl;

import com.chris.smartpark.ibms.dao.IBMSDevAlarmRecordDao;
import com.chris.smartpark.ibms.dao.IBMSDevConnectRecordDao;
import com.chris.smartpark.ibms.entity.IBMSSubsystemStateEntity;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.ibms.dao.IBMSSubsystemDao;
import com.chris.smartpark.ibms.entity.IBMSSubsystemEntity;
import com.chris.smartpark.ibms.service.IBMSSubsystemService;



@Service("ibmsSubsystemService")
public class IBMSSubsystemServiceImpl implements IBMSSubsystemService {
	@Autowired
	private IBMSSubsystemDao ibmsSubsystemDao;
	@Autowired
	private IBMSDevAlarmRecordDao ibmsDevAlarmRecordDao;
	@Autowired
	private IBMSDevConnectRecordDao ibmsDevConnectRecordDao;
	
	@Override
	public IBMSSubsystemEntity queryObject(Integer id){
		return ibmsSubsystemDao.queryObject(id);
	}
	
	@Override
	public List<IBMSSubsystemEntity> queryList(Map<String, Object> map){
		return ibmsSubsystemDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ibmsSubsystemDao.queryTotal(map);
	}
	
	@Override
	public void save(IBMSSubsystemEntity ibmsSubsystem){
		ibmsSubsystemDao.save(ibmsSubsystem);
	}
	
	@Override
	public void update(IBMSSubsystemEntity ibmsSubsystem){
		ibmsSubsystemDao.update(ibmsSubsystem);
	}
	
	@Override
	public void delete(Integer id){
		ibmsSubsystemDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		ibmsSubsystemDao.deleteBatch(ids);
	}

	@Override
	public List<IBMSSubsystemStateEntity> queryListState() {
		Map<String,Object> params = Maps.newHashMap();
		params.put("sidx","id");
		params.put("order","asc");
		List<IBMSSubsystemEntity> ibmsSubsystemEntities = ibmsSubsystemDao.queryList(params);
		List<IBMSSubsystemStateEntity> ibmsSubsystemStateEntities = Lists.newArrayList();
		for (IBMSSubsystemEntity ibmsSubsystemEntity : ibmsSubsystemEntities){
			IBMSSubsystemStateEntity ibmsSubsystemStateEntity = new IBMSSubsystemStateEntity();
			ibmsSubsystemStateEntity.setId(ibmsSubsystemEntity.getId());
			ibmsSubsystemStateEntity.setName(ibmsSubsystemEntity.getName());
			ibmsSubsystemStateEntity.setAlarm(ibmsDevAlarmRecordDao.queryStateBySubsystem(ibmsSubsystemEntity.getId()));
			ibmsSubsystemStateEntity.setConnectStatus(ibmsDevConnectRecordDao.queryStateBySubsystem(ibmsSubsystemEntity.getId()));
			ibmsSubsystemStateEntities.add(ibmsSubsystemStateEntity);
		}
		return ibmsSubsystemStateEntities;
	}

}
