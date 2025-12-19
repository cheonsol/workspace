package com.kh.textGame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BattleTurnResultDto {
    private MemberDto member;
    private MonsterDto monster;
    private List<GameEventDto> events;
    private boolean battleOver;
    private boolean playerWon;
}
