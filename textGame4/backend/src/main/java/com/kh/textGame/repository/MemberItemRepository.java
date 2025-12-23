package com.kh.textGame.repository;

import com.kh.textGame.entity.MemberItem;
import com.kh.textGame.entity.Member;
import com.kh.textGame.entity.Item;
import com.kh.textGame.entity.Item.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberItemRepository extends JpaRepository<MemberItem, Long> {

    List<MemberItem> findByMember(Member member);

    Optional<MemberItem> findByMemberAndItem(Member member, Item item);

    Optional<MemberItem> findByMemberAndItemId(Member member, Long itemId);

    List<MemberItem> findByMemberId(Long memberId);

    Optional<MemberItem> findByMemberAndItemType(Member member, ItemType itemType);

    List<MemberItem> findByMemberAndEquippedTrue(Member member);

    Optional<MemberItem> findByMemberIdAndItemIdAndEquippedTrue(Long memberId, Long itemId);
}