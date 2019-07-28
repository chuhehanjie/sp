package com.chris.smartpark.busi.dao;

import com.chris.smartpark.busi.entity.CarInfoEntity;
import com.chris.base.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 车辆信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 04.18
 */
@Mapper
public interface CarInfoDao extends BaseDao<CarInfoEntity> {

    void batchInsert(List<CarInfoEntity> list);
}
