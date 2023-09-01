package com.soapcookie.ums.dto;

import com.soapcookie.ums.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class RequestDto {
    private String name;
    private String password;
    private String email;
}

