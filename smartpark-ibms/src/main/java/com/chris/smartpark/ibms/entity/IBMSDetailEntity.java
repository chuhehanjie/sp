package com.chris.smartpark.ibms.entity;

import java.util.List;

/**
 * @author hewei
 * @version V.1.0.0
 * @date Created in 19:04 2018/10/30
 */
public class IBMSDetailEntity {

    private CountElectricityEntity totalElectric;

    private List<ElectricityEntity> electricList;

    private List<EnvInfoEntity> envInfoList;

    private List<IBMSSubsystemStateEntity> subSystemStateList;

    private WaterTemperatureEntity waterTemperature;

    public CountElectricityEntity getTotalElectric() {
        return totalElectric;
    }

    public void setTotalElectric(CountElectricityEntity totalElectric) {
        this.totalElectric = totalElectric;
    }

    public List<ElectricityEntity> getElectricList() {
        return electricList;
    }

    public void setElectricList(List<ElectricityEntity> electricList) {
        this.electricList = electricList;
    }

    public List<EnvInfoEntity> getEnvInfoList() {
        return envInfoList;
    }

    public void setEnvInfoList(List<EnvInfoEntity> envInfoList) {
        this.envInfoList = envInfoList;
    }

    public List<IBMSSubsystemStateEntity> getSubSystemStateList() {
        return subSystemStateList;
    }

    public void setSubSystemStateList(List<IBMSSubsystemStateEntity> subSystemStateList) {
        this.subSystemStateList = subSystemStateList;
    }

    public WaterTemperatureEntity getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(WaterTemperatureEntity waterTemperature) {
        this.waterTemperature = waterTemperature;
    }
}
