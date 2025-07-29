package com.jhlab.ninety.domain.game.room.dto.placeditem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlaceItemRequestDto {
    private final double posX;
    private final double posY;
    private final double rotation;
}
