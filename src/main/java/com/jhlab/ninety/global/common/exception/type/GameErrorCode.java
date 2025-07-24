package com.jhlab.ninety.global.common.exception.type;

import com.jhlab.ninety.global.common.exception.ExceptionType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GameErrorCode implements ExceptionType {
    USER_GAME_DATA_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자의 게임 데이터를 찾을 수 없습니다."),
    USER_GAME_DATA_EXIST(HttpStatus.CONFLICT, "이미 게임 데이터가 있습니다."),
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "아이템을 찾을 수 없습니다."),
    NOT_ENOUGH_COINS(HttpStatus.BAD_REQUEST, "코인이 부족합니다."),

    ALREADY_OWNED_ITEM(HttpStatus.CONFLICT, "이미 보유하고 있는 아이템입니다."),
    ITEM_NOT_OWNED(HttpStatus.FORBIDDEN, "보유하고 있지 않은 아이템입니다."),

    ITEM_ALREADY_PLACED(HttpStatus.CONFLICT, "이미 방에 배치된 아이템입니다."),
    ITEM_NOT_PLACED(HttpStatus.NOT_FOUND, "방에 배치되지 않은 아이템입니다."),
    PLACED_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "배치된 아이템을 찾을 수 없습니다."),

    USER_ROOM_EXIST(HttpStatus.CONFLICT, "사용자의 방이 이미 있습니다."),
    USER_ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자의 방을 찾을 수 없습니다."),

    CHARACTER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 캐릭터를 찾을 수 없습니다."),
    CHARACTER_DUPLICATED(HttpStatus.CONFLICT, "중복된 캐릭터 입니다. (같은 이름의 캐릭터를 생성할 수 없습니다.)"),
    CHARACTER_NOT_OWNED(HttpStatus.FORBIDDEN, "보유하고 있지 않은 캐릭터입니다."),
    CHARACTER_ALREADY_OWNED(HttpStatus.CONFLICT, "이미 보유한 캐릭터입니다."),
    PERMISSION_DENIED(HttpStatus.FORBIDDEN, "권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public String getErrorCode() {
        return this.name();
    }
}
