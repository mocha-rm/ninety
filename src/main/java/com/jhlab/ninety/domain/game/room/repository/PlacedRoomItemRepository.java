package com.jhlab.ninety.domain.game.room.repository;

import com.jhlab.ninety.domain.game.room.entity.PlacedRoomItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlacedRoomItemRepository extends JpaRepository<PlacedRoomItem, Long> {
}
