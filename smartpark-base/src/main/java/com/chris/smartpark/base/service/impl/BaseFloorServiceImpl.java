package com.chris.smartpark.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.base.dao.BaseFloorDao;
import com.chris.smartpark.base.entity.BaseFloorEntity;
import com.chris.smartpark.base.service.BaseFloorService;



@Service("baseFloorService")
public class BaseFloorServiceImpl implements BaseFloorService {
	@Autowired
	private BaseFloorDao baseFloorDao;
	
	@Override
	public BaseFloorEntity queryObject(Integer id){
		return baseFloorDao.queryObject(id);
	}
	
	@Override
	public List<BaseFloorEntity> queryList(Map<String, Object> map){
		return baseFloorDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return baseFloorDao.queryTotal(map);
	}
	
	@Override
	public void save(BaseFloorEntity baseFloor){
		baseFloorDao.save(baseFloor);
	}
	
	@Override
	public void update(BaseFloorEntity baseFloor){
		baseFloorDao.update(baseFloor);
	}
	
	@Override
	public void delete(Integer id){
		baseFloorDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		baseFloorDao.deleteBatch(ids);
	}
	
}
