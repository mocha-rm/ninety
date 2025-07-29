package com.jhlab.ninety.domain.game.character.service;

import com.jhlab.ninety.domain.auth.entity.User;
import com.jhlab.ninety.domain.auth.service.UserService;
import com.jhlab.ninety.domain.game.character.dto.UserCharacterActivationRequestDto;
import com.jhlab.ninety.domain.game.character.dto.UserCharacterNicknameUpdateDto;
import com.jhlab.ninety.domain.game.character.dto.UserCharacterResponseDto;
import com.jhlab.ninety.domain.game.character.entity.Character;
import com.jhlab.ninety.domain.game.character.entity.UserCharacter;
import com.jhlab.ninety.domain.game.character.repository.CharacterRepository;
import com.jhlab.ninety.domain.game.character.repository.UserCharacterRepository;
import com.jhlab.ninety.domain.game.user.entity.UserGameData;
import com.jhlab.ninety.domain.game.user.service.UserGameDataService;
import com.jhlab.ninety.global.common.exception.GlobalException;
import com.jhlab.ninety.global.common.exception.type.GameErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserCharacterServiceImpl implements UserCharacterService {
    private final CharacterRepository characterRepository;
    private final UserCharacterRepository userCharacterRepository;
    private final UserService userService;
    private final UserGameDataService userGameDataService;

    @Override
    @Transactional
    public void purchaseCharacter(Long characterId, Long userId) {
        User user = userService.getUserFromDB(userId);

        Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new GlobalException(GameErrorCode.CHARACTER_NOT_FOUND));

        UserGameData userGameData = userGameDataService.getUserGameDataFromDB(userId);

        if (userGameData.getCoins() < character.getPrice()) {
            throw new GlobalException(GameErrorCode.NOT_ENOUGH_COINS);
        }

        if (userCharacterRepository.findByUserIdAndCharacterId(user.getId(), character.getId()).isPresent()) {
            throw new GlobalException(GameErrorCode.CHARACTER_ALREADY_OWNED);
        }

        userGameData.updateCoins(userGameData.getCoins() - character.getPrice());

        UserCharacter userCharacter = new UserCharacter(
                character.getName(),
                1,
                0,
                0,
                true,
                user,
                character
        );

        userCharacterRepository.save(userCharacter);
    }

    @Override
    @Transactional(readOnly = true)
    public Slice<UserCharacterResponseDto> findUserCharacters(Long userId, Pageable pageable) {
        return userCharacterRepository.findByUserId(userId, pageable).map(UserCharacterResponseDto::new);
    }

    @Override
    @Transactional(readOnly = true)
    public UserCharacterResponseDto findUserCharacter(Long userCharacterId, Long userId) {
        UserCharacter character = userCharacterRepository.findById(userCharacterId)
                .orElseThrow(() -> new GlobalException(GameErrorCode.CHARACTER_NOT_OWNED));

        return UserCharacterResponseDto.toDto(character);
    }

    @Override
    @Transactional
    public UserCharacterResponseDto manageActivation(Long userCharacterId, UserCharacterActivationRequestDto requestDto, Long userId) {
        UserCharacter userCharacter = userCharacterRepository.findById(userCharacterId)
                .orElseThrow(() -> new GlobalException(GameErrorCode.CHARACTER_NOT_OWNED));

        if (!Objects.equals(userCharacter.getUser().getId(), userId)) {
            throw new GlobalException(GameErrorCode.PERMISSION_DENIED);
        }

        userCharacter.updateActivateStatus(requestDto.getIsActive());

        return UserCharacterResponseDto.toDto(userCharacter);
    }

    @Override
    @Transactional
    public UserCharacterResponseDto updateCharacterNickname(Long userCharacterId, UserCharacterNicknameUpdateDto dto, Long userId) {
        UserCharacter userCharacter = userCharacterRepository.findById(userCharacterId)
                .orElseThrow(() -> new GlobalException(GameErrorCode.CHARACTER_NOT_OWNED));

        if (!Objects.equals(userCharacter.getUser().getId(), userId)) {
            throw new GlobalException(GameErrorCode.PERMISSION_DENIED);
        }

        userCharacter.updateNickname(dto.getNickname());

        return UserCharacterResponseDto.toDto(userCharacter);
    }

    @Override
    @Transactional
    public void feedCharacter(Long userCharacterId, Long userId) {
        UserCharacter userCharacter = userCharacterRepository.findById(userCharacterId)
                .orElseThrow(() -> new GlobalException(GameErrorCode.CHARACTER_NOT_OWNED));

        if (!Objects.equals(userCharacter.getUser().getId(), userId)) {
            throw new GlobalException(GameErrorCode.PERMISSION_DENIED);
        }

        UserGameData userGameData = userGameDataService.getUserGameDataFromDB(userId);
        if (userGameData.getFood() < 1) {
            throw new GlobalException(GameErrorCode.NOT_ENOUGH_FOOD);
        }

        userGameData.updateFood(userGameData.getFood() - 1);
        userCharacter.updateHappiness(userCharacter.getHappiness() + 10);

    }

    @Override
    @Transactional
    public void playWithCharacter(Long userCharacterId, Long userId) {
        UserCharacter userCharacter = userCharacterRepository.findById(userCharacterId)
                .orElseThrow(() -> new GlobalException(GameErrorCode.CHARACTER_NOT_OWNED));

        if (!Objects.equals(userCharacter.getUser().getId(), userId)) {
            throw new GlobalException(GameErrorCode.PERMISSION_DENIED);
        }

        UserGameData userGameData = userGameDataService.getUserGameDataFromDB(userId);
        if (userGameData.getToy() < 1) {
            throw new GlobalException(GameErrorCode.NOT_ENOUGH_TOY);
        }

        userGameData.updateToy(userGameData.getToy() - 1);
        userCharacter.updateExperience(userCharacter.getExperience() + 15);

        if (userCharacter.canLevelUp()) {
            userCharacter.levelUp();
        }
    }
}
