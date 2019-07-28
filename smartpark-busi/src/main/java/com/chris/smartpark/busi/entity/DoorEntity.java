package com.chris.smartpark.busi.entity;

import java.io.Serializable;
import com.chris.base.common.model.SysUpdateInfo;


/**
 * 门定义
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Dec 13.18
 */
public class DoorEntity  extends SysUpdateInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//门名称
	private String doorName;
	//门编号
	private String doorNo;
	//门类型 1通道门 2房间门
	private String doorType;
	//所属房间
	private Long roomId;
	//所属楼层
	private Long floorId;
	//所属公司，对应base_organization表ID
	private Long companyId;
	//所属部门，对应base_organization表ID
	private Long deptId;
	//状态 0 废弃 1正常
	private String status;
	//备注
	private String remark;
	//扩展字段1
	private String ext1;
	//扩展字段2
	private String ext2;
	//扩展字段3
	private String ext3;
	//门读头ID，对应sp_door_readno表ID
	private Long doorReadnoId;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setDoorName(String doorName) {
		this.doorName = doorName;
	}

	public String getDoorName() {
		return doorName;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorType(String doorType) {
		this.doorType = doorType;
	}

	public String getDoorType() {
		return doorType;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Long getRoomId() {
		return roomId;
	}
	public void setFloorId(Long floorId) {
		this.floorId = floorId;
	}

	public Long getFloorId() {
		return floorId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getCompanyId() {
		return companyId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getDeptId() {
		return deptId;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
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
	public void setDoorReadnoId(Long doorReadnoId) {
		this.doorReadnoId = doorReadnoId;
	}

	public Long getDoorReadnoId() {
		return doorReadnoId;
	}
}
