package com.jhlab.ninety.domain.game.character.service;

import com.jhlab.ninety.domain.game.character.dto.CharacterRequestDto;
import com.jhlab.ninety.domain.game.character.dto.CharacterResponseDto;
import com.jhlab.ninety.domain.game.character.entity.Character;
import com.jhlab.ninety.domain.game.character.repository.CharacterRepository;
import com.jhlab.ninety.global.common.exception.GlobalException;
import com.jhlab.ninety.global.common.exception.type.GameErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;

    @Override
    @Transactional
    public CharacterResponseDto createCharacter(CharacterRequestDto requestDto) {
        Optional<Character> result = characterRepository.findByName(requestDto.getName());

        if (result.isPresent()) {
            throw new GlobalException(GameErrorCode.CHARACTER_DUPLICATED);
        }

        Character character = new Character(
                requestDto.getName(),
                requestDto.getDescription(),
                requestDto.getRarity(),
                requestDto.getPrice(),
                requestDto.getImageUrl()
        );

        characterRepository.save(character);

        return CharacterResponseDto.toDto(character);
    }

    @Override
    @Transactional(readOnly = true)
    public CharacterResponseDto findCharacter(Long characterId) {
        Character character = getCharacterFromDB(characterId);

        return CharacterResponseDto.toDto(character);
    }

    @Override
    @Transactional(readOnly = true)
    public Slice<CharacterResponseDto> findAllCharacters(Pageable pageable) {
        return characterRepository.findAll(pageable).map(CharacterResponseDto::new);
    }

    @Override
    @Transactional
    public CharacterResponseDto updateCharacter(Long characterId, CharacterRequestDto requestDto) {
        Character character = getCharacterFromDB(characterId);

        character.updateCharacter(requestDto.getName(),
                requestDto.getDescription(),
                requestDto.getRarity(),
                requestDto.getPrice(),
                requestDto.getImageUrl()
        );

        characterRepository.save(character);

        return CharacterResponseDto.toDto(character);
    }

    @Override
    @Transactional
    public void deleteCharacter(Long characterId) {
        Character character = getCharacterFromDB(characterId);

        characterRepository.delete(character);
    }

    @Override
    public Character getCharacterFromDB(Long characterId) {
        return characterRepository.findById(characterId)
                .orElseThrow(() -> new GlobalException(GameErrorCode.CHARACTER_NOT_FOUND));
    }
}
