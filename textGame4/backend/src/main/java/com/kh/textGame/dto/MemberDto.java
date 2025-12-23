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