package com.jhlab.ninety.domain.game.user.repository;

import com.jhlab.ninety.domain.game.user.entity.UserGameData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGameDataRepository extends JpaRepository<UserGameData, Long> {
    UserGameData findByUserId(Long userId);
}
