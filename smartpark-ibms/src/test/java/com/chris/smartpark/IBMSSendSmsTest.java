package com.chris.smartpark;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.chris.IBMSApplication;
import com.chris.base.common.utils.Constant;
import com.chris.base.common.utils.SendSMSUtils;
import com.chris.base.modules.sms.entity.SMSEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hewei
 * @version V.1.0.0
 * @date Created in 17:04 2018/11/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IBMSApplication.class)
public class IBMSSendSmsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(IBMSSendSmsTest.class);

    /**
     * 验证码短信发送测试
     */
    @Test
    public void sendVerifyCode() {
        JSONObject jsonObject = new JSONObject();
        String mobile = "13866135563";
        //code 传int类型 表示生成的验证码位数
        jsonObject.put("code", 4);
        String templateParam = jsonObject.toJSONString();
        // smsType  验证码类的短信传Constant.SMSType.VERIFY  通知类的短信传NOTICE
        // templateCode取值如下:
        // RESERVATION_FAIL   预约审核失败短信模板,
        // RESERVATION_SUCCESS  预约审核成功短信模板,
        // RESERVATION_HANDLE  预约处理短信模板,
        // RESERVATION_VERIFY_CODE 预约验证码短信模板,
        // REGISTER_VERIFY_CODE  注册验证码短信模板;
        SMSEntity smsEntity = new SMSEntity();
        smsEntity.setMobile(mobile);
        smsEntity.setSmsType(Constant.SMSType.VERIFY);
        smsEntity.setTemplateParam(templateParam);
        smsEntity.setTemplateCode(Constant.SMSTemplateCode.REGISTER_VERIFY_CODE.getTemplateCode());
        SendSmsResponse sendSmsResponse = SendSMSUtils.sendSms(smsEntity);
        //获取短信发送的验证码
        String verifyCode = SendSMSUtils.getVerifyCode(mobile);
        LOGGER.info("sendVerifyCode sendSmsResponse :{}", JSONObject.toJSONString(sendSmsResponse));
        LOGGER.info("sendVerifyCode verifyCode:{}", verifyCode);
    }

    /**
     * 通知类短信发送测试
     */
    @Test
    public void sendNotice() {
        JSONObject jsonObject = new JSONObject();
        String mobile = "13866135563";
        jsonObject.put("reason", "由于用户拒绝");
        String templateParam = jsonObject.toJSONString();
        SMSEntity smsEntity = new SMSEntity();
        smsEntity.setMobile(mobile);
        smsEntity.setSmsType(Constant.SMSType.NOTICE);
        smsEntity.setTemplateParam(templateParam);
        smsEntity.setTemplateCode(Constant.SMSTemplateCode.RESERVATION_FAIL.getTemplateCode());
        SendSmsResponse sendSmsResponse = SendSMSUtils.sendSms(smsEntity);
        LOGGER.info("sendVerifyCode sendSmsResponse :{}", JSONObject.toJSONString(sendSmsResponse));
    }

    /**
     * 批量短信发送测试
     */
    @Test
    public void sendBatchSms() {
        String mobile = "13866135563,18874023514";
        SMSEntity smsEntity = new SMSEntity();
        smsEntity.setMobile(mobile);
        smsEntity.setSmsType(Constant.SMSType.NOTICE);
        smsEntity.setTemplateCode(Constant.SMSTemplateCode.RESERVATION_HANDLE.getTemplateCode());
        SendSmsResponse sendSmsResponse = SendSMSUtils.sendSms(smsEntity);
        LOGGER.info("sendVerifyCode sendSmsResponse :{}", JSONObject.toJSONString(sendSmsResponse));
    }

}
