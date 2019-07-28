package com.chris.smartpark.busi.dao;

import com.chris.base.modules.sys.dao.BaseDao;
import com.chris.smartpark.busi.dto.OpenDoorLogDTO;
import com.chris.smartpark.busi.entity.OpenDoorLogEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 开门日志表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 30.18
 */
@Mapper
public interface OpenDoorLogDao extends BaseDao<OpenDoorLogEntity> {

    List<OpenDoorLogDTO> queryListByKeyword(Map<String, Object> params);

    int queryTotalByKeyword(Map<String, Object> params);
}
