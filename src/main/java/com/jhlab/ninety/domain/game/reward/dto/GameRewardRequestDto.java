package com.jhlab.ninety.domain.game.reward.dto;

import com.jhlab.ninety.domain.game.reward.type.RewardType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GameRewardRequestDto {
    private final Long habitId;
    private final Long userId;
    private final int coins;
    private final int exp;
    private final RewardType rewardType;
}
