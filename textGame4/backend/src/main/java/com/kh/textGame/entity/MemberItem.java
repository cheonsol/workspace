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

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private boolean equipped;

    @Builder
    public MemberItem(Member member, Item item, int quantity, boolean equipped) {
        this.member = member;
        this.item = item;
        this.quantity = quantity;
        this.equipped = equipped;
    }

    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    public void removeQuantity(int amount) {
        int rest = this.quantity - amount;
        if (rest < 0) {
            throw new IllegalArgumentException("아이템 수량이 부족합니다. (현재: " + this.quantity + ", 요청: " + amount + ")");
        }
        this.quantity = rest;
    }

    public void equip() {
        this.equipped = true;
    }

    public void unequip() {
        this.equipped = false;
    }
}