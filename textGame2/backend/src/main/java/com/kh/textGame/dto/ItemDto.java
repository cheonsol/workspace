package com.kh.textGame.dto;

import com.kh.textGame.entity.Item.ItemType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 게임 아이템의 데이터 전송을 위한 DTO (Data Transfer Object) 입니다.
 * 아이템 생성, 조회, 수정 등 API 통신에 사용됩니다.
 *
 * @Getter: 모든 필드의 getter 메소드를 자동으로 생성합니다.
 * @Setter: 모든 필드의 setter 메소드를 자동으로 생성합니다.
 */
@Getter
@Setter
public class ItemDto {

    private Long id; // 아이템 고유 식별자 (DB 자동 생성)

    @NotBlank(message = "아이템 이름은 필수입니다.")
    @Size(max = 50, message = "아이템 이름은 50자를 넘을 수 없습니다.")
    private String name; // 아이템 이름

    @Size(max = 255, message = "아이템 설명은 255자를 넘을 수 없습니다.")
    private String description; // 아이템 설명

    private String img; // 아이템 이미지 URL 또는 아이콘 클래스

    @NotNull(message = "아이템 타입을 지정해야 합니다.")
    private ItemType type; // 아이템 종류 (WEAPON, ARMOR, POTION 등)

    // ========== 장비 아이템 스탯 ==========
    @PositiveOrZero(message = "공격력 증가는 0 이상이어야 합니다.")
    private int attackBoost; // 장착 시 증가하는 공격력

    @PositiveOrZero(message = "방어력 증가는 0 이상이어야 합니다.")
    private int defenseBoost; // 장착 시 증가하는 방어력

    @PositiveOrZero(message = "체력 증가는 0 이상이어야 합니다.")
    private int healthBoost; // 장착 시 증가하는 최대 체력

    @PositiveOrZero(message = "마나 증가는 0 이상이어야 합니다.")
    private int manaBoost; // 장착 시 증가하는 최대 마나

    // ========== 소모품 아이템 효과 ==========
    @PositiveOrZero(message = "체력 회복량은 0 이상이어야 합니다.")
    private int healAmount; // 사용 시 회복되는 체력량

    @PositiveOrZero(message = "마나 회복량은 0 이상이어야 합니다.")
    private int manaRestoreAmount; // 사용 시 회복되는 마나량

    // ========== 아이템 공통 정보 ==========
    @PositiveOrZero(message = "가격은 0 이상이어야 합니다.")
    private int price; // 아이템 구매/판매 시 가격
}

/*
 * ✨ 효율성 및 유지보수 제안 ✨
 *
 * 1. DTO 클래스 세분화:
 *    - 현재 DTO는 장비 스탯과 소모품 효과를 모두 가지고 있어, POTION 타입 아이템이 `attackBoost` 필드를 갖는 등 의미상 혼란을 줄 수 있습니다.
 *    - 아이템 타입(`ItemType`)에 따라 DTO를 분리하거나, 상속 구조를 사용하는 것을 고려해볼 수 있습니다.
 *      - `ItemDto` (공통 필드: id, name, description, price, type)
 *      - `EquipmentItemDto extends ItemDto` (장비 스탯 필드 추가)
 *      - `PotionItemDto extends ItemDto` (회복 관련 필드 추가)
 *    - JSON 직렬화/역직렬화 시 `@JsonTypeInfo`와 `@JsonSubTypes` 어노테이션을 사용하면
 *      클라이언트가 `type` 필드 값에 따라 다른 DTO 구조로 데이터를 주고받을 수 있게 구현할 수 있습니다.
 *
 * 2. Bean Validation 적용:
 *    - 아이템 데이터의 무결성을 위해 각 필드에 유효성 검사 어노테이션을 추가하는 것이 매우 중요합니다. (코드에 예시 반영)
 *    - 예를 들어, 이름은 비어있으면 안되고(`@NotBlank`), 각종 스탯과 가격은 음수일 수 없습니다(`@PositiveOrZero`).
 *    - 컨트롤러에서 `@Valid`를 사용하여 이 규칙들을 강제할 수 있습니다.
 *
 * 3. 엔티티와 DTO 간의 변환 (매핑):
 *    - `Item` 엔티티와 `ItemDto` 간의 변환 로직이 필요합니다.
 *    - `ItemService`에서 이 로직을 처리하거나, `ItemDto` 내에 `toEntity()` 메소드를 두거나, MapStruct와 같은 매핑 라이브러리를 사용할 수 있습니다.
 *    - 특히 위에서 제안한 상속 구조를 DTO에 적용했다면, 매핑 로직도 이를 고려하여 작성해야 합니다.
 *
 * 4. 조회 전용 DTO 분리:
 *    - 사용자가 아이템을 구매하거나 인벤토리에서 조회할 때는 `MemberItem`이라는 중간 테이블의 정보(보유 개수, 장착 여부)가 함께 필요할 수 있습니다.
 *    - `UserItemResponseDto` 와 같은 별도의 DTO를 만들어, `ItemDto`의 정보와 더불어 `quantity`, `isEquipped` 같은 인벤토리 관련 정보를
 *      함께 담아 클라이언트에 전달하면 더 유용한 정보를 제공할 수 있습니다.
 *
 * 5. 네이밍 컨벤션:
 *    - `img` 필드는 `imageUrl` 또는 `icon` 과 같이 좀 더 명확한 이름으로 변경하는 것을 고려해볼 수 있습니다.
 *      이는 필드의 의도를 더 잘 설명해줍니다.
 */
