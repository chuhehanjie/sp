package com.chris.smartpark.busi.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 搜索历史表
 * 
 * @author chris
 * @email 258321511@qq.com
 * @since May 15.19
 */
public class SearchHisEntity  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//员工ID
	private Long staffId;
	//员工姓名
	private String staffName;
	//员工手机号
	private String mobile;
	//微信openId
	private String openId;
	//搜索时间
	private Date searchTime;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public Long getStaffId() {
		return staffId;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffName() {
		return staffName;
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
	public void setSearchTime(Date searchTime) {
		this.searchTime = searchTime;
	}

	public Date getSearchTime() {
		return searchTime;
	}
}
