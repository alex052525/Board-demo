package com.example.demo.domain.user.exception;

import com.example.demo.global.dto.code.ErrorCode;
import com.example.demo.global.exception.BusinessException;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}