package com.chris.smartpark.base.entity;

import java.io.Serializable;
import java.util.Date;
import com.chris.base.common.model.SysUpdateInfo;


/**
 * 园区信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
public class BaseParkEntity  extends SysUpdateInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//园区ID
	private Integer id;
	//园区名称
	private String name;
	//园区所在省
	private String province;
	//园区所在城市
	private String city;
	//园区所在区县
	private String area;
	//园区详细地址
	private String detailAddress;
	//经度
	private String longitude;
	//纬度
	private String latitude;
	//始建时间
	private Date buildingTime;
	//建筑面积
	private String buildingArea;
	//园区简介
	private String remark;
	//园区图片
	private String parkPicUrl;
	//状态，1：有效 0：无效
	private String status;

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
	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvince() {
		return province;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}
	public void setArea(String area) {
		this.area = area;
	}

	public String getArea() {
		return area;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getDetailAddress() {
		return detailAddress;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLongitude() {
		return longitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLatitude() {
		return latitude;
	}
	public void setBuildingTime(Date buildingTime) {
		this.buildingTime = buildingTime;
	}

	public Date getBuildingTime() {
		return buildingTime;
	}
	public void setBuildingArea(String buildingArea) {
		this.buildingArea = buildingArea;
	}

	public String getBuildingArea() {
		return buildingArea;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}
	public void setParkPicUrl(String parkPicUrl) {
		this.parkPicUrl = parkPicUrl;
	}

	public String getParkPicUrl() {
		return parkPicUrl;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
