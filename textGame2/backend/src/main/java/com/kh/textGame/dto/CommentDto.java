package com.kh.textGame.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 댓글 데이터 전송을 위한 DTO (Data Transfer Object) 입니다.
 * 클라이언트와 서버 간에 댓글 정보를 주고받을 때 사용됩니다.
 *
 * @Getter: 모든 필드의 getter 메소드를 자동으로 생성합니다.
 * @Setter: 모든 필드의 setter 메소드를 자동으로 생성합니다.
 */
@Getter
@Setter
public class CommentDto {
    // 댓글의 고유 식별자 (DB에서 자동 생성)
    // 조회 응답(response) 시에만 값이 채워집니다.
    private Long id;

    // 댓글 내용
    // ✨ 제안: Bean Validation을 사용하여 유효성 검사 규칙을 추가합니다.
    @NotBlank(message = "댓글 내용은 비워둘 수 없습니다.")
    @Size(max = 500, message = "댓글은 500자를 초과할 수 없습니다.")
    private String content;

    // 작성자 이름 (서버에서 인증 정보를 바탕으로 설정)
    // 조회 응답 시에만 값이 채워집니다.
    private String writer;

    // 작성 또는 수정 일시 (서버에서 자동 설정)
    // 조회 응답 시에만 값이 채워집니다.
    private LocalDateTime writeDate;

    // 댓글이 속한 게시글의 ID
    // 댓글 생성 요청(request) 시, 어떤 게시글에 댓글을 달지 지정하기 위해 필요합니다.
    // 컨트롤러에서 @PathVariable로 받은 boardId를 이 필드에 설정해줄 수 있습니다.
    @NotNull(message = "게시글 ID는 필수입니다.") // 댓글 생성 시 반드시 필요
    private Long boardId;
}

/*
 * ✨ 효율성 및 유지보수 제안 ✨
 *
 * 1. DTO 클래스 세분화:
 *    - `BoardDto`와 마찬가지로, `CommentDto`도 용도에 따라 분리하는 것이 좋습니다.
 *    - **`CommentCreateRequestDto`**:
 *      - `content`와 `boardId` 필드만 포함합니다. `id`, `writer`, `writeDate`는 서버에서 결정되므로 요청 시에는 필요 없습니다.
 *      - 이렇게 하면 클라이언트가 불필요하거나 조작해서는 안 되는 데이터를 보내는 것을 원천적으로 막을 수 있습니다.
 *    - **`CommentUpdateRequestDto`**:
 *      - `content` 필드만 포함할 수 있습니다.
 *    - **`CommentResponseDto`**:
 *      - `id`, `content`, `writer`, `writeDate`, `boardId` 등 클라이언트에 보여줄 모든 정보를 포함합니다.
 *      - 이 DTO는 불변(immutable) 객체로 만드는 것이 더 안전합니다. (`@Setter` 대신 `@Builder`나 생성자 사용)
 *
 * 2. Bean Validation 적용:
 *    - `content`나 `boardId` 필드에 `@NotBlank`, `@NotNull` 등의 어노테이션을 추가하여 데이터의 유효성을 보장해야 합니다. (코드에 예시 반영)
 *    - 컨트롤러에서 `@Valid @RequestBody`를 사용하여 이 유효성 검사를 활성화할 수 있습니다.
 *      이렇게 하면 유효하지 않은 데이터가 서비스 계층으로 전달되는 것을 막을 수 있습니다.
 *
 * 3. 엔티티와 DTO 간의 변환:
 *    - `Comment` 엔티티와 `CommentDto` 간의 변환 로직을 명확히 해야 합니다.
 *    - 예를 들어, `CommentService`에서 `createComment` 메소드를 구현할 때 다음과 같은 로직이 들어갈 수 있습니다.
 *      ```java
 *      // DTO -> Entity 변환
 *      Board board = boardRepository.findById(commentDto.getBoardId()).orElseThrow(...);
 *      Member member = memberRepository.findByUsername(username).orElseThrow(...);
 *      Comment comment = new Comment(commentDto.getContent(), board, member);
 *      Comment savedComment = commentRepository.save(comment);
 *
 *      // Entity -> DTO 변환
 *      return convertToDto(savedComment);
 *      ```
 *    - 변환 로직이 복잡해지면 MapStruct와 같은 매퍼 라이브러리를 사용하는 것이 효율적입니다.
 *
 * 4. 응답 데이터 구조:
 *    - 댓글 목록 조회 시, 댓글 정보만 반환하는 것 외에도 부모 댓글 ID(`parentCommentId`) 등을 포함하여
 *      대댓글(nested comments) 구조를 지원하도록 DTO를 확장할 수 있습니다.
 */
