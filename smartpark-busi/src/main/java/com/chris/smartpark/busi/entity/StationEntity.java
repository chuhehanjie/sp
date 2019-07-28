package com.chris.smartpark.busi.entity;

import java.io.Serializable;
import com.chris.base.common.model.SysUpdateInfo;


/**
 * 工位表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 30.18
 */
public class StationEntity  extends SysUpdateInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//工位ID
	private Long id;
	//工位名称
	private String stationName;
	//station_desc
	private String stationDesc;
	//工位编号
	private String stationNo;
	//房间ID
	private Long roomId;
	//楼层ID
	private Long floorId;
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

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationName() {
		return stationName;
	}
	public void setStationDesc(String stationDesc) {
		this.stationDesc = stationDesc;
	}

	public String getStationDesc() {
		return stationDesc;
	}
	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getStationNo() {
		return stationNo;
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
}
