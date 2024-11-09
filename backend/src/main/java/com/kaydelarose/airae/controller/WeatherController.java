package com.kaydelarose.airae.controller;

import com.kaydelarose.airae.model.WeatherData;
import com.kaydelarose.airae.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public WeatherData getWeather(@RequestParam double latitude, @RequestParam double longitude) {
        return weatherService.getWeatherData(latitude, longitude);
    }
}
