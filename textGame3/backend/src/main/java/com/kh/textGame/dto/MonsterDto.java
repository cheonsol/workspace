package com.kh.textGame.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonsterDto {

    private Long id;

    @NotBlank(message = "몬스터 이름은 필수입니다.")
    private String name;

    private int hp;
    private int maxHp;
    private int maxMp;

    @PositiveOrZero(message = "공격력은 0 이상이어야 합니다.")
    private int atk;
    private int def;
    private int dex;
    private int luk;

    @PositiveOrZero(message = "경험치는 0 이상이어야 합니다.")
    private long exp;

    @PositiveOrZero(message = "골드는 0 이상이어야 합니다.")
    private long gold;

    @Positive(message = "층은 1 이상의 값이어야 합니다.")
    private int floor;

    private boolean isBoss;

    private String img;
}