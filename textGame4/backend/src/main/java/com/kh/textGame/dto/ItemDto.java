package com.kh.textGame.dto;

import com.kh.textGame.entity.Item.ItemType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {

    private Long id;

    @NotBlank(message = "아이템 이름은 필수입니다.")
    @Size(max = 50, message = "아이템 이름은 50자를 넘을 수 없습니다.")
    private String name;

    @Size(max = 255, message = "아이템 설명은 255자를 넘을 수 없습니다.")
    private String description;

    private String img;

    @NotNull(message = "아이템 타입을 지정해야 합니다.")
    private ItemType type;

    @PositiveOrZero(message = "공격력 증가는 0 이상이어야 합니다.")
    private int attackBoost;

    @PositiveOrZero(message = "방어력 증가는 0 이상이어야 합니다.")
    private int defenseBoost;

    @PositiveOrZero(message = "체력 증가는 0 이상이어야 합니다.")
    private int healthBoost;

    @PositiveOrZero(message = "마나 증가는 0 이상이어야 합니다.")
    private int manaBoost;

    @PositiveOrZero(message = "체력 회복량은 0 이상이어야 합니다.")
    private int healAmount;

    @PositiveOrZero(message = "마나 회복량은 0 이상이어야 합니다.")
    private int manaRestoreAmount;

    @PositiveOrZero(message = "가격은 0 이상이어야 합니다.")
    private int price;
}