package com.chris.smartpark.busi.dto;

import com.chris.base.common.model.SysUpdateInfo;
import com.chris.smartpark.busi.entity.CarInfoEntity;
import com.chris.smartpark.busi.entity.CompanionsEntity;
import com.chris.smartpark.busi.entity.VisitorIdcardEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BatchReservationOrderDTO extends SysUpdateInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    //姓名
    @NotBlank(message = "姓名必填", groups = {ValidateSaveBatchReservation.class})
    private String name;
    //身份证号
    @NotBlank(message = "身份证必填", groups = {ValidateSaveBatchReservation.class})
    private String idcardNo;
    //手机号
    @NotBlank(message = "手机号必填", groups = {ValidateSaveBatchReservation.class})
    private String phone;
    //验证码
    //@NotBlank(message = "验证码必填", groups = {ValidateSaveBatchReservation.class})
    private String verifyCode;
    //受访者id
    @NotNull(message = "受访者id必填", groups = {ValidateSaveBatchReservation.class})
    private Long staffId;
    //受访者姓名
    @NotBlank(message = "受访者姓名必填", groups = {ValidateSaveBatchReservation.class})
    private String staffName;
    //受访者手机号
    @NotBlank(message = "受访者手机号必填", groups = {ValidateSaveBatchReservation.class})
    private String staffPhone;
    //预约开始时间
    @NotNull(message = "预约开始时间必填", groups = {ValidateSaveBatchReservation.class})
    private Date appointStartTime;
    //预约结束时间
    @NotNull(message = "预约结束时间必填", groups = {ValidateSaveBatchReservation.class})
    private Date appointEndTime;
    //同行人数
    private Integer companions;
    //备注(来访目的)
    private String remark;
    //是否添加车辆 1 是 0 否
    private int isAddCarInfo = 0;
    //访客身份信息表
    private VisitorIdcardEntity visitorIdcardEntity;
    //车辆信息
    //@Size(message = "车辆信息明细必填", groups = {ValidateSaveReservation.class}, min = 1)
    private List<CarInfoEntity> carInfoEntitys;
    //同行人员信息需要看项目配置是否添加
    private List<CompanionsEntity> companionsEntitys;

    private String openId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
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

    public Integer getCompanions() {
        return companions;
    }

    public void setCompanions(Integer companions) {
        this.companions = companions;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getIsAddCarInfo() {
        return isAddCarInfo;
    }

    public void setIsAddCarInfo(int isAddCarInfo) {
        this.isAddCarInfo = isAddCarInfo;
    }

    public VisitorIdcardEntity getVisitorIdcardEntity() {
        return visitorIdcardEntity;
    }

    public void setVisitorIdcardEntity(VisitorIdcardEntity visitorIdcardEntity) {
        this.visitorIdcardEntity = visitorIdcardEntity;
    }

    public List<CarInfoEntity> getCarInfoEntitys() {
        return carInfoEntitys;
    }

    public void setCarInfoEntitys(List<CarInfoEntity> carInfoEntitys) {
        this.carInfoEntitys = carInfoEntitys;
    }

    public List<CompanionsEntity> getCompanionsEntitys() {
        return companionsEntitys;
    }

    public void setCompanionsEntitys(List<CompanionsEntity> companionsEntitys) {
        this.companionsEntitys = companionsEntitys;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /* ********************** 参数校验类 *******************************/
    public interface ValidateSaveBatchReservation {

    }
}
