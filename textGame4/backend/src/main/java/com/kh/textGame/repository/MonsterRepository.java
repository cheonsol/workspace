package com.kh.textGame.repository;

import com.kh.textGame.entity.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MonsterRepository extends JpaRepository<Monster, Long> {

    List<Monster> findByFloor(int floor);


    Monster findByFloorAndIsBossTrue(int floor);
}