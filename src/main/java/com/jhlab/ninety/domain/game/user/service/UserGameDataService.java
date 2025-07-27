package com.jhlab.ninety.domain.game.user.service;

import com.jhlab.ninety.domain.game.user.dto.UserGameDataResponseDto;
import com.jhlab.ninety.domain.game.user.entity.UserGameData;

public interface UserGameDataService {
    UserGameDataResponseDto createUserGameData(Long userId);

    UserGameDataResponseDto findUserGameData(Long userId);

    //UserGameDataResponseDto updateUserGameData(Long userId, UserGameDataRequestDto requestDto);

    UserGameData getUserGameDataFromDB(Long userId);

    void checkAndProcessLevelUp(UserGameData userGameData);
}
