package com.chris.smartpark.busi.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 出入记录表
 * 
 * @author chris
 * @email 258321511@qq.com
 * @since Apr 29.19
 */
public class AccessRecordEntity  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//用户ID
	private Long userId;
	//姓名
	private String userName;
	//所属人类别，1：内部员工，2：外部员工 3：外包 4：访客
	private Integer ownerType;
	//手机号
	private String mobile;
	//部门
	private String deptName;
	//标识类型，1：工号 2：身份证
	private Integer identityType;
	//identity_type=1，则为工号值，identity_type＝2，则为身份证号码
	private String identityValue;
	//抓拍图片
	private String captureImg;
	//出入时间
	private Date accessTime;
	//出入类别，1：进 0：出
	private Integer accessType;
	//通道ID
	private Long channelId;
	//通道名称
	private String channelName;

	/**
	 * 车牌号
	 */
	private String carNumber;

	//扩展字段1
	private String ext1;
	//扩展字段2
	private String ext2;
	//扩展字段3
	private String ext3;
	//创建时间
	private Date createTime;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}
	public void setOwnerType(Integer ownerType) {
		this.ownerType = ownerType;
	}

	public Integer getOwnerType() {
		return ownerType;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptName() {
		return deptName;
	}
	public void setIdentityType(Integer identityType) {
		this.identityType = identityType;
	}

	public Integer getIdentityType() {
		return identityType;
	}
	public void setIdentityValue(String identityValue) {
		this.identityValue = identityValue;
	}

	public String getIdentityValue() {
		return identityValue;
	}
	public void setCaptureImg(String captureImg) {
		this.captureImg = captureImg;
	}

	public String getCaptureImg() {
		return captureImg;
	}
	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	public Date getAccessTime() {
		return accessTime;
	}
	public void setAccessType(Integer accessType) {
		this.accessType = accessType;
	}

	public Integer getAccessType() {
		return accessType;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Long getChannelId() {
		return channelId;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelName() {
		return channelName;
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
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
}
