package com.chris.smartpark.busi.dto;

import java.io.Serializable;


public class DoorLevelDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long doorId;

    private String doorName;

    private Long floorId;

    private String floorName;

    private Long buildingId;

    private String buildingName;

    /**
     * 默认授权标识
     * 1、默认授权
     * 0、默认不授权
     */
    private int defaultAuthorizedFlag;

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

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getDefaultAuthorizedFlag() {
        return defaultAuthorizedFlag;
    }

    public void setDefaultAuthorizedFlag(int defaultAuthorizedFlag) {
        this.defaultAuthorizedFlag = defaultAuthorizedFlag;
    }
}
