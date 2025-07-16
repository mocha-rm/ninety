package com.jhlab.ninety.domain.game.room.dto.roomitem;

import com.jhlab.ninety.domain.game.room.type.ItemCategory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RoomItemRequestDto {
    private final String name;
    private final String description;
    private final ItemCategory category;
    private final int price;
    private final String imageUrl;
}
