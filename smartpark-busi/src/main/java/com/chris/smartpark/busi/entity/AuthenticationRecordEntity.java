package com.chris.smartpark.busi.entity;

import java.io.Serializable;
import java.util.Date;
import com.chris.base.common.model.SysUpdateInfo;


/**
 * 预约审核表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 11.18
 */
public class AuthenticationRecordEntity  extends SysUpdateInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//预约id
	private Long reservationId;
	//访客id
	private Long visitorId;
	//申请开始时间
	private Date applyBeginTime;
	//申请结束时间
	private Date applyEndTime;
	//授权开始时间
	private Date authBeginTime;
	//授权结束时间
	private Date authEndTime;
	//授权状态
	private String status;
	//授权编码 第一位消息,如短信 微信,第二位 人脸 第三位车道 第四位门禁
	private String authCode;
	//授权描述
	private String authDesc;
	//授权时间
	private Date authTime;
	//备注
	private String remark;
	//扩展字段1
	private String ext1;
	//扩展字段2
	private String ext2;
	//扩展字段3
	private String ext3;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public Long getReservationId() {
		return reservationId;
	}
	public void setVisitorId(Long visitorId) {
		this.visitorId = visitorId;
	}

	public Long getVisitorId() {
		return visitorId;
	}
	public void setApplyBeginTime(Date applyBeginTime) {
		this.applyBeginTime = applyBeginTime;
	}

	public Date getApplyBeginTime() {
		return applyBeginTime;
	}
	public void setApplyEndTime(Date applyEndTime) {
		this.applyEndTime = applyEndTime;
	}

	public Date getApplyEndTime() {
		return applyEndTime;
	}
	public void setAuthBeginTime(Date authBeginTime) {
		this.authBeginTime = authBeginTime;
	}

	public Date getAuthBeginTime() {
		return authBeginTime;
	}
	public void setAuthEndTime(Date authEndTime) {
		this.authEndTime = authEndTime;
	}

	public Date getAuthEndTime() {
		return authEndTime;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getAuthCode() {
		return authCode;
	}
	public void setAuthDesc(String authDesc) {
		this.authDesc = authDesc;
	}

	public String getAuthDesc() {
		return authDesc;
	}
	public void setAuthTime(Date authTime) {
		this.authTime = authTime;
	}

	public Date getAuthTime() {
		return authTime;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt1() {
		return ext1;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getExt2() {
		return ext2;
	}
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	public String getExt3() {
		return ext3;
	}
}
