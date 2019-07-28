package com.chris.smartpark.busi.facade;

import com.chris.base.common.utils.CommonResponse;
import com.chris.smartpark.base.dto.EsbResponse;
import com.chris.smartpark.busi.dto.CosonDoorCtrlReqDTO;
import com.chris.smartpark.busi.entity.DoorCtrlAuthEntity;
import com.chris.smartpark.busi.entity.DoorControllerEntity;

import java.util.List;

public interface EsbFacade {
    /**
     * 门禁预约
     * @return
     */
    EsbResponse remoteOpenDoor(DoorControllerEntity param);

    /**
     * 门禁授权并预约
     * @param doorAuthList
     * @return
     */
    EsbResponse doorCtrlAuthAndReserve(List<DoorCtrlAuthEntity> doorAuthList);

    EsbResponse doorCtrlAuthAndReserve4Coson(List<CosonDoorCtrlReqDTO> reqList);
}
