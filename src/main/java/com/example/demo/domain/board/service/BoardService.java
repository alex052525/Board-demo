package com.example.demo.domain.board.service;

import com.example.demo.domain.board.dto.BoardAddRequest;
import com.example.demo.domain.board.dto.BoardDetailResponse;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.exception.BoardNotFoundException;
import com.example.demo.domain.board.repository.BoardRepository;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.domain.user.exception.UserNotFoundException;
@Slf4j
@Service
@RequiredArgsConstructor

public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    // 게시글 생성
    @Transactional
    public BoardDetailResponse add(BoardAddRequest boardAddRequest){
        final User loggedInUser = loadUserInfoWithUserId(boardAddRequest.getUserId());
        final Board newBoard =
                Board.builder()
                        .user(loggedInUser)
                        .title(boardAddRequest.getTitle())
                        .content(boardAddRequest.getContent())
                        .build();

        boardRepository.save(newBoard);
        return BoardDetailResponse.builder()
                .userName(loggedInUser.getName())
                .createdDate(newBoard.getCreatedDate())
                .updatedDate(newBoard.getUpdatedDate())
                .title(newBoard.getTitle())
                .content(newBoard.getContent())
                .build();
    }

    public BoardDetailResponse read(Long boardId){
//        return boardRepository.findById(boardId).orElseThrow()
        final Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
        return BoardDetailResponse.builder()
                .userName(board.getUser().getName())
                .createdDate(board.getCreatedDate())
                .updatedDate(board.getUpdatedDate())
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }

    private User loadUserInfoWithUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
}