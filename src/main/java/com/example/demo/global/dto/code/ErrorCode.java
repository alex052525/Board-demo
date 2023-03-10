package com.example.demo.global.dto.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Global
    INTERNAL_SERVER_ERROR(500, "G001", "서버 오류"),
    TOO_MANY_CREATE_BOARD(429, "G002", "조금 있다가 게시물을 생성해주세요"),

    // USER
    USER_NOT_FOUND(400, "U001", "유저를 찾을 수 없습니다."),

    // BOARD
    BOARD_NOT_FOUND(400, "B001", "게시물을 찾을 수 없습니다."),
    CANT_UPDATE_OTHERS_BOARD(401, "B002", "다른 사람의 게시물을 수정할 수 없습니다."),
    ;

    private final int status;
    private final String code;
    private final String message;
}
