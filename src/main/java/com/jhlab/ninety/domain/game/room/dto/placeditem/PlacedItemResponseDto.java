package com.jhlab.ninety.domain.game.room.dto.placeditem;

import com.jhlab.ninety.domain.game.room.entity.PlacedItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlacedItemResponseDto {
    private final Long id;
    private final String itemName;
    private final String category;
    private final double posX;
    private final double posY;
    private final double rotation;

    public PlacedItemResponseDto(PlacedItem placedItem) {
        this.id = placedItem.getId();
        this.itemName = placedItem.getItem().getName();
        this.category = placedItem.getItem().getCategory().toString();
        this.posX = placedItem.getPosX();
        this.posY = placedItem.getPosY();
        this.rotation = placedItem.getRotation();
    }
}
