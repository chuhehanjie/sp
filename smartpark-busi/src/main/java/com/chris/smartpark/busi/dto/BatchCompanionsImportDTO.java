package com.chris.smartpark.busi.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 批量同行人信息导入DTO
 */
public class BatchCompanionsImportDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Excel(name = "批次号")
    @NotNull(message = "批次号不能为空", groups = {CompanionsImportValidGroup.class})
    private String batchNo;

    @Excel(name = "同行人姓名")
    @NotNull(message = "同行人姓名不能为空", groups = {CompanionsImportValidGroup.class})
    private String name;

    @Excel(name = "同行人身份证号")
    private String idcardNo;

    @Excel(name = "同行人手机号")
    private String phone;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

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

    /**
     * 参数校验
     */
    public interface CompanionsImportValidGroup {

    }
}
