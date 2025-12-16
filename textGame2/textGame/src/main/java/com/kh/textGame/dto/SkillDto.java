package com.kh.textGame.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillDto {
    private Long id;
    private String name;
    private String description;
    private String icon;
    private int damage;
    private int manaCost;
    private int cooldown;
    private boolean isHealing;
    private int healAmount;
}
