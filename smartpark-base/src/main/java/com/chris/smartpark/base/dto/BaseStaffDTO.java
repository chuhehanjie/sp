package com.chris.smartpark.base.dto;

import java.io.Serializable;

public class BaseStaffDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    //工号
    private String employnum;
    //姓名
    private String username;
    //手机号
    private String mobile;
    //身份证
    private String idcardnumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmploynum() {
        return employnum;
    }

    public void setEmploynum(String employnum) {
        this.employnum = employnum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdcardnumber() {
        return idcardnumber;
    }

    public void setIdcardnumber(String idcardnumber) {
        this.idcardnumber = idcardnumber;
    }

    public BaseStaffDTO() {
    }

    public BaseStaffDTO(Long id, String username, String mobile) {
        this.id = id;
        this.username = username;
        this.mobile = mobile;
    }
}
