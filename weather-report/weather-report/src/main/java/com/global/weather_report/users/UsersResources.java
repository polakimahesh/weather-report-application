package com.global.weather_report.users;

import com.global.weather_report.RestControllerErrorHandler;
import com.global.weather_report.users.dto.LoginDTO;
import com.global.weather_report.users.dto.UsersDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UsersResources implements RestControllerErrorHandler {
    private final UserServices userServices;

    public UsersResources(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@RequestBody @Valid UsersDTO usersDTO) {
        userServices.create(usersDTO);
        return ResponseEntity.ok("Created Successfully!");
    }
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody @Valid LoginDTO usersDTO) {
        String verifyUser = userServices.verifyUser(usersDTO);
        Map<String,String> response = new HashMap<>();
        response.put("Jwt_Token",verifyUser);
        return ResponseEntity.ok(response);
    }

}
