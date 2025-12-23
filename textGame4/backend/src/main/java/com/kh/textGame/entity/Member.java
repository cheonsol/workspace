    package com.kh.textGame.entity;

    import jakarta.persistence.*;
    import lombok.*;
    import java.util.ArrayList;
    import java.util.List;

    @Entity
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
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

        @Embedded
        @Builder.Default
        private Status status;


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