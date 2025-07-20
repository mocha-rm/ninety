package com.jhlab.ninety.domain.game.room.entity;

import com.jhlab.ninety.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 배치된 아이템
 */
@Getter
@Entity
@Table(name = "placed_room_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlacedItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_room_id")
    private UserRoom userRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private RoomItem item;

    private double posX;
    private double posY;
    private double rotation;

    public PlacedItem(UserRoom userRoom, RoomItem item, double posX, double posY, double rotation) {
        this.userRoom = userRoom;
        this.item = item;
        this.posX = posX;
        this.posY = posY;
        this.rotation = rotation;
    }

    public void updatePosition(double posX, double posY, double rotation) {
        this.posX = posX;
        this.posY = posY;
        this.rotation = rotation;
    }
}
