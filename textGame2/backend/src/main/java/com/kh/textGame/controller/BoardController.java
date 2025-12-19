package com.kh.textGame.controller;

import com.kh.textGame.dto.BoardDto;
import com.kh.textGame.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @RestController: 이 클래스가 RESTful API의 컨트롤러임을 나타냅니다.
 *                  각 메소드의 반환 값은 HTTP 응답 본문으로 직접 직렬화됩니다.
 *
 * @RequiredArgsConstructor: Lombok 어노테이션으로, `final` 키워드가 붙은 필드를 포함하는 생성자를 자동으로 생성합니다.
 *                         이를 통해 생성자 기반 의존성 주입을 간결하게 구현할 수 있습니다.
 *
 * @RequestMapping("/api/boards"): 이 컨트롤러의 모든 API 경로가 `/api/boards`로 시작하도록 설정합니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    /**
     * 새 게시글을 생성하는 엔드포인트입니다.
     * `@PreAuthorize("isAuthenticated()")`는 이 메소드가 인증된 사용자만 호출할 수 있도록 제한합니다.
     *
     * @param boardDto       HTTP 요청 본문에서 받은 게시글 데이터 (JSON). `@RequestBody`가 JSON을 DTO로 변환합니다.
     * @param authentication Spring Security가 제공하는 현재 인증된 사용자 정보. JWT 필터를 통과하면 이 객체가 채워집니다.
     * @return 생성된 게시글 정보(BoardDto)와 HTTP 200 OK 상태 코드를 담은 ResponseEntity.
     */
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BoardDto> createBoard(@RequestBody BoardDto boardDto, Authentication authentication) {
        String username = authentication.getName(); // 인증된 사용자의 username (또는 고유 식별자)을 가져옵니다.
        return ResponseEntity.ok(boardService.createBoard(boardDto, username));
    }

    /**
     * 모든 게시글 목록을 조회하는 엔드포인트입니다.
     * 이 API는 `SecurityConfig`에서 `permitAll()`로 설정되어 있어 누구나 접근 가능합니다.
     *
     * @return 게시글 목록(List<BoardDto>)과 HTTP 200 OK 상태 코드를 담은 ResponseEntity.
     */
    @GetMapping
    public ResponseEntity<List<BoardDto>> getAllBoards() {
        return ResponseEntity.ok(boardService.getAllBoards());
    }

    /**
     * 특정 ID의 게시글 하나를 조회하는 엔드포인트입니다.
     * 이 API 또한 `permitAll()`로 설정되어 누구나 접근 가능합니다.
     *
     * @param id URL 경로에서 추출한 게시글 ID. `@PathVariable`이 `{id}` 부분을 매개변수에 매핑합니다.
     * @return 조회된 게시글 정보(BoardDto) 또는 게시글이 없으면 HTTP 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BoardDto> getBoardById(@PathVariable Long id) {
        BoardDto boardDto = boardService.getBoardById(id);
        if (boardDto == null) {
            // 해당 ID의 게시글이 존재하지 않으면 404 응답을 보냅니다.
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(boardDto);
    }

    /**
     * 특정 ID의 게시글을 수정하는 엔드포인트입니다.
     *
     * @param id             수정할 게시글의 ID.
     * @param boardDto       수정할 내용을 담은 데이터.
     * @param authentication 현재 인증된 사용자 정보.
     * @return 수정된 게시글 정보(BoardDto) 또는 권한이 없으면 HTTP 403 Forbidden.
     */
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BoardDto> updateBoard(@PathVariable Long id, @RequestBody BoardDto boardDto, Authentication authentication) {
        String username = authentication.getName();
        try {
            // BoardService에서 비즈니스 로직(게시글 존재 여부 및 작성자 일치 여부 확인 포함)을 처리합니다.
            return ResponseEntity.ok(boardService.updateBoard(id, boardDto, username));
        } catch (IllegalAccessException e) {
            // 서비스 계층에서 작성자가 일치하지 않을 경우 던지는 예외를 처리합니다.
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        // ✨ 제안: ResourceNotFoundException과 같은 커스텀 예외를 만들어 처리하는 것이 더 좋습니다. (아래 제안 참조)
    }

    /**
     * 특정 ID의 게시글을 삭제하는 엔드포인트입니다.
     *
     * @param id             삭제할 게시글의 ID.
     * @param authentication 현재 인증된 사용자 정보.
     * @return 성공 시 내용 없이 HTTP 200 OK, 권한이 없으면 HTTP 403 Forbidden.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        try {
            boardService.deleteBoard(id, username);
            // 성공적으로 삭제되면 내용 없이 200 OK 응답을 보냅니다.
            return ResponseEntity.ok().build();
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}

/*
 * ✨ 효율성 및 유지보수 제안 ✨
 *
 * 1. 인증/인가 로직 개선:
 *    - 현재 각 메소드에서 `authentication` 객체를 직접 확인하고 `username`을 추출하는 코드가 반복되고 있습니다.
 *    - `@PreAuthorize("isAuthenticated()")` 어노테이션을 사용하여 인증 여부를 선언적으로 확인하는 것은 좋은 방법입니다.
 *    - 더 나아가, `principal` 객체를 직접 파라미터로 받아올 수 있습니다. `@AuthenticationPrincipal UserDetails userDetails` 와 같이 사용하면
 *      `authentication.getName()` 과정 없이 바로 사용자 정보를 얻을 수 있어 코드가 간결해집니다.
 *
 * 2. 권한 검사 로직 위임:
 *    - 수정/삭제 시 권한 검사(작성자 일치 여부)를 서비스 계층에서 수행하고 컨트롤러에서 `try-catch`로 처리하는 방식 대신,
 *      Spring Security의 `@PreAuthorize` 어노테이션을 활용하여 인가 로직을 선언적으로 처리할 수 있습니다.
 *    - 예: `@PreAuthorize("@boardService.isOwner(#id, authentication.name)")`
 *      - 위와 같이 사용하려면 `boardService`에 `isOwner(Long boardId, String username)` 메소드를 구현하고, `@Component`로 등록해야 합니다.
 *      - 이렇게 하면 컨트롤러 코드가 비즈니스 로직과 보안 로직으로부터 분리되어 훨씬 깔끔해집니다.
 *
 * 3. 전역 예외 처리기(@RestControllerAdvice) 활용:
 *    - `getBoardById`에서 `null` 체크, `update/delete`에서 `try-catch` 등 예외 처리가 컨트롤러에 흩어져 있습니다.
 *    - `ResourceNotFoundException`, `PermissionDeniedException` 같은 커스텀 예외를 정의하고, 서비스 계층에서 이 예외들을 던지도록 합니다.
 *    - `@RestControllerAdvice`가 붙은 클래스에서 `@ExceptionHandler`를 사용하여 이 예외들을 전역적으로 잡아내고,
 *      각각에 맞는 HTTP 상태 코드(404, 403 등)와 일관된 에러 메시지 포맷으로 응답을 생성해주면
 *      컨트롤러 코드가 매우 단순해지고 비즈니스 로직에만 집중할 수 있습니다.
 *
 * 4. 응답 상태 코드 명확화:
 *    - 게시글 생성 성공 시, `ResponseEntity.ok()` (200 OK) 대신 `ResponseEntity.created(uri).body(...)` (201 Created)를 사용하는 것이
 *      RESTful 원칙에 더 부합합니다. `created` 메소드는 생성된 리소스의 위치를 나타내는 URI를 `Location` 헤더에 포함시켜줍니다.
 *    - 삭제 성공 시, `ResponseEntity.ok()` (200 OK) 대신 `ResponseEntity.noContent().build()` (204 No Content)를 사용하는 것이
 *      더 의미상 명확합니다. 204 응답은 성공했지만 본문에 보낼 내용이 없음을 의미합니다.
 *
 * 5. DTO 유효성 검사(@Valid):
 *    - 게시글 생성/수정 시 `boardDto`의 내용(예: 제목이 비어있는지)을 검증하는 로직이 필요합니다.
 *    - `BoardDto` 클래스의 필드에 `@NotBlank`, `@Size` 등의 유효성 검사 어노테이션을 추가하고,
 *      컨트롤러 메소드의 파라미터에 `@Valid`를 붙여주면(`@Valid @RequestBody BoardDto boardDto`),
 *      요청 데이터가 유효하지 않을 경우 `MethodArgumentNotValidException`이 자동으로 발생합니다.
 *      이 예외는 전역 예외 처리기에서 잡아 400 Bad Request로 처리할 수 있습니다.
 */
