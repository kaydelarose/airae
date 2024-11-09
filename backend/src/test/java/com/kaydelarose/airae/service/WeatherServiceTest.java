package com.kaydelarose.airae.service;

import com.kaydelarose.airae.model.WeatherData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherService weatherService;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(weatherService, "apiKey", "test_api_key");
    }

    @Test
    public void testGetWeatherData_currentWeather() {
        // Mock current weather response
        Map<String, Object> main = new HashMap<>();
        main.put("temp", 20.0);
        main.put("humidity", 60);

        Map<String, Object> wind = new HashMap<>();
        wind.put("speed", 5.5);

        Map<String, Object> weather = new HashMap<>();
        weather.put("description", "clear sky");

        List<Map<String, Object>> weatherList = Collections.singletonList(weather);

        Map<String, Object> currentWeatherResponse = new HashMap<>();
        currentWeatherResponse.put("main", main);
        currentWeatherResponse.put("wind", wind);
        currentWeatherResponse.put("weather", weatherList);

        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(currentWeatherResponse);

        WeatherData weatherData = weatherService.getWeatherData(37.7749, -122.4194);

        assertEquals(20.0, weatherData.getTemperature());
        assertEquals(60, weatherData.getHumidity());
        assertEquals(5.5, weatherData.getWindSpeed());
        assertEquals("clear sky", weatherData.getWeatherDescription());
    }

    @Test
    public void testGetWeatherData_airQuality() {
        Map<String, Object> main = new HashMap<>();
        main.put("aqi", 3);

        List<Map<String, Object>> list = Collections.singletonList(Collections.singletonMap("main", main));
        Map<String, Object> airQualityResponse = new HashMap<>();
        airQualityResponse.put("list", list);

        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(airQualityResponse);

        WeatherData weatherData = weatherService.getWeatherData(37.7749, -122.4194);

        assertEquals(3, weatherData.getAirQuality());
    }

    @Test
    public void testGetWeatherData_oneCall() {
        Map<String, Object> current = new HashMap<>();
        current.put("uvi", 7);
        current.put("dew_point", 10.5);

        Map<String, Object> oneCallResponse = new HashMap<>();
        oneCallResponse.put("current", current);

        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(oneCallResponse);

        WeatherData weatherData = weatherService.getWeatherData(37.7749, -122.4194);

        assertEquals(7, weatherData.getUvIndex());
        assertEquals(10.5, weatherData.getDewPoint());
    }
}
