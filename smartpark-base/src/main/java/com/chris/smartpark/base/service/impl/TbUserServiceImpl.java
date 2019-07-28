package com.chris.smartpark.base.service.impl;

import com.chris.base.common.exception.CommonException;
import com.chris.base.common.utils.Constant;
import com.chris.base.common.utils.ValidateUtils;
import com.chris.base.modules.app.cache.AppLoginUser;
import com.chris.base.modules.app.cache.AppLoginUserCacheUtils;
import com.chris.base.modules.sys.service.SysUserRoleService;
import com.chris.smartpark.base.dao.TbUserDao;
import com.chris.smartpark.base.dto.BaseStaffDTO;
import com.chris.smartpark.base.entity.TbUserEntity;
import com.chris.smartpark.base.service.BaseStaffService;
import com.chris.smartpark.base.service.TbUserService;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service("tbUserService")
public class TbUserServiceImpl implements TbUserService {
	@Autowired
	private TbUserDao tbUserDao;

	@Autowired
	private BaseStaffService baseStaffService;

	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	@Override
	public TbUserEntity queryObject(Long userId){
		List<TbUserEntity> userList = this.queryList(ImmutableMap.of("userId", userId));
		return ValidateUtils.isEmptyCollection(userList) ? null : userList.get(0);
	}
	
	@Override
	public List<TbUserEntity> queryList(Map<String, Object> map){
		return tbUserDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tbUserDao.queryTotal(map);
	}
	
	@Override
	public void save(TbUserEntity user){
		tbUserDao.save(user);
	}
	
	@Override
	public void update(TbUserEntity user){
		tbUserDao.update(user);
	}
	
	@Override
	public void delete(Long userId){
		tbUserDao.delete(userId);
	}
	
	@Override
	public void deleteBatch(Long[] userIds){
		tbUserDao.deleteBatch(userIds);
	}

	@Override
	public void setRole(TbUserEntity user) {
		// 如果是员工角色
		if (ValidateUtils.equals(user.getRoleId(), Constant.WXRole.STAFF)) {
			BaseStaffDTO staff = this.baseStaffService.queryByMobile(user.getMobile());
			if (ValidateUtils.isEmpty(staff)) {
				throw new CommonException("用户手机号[" + user.getMobile() + "]未关联员工信息，不能设置");
			}
			this.sysUserRoleService.saveOrUpdate(user.getUserId(), ImmutableList.of(Constant.WXRole.STAFF), 2);
			this.changeRole4AppUser(user, Constant.WXRole.STAFF);
		} else if (ValidateUtils.equals(user.getRoleId(), Constant.WXRole.ADMIN)) {
			this.sysUserRoleService.saveOrUpdate(user.getUserId(), ImmutableList.of(Constant.WXRole.ADMIN), 2);
			this.changeRole4AppUser(user, Constant.WXRole.ADMIN);
		}
	}

	private void changeRole4AppUser(TbUserEntity user, long roleId) {
		AppLoginUser appUser = AppLoginUserCacheUtils.getAppLoginUser(user.getOpenId());
		if (ValidateUtils.isNotEmpty(appUser)) {
            appUser.setRoleId(roleId);
            AppLoginUserCacheUtils.reloginMap.put(user.getOpenId(), true);
        }
	}

	@Override
	public void updateUsernameByOpenId(TbUserEntity user) {
		this.tbUserDao.updateUsernameByOpenId(user);
	}
}
