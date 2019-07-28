package com.chris.smartpark.busi.dto;

import java.io.Serializable;

public class DoorControllerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int doorId;

    private int doorControllerId;

    private String doorName;

    private int readerNo;

    private int doorType;

    private int mappingDoorId;

    public int getDoorId() {
        return doorId;
    }

    public void setDoorId(int doorId) {
        this.doorId = doorId;
    }

    public int getDoorControllerId() {
        return doorControllerId;
    }

    public void setDoorControllerId(int doorControllerId) {
        this.doorControllerId = doorControllerId;
    }

    public String getDoorName() {
        return doorName;
    }

    public void setDoorName(String doorName) {
        this.doorName = doorName;
    }

    public int getReaderNo() {
        return readerNo;
    }

    public void setReaderNo(int readerNo) {
        this.readerNo = readerNo;
    }

    public int getDoorType() {
        return doorType;
    }

    public void setDoorType(int doorType) {
        this.doorType = doorType;
    }

    public int getMappingDoorId() {
        return mappingDoorId;
    }

    public void setMappingDoorId(int mappingDoorId) {
        this.mappingDoorId = mappingDoorId;
    }
}
