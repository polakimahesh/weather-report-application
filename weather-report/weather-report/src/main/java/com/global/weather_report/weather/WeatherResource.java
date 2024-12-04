package com.global.weather_report.weather;

import com.global.weather_report.CommonResponse;
import com.global.weather_report.RestControllerErrorHandler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather-report")
public class WeatherResource implements RestControllerErrorHandler {
    private final WeatherService weatherService;

    public WeatherResource(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/weather-by-ip", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getWeatherByIpaddress(@RequestParam(value = "ip",defaultValue = "123.123.123.123") String ip) {
        CommonResponse response = weatherService.getWeatherByIpaddress(ip);
        return ResponseEntity.status(response.status()).body(response);
    }
}
