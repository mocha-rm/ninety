package com.jhlab.ninety.domain.game.user.repository;

import com.jhlab.ninety.domain.game.user.entity.UserGameData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserGameDataRepository extends JpaRepository<UserGameData, Long> {
    Optional<UserGameData> findByUserId(Long userId);
}
