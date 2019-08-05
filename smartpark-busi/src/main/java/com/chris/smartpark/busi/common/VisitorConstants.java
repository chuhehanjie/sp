package com.chris.smartpark.busi.common;

public class VisitorConstants {

    public interface SendDoorCtrlFlag {
        /**
         * 送门禁开关 送
         */
        String YES = "true";        /**
         * 不送
         */
        String NO = "false";
    }
    public interface IsSendNotice {
        /**
         * 是否已发送短信给受访者
         */
        int YES = 1;
        /**
         * 是否已发送短信给受访者
         */
        int NO = 0;

    }
    public interface ApproveResult {
        /**
         * 审核通过
         */
        int OK = 1;
        /**
         * 审核不通过
         */
        int REJECT = 0;
    }

    /**
     * 预约单状态
     */
    public interface ReservationOrderStatus {
        /**
         * 待审核
         */
        int PENDING_APPROVE = 1;
        /**
         * 审核不通过
         */
        int APPROVE_REJECT = 2;
        /**
         * 审核通过
         */
        int APPROVE_OK = 3;
        /**
         * 已完成
         */
        int COMPLETED = 4;
        /**
         * 已过期
         */
        int EXPIRED = 5;
    }

    /**
     * 预约单类型
     */
    public interface ReservationOrderType {
        /**
         * 线上
         */
        int ONLINE = 1;
        /**
         * 线下
         */
        int OFFLINE = 2;
    }
    /**
     * 是否为
     */
    public interface isLocalappoint {
        /**
         * 线上
         */
        int ONLINE = 0;
        /**
         * 线下
         */
        int OFFLINE = 1;
    }

    /**
     * 默认授权标识
     */
    public interface DefaultAuthorizedFlag {
        /**
         * 是
         */
        int YES = 1;
        /**
         * 否
         */
        int NO = 1;
    }

    public interface Keys {
        String OPEN_ID = "openId";
        String FORM_ID = "formId";
        String USED = "used";
        String PAGE = "page";
        String LIMIT = "limit";
        String IDCARD_NO = "idcardNo";
        String TIME = "time";
        String PARK_NAME = "parkName";
        String REJECT_REASON = "reason";
        String RESERVATION_ORDER_ID = "reservationId";
        String TYPE = "type";
        String HOUR = "hour";
        String ORDERNO = "orderNo";
        String VISITOR_ID = "visitorId";
        String IMAGE_FILE_TYPE = "IMAGE_FILE_TYPE";
        String IMAGE_FILE_LIMIT_SIZE = "IMAGE_FILE_LIMIT_SIZE";
        String DOOR_ID = "doorId";
        String ESB_SERVER = "ESB_SERVER";
        String KEY_WORD = "keyword";
        String BUILDING = "building";
        String DOOR_CTRL_PROVIDER = "DOOR_CTRL_PROVIDER";
    }


    public interface Page {
        int PAGE_NO = 1;
        int PAGE_SIZE = 10;
    }

    public interface DoorReaderNo {
        int NO_ZERO = 0;
        int NO_ONE = 1;
        int NO_TWO = 2;
        int NO_THREEE = 3;
    }

    public interface OpenDoorCommand {
        String SUCCESS = "00";
    }

    public interface DoorType {
        int PUBLIC = 1;
        int PRIVATE = 2;
    }

    public interface OpenDoorResult {
        String SUCCESS = "1";
        String FAILURE = "0";
        String DESC_SUCCESS = "成功";
        String DESC_FAILURE = "失败";
    }

    public interface Action {
        int CREATE = 1;
        int MODIFY = 2;
    }

    /**
     * 开门类型
     * 1、正常开门
     * 2、远程开门
     */
    public interface OpenDoorType {
        int NORMAL = 1;
        int REMOTE = 2;
    }
}
