package com.jhlab.ninety.domain.auth.entity;

import com.jhlab.ninety.domain.auth.type.UserRole;
import com.jhlab.ninety.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true)
    private String email;

    @Column(length = 100)
    private String password;

    @Column(length = 10)
    private String name;

    @Column(length = 20)
    private String nickName;

    @Column(length = 15)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User(String email, String password, String name, String nickName, String phoneNumber, UserRole role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public void updateNickname(String nickName) {
        this.nickName = nickName;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
