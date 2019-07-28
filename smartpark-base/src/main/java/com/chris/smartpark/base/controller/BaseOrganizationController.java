package com.chris.smartpark.base.controller;

import java.util.List;
import java.util.Map;

import com.chris.base.common.tree.TreeNode;
import com.chris.base.common.utils.ValidateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.chris.smartpark.base.entity.BaseOrganizationEntity;
import com.chris.smartpark.base.service.BaseOrganizationService;
import com.chris.base.common.utils.PageUtils;
import com.chris.base.common.utils.Query;
import com.chris.base.common.utils.CommonResponse;




/**
 * 组织机构
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 15.18
 */
@RestController
@RequestMapping("/base/baseorganization")
@Slf4j
public class BaseOrganizationController {
	@Autowired
	private BaseOrganizationService baseOrganizationService;
	
	/**
	 * 列表
	 */
	@PostMapping("/list")
	@RequiresPermissions("base:baseorganization:list")
	public CommonResponse list(@RequestBody Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BaseOrganizationEntity> baseOrganizationList = baseOrganizationService.queryList(query);
		int total = baseOrganizationService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(baseOrganizationList, total, query.getLimit(), query.getPage());
		
		return CommonResponse.ok().put("page", pageUtil);
	}

	/**
	 * 根据园区ID查询园区对应的组织机构
	 * 给第三方提供的接口
	 * @author chris
	 * @since Sep 09.18
	 */
	@GetMapping("/queryBaseOrgList.notoken")
	public CommonResponse queryBaseOrgList(Long parkId){
		log.error("园区ID = {}", parkId);
		if (ValidateUtils.isEmpty(parkId)) {
			return CommonResponse.error("园区ID不能为空");
		}
		List<TreeNode> baseOrgNodes = this.baseOrganizationService.queryBaseOrgHierarchyByParkId(parkId);
		return CommonResponse.ok().setData(ValidateUtils.isNotEmptyCollection(baseOrgNodes) ? baseOrgNodes.get(0) : null);
	}



	/**
	 * 信息
	 */
	@GetMapping("/info/{id}")
	@RequiresPermissions("base:baseorganization:info")
	public CommonResponse info(@PathVariable("id") Long id){
		BaseOrganizationEntity baseOrganization = baseOrganizationService.queryObject(id);
		
		return CommonResponse.ok().put("baseOrganization", baseOrganization);
	}
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	@RequiresPermissions("base:baseorganization:save")
	public CommonResponse save(@RequestBody BaseOrganizationEntity baseOrganization){
		baseOrganizationService.save(baseOrganization);
		
		return CommonResponse.ok();
	}
	
	/**
	 * 修改
	 */
	@PostMapping("/update")
	@RequiresPermissions("base:baseorganization:update")
	public CommonResponse update(@RequestBody BaseOrganizationEntity baseOrganization){
		baseOrganizationService.update(baseOrganization);
		
		return CommonResponse.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/delete")
	@RequiresPermissions("base:baseorganization:delete")
	public CommonResponse delete(@RequestBody Long[] ids){
		baseOrganizationService.deleteBatch(ids);
		
		return CommonResponse.ok();
	}
	
}
