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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@PreAuthorize("isAuthenticated()")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberDto> getMyDetails(Authentication authentication) {
        String username = authentication.getName();
        MemberDto memberDto = memberService.getMemberDetails(username);
        return ResponseEntity.ok(memberDto);
    }

    @PutMapping("/me/stats")
    public ResponseEntity<MemberDto> updateMyStats(Authentication authentication, @RequestBody MemberStatUpdateDto updateDto) {
        String username = authentication.getName();
        MemberDto updatedMember = memberService.updateMemberStats(username, updateDto);
        return ResponseEntity.ok(updatedMember);
    }

    @PostMapping("/me/items/{itemId}/buy")
    public ResponseEntity<MemberDto> buyItem(Authentication authentication, @PathVariable Long itemId, @RequestParam(defaultValue = "1") int quantity) {
        String username = authentication.getName();
        try {
            MemberDto updatedMember = memberService.buyItem(username, itemId, quantity);
            return ResponseEntity.ok(updatedMember);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/me/items/{memberItemId}/sell")
    public ResponseEntity<MemberDto> sellItem(Authentication authentication, @PathVariable Long memberItemId, @RequestParam(defaultValue = "1") int quantity) {
        String username = authentication.getName();
        try {
            MemberDto updatedMember = memberService.sellItem(username, memberItemId, quantity);
            return ResponseEntity.ok(updatedMember);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/me/items/{memberItemId}/use")
    public ResponseEntity<MemberDto> useItem(Authentication authentication, @PathVariable Long memberItemId) {
        String username = authentication.getName();
        try {
            MemberDto updatedMember = memberService.useItem(username, memberItemId);
            return ResponseEntity.ok(updatedMember);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/me/items/{memberItemId}/toggle-equip")
    public ResponseEntity<MemberDto> toggleEquipItem(Authentication authentication, @PathVariable Long memberItemId) {
        String username = authentication.getName();
        try {
            MemberDto updatedMember = memberService.toggleEquipItem(username, memberItemId);
            return ResponseEntity.ok(updatedMember);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}