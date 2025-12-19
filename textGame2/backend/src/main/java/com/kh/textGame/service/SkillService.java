package com.kh.textGame.service;

import com.kh.textGame.dto.SkillDto;
import com.kh.textGame.entity.Skill;
import com.kh.textGame.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    // 스킬 생성 (관리자 권한 필요)
    public SkillDto createSkill(SkillDto skillDto) {
        Skill skill = Skill.builder()
                .name(skillDto.getName())
                .description(skillDto.getDescription())
                .icon(skillDto.getIcon())
                .damage(skillDto.getDamage())
                .manaCost(skillDto.getManaCost())
                .cooldown(skillDto.getCooldown())
                .isHealing(skillDto.isHealing())
                .healAmount(skillDto.getHealAmount())
                .build();
        Skill savedSkill = skillRepository.save(skill);
        return convertEntityToDto(savedSkill);
    }

    // 모든 스킬 조회
    @Transactional(readOnly = true)
    public List<SkillDto> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    // 스킬 ID로 조회
    @Transactional(readOnly = true)
    public SkillDto getSkillById(Long id) {
        return skillRepository.findById(id)
                .map(this::convertEntityToDto)
                .orElse(null);
    }

    // 스킬 수정 (관리자 권한 필요)
    public SkillDto updateSkill(Long id, SkillDto skillDto) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid skill Id:" + id));
        
        // @Setter가 엔티티에 없지만, 빌더 패턴을 사용하거나 엔티티에 직접 수정 메서드를 추가할 수 있습니다.
        // 현재는 편의상 모든 필드를 다시 설정합니다.
        // Let's assume @Setter is present or add an update method in the Skill entity.
        // For consistency, I will add @Setter to the Skill entity.
        
        skill.setName(skillDto.getName());
        skill.setDescription(skillDto.getDescription());
        skill.setIcon(skillDto.getIcon());
        skill.setDamage(skillDto.getDamage());
        skill.setManaCost(skillDto.getManaCost());
        skill.setCooldown(skillDto.getCooldown());
        skill.setHealing(skillDto.isHealing());
        skill.setHealAmount(skillDto.getHealAmount());

        Skill updatedSkill = skillRepository.save(skill);
        return convertEntityToDto(updatedSkill);
    }

    // 스킬 삭제 (관리자 권한 필요)
    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

    private SkillDto convertEntityToDto(Skill skill) {
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
}
