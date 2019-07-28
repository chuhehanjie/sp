package com.chris.smartpark.ibms.entity;

import java.util.List;

/**
 * @author hewei
 * @version V.1.0.0
 * @date Created in 19:52 2018/10/30
 */
public class WaterTemperatureEntity {

    private List<WaterTemperaturePeriodEntity> supplyWater;

    private List<WaterTemperaturePeriodEntity> backWater;

    public List<WaterTemperaturePeriodEntity> getSupplyWater() {
        return supplyWater;
    }

    public void setSupplyWater(List<WaterTemperaturePeriodEntity> supplyWater) {
        this.supplyWater = supplyWater;
    }

    public List<WaterTemperaturePeriodEntity> getBackWater() {
        return backWater;
    }

    public void setBackWater(List<WaterTemperaturePeriodEntity> backWater) {
        this.backWater = backWater;
    }
}
