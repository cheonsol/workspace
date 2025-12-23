    package com.kh.textGame.entity;

    import jakarta.persistence.*;
    import lombok.*;
    import java.util.ArrayList;
    import java.util.List;

    @Entity
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor // Builder 사용을 위해 추가
    @Builder // 클래스 레벨로 이동
    @Table(name = "member")
    public class Member {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true, nullable = false)
        private String userId;

        @Column(nullable = false)
        private String password;

        @Column(nullable = false, unique = true)
        private String nickname;

        // --- 초기값 설정 (Builder.Default) ---
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

        // 보존용 베이스 스탯
        @Builder.Default private int baseMaxHp = 100;
        @Builder.Default private int baseMaxMp = 50;
        @Builder.Default private int baseAtk = 10;
        @Builder.Default private int baseDef = 5;
        @Builder.Default private int baseDex = 5;
        @Builder.Default private int baseLuk = 5;

        @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
        @Builder.Default
        private List<MemberSkill> memberSkills = new ArrayList<>();

        @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
        @Builder.Default
        private List<MemberItem> memberItems = new ArrayList<>();


        public void learnSkill(Skill skill) {
            boolean alreadyLearned = this.memberSkills.stream()
                    .anyMatch(ms -> ms.getSkill().equals(skill));

            if (!alreadyLearned) {
                MemberSkill memberSkill = MemberSkill.builder()
                        .member(this)
                        .skill(skill)
                        .build();
                this.memberSkills.add(memberSkill);
            }
        }
    }