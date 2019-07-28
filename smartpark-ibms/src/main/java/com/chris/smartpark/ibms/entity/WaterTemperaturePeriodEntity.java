package com.chris.smartpark.ibms.entity;

/**
 * @author hewei
 * @version V.1.0.0
 * @date Created in 19:56 2018/10/30
 */
public class WaterTemperaturePeriodEntity {

    private String time;

    private String waterTemperature;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(String waterTemperature) {
        this.waterTemperature = waterTemperature;
    }
}
