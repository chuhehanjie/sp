package com.chris.smartpark.busi.entity;

import java.io.Serializable;
import java.util.Date;
import com.chris.base.common.model.SysUpdateInfo;
import com.chris.base.common.utils.ValidateUtils;


/**
 * 访客预约登记单
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 11.18
 */
public class VisitorReservationEntity  extends SysUpdateInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//订单id
	private Long id;
	//订单号
	private String reservationNo;
	//访客手机号
	private String phone;
	//访客照片地址
	private String photoUrl;
	//访客id
	private Long visitorId;
	//受访者id
	private Long staffId;
	/**
	 * 被访人手机号
	 */
	private String staffMobile;
	//订单状态
	private String status;
	//预约开始时间
	private Date appointStartTime;
	//预约结束时间
	private Date appointEndTime;
	//实际开始时间
	private Date actStartTime;
	//实际结束时间
	private Date actEndTime;
	//预约单类型  1  在线预约    2 线下预约  默认1  
	private Integer type = 1;
	//备注(来访目的)
	private String remark;
	//同行人数
	private Integer companions;
	/**
	 * 审核不通过原因
	 */
	private String ext1;
	/**
	 * 状态流程
	 */
	private String ext2;
	/**
	 * 员工 openId
	 */
	private String ext3;
	//访客物理卡id
	private String physicalCardId;
	private String openId;

	private int isSendNotice;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getReservationNo() {
		return reservationNo;
	}

	public void setReservationNo(String reservationNo) {
		this.reservationNo = reservationNo;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setVisitorId(Long visitorId) {
		this.visitorId = visitorId;
	}

	public Long getVisitorId() {
		return visitorId;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public Long getStaffId() {
		return staffId;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	public void setAppointStartTime(Date appointStartTime) {
		this.appointStartTime = appointStartTime;
	}

	public Date getAppointStartTime() {
		return appointStartTime;
	}
	public void setAppointEndTime(Date appointEndTime) {
		this.appointEndTime = appointEndTime;
	}

	public Date getAppointEndTime() {
		return appointEndTime;
	}
	public void setActStartTime(Date actStartTime) {
		this.actStartTime = actStartTime;
	}

	public Date getActStartTime() {
		return actStartTime;
	}
	public void setActEndTime(Date actEndTime) {
		this.actEndTime = actEndTime;
	}

	public Date getActEndTime() {
		return actEndTime;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}
	public void setCompanions(Integer companions) {
		this.companions = companions;
	}

	public Integer getCompanions() {
		return companions;
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
	public void setPhysicalCardId(String physicalCardId) {
		this.physicalCardId = physicalCardId;
	}

	public String getPhysicalCardId() {
		return physicalCardId;
	}

	public String getStaffMobile() {
		return staffMobile;
	}

	public void setStaffMobile(String staffMobile) {
		this.staffMobile = staffMobile;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getIsSendNotice() {
		return isSendNotice;
	}

	public void setIsSendNotice(int isSendNotice) {
		this.isSendNotice = isSendNotice;
	}

	public void setStatusFlow() {
		this.ext2 = ValidateUtils.isEmptyString(this.ext2) ? this.status : this.ext2 + "," + this.status;
	}

}
