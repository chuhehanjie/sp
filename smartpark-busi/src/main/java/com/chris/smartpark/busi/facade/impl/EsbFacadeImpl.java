package com.chris.smartpark.busi.facade.impl;

import com.alibaba.fastjson.JSONObject;
import com.chris.base.common.utils.CacheDataUtils;
import com.chris.base.common.utils.RestTemplateUtils;
import com.chris.smartpark.base.dto.EsbResponse;
import com.chris.smartpark.busi.common.VisitorConstants;
import com.chris.smartpark.busi.dto.CosonDoorCtrlReqDTO;
import com.chris.smartpark.busi.entity.DoorControllerEntity;
import com.chris.smartpark.busi.entity.DoorCtrlAuthEntity;
import com.chris.smartpark.busi.facade.EsbFacade;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EsbFacadeImpl implements EsbFacade {
    @Autowired
    private RestTemplateUtils restTemplateUtils;

    @Autowired
    private CacheDataUtils cacheDataUtils;

    @Override
    public EsbResponse remoteOpenDoor(DoorControllerEntity param) {
        String url = this.cacheDataUtils.getConfigValueByKey(VisitorConstants.Keys.ESB_SERVER) + "remoteOpenDoor";
        try {
            String resp = this.restTemplateUtils.httpPostMediaTypeJson(url, String.class, this.convertParam(param));
            return JSONObject.parseObject(resp, EsbResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("远程开门异常！原因：" + e.getMessage());
            return EsbResponse.error("远程开门异常！");
        }
    }

    private JSONObject convertParam(DoorControllerEntity param) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("doorCtrlIP", param.getControllerIp());
        jsonObject.put("macAddr", param.getMacAddr());
        jsonObject.put("doorReaderNo", param.getReaderNo());
        jsonObject.put("doorId", param.getMappingDoorId());
        jsonObject.put("doorCtrlProvier", this.cacheDataUtils.getConfigValueByKey(VisitorConstants.Keys.DOOR_CTRL_PROVIDER));
        return jsonObject;
    }

    /**
     * 门禁授权和预约
     * @param doorAuthList
     * @return
     */
    @Override
    public EsbResponse doorCtrlAuthAndReserve(List<DoorCtrlAuthEntity> doorAuthList) {
        String url = this.cacheDataUtils.getConfigValueByKey(VisitorConstants.Keys.ESB_SERVER) + "doorCtrlReserve";
        try {
            Map<String, Object> paramMap = Maps.newHashMap();
            paramMap.put("doorCtrlAuthList", doorAuthList);
            String resp = this.restTemplateUtils.httpPostMediaTypeJson(url, String.class, paramMap);
            return JSONObject.parseObject(resp, EsbResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("达实门禁授权异常！原因：" + e.getMessage());
            return EsbResponse.error("达实门禁授权异常！原因：" + e.getMessage());
        }
    }

    @Override
    public EsbResponse doorCtrlAuthAndReserve4Coson(List<CosonDoorCtrlReqDTO> reqList) {
        String url = this.cacheDataUtils.getConfigValueByKey(VisitorConstants.Keys.ESB_SERVER) + "doorCtrlReserve4Coson";
        try {
            Map<String, Object> paramMap = Maps.newHashMap();
            paramMap.put("params", reqList);
            String resp = this.restTemplateUtils.httpPostMediaTypeJson(url, String.class, paramMap);
            return JSONObject.parseObject(resp, EsbResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("科松门禁授权异常！原因：" + e.getMessage());
            return EsbResponse.error("科松门禁授权异常！原因：" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String json = "{\\\"msg\\\":\\\"远程开门异常！请联系管理员\\\",\\\"code\\\":500}";
        EsbResponse resp = JSONObject.parseObject(json, EsbResponse.class);
    }
}
