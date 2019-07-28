package com.chris.smartpark;

import com.alibaba.fastjson.JSONObject;
import com.chris.BusiApplication;
import com.chris.base.common.tree.TreeNode;
import com.chris.base.common.utils.DateUtils;
import com.chris.base.modules.app.cache.AppLoginUser;
import com.chris.base.modules.app.entity.UserEntity;
import com.chris.smartpark.base.dto.EsbResponse;
import com.chris.smartpark.busi.common.VisitorConstants;
import com.chris.smartpark.busi.dto.CosonDoorCtrlReqDTO;
import com.chris.smartpark.busi.entity.DoorControllerEntity;
import com.chris.smartpark.busi.entity.DoorEntity;
import com.chris.smartpark.busi.entity.OpenDoorLogEntity;
import com.chris.smartpark.busi.facade.EsbFacade;
import com.chris.smartpark.busi.service.EntranceService;
import com.chris.smartpark.busi.service.OpenDoorLogService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BusiApplication.class)
public class EntranceTest {
    @Autowired
    private EntranceService entranceService;

    @Autowired
    private EsbFacade esbFacade;

    @Autowired
    private OpenDoorLogService openDoorLogService;

    @Test
    public void queryUserDoor() {
        String openId = "YzcmCZNvbXocrsz9dm8e";
        List<DoorEntity> userDoorList = this.entranceService.queryUserDoors(openId);
        System.out.println("查询用户门禁权限：" + JSONObject.toJSONString(userDoorList));
    }

    @Test
    public void queryDoorNodes() {
        List<TreeNode> doorNodes = this.entranceService.queryHasPermissionDoorLevelNodesByOpenId("YzcmCZNvbXocrsz9dm8e");
        System.out.println("查询用户门禁权限节点信息：" + JSONObject.toJSONString(doorNodes));
    }

    @Test
    public void remoteOpenDoor() {
        DoorControllerEntity param = new DoorControllerEntity();
//        param.setControllerIp("192.168.1.150");
        param.setControllerIp("192.168.1.77");
        param.setMacAddr("861CB1");
        param.setReaderNo(0);
        param.setMappingDoorId("1");
        EsbResponse resp = this.esbFacade.remoteOpenDoor(param);
        System.out.println("远程开门结果 = " + JSONObject.toJSONString(resp));
    }

    /**
     * 保存开门日志
     */
    @Test
    public void saveOpenDoorLog() {
        UserEntity user = new UserEntity();
        user.setUserId(27L);
        user.setMobile("17601540345");
        user.setUsername("17601540345");
        user.setOpenId("obETm5dkiNlfhrX1A4ztTuU4vmHQ");
        AppLoginUser appLoginUser = new AppLoginUser(user, user.getOpenId());
        // 记录开门日志
        OpenDoorLogEntity openDoorLogEntity = new OpenDoorLogEntity();
        openDoorLogEntity.setDoorId(4L);
        openDoorLogEntity.setUserId(appLoginUser.getUserId());
        Date currentDate = DateUtils.currentDate();
        openDoorLogEntity.setOpenTime(currentDate);
        openDoorLogEntity.setCreateTime(currentDate);
        openDoorLogEntity.setOpenResult(VisitorConstants.OpenDoorResult.SUCCESS);
        openDoorLogEntity.setOpenResultDesc(VisitorConstants.OpenDoorResult.DESC_SUCCESS);
        DoorControllerEntity doorController = new DoorControllerEntity();
        doorController.setId(2L);
        doorController.setControllerName("达实门禁控制器1");
        doorController.setControllerIp("192.168.1.150");
        doorController.setMacAddr("861CB1");
        doorController.setControllerPort("18001");
        doorController.setReaderNo(0);
        String detail = "ID=" + doorController.getId() + ",NAME=" + doorController.getControllerName() + ",READNO=" + doorController.getReaderNo() +
                ",MAC_ADDR=" + doorController.getMacAddr() + ",IP:" + doorController.getControllerIp() + ",PORT=" + doorController.getControllerPort();
        openDoorLogEntity.setExt3(detail);
        openDoorLogEntity.setCreateUserId(appLoginUser.getUserId());
        System.out.println("远程开门日志对象请求JSON：" + JSONObject.toJSONString(openDoorLogEntity));
        this.openDoorLogService.save(openDoorLogEntity);
        System.out.println("保存远程开门日志信息成功！");
    }

    @Test
    public void doorCtrlAuthorized4Coson() {
        List<CosonDoorCtrlReqDTO> reqList = Lists.newArrayList();
        CosonDoorCtrlReqDTO item = new CosonDoorCtrlReqDTO();
        /*item.setDoorId(0);
        item.setMappingDoorId(0);*/
        item.setDoorCtrlIp("192.168.1.77");
//        item.setUserCardId("31B86F87");
        item.setUserCardId("70905366");
        item.setStartTime("2019-07-13 12:08:00");
        item.setEndTime("2019-07-13 12:12:00");
        item.setUserType(1);
        item.setOperationType(4);
        reqList.add(item);
        EsbResponse result = this.esbFacade.doorCtrlAuthAndReserve4Coson(reqList);
        System.out.println(JSONObject.toJSONString(result));
    }

}
