package com.jhlab.ninety.domain.auth.dto.update;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserNicknameUpdateRequestDto {
    private final String nickName;
}
