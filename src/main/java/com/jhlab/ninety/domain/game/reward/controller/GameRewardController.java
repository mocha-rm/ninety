package com.jhlab.ninety.domain.game.reward.controller;

import com.jhlab.ninety.domain.game.reward.dto.GameRewardResponseDto;
import com.jhlab.ninety.domain.game.reward.service.GameRewardService;
import com.jhlab.ninety.global.common.exception.response.ApiResponse;
import com.jhlab.ninety.global.security.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game/rewards")
public class GameRewardController {
    private final GameRewardService gameRewardService;

//    @PostMapping("/habit-completion/{habitId}")
//    public ResponseEntity<ApiResponse<GameRewardResponseDto>> createHabitCompletionReward
//            (@PathVariable Long habitId,
//             @AuthenticationPrincipal UserDetailsImpl userDetails,
//             @RequestBody GameRewardRequestDto requestDto) {
//
//
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(ApiResponse.success(
//                        "습관 완료 보상 생성",
//                        gameRewardService.createReward(userDetails.getUser().getId(), habitId, requestDto))
//                );
//    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<GameRewardResponseDto>>> getRewardHistory(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            Pageable pageable) {

        Page<GameRewardResponseDto> rewards = gameRewardService.getRewardsByUser(userDetails.getUser().getId(), pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("보상 히스토리 조회 성공",rewards));
    }

}
