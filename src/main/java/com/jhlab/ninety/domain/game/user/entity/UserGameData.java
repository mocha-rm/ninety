package com.jhlab.ninety.domain.game.user.entity;

import com.jhlab.ninety.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Entity
@Table(name = "user_game_data")
@DynamicUpdate
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

    public UserGameData(Long userId, Integer coins, Integer level, Integer experience) {
        this.userId = userId;
        this.coins = coins;
        this.level = level;
        this.experience = experience;
    }

    public void updateCoins(int coins) {
        this.coins = coins;
    }

    public void updateLevel(int level) {
        this.level = level;
    }

    public void updateExperience(int experience) {
        this.experience = experience;
    }
}
