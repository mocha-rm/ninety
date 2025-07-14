package com.jhlab.ninety.domain.game.user.service;

import com.jhlab.ninety.domain.game.user.dto.UserGameDataRequestDto;
import com.jhlab.ninety.domain.game.user.dto.UserGameDataResponseDto;
import com.jhlab.ninety.domain.game.user.entity.UserGameData;
import com.jhlab.ninety.domain.game.user.repository.UserGameDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserGameDataServiceImpl implements UserGameDataService {
    private final UserGameDataRepository userGameDataRepository;

    @Override
    @Transactional
    public UserGameDataResponseDto createUserGameData(Long userId) {
        UserGameData data = new UserGameData(
                userId,
                0,
                1,
                0
        );

        userGameDataRepository.save(data);

        return UserGameDataResponseDto.toDto(data);
    }

    @Override
    @Transactional(readOnly = true)
    public UserGameDataResponseDto getUserGameData(Long userId) {
        UserGameData data = userGameDataRepository.findByUserId(userId);
        return UserGameDataResponseDto.toDto(data);
    }

    @Override
    @Transactional
    public UserGameDataResponseDto updateUserGameData(Long userId, UserGameDataRequestDto requestDto) {
        UserGameData data = userGameDataRepository.findByUserId(userId);

        data.updateCoins(requestDto.getCoins());
        data.updateLevel(requestDto.getLevel());
        data.updateExperience(requestDto.getExperience());

        userGameDataRepository.save(data);

        return UserGameDataResponseDto.toDto(data);
    }
}
