package com.chris.smartpark.busi.dao;

import com.chris.smartpark.busi.entity.DoorControllerEntity;
import com.chris.base.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
/**
 * 门控制信息
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 30.18
 */
@Mapper
public interface DoorControllerDao extends BaseDao<DoorControllerEntity> {
	List<DoorControllerEntity> queryDoorControllerByDoorId(Map<String,Object> params);
}
