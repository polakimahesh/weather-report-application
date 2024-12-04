package com.global.weather_report.users.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDTO {
    @NotBlank(message = "Username required")
    private String userName;
    @NotBlank(message = "Password required")
    private String password;

    @NotBlank(message = "Age required")
    private String age;


}
