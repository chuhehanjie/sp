package com.chris.smartpark.busi.dao;

import com.chris.base.modules.sys.dao.BaseDao;
import com.chris.smartpark.busi.dto.DoorControllerDTO;
import com.chris.smartpark.busi.dto.DoorLevelDTO;
import com.chris.smartpark.busi.entity.DoorEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 门定义
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 30.18
 */
@Mapper
public interface DoorDao extends BaseDao<DoorEntity> {
    List<DoorEntity> queryUserDoor(Map<String,Object> params);

    /**
     * 根据openid查询有权限门列表
     * @param openId
     * @return
     */
    List<DoorLevelDTO> queryHasPermissionDoorsByOpenId(String openId);

    List<DoorControllerDTO> queryDoorControllersByOpenId(Map<String,Object> params);

    List<Map<String, Object>> searchDoorCtrlList(Map<String,Object> params);
}
