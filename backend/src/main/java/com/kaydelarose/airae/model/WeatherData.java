package com.kaydelarose.airae.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WeatherData {
    private double temperature;
    private double temperatureF;
    private double feelsLike;
    private double feelsLikeF;
    private String weatherDescription;
    private String weatherIcon;
    private double uvIndex;
    private int humidity;
    private double windSpeed;
    private String windDirection;
    private String airQuality;
    private double dewPoint;
    private int conditionCode;
    private boolean day;
}
