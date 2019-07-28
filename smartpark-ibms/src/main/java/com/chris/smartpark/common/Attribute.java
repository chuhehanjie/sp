package com.chris.smartpark.common;

/**
 * @author hewei
 * @version V.1.0.0
 * @date Created in 10:51 2018/10/31
 */
public enum Attribute {

    /**
     * 温度
     */
    TEMPERATURE(1),
    /**
     * 湿度
     */
    HUMIDITY(2),
    /**
     * 二氧化碳浓度
     */
    PPM(3),
    /**
     * 用电量
     */
    ELECTRIC(4),
    /**
     * 供水温度
     */
    SUPPLY_WATER_TEMPERATURE(5),
    /**
     * 回水温度
     */
    BACK_WATER_TEMPERATURE(6);

    private Integer attrId;

    Attribute(Integer attrId){
        this.attrId = attrId;
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }
}
