package com.jhlab.ninety.domain.game.room.dto.useritem;

import com.jhlab.ninety.domain.game.room.entity.UserItem;
import com.jhlab.ninety.domain.game.room.type.ItemCategory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * 아이템 상세 정보
 */
@Getter
@RequiredArgsConstructor
public class UserItemResponseDto {
    private final Long id;
    private final Long userId;
    private final Long itemId;
    private final String itemName;
    private final String itemDescription;
    private final ItemCategory category;
    private final String imageUrl;
    private final boolean isPlaced;
    private final LocalDateTime createdAt;

    public static UserItemResponseDto toDto(UserItem userItem) {
        return new UserItemResponseDto(
                userItem.getId(),
                userItem.getUser().getId(),
                userItem.getItem().getId(),
                userItem.getItem().getName(),
                userItem.getItem().getDescription(),
                userItem.getItem().getCategory(),
                userItem.getItem().getImageUrl(),
                userItem.getIsPlaced(),
                userItem.getCreatedAt()
        );
    }
}
