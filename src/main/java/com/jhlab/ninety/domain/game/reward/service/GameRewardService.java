package com.jhlab.ninety.domain.game.reward.service;

import com.jhlab.ninety.domain.game.reward.dto.GameRewardResponseDto;
import com.jhlab.ninety.domain.game.reward.type.RewardType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GameRewardService {
    GameRewardResponseDto createReward(Long userId, Long habitId, int coins, int exp, RewardType type);

    Page<GameRewardResponseDto> getRewardsByUser(Long userId, Pageable pageable);
}
