package com.kh.textGame.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; // 추가

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;        // 스킬 이름

    private String description; // 설명
    private String icon;        // 아이콘 (이모지)

    private int damage;         // 데미지
    private int manaCost;       // 마나 소모량
    private int cooldown;       // 쿨타임 (턴 단위)

    // 힐링 스킬 관련
    private boolean isHealing;  // 힐 스킬 여부
    private int healAmount;     // 회복량

    @Builder
    public Skill(String name, String description, String icon, int damage, int manaCost, int cooldown, boolean isHealing, int healAmount) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.damage = damage;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
        this.isHealing = isHealing;
        this.healAmount = healAmount;
    }
}