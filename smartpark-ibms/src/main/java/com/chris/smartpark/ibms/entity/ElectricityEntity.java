package com.chris.smartpark.ibms.entity;

/**
 * @author hewei
 * @version V.1.0.0
 * @date Created in 20:18 2018/10/22
 */
public class ElectricityEntity {

    private String date;

    private Double electricity;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getElectricity() {
        return electricity;
    }

    public void setElectricity(Double electricity) {
        this.electricity = electricity;
    }
}
