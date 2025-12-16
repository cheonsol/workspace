package com.kh.textGame.config;

import com.kh.textGame.entity.Skill;
import com.kh.textGame.repository.SkillRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SkillInitializer {

    @Bean
    public CommandLineRunner initSkills(SkillRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                List<Skill> skills = List.of(
                        Skill.builder()
                                .name("파이어볼").description("적에게 불 공격을 날립니다")
                                .damage(30).manaCost(20).cooldown(3).icon("🔥")
                                .isHealing(false).healAmount(0)
                                .build(),

                        Skill.builder()
                                .name("아이스 샤드").description("적을 얼음으로 공격합니다")
                                .damage(25).manaCost(15).cooldown(2).icon("❄️")
                                .isHealing(false).healAmount(0)
                                .build(),

                        Skill.builder()
                                .name("라이트닝 스트라이크").description("번개로 강력한 공격을 합니다")
                                .damage(40).manaCost(30).cooldown(5).icon("⚡")
                                .isHealing(false).healAmount(0)
                                .build(),

                        Skill.builder()
                                .name("힐").description("자신의 체력을 회복합니다")
                                .damage(0).manaCost(25).cooldown(4).icon("💚")
                                .isHealing(true).healAmount(50) // 힐 스킬 설정
                                .build()
                );

                repository.saveAll(skills);
                System.out.println("기본 스킬 데이터가 DB에 등록되었습니다.");
            }
        };
    }
}