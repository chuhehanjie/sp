package com.chris.smartpark.base.controller;

import java.util.List;
import java.util.Map;

import com.chris.base.common.utils.ValidateUtils;
import com.chris.smartpark.base.dto.BaseStaffDTO;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.chris.smartpark.base.entity.BaseStaffEntity;
import com.chris.smartpark.base.service.BaseStaffService;
import com.chris.base.common.utils.PageUtils;
import com.chris.base.common.utils.Query;
import com.chris.base.common.utils.CommonResponse;




/**
 * 员工用户
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 15.18
 */
@RestController
@RequestMapping("/base/basestaff")
@Slf4j
public class BaseStaffController {
	@Autowired
	private BaseStaffService baseStaffService;
	
	/**
	 * 列表
	 */
	@PostMapping("/list")
	@RequiresPermissions("base:basestaff:list")
	public CommonResponse list(@RequestBody Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BaseStaffEntity> baseStaffList = baseStaffService.queryList(query);
		int total = baseStaffService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(baseStaffList, total, query.getLimit(), query.getPage());
		
		return CommonResponse.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@GetMapping("/info/{id}")
	@RequiresPermissions("base:basestaff:info")
	public CommonResponse info(@PathVariable("id") Long id){
		BaseStaffEntity baseStaff = baseStaffService.queryObject(id);
		
		return CommonResponse.ok().put("baseStaff", baseStaff);
	}

	/**
	 * 根据组织机构ID查询员工列表
	 * 由第三方调用
	 */
	@GetMapping("/queryStaffListByOrgId.notoken")
	public CommonResponse queryStaffListByOrgId(String orgId){
		log.error("组织机构ID = {}", orgId);
		if (ValidateUtils.isEmptyString(orgId)) {
			return CommonResponse.error("组织机构ID不能为空");
		}
		List<BaseStaffDTO> staffDTOList = baseStaffService.queryStaffListByOrgId(orgId);
		return CommonResponse.ok().setData(staffDTOList);
	}

	/**
	 * 保存
	 */
	@PostMapping("/save")
	@RequiresPermissions("base:basestaff:save")
	public CommonResponse save(@RequestBody BaseStaffEntity baseStaff){
		baseStaffService.save(baseStaff);
		
		return CommonResponse.ok();
	}
	
	/**
	 * 修改
	 */
	@PostMapping("/update")
	@RequiresPermissions("base:basestaff:update")
	public CommonResponse update(@RequestBody BaseStaffEntity baseStaff){
		baseStaffService.update(baseStaff);
		
		return CommonResponse.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/delete")
	@RequiresPermissions("base:basestaff:delete")
	public CommonResponse delete(@RequestBody Long[] ids){
		baseStaffService.deleteBatch(ids);
		
		return CommonResponse.ok();
	}

	@PostMapping("/queryByMobile.notoken")
	public CommonResponse queryByMobile(@RequestBody Map<String, Object> params) {
		if (ValidateUtils.isEmpty(params) || ValidateUtils.isEmpty(params.get("mobile"))) {
			return CommonResponse.error("手机号码不能为空！");
		}
		BaseStaffDTO baseStaffDTO = this.baseStaffService.queryByMobile(params.get("mobile").toString());
		return CommonResponse.ok().setData(baseStaffDTO);
	}
	
}
