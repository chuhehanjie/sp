package com.chris.smartpark.busi.controller;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.busi.common.VisitorConstants;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chris.smartpark.busi.entity.VisitorInfoHisEntity;
import com.chris.smartpark.busi.service.VisitorInfoHisService;
import com.chris.base.common.utils.PageUtils;
import com.chris.base.common.utils.Query;
import com.chris.base.common.utils.CommonResponse;




/**
 * 访客信息历史表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 11.18
 */
@RestController
@RequestMapping("/busi/visitorinfohis")
public class VisitorInfoHisController {
	@Autowired
	private VisitorInfoHisService visitorInfoHisService;
	
	/**
	 * 列表
	 */
	@PostMapping("/list")
	@RequiresPermissions("busi:visitorinfohis:list")
	public CommonResponse list(@RequestBody Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<VisitorInfoHisEntity> visitorInfoHisList = visitorInfoHisService.queryList(query);
		int total = visitorInfoHisService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(visitorInfoHisList, total, query.getLimit(), query.getPage());
		
		return CommonResponse.ok().put(VisitorConstants.Keys.PAGE, pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@GetMapping("/info/{id}")
	@RequiresPermissions("busi:visitorinfohis:info")
	public CommonResponse info(@PathVariable("id") Long id){
		VisitorInfoHisEntity visitorInfoHis = visitorInfoHisService.queryObject(id);
		
		return CommonResponse.ok().put("visitorInfoHis", visitorInfoHis);
	}
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	@RequiresPermissions("busi:visitorinfohis:save")
	public CommonResponse save(@RequestBody VisitorInfoHisEntity visitorInfoHis){
		visitorInfoHisService.save(visitorInfoHis);
		
		return CommonResponse.ok();
	}
	
	/**
	 * 修改
	 */
	@PostMapping("/update")
	@RequiresPermissions("busi:visitorinfohis:update")
	public CommonResponse update(@RequestBody VisitorInfoHisEntity visitorInfoHis){
		visitorInfoHisService.update(visitorInfoHis);
		
		return CommonResponse.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/delete")
	@RequiresPermissions("busi:visitorinfohis:delete")
	public CommonResponse delete(@RequestBody Long[] ids){
		visitorInfoHisService.deleteBatch(ids);
		
		return CommonResponse.ok();
	}
	
}
