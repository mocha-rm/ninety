package com.jhlab.ninety.domain.auth.dto.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GoogleOAuth2RequestDto {
    private final String idToken;
    private final String accessToken;
}
