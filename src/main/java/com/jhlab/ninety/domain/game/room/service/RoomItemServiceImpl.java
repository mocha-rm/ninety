package com.jhlab.ninety.domain.game.room.service;

import com.jhlab.ninety.domain.game.room.dto.roomitem.RoomItemRequestDto;
import com.jhlab.ninety.domain.game.room.dto.roomitem.RoomItemResponseDto;
import com.jhlab.ninety.domain.game.room.entity.RoomItem;
import com.jhlab.ninety.domain.game.room.repository.RoomItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
