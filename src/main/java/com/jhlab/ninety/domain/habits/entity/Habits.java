package com.jhlab.ninety.domain.habits.entity;

import com.jhlab.ninety.domain.auth.entity.User;
import com.jhlab.ninety.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Habits extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String title;

    @Column(length = 255)
    private String description;

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


    @Builder
    public Habits(String title, String description, LocalDateTime startAt, LocalDateTime endAt, int duration,
                  LocalTime reminderTime, boolean isAlarmEnabled, Set<DayOfWeek> repeatDays, User user) {
        this.title = title;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
        this.duration = duration;
        this.reminderTime = reminderTime;
        this.isAlarmEnabled = isAlarmEnabled;
        this.repeatDays = repeatDays;
        this.user = user;
    }
}
