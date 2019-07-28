package com.chris.smartpark.busi.entity;

import java.io.Serializable;
import com.chris.base.common.model.SysUpdateInfo;


/**
 * 我的车辆表
 * 
 * @author chris
 * @email 258321511@qq.com
 * @since May 16.19
 */
public class MyCarEntity  extends SysUpdateInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//车牌号
	private String carNo;
	//车品牌
	private Integer carBrand;
	//车颜色
	private Integer carColor;
	//是否默认车辆
	private Integer defaultTag;
	//微信openId
	private String openId;
	//备注
	private String remark;

	/**
	 * 汽车品牌名称
	 */
	private String brandName;

	/**
	 * 汽车颜色
	 */
	private String color;

	private String staffName;

	private String mobile;

	private String idcardNo;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getCarNo() {
		return carNo;
	}
	public void setCarBrand(Integer carBrand) {
		this.carBrand = carBrand;
	}

	public Integer getCarBrand() {
		return carBrand;
	}
	public void setCarColor(Integer carColor) {
		this.carColor = carColor;
	}

	public Integer getCarColor() {
		return carColor;
	}
	public void setDefaultTag(Integer defaultTag) {
		this.defaultTag = defaultTag;
	}

	public Integer getDefaultTag() {
		return defaultTag;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getOpenId() {
		return openId;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdcardNo() {
		return idcardNo;
	}

	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}
}
