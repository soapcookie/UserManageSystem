package com.soapcookie.ums.dto;

import com.soapcookie.ums.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDto {
    private Long id;
    private String name;
    private String email;

    public static ResponseDto fromEntity(User user) {
        ResponseDto dto = new ResponseDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}

