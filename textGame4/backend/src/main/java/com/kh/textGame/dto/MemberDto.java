package com.kh.textGame.dto;

import com.kh.textGame.entity.Item.ItemType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemberDto {
    private Long id;
    private String userId;
    private String nickname;

    private int level;
    private long exp;
    private long gold;
    private int floor;
    private int statPoints;

    private int currentHp;
    private int currentMp;

    private int maxHp;
    private int maxMp;
    private int atk;
    private int def;
    private int dex;
    private int luk;

    private int baseMaxHp;
    private int baseMaxMp;
    private int baseAtk;
    private int baseDef;
    private int baseDex;
    private int baseLuk;

    private List<SkillDto> skills;
    private List<MemberItemDto> inventory;

    @Getter
    @Setter
    public static class MemberItemDto {
        private Long id;
        private int quantity;
        private boolean equipped;

        private Long itemId;
        private String itemName;
        private String itemDescription;
        private String itemImg;
        private ItemType itemType;
        private int itemPrice;

        private int itemAttackBoost;
        private int itemDefenseBoost;
        private int itemHealthBoost;
        private int itemManaBoost;
        private int itemHealAmount;
        private int itemManaRestoreAmount;
    }
}