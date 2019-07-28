package com.chris.smartpark.busi.service;

import com.chris.smartpark.busi.dto.OpenDoorLogDTO;
import com.chris.smartpark.busi.entity.OpenDoorLogEntity;

import java.util.List;
import java.util.Map;

public interface OpenDoorLogService {

    void save(OpenDoorLogEntity openDoorLogEntity);

    List<OpenDoorLogDTO> queryListByKeyword(Map<String, Object> params);

    int queryTotalByKeyword(Map<String, Object> params);
}
