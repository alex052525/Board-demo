package com.example.demo.domain.user.dto;

import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.entity.UserRole;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserInfo {
    @Getter
    @Setter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class RegisterRequest {

        @Pattern(message = "대소문자나 숫자를 포함한 5-20자리로 입력하세요.",regexp = "^[A-Za-z0-9]{5,20}$")
        private String name;

        @NotBlank(message = "유저 권한을 입력해주세요")
        private UserRole userRole;

//        @Builder.Default private String bio = "";
    }
    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class RegisterResponse {
        private String name;
        private UserRole userRole;
    }
}
