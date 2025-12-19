package com.kh.textGame.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

/**
 * 스킬의 데이터 전송을 위한 DTO (Data Transfer Object) 입니다.
 * 스킬 정보 조회, 생성, 수정 등 API 통신에 사용됩니다.
 *
 * @Getter/@Setter: Lombok 어노테이션. 모든 필드에 대한 getter/setter를 자동 생성합니다.
 */
@Getter
@Setter
public class SkillDto {

    private Long id; // 스킬 고유 식별자

    @NotBlank(message = "스킬 이름은 필수입니다.")
    private String name; // 스킬 이름

    private String description; // 스킬 설명

    private String icon; // 스킬 아이콘 (이미지 URL 또는 CSS 클래스)

    // ========== 공격 스킬 정보 ==========
    @PositiveOrZero(message = "데미지는 0 이상이어야 합니다.")
    private int damage; // 스킬 데미지 (회복 스킬의 경우 0)

    // ========== 회복 스킬 정보 ==========
    private boolean isHealing; // 회복 스킬 여부

    @PositiveOrZero(message = "회복량은 0 이상이어야 합니다.")
    private int healAmount; // 체력 회복량 (공격 스킬의 경우 0)

    // ========== 공통 소모값 및 조건 ==========
    @PositiveOrZero(message = "마나 소모량은 0 이상이어야 합니다.")
    private int manaCost; // 스킬 사용 시 소모되는 마나

    @PositiveOrZero(message = "재사용 대기시간은 0 이상이어야 합니다.")
    private int cooldown; // 재사용 대기시간 (턴 단위 또는 초 단위)
}

/*
 * ✨ 효율성 및 유지보수 제안 ✨
 *
 * 1. DTO 구조화 (상속 또는 조합):
 *    - 현재 `SkillDto`는 공격 스킬과 회복 스킬의 속성을 모두 가지고 있습니다. (`damage`와 `healAmount`)
 *    - 이는 `ItemDto`에서 제기된 문제와 유사하며, 스킬의 종류에 따라 DTO 구조를 분리하면 더 명확해집니다.
 *      - **`SkillDto` (기본)**: `id`, `name`, `description`, `icon`, `manaCost`, `cooldown` 등 공통 속성
 *      - **`DamageSkillDto extends SkillDto`**: `damage` 필드 추가
 *      - **`HealingSkillDto extends SkillDto`**: `healAmount` 필드 추가
 *    - Jackson의 `@JsonTypeInfo`와 `@JsonSubTypes`를 사용하면 `type` 필드(예: `ATTACK`, `HEAL`)에 따라
 *      JSON을 적절한 하위 DTO 클래스로 매핑할 수 있습니다.
 *
 * 2. Bean Validation 적용:
 *    - 스킬 데이터의 무결성을 위해 유효성 검사 어노테이션을 사용하는 것이 중요합니다. (코드에 예시 반영)
 *    - 이름은 비워둘 수 없고(`@NotBlank`), 데미지, 마나 소모량 등 수치 관련 값들은 음수가 될 수 없습니다(`@PositiveOrZero`).
 *    - 컨트롤러에서 `@Valid`를 사용하여 검사를 활성화해야 합니다.
 *
 * 3. DTO 클래스 세분화 (용도별):
 *    - **`SkillCreateRequestDto`**: `id` 필드를 제외한 DTO. 관리자가 새로운 스킬을 등록할 때 사용.
 *    - **`SkillResponseDto`**: 조회용 DTO. `id`를 포함한 모든 정보를 가짐. 이 DTO는 불변 객체로 만드는 것이 좋음.
 *    - **`PlayerSkillDto`**: 플레이어가 특정 스킬을 배웠을 때, 플레이어의 스탯에 따라 데미지가 변동된다면,
 *      기본 스킬 정보에 추가로 계산된 최종 데미지(`finalDamage`) 등을 포함하는 DTO를 만들 수 있습니다.
 *
 * 4. 불변성(Immutability):
 *    - 조회용으로 반환되는 DTO는 `@Setter`를 제거하고 생성자나 `@Builder`를 사용하여 불변으로 만드는 것이 안전합니다.
 *
 * 5. 네이밍과 필드 순서:
 *    - 필드의 순서를 논리적 그룹(기본 정보, 효과 정보, 코스트 정보 등)으로 묶으면 가독성이 향상됩니다. (코드에 반영)
 *    - `isHealing` 같은 boolean 필드는 이름만으로도 그 용도가 명확하여 좋습니다.
 */
