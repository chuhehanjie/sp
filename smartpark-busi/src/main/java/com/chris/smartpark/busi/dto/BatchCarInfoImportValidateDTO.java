package com.chris.smartpark.busi.dto;

import cn.afterturn.easypoi.handler.inter.IExcelModel;

public class BatchCarInfoImportValidateDTO extends BatchCarInfoImportDTO implements IExcelModel {
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
