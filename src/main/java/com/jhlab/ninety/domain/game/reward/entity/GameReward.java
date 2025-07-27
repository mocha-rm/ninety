package com.jhlab.ninety.domain.game.reward.entity;

import com.jhlab.ninety.domain.auth.entity.User;
import com.jhlab.ninety.domain.game.reward.type.RewardType;
import com.jhlab.ninety.domain.habits.entity.Habits;
import com.jhlab.ninety.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "game_reward")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GameReward extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habits_id")
    private Habits habits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Integer coinsEarned;
    private Integer experienceEarned;
    private Integer foodEarned;
    private Integer toyEarned;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RewardType rewardType;

    public GameReward(
            Habits habits,
            User user,
            Integer coinsEarned,
            Integer experienceEarned,
            Integer foodEarned,
            Integer toyEarned,
            RewardType rewardType) {
        this.habits = habits;
        this.user = user;
        this.coinsEarned = coinsEarned;
        this.experienceEarned = experienceEarned;
        this.foodEarned = foodEarned;
        this.toyEarned = toyEarned;
        this.rewardType = rewardType;
    }
}
