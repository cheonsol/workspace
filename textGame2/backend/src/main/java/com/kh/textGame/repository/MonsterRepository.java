package com.kh.textGame.repository;

import com.kh.textGame.entity.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MonsterRepository extends JpaRepository<Monster, Long> {
    // 특정 층의 몬스터 목록 조회
    List<Monster> findByFloor(int floor);

    // 특정 층의 보스만 조회 (필요 시)
    Monster findByFloorAndIsBossTrue(int floor);
}