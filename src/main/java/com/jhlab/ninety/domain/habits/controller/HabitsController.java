package com.jhlab.ninety.domain.habits.controller;

import com.jhlab.ninety.domain.habits.dto.HabitsRequestDto;
import com.jhlab.ninety.domain.habits.dto.HabitsResponseDto;
import com.jhlab.ninety.domain.habits.service.HabitsService;
import com.jhlab.ninety.global.common.exception.response.ApiResponse;
import com.jhlab.ninety.global.security.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/habits")
public class HabitsController {
    private final HabitsService habitsService;

    @PostMapping
    public ResponseEntity<ApiResponse<HabitsResponseDto>> createHabits(@RequestBody HabitsRequestDto requestDto,
                                                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {
        HabitsResponseDto response = habitsService.createHabits(requestDto, userDetails);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("습관이 생성되었습니다", response));
    }
}
