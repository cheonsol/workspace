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
    private int value;
    private String img;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemType type;

    @Column(nullable = false)
    private int price;

    @Builder
    public Item(String name, String description, String img, ItemType type,
                int price, int value) {
        this.name = name;
        this.description = description;
        this.img = img;
        this.type = type;
        this.price = price;
        this.value = value;
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