package com.chris.smartpark.ibms.entity;

import java.io.Serializable;


/**
 * 数据采集表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
public class IBMSCollectionAttrEntity  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//采集属性ID
	private Integer id;
	//属性名称
	private String attrName;
	//属性单位
	private String attrUnit;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrName() {
		return attrName;
	}
	public void setAttrUnit(String attrUnit) {
		this.attrUnit = attrUnit;
	}

	public String getAttrUnit() {
		return attrUnit;
	}
}
