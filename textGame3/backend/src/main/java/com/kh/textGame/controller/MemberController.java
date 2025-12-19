package com.kh.textGame.controller;

import com.kh.textGame.dto.MemberDto;
import com.kh.textGame.dto.MemberStatUpdateDto;
import com.kh.textGame.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * @RestController: RESTful API 컨트롤러.
 * @RequiredArgsConstructor: `final` 필드에 대한 생성자 자동 생성.
 * @RequestMapping("/api/members"): 이 컨트롤러의 모든 API는 `/api/members` 경로로 시작합니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@PreAuthorize("isAuthenticated()") // 이 컨트롤러의 모든 메소드는 인증된 사용자만 호출 가능
public class MemberController {

    private final MemberService memberService;

    /**
     * 현재 인증된 사용자의 상세 정보를 조회합니다.
     *
     * @param authentication Spring Security가 주입해주는 현재 사용자 인증 정보.
     * @return 사용자의 상세 정보(MemberDto)와 HTTP 200 OK.
     */
    @GetMapping("/me")
    public ResponseEntity<MemberDto> getMyDetails(Authentication authentication) {
        String username = authentication.getName(); // JWT 토큰에서 사용자의 이름을 추출
        MemberDto memberDto = memberService.getMemberDetails(username);
        return ResponseEntity.ok(memberDto);
    }

    /**
     * 현재 인증된 사용자의 스탯(예: 전투 후 체력, 경험치)을 업데이트합니다.
     *
     * @param authentication 현재 사용자 인증 정보.
     * @param updateDto      업데이트할 스탯 정보를 담은 DTO.
     * @return 업데이트된 사용자 정보(MemberDto)와 HTTP 200 OK.
     */
    @PutMapping("/me/stats")
    public ResponseEntity<MemberDto> updateMyStats(Authentication authentication, @RequestBody MemberStatUpdateDto updateDto) {
        String username = authentication.getName();
        MemberDto updatedMember = memberService.updateMemberStats(username, updateDto);
        return ResponseEntity.ok(updatedMember);
    }

    /**
     * 아이템을 구매합니다.
     *
     * @param authentication 현재 사용자 인증 정보.
     * @param itemId         구매할 아이템의 ID.
     * @param quantity       구매할 수량. URL 파라미터가 없으면 기본값으로 1이 사용됩니다.
     * @return 업데이트된 사용자 정보(MemberDto) 또는 에러 발생 시 HTTP 400 Bad Request.
     */
    @PostMapping("/me/items/{itemId}/buy")
    public ResponseEntity<MemberDto> buyItem(Authentication authentication, @PathVariable Long itemId, @RequestParam(defaultValue = "1") int quantity) {
        String username = authentication.getName();
        // ✨ 제안: try-catch 블록 대신 전역 예외 처리기를 사용하는 것이 좋습니다.
        try {
            MemberDto updatedMember = memberService.buyItem(username, itemId, quantity);
            return ResponseEntity.ok(updatedMember);
        } catch (IllegalArgumentException e) {
            // 예: 돈이 부족하거나 아이템이 존재하지 않을 때
            return ResponseEntity.badRequest().body(null); // ✨ 제안: 에러 메시지를 포함한 공통 응답 객체를 반환하는 것이 좋습니다.
        }
    }

    /**
     * 보유한 아이템을 판매합니다.
     *
     * @param authentication 현재 사용자 인증 정보.
     * @param memberItemId   판매할 아이템의 고유 인벤토리 ID (MemberItem의 ID).
     * @param quantity       판매할 수량.
     * @return 업데이트된 사용자 정보(MemberDto) 또는 에러 발생 시 HTTP 400 Bad Request.
     */
    @PostMapping("/me/items/{memberItemId}/sell")
    public ResponseEntity<MemberDto> sellItem(Authentication authentication, @PathVariable Long memberItemId, @RequestParam(defaultValue = "1") int quantity) {
        String username = authentication.getName();
        try {
            MemberDto updatedMember = memberService.sellItem(username, memberItemId, quantity);
            return ResponseEntity.ok(updatedMember);
        } catch (IllegalArgumentException | IllegalStateException e) {
            // 예: 판매할 아이템이 없거나 수량이 부족할 때
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * 보유한 아이템을 사용합니다. (주로 소모품)
     *
     * @param authentication 현재 사용자 인증 정보.
     * @param memberItemId   사용할 아이템의 고유 인벤토리 ID.
     * @return 업데이트된 사용자 정보(MemberDto) 또는 에러 발생 시 HTTP 400 Bad Request.
     */
    @PostMapping("/me/items/{memberItemId}/use")
    public ResponseEntity<MemberDto> useItem(Authentication authentication, @PathVariable Long memberItemId) {
        String username = authentication.getName();
        try {
            MemberDto updatedMember = memberService.useItem(username, memberItemId);
            return ResponseEntity.ok(updatedMember);
        } catch (IllegalArgumentException | IllegalStateException e) {
            // 예: 아이템을 사용할 수 없는 상황일 때 (체력이 가득 찼을 때 포션 사용 등)
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * 장비 아이템을 장착하거나 해제합니다. (토글 방식)
     *
     * @param authentication 현재 사용자 인증 정보.
     * @param memberItemId   장착/해제할 아이템의 고유 인벤토리 ID.
     * @return 업데이트된 사용자 정보(MemberDto) 또는 에러 발생 시 HTTP 400 Bad Request.
     */
    @PostMapping("/me/items/{memberItemId}/toggle-equip")
    public ResponseEntity<MemberDto> toggleEquipItem(Authentication authentication, @PathVariable Long memberItemId) {
        String username = authentication.getName();
        try {
            MemberDto updatedMember = memberService.toggleEquipItem(username, memberItemId);
            return ResponseEntity.ok(updatedMember);
        } catch (IllegalArgumentException | IllegalStateException e) {
            // 예: 장비 아이템이 아닐 때
            return ResponseEntity.badRequest().body(null);
        }
    }
}

/*
 * ✨ 효율성 및 유지보수 제안 ✨
 *
 * 1. 인증 정보 처리 간소화:
 *    - `@PreAuthorize("isAuthenticated()")`: 컨트롤러 레벨에 이 어노테이션을 추가하면, 모든 메소드에 대해 인증 요구를 한 번에 명시할 수 있습니다. (코드에 반영)
 *    - `@AuthenticationPrincipal UserDetails userDetails`: `Authentication` 객체를 직접 다루는 대신, `@AuthenticationPrincipal` 어노테이션을 사용하면
 *      `UserDetails` 객체를 파라미터로 바로 주입받을 수 있습니다. `userDetails.getUsername()`으로 사용자 이름을 더 간결하게 얻을 수 있습니다.
 *
 * 2. 전역 예외 처리 및 커스텀 예외:
 *    - 현재 모든 메소드에 `try-catch` 블록이 반복되고 있으며, 다양한 종류의 실패(돈 부족, 아이템 없음, 수량 부족 등)를
 *      모두 `IllegalArgumentException`으로 처리하고 `400 Bad Request`만 반환하고 있습니다.
 *    - 이는 클라이언트 입장에서 실패 원인을 파악하기 어렵게 만듭니다.
 *    - `NotEnoughGoldException`, `ItemNotFoundException`, `InsufficientItemQuantityException` 등 의미가 명확한 커스텀 예외를 정의하는 것이 좋습니다.
 *    - 서비스 계층에서 상황에 맞는 커스텀 예외를 던지고, `@RestControllerAdvice`를 이용한 전역 예외 처리기에서 각 예외에 맞는
 *      HTTP 상태 코드(e.g., 400, 404, 409 Conflict)와 명확한 에러 메시지를 담은 응답을 생성해주면 API의 품질이 크게 향상됩니다.
 *
 * 3. 낙관적 락(Optimistic Lock) 또는 비관적 락(Pessimistic Lock) 고려:
 *    - 아이템 구매/판매, 스탯 업데이트 등은 사용자의 재화나 상태를 변경하는 중요한 트랜잭션입니다.
 *    - 여러 요청이 동시에 들어올 경우(예: 아이템을 동시에 여러 번 구매하려는 시도), 데이터 정합성 문제(race condition)가 발생할 수 있습니다.
 *    - Member 엔티티에 `@Version` 어노테이션을 추가하여 낙관적 락을 적용하면, 데이터베이스 업데이트 시 버전 번호를 확인하여
 *      동시성 문제를 감지하고 예외(`ObjectOptimisticLockingFailureException`)를 발생시킬 수 있습니다.
 *    - 또는, 특정 로직에 비관적 락(`@Lock(LockModeType.PESSIMISTIC_WRITE)`)을 사용하여 트랜잭션 동안 해당 데이터에 다른 접근을 막을 수 있습니다.
 *
 * 4. 응답 객체 통일:
 *    - 에러 발생 시 `ResponseEntity.badRequest().body(null)`로 반환하고 있는데,
 *      성공/실패 여부, 데이터, 메시지/에러코드를 담는 공통 응답 DTO를 만들어 사용하는 것이 클라이언트 친화적입니다.
 *      `return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, null, "NOT_ENOUGH_GOLD"));`
 */
