package com.jhlab.ninety.domain.game.reward.dto;

import com.jhlab.ninety.domain.game.reward.type.RewardType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GameRewardRequestDto {
    private final int coins;
    private final int exp;
    private final int food;
    private final int toy;
    private final RewardType rewardType;
}
