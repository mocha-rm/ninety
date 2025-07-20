package com.jhlab.ninety.domain.game.room.service;

import com.jhlab.ninety.domain.game.room.dto.placeditem.PlaceItemRequestDto;

public interface PlacedItemService {
    /**
     * 아이템 방에 배치
     *
     * @param userItemId 보유 아이템 ID
     * @param requestDto 배치 좌표 정보
     * @param email      유저 이메일
     */
    void placeItem(Long userItemId, PlaceItemRequestDto requestDto, String email);

    /**
     * 배치된 아이템 위치 수정
     *
     * @param placedItemId 배치된 아이템 ID
     * @param requestDto   수정할 좌표 정보
     * @param email        유저 이메일
     */
    void moveItem(Long placedItemId, PlaceItemRequestDto requestDto, String email);

    /**
     * 배치된 아이템 해제
     *
     * @param placedItemId 배치된 아이템 ID
     * @param email        유저 이메일
     */
    void removeItem(Long placedItemId, String email);
}
