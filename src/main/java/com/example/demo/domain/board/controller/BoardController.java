package com.example.demo.domain.board.controller;

import com.example.demo.domain.board.dto.BoardAddRequest;
import com.example.demo.domain.board.dto.BoardDetailResponse;
import com.example.demo.domain.board.service.BoardService;
import com.example.demo.global.dto.ResultResponse;
import com.example.demo.global.dto.code.ResultCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.demo.global.dto.code.ResultCode.*;

@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
@RestController

public class    BoardController {
    private final BoardService boardService;

    @PostMapping("/add")
    public ResponseEntity<ResultResponse> addBoard(@RequestBody BoardAddRequest request){
        BoardDetailResponse responses = boardService.add(request);

        return ResponseEntity.ok(ResultResponse.of(BOARD_CREATE_SUCCESS,responses));
    }
    @GetMapping("{boardId}")
    public ResponseEntity<ResultResponse> readBoard(@PathVariable Long boardId){
        BoardDetailResponse responses = boardService.read(boardId);

        return ResponseEntity.ok(ResultResponse.of(BOARD_LIST_PAGE_SEARCH_SUCCESS,responses));
    }
//    @PutMapping("/update")
//    public ResponseEntity<ResultResponse> updateBoard(@PathVariable)
}
