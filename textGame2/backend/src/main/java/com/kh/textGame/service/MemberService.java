package com.kh.textGame.service;

import com.kh.textGame.dto.MemberDto;
import com.kh.textGame.dto.MemberStatUpdateDto;
import com.kh.textGame.entity.Item;
import com.kh.textGame.entity.Member;
import com.kh.textGame.entity.MemberItem;
import com.kh.textGame.entity.Skill;
import com.kh.textGame.repository.ItemRepository;
import com.kh.textGame.repository.MemberItemRepository;
import com.kh.textGame.repository.MemberRepository;
import com.kh.textGame.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.kh.textGame.dto.SkillDto; // 추가

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final MemberItemRepository memberItemRepository;
    private final SkillRepository skillRepository; // MemberDto에 Skill 정보도 포함될 수 있으므로 주입

    // 사용자 상세 정보 조회 (JWT 인증 후 호출)
    @Transactional(readOnly = true)
    public MemberDto getMemberDetails(String userId) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with userId: " + userId));

        // MemberDto로 변환
        return convertToMemberDto(member);
    }

    // 사용자 스탯 업데이트 (부분 업데이트)
    public MemberDto updateMemberStats(String userId, MemberStatUpdateDto updateDto) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with userId: " + userId));

        // DTO에 값이 있는 필드만 업데이트
        Optional.ofNullable(updateDto.getLevel()).ifPresent(member::setLevel);
        Optional.ofNullable(updateDto.getExp()).ifPresent(member::setExp);
        Optional.ofNullable(updateDto.getGold()).ifPresent(member::setGold);
        Optional.ofNullable(updateDto.getFloor()).ifPresent(member::setFloor);
        Optional.ofNullable(updateDto.getStatPoints()).ifPresent(member::setStatPoints);
        Optional.ofNullable(updateDto.getMaxHp()).ifPresent(member::setMaxHp);
        Optional.ofNullable(updateDto.getCurrentHp()).ifPresent(member::setCurrentHp);
        Optional.ofNullable(updateDto.getMaxMp()).ifPresent(member::setMaxMp);
        Optional.ofNullable(updateDto.getCurrentMp()).ifPresent(member::setCurrentMp);
        Optional.ofNullable(updateDto.getAtk()).ifPresent(member::setAtk);
        Optional.ofNullable(updateDto.getDef()).ifPresent(member::setDef);
        Optional.ofNullable(updateDto.getDex()).ifPresent(member::setDex);
        Optional.ofNullable(updateDto.getLuk()).ifPresent(member::setLuk);
        
        // base stats는 직접적으로 업데이트하지 않고, 다른 로직에 의해 파생되어야 할 수 있습니다.
        // 필요하다면 updateDto에 base stats 필드도 추가하여 업데이트 로직을 구현할 수 있습니다.

        return convertToMemberDto(memberRepository.save(member));
    }


    // 아이템 구매
    public MemberDto buyItem(String userId, Long itemId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with userId: " + userId));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with id: " + itemId));

        long totalPrice = (long) item.getPrice() * quantity;
        if (member.getGold() < totalPrice) {
            throw new IllegalArgumentException("Not enough gold to buy this item.");
        }

        member.setGold(member.getGold() - totalPrice);

        Optional<MemberItem> existingMemberItem = memberItemRepository.findByMemberAndItem(member, item);

        if (existingMemberItem.isPresent()) {
            existingMemberItem.get().addQuantity(quantity);
        } else {
            MemberItem newMemberItem = MemberItem.builder()
                    .member(member)
                    .item(item)
                    .quantity(quantity)
                    .equipped(false)
                    .build();
            member.getMemberItems().add(newMemberItem); // 연관관계 설정
        }

        return convertToMemberDto(memberRepository.save(member));
    }

    // 아이템 판매
    public MemberDto sellItem(String userId, Long memberItemId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with userId: " + userId));
        MemberItem memberItem = memberItemRepository.findById(memberItemId)
                .orElseThrow(() -> new IllegalArgumentException("MemberItem not found with id: " + memberItemId));

        if (!memberItem.getMember().equals(member)) {
            throw new IllegalStateException("This item does not belong to the member.");
        }
        if (memberItem.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough quantity to sell.");
        }

        long sellPrice = (long) memberItem.getItem().getPrice() * quantity / 2; // 보통 구매가의 절반
        member.setGold(member.getGold() + sellPrice);

        if (memberItem.getQuantity() == quantity) {
            // 모든 수량 판매 시 인벤토리에서 제거
            member.getMemberItems().remove(memberItem);
            memberItemRepository.delete(memberItem);
        } else {
            memberItem.removeQuantity(quantity);
            memberItemRepository.save(memberItem);
        }

        return convertToMemberDto(memberRepository.save(member));
    }
    
    // 아이템 사용 (예: 포션)
    public MemberDto useItem(String userId, Long memberItemId) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with userId: " + userId));
        MemberItem memberItem = memberItemRepository.findById(memberItemId)
                .orElseThrow(() -> new IllegalArgumentException("MemberItem not found with id: " + memberItemId));

        if (!memberItem.getMember().equals(member)) {
            throw new IllegalStateException("This item does not belong to the member.");
        }
        if (memberItem.getQuantity() <= 0) {
            throw new IllegalArgumentException("No quantity to use.");
        }
        
        Item item = memberItem.getItem();
        if (item.getType() == Item.ItemType.POTION || item.getType() == Item.ItemType.CONSUMABLE) {
            // 아이템 효과 적용 (예: 체력/마나 회복)
            member.setCurrentHp(Math.min(member.getMaxHp(), member.getCurrentHp() + item.getHealAmount()));
            member.setCurrentMp(Math.min(member.getMaxMp(), member.getCurrentMp() + item.getManaRestoreAmount()));
            
            memberItem.removeQuantity(1); // 1개 사용
            if (memberItem.getQuantity() == 0) {
                member.getMemberItems().remove(memberItem);
                memberItemRepository.delete(memberItem);
            } else {
                memberItemRepository.save(memberItem);
            }
        } else {
            throw new IllegalArgumentException("This item cannot be used directly.");
        }

        return convertToMemberDto(memberRepository.save(member));
    }

    // 아이템 장착/장착 해제
    public MemberDto toggleEquipItem(String userId, Long memberItemId) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with userId: " + userId));
        MemberItem memberItem = memberItemRepository.findById(memberItemId)
                .orElseThrow(() -> new IllegalArgumentException("MemberItem not found with id: " + memberItemId));

        if (!memberItem.getMember().equals(member)) {
            throw new IllegalStateException("This item does not belong to the member.");
        }
        Item item = memberItem.getItem();

        if (item.getType() == Item.ItemType.WEAPON || item.getType() == Item.ItemType.ARMOR) {
            // 이미 장착 중인 같은 타입의 아이템이 있다면 해제
            if (!memberItem.isEquipped()) { // 장착 시도
                member.getMemberItems().stream()
                        .filter(mi -> mi.isEquipped() && mi.getItem().getType() == item.getType())
                        .forEach(MemberItem::unequip);
                memberItem.equip();
            } else { // 해제 시도
                memberItem.unequip();
            }
        } else {
            throw new IllegalArgumentException("This item type cannot be equipped.");
        }

        return convertToMemberDto(memberRepository.save(member));
    }


    // Member 엔티티를 MemberDto로 변환하는 헬퍼 메서드
    private MemberDto convertToMemberDto(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setUserId(member.getUserId());
        memberDto.setNickname(member.getNickname());
        memberDto.setLevel(member.getLevel());
        memberDto.setExp(member.getExp());
        memberDto.setGold(member.getGold());
        memberDto.setFloor(member.getFloor());
        memberDto.setStatPoints(member.getStatPoints());
        memberDto.setMaxHp(member.getMaxHp());
        memberDto.setCurrentHp(member.getCurrentHp());
        memberDto.setMaxMp(member.getMaxMp());
        memberDto.setCurrentMp(member.getCurrentMp());
        memberDto.setAtk(member.getAtk());
        memberDto.setDef(member.getDef());
        memberDto.setDex(member.getDex());
        memberDto.setLuk(member.getLuk());
        memberDto.setBaseMaxHp(member.getBaseMaxHp());
        memberDto.setBaseMaxMp(member.getBaseMaxMp());
        memberDto.setBaseAtk(member.getBaseAtk());
        memberDto.setBaseDef(member.getBaseDef());
        memberDto.setBaseDex(member.getBaseDex());
        memberDto.setBaseLuk(member.getBaseLuk());

        // 스킬 정보 추가
        if (member.getSkills() != null) {
            memberDto.setSkills(member.getSkills().stream()
                    .map(this::convertToSkillDto)
                    .collect(Collectors.toList()));
        }

        // 인벤토리 정보 추가
        if (member.getMemberItems() != null) {
            memberDto.setInventory(member.getMemberItems().stream()
                    .map(this::convertToMemberItemDto)
                    .collect(Collectors.toList()));
        }

        return memberDto;
    }
    
    // Skill 엔티티를 SkillDto로 변환하는 헬퍼 메서드 (중복 정의 방지를 위해 필요 시 SkillService에서 가져올 수 있음)
    private SkillDto convertToSkillDto(Skill skill) {
        SkillDto skillDto = new SkillDto();
        skillDto.setId(skill.getId());
        skillDto.setName(skill.getName());
        skillDto.setDescription(skill.getDescription());
        skillDto.setIcon(skill.getIcon());
        skillDto.setDamage(skill.getDamage());
        skillDto.setManaCost(skill.getManaCost());
        skillDto.setCooldown(skill.getCooldown());
        skillDto.setHealing(skill.isHealing());
        skillDto.setHealAmount(skill.getHealAmount());
        return skillDto;
    }

    // MemberItem 엔티티를 MemberDto.MemberItemDto로 변환하는 헬퍼 메서드
    private MemberDto.MemberItemDto convertToMemberItemDto(MemberItem memberItem) {
        MemberDto.MemberItemDto dto = new MemberDto.MemberItemDto();
        dto.setId(memberItem.getId());
        dto.setItemId(memberItem.getItem().getId());
        dto.setItemName(memberItem.getItem().getName());
        dto.setItemDescription(memberItem.getItem().getDescription());
        dto.setItemImg(memberItem.getItem().getImg());
        dto.setItemType(memberItem.getItem().getType());
        dto.setItemAttackBoost(memberItem.getItem().getAttackBoost());
        dto.setItemDefenseBoost(memberItem.getItem().getDefenseBoost());
        dto.setItemHealthBoost(memberItem.getItem().getHealthBoost());
        dto.setItemManaBoost(memberItem.getItem().getManaBoost());
        dto.setItemHealAmount(memberItem.getItem().getHealAmount());
        dto.setItemManaRestoreAmount(memberItem.getItem().getManaRestoreAmount());
        dto.setItemPrice(memberItem.getItem().getPrice());
        dto.setQuantity(memberItem.getQuantity());
        dto.setEquipped(memberItem.isEquipped());
        return dto;
    }
}
