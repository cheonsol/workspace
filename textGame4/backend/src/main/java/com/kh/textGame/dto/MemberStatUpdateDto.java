package com.kh.textGame.dto;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberStatUpdateDto {
    @PositiveOrZero(message = "레벨은 0 이상이어야 합니다.")
    private Integer level;

    @PositiveOrZero(message = "경험치는 0 이상이어야 합니다.")
    private Long exp;

    @PositiveOrZero(message = "골드는 0 이상이어야 합니다.")
    private Long gold;

    @PositiveOrZero(message = "층은 0 이상이어야 합니다.")
    private Integer floor;

    @PositiveOrZero(message = "스탯 포인트는 0 이상이어야 합니다.")
    private Integer statPoints;

    @PositiveOrZero
    private Integer maxHp;

    @PositiveOrZero
    private Integer currentHp;

    @PositiveOrZero
    private Integer maxMp;

    @PositiveOrZero
    private Integer currentMp;

    @PositiveOrZero
    private Integer atk;

    @PositiveOrZero
    private Integer def;

    @PositiveOrZero
    private Integer dex;

    @PositiveOrZero
    private Integer luk;
}