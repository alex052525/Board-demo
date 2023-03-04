package com.example.demo.domain.board.exception;

import com.example.demo.global.dto.code.ErrorCode;
import com.example.demo.global.exception.BusinessException;

public class CantUpdateOthersBoard extends BusinessException {
    public CantUpdateOthersBoard(){
        super(ErrorCode.CANT_UPDATE_OTHERS_BOARD);
    }
}
