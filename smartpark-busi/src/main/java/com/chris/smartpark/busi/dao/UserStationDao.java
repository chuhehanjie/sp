package com.chris.smartpark.busi.dao;

import com.chris.smartpark.busi.entity.UserStationEntity;
import com.chris.base.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 员工工位表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 30.18
 */
@Mapper
public interface UserStationDao extends BaseDao<UserStationEntity> {
	
}
