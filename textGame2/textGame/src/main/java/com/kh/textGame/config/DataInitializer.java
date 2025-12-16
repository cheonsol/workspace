package com.kh.textGame.config;

import com.kh.textGame.entity.Monster;
import com.kh.textGame.repository.MonsterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initMonsters(MonsterRepository repository) {
        return args -> {
            // DB에 몬스터가 하나도 없을 때만 실행
            if (repository.count() == 0) {
                List<Monster> monsters = List.of(
                        // 1층
                        Monster.builder().floor(1).name("고블린").exp(10).gold(50).maxHp(100).maxMp(0).atk(10).def(10).dex(10).luk(10).isBoss(false).img("🐢").build(),
                        Monster.builder().floor(1).name("슬라임").exp(8).gold(40).maxHp(80).maxMp(0).atk(8).def(5).dex(8).luk(5).isBoss(false).img("🟢").build(),
                        Monster.builder().floor(1).name("고블린 킹").exp(50).gold(200).maxHp(300).maxMp(30).atk(25).def(15).dex(15).luk(10).isBoss(true).img("👑").build(),

                        // 2층
                        Monster.builder().floor(2).name("코볼트").exp(30).gold(100).maxHp(150).maxMp(0).atk(15).def(10).dex(10).luk(10).isBoss(false).img("🐺").build(),
                        Monster.builder().floor(2).name("늑대").exp(25).gold(80).maxHp(120).maxMp(0).atk(18).def(8).dex(18).luk(12).isBoss(false).img("🐺").build(),
                        Monster.builder().floor(2).name("늑대 왕").exp(80).gold(400).maxHp(400).maxMp(50).atk(35).def(20).dex(25).luk(15).isBoss(true).img("🐺👑").build(),

                        // 3층
                        Monster.builder().floor(3).name("오크").exp(70).gold(200).maxHp(300).maxMp(50).atk(30).def(50).dex(0).luk(0).isBoss(false).img("🗡️").build(),
                        Monster.builder().floor(3).name("트롤").exp(60).gold(180).maxHp(280).maxMp(40).atk(28).def(45).dex(5).luk(5).isBoss(false).img("👹").build(),
                        Monster.builder().floor(3).name("오크 왕").exp(150).gold(800).maxHp(600).maxMp(100).atk(50).def(60).dex(10).luk(5).isBoss(true).img("🗡️👑").build()
                );

                repository.saveAll(monsters);
                System.out.println("기본 몬스터 데이터가 DB에 등록되었습니다.");
            }
        };
    }
}