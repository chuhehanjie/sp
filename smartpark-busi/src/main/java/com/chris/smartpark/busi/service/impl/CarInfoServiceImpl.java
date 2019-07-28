package com.chris.smartpark.busi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.busi.dao.CarInfoDao;
import com.chris.smartpark.busi.entity.CarInfoEntity;
import com.chris.smartpark.busi.service.CarInfoService;



@Service("carInfoService")
public class CarInfoServiceImpl implements CarInfoService {
	@Autowired
	private CarInfoDao carInfoDao;
	
	@Override
	public CarInfoEntity queryObject(Long id){
		return carInfoDao.queryObject(id);
	}
	
	@Override
	public List<CarInfoEntity> queryList(Map<String, Object> map){
		return carInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return carInfoDao.queryTotal(map);
	}
	@Override
	public void batchInsert(List<CarInfoEntity> list){
		carInfoDao.batchInsert(list);
	}
	
	@Override
	public void save(CarInfoEntity carInfo){
		carInfoDao.save(carInfo);
	}
	
	@Override
	public void update(CarInfoEntity carInfo){
		carInfoDao.update(carInfo);
	}
	
	@Override
	public void delete(Long id){
		carInfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		carInfoDao.deleteBatch(ids);
	}
	
}
