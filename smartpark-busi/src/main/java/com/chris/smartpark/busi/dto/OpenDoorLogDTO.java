package com.chris.smartpark.busi.dto;

import java.io.Serializable;

public class OpenDoorLogDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long doorId;
    private String doorName;
    private Long userId;
    private String userName;
    private String doorCtrlName;
    private String openDoorTime;
    private String openDoorResult;
    private String openDoorType;
    private Integer inOutFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDoorId() {
        return doorId;
    }

    public void setDoorId(Long doorId) {
        this.doorId = doorId;
    }

    public String getDoorName() {
        return doorName;
    }

    public void setDoorName(String doorName) {
        this.doorName = doorName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDoorCtrlName() {
        return doorCtrlName;
    }

    public void setDoorCtrlName(String doorCtrlName) {
        this.doorCtrlName = doorCtrlName;
    }

    public String getOpenDoorTime() {
        return openDoorTime;
    }

    public void setOpenDoorTime(String openDoorTime) {
        this.openDoorTime = openDoorTime;
    }

    public String getOpenDoorResult() {
        return openDoorResult;
    }

    public void setOpenDoorResult(String openDoorResult) {
        this.openDoorResult = openDoorResult;
    }

    public String getOpenDoorType() {
        return openDoorType;
    }

    public void setOpenDoorType(String openDoorType) {
        this.openDoorType = openDoorType;
    }

    public Integer getInOutFlag() {
        return inOutFlag;
    }

    public void setInOutFlag(Integer inOutFlag) {
        this.inOutFlag = inOutFlag;
    }
}
