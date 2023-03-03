package com.example.demo.domain.board.exception;

import com.example.demo.domain.board.entity.Board;
import com.example.demo.global.dto.code.ErrorCode;
import com.example.demo.global.exception.BusinessException;

public class BoardNotFoundException extends BusinessException {
    public BoardNotFoundException() {
        super(ErrorCode.BOARD_NOT_FOUND);
    }
}
