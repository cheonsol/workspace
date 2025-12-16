package com.kh.textGame.repository;

import com.kh.textGame.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    // 필요하다면 이름으로 스킬 찾기 기능 추가
    Skill findByName(String name);
}