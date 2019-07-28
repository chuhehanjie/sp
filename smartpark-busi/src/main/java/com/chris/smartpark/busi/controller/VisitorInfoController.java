package com.chris.smartpark.busi.controller;

import java.util.List;
import java.util.Map;

import com.chris.base.common.utils.*;
import com.chris.base.modules.app.annotation.Login;
import com.chris.smartpark.busi.common.VisitorConstants;
import com.chris.smartpark.busi.dto.UserAndCarsDTO;
import com.chris.smartpark.busi.entity.VisitorInfoHisEntity;
import com.chris.smartpark.busi.service.VisitorInfoHisService;
import com.google.common.collect.ImmutableMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chris.smartpark.busi.entity.VisitorInfoEntity;
import com.chris.smartpark.busi.service.VisitorInfoService;


/**
 * 访客信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 11.18
 */
@RestController
@RequestMapping("/busi/visitorinfo")
public class VisitorInfoController {
	@Autowired
	private VisitorInfoService visitorInfoService;

	@Autowired
	private VisitorInfoHisService visitorInfoHisService;
	
	/**
	 * 列表
	 */
	@PostMapping("/list")
	@RequiresPermissions("busi:visitorinfo:list")
	public CommonResponse list(@RequestBody Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<VisitorInfoEntity> visitorInfoList = visitorInfoService.queryList(query);
		int total = visitorInfoService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(visitorInfoList, total, query.getLimit(), query.getPage());
		
		return CommonResponse.ok().put(VisitorConstants.Keys.PAGE, pageUtil);
	}

	/**
	 * 根据身份证查询访客信息
	 * @param idcardNo
	 * @return
	 */
	@PostMapping("/queryByIdcard.notoken")
	@Login
	public CommonResponse queryByIdcardNo(String idcardNo){
		if (!CommonValidator.isIDCard(idcardNo)) {
			return CommonResponse.error("无效的身份证号码");
		}
		List<VisitorInfoHisEntity> visitors = this.visitorInfoHisService.queryByIdcardNo(idcardNo);
		return CommonResponse.ok().setData(visitors);
	}

	/**
	 * 根据 openid 查询用户信息和车辆信息
	 * @param openId
	 * @return
	 */
	@GetMapping("/queryUserAndCars.notoken")
	@Login
	public CommonResponse queryUserAndCars(String openId){
		if (ValidateUtils.isEmptyString(openId)) {
			return CommonResponse.error("微信openId为空");
		}
		UserAndCarsDTO userAndCarsDTO = this.visitorInfoService.queryUserAndCars(openId);
		return CommonResponse.ok().setData(userAndCarsDTO);
	}


	
	
	/**
	 * 信息
	 */
	@GetMapping("/info/{id}")
	@RequiresPermissions("busi:visitorinfo:info")
	public CommonResponse info(@PathVariable("id") Long id){
		VisitorInfoEntity visitorInfo = visitorInfoService.queryObject(id);
		
		return CommonResponse.ok().put("visitorInfo", visitorInfo);
	}
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	@RequiresPermissions("busi:visitorinfo:save")
	public CommonResponse save(@RequestBody VisitorInfoEntity visitorInfo){
		visitorInfoService.save(visitorInfo);
		
		return CommonResponse.ok();
	}
	
	/**
	 * 修改
	 */
	@PostMapping("/update")
	@RequiresPermissions("busi:visitorinfo:update")
	public CommonResponse update(@RequestBody VisitorInfoEntity visitorInfo){
		visitorInfoService.update(visitorInfo);
		
		return CommonResponse.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/delete")
	@RequiresPermissions("busi:visitorinfo:delete")
	public CommonResponse delete(@RequestBody Long[] ids){
		visitorInfoService.deleteBatch(ids);
		
		return CommonResponse.ok();
	}
	
}
