package com.chris.smartpark.ibms.dao;

import com.chris.smartpark.ibms.entity.IBMSDevAlarmRecordEntity;
import com.chris.base.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 设备报警记录表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
@Mapper
@Component
public interface IBMSDevAlarmRecordDao extends BaseDao<IBMSDevAlarmRecordEntity> {

    Integer queryStateBySubsystem(Integer subsystemId);
	
}
