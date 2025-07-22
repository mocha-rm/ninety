package com.jhlab.ninety.domain.game.room.controller;

import com.jhlab.ninety.domain.game.room.dto.useritem.UserItemRequestDto;
import com.jhlab.ninety.domain.game.room.dto.useritem.UserItemResponseDto;
import com.jhlab.ninety.domain.game.room.service.useritem.UserItemService;
import com.jhlab.ninety.domain.game.room.type.ItemCategory;
import com.jhlab.ninety.global.common.exception.response.ApiResponse;
import com.jhlab.ninety.global.security.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game/user-items")
public class UserItemController {
    private final UserItemService userItemService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createUserItem(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody UserItemRequestDto requestDto) {

        Long userId = userDetails.getUser().getId();
        userItemService.buyItem(requestDto.getItemId(), userId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("아이템 구입 성공", null));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ApiResponse<UserItemResponseDto>> findUserItem(@PathVariable Long itemId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("아이템 단건 조회 성공", userItemService.getUserItem(itemId)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Slice<UserItemResponseDto>>> findUserItems(
            @RequestParam(required = false) ItemCategory category,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long userId = userDetails.getUser().getId();

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("아이템 다건 조회 성공",
                        userItemService.getUserItems(userId, category, pageable))
                );
    }
}
