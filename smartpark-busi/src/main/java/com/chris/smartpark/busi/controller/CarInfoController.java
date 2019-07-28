package com.chris.smartpark.busi.controller;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.busi.common.VisitorConstants;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chris.smartpark.busi.entity.CarInfoEntity;
import com.chris.smartpark.busi.service.CarInfoService;
import com.chris.base.common.utils.PageUtils;
import com.chris.base.common.utils.Query;
import com.chris.base.common.utils.CommonResponse;




/**
 * 车辆信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 04.18
 */
@RestController
@RequestMapping("/app/carinfo")
public class CarInfoController {
	@Autowired
	private CarInfoService carInfoService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public CommonResponse list(@RequestBody Map<String, Object> params){
		//分页查询列表数据
        Query query = new Query(params);

		List<CarInfoEntity> carInfoList = carInfoService.queryList(query);
		int total = carInfoService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(carInfoList, total, query.getLimit(), query.getPage());
		
		return CommonResponse.ok().put(VisitorConstants.Keys.PAGE, pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public CommonResponse info(@PathVariable("id") Long id){
		CarInfoEntity carInfo = carInfoService.queryObject(id);
		
		return CommonResponse.ok().put("carInfo", carInfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public CommonResponse save(@RequestBody CarInfoEntity carInfo){
		carInfoService.save(carInfo);
		
		return CommonResponse.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public CommonResponse update(@RequestBody CarInfoEntity carInfo){
		carInfoService.update(carInfo);
		
		return CommonResponse.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	//@RequiresPermissions("busi:carinfo:delete")
	public CommonResponse delete(@RequestBody Long[] ids){
		carInfoService.deleteBatch(ids);
		
		return CommonResponse.ok();
	}
	
}
