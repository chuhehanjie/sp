package com.chris.smartpark.base.dto;

import org.apache.http.HttpStatus;

import java.io.Serializable;

public class EsbResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;

    private String msg;

    public boolean isOK() {
        return HttpStatus.SC_OK == this.code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public EsbResponse() {
    }

    public EsbResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static EsbResponse error(String msg) {
        return new EsbResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }
}
