package com.chris.smartpark.ibms.entity;

/**
 * @author hewei
 * @version V.1.0.0
 * @date Created in 0:45 2018/10/23
 */
public class EnvInfoEntity {

    private Integer id;

    private String floorName;

    private Double temperature;

    private Double humidity;

    private Double ppm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getPpm() {
        return ppm;
    }

    public void setPpm(Double ppm) {
        this.ppm = ppm;
    }
}
