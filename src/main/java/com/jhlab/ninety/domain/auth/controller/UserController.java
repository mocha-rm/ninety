package com.jhlab.ninety.domain.auth.controller;

import com.jhlab.ninety.domain.auth.dto.UserResponseDto;
import com.jhlab.ninety.domain.auth.dto.update.UserNicknameUpdateRequestDto;
import com.jhlab.ninety.domain.auth.dto.update.UserPasswordUpdateRequestDto;
import com.jhlab.ninety.domain.auth.service.UserService;
import com.jhlab.ninety.global.common.exception.response.ApiResponse;
import com.jhlab.ninety.global.security.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<UserResponseDto>> getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("내 정보 확인하기 성공", userService.findUser(userId)));
    }

    @PutMapping("/profile/updateNickname")
    public ResponseEntity<ApiResponse<UserResponseDto>> updateNickname(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                       @RequestBody UserNicknameUpdateRequestDto requestDto) {
        Long userId = userDetails.getUser().getId();
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("닉네임 변경 완료", userService.updateNickname(userId, requestDto)));
    }

    @PatchMapping("/profile/updatePassword")
    public ResponseEntity<ApiResponse<Void>> updatePassword(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                            @RequestBody UserPasswordUpdateRequestDto requestDto) {
        userService.updatePassword(userDetails.getUser().getId(), requestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("비밀번호 변경 완료", null));
    }
}
