package com.jhlab.ninety.domain.habits.repository;

import com.jhlab.ninety.domain.auth.entity.User;
import com.jhlab.ninety.domain.habits.entity.Habits;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitsRepository extends JpaRepository<Habits, Long> {
    List<Habits> findAllByUser(User user);
}
