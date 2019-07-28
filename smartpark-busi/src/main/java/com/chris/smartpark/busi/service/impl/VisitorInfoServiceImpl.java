package com.chris.smartpark.busi.service.impl;

import com.chris.base.common.exception.CommonException;
import com.chris.base.common.utils.DateUtils;
import com.chris.base.common.utils.ValidateUtils;
import com.chris.base.modules.app.entity.UserEntity;
import com.chris.base.modules.app.service.UserService;
import com.chris.smartpark.busi.common.VisitorConstants;
import com.chris.smartpark.busi.dto.UserAndCarsDTO;
import com.chris.smartpark.busi.entity.CarInfoEntity;
import com.chris.smartpark.busi.entity.MyCarEntity;
import com.chris.smartpark.busi.entity.VisitorInfoHisEntity;
import com.chris.smartpark.busi.service.CarInfoService;
import com.chris.smartpark.busi.service.MyCarService;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.busi.dao.VisitorInfoDao;
import com.chris.smartpark.busi.entity.VisitorInfoEntity;
import com.chris.smartpark.busi.service.VisitorInfoService;



@Service("visitorInfoService")
public class VisitorInfoServiceImpl implements VisitorInfoService {
	@Autowired
	private VisitorInfoDao visitorInfoDao;

	@Autowired
	private CarInfoService carInfoService;

	@Autowired
	private UserService userService;

	@Autowired
	private MyCarService myCarService;
	
	@Override
	public VisitorInfoEntity queryObject(Long id){
		return visitorInfoDao.queryObject(id);
	}
	
	@Override
	public List<VisitorInfoEntity> queryList(Map<String, Object> map){
		return visitorInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return visitorInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(VisitorInfoEntity visitorInfo){
		if (null != visitorInfo.getId()) {
			visitorInfo.setUpdateTime(DateUtils.currentDate());
			this.visitorInfoDao.update(visitorInfo);
		} else {
			this.visitorInfoDao.save(visitorInfo);
		}

	}
	@Override
	public VisitorInfoEntity selectByIdcardNo(VisitorInfoEntity visitorInfo){
		return visitorInfoDao.selectByIdcardNo(visitorInfo);
	}

	@Override
	public void update(VisitorInfoEntity visitorInfo){
		visitorInfoDao.update(visitorInfo);
	}
	
	@Override
	public void delete(Long id){
		visitorInfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		visitorInfoDao.deleteBatch(ids);
	}

	@Override
	public UserAndCarsDTO queryUserAndCars(String openId) {
		UserEntity user = this.userService.queryUserByOpenId(openId);
		if (ValidateUtils.isEmpty(user)) {
			throw new CommonException("用户不存在");
		}
		List<VisitorInfoEntity> visitors = this.queryList(ImmutableMap.of("phone", user.getMobile(), "sidx", "create_time", "order", "desc"));
		UserAndCarsDTO userAndCarsDTO = null;
		if (ValidateUtils.isNotEmptyCollection(visitors)) {
			VisitorInfoEntity visitor = visitors.get(0);
			userAndCarsDTO = new UserAndCarsDTO(visitor.getName(), user.getMobile(), visitor.getIdcardNo());
		} else {
			userAndCarsDTO = new UserAndCarsDTO(ValidateUtils.isNotEmptyString(user.getStaffName()) ? user.getStaffName() : user.getUsername(), user.getMobile(), "");
		}
		List<MyCarEntity> myCars = this.myCarService.queryList(ImmutableMap.of(VisitorConstants.Keys.OPEN_ID, openId));
		userAndCarsDTO.setMyCars(myCars);
		return userAndCarsDTO;
	}

}
