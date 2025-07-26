package com.jhlab.ninety.domain.game.room.controller;

import com.jhlab.ninety.domain.game.room.dto.placeditem.PlaceItemRequestDto;
import com.jhlab.ninety.domain.game.room.dto.userroom.UserRoomResponseDto;
import com.jhlab.ninety.domain.game.room.service.placeditem.PlacedItemService;
import com.jhlab.ninety.domain.game.room.service.userroom.UserRoomService;
import com.jhlab.ninety.global.common.exception.response.ApiResponse;
import com.jhlab.ninety.global.security.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game/user-room")
public class UserRoomController {
    private final UserRoomService userRoomService;
    private final PlacedItemService placedItemService;

    @GetMapping
    public ResponseEntity<ApiResponse<UserRoomResponseDto>> findUserRoom(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long userId = userDetails.getUser().getId();

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("방 조회 성공", userRoomService.getUserRoom(userId)));
    }

    //TODO : PathVariable로 배치할 아이템 지정하기
    @PostMapping("/{roomId}/placed-items")
    public ResponseEntity<ApiResponse<Void>> placeItem(
            @PathVariable Long roomId,
            @RequestBody PlaceItemRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        placedItemService.placeItem(roomId, requestDto, userDetails.getUser().getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("아이템 방에 배치 성공", null));
    }

    @PutMapping("/{roomId}/placed-items/{placedItemId}")
    public ResponseEntity<ApiResponse<Void>> moveItem(
            @PathVariable Long roomId,
            @PathVariable Long placedItemId,
            @RequestBody PlaceItemRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        placedItemService.moveItem(roomId, placedItemId, requestDto, userDetails.getUser().getId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("배치된 아이템 위치 수정 성공", null));
    }

    @DeleteMapping("/{roomId}/placed-items/{placedItemId}")
    public ResponseEntity<ApiResponse<Void>> removeItem(
            @PathVariable Long roomId,
            @PathVariable Long placedItemId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        placedItemService.removeItem(roomId,placedItemId, userDetails.getUser().getId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("배치된 아이템 제거 성공", null));
    }
}
