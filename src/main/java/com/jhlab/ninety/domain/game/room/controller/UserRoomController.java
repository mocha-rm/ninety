package com.jhlab.ninety.domain.game.room.controller;

import com.jhlab.ninety.domain.game.room.dto.userroom.UserRoomResponseDto;
import com.jhlab.ninety.domain.game.room.service.userroom.UserRoomService;
import com.jhlab.ninety.global.common.exception.response.ApiResponse;
import com.jhlab.ninety.global.security.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game/user-room")
public class UserRoomController {
    private final UserRoomService userRoomService;

    @GetMapping
    public ResponseEntity<ApiResponse<UserRoomResponseDto>> findUserRoom(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long userId = userDetails.getUser().getId();

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("방 조회 성공", userRoomService.getUserRoom(userId)));
    }
}
