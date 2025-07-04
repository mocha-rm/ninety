package com.jhlab.ninety.domain.habits.service;

import com.jhlab.ninety.domain.auth.entity.User;
import com.jhlab.ninety.domain.auth.service.UserService;
import com.jhlab.ninety.domain.habits.dto.HabitsRequestDto;
import com.jhlab.ninety.domain.habits.dto.HabitsResponseDto;
import com.jhlab.ninety.domain.habits.entity.Habits;
import com.jhlab.ninety.domain.habits.repository.HabitsRepository;
import com.jhlab.ninety.global.common.exception.GlobalException;
import com.jhlab.ninety.global.common.exception.type.HabitsErrorCode;
import com.jhlab.ninety.global.security.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class HabitsServiceImpl implements HabitsService {
    private final UserService userService;
    private final HabitsRepository habitsRepository;

    @Override
    @Transactional
    public HabitsResponseDto createHabits(HabitsRequestDto requestDto, UserDetailsImpl userDetails) {
        User user = userService.getUserFromDB(userDetails.getUsername());

        Habits habits = Habits.builder()
                .title(requestDto.getTitle())
                .description(requestDto.getDescription())
                .startAt(requestDto.getStartAt())
                .endAt(requestDto.getStartAt().plusDays(90L))
                .repeatDays(requestDto.getRepeatDays())
                .isAlarmEnabled(requestDto.isAlarmEnabled())
                .reminderTime(requestDto.getReminderTime())
                .user(user)
                .build();

        habitsRepository.save(habits);

        return HabitsResponseDto.toDto(habits);
    }

    @Override
    @Transactional(readOnly = true)
    public HabitsResponseDto findHabits(Long habitsId) {
        Habits habits = habitsRepository.findByIdWithUserAndRepeatDays(habitsId)
                .orElseThrow(() -> new GlobalException(HabitsErrorCode.Habits_NOT_FOUND));

        return HabitsResponseDto.toDto(habits);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HabitsResponseDto> findAllHabits(Pageable pageable, UserDetailsImpl userDetails) {
        return habitsRepository.findAllByUser(userDetails.getUsername(), pageable).map(HabitsResponseDto::toDto);
    }

    @Override
    @Transactional
    public HabitsResponseDto updateHabits(Long habitsId, HabitsRequestDto requestDto, UserDetailsImpl userDetails) {
        Habits habits = checkAuthorization(habitsId, userDetails);

        habits.updateHabits(
                requestDto.getTitle(),
                requestDto.getDescription(),
                requestDto.getStartAt(),
                requestDto.getStartAt().plusDays(90L),
                requestDto.getReminderTime(),
                requestDto.isAlarmEnabled()
        );

        habitsRepository.save(habits);

        return HabitsResponseDto.toDto(habits);
    }

    @Override
    @Transactional
    public void deleteHabits(Long habitsId, UserDetailsImpl userDetails) {
        Habits habits = checkAuthorization(habitsId, userDetails);

        habitsRepository.delete(habits);
    }

    private Habits checkAuthorization(Long habitsId, UserDetailsImpl userDetails) {
        User user = userService.getUserFromDB(userDetails.getUsername());

        Habits habits = habitsRepository.findByIdWithUserAndRepeatDays(habitsId)
                .orElseThrow(() -> new GlobalException(HabitsErrorCode.Habits_NOT_FOUND));

        if (!Objects.equals(habits.getUser().getId(), user.getId())) {
            throw new AccessDeniedException("");
        }

        return habits;
    }
}
