package com.jhlab.ninety.domain.game.character.controller;

import com.jhlab.ninety.domain.game.character.dto.CharacterResponseDto;
import com.jhlab.ninety.domain.game.character.service.CharacterService;
import com.jhlab.ninety.global.common.exception.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/characters")
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping
    public ResponseEntity<ApiResponse<Slice<CharacterResponseDto>>> getCharacters(Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success("캐릭터 상점 목록을 조회하였습니다.", characterService.findAllCharacters(pageable)));
    }
}
