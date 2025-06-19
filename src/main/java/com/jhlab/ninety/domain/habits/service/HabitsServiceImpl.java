package com.jhlab.ninety.domain.habits.service;

import com.jhlab.ninety.domain.auth.entity.User;
import com.jhlab.ninety.domain.auth.service.UserService;
import com.jhlab.ninety.domain.habits.dto.HabitsRequestDto;
import com.jhlab.ninety.domain.habits.dto.HabitsResponseDto;
import com.jhlab.ninety.domain.habits.entity.Habits;
import com.jhlab.ninety.domain.habits.repository.HabitsRepository;
import com.jhlab.ninety.global.security.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .build();

        habitsRepository.save(habits);

        return HabitsResponseDto.toDto(habits);
    }
}
