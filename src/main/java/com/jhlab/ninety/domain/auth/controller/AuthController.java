package com.jhlab.ninety.domain.auth.controller;

import com.jhlab.ninety.domain.auth.dto.UserJwtResponseDto;
import com.jhlab.ninety.domain.auth.dto.UserResponseDto;
import com.jhlab.ninety.domain.auth.dto.auth.LoginRequestDto;
import com.jhlab.ninety.domain.auth.dto.auth.SignUpRequestDto;
import com.jhlab.ninety.domain.auth.service.AuthService;
import com.jhlab.ninety.global.common.exception.response.ApiResponse;
import com.jhlab.ninety.global.security.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserResponseDto>> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        UserResponseDto response = authService.signUp(signUpRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("회원가입이 완료되었습니다.", response));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserJwtResponseDto>> login(@RequestBody LoginRequestDto loginRequestDto) {
        UserJwtResponseDto response = authService.login(loginRequestDto);
        return ResponseEntity.ok(ApiResponse.success("로그인이 완료되었습니다.", response));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestHeader("Authorization") String accessToken,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {
        authService.logout(accessToken, userDetails.getUser().getId());
        return ResponseEntity.ok(ApiResponse.success("로그아웃이 완료되었습니다.", null));
    }
}
