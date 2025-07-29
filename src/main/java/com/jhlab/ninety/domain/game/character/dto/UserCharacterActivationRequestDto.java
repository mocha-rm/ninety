package com.jhlab.ninety.domain.game.character.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserCharacterActivationRequestDto {
    private final Boolean isActive;
}
