package com.chris.smartpark.busi.controller;

import com.alibaba.fastjson.JSONObject;
import com.chris.base.common.annotation.SysLog;
import com.chris.base.common.exception.CommonException;
import com.chris.base.common.utils.CommonResponse;
import com.chris.base.common.utils.PageUtils;
import com.chris.base.common.utils.RedisUtils;
import com.chris.base.common.utils.ValidateUtils;
import com.chris.base.modules.app.annotation.Login;
import com.chris.smartpark.busi.common.VisitorConstants;
import com.chris.smartpark.busi.common.WXNoticeMsgUtils;
import com.chris.smartpark.busi.dto.AuthIdCardDTO;
import com.chris.smartpark.busi.dto.ReservationOrderApproveDTO;
import com.chris.smartpark.busi.dto.ReservationOrderDTO;
import com.chris.smartpark.busi.entity.VisitorIdcardEntity;
import com.chris.smartpark.busi.entity.VisitorReservationEntity;
import com.chris.smartpark.busi.service.VisitorReservationService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * 访客预约登记单
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 04.18
 */
@Slf4j
@RestController
@RequestMapping("/app/visitorreservation")
public class VisitorReservationController {
	@Autowired
	private VisitorReservationService visitorReservationService;

	@Autowired
	private RedisUtils redisUtils;

	/**
	 * 根据条件分页查询员工或管理员对应的预约单
	 * @param params
	 * @return
	 */
	@PostMapping("/queryReservationOrdersByOpenId")
	@Login
	public CommonResponse queryReservationOrdersByOpenId(@RequestBody Map<String, Object> params){
		if (ValidateUtils.isEmpty(params.get(VisitorConstants.Keys.OPEN_ID))) {
			return CommonResponse.error("openId为空！");
		}
		if (ValidateUtils.isEmpty(params.get(VisitorConstants.Keys.TYPE))) {
			return CommonResponse.error("type为空！");
		}
		if (ValidateUtils.isEmpty(params.get(VisitorConstants.Keys.PAGE))) {
			params.put(VisitorConstants.Keys.PAGE, VisitorConstants.Page.PAGE_NO + "");
		}
		if (ValidateUtils.isEmpty(params.get(VisitorConstants.Keys.LIMIT))) {
			params.put(VisitorConstants.Keys.LIMIT, VisitorConstants.Page.PAGE_SIZE + "");
		}
		PageUtils pageUtil = this.visitorReservationService.queryReservationOrdersByOpenId(params);
		return CommonResponse.ok().put(VisitorConstants.Keys.PAGE, pageUtil);
	}

	@PostMapping("/searchReservationOrder")
	@RequiresPermissions("busi:reversationorder:list")
	public CommonResponse searchReservationOrder(@RequestBody Map<String, Object> params){
		if (ValidateUtils.isEmpty(params.get(VisitorConstants.Keys.PAGE))) {
			params.put(VisitorConstants.Keys.PAGE, VisitorConstants.Page.PAGE_NO + "");
		}
		if (ValidateUtils.isEmpty(params.get(VisitorConstants.Keys.LIMIT))) {
			params.put(VisitorConstants.Keys.LIMIT, VisitorConstants.Page.PAGE_SIZE + "");
		}
		PageUtils pageUtil = this.visitorReservationService.searchReservationOrders(params);
		return CommonResponse.ok().put(VisitorConstants.Keys.PAGE, pageUtil);
	}


	
	/**
	 * 信息
	 */
	@GetMapping("/info/{id}")
	public CommonResponse info(@PathVariable("id") Long id){
		ReservationOrderDTO reservationOrder = visitorReservationService.queryReservationOrderDetail(id);
		return CommonResponse.ok().setData(reservationOrder);
	}

	/**
	 * 信息
	 */
	@RequestMapping("/authentication")
	@SysLog("身份证识别和门禁授权")
	public CommonResponse authentication(@RequestBody @Validated(VisitorIdcardEntity.ValidateIdentity.class)VisitorIdcardEntity visitorIdcardEntity,BindingResult result){
		log.info("========身份证识别开始并同步信息到门禁系统=====");
		ValidateUtils.validatedParams(result);
		visitorReservationService.checkIdCardAndGetAuth(visitorIdcardEntity);
		return CommonResponse.ok();
	}

	/**
	 * 信息
	 */
	@RequestMapping("/isAppointed")
	public CommonResponse isAppointed(@RequestBody @Validated(AuthIdCardDTO.ValidateIdCard.class)AuthIdCardDTO authIdCardDTO, BindingResult result){
		log.info("========身份证识别开始鉴定是否有有效的预约单=====");
		ValidateUtils.validatedParams(result);
		CommonResponse res = visitorReservationService.checkIdCard(authIdCardDTO);
		return res;
	}

	/**
	 * 上传对应预约单的访客信息 将来访者的信息上传到主控平台、或者现场的预约以后进行授权再次刷卡进入
	 * 提供给第三方调用
	 */
	@RequestMapping("/saveVisitorIdCard")
	@SysLog("生成线上预约单并授权")
	public CommonResponse saveVisitorIdCard(@RequestBody @Validated(AuthIdCardDTO.ValidateSaveVisitorIdCard.class)AuthIdCardDTO authIdCardDTO, BindingResult result){
		log.error("========身份证识别开始将来访者的信息上传到主控平台=====, 请求参数 = {}", JSONObject.toJSONString(authIdCardDTO));
		ValidateUtils.validatedParams(result);
		CommonResponse res = visitorReservationService.saveCardAndGetAuth(authIdCardDTO);
		return res;
	}

	/**
	 * 预约单保存
	 * 由小程序调用
	 */
	@RequestMapping("/save")
	@Login
	@SysLog("预约单生成（小程序）")
	public CommonResponse save(@RequestBody  @Validated(ReservationOrderDTO.ValidateSaveReservation.class)ReservationOrderDTO reservationOrderDTO, BindingResult result){
		log.error("线上预约请求参数 = {}", JSONObject.toJSONString(reservationOrderDTO));
		ValidateUtils.validatedParams(result);
		long id = visitorReservationService.createReservationOrder(reservationOrderDTO);
		return CommonResponse.ok().put("id", id);
	}

	/**
	 * 现场来访保存预约信息到主控平台
	 * 提供给第三方调用
	 */
	@RequestMapping("/localSave")
	@SysLog("预约单生成（线下）")
	public CommonResponse localSave(@RequestBody  @Validated(ReservationOrderDTO.ValidateLocalSave.class)ReservationOrderDTO reservationOrderDTO, BindingResult result){
		reservationOrderDTO.setIsLocalappoint(VisitorConstants.isLocalappoint.OFFLINE);
		log.error("现场预约请求参数 = {}", JSONObject.toJSONString(reservationOrderDTO));
		ValidateUtils.validatedParams(result);
		long id = visitorReservationService.createReservationOrder(reservationOrderDTO);
		return CommonResponse.ok("现场预约成功").put("isSuccess", "true").put("id", id);
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public CommonResponse update(@RequestBody VisitorReservationEntity visitorReservation){
		visitorReservationService.update(visitorReservation);
		return CommonResponse.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@Login
	public CommonResponse delete(@RequestBody Long[] ids){
		visitorReservationService.deleteBatch(ids);
		return CommonResponse.ok();
	}
    /**
     * 访客预约审核
     */
    @RequestMapping("/approve")
	@Login
	@SysLog("预约单审核")
    public CommonResponse approve(@RequestBody ReservationOrderApproveDTO authorizeDTO){
		this.visitorReservationService.approve(authorizeDTO);
		return CommonResponse.ok();
    }

	/**
	 * 上传文件
	 * 提供给第三方调用
	 */
	@RequestMapping("/upload")
	public CommonResponse upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
		if (file.isEmpty()) {
			throw new CommonException("上传文件不能为空！");
		}
		String reservationOrderId = request.getParameter("visitorId");
		log.error("upload resevationOrderId = {}", reservationOrderId);
		if (ValidateUtils.isEmptyString(reservationOrderId)) {
			throw new CommonException("预约单ID不能为空！");
		}
		this.visitorReservationService.uploadVisitorPhoto(file, reservationOrderId);
		return CommonResponse.ok();
	}

	@RequestMapping("/saveFormId")
	public CommonResponse saveFormId(@RequestBody Map<String, Object> params) {
		String openId = params.get(VisitorConstants.Keys.OPEN_ID).toString();
		String formId = params.get(VisitorConstants.Keys.FORM_ID).toString();
		WXNoticeMsgUtils.saveFormId(openId, formId);
		return CommonResponse.ok();
	}
}
