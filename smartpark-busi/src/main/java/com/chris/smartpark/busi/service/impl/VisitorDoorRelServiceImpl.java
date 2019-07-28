package com.chris.smartpark.busi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.busi.dao.VisitorDoorRelDao;
import com.chris.smartpark.busi.entity.VisitorDoorRelEntity;
import com.chris.smartpark.busi.service.VisitorDoorRelService;



@Service("visitorDoorRelService")
public class VisitorDoorRelServiceImpl implements VisitorDoorRelService {
	@Autowired
	private VisitorDoorRelDao visitorDoorRelDao;
	
	@Override
	public VisitorDoorRelEntity queryObject(Long id){
		return visitorDoorRelDao.queryObject(id);
	}
	
	@Override
	public List<VisitorDoorRelEntity> queryList(Map<String, Object> map){
		return visitorDoorRelDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return visitorDoorRelDao.queryTotal(map);
	}
	
	@Override
	public void save(VisitorDoorRelEntity visitorDoorRel){
		visitorDoorRelDao.save(visitorDoorRel);
	}
	
	@Override
	public void update(VisitorDoorRelEntity visitorDoorRel){
		visitorDoorRelDao.update(visitorDoorRel);
	}
	
	@Override
	public void delete(Long id){
		visitorDoorRelDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		visitorDoorRelDao.deleteBatch(ids);
	}

	@Override
	public void saveBatch(List<VisitorDoorRelEntity> list) {
		//to be continue..
        visitorDoorRelDao.saveBatch(list);
	}

	@Override
	public List<String> queryMappdingDoorIdsByReservationOrderId(Long id) {
		return this.visitorDoorRelDao.queryMappdingDoorIdsByReservationOrderId(id);
	}
}
