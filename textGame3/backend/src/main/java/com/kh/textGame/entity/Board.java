package com.kh.textGame.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Entity: 이 클래스가 JPA 엔티티임을 나타냅니다. 데이터베이스의 테이블과 매핑됩니다.
 * @Getter: 모든 필드에 대한 getter 메소드를 자동으로 생성합니다.
 * @Setter: 모든 필드에 대한 setter 메소드를 자동으로 생성합니다.
 *          - ✨ 제안: 엔티티의 일관성을 유지하기 위해 `@Setter` 사용을 지양하고,
 *            상태를 변경해야 할 경우 명확한 의도를 가진 메소드(예: `updateBoard`)를 별도로 만드는 것이 좋습니다. (아래 제안 참조)
 * @NoArgsConstructor(access = AccessLevel.PROTECTED): 매개변수가 없는 기본 생성자를 생성합니다.
 *                                                   - `access = AccessLevel.PROTECTED`는 JPA 명세상 기본 생성자가 필요하지만,
 *                                                     무분별한 객체 생성을 막기 위해 접근 수준을 protected로 설정하는 것입니다.
 * @Table(name = "board"): 이 엔티티가 매핑될 데이터베이스 테이블의 이름을 'board'로 지정합니다.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
public class Board {

    @Id // 이 필드가 테이블의 기본 키(Primary Key)임을 나타냅니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 데이터베이스에 위임합니다(예: MySQL의 AUTO_INCREMENT).
    private Long id;

    @Column(nullable = false, length = 100) // `nullable = false`는 NOT NULL 제약조건을 의미합니다. `length`로 길이 제한.
    private String title;

    @Lob // 데이터베이스의 BLOB, CLOB 타입과 매핑됩니다. 긴 텍스트를 저장하기에 적합합니다.
    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String writer; // 작성자의 username

    private String imageUrl; // 첨부된 이미지의 URL

    // 논리적 삭제(soft delete)를 위한 필드입니다.
    // 실제로 행을 삭제하는 대신, 이 플래그 값을 변경하여 삭제된 것처럼 처리합니다.
    @Column(nullable = false)
    private boolean isShow = true; // 기본값은 true (보임)

    @CreationTimestamp // 엔티티가 처음 저장될 때 현재 시간을 자동으로 기록합니다.
    private LocalDateTime writeDate;

    @UpdateTimestamp // 엔티티가 수정될 때마다 현재 시간을 자동으로 기록합니다.
    private LocalDateTime updateDate;

    // ================== 연관 관계 매핑 ==================

    /**
     * Board(1)와 Comment(N)의 일대다 관계를 설정합니다.
     *
     * @OneToMany: 일대다 관계임을 나타냅니다.
     * `mappedBy = "board"`: 연관관계의 주인이 `Comment` 엔티티의 `board` 필드임을 명시합니다.
     *                        - 즉, 외래 키(FK) 관리는 `Comment` 테이블에서 이루어집니다.
     *                        - 이 설정으로 인해 `board` 테이블에는 불필요한 외래 키 컬럼이 생기지 않습니다.
     * `cascade = CascadeType.ALL`: 영속성 전이(cascade) 옵션입니다.
     *                               - `Board` 엔티티에 대한 변경(저장, 삭제 등)이 연관된 `Comment` 엔티티에도 모두 적용됩니다.
     *                               - 예를 들어, 게시글을 삭제하면 해당 게시글에 달린 댓글들도 함께 삭제됩니다.
     * `orphanRemoval = true`: 고아 객체 제거 기능입니다.
     *                         - `Board`의 `comments` 리스트에서 특정 `Comment`를 제거하면, 데이터베이스에서도 해당 댓글이 삭제됩니다.
     *                         - `cascade`와 함께 사용하면 부모가 사라질 때 자식도 사라지고, 부모가 자식과의 관계를 끊어도 자식이 사라지는 효과를 가집니다.
     */
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    // == 연관관계 편의 메소드 ==
    // 양방향 관계에서 양쪽의 상태를 모두 일관성 있게 맞춰주기 위해 사용합니다.
    // 현재는 단방향으로 추가만 하므로 간단하지만, 양방향일 경우 Comment 객체에도 Board를 설정해주는 로직이 필요합니다.
    public void addComment(Comment comment) {
        this.comments.add(comment);
        // 만약 Comment에도 `setBoard`가 있다면, comment.setBoard(this); 를 추가하여 양쪽을 모두 설정합니다.
    }

    /**
     * @Builder: Lombok 어노테이션으로, 빌더 패턴을 사용하여 객체를 생성할 수 있게 해줍니다.
     *           - 생성자의 매개변수가 많거나, 일부만 사용하여 객체를 생성해야 할 때 유용합니다.
     *           - `new Board(...)` 대신 `Board.builder().title(...).contents(...).build()` 형태로 사용 가능합니다.
     */
    @Builder
    public Board(String title, String contents, String writer, String imageUrl) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.imageUrl = imageUrl;
        this.isShow = true; // 빌더로 생성 시에도 기본값은 true
    }

    // ✨ 제안: 상태 변경을 위한 별도의 비즈니스 메소드
    public void update(String title, String contents, String imageUrl) {
        this.title = title;
        this.contents = contents;
        this.imageUrl = imageUrl;
    }

    public void delete() {
        this.isShow = false;
    }
}

/*
 * ✨ 엔티티 설계 및 유지보수 제안 ✨
 *
 * 1. `@Setter` 사용 최소화 및 명확한 비즈니스 메소드 제공:
 *    - `@Setter`를 무분별하게 열어두면, 객체의 상태가 어디서 어떻게 변경되는지 추적하기 어려워 유지보수가 힘들어집니다.
 *    - 엔티티의 상태 변경은 명확한 의도를 가진 메소드를 통해 이루어져야 합니다. (예: `update`, `delete` 메소드 추가)
 *    - 서비스 계층에서는 `board.setTitle(...)`, `board.setContents(...)` 대신 `board.update(...)`를 호출하여
 *      도메인 로직을 엔티티 내부로 캡슐화할 수 있습니다.
 *
 * 2. `equals()`와 `hashCode()` 재정의:
 *    - JPA 엔티티를 컬렉션(List, Set 등)에서 사용하거나, 영속성 컨텍스트 내에서 엔티티의 동등성을 비교해야 할 때
 *      `equals()`와 `hashCode()`를 올바르게 재정의하는 것이 중요합니다.
 *    - Lombok의 `@EqualsAndHashCode(of = "id")`를 사용하면 `id` 필드만을 기준으로 동등성을 비교할 수 있습니다.
 *      다만, `id`는 엔티티가 저장되기 전까지 `null`일 수 있으므로, 비즈니스 키(절대 중복되지 않는 다른 필드)가 있다면
 *      그것을 기준으로 삼는 것이 더 안정적일 수 있습니다.
 *
 * 3. 연관관계의 주인(Owner of Relationship):
 *    - `mappedBy`를 사용한 것은 연관관계의 주인을 명확히 한 좋은 예시입니다.
 *    - 연관관계의 주인은 외래 키를 관리하는 쪽이며, 주인이 아닌 쪽(`Board`)에서는 `mappedBy`를 사용하여
 *      읽기 전용(read-only) 매핑임을 알려주어야 합니다.
 *
 * 4. 지연 로딩(Lazy Loading) 활용:
 *    - `@OneToMany`, `@ManyToOne` 등의 연관관계 매핑 시에는 `fetch` 타입을 명시할 수 있습니다.
 *    - `@OneToMany`의 기본 `fetch` 타입은 `FetchType.LAZY`이고, `@ManyToOne`은 `FetchType.EAGER`입니다.
 *    - **LAZY(지연 로딩)**: 연관된 엔티티를 실제로 사용할 때(예: `board.getComments()`) 비로소 데이터베이스에서 조회합니다.
 *    - **EAGER(즉시 로딩)**: 주 엔티티를 조회할 때 연관된 엔티티도 함께 즉시 조회합니다.
 *    - N+1 문제를 방지하기 위해, `@OneToMany`는 `LAZY`로 두고, `@ManyToOne`도 가급적 `LAZY`로 설정하는 것이 권장됩니다.
 *      그리고 연관된 엔티티가 필요한 경우에는 Fetch Join 이나 `@EntityGraph`를 사용하여 최적화된 쿼리로 함께 조회하는 것이 좋습니다.
 */