package com.global.weather_report.weather;

import com.global.weather_report.CommonResponse;
import com.global.weather_report.http.HttpRequestService;
import com.global.weather_report.http.ResponseDTO;
import com.global.weather_report.weather.dto.*;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class WeatherService {
    private final HttpRequestService httpRequestService;
    private final Gson gson;
    @Value("${weather.api.url}")
    private String weatherApiUrl;
    @Value("${ipstack.api.url}")
    private String ipstackApiUrl;
    @Value("${weather.api.key}")
    private String weatherApiKey;
    @Value("${ipstack.api.key}")
    private String ipstackApiKey;
    Map<String,Object> emptyResponse = new HashMap<>();
    public WeatherService(HttpRequestService httpRequestService, Gson gson) {
        this.httpRequestService = httpRequestService;
        this.gson = gson;
    }

    public CommonResponse getWeatherByIpaddress(String ip) {
        CommonResponse commonResponse;
        ResponseDTO ipStackResponse =httpRequestService.getRequest(ipstackApiUrl+ip+"?access_key="+ipstackApiKey);
        if(ipStackResponse.getStatusCode() ==200){
            IpStackResponseDTO ipStackResponseDTO = gson.fromJson(ipStackResponse.getResponse(), IpStackResponseDTO.class);
            System.out.println(weatherApiUrl+"weather?q="+ipStackResponseDTO.getCity()+"&appid="+weatherApiKey);
            ResponseDTO weatherResponse =httpRequestService
                    .getRequest(weatherApiUrl+"weather?q="+ipStackResponseDTO.getCity()+"&appid="+weatherApiKey);
            if(weatherResponse.getStatusCode() == 200){
                WeatherLocationResponseDTO weatherLocationResponseDTO = new WeatherLocationResponseDTO();
                WeatherResponseDTO weatherResponseDTO = gson.fromJson(weatherResponse.getResponse(), WeatherResponseDTO.class);
                WeatherDescriptionDTO weatherDescriptionDTO = weatherResponseDTO.getWeather().get(0);
                weatherLocationResponseDTO.setIp(ipStackResponseDTO.getIp());
                weatherLocationResponseDTO.setLocation(new LocationDTO(ipStackResponseDTO));
                weatherLocationResponseDTO.setWeather(new WeatherDTO(weatherDescriptionDTO,weatherResponseDTO.getMain()));
                commonResponse = new CommonResponse(weatherLocationResponseDTO,"success",200);
            }else{
                commonResponse = new CommonResponse(emptyResponse,"invalid weather response!",400);
            }
        }else{
            commonResponse = new CommonResponse(emptyResponse,"invalid ip-stack response!",400);
        }
        return commonResponse;
    }
}
