package com.kh.textGame.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member_item")
public class MemberItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    private int quantity; // 아이템 수량
    private boolean equipped; // 장착 여부

    @Builder
    public MemberItem(Member member, Item item, int quantity, boolean equipped) {
        this.member = member;
        this.item = item;
        this.quantity = quantity;
        this.equipped = equipped;
    }

    // 편의 메서드 (수량 증가/감소)
    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    public void removeQuantity(int amount) {
        if (this.quantity - amount < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.quantity -= amount;
    }

    public void equip() {
        this.equipped = true;
    }

    public void unequip() {
        this.equipped = false;
    }
}
