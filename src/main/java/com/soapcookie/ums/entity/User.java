package com.soapcookie.ums.entity;

import lombok.AccessLevel;
import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue
    @Column(name="user_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(length = 255, nullable = false)
    private String email;

    @Builder
    public User(String name, String password, String email){
        this.name = name;
        setPassword(password);
        this.email = email;
    }


    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }




}
