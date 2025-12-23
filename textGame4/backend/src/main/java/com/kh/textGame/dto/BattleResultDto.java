package com.kh.textGame.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BattleResultDto {
    private Long monsterId;
    private Long memberId;
    private int damage;
    private String message;
    private int monsterHp;
    private int playerMp;
    private boolean isMonsterDead;
}
