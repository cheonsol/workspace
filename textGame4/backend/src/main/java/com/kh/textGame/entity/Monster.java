package com.kh.textGame.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "monster")
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String img;
    private int floor;
    private boolean isBoss;

    private long exp;
    private long gold;

    private int maxHp;
    private int maxMp;
    private int currentHp;
    private int currentMp;
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

    public void takeDamage(int damage) {
        this.currentHp = Math.max(0, currentHp - damage);
    }
}