package com.jhlab.ninety.domain.game.user.dto;

import com.jhlab.ninety.domain.game.user.entity.UserGameData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserGameDataResponseDto {
    private final Long id;
    private final Long userId;
    private final int coins;
    private final int level;
    private final int experience;
    private final int food;
    private final int toy;

    public static UserGameDataResponseDto toDto(UserGameData userGameData) {
        return new UserGameDataResponseDto(
                userGameData.getId(),
                userGameData.getUserId(),
                userGameData.getCoins(),
                userGameData.getLevel(),
                userGameData.getExperience(),
                userGameData.getFood(),
                userGameData.getToy()
        );
    }
}
