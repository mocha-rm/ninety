package com.jhlab.ninety.domain.game.room.service.placeditem;

import com.jhlab.ninety.domain.auth.entity.User;
import com.jhlab.ninety.domain.auth.service.UserService;
import com.jhlab.ninety.domain.game.room.dto.placeditem.PlaceItemRequestDto;
import com.jhlab.ninety.domain.game.room.entity.PlacedItem;
import com.jhlab.ninety.domain.game.room.entity.UserItem;
import com.jhlab.ninety.domain.game.room.entity.UserRoom;
import com.jhlab.ninety.domain.game.room.repository.PlacedItemRepository;
import com.jhlab.ninety.domain.game.room.repository.UserItemRepository;
import com.jhlab.ninety.domain.game.room.repository.UserRoomRepository;
import com.jhlab.ninety.global.common.exception.GlobalException;
import com.jhlab.ninety.global.common.exception.type.GameErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlacedItemServiceImpl implements PlacedItemService {
    private final UserItemRepository userItemRepository;
    private final UserRoomRepository userRoomRepository;
    private final PlacedItemRepository placedItemRepository;

    private final UserService userService;

    @Override
    @Transactional
    public void placeItem(Long userItemId, PlaceItemRequestDto requestDto, String email) {
        User user = userService.getUserFromDB(email);
        UserItem userItem = userItemRepository.findById(userItemId)
                .orElseThrow(() -> new GlobalException(GameErrorCode.ITEM_NOT_OWNED));

        if (!userItem.getUser().getId().equals(user.getId())) {
            throw new GlobalException(GameErrorCode.ITEM_NOT_OWNED);
        }

        if (userItem.getIsPlaced()) {
            throw new GlobalException(GameErrorCode.ITEM_ALREADY_PLACED);
        }

        UserRoom userRoom = userRoomRepository.findByUserId(user.getId())
                .orElseThrow(() -> new GlobalException(GameErrorCode.USER_ROOM_NOT_FOUND));

        PlacedItem placedRoomItem = new PlacedItem(
                userRoom,
                userItem.getItem(),
                requestDto.getPosX(),
                requestDto.getPosY(),
                requestDto.getRotation()
        );
        placedItemRepository.save(placedRoomItem);

        userItem.updateIsPlaced(true);
        userItemRepository.save(userItem);
    }

    @Override
    @Transactional
    public void moveItem(Long placedItemId, PlaceItemRequestDto requestDto, String email) {
        User user = userService.getUserFromDB(email);
        PlacedItem placedRoomItem = placedItemRepository.findById(placedItemId)
                .orElseThrow(() -> new GlobalException(GameErrorCode.PLACED_ITEM_NOT_FOUND));

        if (!placedRoomItem.getUserRoom().getUser().getId().equals(user.getId())) {
            throw new GlobalException(GameErrorCode.PLACED_ITEM_NOT_FOUND);
        }

        placedRoomItem.updatePosition(
                requestDto.getPosX(),
                requestDto.getPosY(),
                requestDto.getRotation()
        );
        placedItemRepository.save(placedRoomItem);
    }

    @Override
    @Transactional
    public void removeItem(Long placedItemId, String email) {
        User user = userService.getUserFromDB(email);
        PlacedItem placedRoomItem = placedItemRepository.findById(placedItemId)
                .orElseThrow(() -> new GlobalException(GameErrorCode.PLACED_ITEM_NOT_FOUND));

        if (!placedRoomItem.getUserRoom().getUser().getId().equals(user.getId())) {
            throw new GlobalException(GameErrorCode.PLACED_ITEM_NOT_FOUND);
        }

        UserItem userItem = userItemRepository.findByUserIdWithItemId(user.getId(), placedRoomItem.getItem().getId())
                .orElseThrow(() -> new GlobalException(GameErrorCode.ITEM_NOT_OWNED));

        placedItemRepository.delete(placedRoomItem);

        userItem.updateIsPlaced(false);
        userItemRepository.save(userItem);
    }
}
