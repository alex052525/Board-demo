package com.example.demo.domain.board.entity;

import com.example.demo.domain.user.entity.User;
import com.example.demo.global.entity.BasedTimeEntity;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="board")
public class Board extends BasedTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="board_id")
    private Long id;

    @Column(name="title",nullable = false)
    private String title;
    @Lob
    @Column(name="content",nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Builder
    public Board(String title,String content,User user){
        this.title = title;
        this.content = content;
        this.user = user;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
