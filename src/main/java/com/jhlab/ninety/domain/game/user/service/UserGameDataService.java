package com.jhlab.ninety.domain.game.user.service;

import com.jhlab.ninety.domain.game.user.dto.UserGameDataRequestDto;
import com.jhlab.ninety.domain.game.user.dto.UserGameDataResponseDto;

public interface UserGameDataService {
    UserGameDataResponseDto createUserGameData(Long userId);

    UserGameDataResponseDto getUserGameData(Long userId);

    UserGameDataResponseDto updateUserGameData(Long userId, UserGameDataRequestDto requestDto);
}
