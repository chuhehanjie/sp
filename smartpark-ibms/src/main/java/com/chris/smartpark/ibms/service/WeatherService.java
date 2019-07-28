package com.chris.smartpark.ibms.service;

import com.chris.smartpark.ibms.entity.WeatherInfoEntity;

/**
 * @author hewei
 * @version V.1.0.0
 * @date Created in 1:02 2018/10/23
 */
public interface WeatherService {

    WeatherInfoEntity getWeatherInfo(String cityId);
}
