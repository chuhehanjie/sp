package com.chris.smartpark.busi.entity;

import java.io.Serializable;
import java.util.List;

import com.chris.base.common.model.SysUpdateInfo;


/**
 * 访客信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 11.18
 */
public class VisitorInfoEntity  extends SysUpdateInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//访客id
	private Long id;
	//姓名
	private String name;
	//身份证号
	private String idcardNo;
	//手机号
	private String phone;
	//访客类型
	private Integer type;
	//访客照片
	private String photoUrl;
	//单位名称
	private String company;
	//备注
	private String remark;
	/**
	 * 访客对应的微信 openid
	 * 用访客手机号与用户表关联
	 */
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
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
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
	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompany() {
		return company;
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
