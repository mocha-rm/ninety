package com.jhlab.ninety.domain.habits.service;

import com.jhlab.ninety.domain.habits.dto.HabitsRequestDto;
import com.jhlab.ninety.domain.habits.dto.HabitsResponseDto;
import com.jhlab.ninety.global.security.auth.UserDetailsImpl;

public interface HabitsService {
    HabitsResponseDto createHabits(HabitsRequestDto requestDto, UserDetailsImpl userDetails);
}
