package com.kh.textGame.repository;

import com.kh.textGame.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 댓글(Comment) 엔티티를 위한 JPA Repository 인터페이스입니다.
 * Spring Data JPA의 기능을 활용하여 댓글 데이터에 대한 CRUD 작업을 수행합니다.
 *
 * `JpaRepository<Comment, Long>`:
 *   - `Comment`: 관리할 엔티티 타입
 *   - `Long`: 엔티티의 기본 키(PK) 타입
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * 특정 게시글(Board) ID에 속하는 모든 댓글을 조회합니다.
     * Spring Data JPA의 쿼리 메소드 기능을 사용한 예시입니다.
     * 메소드 이름 `findAllByBoardId`는 `Comment` 엔티티의 `board` 필드(객체) 내의 `id` 필드를 기준으로
     * 조회하도록 JPA에 지시합니다.
     * (내부적으로 `SELECT * FROM comment WHERE board_id = :boardId`와 유사한 쿼리가 생성됩니다.)
     *
     * @param boardId 댓글을 조회할 게시글의 ID
     * @return 해당 게시글에 속하는 댓글 목록
     */
    List<Comment> findAllByBoardId(Long boardId);
    // ✨ 제안: 작성일 기준 최신순으로 정렬하려면 `findAllByBoardIdOrderByWriteDateDesc(Long boardId)`와 같이 메소드 이름을 변경합니다.
}

/*
 * ✨ Repository 설계 및 유지보수 제안 ✨
 *
 * 1. 정렬(Sorting) 및 페이징(Paging) 추가:
 *    - `findAllByBoardId` 메소드에 정렬 조건을 추가하거나(`findAllByBoardIdOrderByWriteDateDesc`),
 *      `Pageable` 파라미터를 받아 페이징 처리를 할 수 있습니다.
 *      `Page<Comment> findAllByBoardId(Long boardId, Pageable pageable);`
 *
 * 2. `@Query` 어노테이션 활용:
 *    - 특정 조건(예: 특정 작성자가 작성한 댓글)을 추가하거나, Fetch Join을 사용하여
 *      댓글과 연관된 게시글(또는 작성자 Member) 정보를 한 번의 쿼리로 가져오고 싶을 때
 *      `@Query` 어노테이션을 사용하여 JPQL을 직접 작성할 수 있습니다.
 *    - 예: `@Query("SELECT c FROM Comment c JOIN FETCH c.board WHERE c.board.id = :boardId")`
 *
 * 3. 댓글 작성자(`writer`) 필드의 관계 개선:
 *    - `Comment` 엔티티에서 `writer` 필드는 현재 `String` 타입으로 작성자 이름만을 저장하고 있습니다.
 *    - 만약 `Member` 엔티티와 댓글 작성자를 직접 연결하고 싶다면, `Comment` 엔티티에 `Member member` 필드를 추가하고
 *      `@ManyToOne` 연관관계를 설정하는 것이 더 객체 지향적이고, 작성자 정보(예: ID, 닉네임)를 유연하게 가져올 수 있습니다.
 *      `@ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "member_id") private Member member;`
 *    - 이렇게 하면 `findAllByBoardIdAndMember(Long boardId, Member member)` 와 같은 쿼리 메소드를 사용할 수도 있습니다.
 */