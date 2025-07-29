package com.jhlab.ninety.domain.game.character.controller;

import com.jhlab.ninety.domain.game.character.dto.UserCharacterActivationRequestDto;
import com.jhlab.ninety.domain.game.character.dto.UserCharacterNicknameUpdateDto;
import com.jhlab.ninety.domain.game.character.dto.UserCharacterResponseDto;
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
@RequestMapping("/api/game")
public class UserCharacterController {

    private final UserCharacterService userCharacterService;

    @PostMapping("/characters/{characterId}/purchase")
    public ResponseEntity<ApiResponse<Void>> purchaseCharacter(
            @PathVariable Long characterId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long userId = userDetails.getUser().getId();

        userCharacterService.purchaseCharacter(characterId, userId);
        return ResponseEntity.ok(ApiResponse.success("캐릭터를 구매하였습니다.", null));
    }

    @GetMapping("/user-characters")
    public ResponseEntity<ApiResponse<Slice<UserCharacterResponseDto>>> getUserCharacters(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            Pageable pageable) {

        Long userId = userDetails.getUser().getId();

        return ResponseEntity.ok(ApiResponse.success("보유 캐릭터 목록을 조회하였습니다.",
                userCharacterService.findUserCharacters(userId, pageable))
        );
    }

    @GetMapping("/user-characters/{userCharacterId}")
    public ResponseEntity<ApiResponse<UserCharacterResponseDto>> findUserCharacter(
            @PathVariable Long userCharacterId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long userId = userDetails.getUser().getId();

        return ResponseEntity.ok(ApiResponse.success("캐릭터 정보를 조회하였습니다.",
                userCharacterService.findUserCharacter(userCharacterId, userId))
        );
    }

//    @PatchMapping("/user-characters/{userCharacterId}")
//    public ResponseEntity<ApiResponse<Void>> updateUserCharacter(
//            @PathVariable Long userCharacterId,
//            @RequestBody UserCharacterUpdateRequestDto requestDto,
//            @AuthenticationPrincipal UserDetailsImpl userDetails) {
//
//        Long userId = userDetails.getUser().getId();
//        userCharacterService.updateUserCharacter(userCharacterId, requestDto, userId);
//
//        return ResponseEntity.ok(ApiResponse.success("캐릭터 정보를 수정하였습니다.", null));
//    }

    @PatchMapping("/user-characters/{userCharacterId}/activation")
    public ResponseEntity<ApiResponse<UserCharacterResponseDto>> manageCharacterActivation(
            @PathVariable Long userCharacterId,
            @RequestBody UserCharacterActivationRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long userId = userDetails.getUser().getId();

        return ResponseEntity.ok(ApiResponse.success("유저 활성화 상태 업데이트 성공",
                userCharacterService.manageActivation(userCharacterId, requestDto, userId))
        );
    }

    @PatchMapping("/user-characters/{userCharacterId}")
    public ResponseEntity<ApiResponse<UserCharacterResponseDto>> updateCharacterNickname(
            @PathVariable Long userCharacterId,
            @RequestBody UserCharacterNicknameUpdateDto updateDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long userId = userDetails.getUser().getId();

        return ResponseEntity.ok(ApiResponse.success("캐릭터 닉네임 변경 성공",
                userCharacterService.updateCharacterNickname(userCharacterId, updateDto, userId))
        );
    }

    @PostMapping("/user-characters/{userCharacterId}/feeding")
    public ResponseEntity<ApiResponse<Void>> feedCharacter(
            @PathVariable Long userCharacterId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long userId = userDetails.getUser().getId();

        userCharacterService.feedCharacter(userCharacterId, userId);
        return ResponseEntity.ok(ApiResponse.success("캐릭터에게 먹이를 주었습니다.", null));
    }

    @PostMapping("/user-characters/{userCharacterId}/playing")
    public ResponseEntity<ApiResponse<Void>> playWithCharacter(
            @PathVariable Long userCharacterId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long userId = userDetails.getUser().getId();

        userCharacterService.playWithCharacter(userCharacterId, userId);
        return ResponseEntity.ok(ApiResponse.success("캐릭터와 놀아주었습니다.", null));
    }
}
