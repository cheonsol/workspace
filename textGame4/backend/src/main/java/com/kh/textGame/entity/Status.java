package com.kh.textGame.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@Builder
@Embeddable
public class Status {
        @Builder.Default private int level = 1;
        @Builder.Default private long exp = 0;
        @Builder.Default private long gold = 1000;
        @Builder.Default private int floor = 1;
        @Builder.Default private int statPoints = 5;

        @Builder.Default private int maxHp = 100;
        @Builder.Default private int currentHp = 100;
        @Builder.Default private int maxMp = 50;
        @Builder.Default private int currentMp = 50;

        @Builder.Default private int atk = 10;
        @Builder.Default private int def = 5;
        @Builder.Default private int dex = 5;
        @Builder.Default private int luk = 5;
    }


