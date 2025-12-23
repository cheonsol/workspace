package com.kh.textGame.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;
    private String icon;

    private int damage;
    private boolean isHealing;
    private int healAmount;

    private int manaCost;
    private int cooldown;

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