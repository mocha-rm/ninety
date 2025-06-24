package com.jhlab.ninety.domain.auth.service;

import com.jhlab.ninety.domain.auth.dto.update.UserNicknameUpdateRequestDto;
import com.jhlab.ninety.domain.auth.dto.UserResponseDto;
import com.jhlab.ninety.domain.auth.dto.update.UserPasswordUpdateRequestDto;
import com.jhlab.ninety.domain.auth.entity.User;

public interface UserService {
    UserResponseDto findUser(Long userId);

    UserResponseDto updateNickname(Long userId, UserNicknameUpdateRequestDto requestDto);

    void updatePassword(Long userId, UserPasswordUpdateRequestDto requestDto);

    User getUserFromDB(Long userId);

    User getUserFromDB(String email);
}
