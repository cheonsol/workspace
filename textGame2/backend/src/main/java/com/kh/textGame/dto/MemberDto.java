package com.kh.textGame.dto;

import com.kh.textGame.entity.Item.ItemType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 사용자의 모든 상세 정보를 클라이언트에게 전달하기 위한 DTO입니다.
 * 캐릭터의 현재 상태, 스탯, 인벤토리, 스킬 등 게임 플레이에 필요한 거의 모든 정보를 포함합니다.
 *
 * @Getter/@Setter: Lombok 어노테이션으로, 모든 필드에 대한 getter/setter를 자동 생성합니다.
 */
@Getter
@Setter
public class MemberDto {
    // ========== 기본 정보 ==========
    private Long id; // 사용자(Member)의 고유 식별자
    private String userId; // 사용자 로그인 ID
    private String nickname; // 게임 내에서 사용될 닉네임

    // ========== 성장 정보 ==========
    private int level; // 현재 레벨
    private long exp; // 현재 경험치
    private long gold; // 보유 재화
    private int floor; // 현재 위치한 층
    private int statPoints; // 분배 가능한 스탯 포인트

    // ========== 현재 상태 (Current Status) ==========
    private int currentHp; // 현재 체력
    private int currentMp; // 현재 마나

    // ========== 최종 스탯 (Final Stats) - 기본 스탯 + 아이템 효과 등이 모두 적용된 수치 ==========
    private int maxHp; // 최대 체력
    private int maxMp; // 최대 마나
    private int atk; // 공격력
    private int def; // 방어력
    private int dex; // 민첩성
    private int luk; // 행운

    // ========== 순수 스탯 (Base Stats) - 사용자가 직접 분배한 스탯 ==========
    // ✨ 제안: 스탯 필드들을 별도의 Stat DTO로 묶으면 구조가 더 명확해집니다.
    private int baseMaxHp;
    private int baseMaxMp;
    private int baseAtk;
    private int baseDef;
    private int baseDex;
    private int baseLuk;

    // ========== 보유 스킬 및 아이템 ==========
    private List<SkillDto> skills; // 사용자가 보유한 스킬 목록
    private List<MemberItemDto> inventory; // 사용자의 인벤토리 (보유 아이템 목록)

    /**
     * 사용자의 인벤토리에 있는 개별 아이템의 정보를 나타내는 내부 DTO입니다.
     * `Item` 정보와 `MemberItem` 정보를 조합하여 만듭니다.
     */
    @Getter
    @Setter
    public static class MemberItemDto {
        // ========== 인벤토리 고유 정보 ==========
        private Long id; // MemberItem의 고유 ID (인벤토리 슬롯 ID 역할)
        private int quantity; // 보유 수량
        private boolean equipped; // 장착 여부

        // ========== 아이템 자체의 정보 (ItemDto와 유사) ==========
        private Long itemId; // 원본 아이템(Item)의 ID
        private String itemName;
        private String itemDescription;
        private String itemImg;
        private ItemType itemType;
        private int itemPrice;

        // 아이템 효과 정보
        private int itemAttackBoost;
        private int itemDefenseBoost;
        private int itemHealthBoost;
        private int itemManaBoost;
        private int itemHealAmount;
        private int itemManaRestoreAmount;
    }
}

/*
 * ✨ 효율성 및 유지보수 제안 ✨
 *
 * 1. DTO 구조화 (Nesting DTOs):
 *    - `MemberDto`가 너무 많은 필드를 가지고 있어 비대합니다. 관련된 필드들을 별도의 DTO로 묶으면 구조를 더 명확하게 만들 수 있습니다.
 *      - **Stat DTO**: `baseAtk`, `baseDef` 등 순수 스탯과 `atk`, `def` 등 최종 스탯을 `StatDto` 라는 객체로 묶습니다.
 *        ```java
 *        public class StatDto {
 *            private Stat baseStats; // 순수 스탯
 *            private Stat finalStats; // 최종 스탯
 *        }
 *        ```
 *      - **CharacterStatus DTO**: `level`, `exp`, `gold`, `floor`, `currentHp` 등 캐릭터의 현재 상태를 `CharacterStatusDto`로 묶을 수 있습니다.
 *    - 이렇게 하면 `MemberDto`는 `private StatDto stats;`, `private CharacterStatusDto status;` 와 같이 더 읽기 쉬운 구조를 갖게 됩니다.
 *
 * 2. 내부 DTO 분리:
 *    - `MemberItemDto`는 `MemberDto` 외부에서도 재사용될 가능성이 있습니다(예: 상점 API).
 *    - 별도의 파일(`MemberItemDto.java`)로 분리하여 독립적인 DTO로 관리하는 것이 유지보수 측면에서 더 좋습니다.
 *    - 또한 `MemberItemDto`의 필드명에서 `item` 접두사(e.g., `itemName`)는 불필요해 보입니다. 이미 `Item`의 정보를 나타내는 것이 명확하므로 `name`, `description` 등으로 단순화할 수 있습니다.
 *
 * 3. 응답 데이터 최적화:
 *    - 현재 `MemberDto`는 사용자의 모든 정보를 담고 있어, 클라이언트가 단지 레벨과 골드만 필요할 때도 전체 데이터를 전송해야 합니다.
 *    - **`MemberSummaryDto`**: 로그인 직후나 목록 조회 시 필요한 최소한의 정보(`id`, `nickname`, `level`)만 담는 DTO.
 *    - **`Member 전투 정보 DTO`**: 전투에 필요한 스탯만 담는 DTO.
 *    - 이처럼 API의 목적에 맞는 각기 다른 DTO를 설계하고 반환하면 네트워크 트래픽을 줄이고 성능을 향상시킬 수 있습니다.
 *
 * 4. 엔티티 -> DTO 변환 로직:
 *    - 이처럼 복잡한 DTO는 서비스 계층이나 별도의 매퍼 클래스에서 `Member`, `Item`, `MemberItem` 등 여러 엔티티를 조합하여 만들어야 합니다.
 *    - `MemberService`의 `getMemberDetails` 메소드에서 이 변환 로직이 수행될 것입니다.
 *    - MapStruct와 같은 라이브러리는 이런 복잡한 객체 매핑을 자동화하여 보일러플레이트 코드를 크게 줄여줍니다.
 *
 * 5. 불변성(Immutability):
 *    - `MemberDto`와 `MemberItemDto`는 주로 서버에서 클라이언트로 데이터를 전달하는 데 사용됩니다(응답용).
 *    - 이런 경우 `@Setter`를 제거하고 생성자나 `@Builder`를 통해 값을 초기화하는 불변 객체로 만들면,
 *      데이터가 의도치 않게 변경되는 것을 방지하여 안정성을 높일 수 있습니다.
 */
