package com.jhlab.ninety.domain.game.room.repository;

import com.jhlab.ninety.domain.game.room.entity.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserItemRepository extends JpaRepository<UserItem, Long> {
    List<UserItem> findByUserId(Long userId);

    @Query("SELECT ui FROM UserItem ui WHERE ui.user.id = :userId AND ui.item = :itemId")
    Optional<UserItem> findByUserIdWithItemId(@Param("userId") Long userId, @Param("itemId") Long itemId);
}
