package com.chris.smartpark.busi.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lisen on 2018/12/2.
 */
public class DoorCtrlAuthEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private int cardID;
    private int doorID;
    private String password;
    private Date dueDate;
    private int authorType;
    private int authorStatus;
    private int userTimeGrp;
    private int downLoaded;
    private int firstDownLoaded;
    private int preventCard;
    private Date startTime;

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public int getDoorID() {
        return doorID;
    }

    public void setDoorID(int doorID) {
        this.doorID = doorID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getAuthorType() {
        return authorType;
    }

    public void setAuthorType(int authorType) {
        this.authorType = authorType;
    }

    public int getAuthorStatus() {
        return authorStatus;
    }

    public void setAuthorStatus(int authorStatus) {
        this.authorStatus = authorStatus;
    }

    public int getUserTimeGrp() {
        return userTimeGrp;
    }

    public void setUserTimeGrp(int userTimeGrp) {
        this.userTimeGrp = userTimeGrp;
    }

    public int getDownLoaded() {
        return downLoaded;
    }

    public void setDownLoaded(int downLoaded) {
        this.downLoaded = downLoaded;
    }

    public int getFirstDownLoaded() {
        return firstDownLoaded;
    }

    public void setFirstDownLoaded(int firstDownLoaded) {
        this.firstDownLoaded = firstDownLoaded;
    }

    public int getPreventCard() {
        return preventCard;
    }

    public void setPreventCard(int preventCard) {
        this.preventCard = preventCard;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
