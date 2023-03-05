package com.example.demo.domain.user.entity;

import com.example.demo.global.entity.BasedTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
//@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="user")
public class User extends BasedTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable = false)
    private String name;

//    @Column(name="password",nullable = false)
//    private String password;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @Builder
    public User(String name, UserRole userRole) {
        this.name = name;
//        this.password = password;
        this.userRole = userRole;
    }
}