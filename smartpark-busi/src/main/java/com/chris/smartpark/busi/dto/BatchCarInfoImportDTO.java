package com.chris.smartpark.busi.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 批量车辆信息导入DTO
 */
public class BatchCarInfoImportDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Excel(name = "批次号")
    @NotNull(message = "批次号不能为空", groups = {CarInfoImportValidGroup.class})
    private String batchNo;

    @Excel(name = "车牌号")
    @NotNull(message = "车牌号不能为空", groups = {CarInfoImportValidGroup.class})
    private String carNo;

    @Excel(name = "驾驶员姓名")
    private String name;

    @Excel(name = "驾驶员手机号")
    @Min(value = 11, message = "驾驶员手机号只能输入11位")
    @Max(value = 11, message = "驾驶员手机号只能输入11位")
    private String phone;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 参数校验
     */
    public interface CarInfoImportValidGroup {

    }
}
