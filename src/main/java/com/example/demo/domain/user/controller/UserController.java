package com.example.demo.domain.user.controller;

import com.example.demo.domain.user.dto.UserInfo;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.global.dto.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.demo.global.dto.code.ResultCode.USER_CREATE_SUCCESS;
import static com.example.demo.global.dto.code.ResultCode.USER_SEARCH_SUCCESS;

@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResultResponse> registerUser(
            @RequestBody @Valid UserInfo.RegisterRequest registerRequest) {
        userService.register(registerRequest);
        return ResponseEntity.ok(ResultResponse.of(USER_CREATE_SUCCESS));
    }

    @GetMapping("{userId}")
    public ResponseEntity<ResultResponse> getUserInfo(@PathVariable Long userId) {
        UserInfo.RegisterResponse response = userService.getUser(userId);
        return ResponseEntity.ok(ResultResponse.of(USER_SEARCH_SUCCESS,response));
    }
}
