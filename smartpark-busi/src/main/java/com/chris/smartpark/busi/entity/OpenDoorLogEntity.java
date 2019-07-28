package com.chris.smartpark.busi.entity;

import java.io.Serializable;
import java.util.Date;
import com.chris.base.common.model.SysUpdateInfo;


/**
 * 开门日志表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 30.18
 */
public class OpenDoorLogEntity  extends SysUpdateInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//门号
	private Long doorId;
	//开门用户id
	private Long userId;
	//用户类型
	private String userType;
	//开门时间
	private Date openTime;
	//开门类型
	private String openType;
	//开门结果 1成功 0 失败
	private String openResult;
	//开门结果描述
	private String openResultDesc;
	//进出门标识 1进 2出
	private String inOutFlag;
	//关联id
	private Long relId;
	//备注
	private String remark;
	/**
	 * 扩展字段1
	 */
	private String ext1;
	//扩展字段2
	private String ext2;
	/**
	 * 控制器相关详细信息
	 */
	private String ext3;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setDoorId(Long doorId) {
		this.doorId = doorId;
	}

	public Long getDoorId() {
		return doorId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserType() {
		return userType;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getOpenTime() {
		return openTime;
	}
	public void setOpenType(String openType) {
		this.openType = openType;
	}

	public String getOpenType() {
		return openType;
	}
	public void setOpenResult(String openResult) {
		this.openResult = openResult;
	}

	public String getOpenResult() {
		return openResult;
	}
	public void setOpenResultDesc(String openResultDesc) {
		this.openResultDesc = openResultDesc;
	}

	public String getOpenResultDesc() {
		return openResultDesc;
	}
	public void setInOutFlag(String inOutFlag) {
		this.inOutFlag = inOutFlag;
	}

	public String getInOutFlag() {
		return inOutFlag;
	}
	public void setRelId(Long relId) {
		this.relId = relId;
	}

	public Long getRelId() {
		return relId;
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
