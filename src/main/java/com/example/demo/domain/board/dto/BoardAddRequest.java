package com.example.demo.domain.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BoardAddRequest {
    //    @Size()
    private Long userId;
    private String title;
    private String content;

}
