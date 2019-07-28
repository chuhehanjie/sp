package com.chris.smartpark.base.entity;

import java.io.Serializable;
import java.util.Date;
import com.chris.base.common.model.SysUpdateInfo;


/**
 * 设备基本信息
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 22.18
 */
public class BaseDeviceInfoEntity  extends SysUpdateInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//设备ID
	private Integer id;
	//资源设备名称
	private String name;
	//编码
	private String code;
	//子系统ID
	private Integer subsystemId;
	//品牌
	private String brandName;
	//系列
	private String seriesName;
	//型号
	private String modelName;
	//出厂时间
	private Date factoryTime;
	//整机序列号
	private String serialNo;
	//位置ID，如：调度大楼/中栋/3层/310房/东头/上方；附属楼/主楼/2层/走廊/西头/地面
	private Integer locationId;
	//描述性位置
	private String locationDesc;
	//坐标位置，如：F8、H13
	private String locationCoordinate;
	//三维图形对象ID
	private String objId;
	//部门ID
	private Integer deptId;
	//设备描述
	private String remark;
	//责任人
	private Integer personResponsible;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	public void setSubsystemId(Integer subsystemId) {
		this.subsystemId = subsystemId;
	}

	public Integer getSubsystemId() {
		return subsystemId;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandName() {
		return brandName;
	}
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getSeriesName() {
		return seriesName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelName() {
		return modelName;
	}
	public void setFactoryTime(Date factoryTime) {
		this.factoryTime = factoryTime;
	}

	public Date getFactoryTime() {
		return factoryTime;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getSerialNo() {
		return serialNo;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}

	public String getLocationDesc() {
		return locationDesc;
	}
	public void setLocationCoordinate(String locationCoordinate) {
		this.locationCoordinate = locationCoordinate;
	}

	public String getLocationCoordinate() {
		return locationCoordinate;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getObjId() {
		return objId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getDeptId() {
		return deptId;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}
	public void setPersonResponsible(Integer personResponsible) {
		this.personResponsible = personResponsible;
	}

	public Integer getPersonResponsible() {
		return personResponsible;
	}
}
