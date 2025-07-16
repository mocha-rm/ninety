package com.jhlab.ninety.domain.game.room.dto.useritem;

import com.jhlab.ninety.domain.game.room.entity.UserItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserItemResponseDto {
    private final Long id;
    private final Long userId;
    private final Long itemId;
    private final boolean isPlaced;

    public static UserItemResponseDto toDto(UserItem userItem) {
        return new UserItemResponseDto(
                userItem.getId(),
                userItem.getUser().getId(),
                userItem.getItem().getId(),
                userItem.getIsPlaced()
        );
    }
}
