package com.kh.textGame.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

/**
 * @Entity: 이 클래스가 JPA 엔티티임을 나타냅니다. 'comment' 테이블과 매핑됩니다.
 * @Getter: 모든 필드에 대한 getter 메소드를 자동으로 생성합니다.
 * @Setter: 모든 필드에 대한 setter 메소드를 자동으로 생성합니다.
 *          - ✨ 제안: `Board` 엔티티와 마찬가지로, `@Setter` 대신 명확한 의도를 가진
 *            업데이트 메소드(예: `updateContent`)를 사용하는 것이 좋습니다.
 * @NoArgsConstructor(access = AccessLevel.PROTECTED): JPA를 위한 기본 생성자를 protected로 생성합니다.
 * @Table(name = "comment"): 매핑될 테이블 이름을 'comment'로 지정합니다.
 */
@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String content; // 댓글 내용

    @Column(nullable = false)
    private String writer;  // 댓글 작성자의 username

    @CreationTimestamp // 엔티티가 처음 저장될 때 현재 시간을 자동으로 기록합니다.
    private LocalDateTime writeDate;

    // ================== 연관 관계 매핑 ==================

    /**
     * Comment(N)와 Board(1)의 다대일 관계를 설정합니다.
     *
     * @ManyToOne: 다대일 관계임을 나타냅니다.
     *             - 댓글(N)은 하나의 게시글(1)에 속합니다.
     *             - 이쪽이 연관관계의 주인(Owner)이 되며, 외래 키(FK)를 관리합니다.
     *
     * `fetch = FetchType.LAZY`: 지연 로딩(Lazy Loading) 전략을 사용합니다.
     *                          - `Comment` 엔티티를 조회할 때, 연관된 `Board` 엔티티를 즉시 함께 조회하지 않습니다.
     *                          - 대신, `comment.getBoard()`와 같이 실제로 `Board` 객체를 사용하는 시점에
     *                            별도의 쿼리를 통해 데이터베이스에서 `Board` 정보를 가져옵니다.
     *                          - 이는 불필요한 조인을 방지하여 성능을 최적화하는 데 매우 중요합니다. (N+1 문제 방지)
     *
     * @JoinColumn(name = "board_id"): 외래 키를 매핑할 때 사용합니다.
     *                                - `comment` 테이블에 생성될 외래 키 컬럼의 이름을 'board_id'로 지정합니다.
     *                                - 이 컬럼에는 연관된 `Board`의 PK(`id`) 값이 저장됩니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Comment(String content, String writer, Board board) {
        this.content = content;
        this.writer = writer;
        this.board = board;
    }

    // ✨ 제안: 내용 수정을 위한 명확한 비즈니스 메소드
    public void updateContent(String content) {
        this.content = content;
    }
}

/*
 * ✨ 엔티티 설계 및 유지보수 제안 ✨
 *
 * 1. `@Setter` 대신 비즈니스 메소드 사용:
 *    - 댓글 내용만 수정 가능하도록 `updateContent(String content)` 메소드를 제공하는 것이 좋습니다.
 *    - 이를 통해 `writer`나 `writeDate`, `board`와 같이 변경되어서는 안 될 필드들이 실수로 변경되는 것을 막을 수 있습니다.
 *
 * 2. 연관관계 편의 메소드:
 *    - `Board` 엔티티와의 양방향 관계를 완벽하게 설정하려면, `Comment` 엔티티에도 연관관계를 설정하는 편의 메소드를 두는 것이 좋습니다.
 *      ```java
 *      public void setBoard(Board board) {
 *          // 기존 Board와의 연관관계를 제거 (만약 있다면)
 *          if (this.board != null) {
 *              this.board.getComments().remove(this);
 *          }
 *          this.board = board;
 *          // 새로운 Board의 comments 리스트에 현재 Comment를 추가
 *          board.getComments().add(this);
 *      }
 *      ```
 *    - `Comment`를 생성하거나 `Board`를 변경할 때 이 메소드를 호출하면 양쪽의 관계가 항상 일관되게 유지됩니다.
 *
 * 3. 작성자(Member)와의 연관관계:
 *    - 현재 `writer` 필드는 작성자의 `username`을 단순한 문자열로 저장하고 있습니다.
 *    - 애플리케이션이 확장되어 "내가 쓴 댓글 보기"와 같은 기능이 필요해진다면,
 *      `Member` 엔티티와 `@ManyToOne` 관계를 맺는 것이 더 바람직한 설계입니다.
 *      ```java
 *      @ManyToOne(fetch = FetchType.LAZY)
 *      @JoinColumn(name = "member_id")
 *      private Member member;
 *      ```
 *    - 이렇게 하면 `comment.getMember().getNickname()` 과 같이 객체 그래프 탐색을 통해 작성자 정보를 유연하게 가져올 수 있습니다.
 *
 * 4. 대댓글(Nested Comments) 기능:
 *    - 대댓글 기능을 구현하려면, `Comment` 엔티티가 자기 자신을 참조하는 관계를 맺어야 합니다.
 *      ```java
 *      @ManyToOne(fetch = FetchType.LAZY)
 *      @JoinColumn(name = "parent_id")
 *      private Comment parent; // 부모 댓글
 *
 *      @OneToMany(mappedBy = "parent")
 *      private List<Comment> children = new ArrayList<>(); // 자식 댓글 목록
 *      ```
 *    - 이를 통해 계층적인 댓글 구조를 표현할 수 있습니다.
 */