package com.chris.smartpark.ibms.controller;

import com.chris.base.common.utils.CommonResponse;
import com.chris.base.common.utils.ValidateUtils;
import com.chris.smartpark.ibms.entity.WeatherInfoEntity;
import com.chris.smartpark.ibms.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hewei
 * @version V.1.0.0
 * @date Created in 0:52 2018/10/23
 */
@RestController
@RequestMapping("/ibms/weather")
@Api("天气接口")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    /**
     * 查询天气
     */
    @RequestMapping(value = "/getWeatherInfo.notoken", method = RequestMethod.POST)
    @ApiOperation(value = "查询天气", notes = "查询天气")
    public CommonResponse getWeatherInfo(String cityId) {
        if (ValidateUtils.isEmpty(cityId)) {
            cityId = "101250101";
        }
        WeatherInfoEntity weatherInfo = weatherService.getWeatherInfo(cityId);
        return CommonResponse.ok().setData(weatherInfo);
    }
}
