package com.jhlab.ninety.domain.game.character.repository;

import com.jhlab.ninety.domain.game.character.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    Optional<Character> findByName(String name);
}
