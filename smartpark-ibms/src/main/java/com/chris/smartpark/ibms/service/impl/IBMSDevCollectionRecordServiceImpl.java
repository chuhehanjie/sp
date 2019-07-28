package com.chris.smartpark.ibms.service.impl;

import com.chris.smartpark.common.Attribute;
import com.chris.smartpark.ibms.dao.IBMSDevCollectionRecordDao;
import com.chris.smartpark.ibms.entity.EnvInfoEntity;
import com.chris.smartpark.ibms.entity.IBMSDevCollectionRecordEntity;
import com.chris.smartpark.ibms.entity.ElectricityEntity;
import com.chris.smartpark.ibms.entity.WaterTemperaturePeriodEntity;
import com.chris.smartpark.ibms.service.IBMSDevCollectionRecordService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("ibmsDevCollectionRecordService")
public class IBMSDevCollectionRecordServiceImpl implements IBMSDevCollectionRecordService {
    @Autowired
    private IBMSDevCollectionRecordDao ibmsDevCollectionRecordDao;

    @Override
    public IBMSDevCollectionRecordEntity queryObject(Integer id) {
        return ibmsDevCollectionRecordDao.queryObject(id);
    }

    @Override
    public List<IBMSDevCollectionRecordEntity> queryList(Map<String, Object> map) {
        return ibmsDevCollectionRecordDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return ibmsDevCollectionRecordDao.queryTotal(map);
    }

    @Override
    public void save(IBMSDevCollectionRecordEntity ibmsDevCollectionRecord) {
        ibmsDevCollectionRecordDao.save(ibmsDevCollectionRecord);
    }

    @Override
    public void update(IBMSDevCollectionRecordEntity ibmsDevCollectionRecord) {
        ibmsDevCollectionRecordDao.update(ibmsDevCollectionRecord);
    }

    @Override
    public void delete(Integer id) {
        ibmsDevCollectionRecordDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        ibmsDevCollectionRecordDao.deleteBatch(ids);
    }

    @Override
    public List<ElectricityEntity> queryElectricity(Map<String, Object> map) {
        map.put("electric", Attribute.ELECTRIC.getAttrId());
        return ibmsDevCollectionRecordDao.queryElectricity(map);
    }

    @Override
    public Double countElectricity(Map<String, Object> map) {
        map.put("electric", Attribute.ELECTRIC.getAttrId());
        return ibmsDevCollectionRecordDao.countElectricity(map);
    }

    @Override
    public List<EnvInfoEntity> queryEnvInfo() {
        Map<String,Object> param = Maps.newHashMap();
        param.put("temperature", Attribute.TEMPERATURE.getAttrId());
        param.put("humidity", Attribute.HUMIDITY.getAttrId());
        param.put("ppm", Attribute.PPM.getAttrId());
        return ibmsDevCollectionRecordDao.queryEnvInfo(param);
    }

    @Override
    public List<WaterTemperaturePeriodEntity> queryWaterTemperature(Map<String, Object> map) {
        return ibmsDevCollectionRecordDao.queryWaterTemperature(map);
    }

}
