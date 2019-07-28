package com.chris.smartpark.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.base.dao.BaseRoomDao;
import com.chris.smartpark.base.entity.BaseRoomEntity;
import com.chris.smartpark.base.service.BaseRoomService;



@Service("baseRoomService")
public class BaseRoomServiceImpl implements BaseRoomService {
	@Autowired
	private BaseRoomDao baseRoomDao;
	
	@Override
	public BaseRoomEntity queryObject(Integer id){
		return baseRoomDao.queryObject(id);
	}
	
	@Override
	public List<BaseRoomEntity> queryList(Map<String, Object> map){
		return baseRoomDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return baseRoomDao.queryTotal(map);
	}
	
	@Override
	public void save(BaseRoomEntity baseRoom){
		baseRoomDao.save(baseRoom);
	}
	
	@Override
	public void update(BaseRoomEntity baseRoom){
		baseRoomDao.update(baseRoom);
	}
	
	@Override
	public void delete(Integer id){
		baseRoomDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		baseRoomDao.deleteBatch(ids);
	}
	
}
