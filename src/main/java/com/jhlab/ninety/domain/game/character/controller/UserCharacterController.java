package com.jhlab.ninety.domain.game.character.controller;

import com.jhlab.ninety.domain.game.character.dto.UserCharacterResponseDto;
import com.jhlab.ninety.domain.game.character.dto.UserCharacterUpdateRequestDto;
import com.jhlab.ninety.domain.game.character.service.UserCharacterService;
import com.jhlab.ninety.global.common.exception.response.ApiResponse;
import com.jhlab.ninety.global.security.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-characters")
public class UserCharacterController {

    private final UserCharacterService userCharacterService;

    @PostMapping("/{characterId}/purchase")
    public ResponseEntity<ApiResponse<Void>> purchaseCharacter(@PathVariable Long characterId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();

        userCharacterService.purchaseCharacter(characterId, userId);
        return ResponseEntity.ok(ApiResponse.success("캐릭터를 구매하였습니다.", null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Slice<UserCharacterResponseDto>>> getUserCharacters(@AuthenticationPrincipal UserDetailsImpl userDetails, Pageable pageable) {
        Long userId = userDetails.getUser().getId();

        return ResponseEntity.ok(ApiResponse.success("보유 캐릭터 목록을 조회하였습니다.", userCharacterService.findUserCharacters(userId, pageable)));
    }

    @PatchMapping("/{userCharacterId}")
    public ResponseEntity<ApiResponse<Void>> updateUserCharacter(@PathVariable Long userCharacterId, @RequestBody UserCharacterUpdateRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();

        userCharacterService.updateUserCharacter(userCharacterId, requestDto, userId);
        return ResponseEntity.ok(ApiResponse.success("캐릭터 정보를 수정하였습니다.", null));
    }

    @PostMapping("/{userCharacterId}/feed")
    public ResponseEntity<ApiResponse<Void>> feedCharacter(@PathVariable Long userCharacterId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();

        userCharacterService.feedCharacter(userCharacterId, userId);
        return ResponseEntity.ok(ApiResponse.success("캐릭터에게 먹이를 주었습니다.", null));
    }

    @PostMapping("/{userCharacterId}/play")
    public ResponseEntity<ApiResponse<Void>> playWithCharacter(@PathVariable Long userCharacterId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();

        userCharacterService.playWithCharacter(userCharacterId, userId);
        return ResponseEntity.ok(ApiResponse.success("캐릭터와 놀아주었습니다.", null));
    }
}
