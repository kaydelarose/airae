package com.kaydelarose.airae.service.impl;

import com.kaydelarose.airae.model.WeatherData;
import com.kaydelarose.airae.service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class WeatherServiceImpl implements WeatherService {


    @Value("${weather.api}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherData getWeatherData(double latitude, double longitude) {
        WeatherData weatherData = new WeatherData();
        try {
            Map<String, Object> response = fetchCurrentWeatherData(latitude, longitude);
            parseWeatherResponse(response, weatherData);

        } catch (Exception e) {
            System.err.println("Error fetching weather data: " + e.getMessage());
        }

        return weatherData;
    }

    private Map<String, Object> fetchCurrentWeatherData(double latitude, double longitude) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.weatherapi.com/v1/current.json")
                .queryParam("key", apiKey)
                .queryParam("q", latitude + "," + longitude)
                .build().toUriString();
        return restTemplate.getForObject(url, Map.class);
    }

    private void parseWeatherResponse(Map<String, Object> response, WeatherData weatherData) {
        if (response == null) return;

        Map<String, Object> current = (Map<String, Object>) response.get("current");
        Map<String, Object> condition = (Map<String, Object>) current.get("condition");

        weatherData.setTemperature((Double) current.get("temp_c"));
        weatherData.setTemperatureF((Double) current.get("temp_f"));
        weatherData.setFeelsLike((Double) current.get("feelslike_c"));
        weatherData.setFeelsLikeF((Double) current.get("feelslike_f"));
        weatherData.setWeatherDescription((String) condition.get("text"));
        weatherData.setWeatherIcon((String) condition.get("icon"));
        weatherData.setUvIndex((Double) current.get("uv"));
        weatherData.setHumidity((Integer) current.get("humidity"));
        weatherData.setWindSpeed((Double) current.get("wind_kph"));
        weatherData.setWindDirection((String) current.get("wind_dir"));
        weatherData.setAirQuality("Good");
        weatherData.setConditionCode((Integer) condition.get("code"));
        weatherData.setDay(((Integer) current.get("is_day")) == 1);
    }

}
