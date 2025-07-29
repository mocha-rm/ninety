package com.jhlab.ninety.domain.game.room.service.roomitem;

import com.jhlab.ninety.domain.game.room.dto.roomitem.RoomItemRequestDto;
import com.jhlab.ninety.domain.game.room.dto.roomitem.RoomItemResponseDto;
import com.jhlab.ninety.domain.game.room.entity.RoomItem;
import com.jhlab.ninety.domain.game.room.type.ItemCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface RoomItemService {
    RoomItemResponseDto createRoomItem(RoomItemRequestDto requestDto);

    RoomItemResponseDto getRoomItem(Long roomItemId);

    Slice<RoomItemResponseDto> getRoomItems(ItemCategory category, Pageable pageable);

    RoomItemResponseDto updateRoomItem(Long roomItemId, RoomItemRequestDto requestDto);

    void deleteRoomItem(Long roomItemId);

    RoomItem getRoomItemFromDB(Long roomItemId);
}
