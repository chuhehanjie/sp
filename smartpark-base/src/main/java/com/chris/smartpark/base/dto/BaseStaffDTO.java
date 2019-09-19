package com.chris.smartpark.base.dto;

import com.alibaba.fastjson.JSONObject;

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

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

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

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public BaseStaffDTO(Long id, String username, String mobile) {
        this.id = id;
        this.username = username;
        this.mobile = mobile;
    }

    public static void main(String[] args) {
        BaseStaffDTO s = new BaseStaffDTO();
        s.setId(123L);
        s.setEmploynum("EMP10086");
        s.setUsername("张三");
        s.setMobile("18877889900");
        s.setIdcardnumber("2348327239742398");
        System.out.println(JSONObject.toJSONString(s));
    }
}
