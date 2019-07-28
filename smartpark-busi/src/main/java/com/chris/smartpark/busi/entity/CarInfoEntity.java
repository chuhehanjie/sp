package com.chris.smartpark.busi.entity;

import java.io.Serializable;
import com.chris.base.common.model.SysUpdateInfo;


/**
 * 车辆信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 04.18
 */
public class CarInfoEntity  extends SysUpdateInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//车牌号
	private String carNo;
	//驾驶员姓名
	private String name;
	//手机号
	private String phone;
	//预约单id
	private Long reservationId;
	//扩展字段1
	private String ext1;
	//扩展字段2
	private String ext2;
	//扩展字段3
	private String ext3;
	//备注
	private String remark;

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
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public Long getReservationId() {
		return reservationId;
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
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}
}
