package com.jhlab.ninety.domain.game.character.entity;

import com.jhlab.ninety.domain.game.character.type.CharacterRarity;
import com.jhlab.ninety.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 캐릭터 (상점)
 */
@Getter
@Entity
@Table(name = "characters")
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Character extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CharacterRarity rarity;

    private Integer price;

    @Lob
    private String imageUrl;

    public Character(String name, String description, CharacterRarity rarity, int price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public void updateCharacter(String name, String description, CharacterRarity rarity, int price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
