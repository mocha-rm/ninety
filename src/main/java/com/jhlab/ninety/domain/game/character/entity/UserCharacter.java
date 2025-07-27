package com.jhlab.ninety.domain.game.character.entity;

import com.jhlab.ninety.domain.auth.entity.User;
import com.jhlab.ninety.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 소유 캐릭터
 */

@Getter
@Entity
@Table(name = "user_characters")
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCharacter extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id")
    private Character character;

    private String nickname;
    private Integer level;
    private Integer experience;
    private Integer happiness;
    private Boolean isActive;

    private static final int MAX_EXPERIENCE_PER_LEVEL = 100;

    public UserCharacter(String nickname, int level, int experience, int happiness, boolean isActive, User user, Character character) {
        this.nickname = nickname;
        this.level = level;
        this.experience = experience;
        this.happiness = happiness;
        this.isActive = isActive;
        this.user = user;
        this.character = character;
    }

    public void updateHappiness(int value) {
        this.happiness = value;
    }

    public void updateExperience(int value) {
        this.experience = value;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateActivateStatus(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean canLevelUp() {
        return this.experience >= MAX_EXPERIENCE_PER_LEVEL;
    }

    public void levelUp() {
        this.level++;
        this.experience -= MAX_EXPERIENCE_PER_LEVEL;
    }
}
