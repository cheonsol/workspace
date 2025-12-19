package com.kh.textGame.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 게임에 등장하는 몬스터의 원본 데이터를 정의하는 엔티티입니다.
 * 전투 시 이 템플릿을 바탕으로 실제 전투에 참여하는 몬스터 객체가 생성됩니다.
 *
 * @Entity: JPA 엔티티 클래스임을 나타냅니다.
 * @Getter/@Setter: Lombok 어노테이션.
 *                  - ✨ 제안: 몬스터 마스터 데이터는 런타임에 변경될 일이 거의 없으므로, `@Setter`를 제거하여
 *                    불변 객체로 만드는 것이 더 안전합니다.
 * @NoArgsConstructor(access = AccessLevel.PROTECTED): JPA를 위한 기본 생성자를 protected로 생성합니다.
 * @Table(name = "monster"): 'monster' 테이블에 매핑됩니다.
 */
@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "monster")
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ==================== 몬스터 기본 정보 ====================
    @Column(nullable = false)
    private String name; // 몬스터 이름 (예: 고블린, 슬라임)

    private String img;  // 몬스터를 나타내는 이미지 (URL 또는 아이콘)
    private int floor;   // 출현 층수
    private boolean isBoss; // 보스 몬스터 여부

    // ==================== 처치 시 보상 정보 ====================
    private long exp;    // 획득 경험치
    private long gold;   // 획득 골드

    // ==================== 몬스터 전투 스탯 ====================
    // ✨ 제안: 스탯 관련 필드들을 별도의 @Embeddable 클래스(예: `Stats`)로 분리하면 구조가 명확해집니다.
    private int maxHp;
    private int maxMp;
    private int hp;
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
        this.hp = maxHp;
        this.maxMp = maxMp;
        this.atk = atk;
        this.def = def;
        this.dex = dex;
        this.luk = luk;
    }
    
    // ✨ 제안: 여기에 몬스터의 행동 로직(예: 스킬 사용)을 정의할 수 있습니다.
    // public Skill decideNextAction() { ... }
}

/*
 * ✨ 엔티티 설계 및 유지보수 제안 ✨
 *
 * 1. 불변성(Immutability) 강화:
 *    - 몬스터의 마스터 데이터는 게임 서비스 중에 변경될 일이 거의 없습니다.
 *    - `@Setter`를 제거하고 모든 필드를 `final`로 선언하여 불변 객체로 만들면,
 *      데이터가 의도치 않게 변경되는 것을 방지하여 시스템의 안정성을 크게 높일 수 있습니다.
 *    - 데이터 변경이 필요하다면 별도의 관리자용 API와 서비스 로직을 통해 이루어져야 합니다.
 *
 * 2. 스탯 정보 구조화 (@Embeddable):
 *    - `maxHp`, `atk` 등 여러 스탯 필드들을 `Stats`와 같은 `@Embeddable` 클래스로 분리하면
 *      `Monster` 엔티티가 더 깔끔해지고 스탯 관련 로직을 한 곳에 모을 수 있습니다.
 *      `@Embedded private Stats stats;`
 *
 * 3. 몬스터 스킬 및 드랍 아이템 연관관계:
 *    - 몬스터가 특정 스킬을 사용하거나 아이템을 드랍하는 기능을 구현하려면 연관관계 매핑이 필요합니다.
 *      - **스킬**: `@ManyToMany`를 사용하여 몬스터가 사용할 수 있는 스킬 목록을 연결할 수 있습니다.
 *        `private List<Skill> skills = new ArrayList<>();`
 *      - **드랍 아이템**: 몬스터가 여러 아이템을, 각기 다른 확률로 드랍한다면 별도의 `MonsterDropItem` 엔티티를
 *        만들어 `@OneToMany` 관계로 연결하는 것이 좋습니다. 이 엔티티는 몬스터, 아이템, 드랍 확률 등의 정보를 가집니다.
 *
 * 4. 전투용 인스턴스 분리:
 *    - `Monster` 엔티티는 몬스터의 '정의'입니다. 실제 전투가 벌어질 때는 이 엔티티를 직접 사용하기보다,
 *      전투에 필요한 정보(예: `currentHp`)를 포함하는 별도의 DTO나 비영속 객체를 생성하여 사용하는 것이 좋습니다.
 *      - `BattleMonster battleMonster = new BattleMonster(monsterEntity);`
 *    - 이렇게 하면 원본 마스터 데이터(엔티티)는 변경되지 않은 채로, 각 전투마다 독립적인 몬스터 상태를 관리할 수 있습니다.
 *
 * 5. `equals()`와 `hashCode()` 재정의:
 *    - 다른 엔티티들과 마찬가지로, `id`나 고유한 이름(`name`)을 기준으로 `equals()`와 `hashCode()`를
 *      재정의하여 컬렉션에서 안전하게 사용될 수 있도록 하는 것이 좋습니다.
 */