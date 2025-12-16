package com.kh.textGame.repository;

import com.kh.textGame.entity.MemberItem;
import com.kh.textGame.entity.Member;
import com.kh.textGame.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberItemRepository extends JpaRepository<MemberItem, Long> {
    List<MemberItem> findByMember(Member member);
    Optional<MemberItem> findByMemberAndItem(Member member, Item item);
    Optional<MemberItem> findByMemberAndItemId(Member member, Long itemId);
    List<MemberItem> findByMemberId(Long memberId);
}
