package com.chris.smartpark.busi.entity;

import java.io.Serializable;
import com.chris.base.common.model.SysUpdateInfo;


/**
 * 员工工位表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 30.18
 */
public class UserStationEntity  extends SysUpdateInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//员工id
	private Long userId;
	//工位id
	private Long stationId;
	//状态 0 废弃 1正常
	private String status;
	//员工工位IP
	private String ip;
	//员工工位端口
	private String port;
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
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}
	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public Long getStationId() {
		return stationId;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}
	public void setPort(String port) {
		this.port = port;
	}

	public String getPort() {
		return port;
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
