package com.chris.smartpark.ibms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chris.smartpark.common.utils.HttpClientUtils;
import com.chris.smartpark.ibms.entity.WeatherInfoEntity;
import com.chris.smartpark.ibms.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author hewei
 * @version V.1.0.0
 * @date Created in 1:07 2018/10/23
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    /**
     * 日志打印
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @Override
    public WeatherInfoEntity getWeatherInfo(String cityId) {
        WeatherInfoEntity weatherInfoEntity = null;
        String url = "http://www.weather.com.cn/data/cityinfo/" + cityId + ".html";
        String prefix = "http://m.weather.com.cn/img/";
        try {
            String json = HttpClientUtils.get(url);
            LOGGER.info("getWeatherInfo : {}", json);
            JSONObject jsonObject = JSONObject.parseObject(json);
            String weatherInfo = jsonObject.get("weatherinfo").toString();
            weatherInfoEntity = JSONObject.parseObject(weatherInfo, WeatherInfoEntity.class);
            LOGGER.info("weatherInfoEntity : {}", weatherInfoEntity.toString());
            weatherInfoEntity.setImg1(prefix + weatherInfoEntity.getImg1());
            weatherInfoEntity.setImg2(prefix + weatherInfoEntity.getImg2());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Exception occur");
        }
        return weatherInfoEntity;
    }
}
