package com.jhlab.ninety.domain.game.room.entity;

import com.jhlab.ninety.domain.auth.entity.User;
import com.jhlab.ninety.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 사용자 방
 */

@Getter
@Entity
@Table(name = "user_room", uniqueConstraints = @UniqueConstraint(columnNames = "user_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRoom extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "userRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlacedItem> items;

    public UserRoom(User user) {
        this.user = user;
    }
}
