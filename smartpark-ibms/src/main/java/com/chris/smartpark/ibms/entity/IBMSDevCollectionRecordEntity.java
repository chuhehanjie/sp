package com.chris.smartpark.ibms.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 设备采集记录表
 *
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
public class IBMSDevCollectionRecordEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     */
    private Integer id;
    /**
     * 设备ID
     */
    private Integer deviceId;
    /**
     * 子系统ID
     */
    private Integer subsystemId;
    /**
     * 属性ID
     */
    private Integer attrId;
    /**
     * 采集值
     */
    private String value;
    /**
     * 采集日期，如：2018-10-12
     */
    private Date collectionDate;
    /**
     * 采集时间
     */
    private Date createTime;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setSubsystemId(Integer subsystemId) {
        this.subsystemId = subsystemId;
    }

    public Integer getSubsystemId() {
        return subsystemId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
