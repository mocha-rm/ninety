package com.jhlab.ninety.domain.game.character.dto;

import com.jhlab.ninety.domain.game.character.entity.UserCharacter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class UserCharacterResponseDto {
    private final Long id;
    private final Long userId;
    private final Long characterId;
    private final String nickname;
    private final int level;
    private final int experience;
    private final int happiness;
    private final boolean isActive;
    private final LocalDateTime createdAt;

    public UserCharacterResponseDto(UserCharacter userCharacter) {
        this.id = userCharacter.getId();
        this.userId = userCharacter.getUser().getId();
        this.characterId = userCharacter.getCharacter().getId();
        this.nickname = userCharacter.getNickname();
        this.level = userCharacter.getLevel();
        this.experience = userCharacter.getExperience();
        this.happiness = userCharacter.getHappiness();
        this.isActive = userCharacter.getIsActive();
        this.createdAt = userCharacter.getCreatedAt();
    }

    public static UserCharacterResponseDto toDto(UserCharacter userCharacter) {
        return new UserCharacterResponseDto(
                userCharacter.getId(),
                userCharacter.getUser().getId(),
                userCharacter.getCharacter().getId(),
                userCharacter.getNickname(),
                userCharacter.getLevel(),
                userCharacter.getExperience(),
                userCharacter.getHappiness(),
                userCharacter.getIsActive(),
                userCharacter.getCreatedAt()
        );
    }
}
