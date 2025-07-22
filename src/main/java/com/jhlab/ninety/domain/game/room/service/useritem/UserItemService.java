package com.jhlab.ninety.domain.game.room.service.useritem;

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
     * @param userId   유저 ID
     * @param category 아이템 카테고리 (null 가능)
     * @param pageable 페이징 정보
     * @return 아이템 목록
     */
    Slice<UserItemResponseDto> getUserItems(Long userId, ItemCategory category, Pageable pageable);
}