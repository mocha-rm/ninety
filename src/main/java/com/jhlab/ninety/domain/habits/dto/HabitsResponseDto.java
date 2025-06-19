package com.jhlab.ninety.domain.habits.dto;

import com.jhlab.ninety.domain.habits.entity.Habits;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@RequiredArgsConstructor
public class HabitsResponseDto {
    private final Long id;
    private final String title;
    private final String description;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;
    private final int duration;
    private final LocalTime reminderTime;
    private final boolean isAlarmEnabled;


    public static HabitsResponseDto toDto(Habits habits) {
        return new HabitsResponseDto(
                habits.getId(),
                habits.getTitle(),
                habits.getDescription(),
                habits.getStartAt(),
                habits.getEndAt(),
                habits.getDuration(),
                habits.getReminderTime(),
                habits.isAlarmEnabled()
        );
    }
}
