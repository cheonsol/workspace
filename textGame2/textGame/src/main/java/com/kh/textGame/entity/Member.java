package com.kh.textGame.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; // 로직 테스트용, 실제로는 메서드로 변경 권장

@Entity // 이 클래스가 DB 테이블이 된다는 뜻
@Getter @Setter
@NoArgsConstructor // 빈 생성자 자동 생성 (JPA 필수)
@Table(name = "member") // 테이블 이름 지정
public class Member {

    @Id // 기본키 (PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1, 2, 3... 자동 증가
    private Long id;

    // 계정 정보
    @Column(unique = true, nullable = false) // 중복 불가, 필수 입력
    private String userId;   // 프론트의 Uid

    @Column(nullable = false)
    private String password; // 암호화해서 저장해야 함

    @Column(nullable = false)
    private String nickname;

    // == 상태 정보 (Stats) ==
    private int level;       // LV
    private long exp;        // 경험치는 숫자가 커질 수 있음
    private long gold;       // 골드도 마찬가지

    private int floor;       // 현재 층수
    private int statPoints;  // 남은 스탯 포인트

    // == 전투 스탯 (현재 수치) ==
    private int maxHp;
    private int currentHp;
    private int maxMp;
    private int currentMp;

    private int atk;
    private int def;
    private int dex;
    private int luk;

    // == 베이스 스탯 (아이템 제외 순수 능력치) ==
    private int baseMaxHp;
    private int baseMaxMp;
    private int baseAtk;
    private int baseDef;
    private int baseDex;
    private int baseLuk;

    // 생성자 (Builder 패턴 사용 - 객체 생성을 깔끔하게 하기 위함)
    @Builder
    public Member(String userId, String password, String nickname) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;

        // 초기값 설정 (Zustand의 addUser 로직 참고)
        this.level = 1;
        this.exp = 0;
        this.gold = 0;
        this.floor = 1;
        this.statPoints = 0;

        this.maxHp = 100;
        this.currentHp = 100;
        this.maxMp = 100;
        this.currentMp = 100;

        this.atk = 10;
        this.def = 10;
        this.dex = 10;
        this.luk = 10;

        this.baseMaxHp = 100;
        this.baseMaxMp = 100;
        this.baseAtk = 10;
        this.baseDef = 10;
        this.baseDex = 10;
        this.baseLuk = 10;
    }

    @ManyToMany(fetch = FetchType.EAGER) // 로그인할 때 스킬도 바로 로딩
    @JoinTable(
            name = "member_skill", // 연결 테이블 이름
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private java.util.List<Skill> skills = new java.util.ArrayList<>();

    // 아이템 인벤토리 (MemberItem 엔티티를 통해 관리)
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<MemberItem> memberItems = new java.util.ArrayList<>();

    // 스킬 배우기 편의 메서드
    public void learnSkill(Skill skill) {
        if (!this.skills.contains(skill)) {
            this.skills.add(skill);
        }
    }
}