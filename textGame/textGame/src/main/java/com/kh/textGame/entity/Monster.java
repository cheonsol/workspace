package com.kh.textGame.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "monster")
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 몬스터 기본 정보
    @Column(nullable = false)
    private String name; // 이름 (고블린 등)

    private String img;  // 이미지 (이모지 저장 가능)
    private int floor;   // 출현 층수
    private boolean isBoss; // 보스 여부

    // 보상 정보
    private long exp;    // 경험치
    private long gold;   // 드랍 골드

    // 전투 스탯 (Max 값만 저장)
    private int maxHp;
    private int maxMp;
    private int atk;
    private int def;
    private int dex;
    private int luk;

    @Builder
    public Monster(String name, String img, int floor, boolean isBoss,
                   long exp, long gold, int maxHp, int maxMp,
                   int atk, int def, int dex, int luk) {
        this.name = name;
        this.img = img;
        this.floor = floor;
        this.isBoss = isBoss;
        this.exp = exp;
        this.gold = gold;
        this.maxHp = maxHp;
        this.maxMp = maxMp;
        this.atk = atk;
        this.def = def;
        this.dex = dex;
        this.luk = luk;
    }
}