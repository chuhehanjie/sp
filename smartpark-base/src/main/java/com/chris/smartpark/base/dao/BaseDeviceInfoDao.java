package com.chris.smartpark.base.dao;

import com.chris.smartpark.base.entity.BaseDeviceInfoEntity;
import com.chris.base.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备基本信息
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 22.18
 */
@Mapper
public interface BaseDeviceInfoDao extends BaseDao<BaseDeviceInfoEntity> {
	
}
