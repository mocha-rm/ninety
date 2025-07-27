package com.jhlab.ninety.domain.game.reward.service;

import com.jhlab.ninety.domain.game.reward.dto.CalculatedReward;
import com.jhlab.ninety.domain.habits.entity.Habits;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RewardCalculationServiceImpl implements RewardCalculationService {

    @Override
    public CalculatedReward calculateReward(Habits habits) {
        // TODO: 추후 캐릭터 레벨, 연속 출석 등 복잡한 보상 로직 구현
        Random random = new Random();
        int coins = random.nextInt(11) + 10; // 10 ~ 20
        int exp = random.nextInt(6) + 5;     // 5 ~ 10
        int food = 1; // 기본값
        int toy = 1; // 기본값

        return new CalculatedReward(coins, exp, food, toy);
    }
}
