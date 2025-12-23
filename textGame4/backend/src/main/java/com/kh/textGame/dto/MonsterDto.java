package com.kh.textGame.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonsterDto {

    private Long id;

    @NotBlank(message = "몬스터 이름은 필수입니다.")
    private String name;

    private String img;

    @Positive(message = "층은 1 이상의 값이어야 합니다.")
    private int floor;

    private boolean isBoss;

    @PositiveOrZero(message = "경험치는 0 이상이어야 합니다.")
    private long exp;

    @PositiveOrZero(message = "골드는 0 이상이어야 합니다.")
    private long gold;

    @Positive(message = "최대 체력은 1 이상이어야 합니다.")
    private int maxHp;

    @PositiveOrZero(message = "최대 마나는 0 이상이어야 합니다.")
    private int maxMp;

    @PositiveOrZero(message = "공격력은 0 이상이어야 합니다.")
    private int atk;

    @PositiveOrZero(message = "방어력은 0 이상이어야 합니다.")
    private int def;

    @PositiveOrZero(message = "민첩성은 0 이상이어야 합니다.")
    private int dex;

    @PositiveOrZero(message = "행운은 0 이상이어야 합니다.")
    private int luk;
}