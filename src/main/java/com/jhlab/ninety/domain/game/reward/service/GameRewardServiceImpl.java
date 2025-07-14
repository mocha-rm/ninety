package com.jhlab.ninety.domain.game.reward.service;

import com.jhlab.ninety.domain.game.reward.dto.GameRewardRequestDto;
import com.jhlab.ninety.domain.game.reward.dto.GameRewardResponseDto;
import com.jhlab.ninety.domain.game.reward.entity.GameReward;
import com.jhlab.ninety.domain.game.reward.repository.GameRewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GameRewardServiceImpl implements GameRewardService {
    private final GameRewardRepository gameRewardRepository;

    @Override
    @Transactional
    public GameRewardResponseDto createReward(Long userId, Long habitId, GameRewardRequestDto requestDto) {
        GameReward reward = new GameReward(
                habitId,
                userId,
                requestDto.getCoins(),
                requestDto.getExp(),
                requestDto.getRewardType()
        );

        gameRewardRepository.save(reward);
        return GameRewardResponseDto.toDto(reward);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GameRewardResponseDto> getRewardsByUser(Long userId, Pageable pageable) {
        Page<GameReward> rewards = gameRewardRepository.findByUserId(userId, pageable);
        return rewards.map(GameRewardResponseDto::toDto);
    }
}
