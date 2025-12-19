package com.kh.textGame.repository;

import com.kh.textGame.entity.MemberItem;
import com.kh.textGame.entity.Member;
import com.kh.textGame.entity.Item;
import com.kh.textGame.entity.Item.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * 사용자(Member)가 보유한 아이템(Item) 정보를 관리하는 `MemberItem` 엔티티를 위한 JPA Repository 인터페이스입니다.
 * `Member`와 `Item` 사이의 다대다(Many-to-Many) 관계를 연결 테이블로 구현한 `MemberItem` 엔티티에 대한
 * 데이터 접근 계층을 제공합니다.
 *
 * `JpaRepository<MemberItem, Long>`:
 *   - `MemberItem`: 관리할 엔티티 타입
 *   - `Long`: 엔티티의 기본 키(PK) 타입
 */
public interface MemberItemRepository extends JpaRepository<MemberItem, Long> {

    /**
     * 특정 사용자가 보유한 모든 아이템 목록을 조회합니다.
     * `MemberItem` 엔티티 내의 `member` 필드(객체)를 기준으로 조회합니다.
     *
     * @param member 아이템을 조회할 사용자 엔티티
     * @return 해당 사용자가 보유한 `MemberItem` 목록
     */
    List<MemberItem> findByMember(Member member);

    /**
     * 특정 사용자가 특정 아이템을 보유하고 있는지 조회합니다.
     * `Member` 엔티티와 `Item` 엔티티를 모두 기준으로 조회합니다.
     *
     * @param member 아이템을 조회할 사용자 엔티티
     * @param item   조회할 아이템 엔티티
     * @return 해당 사용자가 특정 아이템을 보유하고 있다면 `Optional<MemberItem>`, 없으면 `Optional.empty()`
     */
    Optional<MemberItem> findByMemberAndItem(Member member, Item item);

    /**
     * 특정 사용자가 특정 `itemId`에 해당하는 아이템을 보유하고 있는지 조회합니다.
     * `Member` 엔티티와 `Item` 엔티티의 ID를 기준으로 조회합니다.
     *
     * @param member 아이템을 조회할 사용자 엔티티
     * @param itemId 조회할 아이템의 ID
     * @return 해당 사용자가 특정 아이템을 보유하고 있다면 `Optional<MemberItem>`, 없으면 `Optional.empty()`
     */
    Optional<MemberItem> findByMemberAndItemId(Member member, Long itemId);

    /**
     * 특정 `memberId`를 가진 사용자가 보유한 모든 아이템 목록을 조회합니다.
     * `member.id`를 기준으로 조회합니다.
     *
     * @param memberId 아이템을 조회할 사용자의 ID
     * @return 해당 `memberId`를 가진 사용자가 보유한 `MemberItem` 목록
     */
    List<MemberItem> findByMemberId(Long memberId);
    
    // ✨ 제안: 특정 사용자가 특정 타입의 아이템을 보유하고 있는지 조회
    Optional<MemberItem> findByMemberAndItemType(Member member, ItemType itemType);

    // ✨ 제안: 특정 사용자의 장착된 아이템 목록 조회
    List<MemberItem> findByMemberAndEquippedTrue(Member member);

    /**
     * 특정 사용자가 특정 `itemId`에 해당하는 아이템을 보유하고 있으며 장착된 상태인지 조회합니다.
     */
    Optional<MemberItem> findByMemberIdAndItemIdAndEquippedTrue(Long memberId, Long itemId);
}

/*
 * ✨ Repository 설계 및 유지보수 제안 ✨
 *
 * 1. N+1 문제 해결을 위한 Fetch Join 및 `@EntityGraph`:
 *    - `findByMember(Member member)`와 같이 `MemberItem`만 조회하는 경우,
 *      연관된 `Member`나 `Item` 정보를 사용하려면 추가 쿼리가 발생할 수 있습니다 (지연 로딩 때문에).
 *    - 이를 N+1 문제라고 하며, 성능 저하의 주범이 될 수 있습니다.
 *    - 해결책: `@Query`와 `JOIN FETCH`를 사용하거나 `@EntityGraph` 어노테이션을 사용하여
 *      한 번의 쿼리로 `MemberItem`과 연관된 `Member`, `Item` 엔티티를 함께 로드할 수 있습니다.
 *      ```java
 *      @Query("SELECT mi FROM MemberItem mi JOIN FETCH mi.item WHERE mi.member = :member")
 *      List<MemberItem> findByMemberWithItem(@Param("member") Member member);
 *
 *      @EntityGraph(attributePaths = {"item"}) // item 엔티티를 함께 로딩
 *      List<MemberItem> findByMember(Member member); // 이 메소드에 EntityGraph 적용
 *      ```
 *
 * 2. Projection(투영)을 활용한 응답 최적화:
 *    - 클라이언트에게 `MemberItem`의 모든 필드가 필요하지 않고, 특정 필드만 필요한 경우가 있습니다.
 *    - 이럴 때 DTO Projection이나 Interface Projection을 사용하면 필요한 데이터만 조회하여
 *      네트워크 트래픽과 데이터베이스 부하를 줄일 수 있습니다.
 *    - 예: `interface MemberItemInfo { Long getId(); String getItemName(); int getQuantity(); }`
 *      `List<MemberItemInfo> findByMemberId(Long memberId);`
 *
 * 3. 쿼리 성능 최적화:
 *    - 인벤토리 조회 시 `member_id`와 `item_id` 컬럼에 인덱스(index)를 추가하면 조회 성능을 향상시킬 수 있습니다.
 *    - JPA 엔티티의 `@Table` 어노테이션에 `indexes` 속성을 사용하거나, 직접 DB 스키마에 추가할 수 있습니다.
 *      `@Table(name = "member_item", indexes = @Index(name = "idx_member_item_member_id", columnList = "member_id"))`
 *
 * 4. 트랜잭션 관리:
 *    - Repository는 `Service` 계층에서 호출되어 트랜잭션 범위 내에서 동작해야 합니다.
 *    - `Item` 구매/판매/사용 로직은 여러 엔티티의 상태를 변경하므로, `MemberService`에서 `@Transactional` 어노테이션으로
 *      메소드 전체를 하나의 트랜잭션으로 묶어 데이터 일관성을 보장해야 합니다.
 */
