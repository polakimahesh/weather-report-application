package com.global.weather_report.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    @Query(value = "select u from Users u where u.userName = ?1")
    Users findByUserName(String userName);
}