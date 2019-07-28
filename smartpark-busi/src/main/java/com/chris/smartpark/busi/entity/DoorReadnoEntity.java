package com.chris.smartpark.busi.entity;

import java.io.Serializable;
import com.chris.base.common.model.SysUpdateInfo;


/**
 * 门读头信息
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Dec 13.18
 */
public class DoorReadnoEntity  extends SysUpdateInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//读头号
	private Integer readNo;
	//门禁控制器ID
	private Long controllerId;
	//状态 0 无效 1有效
	private String status;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setReadNo(Integer readNo) {
		this.readNo = readNo;
	}

	public Integer getReadNo() {
		return readNo;
	}
	public void setControllerId(Long controllerId) {
		this.controllerId = controllerId;
	}

	public Long getControllerId() {
		return controllerId;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
