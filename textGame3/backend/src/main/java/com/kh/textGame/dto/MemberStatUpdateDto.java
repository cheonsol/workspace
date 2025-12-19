package com.kh.textGame.dto;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

/**
 * 사용자의 스탯 정보를 부분적으로 또는 전체적으로 업데이트하기 위한 DTO입니다.
 * 예를 들어, 전투 종료 후 변경된 체력, 획득한 경험치와 골드 등을 서버에 업데이트할 때 사용됩니다.
 *
 * @Getter/@Setter: Lombok 어노테이션으로, 모든 필드에 대한 getter/setter를 자동 생성합니다.
 */
@Getter
@Setter
public class MemberStatUpdateDto {
    /**
     * 모든 필드가 기본형(int, long)이 아닌 래퍼 클래스(Integer, Long)로 선언되었습니다.
     * 이는 클라이언트가 특정 필드만 업데이트하고 싶을 때, 요청에 포함되지 않은 필드들은
     * null 값으로 전송되도록 하기 위함입니다.
     *
     * 만약 기본형으로 선언하면, 클라이언트가 값을 보내지 않아도 자동으로 0으로 초기화되어
     * 의도치 않게 특정 스탯이 0으로 변경될 수 있습니다. 래퍼 클래스를 사용하면
     * 서비스 계층에서 `if (dto.getGold() != null)` 과 같이 null 체크를 통해
     * 클라이언트가 업데이트를 요청한 필드만 선택적으로 변경할 수 있습니다.
     */
    @PositiveOrZero(message = "레벨은 0 이상이어야 합니다.")
    private Integer level;

    @PositiveOrZero(message = "경험치는 0 이상이어야 합니다.")
    private Long exp;

    @PositiveOrZero(message = "골드는 0 이상이어야 합니다.")
    private Long gold;

    @PositiveOrZero(message = "층은 0 이상이어야 합니다.")
    private Integer floor;

    @PositiveOrZero(message = "스탯 포인트는 0 이상이어야 합니다.")
    private Integer statPoints;

    // ✨ 제안: 스탯 필드를 별도의 DTO로 묶는 것을 고려해볼 수 있습니다.
    @PositiveOrZero
    private Integer maxHp;
    @PositiveOrZero
    private Integer currentHp;
    @PositiveOrZero
    private Integer maxMp;
    @PositiveOrZero
    private Integer currentMp;
    @PositiveOrZero
    private Integer atk;
    @PositiveOrZero
    private Integer def;
    @PositiveOrZero
    private Integer dex;
    @PositiveOrZero
    private Integer luk;
}

/*
 * ✨ 효율성 및 유지보수 제안 ✨
 *
 * 1. DTO 세분화 (분리):
 *    - 현재 `MemberStatUpdateDto`는 모든 종류의 스탯 업데이트를 하나의 DTO로 처리하려고 합니다.
 *      하지만 업데이트 시나리오는 매우 다양할 수 있습니다.
 *      - **전투 결과 업데이트**: `currentHp`, `exp`, `gold` 변경.
 *      - **레벨업**: `level`, `statPoints`, `maxHp`, `maxMp` 등 기본 스탯 상승.
 *      - **스탯 분배**: 사용자가 `statPoints`를 사용하여 `atk`, `def` 등을 직접 올림.
 *    - 각 시나리오에 맞는 별도의 DTO를 만드는 것이 API의 의도를 더 명확하게 하고, 불필요한 데이터 전송을 막으며,
 *      각 DTO에 맞는 더 정확한 유효성 검사를 적용할 수 있게 합니다.
 *      - `BattleResultDto { Integer currentHp; Long expGained; Long goldGained; }`
 *      - `StatDistributionDto { Integer atk; Integer def; ... }`
 *
 * 2. Bean Validation 적용:
 *    - 모든 스탯은 음수가 될 수 없으므로, `@PositiveOrZero`와 같은 유효성 검사 어노테이션을 추가하여
 *      데이터의 무결성을 보장하는 것이 중요합니다. (코드에 예시 반영)
 *    - 컨트롤러에서 `@Valid`를 사용하여 유효성 검사를 활성화해야 합니다.
 *
 * 3. 서비스 계층의 로직:
 *    - 서비스 계층에서 이 DTO를 처리할 때는 각 필드가 `null`인지 아닌지 확인하여
 *      요청된 필드만 선택적으로 업데이트하는 로직이 필요합니다.
 *      ```java
 *      // MemberService.java
 *      public MemberDto updateMemberStats(String username, MemberStatUpdateDto dto) {
 *          Member member = memberRepository.findByUsername(username).orElseThrow(...);
 *
 *          if (dto.getGold() != null) {
 *              member.setGold(dto.getGold());
 *          }
 *          if (dto.getCurrentHp() != null) {
 *              member.setCurrentHp(dto.getCurrentHp());
 *          }
 *          // ... 다른 필드들에 대해서도 반복 ...
 *
 *          Member updatedMember = memberRepository.save(member);
 *          return convertToDto(updatedMember);
 *      }
 *      ```
 *    - 이런 반복적인 `null` 체크 코드는 MapStruct나 ModelMapper 같은 라이브러리의
 *      'null 값은 복사하지 않기' 옵션을 사용하면 더 간결하게 처리할 수 있습니다.
 *
 * 4. API 엔드포인트 설계:
 *    - 스탯 분배와 같이 사용자가 직접 스탯을 '증가'시키는 행위는 `PUT` 이나 `PATCH` 보다
 *      `POST /api/members/me/stats/distribute` 와 같이 행위를 명시하는 URL을 사용하는 것이 더 RESTful 할 수 있습니다.
 *      이는 리소스의 상태를 완전히 교체하는 것이 아니라, 특정 행위를 통해 상태를 변경하기 때문입니다.
 */
