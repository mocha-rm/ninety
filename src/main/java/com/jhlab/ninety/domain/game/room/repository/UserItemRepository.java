package com.jhlab.ninety.domain.game.room.repository;

import com.jhlab.ninety.domain.game.room.entity.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserItemRepository extends JpaRepository<UserItem, Long> {
    List<UserItem> findByUserId(Long userId);
}
