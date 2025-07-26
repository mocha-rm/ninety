//package com.jhlab.ninety.domain.game.room.controller;
//
//import com.jhlab.ninety.domain.game.room.dto.placeditem.PlaceItemRequestDto;
//import com.jhlab.ninety.domain.game.room.service.placeditem.PlacedItemService;
//import com.jhlab.ninety.global.common.exception.response.ApiResponse;
//import com.jhlab.ninety.global.security.auth.UserDetailsImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/game/placed-items")
//public class PlacedItemController {
//
//    private final PlacedItemService placedItemService;
//
//    @PostMapping("/{userItemId}")
//    public ResponseEntity<ApiResponse<Void>> placeItem(
//            @PathVariable Long userItemId,
//            @RequestBody PlaceItemRequestDto requestDto,
//            @AuthenticationPrincipal UserDetailsImpl userDetails) {
//
//        placedItemService.placeItem(userItemId, requestDto, userDetails.getUsername());
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(ApiResponse.success("아이템 방에 배치 성공", null));
//    }
//
//    @PutMapping("/{placedItemId}")
//    public ResponseEntity<ApiResponse<Void>> moveItem(
//            @PathVariable Long placedItemId,
//            @RequestBody PlaceItemRequestDto requestDto,
//            @AuthenticationPrincipal UserDetailsImpl userDetails) {
//
//        placedItemService.moveItem(placedItemId, requestDto, userDetails.getUsername());
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(ApiResponse.success("배치된 아이템 위치 수정 성공", null));
//    }
//
//    @DeleteMapping("/{placedItemId}")
//    public ResponseEntity<ApiResponse<Void>> removeItem(
//            @PathVariable Long placedItemId,
//            @AuthenticationPrincipal UserDetailsImpl userDetails) {
//
//        placedItemService.removeItem(placedItemId, userDetails.getUsername());
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(ApiResponse.success("배치된 아이템 제거 성공", null));
//    }
//}
