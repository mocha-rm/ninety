package com.jhlab.ninety.domain.game.reward.service;

import com.jhlab.ninety.domain.game.reward.dto.GameRewardRequestDto;
import com.jhlab.ninety.domain.game.reward.dto.GameRewardResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GameRewardService {
    GameRewardResponseDto createReward(Long userId, Long habitId, GameRewardRequestDto requestDto);

    Page<GameRewardResponseDto> getRewardsByUser(Long userId, Pageable pageable);
}
