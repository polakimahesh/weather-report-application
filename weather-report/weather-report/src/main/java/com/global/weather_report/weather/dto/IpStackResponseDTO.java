package com.global.weather_report.weather.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IpStackResponseDTO {
    private String ip;
    @SerializedName("country_name")
    private String countryName;
    private String city;

}
