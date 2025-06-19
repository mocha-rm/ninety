package com.jhlab.ninety.domain.habits.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HabitsRequestDto {
    private final String title;
    private final String description;
}
