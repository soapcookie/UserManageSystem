package com.soapcookie.ums.service;

import com.soapcookie.ums.dto.RequestDto;
import com.soapcookie.ums.dto.ResponseDto;

public interface UserService {
    ResponseDto createUser(RequestDto requestDto);
    void readUser(Long id);

    RequestDto updateUser(Long userId, RequestDto requestDto, Long loggedInUserId);

    void deleteUser(Long userId, String password);


}
