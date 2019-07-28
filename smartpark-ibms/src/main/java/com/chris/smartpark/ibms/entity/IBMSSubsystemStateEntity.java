package com.chris.smartpark.ibms.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author hewei
 * @version V.1.0.0
 * @date Created in 11:04 2018/10/22
 */
public class IBMSSubsystemStateEntity {

    @ApiModelProperty("子系统ID")
    private Integer id;
    @ApiModelProperty("子系统名称")
    private String name;
    @ApiModelProperty("是否连接")
    private Integer connectStatus;
    @ApiModelProperty("是否告警")
    private Integer alarm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getConnectStatus() {
        return connectStatus;
    }

    public void setConnectStatus(Integer connectStatus) {
        this.connectStatus = connectStatus;
    }

    public Integer getAlarm() {
        return alarm;
    }

    public void setAlarm(Integer alarm) {
        this.alarm = alarm;
    }
}
