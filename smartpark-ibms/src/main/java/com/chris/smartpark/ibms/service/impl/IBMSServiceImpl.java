package com.chris.smartpark.ibms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chris.smartpark.common.Attribute;
import com.chris.smartpark.ibms.entity.CountElectricityEntity;
import com.chris.smartpark.ibms.entity.ElectricityEntity;
import com.chris.smartpark.ibms.entity.IBMSDetailEntity;
import com.chris.smartpark.ibms.entity.WaterTemperatureEntity;
import com.chris.smartpark.ibms.service.IBMSDevCollectionRecordService;
import com.chris.smartpark.ibms.service.IBMSService;
import com.chris.smartpark.ibms.service.IBMSSubsystemService;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @author hewei
 * @version V.1.0.0
 * @date Created in 15:44 2018/10/30
 */
@Service
public class IBMSServiceImpl implements IBMSService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IBMSServiceImpl.class);

    @Autowired
    private IBMSDevCollectionRecordService ibmsDevCollectionRecordService;
    @Autowired
    private IBMSSubsystemService ibmsSubsystemService;

    @Override
    public IBMSDetailEntity detail() {
        IBMSDetailEntity ibmsDetailEntity = new IBMSDetailEntity();
        ibmsDetailEntity.setTotalElectric(countElectricityEntity());
        ibmsDetailEntity.setElectricList(queryElectricityInMonth());
        ibmsDetailEntity.setEnvInfoList(ibmsDevCollectionRecordService.queryEnvInfo());
        ibmsDetailEntity.setSubSystemStateList(ibmsSubsystemService.queryListState());
        ibmsDetailEntity.setWaterTemperature(queryWaterTemperaturePeriod());
        return ibmsDetailEntity;
    }

    private CountElectricityEntity countElectricityEntity() {
        CountElectricityEntity countElectricityEntity = new CountElectricityEntity();
        Map<String, Object> currentMonth = Maps.newHashMap();
        currentMonth.put("type", 2);
        Map<String, Object> yesterday = Maps.newHashMap();
        yesterday.put("type", 1);
        Map<String, Object> lastMonth = Maps.newHashMap();
        lastMonth.put("type", 2);
        Calendar cal = Calendar.getInstance();
        currentMonth.put("date", new SimpleDateFormat("yyyy-MM").format(cal.getTime()));
        cal.add(Calendar.DATE, -1);
        yesterday.put("date", new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
        cal.add(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, -1);
        lastMonth.put("date", new SimpleDateFormat("yyyy-MM").format(cal.getTime()));
        countElectricityEntity.setYesterday(ibmsDevCollectionRecordService.countElectricity(yesterday));
        countElectricityEntity.setCurrentMonth(ibmsDevCollectionRecordService.countElectricity(currentMonth));
        countElectricityEntity.setLastMonth(ibmsDevCollectionRecordService.countElectricity(lastMonth));
        LOGGER.info("countElectricityEntity:{}", JSONObject.toJSONString(countElectricityEntity));
        return countElectricityEntity;
    }

    private List<ElectricityEntity> queryElectricityInMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        String startDate = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(ca.getTime());
        Map<String, Object> params = Maps.newHashMap();
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        List<ElectricityEntity> electricityEntities = ibmsDevCollectionRecordService.queryElectricity(params);
        LOGGER.info("queryElectricityInMonth:{}", JSONObject.toJSONString(electricityEntities));
        return electricityEntities;
    }

    private WaterTemperatureEntity queryWaterTemperaturePeriod() {
        WaterTemperatureEntity waterTemperatureEntity = new WaterTemperatureEntity();
        Map<String, Object> supply = Maps.newHashMap();
        supply.put("attrId", Attribute.SUPPLY_WATER_TEMPERATURE.getAttrId());
        waterTemperatureEntity.setSupplyWater(ibmsDevCollectionRecordService.queryWaterTemperature(supply));
        Map<String, Object> back = Maps.newHashMap();
        back.put("attrId", Attribute.SUPPLY_WATER_TEMPERATURE.getAttrId());
        waterTemperatureEntity.setBackWater(ibmsDevCollectionRecordService.queryWaterTemperature(back));
        return waterTemperatureEntity;
    }
}
