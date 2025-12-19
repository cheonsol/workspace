package com.kh.textGame.repository;

import com.kh.textGame.entity.Item;
import com.kh.textGame.entity.Item.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 아이템(Item) 엔티티를 위한 JPA Repository 인터페이스입니다.
 * Spring Data JPA의 기능을 활용하여 아이템 마스터 데이터에 대한 CRUD 작업을 수행합니다.
 *
 * `JpaRepository<Item, Long>`:
 *   - `Item`: 관리할 엔티티 타입
 *   - `Long`: 엔티티의 기본 키(PK) 타입
 */
public interface ItemRepository extends JpaRepository<Item, Long> {

    // ✨ 제안: 아이템 이름으로 조회하는 쿼리 메소드
    Optional<Item> findByName(String name);

    // ✨ 제안: 아이템 타입별로 조회하는 쿼리 메소드
    List<Item> findByType(ItemType type);

    // ✨ 제안: 아이템 이름으로 검색하고 타입으로 필터링하는 쿼리 메소드 (부분 일치, 대소문자 무시)
    List<Item> findByNameContainingIgnoreCaseAndType(String name, ItemType type);
}

/*
 * ✨ Repository 설계 및 유지보수 제안 ✨
 *
 * 1. 쿼리 메소드 확장:
 *    - 게임 아이템은 이름 검색, 타입별 필터링, 스탯 범위 검색 등 다양한 조건으로 조회될 수 있습니다.
 *    - `findByNameContaining(String name)`, `findByType(ItemType type)`,
 *      `findByAttackBoostGreaterThan(int attackBoost)` 등과 같이
 *      Spring Data JPA의 쿼리 메소드 기능을 활용하여 필요한 조회 메소드를 추가할 수 있습니다.
 *
 * 2. 페이징 및 정렬 (Paging and Sorting):
 *    - `findAll(Pageable pageable)` 메소드를 활용하여 모든 아이템 조회 시 페이징을 적용할 수 있습니다.
 *    - `List<Item> findByType(ItemType type, Pageable pageable);`와 같이 특정 조건에 페이징을 적용하는 것도 가능합니다.
 *
 * 3. `@Query` 어노테이션을 사용한 복잡한 쿼리:
 *    - 특정 스탯을 기반으로 아이템을 찾거나, 특정 조합의 필터를 적용하는 등 복잡한 쿼리가 필요한 경우
 *      JPQL이나 Native Query를 `@Query` 어노테이션과 함께 사용할 수 있습니다.
 *    - 예: `@Query("SELECT i FROM Item i WHERE i.type = :type AND i.price BETWEEN :minPrice AND :maxPrice")`
 *
 * 4. 캐싱 전략 고려:
 *    - 아이템 마스터 데이터는 자주 변경되지 않고 자주 조회될 가능성이 높습니다.
 *    - JPA 2차 캐시(Ehcache, Caffeine 등)를 적용하여 데이터베이스 접근 횟수를 줄이고
 *      성능을 향상시킬 수 있습니다. `@Cacheable` 어노테이션을 메소드에 추가하는 방식으로 적용 가능합니다.
 *
 * 5. 트랜잭션 관리:
 *    - Repository 계층은 트랜잭션의 경계를 설정하는 `Service` 계층의 호출을 받아 사용됩니다.
 *    - Repository 자체에 `@Transactional`을 적용하는 경우는 드뭅니다. 대부분 `Service` 계층에서 트랜잭션을 관리합니다.
 */
