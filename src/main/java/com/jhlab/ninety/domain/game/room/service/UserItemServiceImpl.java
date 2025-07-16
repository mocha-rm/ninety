package com.jhlab.ninety.domain.game.room.service;

import com.jhlab.ninety.domain.auth.entity.User;
import com.jhlab.ninety.domain.auth.service.UserService;
import com.jhlab.ninety.domain.game.room.dto.placeditem.PlaceItemRequestDto;
import com.jhlab.ninety.domain.game.room.dto.useritem.UserItemResponseDto;
import com.jhlab.ninety.domain.game.room.entity.RoomItem;
import com.jhlab.ninety.domain.game.room.entity.UserItem;
import com.jhlab.ninety.domain.game.room.repository.PlacedRoomItemRepository;
import com.jhlab.ninety.domain.game.room.repository.UserItemRepository;
import com.jhlab.ninety.domain.game.room.type.ItemCategory;
import com.jhlab.ninety.domain.game.user.entity.UserGameData;
import com.jhlab.ninety.domain.game.user.service.UserGameDataService;
import com.jhlab.ninety.global.common.exception.GlobalException;
import com.jhlab.ninety.global.common.exception.type.GameErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserItemServiceImpl implements UserItemService {
    private final UserItemRepository userItemRepository;
    private final PlacedRoomItemRepository placedRoomItemRepository;
    private final RoomItemService roomItemService;
    private final UserService userService;
    private final UserGameDataService userGameDataService;

    @Override
    @Transactional
    public void buyItem(Long itemId, Long userId) {
        User user = userService.getUserFromDB(userId);
        RoomItem item = roomItemService.getRoomItemFromDB(itemId);
        UserGameData userGameData = userGameDataService.getUserGameDataFromDB(userId);

        if (userItemRepository.findByUserIdWithItemId(userId, itemId).isPresent()) {
            throw new GlobalException(GameErrorCode.ALREADY_OWNED_ITEM);
        }

        if (item.getPrice() > userGameData.getCoins()) {
            throw new GlobalException(GameErrorCode.NOT_ENOUGH_POINT);
        }

        UserItem userItem = new UserItem(
                user,
                item,
                false
        );

        userItemRepository.save(userItem);
    }

    @Override
    @Transactional(readOnly = true)
    public UserItemResponseDto getUserItem(Long userItemId) {
        UserItem item = userItemRepository.findById(userItemId)
                .orElseThrow(() -> new GlobalException(GameErrorCode.ITEM_NOT_OWNED));

        return UserItemResponseDto.toDto(item);
    }

    @Override
    @Transactional(readOnly = true)
    public Slice<UserItemResponseDto> getUserItems(String email, ItemCategory category, Pageable pageable) {
        // TODO: 보유 아이템 목록 조회 로직 구현
        return null;
    }

    @Override
    @Transactional
    public void placeItem(Long userItemId, PlaceItemRequestDto requestDto, String email) {
        // TODO: 아이템 배치 로직 구현
    }

    @Override
    @Transactional
    public void moveItem(Long placedItemId, PlaceItemRequestDto requestDto, String email) {
        // TODO: 배치된 아이템 위치 수정 로직 구현
    }

    @Override
    @Transactional
    public void removeItem(Long placedItemId, String email) {
        // TODO: 배치된 아이템 해제 로직 구현
    }
}
