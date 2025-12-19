package com.kh.textGame.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

/**
 * 몬스터의 데이터 전송을 위한 DTO (Data Transfer Object) 입니다.
 * 몬스터 정보 조회, 생성, 수정 등 API 통신에 사용됩니다.
 *
 * @Getter/@Setter: Lombok 어노테이션. 모든 필드에 대한 getter/setter를 자동 생성합니다.
 */
@Getter
@Setter
public class MonsterDto {

    private Long id; // 몬스터 고유 식별자

    @NotBlank(message = "몬스터 이름은 필수입니다.")
    private String name; // 몬스터 이름

    private String img; // 몬스터 이미지 URL 또는 아이콘

    @Positive(message = "층은 1 이상의 값이어야 합니다.")
    private int floor; // 몬스터가 출현하는 층

    private boolean isBoss; // 보스 몬스터 여부

    @PositiveOrZero(message = "경험치는 0 이상이어야 합니다.")
    private long exp; // 처치 시 획득하는 경험치

    @PositiveOrZero(message = "골드는 0 이상이어야 합니다.")
    private long gold; // 처치 시 획득하는 골드

    // ========== 몬스터 스탯 정보 ==========
    // ✨ 제안: 스탯 관련 필드를 별도의 StatDto로 묶으면 구조가 명확해집니다.
    @Positive(message = "최대 체력은 1 이상이어야 합니다.")
    private int maxHp;

    @PositiveOrZero(message = "최대 마나는 0 이상이어야 합니다.")
    private int maxMp;

    @PositiveOrZero(message = "공격력은 0 이상이어야 합니다.")
    private int atk;

    @PositiveOrZero(message = "방어력은 0 이상이어야 합니다.")
    private int def;

    @PositiveOrZero(message = "민첩성은 0 이상이어야 합니다.")
    private int dex;

    @PositiveOrZero(message = "행운은 0 이상이어야 합니다.")
    private int luk;
}

/*
 * ✨ 효율성 및 유지보수 제안 ✨
 *
 * 1. Bean Validation 적용:
 *    - 몬스터를 생성하거나 수정할 때 데이터의 유효성을 검증하는 것은 매우 중요합니다.
 *    - 예를 들어, 이름은 비워둘 수 없고(`@NotBlank`), 층이나 체력은 1 이상이어야 하며(`@Positive`),
 *      다른 스탯들은 0 이상이어야 합니다(`@PositiveOrZero`). (코드에 예시 반영)
 *    - 컨트롤러에서 `@Valid` 어노테이션과 함께 사용하여 데이터 무결성을 강화할 수 있습니다.
 *
 * 2. DTO 클래스 세분화:
 *    - **`MonsterCreateRequestDto` / `MonsterUpdateRequestDto`**:
 *      - `id` 필드는 클라이언트가 보내는 것이 아니므로 생성/수정용 DTO에서는 제외하는 것이 좋습니다.
 *      - 이를 통해 각 API의 요청 명세를 더 명확하게 할 수 있습니다.
 *    - **`MonsterSummaryDto`**:
 *      - 몬스터 목록을 간단히 보여줄 때는 `id`, `name`, `img`, `floor`, `isBoss` 정도의 핵심 정보만 필요할 수 있습니다.
 *      - 전체 스탯 정보를 제외한 요약 DTO를 만들어 반환하면 네트워크 부하를 줄일 수 있습니다.
 *    - **`BattleMonsterDto`**:
 *      - 실제 전투 로직에서는 몬스터의 스탯 외에 현재 체력(`currentHp`)과 같은 상태 정보가 필요합니다.
 *      - 전투 시작 시 `Monster` 엔티티로부터 `BattleMonsterDto`를 생성하여 전투 세션을 관리하는 데 사용할 수 있습니다.
 *        이 DTO는 서버 내에서만 사용될 수 있습니다.
 *
 * 3. 스탯 정보 구조화:
 *    - `maxHp`, `atk`, `def` 등 여러 스탯 필드들을 `StatDto`와 같은 별도의 객체로 묶으면
 *      `MonsterDto`의 구조가 더 깔끔해지고 스탯 관련 로직을 그룹화하기 용이해집니다.
 *      `private StatDto stats;`
 *
 * 4. 불변성(Immutability):
 *    - 조회용으로 사용되는 DTO의 경우, `@Setter`를 제거하고 생성자나 `@Builder`를 통해 객체를 생성하여
 *      불변 객체로 만드는 것이 데이터의 일관성과 안정성을 높이는 좋은 방법입니다.
 *
 * 5. 엔티티와 DTO 간의 변환:
 *    - 서비스 계층이나 별도의 매퍼 클래스(e.g., MapStruct 사용)에서 `Monster` 엔티티와 `MonsterDto` 간의
 *      변환 로직을 구현해야 합니다.
 *    - 특히, DTO가 세분화될 경우 각 DTO에 맞는 매핑 메소드를 각각 구현해야 합니다.
 */
