package com.chris.smartpark.base.entity;

import java.io.Serializable;
import com.chris.base.common.model.SysUpdateInfo;


/**
 * 园区楼宇信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
public class BaseBuildingEntity  extends SysUpdateInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//楼宇ID
	private Integer id;
	//楼宇名称
	private String buildingName;
	//楼宇地址
	private String address;
	//楼宇层数
	private Integer floors;
	//园区ID
	private Integer parkId;
	//区域ID
	private Integer areaId;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getBuildingName() {
		return buildingName;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}
	public void setFloors(Integer floors) {
		this.floors = floors;
	}

	public Integer getFloors() {
		return floors;
	}
	public void setParkId(Integer parkId) {
		this.parkId = parkId;
	}

	public Integer getParkId() {
		return parkId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getAreaId() {
		return areaId;
	}
}
