package com.global.weather_report.users;

import com.global.weather_report.users.dto.UsersDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.ZonedDateTime;
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String password;
    private String age;
    @CreationTimestamp
    @Column(columnDefinition =  "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
    private ZonedDateTime createdOn;
    @UpdateTimestamp
    @Column(columnDefinition =  "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
    private ZonedDateTime updatedOn;

    public Users(UsersDTO usersDTO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userName = usersDTO.getUserName();
        this.age = usersDTO.getAge();
        this.password = bCryptPasswordEncoder.encode(usersDTO.getPassword());

    }
}
