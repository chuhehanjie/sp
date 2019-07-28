package com.chris.smartpark.base.entity;

import java.io.Serializable;
import com.chris.base.common.model.SysUpdateInfo;


/**
 * 园区楼层信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
public class BaseFloorEntity  extends SysUpdateInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//楼层ID
	private Integer id;
	//楼层名称
	private String floorName;
	//楼层房间数
	private Integer rooms;
	//公司ID
	private Integer companyId;
	//楼宇ID
	private Integer buildingId;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public String getFloorName() {
		return floorName;
	}
	public void setRooms(Integer rooms) {
		this.rooms = rooms;
	}

	public Integer getRooms() {
		return rooms;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getCompanyId() {
		return companyId;
	}
	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public Integer getBuildingId() {
		return buildingId;
	}
}
