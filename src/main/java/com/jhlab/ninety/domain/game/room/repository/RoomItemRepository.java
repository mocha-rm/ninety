package com.jhlab.ninety.domain.game.room.repository;

import com.jhlab.ninety.domain.game.room.entity.RoomItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomItemRepository extends JpaRepository<RoomItem, Long> {
}
