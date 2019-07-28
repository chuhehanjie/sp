package com.chris.smartpark.busi.entity;

import java.io.Serializable;
import java.util.List;

import com.chris.base.common.model.SysUpdateInfo;
import com.chris.base.modules.oss.entity.SysAttachmentEntity;


/**
 * 工单表
 * 
 * @author chris
 * @email 258321511@qq.com
 * @since Apr 29.19
 */
public class WorkOrderEntity  extends SysUpdateInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Long id;
	//标题
	private String title;
	//工单内容
	private String content;
	//工单类型
	private Integer workOrderType;
	//状态
	private Integer status;
	//扩展字段1
	private String ext1;
	//扩展字段2
	private String ext2;
	//扩展字段3
	private String ext3;

	private String openId;

	private List<SysAttachmentEntity> attachmentList;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
	public void setWorkOrderType(Integer workOrderType) {
		this.workOrderType = workOrderType;
	}

	public Integer getWorkOrderType() {
		return workOrderType;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
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

	public List<SysAttachmentEntity> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<SysAttachmentEntity> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
}
