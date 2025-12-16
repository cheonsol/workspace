package com.kh.textGame.controller;

import com.kh.textGame.dto.MemberDto;
import com.kh.textGame.dto.MemberStatUpdateDto;
import com.kh.textGame.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    // 인증된 사용자 상세 정보 조회
    @GetMapping("/me")
    public ResponseEntity<MemberDto> getMyDetails(Authentication authentication) {
        String userId = authentication.getName();
        MemberDto memberDto = memberService.getMemberDetails(userId);
        return ResponseEntity.ok(memberDto);
    }

    // 인증된 사용자 스탯 업데이트
    @PutMapping("/me/stats")
    public ResponseEntity<MemberDto> updateMyStats(
            Authentication authentication,
            @RequestBody MemberStatUpdateDto updateDto) {
        String userId = authentication.getName();
        MemberDto updatedMember = memberService.updateMemberStats(userId, updateDto);
        return ResponseEntity.ok(updatedMember);
    }

    // 아이템 구매
    @PostMapping("/me/items/{itemId}/buy")
    public ResponseEntity<MemberDto> buyItem(
            Authentication authentication,
            @PathVariable Long itemId,
            @RequestParam(defaultValue = "1") int quantity) {
        String userId = authentication.getName();
        try {
            MemberDto updatedMember = memberService.buyItem(userId, itemId, quantity);
            return ResponseEntity.ok(updatedMember);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // 에러 메시지 포함 가능
        }
    }

    // 아이템 판매
    @PostMapping("/me/items/{memberItemId}/sell")
    public ResponseEntity<MemberDto> sellItem(
            Authentication authentication,
            @PathVariable Long memberItemId,
            @RequestParam(defaultValue = "1") int quantity) {
        String userId = authentication.getName();
        try {
            MemberDto updatedMember = memberService.sellItem(userId, memberItemId, quantity);
            return ResponseEntity.ok(updatedMember);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // 아이템 사용
    @PostMapping("/me/items/{memberItemId}/use")
    public ResponseEntity<MemberDto> useItem(
            Authentication authentication,
            @PathVariable Long memberItemId) {
        String userId = authentication.getName();
        try {
            MemberDto updatedMember = memberService.useItem(userId, memberItemId);
            return ResponseEntity.ok(updatedMember);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // 아이템 장착/해제 토글
    @PostMapping("/me/items/{memberItemId}/toggle-equip")
    public ResponseEntity<MemberDto> toggleEquipItem(
            Authentication authentication,
            @PathVariable Long memberItemId) {
        String userId = authentication.getName();
        try {
            MemberDto updatedMember = memberService.toggleEquipItem(userId, memberItemId);
            return ResponseEntity.ok(updatedMember);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
