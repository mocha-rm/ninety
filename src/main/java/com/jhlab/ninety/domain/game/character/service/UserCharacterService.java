package com.jhlab.ninety.domain.game.character.service;

import com.jhlab.ninety.domain.game.character.dto.UserCharacterActivationRequestDto;
import com.jhlab.ninety.domain.game.character.dto.UserCharacterNicknameUpdateDto;
import com.jhlab.ninety.domain.game.character.dto.UserCharacterResponseDto;
import com.jhlab.ninety.domain.game.character.dto.UserCharacterUpdateRequestDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface UserCharacterService {
    void purchaseCharacter(Long characterId, Long userId);

    Slice<UserCharacterResponseDto> findUserCharacters(Long userId, Pageable pageable);

    UserCharacterResponseDto findUserCharacter(Long userCharacterId, Long userId);

    UserCharacterResponseDto manageActivation(Long userCharacterId, UserCharacterActivationRequestDto requestDto, Long userId);

    UserCharacterResponseDto updateCharacterNickname(Long userCharacterId, UserCharacterNicknameUpdateDto dto, Long userId);

    void feedCharacter(Long userCharacterId, Long userId);

    void playWithCharacter(Long userCharacterId, Long userId);
}

