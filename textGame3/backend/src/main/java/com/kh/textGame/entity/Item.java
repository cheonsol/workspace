package com.kh.textGame.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 게임에 등장하는 모든 아이템의 원본 정보를 정의하는 엔티티입니다.
 * 플레이어가 소유한 개별 아이템이 아닌, 아이템 자체의 템플릿 또는 마스터 데이터 역할을 합니다.
 * (예: "체력 포션"이라는 아이템의 속성, "강철 검"이라는 아이템의 속성)
 *
 * @Entity: JPA 엔티티 클래스임을 나타냅니다.
 * @Getter/@Setter: Lombok 어노테이션.
 *                  - ✨ 제안: 아이템 마스터 데이터는 런타임에 변경될 일이 거의 없으므로, `@Setter`를 제거하여
 *                    불변(immutable) 객체로 만드는 것이 더 안전합니다. 변경이 필요하다면 명확한 메소드를 제공해야 합니다.
 * @NoArgsConstructor(access = AccessLevel.PROTECTED): JPA를 위한 기본 생성자를 protected로 생성합니다.
 * @Table(name = "item"): 'item' 테이블에 매핑됩니다.
 */
@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 아이템 고유 식별자

    @Column(nullable = false, unique = true) // 아이템 이름은 중복되지 않아야 합니다.
    private String name;

    private String description;
    private String img; // 아이템 이미지 (URL 또는 아이콘 클래스)

    /**
     * @Enumerated: Enum 타입을 데이터베이스에 어떻게 저장할지 결정합니다.
     *  - `EnumType.STRING`(권장): Enum의 이름("WEAPON", "POTION" 등)을 문자열로 저장합니다.
     *                           - 가독성이 좋고, Enum의 순서가 변경되어도 데이터가 깨지지 않아 안전합니다.
     *  - `EnumType.ORDINAL`(기본값, 비권장): Enum의 순서(0, 1, 2...)를 숫자로 저장합니다.
     *                                      - Enum의 순서가 바뀌면 데이터가 엉망이 될 수 있어 매우 위험합니다.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemType type; // 아이템 타입

    // ========== 장비(Equipment) 효과 ==========
    private int attackBoost;
    private int defenseBoost;
    private int healthBoost;
    private int manaBoost;

    // ========== 소모품(Consumable) 효과 ==========
    private int healAmount;
    private int manaRestoreAmount;

    // ========== 공통 정보 ==========
    @Column(nullable = false)
    private int price; // 상점 판매/구매 가격

    @Builder
    public Item(String name, String description, String img, ItemType type,
                int attackBoost, int defenseBoost, int healthBoost, int manaBoost,
                int healAmount, int manaRestoreAmount, int price) {
        this.name = name;
        this.description = description;
        this.img = img;
        this.type = type;
        this.attackBoost = attackBoost;
        this.defenseBoost = defenseBoost;
        this.healthBoost = healthBoost;
        this.manaBoost = manaBoost;
        this.healAmount = healAmount;
        this.manaRestoreAmount = manaRestoreAmount;
        this.price = price;
    }

    /**
     * 아이템의 종류를 나타내는 Enum(열거형)입니다.
     */
    public enum ItemType {
        WEAPON,  // 무기
        ARMOR,   // 방어구
        POTION,  // 물약 (즉시 회복)
        CONSUMABLE, // 기타 소모품 (버프 등)
        KEY,     // 열쇠 아이템
        ETC      // 기타 잡템
    }
}

/*
 * ✨ 엔티티 설계 및 유지보수 제안 ✨
 *
 * 1. 상속 관계 매핑 (Inheritance Mapping):
 *    - 현재 `Item` 엔티티는 모든 종류의 아이템 속성을 한 테이블에 가지고 있습니다.
 *      이로 인해 `POTION` 타입의 아이템이 `attackBoost` 컬럼을 가지는 등 데이터 모델이 명확하지 않습니다.
 *    - JPA의 상속 관계 매핑 전략을 사용하면 이를 개선할 수 있습니다.
 *      - **@Inheritance(strategy = InheritanceType.JOINED)** 또는 **SINGLE_TABLE** 전략을 사용합니다.
 *        - `Item`을 추상 클래스(abstract class)로 선언합니다.
 *        - `Equipment extends Item`, `Potion extends Item` 과 같이 아이템 타입별로 엔티티를 만듭니다.
 *        - `Equipment` 엔티티는 `attackBoost`, `defenseBoost` 등을, `Potion` 엔티티는 `healAmount` 등을 갖도록 설계합니다.
 *      - 이 방식은 객체 지향적인 설계를 데이터베이스에 반영할 수 있게 해주어 모델을 더 명확하고 확장 가능하게 만듭니다.
 *
 * 2. 불변성(Immutability) 강화:
 *    - 아이템 마스터 데이터는 게임 서비스 중에 변경될 일이 거의 없습니다.
 *    - `@Setter`를 제거하고, 모든 필드를 `final`로 선언하여 이 엔티티를 불변 객체로 만드는 것이 안전합니다.
 *    - 데이터 수정이 필요하다면, 이는 운영 툴이나 별도의 관리자 API를 통해 이루어져야 하며,
 *      엔티티 자체에 public setter를 열어두는 것은 위험할 수 있습니다.
 *
 * 3. 플레이어 인벤토리와의 관계:
 *    - `Item`은 아이템의 '정의'일 뿐, 플레이어가 '소유'한 아이템을 나타내지 않습니다.
 *    - 플레이어의 인벤토리를 표현하기 위해서는 `Member`와 `Item`을 연결하는 중간 엔티티(`MemberItem`)가 필요합니다.
 *      `MemberItem`은 어떤 플레이어(`@ManyToOne Member`)가 어떤 아이템(`@ManyToOne Item`)을 몇 개(`quantity`) 가지고 있는지,
 *      그리고 장착했는지(`equipped`) 등의 정보를 가집니다.
 *    - 이는 `Item`과 `Member`의 관계가 다대다(Many-to-Many) 관계임을 의미하며, 중간 테이블을 통해 이를 구현하는 것이 정석입니다.
 *
 * 4. `equals()`와 `hashCode()` 재정의:
 *    - `Board` 엔티티에서 제안된 바와 같이, `id`나 고유한 비즈니스 키(여기서는 `name`)를 기준으로
 *      `equals()`와 `hashCode()`를 재정의하여 컬렉션에서 안전하게 사용하고 동등성을 올바르게 비교할 수 있도록 해야 합니다.
 */
