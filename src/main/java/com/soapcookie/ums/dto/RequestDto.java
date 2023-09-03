package com.soapcookie.ums.dto;

import com.soapcookie.ums.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class RequestDto {
    private String name;
    private String password;
    private String email;

    @Builder
    public RequestDto(String name, String password, String email) {
        this.name = name;
        setPassword(password);
        this.email = email;
    }

    public User toEntity() {
        return User.builder()
                .name(this.name)
                .password(this.password)
                .email(this.email)
                .build();
    }
}
