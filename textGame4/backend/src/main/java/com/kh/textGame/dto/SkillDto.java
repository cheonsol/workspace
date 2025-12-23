package com.kh.textGame.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillDto {

    private Long id;

    @NotBlank(message = "스킬 이름은 필수입니다.")
    private String name;

    private String description;

    private String icon;

    @PositiveOrZero(message = "데미지는 0 이상이어야 합니다.")
    private int damage;

    private boolean isHealing;

    @PositiveOrZero(message = "회복량은 0 이상이어야 합니다.")
    private int healAmount;

    @PositiveOrZero(message = "마나 소모량은 0 이상이어야 합니다.")
    private int manaCost;

    @PositiveOrZero(message = "재사용 대기시간은 0 이상이어야 합니다.")
    private int cooldown;
}