package com.jhlab.ninety.domain.game.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserGameDataRequestDto {
    private final int coins;
    private final int level;
    private final int experience;
}
