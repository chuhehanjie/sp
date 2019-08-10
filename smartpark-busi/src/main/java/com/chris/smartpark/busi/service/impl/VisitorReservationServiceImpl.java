package com.chris.smartpark.busi.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.alibaba.fastjson.JSONObject;
import com.chris.base.common.exception.CommonException;
import com.chris.base.common.utils.*;
import com.chris.base.modules.app.entity.UserEntity;
import com.chris.base.modules.app.service.UserService;
import com.chris.base.modules.oss.entity.SysAttachmentEntity;
import com.chris.base.modules.oss.service.SysAttachmentService;
import com.chris.base.modules.sms.entity.SMSEntity;
import com.chris.base.modules.sys.service.SysConfigService;
import com.chris.smartpark.base.dto.EsbResponse;
import com.chris.smartpark.base.entity.BaseStaffEntity;
import com.chris.smartpark.base.service.BaseStaffService;
import com.chris.smartpark.busi.common.BeanUtil;
import com.chris.smartpark.busi.common.VisitorConstants;
import com.chris.smartpark.busi.common.VisitorUtils;
import com.chris.smartpark.busi.common.WXNoticeMsgUtils;
import com.chris.smartpark.busi.dao.AuthenticationRecordDao;
import com.chris.smartpark.busi.dao.VisitorDoorRelDao;
import com.chris.smartpark.busi.dao.VisitorReservationDao;
import com.chris.smartpark.busi.doorctrl.provider.DoorCtrlProvider;
import com.chris.smartpark.busi.dto.*;
import com.chris.smartpark.busi.entity.*;
import com.chris.smartpark.busi.facade.EsbFacade;
import com.chris.smartpark.busi.service.*;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Slf4j
@Service("visitorReservationService")
public class VisitorReservationServiceImpl implements VisitorReservationService {
    @Autowired
    private VisitorReservationDao visitorReservationDao;
    @Autowired
    private CarInfoService carInfoService;
    @Autowired
    private VisitorInfoService visitorInfoService;
    @Autowired
    private BaseStaffService baseStaffService;
    @Autowired
    private VisitorDoorRelDao visitorDoorRelDao;
    @Autowired
    private AuthenticationRecordDao authenticationRecordDao;
    @Autowired
    private UserService userService;
    @Autowired
    private VisitorIdcardService visitorIdcardService;
    @Autowired
    private VisitorInfoHisService visitorInfoHisService;
    @Autowired
    private VisitorDoorRelService visitorDoorRelService;
    @Autowired
    private SysConfigService sysconfigservice;
    @Autowired
    private SysAttachmentService sysAttachmentService;

    @Autowired
    private CacheDataUtils cacheDataUtils;

    @Autowired
    private EsbFacade esbFacade;

    @Autowired
    private WXNoticeMsgUtils wxNoticeMsgUtils;

    private ScheduledExecutorService scheduledExecutorService;

    /**
     * 查询预约单明细
     * @param id
     * @return
     */
    @Override
    public ReservationOrderDTO queryReservationOrderDetail(Long id) {
        ReservationOrderDTO reservationOrderDTO = new ReservationOrderDTO();
        //获取预约单信息
        VisitorReservationEntity reservationOrder = this.visitorReservationDao.queryObject(id);
        if (ValidateUtils.isEmpty(reservationOrder)) {
            throw new CommonException("预约单不存在！");
        }
        BeanUtil.copyProperties(reservationOrder, reservationOrderDTO);
        if (ValidateUtils.isNotEmptyString(reservationOrder.getExt1())) {
            reservationOrderDTO.setRejectReason(reservationOrder.getExt1());
        }

        //访客
        VisitorInfoHisEntity visitorHisInfo = this.visitorInfoHisService.queryByReservationId(reservationOrder.getId());
//        VisitorInfoEntity visitorInfoEntity = this.visitorInfoService.queryObject(reservationOrder.getVisitorId());
        reservationOrderDTO.setName(visitorHisInfo.getName());
        reservationOrderDTO.setIdcardNo(VisitorUtils.idcardNoFuzzy(visitorHisInfo.getIdcardNo()));
        //受访者
        BaseStaffEntity baseStaffEntity = this.baseStaffService.queryObject(reservationOrder.getStaffId());
        reservationOrderDTO.setStaffId(baseStaffEntity.getId());
        reservationOrderDTO.setStaffName(baseStaffEntity.getUsername());
        reservationOrderDTO.setStaffPhone(baseStaffEntity.getMobile());
        //获取同行车辆信息
        List<CarInfoEntity> list = this.carInfoService.queryList(ImmutableMap.of(VisitorConstants.Keys.RESERVATION_ORDER_ID, id));
        reservationOrderDTO.setCarInfoEntitys(list);
        return reservationOrderDTO;
    }

    @Override
    public List<VisitorReservationEntity> queryList(Map<String, Object> map) {
        return visitorReservationDao.queryList(map);
    }

    @Override
    public PageUtils queryReservationOrdersByOpenId(Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        if (VisitorUtils.isAdminRole(query.get(VisitorConstants.Keys.OPEN_ID).toString())) {
            query.put("isAdminRole", true);
        } else {
            query.put("isAdminRole", false);
        }
        int total = this.visitorReservationDao.countReservationOrdersByOpenId(query);
        List<ReservationOrderQryDTO> resultList = new ArrayList<>();
        if (total > 0) {
            resultList = this.sortData(this.visitorReservationDao.queryReservationOrdersByOpenId(query));
            // 身份证号码中间4位用“*”显示
            resultList.forEach(item -> item.setIdcardNo(VisitorUtils.idcardNoFuzzy(item.getIdcardNo())));
        }
        PageUtils pageUtil = new PageUtils(resultList, total, query.getLimit(), query.getPage());
        return pageUtil;
    }

    private List<ReservationOrderQryDTO> sortData(List<ReservationOrderQryDTO> resultList) {
        if (ValidateUtils.isEmptyCollection(resultList)) {
            return Lists.newArrayList();
        }
        List<ReservationOrderQryDTO> firstData = Lists.newArrayList();
        List<ReservationOrderQryDTO> secondData = Lists.newArrayList();
        for (int i = 0; i < resultList.size(); i++) {
            ReservationOrderQryDTO item = resultList.get(i);
            if (ValidateUtils.equals(VisitorConstants.ReservationOrderStatus.PENDING_APPROVE + "", item.getStatus())) {
                System.out.println(item.getStatus());
                firstData.add(item);
            } else {
                secondData.add(item);
            }
        }
        firstData.addAll(secondData);
        return firstData;
    }

    @Override
    public List<VisitorReservationEntity> queryEffectRecord(String idcardNo) {
        return visitorReservationDao.queryEffectRecord(idcardNo);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return visitorReservationDao.queryTotal(map);
    }

    @Override
    public void save(VisitorReservationEntity visitorReservation) {
        visitorReservationDao.save(visitorReservation);
    }

    @Override
    public void update(VisitorReservationEntity visitorReservation) {
        visitorReservationDao.update(visitorReservation);
    }

    @Override
    public void delete(Long id) {
        visitorReservationDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        visitorReservationDao.deleteBatch(ids);
    }

    /**
     * 生成预约单信息
     */
    @Override
    public Long createReservationOrder(ReservationOrderDTO reservationOrderDTO) {
        this.validateReservationOrder(reservationOrderDTO);
        return this.saveReservationOrder(reservationOrderDTO);
    }

    private Long saveReservationOrder(ReservationOrderDTO reservationOrderDTO){
        // TODO 查询系统配置判断是否配置了同行人详细信息(需要抽掉一个字典查询公共方法) 暂时放到第二阶段
        //1.保存访客信息（历史信息）
        VisitorInfoEntity visitorInfo = new VisitorInfoEntity();
        visitorInfo.setIdcardNo(reservationOrderDTO.getIdcardNo());
        VisitorInfoEntity rcd = this.visitorInfoService.selectByIdcardNo(visitorInfo);
        //存在身份证信息则保存
        if (ValidateUtils.isNotEmpty(reservationOrderDTO.getVisitorIdcardEntity()) &&
                ValidateUtils.isNotEmptyString(reservationOrderDTO.getIdcardNo())) {
            this.visitorIdcardService.save(reservationOrderDTO.getVisitorIdcardEntity());
        }
        // 1、保存访客信息
        if (ValidateUtils.isNotEmpty(rcd)) {
            visitorInfo.setId(rcd.getId());
        }
        visitorInfo.setName(reservationOrderDTO.getName());
        visitorInfo.setPhone(reservationOrderDTO.getPhone());
        visitorInfo.setCreateTime(DateUtils.currentDate());
        this.visitorInfoService.save(visitorInfo);

        // 2、保存预约单信息
        VisitorReservationEntity reservationOrder = new VisitorReservationEntity();
        BeanUtil.copyProperties(reservationOrderDTO, reservationOrder);
        reservationOrder.setStaffMobile(reservationOrderDTO.getStaffPhone());
        reservationOrder.setType(VisitorConstants.ReservationOrderType.ONLINE);
        reservationOrder.setVisitorId(visitorInfo.getId());
        reservationOrder.setCreateTime(DateUtils.currentDate());
        reservationOrder.setStatus(VisitorConstants.ReservationOrderStatus.PENDING_APPROVE + "");
        reservationOrder.setReservationNo(VisitorUtils.getReservationNo());
        //如果为现场预约则预约单类型为线下预约
        if(reservationOrderDTO.getIsLocalappoint() == VisitorConstants.isLocalappoint.OFFLINE){
            reservationOrder.setType(VisitorConstants.ReservationOrderType.OFFLINE);
        }
        this.save(reservationOrder);

        // 3、保存访客历史信息
        VisitorInfoHisEntity visitorInfoHis = new VisitorInfoHisEntity();
        BeanUtil.copyProperties(visitorInfo, visitorInfoHis);
        visitorInfoHis.setCreateTime(DateUtils.currentDate());
        visitorInfoHis.setVisitorId(visitorInfo.getId());
        visitorInfoHis.setId(null);
        visitorInfoHis.setReservationId(reservationOrder.getId());
        this.visitorInfoHisService.save(visitorInfoHis);

        // 4、保存车牌信息
        if (ValidateUtils.isNotEmptyCollection(reservationOrderDTO.getCarInfoEntitys())) {
            for (CarInfoEntity carInfo : reservationOrderDTO.getCarInfoEntitys()) {
                carInfo.setCreateTime(DateUtils.currentDate());
                carInfo.setReservationId(reservationOrder.getId());
                carInfo.setId(null);
            }
            this.carInfoService.batchInsert(reservationOrderDTO.getCarInfoEntitys());
        }
        // TODO 5.保存同行人信息（暂缓）

        // 发送消息
        this.asyncSendMsg(reservationOrder, visitorInfo, reservationOrderDTO.getFormId());
        return reservationOrder.getId();
    }

    /**
     * 异步发送消息
     * @param reservationOrder
     * @param visitorInfo
     */
    private void asyncSendMsg(VisitorReservationEntity reservationOrder, VisitorInfoEntity visitorInfo, String formId) {
        this.initScheduledExecutorService();
        this.scheduledExecutorService.execute(() -> {
            try {
                this.wxNoticeMsgUtils.sendOrderCreatedMsg2Visitor(reservationOrder, formId);
                this.wxNoticeMsgUtils.sendOrderAccpetMsg2Staff(reservationOrder, visitorInfo, formId);
                this.sendSMS2Staff(reservationOrder);
            } catch (Exception e) {
                log.error("异步消息发送异常！", e);
            }
        });
    }

    private void initScheduledExecutorService() {
        if (ValidateUtils.isEmpty(this.scheduledExecutorService)) {
            this.scheduledExecutorService =  new ScheduledThreadPoolExecutor(2,
                    new BasicThreadFactory.Builder().namingPattern("scheduled-sendmsg-%d").daemon(true).build());
        }
    }

    private void sendSMS2Staff(VisitorReservationEntity reservationOrder) {
        SMSEntity smsEntity = new SMSEntity();
        smsEntity.setMobile(reservationOrder.getStaffMobile());
        smsEntity.setSmsType(Constant.SMSType.NOTICE);
        smsEntity.setTemplateCode(Constant.SMSTemplateCode.RESERVATION_HANDLE.getTemplateCode());
        SendSMSUtils.sendSms(smsEntity);
        log.info("发送短信给员工成功");
    }

    /**
     * 校验预约单
     * @param reservationOrderDTO
     */
    private void validateReservationOrder(ReservationOrderDTO reservationOrderDTO) {
        //校验预约结束时间结束时间在开始时间一小时以后
        if(!this.isOneMinuteAfter(reservationOrderDTO.getAppointStartTime(),reservationOrderDTO.getAppointEndTime())){
            throw new CommonException("预约结束时间需要在开始时间一分钟以后！");
        }
        //校验身份证是否合法
        if (!CommonValidator.isIDCard(reservationOrderDTO.getIdcardNo())) {
            throw new CommonException("身份证格式不正确！");
        }
        //校验手机号是否正确
        if (!CommonValidator.isMobile(reservationOrderDTO.getPhone())) {
            throw new CommonException("手机号格式不正确！");
        }
        //校验验证码是否正确
        if (reservationOrderDTO.getIsLocalappoint() == VisitorConstants.isLocalappoint.ONLINE && !VisitorUtils.isVerifyCodeOK(reservationOrderDTO.getPhone(), reservationOrderDTO.getVerifyCode(), Constant.SMSTemplateCode.RESERVATION_VERIFY_CODE.getTemplateCode())) {
            throw new CommonException("验证码不正确！");
        }
        //校验员工手机号是否存在
        if (ValidateUtils.isEmpty(this.baseStaffService.queryByMobile(reservationOrderDTO.getStaffPhone()))) {
            throw new CommonException("被访人信息不存在！");
        }
    }
    /**
     * 身份鉴权
     */
    @Override
    public CommonResponse checkIdCard(AuthIdCardDTO authIdCardDTO) {
        log.error("访客身份信息验证请求JSON {}", JSONObject.toJSONString(authIdCardDTO));
        //验证有无有效预约单
        List<VisitorReservationEntity> reservationOrders = this.queryByIdcardAndStatus(authIdCardDTO.getCardNO(), VisitorConstants.ReservationOrderStatus.APPROVE_OK + "");
        if (ValidateUtils.isEmptyCollection(reservationOrders)) {
            return CommonResponse.ok("现场访问").put("isSuccess","false").put("id","");
        } else {
            StringBuilder sb = new StringBuilder();
            for(VisitorReservationEntity reservation : reservationOrders){
                sb.append(reservation.getId()+",");
            }
            sb.deleteCharAt(sb.length()-1);
            return CommonResponse.ok("预约访问").put("isSuccess","true").put("id",sb);
        }
    }

    /**
     * 保存访客身份信息并推送门禁
     */
    @Override
    public CommonResponse saveCardAndGetAuth(AuthIdCardDTO authIdCardDTO) {
        log.info("访客身份信息上传请求JSON {}", JSONObject.toJSONString(authIdCardDTO));
        //验证有无有效预约单
        CommonResponse result = CommonResponse.ok("上传成功").put("isSuccess","true");
        try {
            String ids = authIdCardDTO.getId();
            String[] idList = ids.split(",");
            for (String id : idList) {
                //查询预约单详细信息
                VisitorReservationEntity reservationOrder = this.queryObjectById(Long.parseLong(id));
                VisitorIdcardEntity visitorIdcard = new VisitorIdcardEntity();
                //构造访客信息用于保存证件信息和更新访客信息
                visitorIdcard.setVisitorId(reservationOrder.getVisitorId());
                visitorIdcard.setName(authIdCardDTO.getCardName());
                visitorIdcard.setIdcardNo(authIdCardDTO.getCardNO());
                visitorIdcard.setIdcardPhotoUrl(authIdCardDTO.getCardPht());
                visitorIdcard.setPhysicalCardId(String.valueOf(authIdCardDTO.getCardID()));

                //保存或更新信息到访客身份信息表
                List<VisitorIdcardEntity> idcardList = this.visitorIdcardService.queryList(ImmutableMap.of(VisitorConstants.Keys.IDCARD_NO, authIdCardDTO.getCardNO()));
                if (ValidateUtils.isEmptyCollection(idcardList)) {
                    this.visitorIdcardService.save(visitorIdcard);
                } else {
                    //更新访客身份信息，一般更新的情况很少
                    visitorIdcard.setId(idcardList.get(0).getId());
                    visitorIdcard.setUpdateTime(DateUtils.currentDate());
                    this.visitorIdcardService.update(visitorIdcard);
                }
                this.processDoorCtrlAuth4Coson(visitorIdcard, reservationOrder);
                //更新预约单状态为完成
                reservationOrder.setStatus(VisitorConstants.ReservationOrderStatus.COMPLETED + "");
                reservationOrder.setPhysicalCardId(String.valueOf(authIdCardDTO.getCardID()));
                // 更新状态流程
                reservationOrder.setStatusFlow();
                this.visitorReservationDao.updateStatus(reservationOrder);
            }
        } catch (Exception e){
            result = CommonResponse.error("上传失败").put("isSuccess","fasle");
            log.error("上传失败，原因：{}", e.getMessage());
        }
        return result;
    }

    /**
     * 送物理卡到门禁系统
     * @param reservationOrder
     * @param visitorIdcard
     */
    private void sendPhyIdCard2DoorCtrlSys(VisitorReservationEntity reservationOrder, VisitorIdcardEntity visitorIdcard) {
        //组装入参调用门禁接口授权
        java.util.Date startTime = reservationOrder.getAppointStartTime();
        java.util.Date endTime = reservationOrder.getAppointEndTime();
        // TODO 需要根据访客ID查询访客对应可授权的门禁列表
        List<String> mappingDoorIds = visitorDoorRelService.queryMappdingDoorIdsByReservationOrderId(reservationOrder.getId());
        if (ValidateUtils.isEmptyCollection(mappingDoorIds)) {
            throw new CommonException("门禁未与第三方厂家做映射！");
        }
        List<DoorCtrlAuthEntity> doorAuthList = new ArrayList<>();
        for (String mappingDoorId : mappingDoorIds) {
            //一个门对应两条数据
            DoorCtrlAuthEntity door1 = new DoorCtrlAuthEntity();
            DoorCtrlAuthEntity door2 = new DoorCtrlAuthEntity();
            door1.setCardID(Integer.valueOf(this.convertPhyCardId(visitorIdcard.getPhysicalCardId())));
            door1.setDoorID(Integer.valueOf(mappingDoorId));
            door1.setPassword("0000");
            door1.setDueDate(DateUtils.parseDate("2099-12-31"));
            door1.setAuthorType(0);
            door1.setAuthorStatus(0);
            door1.setUserTimeGrp(0);
            door1.setDownLoaded(0);
            door1.setFirstDownLoaded(0);
            door1.setPreventCard(0);
            door1.setStartTime(startTime);

            BeanUtil.copyProperties(door1, door2);
            door2.setAuthorStatus(2);
            door2.setStartTime(endTime);
            doorAuthList.add(door1);
            doorAuthList.add(door2);
        }
        this.saveDoorAuthRecords(doorAuthList);
        log.info("========调用门禁接口授权成功=====");
    }

    /**
     * 转换物理卡ID
     * @param physicalCardId
     * @return
     */
    private String convertPhyCardId(String physicalCardId) {
        if (physicalCardId.length() < 15) {
            throw new CommonException("物理卡ID位数小于15位");
        }
        DoorCtrlProvider doorCtrlProvider = SpringContextUtils.getBean(this.cacheDataUtils.getConfigValueByKey(VisitorConstants.Keys.DOOR_CTRL_PROVIDER), DoorCtrlProvider.class);
        String resultValue = doorCtrlProvider.convertPhyCardId(physicalCardId);
        log.error("物理卡ID[{}]转换后的值为[{}]", physicalCardId, resultValue);
        return resultValue;
    }

    public static void main(String[] args) {
        VisitorReservationServiceImpl v = new VisitorReservationServiceImpl();
        // "4196FB0431B86F87"
//        int cardId = new VisitorReservationServiceImpl().convertPhyCardId("4196FB0431B86F87");
        String [] ids = {"4196FB0431B86F87", "30E43792801382B2", "3233127FC0178588", "1237788520"};
        for (String id : ids) {
            String cardId = id.substring(id.length() - 8);
            System.out.println(cardId);
            int resultValue = VisitorUtils.hex2Int(cardId);
            System.out.println(resultValue);
        }
        System.out.println("v = " + v.isOneMinuteAfter(DateUtils.parseDate("2019-07-15 23:09:30", DateUtils.DATE_TIME_PATTERN), DateUtils.parseDate("2019-07-15 23:11:30", DateUtils.DATE_TIME_PATTERN)));
        System.out.println("v2 = " + v.isOneMinuteAfter(DateUtils.parseDate("2019-07-15 23:09:30", DateUtils.DATE_TIME_PATTERN), DateUtils.parseDate("2019-07-15 23:10:22", DateUtils.DATE_TIME_PATTERN)));

//        int cardId = new VisitorReservationServiceImpl().convertPhyCardId("3233127FC0178588");
//        int cardId = new VisitorReservationServiceImpl().convertPhyCardId("1164B001ACB58707");
//        convertPhyCardId("20A2C42894518466");
        /*System.out.println("vvvv = " + VisitorUtils.hex2Int("845194"));
        System.out.println("value = " + VisitorUtils.hex2Int("87b5ac"));*/
        System.out.println("430302198410030794".replaceAll("(\\d{4})(\\d+)(\\d{4})", "$1***$3"));
    }



    /**
     * 保存访客门授权记录
     * @param doorCtrlAuthList
     */
    private void saveDoorAuthRecords(List<DoorCtrlAuthEntity> doorCtrlAuthList) {
        EsbResponse resp = this.esbFacade.doorCtrlAuthAndReserve(doorCtrlAuthList);
        if (!resp.isOK()) {
            log.error(resp.getMsg());
            throw new CommonException(resp.getMsg());
        }
    }

    /**
     * 身份鉴权送门禁
     */
    @Override
    public void checkIdCardAndGetAuth(VisitorIdcardEntity visitorIdcard) {
        log.error("访客身份信息验证请求JSON {}", JSONObject.toJSONString(visitorIdcard));
        //验证有无有效预约单
        List<VisitorReservationEntity> reservationOrders = this.queryByIdcardAndStatus(visitorIdcard.getIdcardNo(), VisitorConstants.ReservationOrderStatus.APPROVE_OK + "");
        if (ValidateUtils.isEmptyCollection(reservationOrders)) {
            throw new CommonException("该身份证没有匹配的预约单信息");
        } else {
            for (VisitorReservationEntity reservationOrder : reservationOrders) {
                visitorIdcard.setVisitorId(reservationOrder.getVisitorId());
                //保存或更新信息到访客身份信息表
                List<VisitorIdcardEntity> idcardList = this.visitorIdcardService.queryList(ImmutableMap.of(VisitorConstants.Keys.IDCARD_NO, visitorIdcard.getIdcardNo()));
                if (ValidateUtils.isEmptyCollection(idcardList)) {
                    this.visitorIdcardService.save(visitorIdcard);
                } else {
                    //更新访客身份信息，一般更新的情况很少
                    visitorIdcard.setId(idcardList.get(0).getId());
                    this.visitorIdcardService.update(visitorIdcard);
                }
                if (VisitorUtils.isCosonDoorCtrl()) {
                    log.error("处理科松门禁");
                    this.processDoorCtrlAuth4Coson(visitorIdcard, reservationOrder);
                } else {
                    log.error("处理达实门禁");
                    // 处理达实门禁授权
                    List<DoorCtrlAuthEntity> doorCtrlAuthList = this.buildDoorCtrlAuthParams(visitorIdcard, reservationOrder);
                    this.saveDoorAuthRecords(doorCtrlAuthList);
                }
                log.info("========调用门禁接口授权成功=====");
                //更新预约单状态为完成
                reservationOrder.setStatus(VisitorConstants.ReservationOrderStatus.COMPLETED + "");
                // 更新状态流程
                reservationOrder.setStatusFlow();
                this.visitorReservationDao.updateStatus(reservationOrder);
            }
        }
    }

    private void processDoorCtrlAuth4Coson(VisitorIdcardEntity visitorIdcard, VisitorReservationEntity reservationOrder) {
        // 处理科松门禁授权
        String userCardId = this.convertPhyCardId(visitorIdcard.getPhysicalCardId());
        // 查询控制器
        List<CosonDoorCtrlReqDTO> reqList = this.visitorReservationDao.queryCosonDoorCtrlParams(reservationOrder.getId());
        if (ValidateUtils.isEmptyCollection(reqList)) {
            throw new CommonException("未配置门禁权限");
        }
        reqList.forEach(item -> {
            item.setStartTime(DateUtils.format(reservationOrder.getAppointStartTime(), DateUtils.DATE_TIME_PATTERN));
            item.setEndTime(DateUtils.format(reservationOrder.getAppointEndTime(), DateUtils.DATE_TIME_PATTERN));
            item.setUserCardId(userCardId);
            item.setUserType(1); // TODO 暂时写死为固定卡
            item.setOperationType(4); // TODO 暂时写死为临时卡设置授权时间
        });
        EsbResponse resp = this.esbFacade.doorCtrlAuthAndReserve4Coson(reqList);
        if (!resp.isOK()) {
            throw new CommonException(resp.getMsg());
        }
    }

    /**
     * 构建门禁授权对象参数
     * @param visitorIdcard
     * @param reservationOrder
     * @return
     */
    private List<DoorCtrlAuthEntity> buildDoorCtrlAuthParams(VisitorIdcardEntity visitorIdcard, VisitorReservationEntity reservationOrder) {
        //组装入参调用门禁接口授权
        Date startTime = reservationOrder.getAppointStartTime();
        Date endTime = reservationOrder.getAppointEndTime();
        // TODO 需要根据访客ID查询访客对应可授权的门禁列表
        List<VisitorDoorRelEntity> doorList = this.visitorDoorRelService.queryList(ImmutableMap.of(VisitorConstants.Keys.RESERVATION_ORDER_ID, reservationOrder.getId()));
        List<DoorCtrlAuthEntity> doorAuthList = Lists.newArrayList();
        for (VisitorDoorRelEntity door : doorList) {
            //一个门对应两条数据
            DoorCtrlAuthEntity door1 = new DoorCtrlAuthEntity();
            DoorCtrlAuthEntity door2 = new DoorCtrlAuthEntity();
            door1.setCardID(Integer.valueOf(this.convertPhyCardId(visitorIdcard.getPhysicalCardId())));
            door1.setDoorID(Math.toIntExact(door.getDoorId()));
            door1.setPassword("0000");
            door1.setDueDate(DateUtils.parseDate("2099-12-31"));
            door1.setAuthorType(0);
            door1.setAuthorStatus(0);
            door1.setUserTimeGrp(0);
            door1.setDownLoaded(0);
            door1.setFirstDownLoaded(0);
            door1.setPreventCard(0);
            door1.setStartTime(startTime);

            BeanUtil.copyProperties(door1, door2);
            door2.setAuthorStatus(2);
            door2.setStartTime(endTime);
            doorAuthList.add(door2);
        }
        return doorAuthList;
    }

    @Override
    public VisitorReservationEntity queryObjectById(Long id) {
        return visitorReservationDao.queryObject(id);
    }

    /**
     * 访客预约审核
     * @param authorizeDTO
     * @return
     */
    @Override
    public void approve(ReservationOrderApproveDTO authorizeDTO) {
        log.error("请求参数：{}", JSONObject.toJSONString(authorizeDTO));
        this.validateParams(authorizeDTO);
        //1.查询预约单
        VisitorReservationEntity reservationOrder = visitorReservationDao.queryObject(authorizeDTO.getReservationId());
        if (ValidateUtils.notEquals(VisitorConstants.ReservationOrderStatus.PENDING_APPROVE + "", reservationOrder.getStatus())) {
            //不是待审核状态无需处理
            throw new CommonException("当前预约单已经审核，请查看预约单详情！");
        }
        // 根据访客ID查询访客信息
        VisitorInfoEntity visitorInfo = this.visitorInfoService.queryObject(reservationOrder.getVisitorId());
        // 根据openId查询用户信息(员工或管理员)
        UserEntity user = this.userService.queryUserByOpenId(authorizeDTO.getOpenId());
        // 审核不通过处理
        if (ValidateUtils.equals(VisitorConstants.ApproveResult.REJECT, authorizeDTO.getApproveReslut())) {
            //更新预约单状态为审核不通过
            reservationOrder.setStatus(VisitorConstants.ReservationOrderStatus.APPROVE_REJECT + "");
            reservationOrder.setExt1(authorizeDTO.getRejectReaon());
            // 更新状态流程
            reservationOrder.setStatusFlow();
            this.visitorReservationDao.updateRejectReasonAndStatus(reservationOrder);
            //发送审核不通过短信给访客
            this.sendApproveRejectSMS(authorizeDTO, visitorInfo);
        } else if (ValidateUtils.equals(VisitorConstants.ApproveResult.OK, authorizeDTO.getApproveReslut())) {
            /**
             * 授权审核通过处理
             * 1、保存访客门禁关系
             * 2、同步信息到人脸识别
             * 3、同步信息到车管系统（如果有车辆信息）
             * 4、如果是线下预约，则需要将门禁信息送门禁系统
             */
            this.saveVisitorDoorRelation(authorizeDTO, reservationOrder);
            // TODO 送人脸识别系统（第一阶段暂不实现）
            log.error("送人脸识别系统");

            List<CarInfoEntity> carInfoList = this.carInfoService.queryList(ImmutableMap.of(VisitorConstants.Keys.RESERVATION_ORDER_ID, reservationOrder.getId()));
            if (ValidateUtils.isNotEmptyCollection(carInfoList)) {
                // 存在车辆信息则需要送车管系统
                // TODO 送车管系统（第一阶段暂不实现）
                log.error("送车管系统开始......");
            }
            if (VisitorConstants.ReservationOrderType.OFFLINE == reservationOrder.getType()) {
                // 线下预约的预约单需要送门禁
                VisitorIdcardEntity visitorIdcard = this.queryVisitorIdcardByOrderId(reservationOrder.getId() + "");
                if (VisitorUtils.isCosonDoorCtrl()) {
                    log.error("处理科松门禁");
                    this.processDoorCtrlAuth4Coson(visitorIdcard, reservationOrder);
                } else {
                    log.error("处理达实门禁");
                    this.sendPhyIdCard2DoorCtrlSys(reservationOrder, visitorIdcard);
                }
            }
            //4.更新预约信息
            reservationOrder.setActStartTime(authorizeDTO.getActStartTime());
            reservationOrder.setActEndTime(authorizeDTO.getActEndTime());
            reservationOrder.setStatus(VisitorConstants.ReservationOrderStatus.APPROVE_OK + "");
            // 更新状态流程
            reservationOrder.setStatusFlow();
            this.visitorReservationDao.update(reservationOrder);
            //5.发送审核成功短信给访客
            this.sendApproveOKSMS(authorizeDTO, visitorInfo, user);
        }
        this.wxNoticeMsgUtils.sendOrderApproveResultMsg2Visitor(reservationOrder, authorizeDTO);
        this.saveAuthenticationRecord(authorizeDTO, reservationOrder, user);
        log.error("授权结束......");
    }

    /**
     * 保存授权结果记录
     *
     * @param authorizeDTO
     * @param visitorReservationOrder
     */
    private void saveAuthenticationRecord(ReservationOrderApproveDTO authorizeDTO, VisitorReservationEntity visitorReservationOrder, UserEntity user) {
        AuthenticationRecordEntity authenticationRecord = new AuthenticationRecordEntity();
        authenticationRecord.setReservationId(visitorReservationOrder.getId());
        authenticationRecord.setVisitorId(visitorReservationOrder.getVisitorId());
        authenticationRecord.setApplyBeginTime(visitorReservationOrder.getAppointEndTime());
        authenticationRecord.setApplyEndTime(visitorReservationOrder.getAppointEndTime());
        if (ValidateUtils.equals(authorizeDTO.getApproveReslut(), VisitorConstants.ApproveResult.OK)) {
            authenticationRecord.setAuthBeginTime(authorizeDTO.getActStartTime());
            authenticationRecord.setAuthEndTime(authorizeDTO.getActEndTime());
            authenticationRecord.setStatus(VisitorConstants.ApproveResult.OK + "");
            authenticationRecord.setAuthDesc("审核通过");
        } else {
            authenticationRecord.setStatus(VisitorConstants.ApproveResult.REJECT + "");
            authenticationRecord.setAuthDesc(authorizeDTO.getRejectReaon());
        }
        authenticationRecord.setAuthTime(new java.util.Date());
        authenticationRecord.setCreateUserId(user.getUserId());
        authenticationRecord.setCreateTime(new java.util.Date());
        authenticationRecord.setUpdateUserId(user.getUserId());
        authenticationRecord.setUpdateTime(new java.util.Date());
        authenticationRecordDao.save(authenticationRecord);
    }

    private void saveVisitorDoorRelation(ReservationOrderApproveDTO authorizeDTO, VisitorReservationEntity visitorReservation) {
        List<VisitorDoorRelEntity> visitorDoorRelList = new ArrayList<VisitorDoorRelEntity>();
        for (Long doorId : authorizeDTO.getDoorList()) {
            VisitorDoorRelEntity visitorDoorRelEntity = new VisitorDoorRelEntity();
            visitorDoorRelEntity.setReservationId(authorizeDTO.getReservationId());
            visitorDoorRelEntity.setDoorId(doorId);
            visitorDoorRelEntity.setVisitorId(visitorReservation.getVisitorId());
            visitorDoorRelEntity.setStatus("1");
            visitorDoorRelList.add(visitorDoorRelEntity);
        }
        visitorDoorRelDao.saveBatch(visitorDoorRelList);
    }

    private void sendApproveOKSMS(ReservationOrderApproveDTO authorizeDTO, VisitorInfoEntity visitorInfo, UserEntity user) {
        SMSEntity smsEntity = new SMSEntity();
        smsEntity.setMobile(visitorInfo.getPhone());
        smsEntity.setSmsType(Constant.SMSType.NOTICE);
        smsEntity.setTemplateCode(Constant.SMSTemplateCode.RESERVATION_SUCCESS.getTemplateCode());
        JSONObject templateParam = new JSONObject();
        templateParam.put(VisitorConstants.Keys.TIME, DateUtils.format(authorizeDTO.getActStartTime(), "yyyy年MM月dd日 hh时mm分"));
        Map<String, Object> parkInfo = this.baseStaffService.queryParkInfoByStaffMobile(user.getMobile());
        templateParam.put(VisitorConstants.Keys.PARK_NAME, parkInfo.get("parkName"));
        templateParam.put(VisitorConstants.Keys.BUILDING, parkInfo.get("buildingName"));
        smsEntity.setTemplateParam(templateParam.toJSONString());
        SendSMSUtils.sendSms(smsEntity);
    }

    private void sendApproveRejectSMS(ReservationOrderApproveDTO authorizeDTO, VisitorInfoEntity visitorInfo) {
        SMSEntity smsEntity = new SMSEntity();
        smsEntity.setMobile(visitorInfo.getPhone());
        smsEntity.setSmsType(Constant.SMSType.NOTICE);
        smsEntity.setTemplateCode(Constant.SMSTemplateCode.RESERVATION_FAIL.getTemplateCode());
        JSONObject templateParam = new JSONObject();
        templateParam.put(VisitorConstants.Keys.REJECT_REASON, authorizeDTO.getRejectReaon());
        smsEntity.setTemplateParam(templateParam.toJSONString());
        SendSMSUtils.sendSms(smsEntity);
    }

    /**
     * 参数校验
     *
     * @param authorizeDTO
     */
    private void validateParams(ReservationOrderApproveDTO authorizeDTO) {
        if (ValidateUtils.isEmpty(authorizeDTO.getReservationId())) {
            throw new CommonException("预约单为空！");
        }
        if (ValidateUtils.isEmptyString(authorizeDTO.getOpenId())) {
            throw new CommonException("openid为空！");
        }
        if (VisitorConstants.ApproveResult.OK == authorizeDTO.getApproveReslut()) {
            // 授权通过则需要传入门禁列表ID
            if (ValidateUtils.isEmptyCollection(authorizeDTO.getDoorList())) {
                throw new CommonException("授权门禁列表为空！");
            }
        }
        if (VisitorConstants.ApproveResult.REJECT == authorizeDTO.getApproveReslut()) {
            // 未授权则需要填写拒绝原因
            if (ValidateUtils.isEmptyString(authorizeDTO.getRejectReaon())) {
                throw new CommonException("拒绝原因为空！");
            }
        }
    }

    @Override
    public void change2Overdue(){
        try{
            //1.取待审核，待激活但未在规定时间现场激活的预约单
            List<VisitorReservationEntity> reservation = this.visitorReservationDao.queryByStatusAndTime();
            for(VisitorReservationEntity res : reservation){
                res.setUpdateTime(DateUtils.currentDate());
                //变更为已过期
                res.setStatus(String.valueOf(VisitorConstants.ReservationOrderStatus.EXPIRED));
                res.setStatusFlow();
                this.visitorReservationDao.update(res);
            }
        }catch (Exception e){
            log.error(e.toString());
        }

    }

    @Override
    public  void sendSMSPrompt(String beforeHours){
        try{
            List<VisitorReservationEntity> reservation = this.visitorReservationDao.queryByStatusAndTime2(beforeHours);
            //获取待审核的预约单发送短信给受访人
            for(VisitorReservationEntity res : reservation){
                //获取被访人手机号码
                BaseStaffEntity baseStaffEntity = this.baseStaffService.queryObject(res.getStaffId());
                SMSEntity smsEntity = new SMSEntity();
                smsEntity.setMobile(baseStaffEntity.getMobile());//baseStaffEntity.getMobile()
                smsEntity.setSmsType(Constant.SMSType.NOTICE);
                smsEntity.setTemplateCode(Constant.SMSTemplateCode.RESERVATION_NOTICE.getTemplateCode());
                JSONObject templateParam = new JSONObject();
                templateParam.put(VisitorConstants.Keys.HOUR, beforeHours);
                templateParam.put(VisitorConstants.Keys.ORDERNO, res.getReservationNo());
                smsEntity.setTemplateParam(templateParam.toJSONString());
                SendSMSUtils.sendSms(smsEntity);
                res.setIsSendNotice(VisitorConstants.IsSendNotice.YES);
                res.setUpdateTime(DateUtils.currentDate());
                //更新标识
                visitorReservationDao.update(res);
            }
        }catch (Exception e){
            log.error(e.toString());
        }

    }

    @Override
    public List<VisitorReservationEntity> queryByIdcardAndStatus(String idcardNo, String status) {
        return this.visitorReservationDao.queryByIdcardAndStatus(idcardNo, status);
    }

    /**
     * 批量预约
     * @param excelFile
     */
    @Override
    public void batchImportReservation(File excelFile) {
        ImportParams importParams = new ImportParams();
        //设置需要校验
        importParams.setNeedVerfiy(true);
        //1. 第一个 sheet 页（预约单信息）
        importParams.setVerfiyGroup(new Class[]{BatchReservationImportDTO.ReservationImportValidGroup.class});
        importParams.setStartSheetIndex(0);
        ExcelImportResult<BatchReservationImportValidateDTO> reservationImportResult = ExcelImportUtil.importExcelMore(excelFile, BatchReservationImportValidateDTO.class, importParams);
        List<BatchReservationImportValidateDTO> reservationImportList = reservationImportResult.getList();
        log.info("预约信息校验正确的列表：" + JSONObject.toJSONString(reservationImportList));
        log.info("预约信息是否校验失败 = " + reservationImportResult.isVerfiyFail());
        if (ValidateUtils.isNotEmptyCollection(reservationImportResult.getFailList())) {
            reservationImportResult.getFailList().forEach(item -> {
                log.error("错误消息：" + item.getErrorMsg());
                throw new CommonException(item.getErrorMsg());
            });
        }
        if (ValidateUtils.isEmptyCollection(reservationImportList)) {
            log.error("访客信息不能为空!");
            throw new CommonException("访客信息不能为空!");
        }

        //2 第二个sheet 车辆信息
        importParams.setStartSheetIndex(1);
        importParams.setVerfiyGroup(new Class[]{BatchCarInfoImportDTO.CarInfoImportValidGroup.class});
        ExcelImportResult<BatchCarInfoImportValidateDTO> carInfoImportResult = ExcelImportUtil.importExcelMore(excelFile, BatchCarInfoImportValidateDTO.class, importParams);
        List<BatchCarInfoImportValidateDTO> carInfoImportList = carInfoImportResult.getList();
        log.info("车辆信息校验正确的列表：" + JSONObject.toJSONString(carInfoImportList));
        log.info("车辆信息是否校验失败 = " + carInfoImportResult.isVerfiyFail());
        if (ValidateUtils.isNotEmptyCollection(carInfoImportResult.getFailList())) {
            carInfoImportResult.getFailList().forEach(item -> {
                log.error("错误消息：" + item.getErrorMsg());
                throw new CommonException(item.getErrorMsg());
            });
        }

        //3.第三个sheet页 同行人信息
        importParams.setStartSheetIndex(2);
        importParams.setVerfiyGroup(new Class[]{BatchCompanionsImportDTO.CompanionsImportValidGroup.class});
        ExcelImportResult<BatchCompanionsImportValidateDTO> companionsImportResult = ExcelImportUtil.importExcelMore(excelFile, BatchCompanionsImportValidateDTO.class, importParams);
        List<BatchCompanionsImportValidateDTO> companionsImportList = companionsImportResult.getList();
        log.info("同行人信息校验正确的列表：" + JSONObject.toJSONString(companionsImportList));
        log.info("同行人信息是否校验失败 = " + companionsImportResult.isVerfiyFail());
        if (ValidateUtils.isNotEmptyCollection(companionsImportResult.getFailList())) {
            companionsImportResult.getFailList().forEach(item -> {
                log.error("错误消息：" + item.getErrorMsg());
                throw new CommonException(item.getErrorMsg());
            });
        }

        //4.信息入库
        reservationImportList.forEach(item -> {
            BatchReservationOrderDTO reservationOrderDTO = new BatchReservationOrderDTO();
            //设置预约单基本信息
            reservationOrderDTO.setIdcardNo(item.getIdcardNo());
            reservationOrderDTO.setName(item.getName());
            reservationOrderDTO.setAppointStartTime(item.getAppointStartTime());
            reservationOrderDTO.setAppointEndTime(item.getAppointEndTime());
            reservationOrderDTO.setPhone(item.getPhone());
            reservationOrderDTO.setRemark(item.getRemark());
            //被访问人电话,名字,工号
            reservationOrderDTO.setStaffId(item.getStaffId());
            reservationOrderDTO.setStaffName(item.getStaffName());
            reservationOrderDTO.setStaffPhone(item.getStaffPhone());

            //设置车辆信息
            List<CarInfoEntity> carInfoEntityList = new ArrayList<CarInfoEntity>();
            if (ValidateUtils.isNotEmptyCollection(carInfoImportList)) {
                carInfoImportList.forEach(car -> {
                    if(car.getBatchNo().equalsIgnoreCase(item.getBatchNo())){//批次相同
                        CarInfoEntity carInfo = new CarInfoEntity();
                        carInfo.setCarNo(car.getCarNo());
                        carInfo.setName(car.getName());
                        carInfo.setPhone(car.getPhone());
                        carInfoEntityList.add(carInfo);
                    }
                });
            }
            if (ValidateUtils.isNotEmptyCollection(carInfoEntityList)) {
                reservationOrderDTO.setCarInfoEntitys(carInfoEntityList);
            }
            reservationOrderDTO.setIsAddCarInfo(carInfoEntityList.size());

            //设置同行人信息
            List<CompanionsEntity> companionsList = new ArrayList<CompanionsEntity>();
            if (ValidateUtils.isNotEmptyCollection(companionsImportList)) {
                companionsImportList.forEach(companions -> {
                    if(companions.getBatchNo().equalsIgnoreCase(item.getBatchNo())){//批次相同
                        CompanionsEntity companionsEntity = new CompanionsEntity();
                        companionsEntity.setName(companions.getName());
                        companionsEntity.setPhone(companions.getPhone());
                        companionsEntity.setIdcardNo(companions.getIdcardNo());
                        companionsList.add(companionsEntity);
                    }
                });
            }
            if (ValidateUtils.isNotEmptyCollection(companionsList)) {
                reservationOrderDTO.setCompanionsEntitys(companionsList);
            }
            reservationOrderDTO.setCompanions(companionsList.size());

            //保存
            this.createBatchReservationOrder(reservationOrderDTO);

        });
    }
    private void createBatchReservationOrder(BatchReservationOrderDTO batchCeservationOrderDTO) {
        this.validateBatchReservationOrder(batchCeservationOrderDTO);
        ReservationOrderDTO reservationOrderDTO = new ReservationOrderDTO();
        BeanUtil.copyProperties(batchCeservationOrderDTO ,reservationOrderDTO);
        this.saveReservationOrder(reservationOrderDTO);
    }
    /**
     * 校验预约单(批量)
     * @param reservationOrderDTO
     */
    private void validateBatchReservationOrder(BatchReservationOrderDTO reservationOrderDTO) {
        //校验预约结束时间结束时间在开始时间一小时以后
        if(!this.isOneMinuteAfter(reservationOrderDTO.getAppointStartTime(),reservationOrderDTO.getAppointEndTime())){
            throw new CommonException("预约结束时间需要在开始时间一分钟以后！");
        }
        //校验身份证是否合法
        if (!CommonValidator.isIDCard(reservationOrderDTO.getIdcardNo())) {
            throw new CommonException("身份证格式不正确！");
        }
        //校验手机号是否正确
        if (!CommonValidator.isMobile(reservationOrderDTO.getPhone())) {
            throw new CommonException("手机号格式不正确！");
        }
        //校验员工手机号是否存在
        if (ValidateUtils.isEmpty(this.baseStaffService.queryByMobile(reservationOrderDTO.getStaffPhone()))) {
            throw new CommonException("被访人信息不存在！");
        }
    }

    private boolean isOneMinuteAfter(java.util.Date actStartTime, java.util.Date actEndTime){
        int intervalMins = DateUtils.getBetweenMinutes(actStartTime, actEndTime);
        return intervalMins >= 1;
    }

    @Override
    public void uploadVisitorPhoto(MultipartFile file, String reservationOrderId) throws IOException {
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        this.verifyImageFileType(suffix);
        this.verifyImageFileSize(file);
        VisitorIdcardEntity visitorIdcardInfo = this.queryVisitorIdcardByOrderId(reservationOrderId);
        if (ValidateUtils.isEmpty(visitorIdcardInfo)) {
            log.error("访客身份信息为空！");
            throw new CommonException("访客身份信息不存在！");
        }
        SysAttachmentEntity attachmentEntity = this.sysAttachmentService.doUploadFile(file, null);
        visitorIdcardInfo.setIdcardPhotoUrl(attachmentEntity.getUrl());
        this.visitorIdcardService.update(visitorIdcardInfo);
    }

    /**
     * 根据预约单号查询访客身份信息
     * @param reservationOrderId
     * @return
     */
    private VisitorIdcardEntity queryVisitorIdcardByOrderId(String reservationOrderId) {
        List<VisitorIdcardEntity> visitorIdcardList = this.visitorIdcardService.queryList(ImmutableMap.of(VisitorConstants.Keys.RESERVATION_ORDER_ID, reservationOrderId));
        return ValidateUtils.isNotEmptyCollection(visitorIdcardList) ? visitorIdcardList.get(0) : null;
    }

    private void verifyImageFileSize(MultipartFile file) {
        long fileSize = file.getSize() / 1024;
        long imgFileLimitSize = Long.valueOf(this.cacheDataUtils.getConfigValueByKey(VisitorConstants.Keys.IMAGE_FILE_LIMIT_SIZE));
        log.error("fileSize = {}, imgFileLimitSize = {}", fileSize, imgFileLimitSize);
        if (fileSize > imgFileLimitSize) {
            throw new CommonException("文件大小不能超过" + imgFileLimitSize + "KB！");
        }
    }

    private void verifyImageFileType(String suffix) {
        String imageFileType = this.cacheDataUtils.getConfigValueByKey(VisitorConstants.Keys.IMAGE_FILE_TYPE);
        String[] imageFileTypes = imageFileType.split(Constant.Symbol.COMMA);
        boolean result = false;
        for (int i = 0; i < imageFileTypes.length; i++) {
            if (ValidateUtils.equals(imageFileTypes[i], suffix)) {
                result = true;
                break;
            }
        }
        if (!result) {
            throw new CommonException("文件仅支持上传[" + imageFileType + "]图片类型！");
        }
    }

    @Override
    public PageUtils searchReservationOrders(Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        int total = this.visitorReservationDao.countReservationOrders(query);
        List<ReservationOrderQryDTO> resultList = total > 0 ? this.visitorReservationDao.searchReservationOrders(query) : Lists.newArrayList();
        PageUtils pageUtil = new PageUtils(resultList, total, query.getLimit(), query.getPage());
        return pageUtil;
    }
}
