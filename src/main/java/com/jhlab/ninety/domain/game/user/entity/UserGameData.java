package com.jhlab.ninety.domain.game.user.entity;

import com.jhlab.ninety.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Entity
@Table(name = "user_game_data")
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column(nullable = false)
    private Integer food;

    @Column(nullable = false)
    private Integer toy;

    private static final int MAX_EXPERIENCE_PER_LEVEL = 100;

    public UserGameData(Long userId, Integer coins, Integer level, Integer experience, Integer food, Integer toy) {
        this.userId = userId;
        this.coins = coins;
        this.level = level;
        this.experience = experience;
        this.food = food;
        this.toy = toy;
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

    public void updateFood(int food) {
        this.food = food;
    }

    public void updateToy(int toy) {
        this.toy = toy;
    }

    public boolean canLevelUp() {
        return this.experience >= MAX_EXPERIENCE_PER_LEVEL;
    }

    public void levelUp() {
        this.level++;
        this.experience -= MAX_EXPERIENCE_PER_LEVEL;
    }
}
