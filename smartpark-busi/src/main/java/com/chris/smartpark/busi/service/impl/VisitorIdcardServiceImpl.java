package com.chris.smartpark.busi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.busi.dao.VisitorIdcardDao;
import com.chris.smartpark.busi.entity.VisitorIdcardEntity;
import com.chris.smartpark.busi.service.VisitorIdcardService;



@Service("visitorIdcardService")
public class VisitorIdcardServiceImpl implements VisitorIdcardService {
	@Autowired
	private VisitorIdcardDao visitorIdcardDao;
	
	@Override
	public VisitorIdcardEntity queryObject(Long id){
		return visitorIdcardDao.queryObject(id);
	}
	
	@Override
	public List<VisitorIdcardEntity> queryList(Map<String, Object> map){
		return visitorIdcardDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return visitorIdcardDao.queryTotal(map);
	}
	
	@Override
	public void save(VisitorIdcardEntity visitorIdcard){
		visitorIdcardDao.save(visitorIdcard);
	}
	
	@Override
	public void update(VisitorIdcardEntity visitorIdcard){
		visitorIdcardDao.update(visitorIdcard);
	}
	
	@Override
	public void delete(Long id){
		visitorIdcardDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		visitorIdcardDao.deleteBatch(ids);
	}
	
}
