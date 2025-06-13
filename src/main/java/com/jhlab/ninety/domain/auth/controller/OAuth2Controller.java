package com.jhlab.ninety.domain.auth.controller;

import com.jhlab.ninety.domain.auth.dto.auth.GoogleOAuth2RequestDto;
import com.jhlab.ninety.domain.auth.dto.UserJwtResponseDto;
import com.jhlab.ninety.domain.auth.service.OAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oauth2")
@RequiredArgsConstructor
public class OAuth2Controller {
    private final OAuth2Service oAuth2Service;

    @PostMapping("/google")
    public ResponseEntity<UserJwtResponseDto> googleLogin(@RequestBody GoogleOAuth2RequestDto request) {
        return ResponseEntity.ok(oAuth2Service.googleLogin(request.getIdToken(), request.getAccessToken()));
    }
} 