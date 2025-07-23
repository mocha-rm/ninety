package com.jhlab.ninety.domain.game.character.dto;

import com.jhlab.ninety.domain.game.character.entity.Character;
import com.jhlab.ninety.domain.game.character.type.CharacterRarity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CharacterResponseDto {
    private final Long id;
    private final String name;
    private final String description;
    private final CharacterRarity rarity;
    private final int price;
    private final String imageUrl;
    private final LocalDateTime createdAt;

    public CharacterResponseDto(Character character) {
        this.id = character.getId();
        this.name = character.getName();
        this.description = character.getDescription();
        this.rarity = character.getRarity();
        this.price = character.getPrice();
        this.imageUrl = character.getImageUrl();
        this.createdAt = character.getCreatedAt();
    }
}
