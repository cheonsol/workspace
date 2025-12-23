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
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;
    private String img;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemType type;

    private int attackBoost;
    private int defenseBoost;
    private int healthBoost;
    private int manaBoost;

    private int healAmount;
    private int manaRestoreAmount;

    @Column(nullable = false)
    private int price;

    @Builder
    public Item(String name, String description, String img, ItemType type,
                int attackBoost, int defenseBoost, int healthBoost, int manaBoost,
                int healAmount, int manaRestoreAmount, int price) {
        this.name = name;
        this.description = description;
        this.img = img;
        this.type = type;
        this.attackBoost = attackBoost;
        this.defenseBoost = defenseBoost;
        this.healthBoost = healthBoost;
        this.manaBoost = manaBoost;
        this.healAmount = healAmount;
        this.manaRestoreAmount = manaRestoreAmount;
        this.price = price;
    }

    public enum ItemType {
        WEAPON,
        ARMOR,
        POTION,
        CONSUMABLE,
        KEY,
        ETC
    }
}