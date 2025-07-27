package com.jhlab.ninety.domain.game.reward.service;

import com.jhlab.ninety.domain.auth.entity.User;
import com.jhlab.ninety.domain.game.reward.dto.GameRewardRequestDto;
import com.jhlab.ninety.domain.game.reward.dto.GameRewardResponseDto;
import com.jhlab.ninety.domain.game.reward.entity.GameReward;
import com.jhlab.ninety.domain.game.reward.repository.GameRewardRepository;
import com.jhlab.ninety.domain.game.user.entity.UserGameData;
import com.jhlab.ninety.domain.game.user.service.UserGameDataService;
import com.jhlab.ninety.domain.habits.entity.Habits;
import com.jhlab.ninety.global.common.exception.GlobalException;
import com.jhlab.ninety.global.common.exception.type.GameErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class GameRewardServiceImpl implements GameRewardService {
    private final GameRewardRepository gameRewardRepository;
    private final UserGameDataService userGameDataService;

    @Override
    @Transactional
    public GameRewardResponseDto createReward(User user, Habits habits, GameRewardRequestDto requestDto) {
        if (gameRewardRepository.existsByHabitsAndUserAndCreatedAtAfter(habits, user, LocalDate.now().atStartOfDay())) {
            throw new GlobalException(GameErrorCode.ALREADY_REWARDED_TODAY);
        }

        UserGameData userGameData = userGameDataService.getUserGameDataFromDB(user.getId());

        GameReward reward = new GameReward(
                habits,
                user,
                requestDto.getCoins(),
                requestDto.getExp(),
                requestDto.getFood(),
                requestDto.getToy(),
                requestDto.getRewardType()
        );

        gameRewardRepository.save(reward);

        userGameData.updateExperience(userGameData.getExperience() + reward.getExperienceEarned());
        userGameData.updateCoins(userGameData.getCoins() + reward.getCoinsEarned());
        userGameData.updateFood(userGameData.getFood() + reward.getFoodEarned());
        userGameData.updateToy(userGameData.getToy() + reward.getToyEarned());

        userGameDataService.checkAndProcessLevelUp(userGameData);

        return GameRewardResponseDto.toDto(reward);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GameRewardResponseDto> getRewardsByUser(Long userId, Pageable pageable) {
        Page<GameReward> rewards = gameRewardRepository.findByUserId(userId, pageable);
        return rewards.map(GameRewardResponseDto::toDto);
    }
}

