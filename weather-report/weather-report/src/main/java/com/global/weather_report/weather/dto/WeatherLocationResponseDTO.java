package com.global.weather_report.weather.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherLocationResponseDTO {
    public String ip;
    public LocationDTO location;
    public WeatherDTO weather;

}
