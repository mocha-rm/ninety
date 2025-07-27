package com.jhlab.ninety.domain.game.reward.service;

import com.jhlab.ninety.domain.auth.entity.User;
import com.jhlab.ninety.domain.game.reward.dto.GameRewardRequestDto;
import com.jhlab.ninety.domain.game.reward.dto.GameRewardResponseDto;
import com.jhlab.ninety.domain.habits.entity.Habits;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GameRewardService {
    GameRewardResponseDto createReward(User user, Habits habits, GameRewardRequestDto requestDto);

    Page<GameRewardResponseDto> getRewardsByUser(Long userId, Pageable pageable);
}
