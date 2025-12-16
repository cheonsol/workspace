package com.kh.textGame.dto;

import com.kh.textGame.entity.Item.ItemType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private Long id;
    private String name;
    private String description;
    private String img;
    private ItemType type;
    private int attackBoost;
    private int defenseBoost;
    private int healthBoost;
    private int manaBoost;
    private int healAmount;
    private int manaRestoreAmount;
    private int price;
}
