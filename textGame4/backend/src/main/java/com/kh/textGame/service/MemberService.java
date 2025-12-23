package com.kh.textGame.service;

import com.kh.textGame.dto.MemberDto;
import com.kh.textGame.dto.SignUpDto;
import com.kh.textGame.dto.SkillDto;
import com.kh.textGame.entity.*;
import com.kh.textGame.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final SkillRepository skillRepository;
    private final ItemRepository itemRepository;
    private final MemberItemRepository memberItemRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpDto signUpDto) {
        if (memberRepository.existsByUserId(signUpDto.getUserId())) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }

        Member member = Member.builder()
                .userId(signUpDto.getUserId())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .nickname(signUpDto.getNickname())
                .build();

        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public MemberDto getMemberDetails(String userId) {
        Member member = memberRepository.findByUserIdWithSkills(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        return convertToMemberDto(member);
    }

    public MemberDto updateMemberStats(String userId, MemberStatUpdateDto dto) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        int totalUsedPoints = dto.getAtk() + dto.getDef() + dto.getDex() + dto.getLuk();
        if (member.getStatPoints() < totalUsedPoints) {
            throw new RuntimeException("스탯 포인트가 부족합니다.");
        }

        member.setBaseAtk(member.getBaseAtk() + dto.getAtk());
        member.setBaseDef(member.getBaseDef() + dto.getDef());
        member.setBaseDex(member.getBaseDex() + dto.getDex());
        member.setBaseLuk(member.getBaseLuk() + dto.getLuk());
        member.setStatPoints(member.getStatPoints() - totalUsedPoints);

        recalculateFinalStats(member);
        return convertToMemberDto(member);
    }

    public MemberDto buyItem(String userId, Long itemId, int quantity) {
        Member member = memberRepository.findByUserId(userId).orElseThrow();
        Item item = itemRepository.findById(itemId).orElseThrow();

        long totalCost = (long) item.getPrice() * quantity;
        if (member.getGold() < totalCost) throw new IllegalArgumentException("골드가 부족합니다.");

        member.setGold(member.getGold() - totalCost);

        MemberItem memberItem = memberItemRepository.findByMemberAndItem(member, item)
                .orElseGet(() -> MemberItem.builder()
                        .member(member)
                        .item(item)
                        .quantity(0)
                        .equipped(false)
                        .build());

        memberItem.addQuantity(quantity);
        memberItemRepository.save(memberItem);

        return convertToMemberDto(member);
    }

    public MemberDto sellItem(String userId, Long memberItemId, int quantity) {
        Member member = memberRepository.findByUserId(userId).orElseThrow();
        MemberItem memberItem = memberItemRepository.findById(memberItemId).orElseThrow();

        if (memberItem.isEquipped()) throw new IllegalStateException("장착 중인 아이템은 팔 수 없습니다.");

        memberItem.removeQuantity(quantity);
        member.setGold(member.getGold() + (long) (memberItem.getItem().getPrice() * 0.5) * quantity);

        if (memberItem.getQuantity() == 0) {
            memberItemRepository.delete(memberItem);
            member.getMemberItems().remove(memberItem);
        }

        return convertToMemberDto(member);
    }

    public MemberDto useItem(String userId, Long memberItemId) {
        Member member = memberRepository.findByUserId(userId).orElseThrow();
        MemberItem memberItem = memberItemRepository.findById(memberItemId).orElseThrow();
        Item item = memberItem.getItem();

        if (item.getType() != Item.ItemType.POTION) throw new IllegalStateException("소모품이 아닙니다.");

        member.setCurrentHp(Math.min(member.getMaxHp(), member.getCurrentHp() + item.getHealAmount()));
        member.setCurrentMp(Math.min(member.getMaxMp(), member.getCurrentMp() + item.getManaRestoreAmount()));

        memberItem.removeQuantity(1);
        if (memberItem.getQuantity() == 0) {
            memberItemRepository.delete(memberItem);
            member.getMemberItems().remove(memberItem);
        }

        return convertToMemberDto(member);
    }

    public MemberDto toggleEquipItem(String userId, Long memberItemId) {
        Member member = memberRepository.findByUserId(userId).orElseThrow();
        MemberItem targetItem = memberItemRepository.findById(memberItemId).orElseThrow();

        if (targetItem.getItem().getType() == Item.ItemType.POTION) throw new IllegalStateException("장착할 수 없는 아이템입니다.");

        if (!targetItem.isEquipped()) {
            member.getMemberItems().stream()
                    .filter(mi -> mi.isEquipped() && mi.getItem().getType() == targetItem.getItem().getType())
                    .forEach(MemberItem::unequip);
            targetItem.equip();
        } else {
            targetItem.unequip();
        }

        recalculateFinalStats(member);
        return convertToMemberDto(member);
    }

    private void recalculateFinalStats(Member member) {
        int bonusAtk = 0, bonusDef = 0, bonusHp = 0, bonusMp = 0;

        for (MemberItem mi : member.getMemberItems()) {
            if (mi.isEquipped()) {
                Item item = mi.getItem();
                bonusAtk += item.getAttackBoost();
                bonusDef += item.getDefenseBoost();
                bonusHp += item.getHealthBoost();
                bonusMp += item.getManaBoost();
            }
        }

        member.setAtk(member.getBaseAtk() + bonusAtk);
        member.setDef(member.getBaseDef() + bonusDef);
        member.setMaxHp(member.getBaseMaxHp() + bonusHp);
        member.setMaxMp(member.getBaseMaxMp() + bonusMp);
    }

    public void addExp(String userId, long earnedExp) {
        Member member = memberRepository.findByUserId(userId).orElseThrow();
        member.setExp(member.getExp() + earnedExp);
        long requiredExp = member.getLevel() * 100L;
        if (member.getExp() >= requiredExp) {
            levelUp(member);
        }
    }

    private void levelUp(Member member) {
        member.setLevel(member.getLevel() + 1);
        member.setExp(0);
        member.setStatPoints(member.getStatPoints() + 5);
        member.setBaseMaxHp(member.getBaseMaxHp() + 20);
        member.setCurrentHp(member.getMaxHp());
        recalculateFinalStats(member);
    }

    private MemberDto convertToMemberDto(Member member) {
        MemberDto dto = new MemberDto();
        dto.setId(member.getId());
        dto.setUserId(member.getUserId());
        dto.setNickname(member.getNickname());
        dto.setLevel(member.getLevel());
        dto.setExp(member.getExp());
        dto.setGold(member.getGold());
        dto.setFloor(member.getFloor());
        dto.setStatPoints(member.getStatPoints());
        dto.setCurrentHp(member.getCurrentHp());
        dto.setMaxHp(member.getMaxHp());
        dto.setCurrentMp(member.getCurrentMp());
        dto.setMaxMp(member.getMaxMp());
        dto.setAtk(member.getAtk());
        dto.setDef(member.getDef());

        dto.setSkills(member.getMemberSkills().stream()
                .map(MemberSkill::getSkill)
                .map(this::convertToSkillDto)
                .toList());

        dto.setInventory(member.getMemberItems().stream()
                .map(this::convertToMemberItemDto)
                .toList());

        return dto;
    }

    private SkillDto convertToSkillDto(Skill skill) {
        SkillDto dto = new SkillDto();
        dto.setId(skill.getId());
        dto.setName(skill.getName());
        dto.setDescription(skill.getDescription());
        dto.setDamage(skill.getDamage());
        dto.setManaCost(skill.getManaCost());
        return dto;
    }

    private MemberDto.MemberItemDto convertToMemberItemDto(MemberItem memberItem) {
        MemberDto.MemberItemDto dto = new MemberDto.MemberItemDto();
        Item item = memberItem.getItem();
        dto.setId(memberItem.getId());
        dto.setQuantity(memberItem.getQuantity());
        dto.setEquipped(memberItem.isEquipped());
        dto.setItemId(item.getId());
        dto.setItemName(item.getName());
        dto.setItemType(item.getType());
        dto.setItemAttackBoost(item.getAttackBoost());
        dto.setItemDefenseBoost(item.getDefenseBoost());
        return dto;
    }
}