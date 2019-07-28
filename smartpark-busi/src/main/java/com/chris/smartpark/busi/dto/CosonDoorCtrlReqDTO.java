package com.chris.smartpark.busi.dto;

import java.io.Serializable;

public class CosonDoorCtrlReqDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer doorId;
    private Integer mappingDoorId;
    private String doorCtrlIp;
    private String userCardId;
    private String startTime;
    private String endTime;
    /**
     * 用户类型（默认临时卡）
     * 0：固定卡
     * 1：临时卡
     */
    private int userType = 1;
    /**
     * 1：添加权限卡
     * 2：删除权限卡
     * 3：修改权限卡
     * 4：预设临时卡时间
     */
    private int operationType;

    public Integer getDoorId() {
        return doorId;
    }

    public void setDoorId(Integer doorId) {
        this.doorId = doorId;
    }

    public Integer getMappingDoorId() {
        return mappingDoorId;
    }

    public void setMappingDoorId(Integer mappingDoorId) {
        this.mappingDoorId = mappingDoorId;
    }

    public String getDoorCtrlIp() {
        return doorCtrlIp;
    }

    public void setDoorCtrlIp(String doorCtrlIp) {
        this.doorCtrlIp = doorCtrlIp;
    }

    public String getUserCardId() {
        return userCardId;
    }

    public void setUserCardId(String userCardId) {
        this.userCardId = userCardId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getOperationType() {
        return operationType;
    }

    public void setOperationType(int operationType) {
        this.operationType = operationType;
    }
}
