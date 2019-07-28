package com.chris.smartpark.base.controller;

import com.chris.base.common.exception.CommonException;
import com.chris.base.common.utils.CommonResponse;
import com.chris.base.common.utils.PageUtils;
import com.chris.base.common.utils.Query;
import com.chris.base.common.utils.ValidateUtils;
import com.chris.smartpark.base.entity.TbUserEntity;
import com.chris.smartpark.base.service.TbUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;




/**
 * 用户
 * 
 * @author chris
 * @email 258321511@qq.com
 * @since May 28.19
 */
@RestController
@RequestMapping("/base/tbuser")
public class TbUserController {
	@Autowired
	private TbUserService tbUserService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("base:tbuser:list")
	public CommonResponse list(@RequestBody Map<String, Object> params) {
		//查询列表数据
        Query query = new Query(params);

		List<TbUserEntity> tbUserList = tbUserService.queryList(query);
		int total = tbUserService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tbUserList, total, query.getLimit(), query.getPage());
		
		return CommonResponse.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@GetMapping("/info/{userId}")
	@RequiresPermissions("base:tbuser:info")
	public CommonResponse info(@PathVariable("userId") Long userId){
		TbUserEntity user = tbUserService.queryObject(userId);
		return CommonResponse.ok().put("user", user);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("base:tbuser:save")
	public CommonResponse save(@RequestBody TbUserEntity user){
		tbUserService.save(user);
		
		return CommonResponse.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("base:tbuser:update")
	public CommonResponse update(@RequestBody TbUserEntity user){
		tbUserService.update(user);
		
		return CommonResponse.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/setRole")
	@RequiresPermissions("base:tbuser:setRole")
	public CommonResponse setRole(@RequestBody TbUserEntity user){
		this.tbUserService.setRole(user);
		return CommonResponse.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("base:tbuser:delete")
	public CommonResponse delete(@RequestBody Long[] userIds){
		tbUserService.deleteBatch(userIds);
		
		return CommonResponse.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/setUsername.notoken")
	public CommonResponse setUsername(@RequestBody Map<String, Object> params){
		if (ValidateUtils.isEmpty(params.get("openId"))) {
			throw new CommonException("openId为空");
		}
		if (ValidateUtils.isEmpty(params.get("username"))) {
			throw new CommonException("用户名为空");
		}
		TbUserEntity user = new TbUserEntity(params.get("openId").toString(), params.get("username").toString());
		tbUserService.updateUsernameByOpenId(user);
		return CommonResponse.ok();
	}
	
}
