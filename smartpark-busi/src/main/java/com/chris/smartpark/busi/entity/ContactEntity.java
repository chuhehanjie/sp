package com.chris.smartpark.busi.entity;

import java.io.Serializable;
import com.chris.base.common.model.SysUpdateInfo;


/**
 * 联系人表
 * 
 * @author chris
 * @email 258321511@qq.com
 * @since May 16.19
 */
public class ContactEntity  extends SysUpdateInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//联系人姓名
	private String name;
	//联系人身份证号
	private String idcardNo;
	//联系人手机号
	private String mobile;
	//微信openId
	private String openId;
	//备注
	private String remark;

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
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
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
}
