package com.jhlab.ninety.domain.auth.dto.update;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserPasswordUpdateRequestDto {
    private final String currentPassword;
    private final String newPassword;
    private final String confirmPassword;
}
