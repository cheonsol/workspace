package com.kh.textGame.controller;

import com.kh.textGame.dto.MonsterDto;
import com.kh.textGame.service.MonsterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @RestController: RESTful API의 컨트롤러.
 * @RequiredArgsConstructor: `final` 필드에 대한 생성자 자동 생성.
 * @RequestMapping("/api/monsters"): 이 컨트롤러의 모든 API는 `/api/monsters` 경로로 시작합니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/monsters")
public class MonsterController {

    private final MonsterService monsterService;

    /**
     * 새로운 몬스터를 생성합니다. (관리자 전용)
     * `@PreAuthorize("hasRole('ADMIN')")`: 'ADMIN' 역할을 가진 사용자만 이 API를 호출할 수 있습니다.
     *
     * @param monsterDto 생성할 몬스터의 데이터.
     * @return 생성된 몬스터 정보(MonsterDto)와 HTTP 201 Created.
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MonsterDto> createMonster(@RequestBody MonsterDto monsterDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(monsterService.createMonster(monsterDto));
    }

    /**
     * 모든 몬스터 목록을 조회합니다. (모두 접근 가능)
     *
     * @return 몬스터 목록(List<MonsterDto>)과 HTTP 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<MonsterDto>> getAllMonsters() {
        return ResponseEntity.ok(monsterService.getAllMonsters());
    }

    /**
     * 특정 ID의 몬스터를 조회합니다. (모두 접근 가능)
     *
     * @param id 조회할 몬스터의 ID.
     * @return 조회된 몬스터 정보(MonsterDto) 또는 몬스터가 없으면 HTTP 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MonsterDto> getMonsterById(@PathVariable Long id) {
        MonsterDto monsterDto = monsterService.getMonsterById(id);
        if (monsterDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(monsterDto);
    }

    /**
     * 특정 ID의 몬스터 정보를 수정합니다. (관리자 전용)
     *
     * @param id         수정할 몬스터의 ID.
     * @param monsterDto 수정할 내용을 담은 데이터.
     * @return 수정된 몬스터 정보(MonsterDto) 또는 몬스터가 없으면 HTTP 404 Not Found.
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MonsterDto> updateMonster(@PathVariable Long id, @RequestBody MonsterDto monsterDto) {
        try {
            return ResponseEntity.ok(monsterService.updateMonster(id, monsterDto));
        } catch (IllegalArgumentException e) {
            // ✨ 제안: 이 로직은 전역 예외 처리기에서 처리하는 것이 더 좋습니다.
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 특정 ID의 몬스터를 삭제합니다. (관리자 전용)
     *
     * @param id 삭제할 몬스터의 ID.
     * @return 성공 시 내용 없이 HTTP 204 No Content, 몬스터가 없으면 HTTP 404 Not Found.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMonster(@PathVariable Long id) {
        try {
            monsterService.deleteMonster(id);
            return ResponseEntity.noContent().build(); // 204 No Content가 더 적합합니다.
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

/*
 * ✨ 효율성 및 유지보수 제안 ✨
 *
 * 1. 역할 기반 접근 제어 (RBAC) 완전한 구현:
 *    - `@PreAuthorize("hasRole('ADMIN')")`을 사용하기 위해서는 `Member` 엔티티에 역할(Role) 필드를 추가하고,
 *      `UserDetailsServiceImpl`에서 사용자의 역할을 `GrantedAuthority`로 Spring Security에 제공해야 합니다.
 *    - 이는 `ItemController`에서 제안된 내용과 동일하며, 애플리케이션 전반의 보안을 체계적으로 관리하기 위해 필수적입니다.
 *
 * 2. 전역 예외 처리기 도입:
 *    - 컨트롤러 내의 `try-catch` 블록과 `null` 체크를 제거하고 `@RestControllerAdvice`를 사용하여 예외를 중앙에서 관리해야 합니다.
 *    - `MonsterNotFoundException`과 같은 명확한 커스텀 예외를 서비스 계층에서 발생시키고,
 *      이를 예외 처리기에서 받아 일관된 404 Not Found 응답을 생성하는 것이 이상적입니다.
 *
 * 3. 페이지네이션(Pagination) 적용:
 *    - `getAllMonsters`: 몬스터의 종류가 많아질 경우, 모든 데이터를 한 번에 보내는 것은 비효율적입니다.
 *    - `Pageable`을 사용하여 페이징을 구현하면 클라이언트가 필요한 만큼의 데이터만 요청할 수 있어 성능이 향상됩니다.
 *    - 예: `public ResponseEntity<Page<MonsterDto>> getAllMonsters(Pageable pageable) { ... }`
 *
 * 4. DTO 유효성 검사 (@Valid):
 *    - 몬스터 생성/수정 시 `monsterDto`의 값이 유효한지(예: 이름은 비어있지 않은지, 스탯은 0 이상인지 등) 검증해야 합니다.
 *    - `MonsterDto` 필드에 `@NotBlank`, `@PositiveOrZero` 등 Bean Validation 어노테이션을 추가하고,
 *      컨트롤러 메소드 파라미터에 `@Valid`를 붙여 요청 데이터의 유효성을 검사할 수 있습니다.
 *
 * 5. 요청 경로(Request Path) 필터링:
 *    - `getAllMonsters` API에 특정 조건(예: 특정 층(floor)의 몬스터만 조회)을 추가하고 싶을 수 있습니다.
 *    - `@RequestParam`을 사용하여 쿼리 파라미터로 조건을 받을 수 있습니다.
 *    - 예: `public ResponseEntity<List<MonsterDto>> getAllMonsters(@RequestParam(required = false) Integer floor) { ... }`
 *      - 이렇게 하면 `/api/monsters?floor=1`과 같은 요청으로 1층 몬스터만 필터링하여 조회할 수 있습니다.
 */
