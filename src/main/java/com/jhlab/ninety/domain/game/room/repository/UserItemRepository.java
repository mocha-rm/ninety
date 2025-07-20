package com.jhlab.ninety.domain.game.room.repository;

import com.jhlab.ninety.domain.game.room.entity.UserItem;
import com.jhlab.ninety.domain.game.room.type.ItemCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserItemRepository extends JpaRepository<UserItem, Long> {
    Slice<UserItem> findByUserId(Long userId, Pageable pageable);

    Slice<UserItem> findByUserIdAndItemCategory(Long userId, ItemCategory category, Pageable pageable);

    @Query("SELECT ui FROM UserItem ui WHERE ui.user.id = :userId AND ui.item.id = :itemId")
    Optional<UserItem> findByUserIdWithItemId(@Param("userId") Long userId, @Param("itemId") Long itemId);
}
