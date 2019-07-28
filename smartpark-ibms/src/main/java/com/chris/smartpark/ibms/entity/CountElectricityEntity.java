package com.chris.smartpark.ibms.entity;

/**
 * @author hewei
 * @version V.1.0.0
 * @date Created in 23:30 2018/10/22
 */
public class CountElectricityEntity {

    private Double yesterday;

    private Double lastMonth;

    private Double currentMonth;

    public Double getYesterday() {
        return yesterday;
    }

    public void setYesterday(Double yesterday) {
        this.yesterday = yesterday;
    }

    public Double getLastMonth() {
        return lastMonth;
    }

    public void setLastMonth(Double lastMonth) {
        this.lastMonth = lastMonth;
    }

    public Double getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(Double currentMonth) {
        this.currentMonth = currentMonth;
    }
}
