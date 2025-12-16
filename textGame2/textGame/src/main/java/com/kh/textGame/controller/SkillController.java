package com.kh.textGame.controller;

import com.kh.textGame.dto.SkillDto;
import com.kh.textGame.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    // 스킬 생성 (관리자 권한 필요)
    @PostMapping
    @PreAuthorize("isAuthenticated()") // TODO: ADMIN 권한으로 변경
    public ResponseEntity<SkillDto> createSkill(@RequestBody SkillDto skillDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(skillService.createSkill(skillDto));
    }

    // 모든 스킬 조회 (모두 허용)
    @GetMapping
    public ResponseEntity<List<SkillDto>> getAllSkills() {
        return ResponseEntity.ok(skillService.getAllSkills());
    }

    // 스킬 ID로 조회 (모두 허용)
    @GetMapping("/{id}")
    public ResponseEntity<SkillDto> getSkillById(@PathVariable Long id) {
        SkillDto skillDto = skillService.getSkillById(id);
        if (skillDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(skillDto);
    }

    // 스킬 수정 (관리자 권한 필요)
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()") // TODO: ADMIN 권한으로 변경
    public ResponseEntity<SkillDto> updateSkill(@PathVariable Long id, @RequestBody SkillDto skillDto) {
        try {
            return ResponseEntity.ok(skillService.updateSkill(id, skillDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 스킬 삭제 (관리자 권한 필요)
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()") // TODO: ADMIN 권한으로 변경
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        try {
            skillService.deleteSkill(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
