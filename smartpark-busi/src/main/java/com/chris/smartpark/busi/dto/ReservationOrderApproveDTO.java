package com.chris.smartpark.busi.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ReservationOrderApproveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    //预约单号
    private Long reservationId;
    //授权门禁列表
    private List<Long> doorList ;
    //授权结果 1通过 0不通过
    private int approveReslut ;
    //拒绝原因
    private String rejectReaon ;

    private String openId;
    //预约授权开始时间
    private Date actStartTime;
    //预约授权结束时间
    private Date actEndTime;

    private String formId;

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public List<Long> getDoorList() {
        return doorList;
    }

    public void setDoorList(List<Long> doorList) {
        this.doorList = doorList;
    }

    public int getApproveReslut() {
        return approveReslut;
    }

    public void setApproveReslut(int approveReslut) {
        this.approveReslut = approveReslut;
    }

    public String getRejectReaon() {
        return rejectReaon;
    }

    public void setRejectReaon(String rejectReaon) {
        this.rejectReaon = rejectReaon;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getActStartTime() {
        return actStartTime;
    }

    public void setActStartTime(Date actStartTime) {
        this.actStartTime = actStartTime;
    }

    public Date getActEndTime() {
        return actEndTime;
    }

    public void setActEndTime(Date actEndTime) {
        this.actEndTime = actEndTime;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }
}
