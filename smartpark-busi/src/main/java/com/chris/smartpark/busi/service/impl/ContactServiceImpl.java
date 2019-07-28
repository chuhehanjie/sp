package com.chris.smartpark.busi.service.impl;

import com.chris.base.common.exception.CommonException;
import com.chris.base.common.utils.CommonValidator;
import com.chris.base.common.utils.ValidateUtils;
import com.chris.base.modules.app.cache.AppLoginUser;
import com.chris.base.modules.app.cache.AppLoginUserCacheUtils;
import com.chris.smartpark.busi.common.VisitorConstants;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.busi.dao.ContactDao;
import com.chris.smartpark.busi.entity.ContactEntity;
import com.chris.smartpark.busi.service.ContactService;



@Service("contactService")
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactDao contactDao;
	
	@Override
	public ContactEntity queryObject(Long id){
		return contactDao.queryObject(id);
	}
	
	@Override
	public List<ContactEntity> queryList(Map<String, Object> map){
		return contactDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return contactDao.queryTotal(map);
	}
	
	@Override
	public void save(ContactEntity contact){
		this.validate(contact, VisitorConstants.Action.CREATE);
		AppLoginUser appLoginUser = AppLoginUserCacheUtils.getAppLoginUser(contact.getOpenId());
		if (ValidateUtils.isNotEmpty(appLoginUser)) {
			contact.setCreateUserId(appLoginUser.getUserId());
		}
		this.contactDao.save(contact);
	}

	private void validate(ContactEntity contact, int action) {
		if (ValidateUtils.equals(VisitorConstants.Action.MODIFY, action)) {
			if (ValidateUtils.isEmpty(contact.getId())) {
				throw new CommonException("联系人ID为空");
			}
		}
		if (ValidateUtils.isEmptyString(contact.getName())) {
			throw new CommonException("联系人姓名为空");
		}
		if (ValidateUtils.isEmptyString(contact.getIdcardNo())) {
			throw new CommonException("联系人身份证号码为空");
		}
		if (!CommonValidator.isIDCard(contact.getIdcardNo())) {
			throw new CommonException("联系人身份证号码格式不正确");
		}
		if (ValidateUtils.isEmptyString(contact.getMobile())) {
			throw new CommonException("联系人手机号为空");
		}
		if (!CommonValidator.isMobile(contact.getMobile())) {
			throw new CommonException("联系人手机号格式不正确");
		}
		if (ValidateUtils.isEmptyString(contact.getOpenId())) {
			throw new CommonException("openId为空");
		}
		if (ValidateUtils.equals(VisitorConstants.Action.CREATE, action)) {
			if (ValidateUtils.isNotEmptyCollection(this.queryList(ImmutableMap.of("mobile", contact.getMobile())))) {
				throw new CommonException("联系人手机号[" + contact.getMobile() + "]已添加");
			}
			if (ValidateUtils.isNotEmptyCollection(this.queryList(ImmutableMap.of("idcardNo", contact.getIdcardNo())))) {
				throw new CommonException("联系人身份证号[" + contact.getIdcardNo() + "]已添加");
			}
		}
	}

	@Override
	public void update(ContactEntity contact){
		this.validate(contact, VisitorConstants.Action.MODIFY);
		AppLoginUser appLoginUser = AppLoginUserCacheUtils.getAppLoginUser(contact.getOpenId());
		if (ValidateUtils.isNotEmpty(appLoginUser)) {
			contact.setUpdateUserId(appLoginUser.getUserId());
		}
		this.contactDao.update(contact);
	}
	
	@Override
	public void delete(Long id){
		this.contactDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		contactDao.deleteBatch(ids);
	}
	
}
