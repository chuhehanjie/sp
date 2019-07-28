package com.chris.smartpark.ibms.service;

import com.chris.smartpark.ibms.entity.ElectricityEntity;
import com.chris.smartpark.ibms.entity.EnvInfoEntity;
import com.chris.smartpark.ibms.entity.IBMSDevCollectionRecordEntity;
import com.chris.smartpark.ibms.entity.WaterTemperaturePeriodEntity;

import java.util.List;
import java.util.Map;

/**
 * 设备采集记录表
 *
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
public interface IBMSDevCollectionRecordService {

    IBMSDevCollectionRecordEntity queryObject(Integer id);

    List<IBMSDevCollectionRecordEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(IBMSDevCollectionRecordEntity ibmsDevCollectionRecord);

    void update(IBMSDevCollectionRecordEntity ibmsDevCollectionRecord);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    List<ElectricityEntity> queryElectricity(Map<String, Object> map);

    Double countElectricity(Map<String, Object> map);

    List<EnvInfoEntity> queryEnvInfo();

    List<WaterTemperaturePeriodEntity> queryWaterTemperature(Map<String, Object> map);
}
