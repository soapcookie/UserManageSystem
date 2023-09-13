package com.soapcookie.ums.controller;

import com.soapcookie.ums.dto.RequestDto;
import com.soapcookie.ums.dto.ResponseDto;
import com.soapcookie.ums.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public void createUser(@RequestBody RequestDto requestDto) {
        userService.createUser(requestDto);
    }

    @GetMapping("/{userId}")
    public ResponseDto readUser(@PathVariable Long userId) {
        return userService.readUser(userId);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId, @RequestBody RequestDto requestDto) {
        userService.updateUser(userId, requestDto, userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId, @RequestParam String password) {
        userService.deleteUser(userId, password);
    }
}
