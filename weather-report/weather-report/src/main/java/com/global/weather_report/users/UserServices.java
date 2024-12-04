package com.global.weather_report.users;

import com.global.weather_report.jwt.JwtService;
import com.global.weather_report.users.dto.LoginDTO;
import com.global.weather_report.users.dto.UsersDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public UserServices(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                        AuthenticationManager authenticationManager, JwtService jwtService) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }

    public void create(UsersDTO usersDTO) {
        Users users = new Users(usersDTO,bCryptPasswordEncoder);
        usersRepository.save(users);
    }


    public String verifyUser(LoginDTO usersDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(usersDTO.getUserName(),usersDTO.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(usersDTO.getUserName());
        }else{
            return "Invalid Details!";
        }
    }

}
