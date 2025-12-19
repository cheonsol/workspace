package com.kh.textGame.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * `Member`와 `Item` 사이의 다대다(Many-to-Many) 관계를 연결하는 중간 엔티티(Junction Entity)입니다.
 * 단순히 두 엔티티를 연결만 하는 것이 아니라, '수량(quantity)'과 '장착 여부(equipped)' 같은
 * 추가적인 정보를 포함하므로, JPA의 `@ManyToMany` 대신 `@OneToMany`와 `@ManyToOne`을 사용하여
 * 직접 이 중간 엔티티를 만들어야 합니다.
 * 이 엔티티는 플레이어의 '인벤토리 슬롯'과 유사한 역할을 합니다.
 *
 * @Entity: JPA 엔티티 클래스임을 나타냅니다.
 * @Getter/@Setter: Lombok 어노테이션.
 *                  - ✨ 제안: `@Setter`는 외부에서 상태를 쉽게 변경할 수 있게 하므로,
 *                    `addQuantity`, `equip` 등 명확한 의도를 가진 비즈니스 메소드만 노출하는 것이 더 안전한 설계입니다.
 * @NoArgsConstructor(access = AccessLevel.PROTECTED): JPA를 위한 기본 생성자를 protected로 생성합니다.
 * @Table(name = "member_item"): 'member_item' 테이블에 매핑됩니다.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member_item")
public class MemberItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 인벤토리 항목의 고유 식별자

    /**
     * `Member` 엔티티와의 다대일(@ManyToOne) 관계입니다.
     * 이 `MemberItem`이 어떤 사용자의 인벤토리에 속해있는지를 나타냅니다.
     * `fetch = FetchType.LAZY`: `MemberItem` 조회 시 `Member` 정보를 즉시 로드하지 않고, 필요할 때만 가져옵니다. (성능 최적화)
     * `@JoinColumn`: 외래 키 컬럼을 'member_id'로 지정합니다. `nullable = false`는 모든 인벤토리 아이템이 반드시 주인을 가져야 함을 의미합니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    /**
     * `Item` 엔티티와의 다대일(@ManyToOne) 관계입니다.
     * 이 `MemberItem`이 어떤 종류의 아이템인지를 나타냅니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private int quantity; // 아이템 보유 수량

    @Column(nullable = false)
    private boolean equipped; // 장비 아이템의 경우, 현재 장착 중인지 여부

    @Builder
    public MemberItem(Member member, Item item, int quantity, boolean equipped) {
        this.member = member;
        this.item = item;
        this.quantity = quantity;
        this.equipped = equipped;
    }

    // ================== 비즈니스 편의 메소드 ==================
    // 도메인 로직을 엔티티 내에 캡슐화하여 서비스 계층의 코드를 더 깔끔하게 만듭니다.

    /**
     * 아이템 수량을 증가시킵니다.
     * @param amount 증가시킬 수량
     */
    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    /**
     * 아이템 수량을 감소시킵니다.
     * @param amount 감소시킬 수량
     * @throws IllegalArgumentException 보유 수량보다 많이 감소시키려고 할 경우 발생
     */
    public void removeQuantity(int amount) {
        int rest = this.quantity - amount;
        if (rest < 0) {
            throw new IllegalArgumentException("아이템 수량이 부족합니다. (현재: " + this.quantity + ", 요청: " + amount + ")");
        }
        this.quantity = rest;
    }

    /**
     * 아이템을 장착 상태로 변경합니다.
     */
    public void equip() {
        this.equipped = true;
    }

    /**
     * 아이템을 장착 해제 상태로 변경합니다.
     */
    public void unequip() {
        this.equipped = false;
    }
    
    /**
     * 아이템 장착/해제를 토글합니다.
     */
    public void toggleEquip() {
        this.equipped = !this.equipped;
    }
}

/*
 * ✨ 엔티티 설계 및 유지보수 제안 ✨
 *
 * 1. `@Setter` 제거 및 비즈니스 메소드 활용:
 *    - `setQuantity(5)`와 같이 상태를 직접 설정하는 것보다, `addQuantity(2)`나 `removeQuantity(1)`처럼
 *      의도가 명확한 메소드를 사용하는 것이 도메인 규칙을 강제하고 실수를 줄이는 데 도움이 됩니다.
 *    - 예를 들어, `setQuantity(-1)` 같은 잘못된 값 설정을 원천적으로 막을 수 있습니다.
 *    - `equip`, `unequip` 같은 메소드도 `setEquipped(true)` 보다 훨씬 명확합니다.
 *
 * 2. 복합키(Composite Key) 사용 고려:
 *    - `id`라는 대리 키(surrogate key) 대신, `member_id`와 `item_id`를 묶어 복합 기본 키(composite primary key)로 사용할 수도 있습니다.
 *    - `@IdClass`나 `@EmbeddedId`를 사용하여 복합 키를 구현할 수 있습니다.
 *    - 장점: 한 명의 사용자가 같은 종류의 아이템을 여러 슬롯에 가질 수 없도록 DB 수준에서 강제할 수 있습니다. (e.g., 포션 아이템은 하나의 슬롯에만 존재)
 *    - 단점: JPA에서 다루기가 다소 복잡해지고, 장비 아이템처럼 같은 종류의 아이템을 여러 개(e.g., 반지 2개) 가질 수 있는 경우엔 부적합합니다.
 *    - 현재 설계처럼 `id`를 PK로 사용하는 것이 대부분의 경우 더 유연하고 관리하기 쉽습니다.
 *
 * 3. 연관관계 편의 메소드:
 *    - `Member` 엔티티에도 `addItem(Item item, int quantity)`와 같은 편의 메소드를 만들어,
 *      `MemberItem` 생성과 `Member`의 `memberItems` 리스트에 추가하는 로직을 한 번에 처리하면 좋습니다.
 *      이렇게 하면 양방향 연관관계의 일관성을 쉽게 유지할 수 있습니다.
 */
