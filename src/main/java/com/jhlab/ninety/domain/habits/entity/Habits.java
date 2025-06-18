package com.jhlab.ninety.domain.habits.entity;

import com.jhlab.ninety.domain.auth.entity.User;
import com.jhlab.ninety.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Entity
public class Habits extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String desc;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    private int duration;

    private LocalTime reminderTime;

    private boolean isAlarmEnabled;

    @ElementCollection
    @CollectionTable(name = "habit_repeat_days", joinColumns = @JoinColumn(name = "habit_id"))
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> repeatDays;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
