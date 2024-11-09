package com.kaydelarose.airae.service;

import com.kaydelarose.airae.model.WeatherData;

public interface WeatherService {
    WeatherData getWeatherData(double latitude, double longitude);
}
