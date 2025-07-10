package com.jhlab.ninety.domain.game.user.controller;

import com.jhlab.ninety.domain.game.user.dto.UserGameDataRequestDto;
import com.jhlab.ninety.domain.game.user.dto.UserGameDataResponseDto;
import com.jhlab.ninety.domain.game.user.service.UserGameDataService;
import com.jhlab.ninety.global.common.exception.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game/user-data")
public class UserGameDataController {
    private final UserGameDataService userGameDataService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserGameDataResponseDto>> createUserGameData(@RequestParam Long userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("게임 데이터 생성 성공", null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<UserGameDataResponseDto>> getUserGameData(@RequestParam Long userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("게임 데이터 로드 성공", null));
    }

    @PatchMapping
    public ResponseEntity<ApiResponse<UserGameDataResponseDto>> updateUserGameData(@RequestParam Long userId,
                                                                                   @RequestBody UserGameDataRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("게임 데이터 수정 성공", null));
    }
}
