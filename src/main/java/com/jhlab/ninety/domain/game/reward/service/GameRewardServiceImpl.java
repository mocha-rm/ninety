package com.jhlab.ninety.domain.game.reward.service;

import com.jhlab.ninety.domain.auth.entity.User;
import com.jhlab.ninety.domain.auth.service.UserService;
import com.jhlab.ninety.domain.game.reward.dto.GameRewardRequestDto;
import com.jhlab.ninety.domain.game.reward.dto.GameRewardResponseDto;
import com.jhlab.ninety.domain.game.reward.entity.GameReward;
import com.jhlab.ninety.domain.game.reward.repository.GameRewardRepository;
import com.jhlab.ninety.domain.game.user.entity.UserGameData;
import com.jhlab.ninety.domain.game.user.service.UserGameDataService;
import com.jhlab.ninety.domain.habits.entity.Habits;
import com.jhlab.ninety.domain.habits.service.HabitsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GameRewardServiceImpl implements GameRewardService {
    private final GameRewardRepository gameRewardRepository;

    private final UserService userService;
    private final HabitsService habitsService;
    private final UserGameDataService userGameDataService;

    @Override
    @Transactional
    public GameRewardResponseDto createReward(Long userId, Long habitId, GameRewardRequestDto requestDto) {
        User user = userService.getUserFromDB(userId);
        Habits habits = habitsService.getHabitsFromDB(habitId);
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

        if (userGameData.getExperience() >= 100) {
            //TODO : 레벨 업 로직
        }

        return GameRewardResponseDto.toDto(reward);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<GameRewardResponseDto> getRewardsByUser(Long userId, Pageable pageable) {
        Page<GameReward> rewards = gameRewardRepository.findByUserId(userId, pageable);
        return rewards.map(GameRewardResponseDto::toDto);
    }
}
