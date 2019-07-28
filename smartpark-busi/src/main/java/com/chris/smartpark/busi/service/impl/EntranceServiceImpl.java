package com.chris.smartpark.busi.service.impl;

import com.chris.base.common.exception.CommonException;
import com.chris.base.common.tree.TreeNode;
import com.chris.base.common.utils.*;
import com.chris.base.modules.app.cache.AppLoginUser;
import com.chris.base.modules.app.cache.AppLoginUserCacheUtils;
import com.chris.smartpark.base.dto.EsbResponse;
import com.chris.smartpark.busi.common.VisitorConstants;
import com.chris.smartpark.busi.common.VisitorUtils;
import com.chris.smartpark.busi.dao.DoorControllerDao;
import com.chris.smartpark.busi.dao.DoorDao;
import com.chris.smartpark.busi.dto.DoorLevelDTO;
import com.chris.smartpark.busi.dto.OpenDoorLogDTO;
import com.chris.smartpark.busi.entity.DoorControllerEntity;
import com.chris.smartpark.busi.entity.DoorEntity;
import com.chris.smartpark.busi.entity.OpenDoorLogEntity;
import com.chris.smartpark.busi.facade.EsbFacade;
import com.chris.smartpark.busi.service.EntranceService;
import com.chris.smartpark.busi.service.OpenDoorLogService;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service("entranceService")
public class EntranceServiceImpl implements EntranceService {
    //日志处理

    @Autowired
    private DoorDao doorDao;

    @Autowired
    private DoorControllerDao doorControllerDao;

    @Autowired
    private OpenDoorLogService openDoorLogService;

    @Autowired
    private EsbFacade esbFacade;

    @Override
    public List<DoorEntity> queryUserDoors(String openId) {
        //openId 从tb_user 取mobile , 用mobile 从 base_staff 中取出员工id, 根据员工id的工位取出门禁列表
        //参数校验
        if (ValidateUtils.isEmptyString(openId)) {
            throw new CommonException("参数openId不能为空!");
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(VisitorConstants.Keys.OPEN_ID, openId);
        return this.doorDao.queryUserDoor(params);
    }

    @Override
    public List<TreeNode> queryHasPermissionDoorLevelNodesByOpenId(String openId) {
        // 查询员工有权限的门信息
        List<DoorLevelDTO> hasPermissionDoorLevelList = this.doorDao.queryHasPermissionDoorsByOpenId(openId);
        if (ValidateUtils.isEmptyCollection(hasPermissionDoorLevelList)) {
            throw new CommonException("当前员工没有门禁权限，不能授权！");
        }
        // 组装成树结构，用于前台展示
        // 根节点为楼宇名称
        List<TreeNode> treeNodes = Lists.newArrayList();
        TreeNode root = TreeNode.createRoot("0", hasPermissionDoorLevelList.get(0).getBuildingName());
        treeNodes.add(root);
        root.setChildren(this.buildFloorNodes(root, hasPermissionDoorLevelList));
        List<TreeNode> doorNodes = this.buildDoorNodes(hasPermissionDoorLevelList);
        root.getChildren().forEach(floorNode -> {
            List<TreeNode> chidrenNodes = doorNodes.stream().filter(doorNode -> ValidateUtils.equals(doorNode.getParentNodeId(), floorNode.getNodeId())).collect(Collectors.toList());
            floorNode.getChildren().addAll(chidrenNodes);
        });
        return treeNodes;
    }

    private List<TreeNode> buildFloorNodes(TreeNode root, List<DoorLevelDTO> hasPermissionDoorLevelList) {
        Map<Long, List<DoorLevelDTO>> groupFloorMap = hasPermissionDoorLevelList.stream().collect(Collectors.groupingBy(DoorLevelDTO::getFloorId));
        List<TreeNode> floorNodes = new ArrayList<>(groupFloorMap.size());
        groupFloorMap.forEach((k, v) -> {
            TreeNode floorNode = TreeNode.createNode(k + "", v.get(0).getFloorName(), root.getNodeId(), null);
            floorNodes.add(floorNode);
        });
        return floorNodes;
    }

    private List<TreeNode> buildDoorNodes(List<DoorLevelDTO> hasPermissionDoorLevelList) {
        return hasPermissionDoorLevelList.stream().map(item -> {
            TreeNode doorNode = TreeNode.createNode(item.getDoorId() + "", item.getDoorName(), item.getFloorId() + "",
                    VisitorConstants.DefaultAuthorizedFlag.YES == item.getDefaultAuthorizedFlag() ? VisitorConstants.DefaultAuthorizedFlag.YES + "" : null);
            return doorNode;
        }).collect(Collectors.toList());
    }

    /**
     * 远程开门
     * @param params
     */
    @Override
    public void remoteOpenDoor(Map<String, Object> params) {
        if (ValidateUtils.isEmpty(params)) {
            throw new CommonException("请求参数不能为空！");
        }
        if (ValidateUtils.isEmpty(params.get(VisitorConstants.Keys.OPEN_ID))) {
            throw new CommonException("openid不能为空！");
        }
        if (ValidateUtils.isEmpty(params.get(VisitorConstants.Keys.DOOR_ID))) {
            throw new CommonException("门ID不能为空！");
        }
        // 首先校验员工是否有对应门的权限
        Long doorId = Long.valueOf(params.get(VisitorConstants.Keys.DOOR_ID).toString());
        String openId = params.get(VisitorConstants.Keys.OPEN_ID).toString();
        // 根据门ID查询其对应的门禁控制器
        List<DoorControllerEntity> doorControllers = doorControllerDao.queryDoorControllerByDoorId(
                ImmutableMap.of(VisitorConstants.Keys.DOOR_ID, doorId, VisitorConstants.Keys.OPEN_ID, openId));
        if (ValidateUtils.isEmptyCollection(doorControllers)) {
            throw new CommonException("当前员工没有该门禁操作权限");
        }
        DoorControllerEntity doorController = doorControllers.get(0);
        EsbResponse resp = this.esbFacade.remoteOpenDoor(doorController);
        this.recordOpenDoorLog(doorId, openId, doorController, resp);
        if (!resp.isOK()) {
            log.error(resp.getMsg());
            throw new CommonException(resp.getMsg());
        }
    }

    /**
     * 记录开门日志
     * @param doorId
     * @param openId
     * @param doorController
     * @param resp
     */
    private void recordOpenDoorLog(Long doorId, String openId, DoorControllerEntity doorController, EsbResponse resp) {
        AppLoginUser appLoginUser = AppLoginUserCacheUtils.getAppLoginUser(openId);
        // 记录开门日志
        OpenDoorLogEntity openDoorLog = new OpenDoorLogEntity();
        openDoorLog.setDoorId(doorId);
        openDoorLog.setUserId(appLoginUser.getUserId());
        openDoorLog.setUserType(appLoginUser.getRoleId() + "");
        Date currentDate = DateUtils.currentDate();
        openDoorLog.setOpenTime(currentDate);
        openDoorLog.setCreateTime(currentDate);
        openDoorLog.setOpenType(VisitorConstants.OpenDoorType.REMOTE + "");
        openDoorLog.setOpenResult(resp.isOK() ? VisitorConstants.OpenDoorResult.SUCCESS : VisitorConstants.OpenDoorResult.FAILURE);
        openDoorLog.setOpenResultDesc(resp.isOK() ? VisitorConstants.OpenDoorResult.DESC_SUCCESS : VisitorConstants.OpenDoorResult.DESC_FAILURE);
        openDoorLog.setRemark(resp.getMsg());
        String detail = "ID=" + doorController.getId() + ",NAME=" + doorController.getControllerName() + ",READNO=" + doorController.getReaderNo() +
                ",MAC_ADDR=" + doorController.getMacAddr() + ",IP:" + doorController.getControllerIp() + ",PORT=" + doorController.getControllerPort();
        openDoorLog.setExt3(detail);
        openDoorLog.setCreateUserId(appLoginUser.getUserId());
        this.openDoorLogService.save(openDoorLog);
    }

    @Override
    public CommonResponse queryOpenDoorLogs(Map<String, Object> params) {
        if (ValidateUtils.isEmpty(params.get(VisitorConstants.Keys.OPEN_ID))) {
            throw new CommonException("openId为空");
        }
        if (VisitorUtils.isAdminRole(params.get(VisitorConstants.Keys.OPEN_ID).toString())) {
            params.remove(VisitorConstants.Keys.OPEN_ID);
        }
        //查询列表数据
        Query query = new Query(params);

        int total = this.openDoorLogService.queryTotalByKeyword(query);
        List<OpenDoorLogDTO> openDoorLogs = total > 0 ? this.openDoorLogService.queryListByKeyword(query) : Lists.newArrayList();

        PageUtils pageUtil = new PageUtils(openDoorLogs, total, query.getLimit(), query.getPage());

        return CommonResponse.ok().put(VisitorConstants.Keys.PAGE, pageUtil);
    }
}
