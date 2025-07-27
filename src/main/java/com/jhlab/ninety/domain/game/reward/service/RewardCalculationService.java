package com.jhlab.ninety.domain.game.reward.service;

import com.jhlab.ninety.domain.game.reward.dto.CalculatedReward;
import com.jhlab.ninety.domain.habits.entity.Habits;

public interface RewardCalculationService {
    CalculatedReward calculateReward(Habits habits);
}
