package com.example.demo.domain.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
//@AllArgsConstructor
@NoArgsConstructor
public class BoardDetailResponse {

    // 게시판 제목
    private String title;
    // 게시글 작성자 이름
    private String userName;
    // 게시판 내용
    private String content;
    // 게시판 작성 일시
    private LocalDateTime createdDate;
    // 게시판 수정 일시
    private LocalDateTime updatedDate;

    @Builder
    public BoardDetailResponse(
            String title,
            String userName,
            String content,
            LocalDateTime createdDate,
            LocalDateTime updatedDate
    ) {
        this.title = title;
        this.userName = userName;
        this.content = content;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
