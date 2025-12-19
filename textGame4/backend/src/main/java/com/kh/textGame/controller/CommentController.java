package com.kh.textGame.controller;

import com.kh.textGame.dto.CommentDto;
import com.kh.textGame.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @RestController: RESTful API를 위한 컨트롤러. 모든 메소드는 HTTP 응답 본문에 데이터를 직접 씁니다.
 * @RequiredArgsConstructor: `final` 필드에 대한 생성자를 자동으로 생성하여 의존성 주입을 간소화합니다.
 * @RequestMapping("/api"): 이 컨트롤러의 모든 API는 `/api` 경로로 시작합니다.
 *                     - 댓글은 게시글(Board)에 종속적인 리소스이므로, RESTful 디자인 원칙에 따라
 *                       `/api/boards/{boardId}/comments` 와 같은 계층적 구조로 URL을 설계합니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    /**
     * 특정 게시글에 달린 모든 댓글을 조회합니다.
     *
     * @param boardId 댓글을 조회할 게시글의 ID.
     * @return 해당 게시글의 댓글 목록(List<CommentDto>)과 HTTP 200 OK.
     */
    @GetMapping("/boards/{boardId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByBoardId(@PathVariable Long boardId) {
        return ResponseEntity.ok(commentService.getCommentsByBoardId(boardId));
    }

    /**
     * 특정 게시글에 새로운 댓글을 생성합니다.
     * 인증된 사용자만 이 API를 호출할 수 있습니다.
     *
     * @param boardId        댓글을 추가할 게시글의 ID.
     * @param commentDto     요청 본문으로 받은 댓글 내용.
     * @param authentication 현재 인증된 사용자 정보.
     * @return 생성된 댓글 정보(CommentDto)와 HTTP 201 Created.
     */
    @PostMapping("/boards/{boardId}/comments")
    @PreAuthorize("isAuthenticated()") // 인증된 사용자만 접근 가능하도록 설정
    public ResponseEntity<CommentDto> createComment(@PathVariable Long boardId, @RequestBody CommentDto commentDto, Authentication authentication) {
        commentDto.setBoardId(boardId); // URL 경로의 boardId를 DTO에 설정합니다.
        String username = authentication.getName();
        // 리소스가 성공적으로 생성되었음을 알리는 201 Created 상태 코드를 반환합니다.
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(commentDto, username));
    }

    /**
     * 특정 댓글을 수정합니다.
     *
     * @param commentId      수정할 댓글의 ID.
     * @param commentDto     수정할 내용을 담은 데이터.
     * @param authentication 현재 인증된 사용자 정보.
     * @return 수정된 댓글 정보(CommentDto), 또는 권한이 없으면 403 Forbidden, 댓글이 없으면 404 Not Found.
     */
    @PutMapping("/comments/{commentId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long commentId, @RequestBody CommentDto commentDto, Authentication authentication) {
        String username = authentication.getName();
        try {
            return ResponseEntity.ok(commentService.updateComment(commentId, commentDto, username));
        } catch (IllegalAccessException e) {
            // 서비스에서 작성자 불일치 시 던지는 예외
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (IllegalArgumentException e) {
            // 서비스에서 댓글 ID를 찾을 수 없을 때 던지는 예외
            return ResponseEntity.notFound().build();
        }
        // ✨ 제안: 전역 예외 처리기를 사용하여 이 try-catch 블록을 제거하는 것이 좋습니다.
    }

    /**
     * 특정 댓글을 삭제합니다.
     *
     * @param commentId      삭제할 댓글의 ID.
     * @param authentication 현재 인증된 사용자 정보.
     * @return 성공 시 내용 없이 200 OK, 권한 없으면 403 Forbidden, 댓글 없으면 404 Not Found.
     */
    @DeleteMapping("/comments/{commentId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, Authentication authentication) {
        String username = authentication.getName();
        try {
            commentService.deleteComment(commentId, username);
            return ResponseEntity.ok().build(); // ✨ 제안: 삭제 성공 시엔 204 No Content가 더 적합합니다.
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

/*
 * ✨ 효율성 및 유지보수 제안 ✨
 *
 * 1. 인증 로직 간소화 및 보안 강화:
 *    - 모든 인증이 필요한 메소드에 `if (authentication == null)` 체크 대신 `@PreAuthorize("isAuthenticated()")` 어노테이션을 사용하는 것이
 *      더 선언적이고 일관성 있습니다. (코드에 일부 반영)
 *    - 수정/삭제 시 권한 검사를 위해 `@PreAuthorize("@commentService.isOwner(#commentId, authentication.name)")`와 같이 Spring Expression Language(SpEL)를
 *      사용하면 컨트롤러의 책임(인가)을 서비스 계층의 메소드 호출로 위임하여 코드를 더 깔끔하게 만들 수 있습니다.
 *
 * 2. 전역 예외 처리기 도입 (@RestControllerAdvice):
 *    - 현재 `updateComment`, `deleteComment` 메소드 내에 `try-catch` 블록이 반복되고 있습니다.
 *    - `ResourceNotFoundException`(e.g., for `IllegalArgumentException`)과 `PermissionDeniedException`(e.g., for `IllegalAccessException`) 같은
 *      커스텀 예외를 서비스 계층에서 던지도록 하고, 이를 `@RestControllerAdvice`에서 공통으로 처리하면 컨트롤러 코드가 매우 깨끗해집니다.
 *    - 예외 처리 로직이 한 곳에 모여 있어 유지보수가 용이해지고, 일관된 형식의 에러 응답을 보장할 수 있습니다.
 *
 * 3. RESTful API 디자인 개선:
 *    - **URL 구조**: 현재 댓글 수정/삭제 API가 `/api/comments/{commentId}`로 되어 있습니다. 이는 독립적인 리소스로 취급하는 방식으로, 틀린 것은 아닙니다.
 *      하지만 완전한 계층 구조를 따르려면 `/api/boards/{boardId}/comments/{commentId}` 형태로 설계할 수도 있습니다.
 *      - 장점: 리소스 간의 관계가 URL만으로 명확해집니다.
 *      - 단점: URL이 길어지고, `boardId`가 실제 로직에 필요 없는 경우에도 경로에 포함되어야 합니다.
 *      - 현재 방식(` /api/comments/{commentId}`)은 댓글 ID가 고유하다면 충분히 실용적이고 널리 사용되는 접근법입니다.
 *    - **HTTP 상태 코드**:
 *      - `createComment`: 성공 시 `201 Created`를 반환하고 있는데, 이는 매우 좋은实践입니다. 추가적으로 `Location` 헤더에 생성된 리소스의 URI를 포함해주면 더 좋습니다.
 *        (e.g., `ResponseEntity.created(URI.create("/api/comments/" + newComment.getId()))`)
 *      - `deleteComment`: 성공 시 `200 OK` 대신 `204 No Content` (`ResponseEntity.noContent().build()`)를 반환하는 것이 더 의미상 정확합니다.
 *
 * 4. DTO 유효성 검사 (@Valid):
 *    - 댓글 내용이 비어있는 요청을 방지하기 위해 `CommentDto`의 `content` 필드에 `@NotBlank` 어노테이션을 추가하고,
 *      컨트롤러 메소드에서 `@Valid @RequestBody CommentDto commentDto` 와 같이 사용하여 요청 데이터의 유효성을 검사해야 합니다.
 */
