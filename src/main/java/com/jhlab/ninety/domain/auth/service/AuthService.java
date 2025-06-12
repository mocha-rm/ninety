package com.jhlab.ninety.domain.auth.service;

import com.jhlab.ninety.domain.auth.dto.UserJwtResponseDto;
import com.jhlab.ninety.domain.auth.dto.UserResponseDto;
import com.jhlab.ninety.domain.auth.dto.auth.LoginRequestDto;
import com.jhlab.ninety.domain.auth.dto.auth.SignUpRequestDto;

import java.util.Map;

public interface AuthService {
    UserResponseDto signUp(SignUpRequestDto requestDto);

    UserJwtResponseDto login(LoginRequestDto requestDto);

    void logout(String accessToken, Long userId);

    Map<String, Object> refreshAccessToken(String refreshToken);

    //TODO : Find Email & Reset Password
}
