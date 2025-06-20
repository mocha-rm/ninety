package com.jhlab.ninety.domain.habits.service;

import com.jhlab.ninety.domain.habits.dto.HabitsRequestDto;
import com.jhlab.ninety.domain.habits.dto.HabitsResponseDto;
import com.jhlab.ninety.global.security.auth.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HabitsService {
    HabitsResponseDto createHabits(HabitsRequestDto requestDto, UserDetailsImpl userDetails);

    HabitsResponseDto findHabits(Long habitsId);

    Page<HabitsResponseDto> findAllHabits(Pageable pageable, UserDetailsImpl userDetails);

    HabitsResponseDto updateHabits(Long habitsId, HabitsRequestDto requestDto, UserDetailsImpl userDetails);

    void deleteHabits(Long habitsId, UserDetailsImpl userDetails);
}
