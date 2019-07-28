package com.chris.smartpark.busi.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.chris.base.common.model.SysUpdateInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * 访客身份信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 11.18
 */
public class VisitorIdcardEntity  extends SysUpdateInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//访客id
	private Long visitorId;
	//物理卡id
	@NotBlank(message = "物理卡id必填", groups = {VisitorIdcardEntity.ValidateIdentity.class})
	private String physicalCardId;
	//身份证号
	@NotBlank(message = "身份证号必填", groups = {VisitorIdcardEntity.ValidateIdentity.class})
	private String idcardNo;
	//姓名
	@NotBlank(message = "姓名必填", groups = {VisitorIdcardEntity.ValidateIdentity.class})
	private String name;
	//人脸识别照片
	private String facePhotoUrl;
	//身份证照片
	private String idcardPhotoUrl;
	//性别  1 男  2 女
	private String gender;
	//住址
	private String address;
	//签发机关
	private String issuOrganization;
	//生效日期
	private Date effDate;
	//失效日期
	private Date expDate;
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
	public void setVisitorId(Long visitorId) {
		this.visitorId = visitorId;
	}

	public Long getVisitorId() {
		return visitorId;
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
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setFacePhotoUrl(String facePhotoUrl) {
		this.facePhotoUrl = facePhotoUrl;
	}

	public String getFacePhotoUrl() {
		return facePhotoUrl;
	}
	public void setIdcardPhotoUrl(String idcardPhotoUrl) {
		this.idcardPhotoUrl = idcardPhotoUrl;
	}

	public String getIdcardPhotoUrl() {
		return idcardPhotoUrl;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}
	public void setIssuOrganization(String issuOrganization) {
		this.issuOrganization = issuOrganization;
	}

	public String getIssuOrganization() {
		return issuOrganization;
	}
	@JsonFormat(pattern="yyyyMMdd",timezone = "GMT+8")
	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public Date getEffDate() {
		return effDate;
	}
	@JsonFormat(pattern="yyyyMMdd",timezone = "GMT+8")
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public Date getExpDate() {
		return expDate;
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
	/* ********************** 参数校验类 *******************************/
	public interface ValidateIdentity{

	}

	public static void main(String[] args) {
		String json = "{\"address\":\"住址\",\"effDate\":\"20180722\",\"expDate\":\"20251125\",\"physicalCardId\":\"20A2C42894518466\",\"idcardNo\":\"342342929384724379\",\"idcardPhotoUrl\":\"身份证照片图片\",\"issuOrganization\":\"签发机关\",\"name\":\"身份证姓名\",\"sex\":1,\"visitorId\":23}";
		VisitorIdcardEntity v = JSONObject.parseObject(json, VisitorIdcardEntity.class);
		System.out.println(v.getEffDate());
		System.out.println(v.getExpDate());
	}
}
