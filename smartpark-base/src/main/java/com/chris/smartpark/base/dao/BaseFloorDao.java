package com.chris.smartpark.base.dao;

import com.chris.smartpark.base.entity.BaseFloorEntity;
import com.chris.base.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 园区楼层信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
@Mapper
public interface BaseFloorDao extends BaseDao<BaseFloorEntity> {
	
}
