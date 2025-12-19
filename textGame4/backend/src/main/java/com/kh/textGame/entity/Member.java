package com.kh.textGame.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

/**
 * 게임 사용자를 나타내는 핵심 엔티티입니다.
 * 계정 정보, 캐릭터 스탯, 인벤토리, 스킬 등 사용자 관련 모든 데이터를 포함합니다.
 *
 * @Entity: JPA 엔티티 클래스임을 나타냅니다.
 * @Getter/@Setter: Lombok 어노테이션.
 *                  - ✨ 제안: `@Setter`는 엔티티의 상태를 외부에서 무분별하게 변경할 수 있게 하므로,
 *                    제거하고 명확한 비즈니스 메소드(예: `levelUp`, `takeDamage`)를 통해 상태를 변경하는 것이 매우 권장됩니다.
 * @NoArgsConstructor: JPA를 위한 기본 생성자를 생성합니다.
 * @Table(name = "member"): 'member' 테이블에 매핑됩니다.
 */
@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ==================== 계정 정보 ====================
    @Column(unique = true, nullable = false)
    private String userId; // 로그인 ID

    @Column(nullable = false)
    private String password; // 암호화된 비밀번호

    @Column(nullable = false, unique = true)
    private String nickname; // 게임 내 닉네임, 중복 불가

    // ✨ 제안: 역할(Role) 필드 추가. 예: private String role; // "ROLE_USER", "ROLE_ADMIN"

    // ==================== 캐릭터 성장 및 상태 정보 ====================
    private int level;
    private long exp; // 경험치는 큰 숫자가 될 수 있으므로 long 타입 사용
    private long gold;

    private int floor;      // 현재 위치한 층
    private int statPoints; // 분배 가능한 스탯 포인트

    // ==================== 전투 스탯 (아이템 효과 포함 최종 수치) ====================
    // ✨ 제안: 스탯 관련 필드들을 별도의 @Embeddable 클래스로 분리하면 구조가 명확해집니다.
    private int maxHp;
    private int currentHp;
    private int maxMp;
    private int currentMp;

    private int atk;
    private int def;
    private int dex;
    private int luk;

    // ==================== 순수 스탯 (사용자가 분배한 기본 수치) ====================
    private int baseMaxHp;
    private int baseMaxMp;
    private int baseAtk;
    private int baseDef;
    private int baseDex;
    private int baseLuk;


    // ================== 연관 관계 매핑 ==================

    /**
     * Member와 Skill의 다대다(Many-to-Many) 관계를 설정합니다.
     * 한 명의 사용자는 여러 스킬을 가질 수 있고, 하나의 스킬은 여러 사용자가 가질 수 있습니다.
     *
     * `fetch = FetchType.EAGER`: 즉시 로딩. `Member`를 조회할 때 연관된 `Skill` 목록도 항상 함께 조회합니다.
     *                          - ✨ 제안: 스킬 목록이 항상 필요한 것이 아니라면 `FetchType.LAZY`(지연 로딩)로 변경하여
     *                            성능을 최적화하는 것이 좋습니다. N+1 문제의 원인이 될 수 있습니다.
     *
     * @JoinTable: 다대다 관계를 위한 중간 테이블(연결 테이블)을 정의합니다.
     *  - `name = "member_skill"`: 생성될 중간 테이블의 이름입니다.
     *  - `joinColumns`: 현재 엔티티(`Member`)를 참조하는 외래 키 컬럼을 정의합니다.
     *  - `inverseJoinColumns`: 반대쪽 엔티티(`Skill`)를 참조하는 외래 키 컬럼을 정의합니다.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "member_skill",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills = new ArrayList<>();

    /**
     * Member(1)와 MemberItem(N)의 일대다 관계를 설정합니다.
     * `MemberItem`은 `Member`와 `Item`의 다대다 관계를 풀기 위한 중간 엔티티입니다.
     *
     * `mappedBy = "member"`: 연관관계의 주인이 `MemberItem` 엔티티의 `member` 필드임을 명시합니다.
     * `cascade = CascadeType.ALL`: `Member`의 영속성 상태 변화를 `MemberItem`에도 전파합니다. (멤버가 삭제되면 인벤토리도 삭제)
     * `orphanRemoval = true`: `memberItems` 리스트에서 아이템을 제거하면 DB에서도 해당 `MemberItem`이 삭제됩니다.
     */
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberItem> memberItems = new ArrayList<>();

    // ================== 생성자 및 편의 메소드 ==================

    @Builder
    public Member(String userId, String password, String nickname) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;

        // 신규 캐릭터 생성 시 초기 스탯 설정
        this.level = 1;
        this.exp = 0;
        this.gold = 1000; // 초기 자금
        this.floor = 1;
        this.statPoints = 5; // 초기 스탯 포인트

        this.baseMaxHp = 100; this.maxHp = 100; this.currentHp = 100;
        this.baseMaxMp = 50;  this.maxMp = 50;  this.currentMp = 50;
        this.baseAtk = 10;    this.atk = 10;
        this.baseDef = 5;     this.def = 5;
        this.baseDex = 5;     this.dex = 5;
        this.baseLuk = 5;     this.luk = 5;
    }

    /**
     * 연관관계 편의 메소드: 스킬을 배우는 로직
     */
    public void learnSkill(Skill skill) {
        if (!this.skills.contains(skill)) {
            this.skills.add(skill);
        }
    }
    
    // ✨ 제안: 여기에 다양한 비즈니스 로직 메소드를 추가할 수 있습니다.
    // public void gainExp(long earnedExp) { ... 레벨업 처리 ... }
    // public void takeDamage(int damage) { ... 체력 감소 및 사망 처리 ... }
    // public void recalculateStats() { ... 장비 변경 시 최종 스탯 재계산 ... }
}

/*
 * ✨ 엔티티 설계 및 유지보수 제안 ✨
 *
 * 1. `@Setter` 제거 및 비즈니스 메소드 도입:
 *    - 엔티티의 모든 필드에 `@Setter`를 열어두는 것은 객체의 상태가 무분별하게 변경될 위험을 만듭니다.
 *    - `gainExp(long exp)`, `useGold(long amount)`, `distributeStat(StatType type, int value)` 등
 *      명확한 의도를 가진 메소드를 통해 상태를 변경하도록 설계하면, 도메인 로직이 엔티티에 캡슐화되어
 *      코드가 훨씬 안정적이고 테스트하기 쉬워집니다.
 *
 * 2. 스탯 필드 분리 (@Embeddable):
 *    - `atk`, `def`, `baseAtk`, `baseDef` 등 수많은 스탯 관련 필드는 별도의 클래스로 분리하는 것이 좋습니다.
 *    - `@Embeddable` 어노테이션으로 `Stats` 클래스를 만들고, `Member` 엔티티에는 `@Embedded`를 사용하여 포함시킬 수 있습니다.
 *      `@Embedded private Stats baseStats;`
 *      `@Embedded @AttributeOverride(...) private Stats finalStats;`
 *    - 이렇게 하면 `Member` 엔티티가 훨씬 깔끔해지고, 스탯 관련 로직을 `Stats` 클래스에서 처리할 수 있습니다.
 *
 * 3. Spring Security 연동 (`UserDetails` 구현):
 *    - `Member` 엔티티가 Spring Security의 `UserDetails` 인터페이스를 구현하도록 만들면,
 *      인증/인가 프로세스와의 통합이 매우 자연스러워집니다.
 *    - `getAuthorities()`, `isAccountNonExpired()` 등 `UserDetails`의 메소드들을 구현해야 합니다.
 *    - `getAuthorities()` 메소드에서는 `role` 필드를 기반으로 `GrantedAuthority` 컬렉션을 반환하도록 구현합니다.
 *
 * 4. 지연 로딩(Lazy Loading) 적극 사용:
 *    - `skills` 필드의 `FetchType.EAGER`는 성능 문제를 일으킬 가능성이 높습니다.
 *      `Member`를 조회할 때마다 불필요하게 `skills` 테이블까지 조인하기 때문입니다.
 *    - `@ManyToMany`, `@OneToMany`는 기본적으로 `FetchType.LAZY`로 설정하고,
 *      스킬 정보가 필요한 경우에만 서비스 계층에서 JPQL Fetch Join(`join fetch m.skills`)이나
 *      `@EntityGraph`를 사용하여 한 번의 쿼리로 함께 가져오는 것이 성능 최적화의 핵심입니다.
 */