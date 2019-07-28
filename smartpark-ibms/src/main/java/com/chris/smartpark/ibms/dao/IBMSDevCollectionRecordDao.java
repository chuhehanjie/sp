package com.chris.smartpark.ibms.dao;

import com.chris.base.modules.sys.dao.BaseDao;
import com.chris.smartpark.ibms.entity.ElectricityEntity;
import com.chris.smartpark.ibms.entity.EnvInfoEntity;
import com.chris.smartpark.ibms.entity.IBMSDevCollectionRecordEntity;
import com.chris.smartpark.ibms.entity.WaterTemperaturePeriodEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 设备采集记录表
 *
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
@Mapper
@Component
public interface IBMSDevCollectionRecordDao extends BaseDao<IBMSDevCollectionRecordEntity> {

    List<ElectricityEntity> queryElectricity(Map<String, Object> map);

    Double countElectricity(Map<String, Object> map);

    List<EnvInfoEntity> queryEnvInfo(Map<String, Object> map);

    List<WaterTemperaturePeriodEntity> queryWaterTemperature(Map<String, Object> map);

}
