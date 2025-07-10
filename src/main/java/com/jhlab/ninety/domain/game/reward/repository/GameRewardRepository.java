package com.jhlab.ninety.domain.game.reward.repository;

import com.jhlab.ninety.domain.game.reward.entity.GameReward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRewardRepository extends JpaRepository<GameReward, Long> {
    Page<GameReward> findByUserId(Long userId, Pageable pageable);
}
