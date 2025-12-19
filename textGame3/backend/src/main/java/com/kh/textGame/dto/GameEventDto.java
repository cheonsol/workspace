package com.kh.textGame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameEventDto {
    private String type;
    private String message;
    private int damage;
    private boolean critical;
    private int healing;
    private long expGained;
    private long goldGained;
    private int levelGained;
}
