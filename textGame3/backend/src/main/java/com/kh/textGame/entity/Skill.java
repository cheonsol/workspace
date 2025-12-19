package com.kh.textGame.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 게임에 등장하는 모든 스킬의 원본 정보를 정의하는 엔티티입니다.
 *
 * @Entity: JPA 엔티티 클래스임을 나타냅니다.
 * @Getter/@Setter: Lombok 어노테이션.
 *                  - ✨ 제안: 스킬 마스터 데이터는 런타임에 변경될 일이 거의 없으므로, `@Setter`를 제거하여
 *                    불변 객체로 만드는 것이 더 안전합니다.
 * @NoArgsConstructor(access = AccessLevel.PROTECTED): JPA를 위한 기본 생성자를 protected로 생성합니다.
 * @Table(name = "skill"): 'skill' 테이블에 매핑됩니다.
 */
@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // 스킬 이름은 고유해야 합니다.
    private String name;

    private String description;
    private String icon;

    // ========== 스킬 효과 정보 ==========
    // ✨ 제안: 스킬 종류(공격, 회복, 버프 등)에 따라 상속 구조로 분리하는 것을 고려할 수 있습니다.
    private int damage;       // 데미지 (회복/버프 스킬의 경우 0)
    private boolean isHealing;  // 회복 스킬 여부
    private int healAmount;   // 체력 회복량 (공격/버프 스킬의 경우 0)

    // ========== 스킬 사용 조건 ==========
    private int manaCost;     // 마나 소모량
    private int cooldown;     // 재사용 대기시간 (턴 또는 초 단위)

    @Builder
    public Skill(String name, String description, String icon, int damage, int manaCost, int cooldown, boolean isHealing, int healAmount) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.damage = damage;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
        this.isHealing = isHealing;
        this.healAmount = healAmount;
    }
}

/*
 * ✨ 엔티티 설계 및 유지보수 제안 ✨
 *
 * 1. 상속 관계 매핑 (Inheritance Mapping):
 *    - `Item` 엔티티와 마찬가지로, `Skill` 엔티티도 종류에 따라 필요한 속성이 다릅니다.
 *      - **공격 스킬**: `damage`
 *      - **회복 스킬**: `healAmount`
 *      - **버프 스킬**: `statBoost`, `duration` 등
 *    - `@Inheritance` 전략을 사용하여 `Skill`을 추상 클래스로 만들고, `DamageSkill`, `HealingSkill`, `BuffSkill` 등
 *      구체적인 스킬 엔티티들이 이를 상속받도록 설계하면 객체 지향적이고 확장성 있는 모델을 만들 수 있습니다.
 *
 * 2. 불변성(Immutability) 강화:
 *    - 스킬의 기본 정보는 게임 서비스 중에 거의 변경되지 않습니다.
 *    - `@Setter`를 제거하고 모든 필드를 `final`로 선언하여 불변 객체로 설계하면,
 *      데이터가 의도치 않게 변경되는 것을 막아 시스템의 안정성을 높일 수 있습니다.
 *
 * 3. 스킬 효과의 다양성 모델링:
 *    - `damage`, `healAmount`와 같이 효과를 개별 컬럼으로 두는 방식은 간단하지만 확장성이 떨어집니다.
 *      (예: '3턴간 매초 10의 데미지' 같은 지속 효과나, '방어력 20% 증가' 같은 버프 효과를 표현하기 어렵습니다.)
 *    - 스킬 효과를 별도의 `SkillEffect` 엔티티나 `@Embeddable` 객체로 모델링하는 것을 고려할 수 있습니다.
 *      - `SkillEffect`는 `effectType` (e.g., `DIRECT_DAMAGE`, `HEAL`, `STAT_BUFF`), `target` (e.g., `SELF`, `ENEMY`),
 *        `amount`, `percentage`, `duration` 등의 필드를 가질 수 있습니다.
 *      - 하나의 스킬이 여러 효과를 가질 수 있으므로 `@OneToMany` 관계로 `List<SkillEffect>`를 포함할 수 있습니다.
 *      - 이 방식은 매우 유연하고 확장성 높은 스킬 시스템을 구축할 수 있게 해줍니다.
 *
 * 4. `equals()`와 `hashCode()` 재정의:
 *    - `id`나 고유한 비즈니스 키(`name`)를 기준으로 `equals()`와 `hashCode()`를 재정의하여
 *      컬렉션에서의 오작동을 방지하고 엔티티의 동등성을 올바르게 비교할 수 있도록 해야 합니다.
 */