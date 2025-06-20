package com.jhlab.ninety.domain.habits.controller;

import com.jhlab.ninety.domain.habits.dto.HabitsRequestDto;
import com.jhlab.ninety.domain.habits.dto.HabitsResponseDto;
import com.jhlab.ninety.domain.habits.service.HabitsService;
import com.jhlab.ninety.global.common.exception.response.ApiResponse;
import com.jhlab.ninety.global.security.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{habitsId}")
    public ResponseEntity<ApiResponse<HabitsResponseDto>> findHabits(@PathVariable Long habitsId) {
        HabitsResponseDto response = habitsService.findHabits(habitsId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("습관 조회 성공", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<HabitsResponseDto>>> findAllHabits(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Page<HabitsResponseDto> response = habitsService.findAllHabits(pageable, userDetails);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("습관리스트 조회 성공", response));
    }

    @PatchMapping("/{habitsId}")
    public ResponseEntity<ApiResponse<HabitsResponseDto>> updateHabits(@PathVariable Long habitsId,
                                                                       @RequestBody HabitsRequestDto requestDto,
                                                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {
        HabitsResponseDto response = habitsService.updateHabits(habitsId, requestDto, userDetails);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("습관 수정 완료", response));
    }

    @DeleteMapping("/{habitsId}")
    public ResponseEntity<ApiResponse<Void>> deleteHabits(@PathVariable Long habitsId,
                                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        habitsService.deleteHabits(habitsId, userDetails);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("습관 삭제 완료", null));
    }
}
