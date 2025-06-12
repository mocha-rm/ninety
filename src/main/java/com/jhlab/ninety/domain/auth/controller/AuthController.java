package com.jhlab.ninety.domain.auth.controller;

import com.jhlab.ninety.domain.auth.dto.UserJwtResponseDto;
import com.jhlab.ninety.domain.auth.dto.UserResponseDto;
import com.jhlab.ninety.domain.auth.dto.auth.LoginRequestDto;
import com.jhlab.ninety.domain.auth.dto.auth.SignUpRequestDto;
import com.jhlab.ninety.domain.auth.service.AuthService;
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
    public ResponseEntity<UserResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return new ResponseEntity<>(authService.signUp(signUpRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserJwtResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(authService.login(loginRequestDto), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String accessToken,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {
        authService.logout(accessToken, userDetails.getUser().getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
