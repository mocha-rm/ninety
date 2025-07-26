package com.jhlab.ninety.domain.game.character.dto;

import com.jhlab.ninety.domain.game.character.type.CharacterRarity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CharacterRequestDto {
    private final String name;
    private final String description;
    private final CharacterRarity rarity;
    private final int price;
    private final String imageUrl;
}
