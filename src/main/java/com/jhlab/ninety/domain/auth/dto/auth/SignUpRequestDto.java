package com.jhlab.ninety.domain.auth.dto.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignUpRequestDto {
    private final String email;
    private final String password;
    private final String name;
    private final String nickName;
    private final String phoneNumber;
}
