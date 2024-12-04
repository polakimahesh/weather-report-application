package com.global.weather_report.weather.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDTO {
    public String city;
    public String country;

    public LocationDTO(IpStackResponseDTO ipStackResponseDTO) {
        this.city = ipStackResponseDTO.getCity();
        this.country = ipStackResponseDTO.getCountryName();
    }
}
