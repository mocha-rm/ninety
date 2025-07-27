package com.jhlab.ninety.domain.game.reward.repository;

import com.jhlab.ninety.domain.auth.entity.User;
import com.jhlab.ninety.domain.game.reward.entity.GameReward;
import com.jhlab.ninety.domain.habits.entity.Habits;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface GameRewardRepository extends JpaRepository<GameReward, Long> {
    Page<GameReward> findByUserId(Long userId, Pageable pageable);

    boolean existsByHabitsAndUserAndCreatedAtAfter(Habits habits, User user, LocalDateTime dateTime);
}
