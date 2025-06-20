package com.jhlab.ninety.global.common.exception.type;

import com.jhlab.ninety.global.common.exception.ExceptionType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum HabitsErrorCode implements ExceptionType {
    Habits_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 습관을 찾을 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public String getErrorCode() {
        return this.name();
    }
}
