package com.chris.smartpark.busi.common;

import com.alibaba.fastjson.JSONObject;
import com.chris.base.common.utils.*;
import com.chris.base.common.wx.dto.WXMsgTempSendDTO;
import com.chris.base.common.wx.service.WXService;
import com.chris.base.modules.app.service.UserService;
import com.chris.smartpark.base.entity.BaseStaffEntity;
import com.chris.smartpark.base.service.BaseStaffService;
import com.chris.smartpark.busi.dto.ReservationOrderApproveDTO;
import com.chris.smartpark.busi.dto.WXFormIdMarker;
import com.chris.smartpark.busi.entity.VisitorInfoEntity;
import com.chris.smartpark.busi.entity.VisitorReservationEntity;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class WXNoticeMsgUtils {

    @Autowired
    private WXService wxService;

    @Autowired
    private UserService userService;

    @Autowired
    private CacheDataUtils cacheDataUtils;

    @Autowired
    private BaseStaffService staffService;

    public static final long WX_FORM_ID_EXPIRE = 604800L;
    public static final String WX_FORM_ID_PREFIX = "WX:FORMID:";
    private static final String WX_TEMP_ID_ORDER_CREATED = "SIuQgDBJxvxZTlTJMhjP-hoJ_fHUcIdoP43WyroBFR4";
    private static final String WX_TEMP_ID_ORDER_ACCEPT = "ACIneEtrq_Qd1plikRjksShw0LXUa6go7xE56M5rul4";
    private static final String WX_TEMP_ID_ORDER_APPROVE_RESULT = "tyO1NzleQYuI6uwPR0b72xN3RpBRVtKCHQ8FP4tIoTY";

    /**
     * 发送预约单受理消息给员工
     * @param reservationOrder
     * @param visitorInfo
     */
    public void sendOrderAccpetMsg2Staff(VisitorReservationEntity reservationOrder, VisitorInfoEntity visitorInfo, String formId) {
        // 模板消息推送
        JSONObject msg = new JSONObject();
        //访客姓名
        msg.put("keyword1", ImmutableMap.of("value", visitorInfo.getName()));
        //预约单号
        msg.put("keyword2", ImmutableMap.of("value", reservationOrder.getReservationNo()));
        //来访时间
        msg.put("keyword3", ImmutableMap.of("value", this.buildVisitTime(reservationOrder)));
        //来访事由
        msg.put("keyword4", ImmutableMap.of("value", reservationOrder.getRemark()));
        reservationOrder.setExt3(this.getStaffOpenIdByMobile(reservationOrder.getStaffMobile()));
        String staffFormId = getFormIdFromRedis(reservationOrder.getExt3());
        if (ValidateUtils.isEmptyString(staffFormId)) {
            log.error("未获取到员工可用的formid，无法发送预约单受理消息给员工");
            return;
        }
        this.wxService.sendWXMsgTemp(WXMsgTempSendDTO.builder().form_id(staffFormId).template_id(WX_TEMP_ID_ORDER_ACCEPT).
                        touser(reservationOrder.getExt3()).page("/staff/pages/visit/info?id=" + reservationOrder.getId()).data(msg).build(),
                this.cacheDataUtils.getConfigValueByKey("WX_APP_ID"), this.cacheDataUtils.getConfigValueByKey("WX_APP_SECRET"));
        log.info("发送预约单受理通知消息给员工！");
    }

    public static String getFormIdFromRedis(String openId) {
        RedisUtils redisUtils = (RedisUtils) SpringContextUtils.getBean("redisUtils");
        String key = WX_FORM_ID_PREFIX + openId;
        List<Map<String, Object>> list = redisUtils.get(key, List.class);
        String formId = null;
        if (ValidateUtils.isEmptyCollection(list)) {
            log.error("缓存中不存在对应的formId");
        } else {
            for (Map<String, Object> item : list) {
                WXFormIdMarker formIdMarker = convertMap2WXFormIdMarker(item);
                if (!formIdMarker.isUsed()) {
                    formId = formIdMarker.getFormId();
                    formIdMarker.setUsed(true);
                    item.put(VisitorConstants.Keys.USED, Boolean.TRUE);
                    log.info("取出的 formId = {}", formId);
                    break;
                }
            }
            redisUtils.set(key, list, WX_FORM_ID_EXPIRE);
        }
        return formId;
    }

    public static void saveFormId(String openId, String formId) {
        RedisUtils redisUtils = (RedisUtils) SpringContextUtils.getBean("redisUtils");
        String key = WX_FORM_ID_PREFIX + openId;
        List<Map<String, Object>> list = redisUtils.get(key, List.class);
        if (ValidateUtils.isEmptyCollection(list)) {
            list = Lists.newArrayList();
        }
        if (ValidateUtils.equals("the formId is a mock one", formId)) {
            log.error("formid 值是模拟的");
            return;
        }
        list.add(convertWXFormIdMarker2Map(new WXFormIdMarker(formId, false)));
        redisUtils.set(key, list, WX_FORM_ID_EXPIRE);
    }

    private static WXFormIdMarker convertMap2WXFormIdMarker(Map<String, Object> item) {
        return new WXFormIdMarker(item.get(VisitorConstants.Keys.FORM_ID).toString(), (Boolean) item.get(VisitorConstants.Keys.USED));
    }

    private static Map<String,Object> convertWXFormIdMarker2Map(WXFormIdMarker wxFormIdMarker) {
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put(VisitorConstants.Keys.FORM_ID, wxFormIdMarker.getFormId());
        resultMap.put(VisitorConstants.Keys.USED, wxFormIdMarker.isUsed());
        return resultMap;
    }

    /**
     * 发送预约单创建成功消息给访客
     * @param reservationOrder
     */
    public void sendOrderCreatedMsg2Visitor(VisitorReservationEntity reservationOrder, String formId) {
        // 模板消息推送
        JSONObject msg = new JSONObject();
        BaseStaffEntity staff = this.staffService.queryObject(reservationOrder.getStaffId());
        //被访人
        msg.put("keyword1", ImmutableMap.of("value", staff.getUsername()));
        //被访人单位 TODO 暂时写死
        msg.put("keyword2", ImmutableMap.of("value", "中国通信服务湖南分公司"));
        //来访事由
        msg.put("keyword3", ImmutableMap.of("value", reservationOrder.getRemark()));
        //来访时间
        msg.put("keyword4", ImmutableMap.of("value", this.buildVisitTime(reservationOrder)));
        this.wxService.sendWXMsgTemp(WXMsgTempSendDTO.builder().form_id(formId).template_id(WX_TEMP_ID_ORDER_CREATED).
                        touser(reservationOrder.getOpenId()).page("/pages/visitor/info?id=" + reservationOrder.getId()).data(msg).build(),
                this.cacheDataUtils.getConfigValueByKey("WX_APP_ID"), this.cacheDataUtils.getConfigValueByKey("WX_APP_SECRET"));
        log.info("发送预约单创建成功消息给访客！");
    }

    /**
     * 发送预约单审核结果消息给访客
     * @param reservationOrder
     * @param authorizeDTO
     */
    public void sendOrderApproveResultMsg2Visitor(VisitorReservationEntity reservationOrder, ReservationOrderApproveDTO authorizeDTO) {
        // 模板消息推送
        JSONObject msg = new JSONObject();
        msg.put("keyword1", ImmutableMap.of("value", ValidateUtils.equals(VisitorConstants.ApproveResult.OK, authorizeDTO.getApproveReslut()) ? "审核通过" : "审核不通过"));
        msg.put("keyword2", ImmutableMap.of("value", reservationOrder.getReservationNo()));
        String formId = this.getFormIdFromRedis(reservationOrder.getOpenId());
        if (ValidateUtils.isEmptyString(formId)) {
            log.error("未获取到访客可用的formid，无法发送审核结果给访客");
            return;
        }
        this.wxService.sendWXMsgTemp(WXMsgTempSendDTO.builder().form_id(formId).template_id(WX_TEMP_ID_ORDER_APPROVE_RESULT).
                        touser(reservationOrder.getOpenId()).page("/pages/visitor/info?id=" + reservationOrder.getId()).data(msg).build(),
                this.cacheDataUtils.getConfigValueByKey("WX_APP_ID"), this.cacheDataUtils.getConfigValueByKey("WX_APP_SECRET"));
        log.info("发送预约单审核结果消息给访客！");
    }

    private String buildVisitTime(VisitorReservationEntity reservationOrder) {
        return DateUtils.format(reservationOrder.getAppointStartTime(), DateUtils.DATE_TIME_CHN_PATTERN) + " 至 " +
                DateUtils.format(reservationOrder.getAppointEndTime(), DateUtils.DATE_TIME_CHN_PATTERN);
    }

    private String getStaffOpenIdByMobile(String mobile) {
        return this.userService.queryByMobile(mobile).getOpenId();
    }
}
