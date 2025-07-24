package com.jhlab.ninety.domain.game.character.controller;

import com.jhlab.ninety.domain.game.character.dto.CharacterRequestDto;
import com.jhlab.ninety.domain.game.character.dto.CharacterResponseDto;
import com.jhlab.ninety.domain.game.character.service.CharacterService;
import com.jhlab.ninety.global.common.exception.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game/characters")
public class CharacterController {
    private final CharacterService characterService;

    @PostMapping
    public ResponseEntity<ApiResponse<CharacterResponseDto>> createCharacter(
            @RequestBody CharacterRequestDto requestDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("캐릭터 생성 성공",
                        characterService.createCharacter(requestDto))
                );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Slice<CharacterResponseDto>>> findCharacters(Pageable pageable) {

        return ResponseEntity.ok(ApiResponse.success("캐릭터 상점 목록을 조회하였습니다.",
                characterService.findAllCharacters(pageable))
        );
    }

    @GetMapping("/{characterId}")
    public ResponseEntity<ApiResponse<CharacterResponseDto>> findCharacter(@PathVariable Long characterId) {

        return ResponseEntity.ok(ApiResponse.success("캐릭터 상세 조회 성공",
                characterService.findCharacter(characterId))
        );
    }

    @PatchMapping("/{characterId}")
    public ResponseEntity<ApiResponse<CharacterResponseDto>> updateCharacter(
            @PathVariable Long characterId,
            @RequestBody CharacterRequestDto requestDto) {

        return ResponseEntity.ok(ApiResponse.success("캐릭터 수정 성공",
                characterService.updateCharacter(characterId, requestDto))
        );
    }

    @DeleteMapping("/{characterId}")
    public ResponseEntity<ApiResponse<Void>> deleteCharacter(@PathVariable Long characterId) {

        characterService.deleteCharacter(characterId);

        return ResponseEntity.ok(ApiResponse.success("캐릭터 삭제 성공", null));
    }
}
