package com.chris.smartpark.busi.controller;

import com.chris.base.common.exception.CommonException;
import com.chris.base.common.utils.CommonResponse;
import com.chris.base.common.utils.PageUtils;
import com.chris.base.common.utils.Query;
import com.chris.base.common.utils.ValidateUtils;
import com.chris.base.modules.app.annotation.Login;
import com.chris.base.modules.app.cache.AppLoginUser;
import com.chris.base.modules.app.cache.AppLoginUserCacheUtils;
import com.chris.smartpark.busi.common.VisitorConstants;
import com.chris.smartpark.busi.common.VisitorUtils;
import com.chris.smartpark.busi.entity.ContactEntity;
import com.chris.smartpark.busi.service.ContactService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;




/**
 * 联系人 controller
 * @author chris
 * @email 258321511@qq.com
 * @since May 16.19
 */
@RestController
@RequestMapping("/app/contact")
public class ContactController {
	@Autowired
	private ContactService contactService;
	
	/**
	 * 列表
	 */
	@PostMapping("/list")
	@Login
	public CommonResponse list(@RequestBody Map<String, Object> params){
		if (ValidateUtils.isEmpty(params.get(VisitorConstants.Keys.OPEN_ID))) {
			throw new CommonException("微信openId为空");
		}
		//查询列表数据
        Query query = new Query(params);

		int total = contactService.queryTotal(query);
		List<ContactEntity> contactList = total > 0 ? contactService.queryList(query) : Lists.newArrayList();

		PageUtils pageUtil = new PageUtils(contactList, total, query.getLimit(), query.getPage());
		
		return CommonResponse.ok().put(VisitorConstants.Keys.PAGE, pageUtil);
	}
	
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	public CommonResponse save(@RequestBody ContactEntity contact){
		contactService.save(contact);
		return CommonResponse.ok();
	}
	
	/**
	 * 修改
	 */
	@PostMapping("/update")
	@Login
	public CommonResponse update(@RequestBody ContactEntity contact){
		contactService.update(contact);
		return CommonResponse.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/delete")
	@Login
	public CommonResponse delete(@RequestBody Long[] ids){
		contactService.deleteBatch(ids);
		
		return CommonResponse.ok();
	}
	
}
