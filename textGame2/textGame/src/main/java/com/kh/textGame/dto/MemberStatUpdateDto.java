package com.kh.textGame.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberStatUpdateDto {
    private Integer level;
    private Long exp;
    private Long gold;
    private Integer floor;
    private Integer statPoints;
    private Integer maxHp;
    private Integer currentHp;
    private Integer maxMp;
    private Integer currentMp;
    private Integer atk;
    private Integer def;
    private Integer dex;
    private Integer luk;
}
