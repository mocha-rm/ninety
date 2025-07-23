package com.jhlab.ninety.domain.game.character.repository;

import com.jhlab.ninety.domain.game.character.entity.UserCharacter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCharacterRepository extends JpaRepository<UserCharacter, Long> {
    Slice<UserCharacter> findByUserId(Long userId, Pageable pageable);

    Optional<UserCharacter> findByUserIdAndIsActiveTrue(Long userId);
}
