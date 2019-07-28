package com.chris.smartpark.busi.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 预约单查询DTO
 */
public class ReservationOrderQryDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 预约单id
     */
    private Long id;
    /**
     * 访客姓名
     */
    private String visitorName;
    /**
     * 访客手机号
     */
    private String visitorMobile;
    /**
     * 访客身份证
     */
    private String idcardNo;
    /**
     * 状态
     */
    private String status;
    /**
     * 预约开始时间
     */
    private Date appointStartTime;
    /**
     * 预约结束时间
     */
    private Date appointEndTime;

    private Date createTime;
    /**
     * 备注(来访目的)
     */
    private String remark;

    private String reservationNo;

    /**
     * 被访人姓名
     */
    private String staffName;

    /**
     * 被访人手机号
     */
    private String staffMobile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorMobile() {
        return visitorMobile;
    }

    public void setVisitorMobile(String visitorMobile) {
        this.visitorMobile = visitorMobile;
    }

    public String getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAppointStartTime() {
        return appointStartTime;
    }

    public void setAppointStartTime(Date appointStartTime) {
        this.appointStartTime = appointStartTime;
    }

    public Date getAppointEndTime() {
        return appointEndTime;
    }

    public void setAppointEndTime(Date appointEndTime) {
        this.appointEndTime = appointEndTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getReservationNo() {
        return reservationNo;
    }

    public void setReservationNo(String reservationNo) {
        this.reservationNo = reservationNo;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffMobile() {
        return staffMobile;
    }

    public void setStaffMobile(String staffMobile) {
        this.staffMobile = staffMobile;
    }
}
