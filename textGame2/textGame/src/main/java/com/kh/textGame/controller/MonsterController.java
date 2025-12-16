package com.kh.textGame.controller;

import com.kh.textGame.dto.MonsterDto;
import com.kh.textGame.service.MonsterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/monsters")
public class MonsterController {

    private final MonsterService monsterService;

    // 몬스터 생성 (관리자 권한 필요)
    @PostMapping
    @PreAuthorize("isAuthenticated()") // TODO: ADMIN 권한으로 변경
    public ResponseEntity<MonsterDto> createMonster(@RequestBody MonsterDto monsterDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(monsterService.createMonster(monsterDto));
    }

    // 모든 몬스터 조회 (모두 허용)
    @GetMapping
    public ResponseEntity<List<MonsterDto>> getAllMonsters() {
        return ResponseEntity.ok(monsterService.getAllMonsters());
    }

    // 몬스터 ID로 조회 (모두 허용)
    @GetMapping("/{id}")
    public ResponseEntity<MonsterDto> getMonsterById(@PathVariable Long id) {
        MonsterDto monsterDto = monsterService.getMonsterById(id);
        if (monsterDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(monsterDto);
    }

    // 몬스터 수정 (관리자 권한 필요)
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()") // TODO: ADMIN 권한으로 변경
    public ResponseEntity<MonsterDto> updateMonster(@PathVariable Long id, @RequestBody MonsterDto monsterDto) {
        try {
            return ResponseEntity.ok(monsterService.updateMonster(id, monsterDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 몬스터 삭제 (관리자 권한 필요)
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()") // TODO: ADMIN 권한으로 변경
    public ResponseEntity<Void> deleteMonster(@PathVariable Long id) {
        try {
            monsterService.deleteMonster(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
