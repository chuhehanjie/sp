package com.chris.smartpark.busi.service.impl;

import com.chris.base.common.exception.CommonException;
import com.chris.base.common.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.busi.dao.SearchHisDao;
import com.chris.smartpark.busi.entity.SearchHisEntity;
import com.chris.smartpark.busi.service.SearchHisService;



@Service("searchHisService")
public class SearchHisServiceImpl implements SearchHisService {
	@Autowired
	private SearchHisDao searchHisDao;
	
	@Override
	public SearchHisEntity queryObject(Long id){
		return searchHisDao.queryObject(id);
	}
	
	@Override
	public List<SearchHisEntity> queryList(Map<String, Object> map){
		return searchHisDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return searchHisDao.queryTotal(map);
	}
	
	@Override
	public void save(SearchHisEntity searchHis){
		this.validate(searchHis);
		searchHisDao.save(searchHis);
	}

	private void validate(SearchHisEntity searchHis) {
		if (ValidateUtils.isEmpty(searchHis.getStaffId())) {
			throw new CommonException("员工ID为空");
		}
		if (ValidateUtils.isEmptyString(searchHis.getStaffName())) {
			throw new CommonException("员工姓名为空");
		}
		if (ValidateUtils.isEmptyString(searchHis.getMobile())) {
			throw new CommonException("员工手机号为空");
		}
		if (ValidateUtils.isEmptyString(searchHis.getOpenId())) {
			throw new CommonException("微信openId为空");
		}
	}

	@Override
	public void update(SearchHisEntity searchHis){
		if (ValidateUtils.isEmpty(searchHis.getId())) {
			throw new CommonException("搜索记录ID为空");
		}
		this.validate(searchHis);
		searchHisDao.update(searchHis);
	}
	
	@Override
	public void delete(Long id){
		searchHisDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		searchHisDao.deleteBatch(ids);
	}
	
}
