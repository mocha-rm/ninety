package com.jhlab.ninety.domain.game.room.service.roomitem;

import com.jhlab.ninety.domain.game.room.dto.roomitem.RoomItemRequestDto;
import com.jhlab.ninety.domain.game.room.dto.roomitem.RoomItemResponseDto;
import com.jhlab.ninety.domain.game.room.entity.RoomItem;

public interface RoomItemService {
    RoomItemResponseDto createRoomItem(RoomItemRequestDto requestDto);

    RoomItemResponseDto getRoomItem(Long roomItemId);

    RoomItemResponseDto updateRoomItem(Long roomItemId, RoomItemRequestDto requestDto);

    void deleteRoomItem(Long roomItemId);

    RoomItem getRoomItemFromDB(Long roomItemId);
}
