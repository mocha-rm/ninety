package com.jhlab.ninety.domain.game.character.repository;

import com.jhlab.ninety.domain.game.character.entity.Character;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    Slice<Character> findAllById(Pageable pageable);
}
