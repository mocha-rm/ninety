package com.jhlab.ninety.domain.game.room.controller;

import com.jhlab.ninety.domain.game.room.dto.roomitem.RoomItemRequestDto;
import com.jhlab.ninety.domain.game.room.dto.roomitem.RoomItemResponseDto;
import com.jhlab.ninety.domain.game.room.service.roomitem.RoomItemService;
import com.jhlab.ninety.domain.game.room.type.ItemCategory;
import com.jhlab.ninety.global.common.exception.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game/room-items")
public class RoomItemController {
    private final RoomItemService roomItemService;

    @PostMapping
    public ResponseEntity<ApiResponse<RoomItemResponseDto>> createRoomItem(@RequestBody RoomItemRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("아이템 생성 성공", roomItemService.createRoomItem(requestDto)));
    }

    @GetMapping("/{roomItemId}")
    public ResponseEntity<ApiResponse<RoomItemResponseDto>> findRoomItem(@PathVariable Long roomItemId) {
        return ResponseEntity.ok(ApiResponse.success("아이템 조회 성공", roomItemService.getRoomItem(roomItemId)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Slice<RoomItemResponseDto>>> findRoomItems(
            @RequestParam(required = false)ItemCategory category,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)Pageable pageable) {

        return ResponseEntity.ok(ApiResponse.success("아이템 목록 조회 성공",
                roomItemService.getRoomItems(category, pageable))
        );
    }

    @PatchMapping("/{roomItemId}")
    public ResponseEntity<ApiResponse<RoomItemResponseDto>> updateRoomItem(@PathVariable Long roomItemId,
                                                                           @RequestBody RoomItemRequestDto requestDto) {
        return ResponseEntity.ok(ApiResponse.success("아이템 수정 성공",
                roomItemService.updateRoomItem(roomItemId, requestDto))
        );
    }

    @DeleteMapping("/{roomItemId}")
    public ResponseEntity<ApiResponse<Void>> deleteRoomItem(@PathVariable Long roomItemId) {
        roomItemService.deleteRoomItem(roomItemId);
        return ResponseEntity.ok(ApiResponse.success("아이템 삭제 성공", null));
    }
}
