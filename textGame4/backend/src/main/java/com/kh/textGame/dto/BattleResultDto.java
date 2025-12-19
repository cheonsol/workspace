package com.kh.textGame.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BattleResultDto {
    private Long monsterId;
    private Long memberId;
    private int damage;         // 가해진 데미지
    private String message;     // 로그에 출력할 메시지
    private int monsterHp;      // 변경된 몬스터 HP
    private int playerMp;       // 변경된 플레이어 MP
    private boolean isMonsterDead; // 몬스터 사망 여부
}
