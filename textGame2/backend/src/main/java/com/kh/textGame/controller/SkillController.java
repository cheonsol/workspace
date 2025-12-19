package com.kh.textGame.controller;

import com.kh.textGame.dto.SkillDto;
import com.kh.textGame.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @RestController: RESTful API의 컨트롤러.
 * @RequiredArgsConstructor: `final` 필드에 대한 생성자 자동 생성.
 * @RequestMapping("/api/skills"): 이 컨트롤러의 모든 API는 `/api/skills` 경로로 시작합니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    /**
     * 새로운 스킬을 생성합니다. (관리자 전용)
     * `@PreAuthorize("hasRole('ADMIN')")`: 'ADMIN' 역할을 가진 사용자만 호출할 수 있습니다.
     *
     * @param skillDto 생성할 스킬의 데이터.
     * @return 생성된 스킬 정보(SkillDto)와 HTTP 201 Created.
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SkillDto> createSkill(@RequestBody SkillDto skillDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(skillService.createSkill(skillDto));
    }

    /**
     * 모든 스킬 목록을 조회합니다. (모두 접근 가능)
     *
     * @return 스킬 목록(List<SkillDto>)과 HTTP 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<SkillDto>> getAllSkills() {
        return ResponseEntity.ok(skillService.getAllSkills());
    }

    /**
     * 특정 ID의 스킬을 조회합니다. (모두 접근 가능)
     *
     * @param id 조회할 스킬의 ID.
     * @return 조회된 스킬 정보(SkillDto) 또는 스킬이 없으면 HTTP 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SkillDto> getSkillById(@PathVariable Long id) {
        SkillDto skillDto = skillService.getSkillById(id);
        if (skillDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(skillDto);
    }

    /**
     * 특정 ID의 스킬 정보를 수정합니다. (관리자 전용)
     *
     * @param id       수정할 스킬의 ID.
     * @param skillDto 수정할 내용을 담은 데이터.
     * @return 수정된 스킬 정보(SkillDto) 또는 스킬이 없으면 HTTP 404 Not Found.
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SkillDto> updateSkill(@PathVariable Long id, @RequestBody SkillDto skillDto) {
        try {
            return ResponseEntity.ok(skillService.updateSkill(id, skillDto));
        } catch (IllegalArgumentException e) {
            // ✨ 제안: 이 로직은 전역 예외 처리기에서 처리해야 합니다.
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 특정 ID의 스킬을 삭제합니다. (관리자 전용)
     *
     * @param id 삭제할 스킬의 ID.
     * @return 성공 시 내용 없이 HTTP 204 No Content, 스킬이 없으면 HTTP 404 Not Found.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        try {
            skillService.deleteSkill(id);
            return ResponseEntity.noContent().build(); // 204 No Content가 더 적합합니다.
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

/*
 * ✨ 효율성 및 유지보수 제안 ✨
 *
 * 1. 역할 기반 접근 제어 (RBAC) 구현:
 *    - 관리자 전용 API에 `@PreAuthorize("hasRole('ADMIN')")`을 적용했습니다. 이 기능이 올바르게 동작하려면,
 *      `Member` 엔티티에 역할(Role) 정보를 추가하고, `UserDetailsServiceImpl`에서 이 정보를
 *      `GrantedAuthority`로 변환하여 Spring Security에 제공하는 과정이 반드시 필요합니다.
 *
 * 2. 전역 예외 처리기 도입:
 *    - 컨트롤러 내의 모든 `try-catch` 블록과 `null` 체크는 `@RestControllerAdvice`를 사용한 전역 예외 처리 클래스로 이전해야 합니다.
 *    - `SkillNotFoundException`과 같은 커스텀 예외를 서비스 계층에서 발생시키고,
 *      예외 처리기에서 이를 일관된 HTTP 404 응답으로 변환하면 코드가 깔끔해지고 유지보수가 용이해집니다.
 *
 * 3. 페이지네이션(Pagination) 적용:
 *    - `getAllSkills` API는 스킬의 수가 많아질 경우 성능 문제를 일으킬 수 있습니다.
 *    - `Pageable`을 파라미터로 받아 페이징 처리를 구현하면 클라이언트가 필요한 만큼의 데이터만 효율적으로 가져갈 수 있습니다.
 *    - 예: `public ResponseEntity<Page<SkillDto>> getAllSkills(Pageable pageable) { ... }`
 *
 * 4. DTO 유효성 검사 (@Valid):
 *    - 스킬 생성/수정 시 `skillDto`의 데이터가 유효한지(예: 이름, 데미지, 마나 소모량 등) 서버에서 검증해야 합니다.
 *    - `SkillDto` 필드에 `@NotBlank`, `@Positive` 등의 Bean Validation 어노테이션을 추가하고,
 *      컨트롤러 메소드에서 `@Valid` 어노테이션을 사용하여 유효성 검사를 자동으로 수행하도록 할 수 있습니다.
 *
 * 5. API 일관성:
 *    - 다른 컨트롤러(ItemController, MonsterController)와 마찬가지로 CRUD API 패턴을 일관되게 유지하는 것이 중요합니다.
 *    - 생성(POST), 전체 조회(GET), 개별 조회(GET), 수정(PUT), 삭제(DELETE)의 HTTP 메소드와 경로를
 *      표준적인 RESTful 규칙에 따라 설계하여 API의 예측 가능성을 높입니다.
 */
