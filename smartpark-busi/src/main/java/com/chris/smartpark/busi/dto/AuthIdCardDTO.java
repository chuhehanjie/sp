package com.chris.smartpark.busi.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by lisen on 2018/12/4.
 */
public class AuthIdCardDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    //身份证号
    @NotBlank(message = "身份证号必填", groups = {AuthIdCardDTO.ValidateIdCard.class,AuthIdCardDTO.ValidateSaveVisitorIdCard.class})
    private String cardNO;
    //身份证ID
    @NotBlank(message = "身份证ID必填", groups = {AuthIdCardDTO.ValidateIdCard.class,AuthIdCardDTO.ValidateSaveVisitorIdCard.class})
    private String cardID;
    //来访人姓名
    @NotBlank(message = "来访人姓名必填", groups = {AuthIdCardDTO.ValidateIdCard.class,AuthIdCardDTO.ValidateSaveVisitorIdCard.class})
    private String cardName;
    //现场访问在判定中生成的主控平台访问记录主键
    @NotBlank(message = "主控平台访问记录主键必填", groups = {AuthIdCardDTO.ValidateSaveVisitorIdCard.class})
    private String id ;
    //来访人照片（base64）字符串
    @NotBlank(message = "来访人照片必填", groups = {AuthIdCardDTO.ValidateSaveVisitorIdCard.class})
    private String cardPht;
    //被访人电话
    private String telNum;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCardNO() {
        return cardNO;
    }

    public void setCardNO(String cardNO) {
        this.cardNO = cardNO;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardPht() {
        return cardPht;
    }

    public void setCardPht(String cardPht) {
        this.cardPht = cardPht;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /* ********************** 参数校验类 *******************************/
    public interface ValidateIdCard{

    }
    public interface ValidateSaveVisitorIdCard{

    }
}
