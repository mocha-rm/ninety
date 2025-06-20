package com.jhlab.ninety.domain.habits.dto;

import com.jhlab.ninety.domain.habits.entity.Habits;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public class HabitsResponseDto {
    private final Long id;
    private final Long userId;
    private final String title;
    private final String description;
    private final LocalDate startAt;
    private final LocalDate endAt;
    private final Set<DayOfWeek> repeatDays;
    private final boolean isAlarmEnabled;
    private final LocalTime reminderTime;


    public static HabitsResponseDto toDto(Habits habits) {
        return new HabitsResponseDto(
                habits.getId(),
                habits.getUser().getId(),
                habits.getTitle(),
                habits.getDescription(),
                habits.getStartAt(),
                habits.getEndAt(),
                habits.getRepeatDays(),
                habits.isAlarmEnabled(),
                habits.getReminderTime()
        );
    }
}
