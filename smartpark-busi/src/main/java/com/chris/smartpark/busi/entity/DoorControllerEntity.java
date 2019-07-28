package com.chris.smartpark.busi.entity;

import java.io.Serializable;
import com.chris.base.common.model.SysUpdateInfo;


/**
 * 门禁控制器表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Dec 13.18
 */
public class DoorControllerEntity  extends SysUpdateInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//门禁控制器id
	private Long id;
	//控制器名称
	private String controllerName;
	//控制器编号
	private String controllerNo;
	//控制器IP
	private String controllerIp;
	//控制器MAC地址
	private String macAddr;
	//控制器端口
	private String controllerPort;
	//状态 0 废弃 1正常
	private String status;

	private String mappingDoorId;

	/**
	 * 读头号
	 */
	private int readerNo;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}

	public String getControllerName() {
		return controllerName;
	}
	public void setControllerNo(String controllerNo) {
		this.controllerNo = controllerNo;
	}

	public String getControllerNo() {
		return controllerNo;
	}
	public void setControllerIp(String controllerIp) {
		this.controllerIp = controllerIp;
	}

	public String getControllerIp() {
		return controllerIp;
	}
	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}

	public String getMacAddr() {
		return macAddr;
	}
	public void setControllerPort(String controllerPort) {
		this.controllerPort = controllerPort;
	}

	public String getControllerPort() {
		return controllerPort;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public int getReaderNo() {
		return readerNo;
	}

	public void setReaderNo(int readerNo) {
		this.readerNo = readerNo;
	}

	public String getMappingDoorId() {
		return mappingDoorId;
	}

	public void setMappingDoorId(String mappingDoorId) {
		this.mappingDoorId = mappingDoorId;
	}
}
