package com.kh.textGame.repository;

import com.kh.textGame.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * 게시글(Board) 엔티티를 위한 JPA Repository 인터페이스입니다.
 * Spring Data JPA는 이 인터페이스를 상속받아 자동으로 구현체를 생성해줍니다.
 * 이를 통해 개발자는 데이터베이스와의 상호작용(CRUD operations)을 직접 구현할 필요 없이
 * 메소드 호출만으로 데이터베이스 작업을 수행할 수 있습니다.
 *
 * `JpaRepository<T, ID>`:
 *   - `T`: 엔티티 타입 (여기서는 `Board`)
 *   - `ID`: 엔티티의 기본 키(Primary Key) 타입 (여기서는 `Long`)
 *
 * JpaRepository는 기본적인 CRUD(Create, Read, Update, Delete) 기능을 제공하며,
 * 페이징(Paging) 및 정렬(Sorting) 기능까지 포함하고 있습니다.
 */
public interface BoardRepository extends JpaRepository<Board, Long> {

    /**
     * isShow 필드가 true인 (논리적으로 삭제되지 않은) 게시글들을 최신 순서(ID 내림차순)로 조회합니다.
     * Spring Data JPA의 쿼리 메소드(Query Method) 기능을 활용한 예시입니다.
     * 메소드 이름(`findAllByIsShowTrueOrderByIdDesc`)을 Spring Data JPA의 규칙에 맞게 작성하면,
     * 별도의 SQL 쿼리 작성 없이 JPA가 자동으로 해당 쿼리를 생성하여 실행합니다.
     *
     * - `findAll`: 모든 엔티티를 조회합니다.
     * - `By`: WHERE 절의 시작을 나타냅니다.
     * - `IsShowTrue`: `isShow` 필드가 `true`인 조건을 의미합니다.
     * - `OrderByIdDesc`: `id` 필드를 기준으로 내림차순(Descending)으로 정렬합니다.
     *
     * @return 논리적으로 삭제되지 않은 모든 게시글 목록 (최신순)
     */
    List<Board> findAllByIsShowTrueOrderByIdDesc();
    
    // ✨ 제안: 특정 ID의 게시글을 조회하되, isShow=true인 경우만 조회하는 메소드
    Optional<Board> findByIdAndIsShowTrue(Long id);
}

/*
 * ✨ Repository 설계 및 유지보수 제안 ✨
 *
 * 1. 쿼리 메소드 컨벤션 활용:
 *    - Spring Data JPA는 메소드 이름만으로도 복잡한 쿼리를 생성할 수 있는 강력한 기능을 제공합니다.
 *    - `findBy<컬럼명><조건>` (`findByTitleContains`), `findBy<컬럼명>And<다른컬럼명>` (`findByWriterAndIsShowTrue`) 등
 *      다양한 조합을 통해 비즈니스 요구사항에 맞는 쿼리를 구현할 수 있습니다.
 *
 * 2. `@Query` 어노테이션 사용:
 *    - 쿼리 메소드 이름으로 표현하기 어려운 복잡한 쿼리나, JPQL(Java Persistence Query Language)을 직접 작성해야 할 경우
 *      `@Query` 어노테이션을 사용할 수 있습니다.
 *    - 예: `@Query("SELECT b FROM Board b WHERE b.title LIKE %:keyword% AND b.isShow = true ORDER BY b.writeDate DESC")`
 *          `List<Board> searchBoards(@Param("keyword") String keyword);`
 *
 * 3. 페이징 및 정렬 (Paging and Sorting):
 *    - `JpaRepository`는 `Pageable` 인터페이스를 사용하여 페이징 및 정렬 기능을 기본적으로 제공합니다.
 *    - `Page<Board> findAll(Pageable pageable);`
 *    - `Page<Board> findAllByIsShowTrue(Pageable pageable);`
 *    - 이를 활용하면 클라이언트가 요청하는 페이지와 정렬 조건에 맞춰 유연하게 데이터를 조회할 수 있습니다.
 *
 * 4. `Optional` 타입 사용:
 *    - 단일 엔티티를 조회하는 메소드의 경우, 결과가 `null`일 가능성이 있다면 `Optional<T>`를 반환 타입으로 사용하는 것이 좋습니다.
 *    - 이는 `NullPointerException`을 방지하고, 코드의 안정성을 높여줍니다. (예: `findById(Long id)`는 기본적으로 `Optional`을 반환)
 */