package com.chris.smartpark.busi.controller;

import java.util.List;
import java.util.Map;

import com.chris.base.common.exception.CommonException;
import com.chris.base.common.utils.*;
import com.chris.base.modules.app.annotation.Login;
import com.chris.smartpark.busi.common.VisitorConstants;
import com.chris.smartpark.busi.common.VisitorUtils;
import com.chris.smartpark.busi.dto.CarAuthorizationDTO;
import com.google.common.collect.Lists;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.chris.smartpark.busi.entity.MyCarEntity;
import com.chris.smartpark.busi.service.MyCarService;


/**
 * 我的车辆表
 * 
 * @author chris
 * @email 258321511@qq.com
 * @since May 16.19
 */
@RestController
@RequestMapping("/app/mycar")
public class MyCarController {
	@Autowired
	private MyCarService myCarService;
	
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

		int total = myCarService.queryTotal(query);
		List<MyCarEntity> myCarList = total > 0 ? myCarService.queryList(query) : Lists.newArrayList();

		PageUtils pageUtil = new PageUtils(myCarList, total, query.getLimit(), query.getPage());
		
		return CommonResponse.ok().put(VisitorConstants.Keys.PAGE, pageUtil);
	}


	/**
	 * 管理员查询
	 */
	@PostMapping("/list4Admin")
	@Login
	public CommonResponse list4Admin(@RequestBody Map<String, Object> params){
		if (ValidateUtils.isEmpty(params.get(VisitorConstants.Keys.OPEN_ID))) {
			throw new CommonException("微信openId为空");
		}
		if (ValidateUtils.isEmpty(params.get(VisitorConstants.Keys.KEY_WORD))) {
			throw new CommonException("查询关键字为空");
		}
        if (!VisitorUtils.isAdminRole(params.get(VisitorConstants.Keys.OPEN_ID).toString())) {
			throw new CommonException("当前用户不是管理员");
        }
		params.remove(VisitorConstants.Keys.OPEN_ID);
		//查询列表数据
        Query query = new Query(params);

		int total = myCarService.queryTotal(query);
		List<MyCarEntity> myCarList = total > 0 ? myCarService.queryList(query) : Lists.newArrayList();

		PageUtils pageUtil = new PageUtils(myCarList, total, query.getLimit(), query.getPage());

		return CommonResponse.ok().put(VisitorConstants.Keys.PAGE, pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@GetMapping("/info/{id}")
	@Login
	public CommonResponse info(@PathVariable("id") Long id){
		MyCarEntity myCar = myCarService.queryObject(id);
		
		return CommonResponse.ok().put("myCar", myCar);
	}
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	@Login
	public CommonResponse save(@RequestBody MyCarEntity myCar){
		myCarService.save(myCar);
		
		return CommonResponse.ok();
	}
	
	/**
	 * 修改
	 */
	@PostMapping("/update")
	@Login
	public CommonResponse update(@RequestBody MyCarEntity myCar){
		myCarService.update(myCar);
		
		return CommonResponse.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/delete")
	@Login
	public CommonResponse delete(@RequestBody Long[] ids){
		myCarService.deleteBatch(ids);
		
		return CommonResponse.ok();
	}

	/**
	 * 车辆授权
	 */
	@PostMapping("/authorize")
	@Login
	public CommonResponse authorize(@RequestBody CarAuthorizationDTO carAuthorizationDTO){
		this.validate(carAuthorizationDTO);
		// TODO 需对接车管系统，暂时模拟
		return CommonResponse.ok("授权成功");
	}

    private void validate(CarAuthorizationDTO carAuthorizationDTO) {
	    if (ValidateUtils.isEmptyString(carAuthorizationDTO.getOpenId())) {
	        throw new CommonException("openid不能为空");
        }
        if (ValidateUtils.isEmptyString(carAuthorizationDTO.getCarNo())) {
	        throw new CommonException("车牌号不能为空");
        }
        if (!VisitorUtils.isValidCarNo(carAuthorizationDTO.getCarNo())) {
	        throw new CommonException("车牌号格式不正确");
        }
        if (ValidateUtils.isEmptyString(carAuthorizationDTO.getIdcardNo())) {
	        throw new CommonException("身份证号码不能为空");
        }
        if (!CommonValidator.isIDCard(carAuthorizationDTO.getIdcardNo())) {
	        throw new CommonException("身份证号码格式不正确");
        }
        if (ValidateUtils.isEmpty(carAuthorizationDTO.getEnterTime())) {
	        throw new CommonException("进入时间不能为空");
        }
        if (ValidateUtils.isEmpty(carAuthorizationDTO.getLeaveTime())) {
	        throw new CommonException("离开时间不能为空");
        }
        if (carAuthorizationDTO.getEnterTime().before(DateUtils.currentDate())) {
	        throw new CommonException("进入时间必须大于当前时间");
        }
        if (carAuthorizationDTO.getLeaveTime().before(carAuthorizationDTO.getEnterTime())) {
	        throw new CommonException("离开时间必须大于进入时间");
        }
    }

}
