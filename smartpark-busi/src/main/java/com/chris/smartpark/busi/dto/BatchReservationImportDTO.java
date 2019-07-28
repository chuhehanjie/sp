package com.chris.smartpark.busi.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 批量预约单导入DTO
 */
public class BatchReservationImportDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Excel(name = "访客姓名")
    @NotNull(message = "访客姓名不能为空", groups = {ReservationImportValidGroup.class})
    private String name;

    @Excel(name = "批次号")
    @NotNull(message = "批次号不能为空", groups = {ReservationImportValidGroup.class})
    private String batchNo;

    @Excel(name = "身份证号")
    private String idcardNo;

    @Excel(name = "手机号")
    @Min(value = 11, message = "手机号只能输入11位")
    @Max(value = 11, message = "手机号只能输入11位")
    private String phone;

    @Excel(name = "来访开始时间", format = "yyyy-MM-dd HH:mm")
    private Date appointStartTime;

    @Excel(name = "来访结束时间", format = "yyyy-MM-dd HH:mm")
    private Date appointEndTime;

    @Excel(name = "来访事由")
    private String remark;

    @Excel(name = "受访者id")
    @NotNull(message = "受访者id不能为空", groups = {ReservationImportValidGroup.class})
    private Long staffId;
    //受访者姓名
    @Excel(name = "受访者姓名")
    @NotNull(message = "受访者姓名不能为空", groups = {ReservationImportValidGroup.class})
    private String staffName;
    //受访者手机号
    @Excel(name = "受访者手机号")
    @NotNull(message = "受访者手机号不能为空", groups = {ReservationImportValidGroup.class})
    private String staffPhone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Long getStaffId() {
        return staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }

    /**
     * 参数校验
     */
    public interface ReservationImportValidGroup {

    }
}
