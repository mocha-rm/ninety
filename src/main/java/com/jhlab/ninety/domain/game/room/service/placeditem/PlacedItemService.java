package com.jhlab.ninety.domain.game.room.service.placeditem;

import com.jhlab.ninety.domain.game.room.dto.placeditem.PlaceItemRequestDto;

public interface PlacedItemService {
    /**
     * 아이템 방에 배치
     *
     * @param roomId     Room ID
     * @param requestDto 배치 좌표 정보
     * @param userId     유저 ID
     */
    void placeItem(Long roomId, Long userItemId, PlaceItemRequestDto requestDto, Long userId);

    /**
     * 배치된 아이템 위치 수정
     *
     * @param roomId       Room ID
     * @param placedItemId 배치된 아이템 ID
     * @param requestDto   수정할 좌표 정보
     * @param userId       유저 ID
     */
    void moveItem(Long roomId, Long placedItemId, PlaceItemRequestDto requestDto, Long userId);

    /**
     * 배치된 아이템 해제
     *
     * @param roomId       Room ID
     * @param placedItemId 배치된 아이템 ID
     * @param userId       유저 ID
     */
    void removeItem(Long roomId, Long placedItemId, Long userId);
}
