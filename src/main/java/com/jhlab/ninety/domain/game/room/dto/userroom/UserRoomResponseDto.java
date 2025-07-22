package com.jhlab.ninety.domain.game.room.dto.userroom;

import com.jhlab.ninety.domain.game.room.dto.placeditem.PlacedItemResponseDto;
import com.jhlab.ninety.domain.game.room.entity.UserRoom;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class UserRoomResponseDto {
    private final Long userRoomId;
    private final List<PlacedItemResponseDto> items;

    public UserRoomResponseDto(UserRoom userRoom) {
        this.userRoomId = userRoom.getId();
        this.items = userRoom.getItems().stream()
                .map(PlacedItemResponseDto::new)
                .collect(Collectors.toList());
    }
}
