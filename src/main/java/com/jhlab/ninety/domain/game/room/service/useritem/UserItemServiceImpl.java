package com.jhlab.ninety.domain.game.room.service.useritem;

import com.jhlab.ninety.domain.auth.entity.User;
import com.jhlab.ninety.domain.auth.service.UserService;
import com.jhlab.ninety.domain.game.room.dto.useritem.UserItemResponseDto;
import com.jhlab.ninety.domain.game.room.entity.RoomItem;
import com.jhlab.ninety.domain.game.room.entity.UserItem;
import com.jhlab.ninety.domain.game.room.repository.RoomItemRepository;
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
    private final RoomItemRepository roomItemRepository;

    private final UserService userService;
    private final UserGameDataService userGameDataService;

    @Override
    @Transactional
    public void buyItem(Long itemId, Long userId) {
        RoomItem item = roomItemRepository.findById(itemId)
                .orElseThrow(() -> new GlobalException(GameErrorCode.ITEM_NOT_FOUND));

        User user = userService.getUserFromDB(userId);

        UserGameData userGameData = userGameDataService.getUserGameDataFromDB(userId);

        if (userItemRepository.findByUserIdWithItemId(userId, itemId).isPresent()) {
            throw new GlobalException(GameErrorCode.ALREADY_OWNED_ITEM);
        }

        if (item.getPrice() > userGameData.getCoins()) {
            throw new GlobalException(GameErrorCode.NOT_ENOUGH_POINT);
        }

        // TODO : UserGameData 에 구입 결과 반영하기 (코인정보)

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
    public Slice<UserItemResponseDto> getUserItems(Long userId, ItemCategory category, Pageable pageable) {
        User user = userService.getUserFromDB(userId);

        Slice<UserItem> userItems;

        if (category != null) {
            userItems = userItemRepository.findByUserIdAndItemCategory(user.getId(), category, pageable);
        } else {
            userItems = userItemRepository.findByUserId(user.getId(), pageable);
        }

        return userItems.map(UserItemResponseDto::toDto);
    }
}
