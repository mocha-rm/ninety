package com.jhlab.ninety.domain.game.room.service;

import com.jhlab.ninety.domain.game.room.dto.placeditem.PlaceItemRequestDto;
import com.jhlab.ninety.domain.game.room.dto.useritem.UserItemResponseDto;
import com.jhlab.ninety.domain.game.room.type.ItemCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface UserItemService {

    /**
     * 아이템 구매
     *
     * @param itemId 구매할 아이템 ID
     * @param userId  유저 ID
     */
    void buyItem(Long itemId, Long userId);

    /**
     * 보유 아이템 단건 조회
     *
     * @param userItemId 보유 아이템 ID
     * @return 아이템 정보
     */
    UserItemResponseDto getUserItem(Long userItemId);

    /**
     * 보유 아이템 목록 조회
     *
     * @param email    유저 이메일
     * @param category 아이템 카테고리 (null 가능)
     * @param pageable 페이징 정보
     * @return 아이템 목록
     */
    Slice<UserItemResponseDto> getUserItems(String email, ItemCategory category, Pageable pageable);

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