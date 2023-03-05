package com.example.demo.domain.board.service;

import com.example.demo.domain.board.dto.BoardAddRequest;
import com.example.demo.domain.board.dto.BoardDetailResponse;
import com.example.demo.domain.board.dto.BoardUpdateRequest;
import com.example.demo.domain.board.entity.Board;
import com.example.demo.domain.board.exception.BoardNotFoundException;
import com.example.demo.domain.board.exception.CantUpdateOthersBoard;
import com.example.demo.domain.board.repository.BoardRepository;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.domain.user.exception.UserNotFoundException;

import java.util.Objects;

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
        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
        return BoardDetailResponse.builder()
                .userName(board.getUser().getName())
                .createdDate(board.getCreatedDate())
                .updatedDate(board.getUpdatedDate())
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }

    public BoardDetailResponse update(BoardUpdateRequest boardUpdateRequest){
        Board modifiedBoard = boardRepository.findById(boardUpdateRequest.getBoardId())
                .orElseThrow(BoardNotFoundException::new);
        User loggedInuser = loadUserInfoWithUserId(boardUpdateRequest.getUserId());
        checkIsWriter(boardUpdateRequest,modifiedBoard);

        return BoardDetailResponse.builder()
                .userName(loggedInuser.getName())
                .createdDate(modifiedBoard.getCreatedDate())
                .updatedDate(modifiedBoard.getUpdatedDate())
                .title(modifiedBoard.getTitle())
                .content(modifiedBoard.getContent())
                .build();
    }

    public void delete(Long boardId,Long userId){
        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
        if (!Objects.equals(board.getUser().getId(),userId))
            throw new CantUpdateOthersBoard();
        boardRepository.deleteById(board.getId());
    }

    private void checkIsWriter(BoardUpdateRequest boardUpdateRequest,Board modifiedBoard) {
        if (!Objects.equals(boardUpdateRequest.getUserId(),modifiedBoard.getUser().getId())){
            throw new CantUpdateOthersBoard();
        }
        modifiedBoard.setTitle(boardUpdateRequest.getTitle());
        modifiedBoard.setContent(boardUpdateRequest.getContent());
        boardRepository.save(modifiedBoard);
    }

    private User loadUserInfoWithUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
}