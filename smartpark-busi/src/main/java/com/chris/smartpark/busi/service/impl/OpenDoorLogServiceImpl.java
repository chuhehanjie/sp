package com.chris.smartpark.busi.service.impl;

import com.chris.smartpark.busi.dao.OpenDoorLogDao;
import com.chris.smartpark.busi.dto.OpenDoorLogDTO;
import com.chris.smartpark.busi.entity.OpenDoorLogEntity;
import com.chris.smartpark.busi.service.OpenDoorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OpenDoorLogServiceImpl implements OpenDoorLogService {

    @Autowired
    private OpenDoorLogDao openDoorLogDao;

    @Override
    public void save(OpenDoorLogEntity openDoorLogEntity) {
        this.openDoorLogDao.save(openDoorLogEntity);
    }

    @Override
    public List<OpenDoorLogDTO> queryListByKeyword(Map<String, Object> params) {
        return this.openDoorLogDao.queryListByKeyword(params);
    }

    @Override
    public int queryTotalByKeyword(Map<String, Object> params) {
        return this.openDoorLogDao.queryTotalByKeyword(params);
    }
}
