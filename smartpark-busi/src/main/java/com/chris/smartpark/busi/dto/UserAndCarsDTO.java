package com.chris.smartpark.busi.dto;

import com.chris.smartpark.busi.entity.MyCarEntity;

import java.io.Serializable;
import java.util.List;

public class UserAndCarsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName;

    private String mobile;

    private String idcardNo;

    private List<MyCarEntity> myCars;

    public UserAndCarsDTO() {
    }

    public UserAndCarsDTO(String userName, String mobile, String idcardNo) {
        this.userName = userName;
        this.mobile = mobile;
        this.idcardNo = idcardNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo;
    }

    public List<MyCarEntity> getMyCars() {
        return myCars;
    }

    public void setMyCars(List<MyCarEntity> myCars) {
        this.myCars = myCars;
    }
}
