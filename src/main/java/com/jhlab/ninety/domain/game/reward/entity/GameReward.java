package com.jhlab.ninety.domain.game.reward.entity;

import com.jhlab.ninety.domain.game.reward.type.RewardType;
import com.jhlab.ninety.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "game_reward")
public class GameReward extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long habitId;
    private Long userId;

    private Integer coinsEarned;
    private Integer experienceEarned;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RewardType rewardType;

    public GameReward(Long habitId, Long userId, Integer coinsEarned, Integer experienceEarned, RewardType rewardType) {
        this.habitId = habitId;
        this.userId = userId;
        this.coinsEarned = coinsEarned;
        this.experienceEarned = experienceEarned;
        this.rewardType = rewardType;
    }
}
