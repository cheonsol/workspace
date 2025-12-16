package com.kh.textGame.dto;

import com.kh.textGame.entity.Monster;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonsterDto {
    private Long id;
    private String name;
    private String img;
    private int floor;
    private boolean isBoss;
    private long exp;
    private long gold;
    private int maxHp;
    private int maxMp;
    private int atk;
    private int def;
    private int dex;
    private int luk;
}
