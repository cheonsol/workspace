package com.kh.textGame.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillDto {

    private Long id;

    private String name;

    private String description;

    private String icon;

    private int value;

    private int manaCost;

    private int cooldown;
}