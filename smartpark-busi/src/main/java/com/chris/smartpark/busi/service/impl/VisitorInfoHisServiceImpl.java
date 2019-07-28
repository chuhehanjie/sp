package com.chris.smartpark.busi.service.impl;

import com.chris.base.common.utils.ValidateUtils;
import com.chris.smartpark.busi.common.VisitorConstants;
import com.chris.smartpark.busi.dao.VisitorInfoHisDao;
import com.chris.smartpark.busi.entity.CarInfoEntity;
import com.chris.smartpark.busi.entity.VisitorInfoHisEntity;
import com.chris.smartpark.busi.service.CarInfoService;
import com.chris.smartpark.busi.service.VisitorInfoHisService;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service("visitorInfoHisService")
public class VisitorInfoHisServiceImpl implements VisitorInfoHisService {
	@Autowired
	private VisitorInfoHisDao visitorInfoHisDao;

	@Autowired
	private CarInfoService carInfoService;

	@Override
	public VisitorInfoHisEntity queryObject(Long id){
		return visitorInfoHisDao.queryObject(id);
	}
	
	@Override
	public List<VisitorInfoHisEntity> queryList(Map<String, Object> map){
		return visitorInfoHisDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return visitorInfoHisDao.queryTotal(map);
	}
	
	@Override
	public void save(VisitorInfoHisEntity visitorInfoHis){
		visitorInfoHisDao.save(visitorInfoHis);
	}
	
	@Override
	public void update(VisitorInfoHisEntity visitorInfoHis){
		visitorInfoHisDao.update(visitorInfoHis);
	}
	
	@Override
	public void delete(Long id){
		visitorInfoHisDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		visitorInfoHisDao.deleteBatch(ids);
	}

	@Override
	public VisitorInfoHisEntity queryByReservationId(Long reservationId) {
		List<VisitorInfoHisEntity> list = this.queryList(ImmutableMap.of(VisitorConstants.Keys.RESERVATION_ORDER_ID, reservationId));
		return ValidateUtils.isEmptyCollection(list) ? null : list.get(0);
	}

	@Override
	public List<VisitorInfoHisEntity> queryByIdcardNo(String idcardNo) {
		List<VisitorInfoHisEntity> visitors = this.visitorInfoHisDao.queryByIdcardNo(idcardNo);
		if (ValidateUtils.isNotEmptyCollection(visitors)) {
			for (VisitorInfoHisEntity visitor : visitors) {
				// 根据最近的一次预约单ID 查询车辆信息
				List<CarInfoEntity> carInfoList = this.carInfoService.queryList(ImmutableMap.of(VisitorConstants.Keys.RESERVATION_ORDER_ID, visitor.getReservationId()));
				visitor.setCarInfoList(carInfoList);
			}
			return visitors;
		}
		return null;
	}

}
