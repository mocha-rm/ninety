package com.jhlab.ninety.domain.game.character.service;

import com.jhlab.ninety.domain.game.character.dto.CharacterRequestDto;
import com.jhlab.ninety.domain.game.character.dto.CharacterResponseDto;
import com.jhlab.ninety.domain.game.character.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;

    @Override
    @Transactional
    public void createCharacter(CharacterRequestDto requestDto) {

    }

    @Override
    @Transactional(readOnly = true)
    public CharacterResponseDto findCharacter(Long characterId) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Slice<CharacterResponseDto> findAllCharacters(Pageable pageable) {
        return characterRepository.findAllById(pageable).map(CharacterResponseDto::new);
    }

    @Override
    @Transactional
    public CharacterResponseDto updateCharacter(Long characterId, CharacterRequestDto requestDto) {
        return null;
    }

    @Override
    @Transactional
    public void deleteCharacter(Long characterId) {

    }
}
