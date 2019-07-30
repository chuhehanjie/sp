package com.chris.smartpark.busi.dto;

import java.io.Serializable;

public class WXFormIdMarker implements Serializable {
    private static final long serialVersionUID = 1L;

    private String formId;
    private boolean used;

    public WXFormIdMarker(String formId, boolean used) {
        this.formId = formId;
        this.used = used;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
