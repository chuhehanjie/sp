package com.chris.smartpark.ibms.dao;

import com.chris.smartpark.ibms.entity.IBMSSubsystemEntity;
import com.chris.base.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * ibms子系统表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 06.18
 */
@Mapper
@Component
public interface IBMSSubsystemDao extends BaseDao<IBMSSubsystemEntity> {
	
}
