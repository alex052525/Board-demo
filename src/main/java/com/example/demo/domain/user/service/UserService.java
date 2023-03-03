package com.example.demo.domain.user.service;

import com.example.demo.domain.board.dto.BoardDetailResponse;
import com.example.demo.domain.board.repository.BoardRepository;
import com.example.demo.domain.user.dto.UserInfo;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.exception.UserNotFoundException;
import com.example.demo.domain.user.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)

public class UserService {
    private final UserRepository userRepository;
    public void register(UserInfo.RegisterRequest requestDto) {
        User user =
                User.builder()
                        .name(requestDto.getName())
                        .userRole(requestDto.getUserRole())
                        .build();
        userRepository.save(user);
    }
    public UserInfo.RegisterResponse getUser(Long userId){
        final User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return UserInfo.RegisterResponse.builder()
                .name(user.getName())
                .userRole(user.getUserRole())
                .build();
    }
}
