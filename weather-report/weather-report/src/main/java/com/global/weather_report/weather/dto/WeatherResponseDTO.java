package com.global.weather_report.weather.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponseDTO {
    public MainDTO main;
    public List<WeatherDescriptionDTO> weather;
}
