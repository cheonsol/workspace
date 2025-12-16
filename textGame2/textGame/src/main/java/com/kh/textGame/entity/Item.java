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
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;
    private String img; // 아이템 이미지 (이모지 등)

    @Enumerated(EnumType.STRING) // Enum 이름을 DB에 저장
    @Column(nullable = false)
    private ItemType type; // 아이템 타입 (예: WEAPON, ARMOR, POTION)

    private int attackBoost; // 공격력 증가
    private int defenseBoost; // 방어력 증가
    private int healthBoost; // 최대 체력 증가
    private int manaBoost; // 최대 마나 증가
    private int healAmount; // 체력 회복량
    private int manaRestoreAmount; // 마나 회복량

    private int price; // 상점 판매/구매 가격

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
        WEAPON, ARMOR, POTION, CONSUMABLE, KEY, ETC
    }
}
