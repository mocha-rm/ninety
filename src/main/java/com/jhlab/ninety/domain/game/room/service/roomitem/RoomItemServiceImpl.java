package com.jhlab.ninety.domain.game.room.service.roomitem;

import com.jhlab.ninety.domain.game.room.dto.roomitem.RoomItemRequestDto;
import com.jhlab.ninety.domain.game.room.dto.roomitem.RoomItemResponseDto;
import com.jhlab.ninety.domain.game.room.entity.RoomItem;
import com.jhlab.ninety.domain.game.room.repository.RoomItemRepository;
import com.jhlab.ninety.domain.game.room.type.ItemCategory;
import com.jhlab.ninety.global.common.exception.GlobalException;
import com.jhlab.ninety.global.common.exception.type.GameErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoomItemServiceImpl implements RoomItemService {
    private final RoomItemRepository roomItemRepository;

    @Override
    @Transactional
    public RoomItemResponseDto createRoomItem(RoomItemRequestDto requestDto) {
        RoomItem item = new RoomItem(
                requestDto.getName(),
                requestDto.getDescription(),
                requestDto.getCategory(),
                requestDto.getPrice(),
                requestDto.getImageUrl()
        );

        roomItemRepository.save(item);

        return RoomItemResponseDto.toDto(item);
    }

    @Override
    @Transactional(readOnly = true)
    public RoomItemResponseDto getRoomItem(Long roomItemId) {
        return RoomItemResponseDto.toDto(getRoomItemFromDB(roomItemId));
    }

    @Override
    public Slice<RoomItemResponseDto> getRoomItems(ItemCategory category, Pageable pageable) {
        Slice<RoomItem> roomItems;

        if (category != null) {
            roomItems = roomItemRepository.findByCategory(category, pageable);
        } else {
            roomItems = roomItemRepository.findAll(pageable);
        }

        return roomItems.map(RoomItemResponseDto::toDto);
    }

    @Override
    @Transactional
    public RoomItemResponseDto updateRoomItem(Long roomItemId, RoomItemRequestDto requestDto) {
        RoomItem item = getRoomItemFromDB(roomItemId);

        item.updateRoomItem(requestDto.getName(),
                requestDto.getDescription(),
                requestDto.getCategory(),
                requestDto.getPrice(),
                requestDto.getImageUrl()
        );

        roomItemRepository.save(item);

        return RoomItemResponseDto.toDto(item);
    }

    @Override
    @Transactional
    public void deleteRoomItem(Long roomItemId) {
        RoomItem item = getRoomItemFromDB(roomItemId);

        roomItemRepository.delete(item);
    }

    @Override
    public RoomItem getRoomItemFromDB(Long roomItemId) {
        return roomItemRepository.findById(roomItemId)
                .orElseThrow(() -> new GlobalException(GameErrorCode.ITEM_NOT_FOUND));
    }
}
