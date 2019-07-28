package com.chris.smartpark.busi.common;

import com.chris.base.common.utils.*;
import com.chris.base.modules.app.cache.AppLoginUser;
import com.chris.base.modules.app.cache.AppLoginUserCacheUtils;

public final class VisitorUtils {

    public static boolean isVerifyCodeOK(String mobile, String verifyCode, String tempCode) {
        return ValidateUtils.equals(verifyCode, SendSMSUtils.getVerifyCode4App(mobile, tempCode));
    }
    public static String getReservationNo(){
        StringBuilder sb = new StringBuilder();
        sb.append("YYD").append(DateUtils.currentDateStr("yyyyMMddHHmmss"));
        return  sb.toString();
    }

    /**
     * 16进制字符串转 int
     * @param str
     * @return
     */
    public static int hex2Int(String str) {
        int result = 0;
        //str.toCharArray()把String类的str转换成字符数组
        char[] hex = str.toCharArray();
        for(int i = 0; i < hex.length; i++){
            if(getHexValue(hex[i]) != -1){
                result += getHexValue(hex[i]) * Math.pow(16, hex.length-i-1);
            }
            else {
                return -1;
            }
        }
        return result;
    }

    private static int getHexValue(char ch){
        if(ch >= '0' && ch <= '9'){
            return Integer.parseInt(String.valueOf(ch));
        }
        if ( (ch >= 'a'  && ch <= 'f') || (ch >= 'A' && ch <= 'F')) {
            switch (ch) {
                case 'a':
                case 'A':
                    //这里不用break是因为执行了return以后就不会再往下执行了
                    return 10;
                case 'b':
                case 'B':
                    return 11;
                case 'c':
                case 'C':
                    return 12;
                case 'd':
                case 'D':
                    return 13;
                case 'e':
                case 'E':
                    return 14;
                case 'f':
                case 'F':
                    return 15;
            }
        }
        return -1;
    }

    /**
     * 是否为管理员角色
     * @param openId
     * @return
     */
    public static boolean isAdminRole(String openId) {
        AppLoginUser loginUser = AppLoginUserCacheUtils.getAppLoginUser(openId);
        if (ValidateUtils.isNotEmpty(loginUser)) {
            return ValidateUtils.equals(Constant.WXRole.ADMIN, loginUser.getRoleId());
        }
        return false;
    }

    private static final String REG_CAR_NO = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[警京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{0,1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";

    /**
     * 是否有效的车牌号
     * 1.常规车牌号：仅允许以汉字开头，后面可录入六个字符，由大写英文字母和阿拉伯数字组成。如：粤B12345；
     * 2.武警车牌：允许前两位为大写英文字母，后面可录入五个或六个字符，由大写英文字母和阿拉伯数字组成，其中第三位可录汉字也可录大写英文字母及阿拉伯数字，第三位也可空，如：WJ警00081、WJ京1234J、WJ1234X。
     * 3.最后一个为汉字的车牌：允许以汉字开头，后面可录入六个字符，前五位字符，由大写英文字母和阿拉伯数字组成，而最后一个字符为汉字，汉字包括“挂”、“学”、“警”、“军”、“港”、“澳”。如：粤Z1234港。
     * 4.新军车牌：以两位为大写英文字母开头，后面以5位阿拉伯数字组成。如：BA12345。
     * @param carNo
     * @return
     */
    public static boolean isValidCarNo(String carNo) {
        if (ValidateUtils.isEmptyString(carNo)) {
            return false;
        } else {
            return carNo.matches(REG_CAR_NO);
        }
    }

    public static boolean isCosonDoorCtrl() {
        /*CacheDataUtils cacheDataUtils = (CacheDataUtils) SpringContextUtils.getBean("cacheDataUtils");
        return ValidateUtils.equals("cosonDoorCtrlProvider", cacheDataUtils.getConfigValueByKey(VisitorConstants.Keys.DOOR_CTRL_PROVIDER));*/
        return true;
    }
}
