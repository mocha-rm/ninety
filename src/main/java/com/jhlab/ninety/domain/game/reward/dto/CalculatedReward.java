package com.jhlab.ninety.domain.game.reward.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CalculatedReward {
    private final int coins;
    private final int exp;
    private final int food;
    private final int toy;
}
