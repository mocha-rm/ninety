package com.jhlab.ninety.domain.game.room.entity;

import com.jhlab.ninety.domain.game.room.type.ItemCategory;
import com.jhlab.ninety.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 상점 / 아이템
 */
@Getter
@Entity
@Table(name = "room_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class RoomItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemCategory category;

    private Integer price;
    private String imageUrl;

    public RoomItem(String name, String description, ItemCategory category, int price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public void updateRoomItem(String name, String description, ItemCategory category, int price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
