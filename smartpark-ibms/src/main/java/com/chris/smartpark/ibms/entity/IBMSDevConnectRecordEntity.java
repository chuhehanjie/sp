package com.chris.smartpark.ibms.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 设备连接记录表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
public class IBMSDevConnectRecordEntity  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//记录ID
	private Integer id;
	//设备ID
	private Integer deviceId;
	//子系统ID
	private Integer subsystemId;
	//连接状态，1：已连接，0：未连接
	private Integer connectStatus;
	//创建时间
	private Date createTime;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getDeviceId() {
		return deviceId;
	}
	public void setSubsystemId(Integer subsystemId) {
		this.subsystemId = subsystemId;
	}

	public Integer getSubsystemId() {
		return subsystemId;
	}
	public void setConnectStatus(Integer connectStatus) {
		this.connectStatus = connectStatus;
	}

	public Integer getConnectStatus() {
		return connectStatus;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}
}
