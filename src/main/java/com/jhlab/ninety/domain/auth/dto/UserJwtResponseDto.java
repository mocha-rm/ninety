package com.jhlab.ninety.domain.auth.dto;

import com.jhlab.ninety.domain.auth.type.UserRole;
import lombok.Getter;

@Getter
public class UserJwtResponseDto {
    private final Long id;
    private final String email;
    private final String name;
    private final UserRole role;
    private final String accessToken;
    private final String refreshToken;

    public UserJwtResponseDto(Long id, String email, String name, UserRole role, String accessToken, String refreshToken) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
