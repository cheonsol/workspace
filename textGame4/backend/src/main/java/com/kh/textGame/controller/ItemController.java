package com.kh.textGame.controller;

import com.kh.textGame.dto.ItemDto;
import com.kh.textGame.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @RestController: RESTful API의 컨트롤러. 응답은 HTTP 본문에 직접 작성됩니다.
 * @RequiredArgsConstructor: `final` 필드에 대한 생성자를 자동 생성하여 의존성 주입을 간소화합니다.
 * @RequestMapping("/api/items"): 이 컨트롤러의 모든 API는 `/api/items` 경로로 시작합니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    /**
     * 새로운 아이템을 생성합니다. (관리자 전용)
     * `@PreAuthorize("hasRole('ADMIN')")`: 이 메소드는 'ADMIN' 역할을 가진 사용자만 호출할 수 있습니다.
     *                                   - `SecurityConfig`에 `@EnableMethodSecurity`가 설정되어 있어야 작동합니다.
     *                                   - 사용자의 역할(Role)은 `UserDetails` 구현체에 포함되어야 합니다.
     *
     * @param itemDto 생성할 아이템의 데이터.
     * @return 생성된 아이템 정보(ItemDto)와 HTTP 201 Created 상태 코드.
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") // 'ADMIN' 역할을 가진 사용자만 접근 가능
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto itemDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.createItem(itemDto));
    }

    /**
     * 모든 아이템 목록을 조회합니다. (모두 접근 가능)
     *
     * @return 아이템 목록(List<ItemDto>)과 HTTP 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<ItemDto>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    /**
     * 특정 ID의 아이템을 조회합니다. (모두 접근 가능)
     *
     * @param id 조회할 아이템의 ID.
     * @return 조회된 아이템 정보(ItemDto) 또는 아이템이 없으면 HTTP 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable Long id) {
        ItemDto itemDto = itemService.getItemById(id);
        if (itemDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(itemDto);
    }

    /**
     * 특정 ID의 아이템을 수정합니다. (관리자 전용)
     *
     * @param id      수정할 아이템의 ID.
     * @param itemDto 수정할 내용을 담은 데이터.
     * @return 수정된 아이템 정보(ItemDto) 또는 아이템이 없으면 HTTP 404 Not Found.
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") // 'ADMIN' 역할을 가진 사용자만 접근 가능
    public ResponseEntity<ItemDto> updateItem(@PathVariable Long id, @RequestBody ItemDto itemDto) {
        try {
            return ResponseEntity.ok(itemService.updateItem(id, itemDto));
        } catch (IllegalArgumentException e) {
            // ✨ 제안: 전역 예외 처리기로 이 로직을 옮기는 것이 좋습니다.
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 특정 ID의 아이템을 삭제합니다. (관리자 전용)
     *
     * @param id 삭제할 아이템의 ID.
     * @return 성공 시 내용 없이 HTTP 204 No Content, 아이템이 없으면 HTTP 404 Not Found.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") // 'ADMIN' 역할을 가진 사용자만 접근 가능
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        try {
            itemService.deleteItem(id);
            return ResponseEntity.noContent().build(); // 내용이 없는 성공 응답은 204 No Content가 더 적합합니다.
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

/*
 * ✨ 효율성 및 유지보수 제안 ✨
 *
 * 1. 역할 기반 접근 제어 (Role-Based Access Control, RBAC) 구현:
 *    - `@PreAuthorize("isAuthenticated()")`는 단순히 로그인 여부만 확인합니다.
 *    - 아이템 생성, 수정, 삭제는 관리자(Admin)만 가능해야 하므로 `@PreAuthorize("hasRole('ADMIN')")`을 사용하는 것이 정확합니다. (코드에 반영)
 *    - 이를 위해서는 `Member` 엔티티에 역할(Role) 필드(예: `private String role;`)를 추가하고,
 *      회원가입 시 기본값으로 'ROLE_USER'를, 관리자 계정에는 'ROLE_ADMIN'을 할당해야 합니다.
 *    - `UserDetailsServiceImpl`의 `loadUserByUsername` 메소드에서 사용자의 역할을 `GrantedAuthority`로 변환하여 `UserDetails` 객체에 포함시켜야 합니다.
 *      `new User(member.getUsername(), member.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(member.getRole())))`
 *
 * 2. 전역 예외 처리기 도입:
 *    - `updateItem`, `deleteItem`에서 발생하는 `IllegalArgumentException`과 `getItemById`의 `null` 체크 로직을 `@RestControllerAdvice`를 사용한
 *      전역 예외 처리 클래스로 옮기면 컨트롤러 코드가 훨씬 간결해집니다.
 *    - `ItemNotFoundException`과 같은 커스텀 예외를 정의하고 서비스 계층에서 발생시킨 뒤, 예외 처리기에서 이를 잡아
 *      HTTP 404 Not Found 응답을 생성하도록 구현하는 것이 가장 이상적인 방법입니다.
 *
 * 3. 응답 상태 코드 명확화:
 *    - `deleteItem`: 성공 시 `200 OK`보다 `204 No Content`가 더 의미론적으로 정확한 응답입니다. (코드에 반영)
 *
 * 4. DTO 유효성 검사 (@Valid):
 *    - 아이템 생성/수정 시 `itemDto`의 필드들(이름, 가격 등)이 유효한 값을 가지고 있는지 검증해야 합니다.
 *    - `ItemDto` 필드에 `@NotBlank`, `@PositiveOrZero` 등의 어노테이션을 추가하고, 컨트롤러 메소드 파라미터에 `@Valid`를 사용하여
 *      데이터 유효성 검사를 Spring에 위임할 수 있습니다. 유효성 검증 실패 시 발생하는 `MethodArgumentNotValidException`은
 *      전역 예외 처리기에서 400 Bad Request로 처리할 수 있습니다.
 *
 * 5. 페이지네이션(Pagination) 적용:
 *    - `getAllItems`: 아이템의 수가 많아질 경우, 모든 아이템을 한 번에 반환하는 것은 성능 저하와 과도한 데이터 전송을 유발할 수 있습니다.
 *    - `Pageable` 객체를 파라미터로 받아 페이징 처리를 적용하는 것이 좋습니다.
 *    - 예: `public ResponseEntity<Page<ItemDto>> getAllItems(Pageable pageable) { ... }`
 *      - 이렇게 하면 클라이언트는 `?page=0&size=10&sort=name,asc` 와 같은 파라미터를 통해 원하는 페이지와 정렬 순서를 요청할 수 있습니다.
 */
