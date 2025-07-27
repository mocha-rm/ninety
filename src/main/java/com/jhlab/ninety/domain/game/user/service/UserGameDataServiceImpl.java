package com.jhlab.ninety.domain.game.user.service;

import com.jhlab.ninety.domain.game.user.dto.UserGameDataResponseDto;
import com.jhlab.ninety.domain.game.user.entity.UserGameData;
import com.jhlab.ninety.domain.game.user.repository.UserGameDataRepository;
import com.jhlab.ninety.global.common.exception.GlobalException;
import com.jhlab.ninety.global.common.exception.type.GameErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserGameDataServiceImpl implements UserGameDataService {
    private final UserGameDataRepository userGameDataRepository;

    @Override
    @Transactional
    public UserGameDataResponseDto createUserGameData(Long userId) {
        Optional<UserGameData> prevData = userGameDataRepository.findByUserId(userId);

        if (prevData.isPresent()) {
            throw new GlobalException(GameErrorCode.USER_GAME_DATA_EXIST);
        }

        UserGameData data = new UserGameData(
                userId,
                500,
                1,
                0,
                1,
                1
        );

        userGameDataRepository.save(data);

        return UserGameDataResponseDto.toDto(data);
    }

    @Override
    @Transactional(readOnly = true)
    public UserGameDataResponseDto findUserGameData(Long userId) {
        UserGameData data = getUserGameDataFromDB(userId);
        return UserGameDataResponseDto.toDto(data);
    }

//    @Override
//    @Transactional
//    public UserGameDataResponseDto updateUserGameData(Long userId, UserGameDataRequestDto requestDto) {
//        UserGameData data = getUserGameDataFromDB(userId);
//
//        data.updateCoins(requestDto.getCoins());
//        data.updateLevel(requestDto.getLevel());
//        data.updateExperience(requestDto.getExperience());
//
//        userGameDataRepository.save(data);
//
//        return UserGameDataResponseDto.toDto(data);
//    }

    @Override
    @Transactional(readOnly = true)
    public UserGameData getUserGameDataFromDB(Long userId) {
        return userGameDataRepository.findByUserId(userId)
                .orElseThrow(() -> new GlobalException(GameErrorCode.USER_GAME_DATA_NOT_FOUND));
    }

    @Override
    @Transactional
    public void checkAndProcessLevelUp(UserGameData userGameData) {
        while (userGameData.canLevelUp()) {
            userGameData.levelUp();
        }
    }
}
