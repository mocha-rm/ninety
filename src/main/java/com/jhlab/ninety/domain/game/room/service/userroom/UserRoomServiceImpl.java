package com.jhlab.ninety.domain.game.room.service.userroom;

import com.jhlab.ninety.domain.auth.entity.User;
import com.jhlab.ninety.domain.auth.service.UserService;
import com.jhlab.ninety.domain.game.room.dto.userroom.UserRoomResponseDto;
import com.jhlab.ninety.domain.game.room.entity.UserRoom;
import com.jhlab.ninety.domain.game.room.repository.UserRoomRepository;
import com.jhlab.ninety.global.common.exception.GlobalException;
import com.jhlab.ninety.global.common.exception.type.GameErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoomServiceImpl implements UserRoomService {

    private final UserRoomRepository userRoomRepository;
    private final UserService userService;

    @Transactional(readOnly = true)
    @Override
    public UserRoomResponseDto getUserRoom(Long userId) {
        User user = userService.getUserFromDB(userId);

        UserRoom userRoom = userRoomRepository.findByUserId(user.getId()).orElseThrow(
                () -> new GlobalException(GameErrorCode.USER_ROOM_NOT_FOUND)
        );
        return new UserRoomResponseDto(userRoom);
    }

    @Transactional
    @Override
    public void createInitialUserRoom(Long userId) {
        User user = userService.getUserFromDB(userId);
        Optional<UserRoom> userRoom = userRoomRepository.findByUserId(user.getId());

        if (userRoom.isPresent()) {
            throw new GlobalException(GameErrorCode.USER_ROOM_EXIST);
        }

        userRoomRepository.save(new UserRoom(user));
    }
}
