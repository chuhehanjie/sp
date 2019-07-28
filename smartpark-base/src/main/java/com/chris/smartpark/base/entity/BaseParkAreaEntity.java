package com.chris.smartpark.base.entity;

import java.io.Serializable;
import com.chris.base.common.model.SysUpdateInfo;


/**
 * 园区区域信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
public class BaseParkAreaEntity  extends SysUpdateInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//区域ID
	private Integer id;
	//区域名称
	private String areaName;
	//区域描述
	private String areaDesc;
	//园区ID
	private Integer parkId;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaName() {
		return areaName;
	}
	public void setAreaDesc(String areaDesc) {
		this.areaDesc = areaDesc;
	}

	public String getAreaDesc() {
		return areaDesc;
	}
	public void setParkId(Integer parkId) {
		this.parkId = parkId;
	}

	public Integer getParkId() {
		return parkId;
	}
}
