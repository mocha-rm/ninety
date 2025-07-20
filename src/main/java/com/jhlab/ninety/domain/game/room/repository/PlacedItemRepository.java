package com.jhlab.ninety.domain.game.room.repository;

import com.jhlab.ninety.domain.game.room.entity.PlacedItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlacedItemRepository extends JpaRepository<PlacedItem, Long> {
}
