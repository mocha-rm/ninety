package com.jhlab.ninety.domain.game.user.controller;

import com.jhlab.ninety.domain.game.user.dto.UserGameDataRequestDto;
import com.jhlab.ninety.domain.game.user.dto.UserGameDataResponseDto;
import com.jhlab.ninety.domain.game.user.service.UserGameDataService;
import com.jhlab.ninety.global.common.exception.response.ApiResponse;
import com.jhlab.ninety.global.security.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game/user-data")
public class UserGameDataController {
    private final UserGameDataService userGameDataService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserGameDataResponseDto>> createUserGameData(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("게임 데이터 생성 성공",
                        userGameDataService.createUserGameData(userDetails.getUser().getId()))
                );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<UserGameDataResponseDto>> findUserGameData(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("게임 데이터 로드 성공",
                        userGameDataService.findUserGameData(userDetails.getUser().getId()))
                );
    }

    @PatchMapping
    public ResponseEntity<ApiResponse<UserGameDataResponseDto>> updateUserGameData(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody UserGameDataRequestDto requestDto) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("게임 데이터 수정 성공",
                        userGameDataService.updateUserGameData(userDetails.getUser().getId(), requestDto))
                );
    }
}
