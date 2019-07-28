package com.chris.smartpark.busi.controller;

import com.alibaba.fastjson.JSONObject;
import com.chris.base.common.utils.CommonResponse;
import com.chris.base.modules.app.annotation.Login;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    /**
     * 提供给 APP 端的方法以 app/ 开头
     * @login 注解表示业务需要进行登录认证，如登录淘宝网网页，如长时间不操作，点击我的订单，会先跳转到登录页
     * @param jsonObject
     * @return
     */
    @PostMapping("/app/remoteOpenDoors")
    @Login
    public CommonResponse remoteOpenDoors(JSONObject jsonObject) {
        // do business
        return CommonResponse.ok();
    }
}
