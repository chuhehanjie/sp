package com.chris.smartpark.busi.service.impl;

import com.chris.base.common.utils.ValidateUtils;
import com.chris.smartpark.busi.common.VisitorConstants;
import com.chris.smartpark.busi.common.VisitorUtils;
import com.chris.smartpark.busi.dao.DoorDao;
import com.chris.smartpark.busi.dto.DoorControllerDTO;
import com.chris.smartpark.busi.entity.DoorEntity;
import com.chris.smartpark.busi.service.DoorService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;



@Service("doorService")
public class DoorServiceImpl implements DoorService {
	@Autowired
	private DoorDao doorDao;
	
	@Override
	public DoorEntity queryObject(Long id){
		return doorDao.queryObject(id);
	}
	
	@Override
	public List<DoorEntity> queryList(Map<String, Object> map){
		return doorDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return doorDao.queryTotal(map);
	}
	
	@Override
	public void save(DoorEntity door){
		doorDao.save(door);
	}
	
	@Override
	public void update(DoorEntity door){
		doorDao.update(door);
	}
	
	@Override
	public void delete(Long id){
		doorDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		doorDao.deleteBatch(ids);
	}

	@Override
	public List<DoorControllerDTO> queryDoorControllersByOpenId(String openId) {
		Map<String, Object> params = Maps.newHashMap();
		if (!VisitorUtils.isAdminRole(openId)) {
			// 不是管理员角色，需要关联 openid 查询
			params.put(VisitorConstants.Keys.OPEN_ID, openId);
		}
		return this.doorDao.queryDoorControllersByOpenId(params);
	}

	@Override
	public List<Map<String, Object>> searchDoorCtrlList(Map<String, Object> params) {
		List<Map<String, Object>> resultList = this.doorDao.searchDoorCtrlList(params);
		if (ValidateUtils.isNotEmptyCollection(resultList) && params.containsKey(VisitorConstants.Keys.OPEN_ID)) {
			List<DoorControllerDTO> doorCtrlList = this.queryDoorControllersByOpenId(params.get(VisitorConstants.Keys.OPEN_ID).toString());
			if (ValidateUtils.isEmptyCollection(doorCtrlList)) {
				return Collections.EMPTY_LIST;
			}
			//  过滤掉无权限的门禁
			resultList.removeIf(item -> !this.hasAuthorizedDoorCtrl(doorCtrlList, item.get("doorId").toString()));
		}
		return resultList;
	}

	private boolean hasAuthorizedDoorCtrl(List<DoorControllerDTO> doorCtrlList, String doorCtrlId) {
		for (DoorControllerDTO doorCtrl : doorCtrlList) {
			if (ValidateUtils.equals(doorCtrl.getDoorId() + "", doorCtrlId)) {
				return true;
			}
        }
		return false;
	}
}
