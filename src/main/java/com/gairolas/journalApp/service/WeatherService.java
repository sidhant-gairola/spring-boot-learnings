package com.gairolas.journalApp.service;

import com.gairolas.journalApp.api.response.WeatherResponse;
import com.gairolas.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")    // to fetch value of mapped key from .yml file
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;  // to send request directly from spring instead of from postman

    @Autowired
    private AppCache appCache;  // to store the journal entries in a local storage such as map for faster accessibility

    @Autowired
    private RedisService redisService;  // to use redis services/methods such as get, set, etc.

    public WeatherResponse getWeather(String city) {
        String key = "weather_of_" + city.toLowerCase();
        WeatherResponse weatherResponse = redisService.get(key, WeatherResponse.class);// key and what type of data is sent as a second parameter
        if (weatherResponse != null) {
            return weatherResponse;
        } else {
            String finalAPI = appCache.appCache.get("weather_api").replace("<apiKey>", apiKey).replace("<city>", city);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if (body != null) {
                redisService.set(key, body, 300l);
            }
            return body;
        }
    }
}
