package com.global.weather_report.security;

import com.global.weather_report.users.Users;
import com.global.weather_report.users.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService  implements UserDetailsService {
    private final UsersRepository usersRepository;
    public MyUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUserName(username);
        if(users == null ){
            throw  new UsernameNotFoundException("User not found!");
        }
        return new UserPrinciples(users);

    }

}
