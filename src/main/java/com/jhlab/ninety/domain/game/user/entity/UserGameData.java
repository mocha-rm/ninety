package com.jhlab.ninety.domain.game.user.entity;

import com.jhlab.ninety.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user_game_data")
public class UserGameData extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Integer coins;

    @Column(nullable = false)
    private Integer level;

    @Column(nullable = false)
    private Integer experience;
}
