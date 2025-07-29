package com.jhlab.ninety.domain.game.room.repository;

import com.jhlab.ninety.domain.game.room.entity.RoomItem;
import com.jhlab.ninety.domain.game.room.type.ItemCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomItemRepository extends JpaRepository<RoomItem, Long> {
    Slice<RoomItem> findByCategory(ItemCategory category, Pageable pageable);
}
