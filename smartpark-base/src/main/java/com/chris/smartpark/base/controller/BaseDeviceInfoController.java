package com.chris.smartpark.base.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chris.smartpark.base.entity.BaseDeviceInfoEntity;
import com.chris.smartpark.base.service.BaseDeviceInfoService;
import com.chris.base.common.utils.PageUtils;
import com.chris.base.common.utils.Query;
import com.chris.base.common.utils.CommonResponse;




/**
 * 设备基本信息
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 22.18
 */
@RestController
@RequestMapping("/base/basedeviceinfo")
public class BaseDeviceInfoController {
	@Autowired
	private BaseDeviceInfoService baseDeviceInfoService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("base:basedeviceinfo:list")
	public CommonResponse list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BaseDeviceInfoEntity> baseDeviceInfoList = baseDeviceInfoService.queryList(query);
		int total = baseDeviceInfoService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(baseDeviceInfoList, total, query.getLimit(), query.getPage());
		
		return CommonResponse.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("base:basedeviceinfo:info")
	public CommonResponse info(@PathVariable("id") Integer id){
		BaseDeviceInfoEntity baseDeviceInfo = baseDeviceInfoService.queryObject(id);
		
		return CommonResponse.ok().put("baseDeviceInfo", baseDeviceInfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("base:basedeviceinfo:save")
	public CommonResponse save(@RequestBody BaseDeviceInfoEntity baseDeviceInfo){
		baseDeviceInfoService.save(baseDeviceInfo);
		
		return CommonResponse.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("base:basedeviceinfo:update")
	public CommonResponse update(@RequestBody BaseDeviceInfoEntity baseDeviceInfo){
		baseDeviceInfoService.update(baseDeviceInfo);
		
		return CommonResponse.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("base:basedeviceinfo:delete")
	public CommonResponse delete(@RequestBody Integer[] ids){
		baseDeviceInfoService.deleteBatch(ids);
		
		return CommonResponse.ok();
	}
	
}
