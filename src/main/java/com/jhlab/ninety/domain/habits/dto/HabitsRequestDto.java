package com.jhlab.ninety.domain.habits.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public class HabitsRequestDto {
    private final String title;
    private final String description;
    private final LocalDate startAt;
    private final Set<DayOfWeek> repeatDays;
    private final boolean isAlarmEnabled;
    private final LocalTime reminderTime; // 알림 언제 받을 건지
}
