package com.chris.smartpark.busi.entity;

import java.io.Serializable;
import com.chris.base.common.model.SysUpdateInfo;


/**
 * 同行人员信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 04.18
 */
public class CompanionsEntity  extends SysUpdateInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//预约单id
	private Integer reservationId;
	//姓名
	private String name;
	//
	private String physicalCardId;
	//身份证号
	private String idcardNo;
	//手机号
	private String phone;
	//访客照片
	private String photoUrl;
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
	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	public Integer getReservationId() {
		return reservationId;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setPhysicalCardId(String physicalCardId) {
		this.physicalCardId = physicalCardId;
	}

	public String getPhysicalCardId() {
		return physicalCardId;
	}
	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}

	public String getIdcardNo() {
		return idcardNo;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getPhotoUrl() {
		return photoUrl;
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
