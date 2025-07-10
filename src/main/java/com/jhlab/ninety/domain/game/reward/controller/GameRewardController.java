package com.jhlab.ninety.domain.game.reward.controller;

import com.jhlab.ninety.domain.game.reward.dto.GameRewardResponseDto;
import com.jhlab.ninety.domain.game.reward.service.GameRewardService;
import com.jhlab.ninety.global.common.exception.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game/rewards")
public class GameRewardController {
    private final GameRewardService gameRewardService;

    @PostMapping("/habit-completion/{habitId}")
    public ResponseEntity<ApiResponse<GameRewardResponseDto>> createHabitCompletionReward(@PathVariable Long habitId,
                                                                                          @RequestParam Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("습관 완료 보상 생성", null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<GameRewardResponseDto>>> getRewardHistory(@RequestParam Long userId,
                                                                                     @RequestParam int page,
                                                                                     @RequestParam int size) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("보상 히스토리 조회 성공", null));
    }
}
