package com.chris.smartpark.busi.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 车辆授权DTO
 */
public class CarAuthorizationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String openId;
    /**
     * 身份证号
     */
    private String idcardNo;

    /**
     * 车牌号
     */
    private String carNo;

    /**
     * 进入时间
     */
    private Date enterTime;

    /**
     * 离开时间
     */
    private Date leaveTime;

    public String getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
