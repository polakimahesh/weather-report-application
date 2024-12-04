package com.global.weather_report.weather.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherDTO {
    public double temperature;
    public int humidity;
    public String description;

    public WeatherDTO(WeatherDescriptionDTO weatherDescriptionDTO, MainDTO main) {
        this.temperature = main.getTemp();
        this.humidity = main.getHumidity();
        this.description = weatherDescriptionDTO.getDescription();
    }
}
