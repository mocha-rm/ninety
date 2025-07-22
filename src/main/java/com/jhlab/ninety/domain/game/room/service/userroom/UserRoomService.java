package com.jhlab.ninety.domain.game.room.service.userroom;

import com.jhlab.ninety.domain.game.room.dto.userroom.UserRoomResponseDto;

public interface UserRoomService {
    UserRoomResponseDto getUserRoom(Long userId);

    void createInitialUserRoom(Long userId);
}
