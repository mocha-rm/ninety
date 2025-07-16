package com.jhlab.ninety.domain.game.room.dto.roomitem;

import com.jhlab.ninety.domain.game.room.entity.RoomItem;
import com.jhlab.ninety.domain.game.room.type.ItemCategory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class RoomItemResponseDto {
    private final Long id;
    private final String name;
    private final String description;
    private final ItemCategory category;
    private final int price;
    private final String imageUrl;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static RoomItemResponseDto toDto(RoomItem item) {
        return new RoomItemResponseDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getCategory(),
                item.getPrice(),
                item.getImageUrl(),
                item.getCreatedAt(),
                item.getUpdatedAt()
        );
    }
}
