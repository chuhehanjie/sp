package com.chris.smartpark.base.entity;

import java.io.Serializable;
import com.chris.base.common.model.SysUpdateInfo;


/**
 * 园区房间信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
public class BaseRoomEntity  extends SysUpdateInfo  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//房间ID
	private Integer id;
	//房间号
	private String roomNo;
	//房间名称
	private String roomName;
	//房间类型
	private String roomType;
	//楼层ID
	private Integer floorId;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomName() {
		return roomName;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomType() {
		return roomType;
	}
	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

	public Integer getFloorId() {
		return floorId;
	}
}
