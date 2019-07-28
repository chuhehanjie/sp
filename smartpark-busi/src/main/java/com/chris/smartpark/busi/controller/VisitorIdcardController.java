package com.chris.smartpark.busi.controller;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.busi.common.VisitorConstants;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chris.smartpark.busi.entity.VisitorIdcardEntity;
import com.chris.smartpark.busi.service.VisitorIdcardService;
import com.chris.base.common.utils.PageUtils;
import com.chris.base.common.utils.Query;
import com.chris.base.common.utils.CommonResponse;




/**
 * 访客身份信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 11.18
 */
@RestController
@RequestMapping("/busi/visitoridcard")
public class VisitorIdcardController {
	@Autowired
	private VisitorIdcardService visitorIdcardService;
	
	/**
	 * 列表
	 */
	@PostMapping("/list")
	@RequiresPermissions("busi:visitoridcard:list")
	public CommonResponse list(@RequestBody Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<VisitorIdcardEntity> visitorIdcardList = visitorIdcardService.queryList(query);
		int total = visitorIdcardService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(visitorIdcardList, total, query.getLimit(), query.getPage());
		
		return CommonResponse.ok().put(VisitorConstants.Keys.PAGE, pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@GetMapping("/info/{id}")
	@RequiresPermissions("busi:visitoridcard:info")
	public CommonResponse info(@PathVariable("id") Long id){
		VisitorIdcardEntity visitorIdcard = visitorIdcardService.queryObject(id);
		
		return CommonResponse.ok().put("visitorIdcard", visitorIdcard);
	}
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	@RequiresPermissions("busi:visitoridcard:save")
	public CommonResponse save(@RequestBody VisitorIdcardEntity visitorIdcard){
		visitorIdcardService.save(visitorIdcard);
		
		return CommonResponse.ok();
	}
	
	/**
	 * 修改
	 */
	@PostMapping("/update")
	@RequiresPermissions("busi:visitoridcard:update")
	public CommonResponse update(@RequestBody VisitorIdcardEntity visitorIdcard){
		visitorIdcardService.update(visitorIdcard);
		
		return CommonResponse.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/delete")
	@RequiresPermissions("busi:visitoridcard:delete")
	public CommonResponse delete(@RequestBody Long[] ids){
		visitorIdcardService.deleteBatch(ids);
		
		return CommonResponse.ok();
	}
	
}
