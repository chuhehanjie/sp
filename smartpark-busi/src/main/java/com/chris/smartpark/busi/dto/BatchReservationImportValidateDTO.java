package com.chris.smartpark.busi.dto;

import cn.afterturn.easypoi.handler.inter.IExcelModel;

/**
 * 批量预约单导入校验DTO
 */
public class BatchReservationImportValidateDTO extends BatchReservationImportDTO implements IExcelModel {
    private String errorMsg;
    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }

    @Override
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
