package com.chris.smartpark.busi.service.impl;

import com.chris.base.common.exception.CommonException;
import com.chris.base.common.utils.ValidateUtils;
import com.chris.base.modules.app.cache.AppLoginUser;
import com.chris.base.modules.app.cache.AppLoginUserCacheUtils;
import com.chris.smartpark.busi.common.VisitorConstants;
import com.chris.smartpark.busi.common.VisitorUtils;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.busi.dao.MyCarDao;
import com.chris.smartpark.busi.entity.MyCarEntity;
import com.chris.smartpark.busi.service.MyCarService;



@Service("myCarService")
public class MyCarServiceImpl implements MyCarService {
	@Autowired
	private MyCarDao myCarDao;
	
	@Override
	public MyCarEntity queryObject(Long id){
		return myCarDao.queryObject(id);
	}
	
	@Override
	public List<MyCarEntity> queryList(Map<String, Object> map){
		return myCarDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return myCarDao.queryTotal(map);
	}
	
	@Override
	public void save(MyCarEntity myCar){
		this.validate(myCar, VisitorConstants.Action.CREATE);
		AppLoginUser appLoginUser = AppLoginUserCacheUtils.getAppLoginUser(myCar.getOpenId());
		if (ValidateUtils.isNotEmpty(appLoginUser)) {
			myCar.setCreateUserId(appLoginUser.getUserId());
		}
		myCarDao.save(myCar);
	}

	private void validate(MyCarEntity myCar, int action) {
		if (ValidateUtils.equals(VisitorConstants.Action.MODIFY, action)) {
			if (ValidateUtils.isEmpty(myCar.getId())) {
				throw new CommonException("车辆ID为空");
			}
		}
		if (ValidateUtils.isEmptyString(myCar.getCarNo())) {
			throw new CommonException("车牌号为空");
		}
		if (!VisitorUtils.isValidCarNo(myCar.getCarNo())) {
			throw new CommonException("车牌号格式不正确");
		}
		if (ValidateUtils.isEmpty(myCar.getCarBrand())) {
			throw new CommonException("汽车品牌为空");
		}
		if (ValidateUtils.isEmpty(myCar.getCarColor())) {
			throw new CommonException("汽车颜色为空");
		}
		if (ValidateUtils.isEmpty(myCar.getDefaultTag())) {
			myCar.setDefaultTag(0);
		}
		List<MyCarEntity> myCarList = this.queryList(ImmutableMap.of("carNo", myCar.getCarNo()));
		if (ValidateUtils.equals(VisitorConstants.Action.CREATE, action)) {
			if (ValidateUtils.isNotEmptyCollection(myCarList)) {
				throw new CommonException("车牌号[" + myCar.getCarNo() + "]已存在");
			}
		} else {
			MyCarEntity curCar = this.queryObject(myCar.getId());
			if (ValidateUtils.isNotEmptyCollection(myCarList) && ValidateUtils.notEquals(curCar.getCarNo(), myCarList.get(0).getCarNo())) {
				throw new CommonException("车牌号[" + myCar.getCarNo() + "]已存在");
			}
		}
	}

	@Override
	public void update(MyCarEntity myCar){
		this.validate(myCar, VisitorConstants.Action.MODIFY);
		AppLoginUser appLoginUser = AppLoginUserCacheUtils.getAppLoginUser(myCar.getOpenId());
		if (ValidateUtils.isNotEmpty(appLoginUser)) {
			myCar.setUpdateUserId(appLoginUser.getUserId());
		}
		this.myCarDao.update(myCar);
	}
	
	@Override
	public void delete(Long id){
		myCarDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		myCarDao.deleteBatch(ids);
	}
	
}
