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

        @NotBlank(message = "아이디는 필수 입력 값입니다.")
        private String name;

//        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
//        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
//        private String password;

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
