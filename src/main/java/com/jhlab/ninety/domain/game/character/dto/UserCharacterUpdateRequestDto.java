package com.jhlab.ninety.domain.game.character.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserCharacterUpdateRequestDto {
    private final String nickname;
    private final Boolean isActive;
}
