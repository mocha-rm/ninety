package com.jhlab.ninety.domain.game.character.service;

import com.jhlab.ninety.domain.game.character.dto.CharacterRequestDto;
import com.jhlab.ninety.domain.game.character.dto.CharacterResponseDto;
import com.jhlab.ninety.domain.game.character.entity.Character;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CharacterService {
    CharacterResponseDto createCharacter(CharacterRequestDto requestDto);

    CharacterResponseDto findCharacter(Long characterId);

    Slice<CharacterResponseDto> findAllCharacters(Pageable pageable);

    CharacterResponseDto updateCharacter(Long characterId, CharacterRequestDto requestDto);

    void deleteCharacter(Long characterId);

    Character getCharacterFromDB(Long characterId);
}

