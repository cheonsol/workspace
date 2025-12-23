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

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MonsterDto> createMonster(@RequestBody MonsterDto monsterDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(monsterService.createMonster(monsterDto));
    }

    @GetMapping
    public ResponseEntity<List<MonsterDto>> getAllMonsters() {
        return ResponseEntity.ok(monsterService.getAllMonsters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonsterDto> getMonsterById(@PathVariable Long id) {
        MonsterDto monsterDto = monsterService.getMonsterById(id);
        if (monsterDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(monsterDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MonsterDto> updateMonster(@PathVariable Long id, @RequestBody MonsterDto monsterDto) {
        try {
            return ResponseEntity.ok(monsterService.updateMonster(id, monsterDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMonster(@PathVariable Long id) {
        try {
            monsterService.deleteMonster(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}