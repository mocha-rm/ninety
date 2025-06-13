package com.jhlab.ninety.domain.auth.service;

import com.jhlab.ninety.domain.auth.dto.UserJwtResponseDto;

public interface OAuth2Service {
    UserJwtResponseDto googleLogin(String idToken, String accessToken);
} 