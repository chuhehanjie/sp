package com.chris.smartpark.ibms.controller;

import com.chris.base.common.utils.CommonResponse;
import com.chris.smartpark.ibms.entity.IBMSDetailEntity;
import com.chris.smartpark.ibms.service.IBMSService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hewei
 * @version V.1.0.0
 * @date Created in 15:40 2018/10/30
 */
@RestController
@RequestMapping("/ibms")
public class IBMSController {

    @Autowired
    private IBMSService ibmsService;

    @RequestMapping(value = "/detail.notoken", method = RequestMethod.POST)
    @ApiOperation(value = "首页详情展示接口", notes = "首页详情展示接口")
    public CommonResponse detail() {
        IBMSDetailEntity detail = ibmsService.detail();
        return CommonResponse.ok().setData(detail);
    }
}
