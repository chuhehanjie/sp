package com.chris.smartpark.ibms.entity;

import java.io.Serializable;
import com.chris.base.common.model.SysUpdateInfo;


/**
 * ibms子系统表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 06.18
 */
public class IBMSSubsystemEntity  extends SysUpdateInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//子系统ID
	private Integer id;
	//子系统名称
	private String name;
	//子系统描述
	private String remark;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}
}
