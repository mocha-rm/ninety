package com.jhlab.ninety.domain.game.reward.dto;

import com.jhlab.ninety.domain.game.reward.entity.GameReward;
import com.jhlab.ninety.domain.game.reward.type.RewardType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GameRewardResponseDto {
    private final Long id;
    private final Long habitId;
    private final Long userId;
    private final int coinsEarned;
    private final int experienceEarned;
    private final RewardType rewardType;

    public static GameRewardResponseDto toDto(GameReward reward) {
        return new GameRewardResponseDto(
                reward.getId(),
                reward.getHabitId(),
                reward.getUserId(),
                reward.getCoinsEarned(),
                reward.getExperienceEarned(),
                reward.getRewardType()
        );
    }
}
